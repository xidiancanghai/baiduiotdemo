package com.example.baiduiotdemo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.baiduiotdemo.conf.BaiduConf;
import com.example.baiduiotdemo.conf.RabbitMQInit;
import com.example.baiduiotdemo.dao.ApplianceMapper;
import com.example.baiduiotdemo.lib.Request;
import com.example.baiduiotdemo.lib.Response;
import com.example.baiduiotdemo.pojo.Appliance;
import com.example.baiduiotdemo.service.ControlApplianceService;
import com.example.baiduiotdemo.service.OperateService;
import com.example.baiduiotdemo.util.BdLog;
import com.example.baiduiotdemo.util.PushMessage;
import com.rabbitmq.client.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ControlApplianceServiceImpl implements ControlApplianceService {

    @Autowired
    private ApplianceMapper applianceMapper;

    @Autowired
    private OperateService operateService;

    @Autowired
    private PushMessage pushMessage;

    @Override
    public void ControlAppliance(Request request, Response response) {
       Appliance appliance = this.initControlAppliance(request);
       if (appliance == null) {
           response.setName(BaiduConf.NO_TARGET_ERRORS);
           return;
       }
       String action = this.getAction(request.getName());
       if (action.equals("")) {
           response.setName(BaiduConf.NO_SUPPORT_ACTION_ERROR);
           return;
       }

       if (!this.supportAction(appliance.getActions(), action)) {
           response.setName(BaiduConf.NO_SUPPORT_ACTION_ERROR);
           return;
       }
        String formatMessage = operateService.formatMessage(action, request.getPayload());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("order", formatMessage);
        jsonObject.put("mac", appliance.getMac());
        pushMessage.SendMessageTTL(RabbitMQInit.NORMAL_EXCHANGE, jsonObject.toJSONString(), RabbitMQInit.ROUTTING_KEY, 60000);
    }

    private Appliance initControlAppliance(Request request) {
        String applianceId = request.getPayload().getJSONObject("appliance").getString("applianceId");
        String userId = request.getPayload().getString("accessToken");
        Appliance appliance = applianceMapper.getApplianceByUserIdApplianceId(userId, applianceId);
        return appliance;
    }

    private String getAction(String name) {
        Pattern pattern = Pattern.compile("(.*)Request");
        Matcher matcher = pattern.matcher(name);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return "";
    }

    private boolean supportAction(List<String> actionsList, String action) {
        Iterator<String> iterator = actionsList.iterator();
        boolean supportAction = false;
        while (iterator.hasNext()) {
            String temp = iterator.next().replaceAll("\"", "");
            if (temp.toLowerCase().equals(action.toLowerCase())) {
                supportAction = true;
                break;
            }
        }
        return supportAction;
    }


}
