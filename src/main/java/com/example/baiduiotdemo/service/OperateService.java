package com.example.baiduiotdemo.service;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

public interface OperateService {

    public String formatMessage(String action, JSONObject payload);
}
