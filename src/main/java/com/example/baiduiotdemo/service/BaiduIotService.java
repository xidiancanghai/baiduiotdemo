package com.example.baiduiotdemo.service;

import com.example.baiduiotdemo.lib.Request;
import com.example.baiduiotdemo.lib.Response;
import org.springframework.stereotype.Service;

public interface BaiduIotService {

    public void execute(Request request, Response response);

}
