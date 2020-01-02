package com.example.baiduiotdemo.lib;


import com.alibaba.fastjson.JSONObject;

public class
Request {

    private String nameSpace;
    private String name;
    private String payloadVersion;
    private JSONObject payload;

    public Request(JSONObject data) {
        if (data == null) {
            System.out.println("error data");
        }
        if (data.getJSONObject("header") != null) {
            this.nameSpace = data.getJSONObject("header").getString("namespace");
            this.name = data.getJSONObject("header").getString("name");
            this.payloadVersion = data.getJSONObject("header").getString("payloadVersion");
        }
        if (data.getJSONObject("payload") != null) {
            this.payload = data.getJSONObject("payload");
        }
    }


    public String getAccessToken() {
        if (this.payload != null) {
            return this.payload.getString("accessToken");
        }
        return "";
    }

    public String getOpenUid() {
        if (this.payload != null) {
            return this.payload.getString("openUid");
        }
        return "";
    }

    public JSONObject getAppliance() {
        if (this.payload != null) {
            return this.payload.getJSONObject("appliance");
        }
        return null;
    }

    public String getNameSpace() {
        return nameSpace;
    }

    public void setNameSpace(String nameSpace) {
        this.nameSpace = nameSpace;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public JSONObject getPayload() {
        return payload;
    }

    public void setPayload(JSONObject payload) {
        this.payload = payload;
    }

    public String getPayloadVersion() {
        return payloadVersion;
    }

    @Override
    public String toString() {
        JSONObject header = new JSONObject();
        header.put("namespace", this.nameSpace);
        header.put("name", this.name);
        header.put("payloadVersion", this.payloadVersion);
        JSONObject request = new JSONObject();
        request.put("header", header);
        request.put("payload", this.payload);
        return request.toJSONString();
    }
}
