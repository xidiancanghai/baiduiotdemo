package com.example.baiduiotdemo.dao;

import com.example.baiduiotdemo.pojo.Appliance;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface ApplianceMapper {
    public Appliance getApplianceByUserIdApplianceId(@Param("userId") String userId, @Param("applianceId") String applianceId);

    public int insertAppliance(Appliance appliance);

    public List<Appliance> getAllApplianceByUserId(String userId);
}
