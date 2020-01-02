package com.example.baiduiotdemo.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.baiduiotdemo.conf.BaiduConf;
import com.example.baiduiotdemo.dao.ApplianceMapper;
import com.example.baiduiotdemo.lib.Request;
import com.example.baiduiotdemo.lib.Response;
import com.example.baiduiotdemo.pojo.Appliance;
import com.example.baiduiotdemo.service.DisCoverApplianceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisCoverApplianceServiceImpl implements DisCoverApplianceService {

    @Autowired
    private ApplianceMapper applianceMapper;

    @Override
    public void discoverAppliance(Request request, Response response) {
        String userId = "42648469";
        List<Appliance> listAppliance = applianceMapper.getAllApplianceByUserId(userId);
        response.setName(BaiduConf.DISCOVER_APPLIANCE_RESPONSE);
        JSONObject payload = new JSONObject();
        JSONArray discoveredAppliances = new JSONArray();
        JSONArray discoveredGroups = new JSONArray();
        for (Appliance e : listAppliance) {
            discoveredAppliances.add(this.formatApplianceToJson(e));
        }
        payload.put("discoveredAppliances", discoveredAppliances);
        payload.put("discoveredGroups", discoveredGroups);
        response.setPayload(payload);
    }

    private JSONObject formatApplianceToJson(Appliance e) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("actions", e.getActions());
        jsonObject.put("applianceTypes", e.getApplianceTypes());
        jsonObject.put("additionalApplianceDetails", e.getAdditionalApplianceDetails());
        jsonObject.put("applianceId", e.getApplianceId());
        jsonObject.put("friendlyDescription", e.getFriendlyDescription());
        jsonObject.put("friendlyName", e.getFriendlyName());
        jsonObject.put("isReachable", e.getIsReachable());
        jsonObject.put("manufacturerName", "飞人之家");
        jsonObject.put("modelName", "feiren_n1");
        jsonObject.put("version", "1.0.0");
        return jsonObject;
    }
}
