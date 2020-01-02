package com.example.baiduiotdemo.aspect;


import com.alibaba.fastjson.JSONObject;
import com.example.baiduiotdemo.lib.Request;
import com.example.baiduiotdemo.lib.Response;
import com.example.baiduiotdemo.util.BdLog;
import org.aspectj.lang.annotation.*;

@Aspect
public class TokenCheck {

    @Pointcut("execution(* com.example.baiduiotdemo.service.impl.BaiduIotServiceImpl.execute(..))" + "&& args(request, response)")
    public void pointCut(Request request, Response response) {}

    @Before("pointCut(request, response)")
    public void before(Request request, Response response) {
        BdLog.info("Request:" + request.toString());
        String token = request.getAccessToken();
        BdLog.info("token=" + token);
    }

    @AfterReturning("pointCut(request, response)")
    public void afterReturning(Request request, Response response) {
        BdLog.info("Response:" + response.BuildResponseString());

    }
}
