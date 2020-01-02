package com.example.baiduiotdemo.controller;


import com.alibaba.fastjson.JSONObject;
import com.example.baiduiotdemo.lib.*;
import com.example.baiduiotdemo.service.BaiduIotService;
import com.example.baiduiotdemo.util.BdLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class BaiduController {

    @Autowired
    BaiduIotService baiduIotService;

    @RequestMapping(value = "/baidu", method = RequestMethod.POST)
    @ResponseBody
    public String BaiduIndex(@RequestBody JSONObject jsonObject) {
        Request request = new Request(jsonObject);
        Response response = new Response(request);
        baiduIotService.execute(request, response);
        return response.BuildResponseString();
    }

    @RequestMapping(value="/app", method = RequestMethod.GET)
    @ResponseBody
    public String AppIndex() {
        return "hello";
    }
}
