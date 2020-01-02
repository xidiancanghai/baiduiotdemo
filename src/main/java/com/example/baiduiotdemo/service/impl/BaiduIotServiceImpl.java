package com.example.baiduiotdemo.service.impl;

import com.example.baiduiotdemo.conf.BaiduConf;
import com.example.baiduiotdemo.dao.ApplianceMapper;
import com.example.baiduiotdemo.lib.Request;
import com.example.baiduiotdemo.lib.Response;
import com.example.baiduiotdemo.pojo.Appliance;
import com.example.baiduiotdemo.service.BaiduIotService;
import com.example.baiduiotdemo.service.ControlApplianceService;
import com.example.baiduiotdemo.service.DisCoverApplianceService;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BaiduIotServiceImpl implements BaiduIotService {

    @Autowired
    private DisCoverApplianceService disCoverApplianceService;

    @Autowired
    private ControlApplianceService controlApplianceService;

    @Override
    public void execute(Request request, Response response) {
        switch (request.getNameSpace()){
            case BaiduConf.DISCOVER_NAMESPACE:
                disCoverApplianceService.discoverAppliance(request, response);
                break;
            case BaiduConf.CONTROL_NAMESPACE:
                controlApplianceService.ControlAppliance(request, response);
                break;
            default:
                break;
        }
        return;
    }


}
