package com.example.baiduiotdemo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.baiduiotdemo.service.OperateService;
import com.example.baiduiotdemo.util.BdLog;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.List;

@Service
public class OperateServiceImpl implements OperateService {

    @Override
    public String formatMessage(String action, JSONObject payload) {
        String res = "";
        BdLog.info("action=" + action);
        try {
            Method method = this.getClass().getMethod(action, JSONObject.class);
            res = (String)method.invoke(this, payload);
        } catch (Exception e) {
            e.printStackTrace();
            BdLog.info("error_msg=" + e.getMessage());
        }
        return res;
    }



    public String turnOn(JSONObject payload) {
        return "set power on";
    }

    public String turnOff(JSONObject payload) {
        return "set power off";
    }

    public String timingTurnOn(JSONObject payload) {
        return "set power on";
    }

}
