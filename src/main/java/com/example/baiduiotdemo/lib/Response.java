package com.example.baiduiotdemo.lib;

import com.alibaba.fastjson.JSONObject;
import com.example.baiduiotdemo.util.Utils;

public class Response {

    private String nameSpace = "";
    private String name = "";
    private String messageId = "";
    private String payloadVersion = "";
    private JSONObject payload;

    public Response(Request request) {
        nameSpace = request.getNameSpace();
        payloadVersion = request.getPayloadVersion();
        messageId = Utils.generateMessageId();
    }

    public void setNameSpace(String nameSpace) {
        this.nameSpace = nameSpace;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public void setPayloadVersion(String payloadVersion) {
        this.payloadVersion = payloadVersion;
    }

    public void setPayload(JSONObject payload) {
        this.payload = payload;
    }

    public String BuildResponseString() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("header", this.getHeader());
        if (payload == null) {
            payload = new JSONObject();
        }
        jsonObject.put("payload", this.payload);
        return jsonObject.toJSONString();
    }

    private JSONObject getHeader() {
        JSONObject jsonObject = new JSONObject();
        if (!this.nameSpace.equals("")) {
            jsonObject.put("namespace", this.nameSpace);
        }
        if (!this.name.equals("")) {
            jsonObject.put("name", this.name);
        }
        if (!this.messageId.equals("")) {
            jsonObject.put("messageId", this.messageId);
        }
        if (!this.payloadVersion.equals("")) {
            jsonObject.put("payloadVersion", this.payloadVersion);
        }
        return jsonObject;
    }
}
