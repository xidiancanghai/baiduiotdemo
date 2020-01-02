package com.example.baiduiotdemo.pojo;

import com.alibaba.fastjson.JSONObject;

import java.math.BigInteger;
import java.util.List;

public class Appliance {

    private Long id;
    private String userId;
    private Long createTime;
    private Long updateTime;
    private List<String> applianceTypes;
    private String applianceId;
    private String mac;
    private String friendlyName;
    private String friendlyDescription;
    private int isReachable;
    private List<String> actions;
    private JSONObject additionalApplianceDetails;
    private int isDelete;
    private List<String> nickNames;

    public Appliance() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public List<String> getApplianceTypes() {
        return applianceTypes;
    }

    public void setApplianceTypes(List<String> applianceTypes) {
        this.applianceTypes = applianceTypes;
    }

    public String getApplianceId() {
        return applianceId;
    }

    public void setApplianceId(String applianceId) {
        this.applianceId = applianceId;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getFriendlyName() {
        return friendlyName;
    }

    public void setFriendlyName(String friendlyName) {
        this.friendlyName = friendlyName;
    }

    public String getFriendlyDescription() {
        return friendlyDescription;
    }

    public void setFriendlyDescription(String friendlyDescription) {
        this.friendlyDescription = friendlyDescription;
    }

    public int getIsReachable() {
        return isReachable;
    }

    public void setIsReachable(int isReachable) {
        this.isReachable = isReachable;
    }

    public List<String> getActions() {
        return actions;
    }

    public void setActions(List<String> actions) {
        this.actions = actions;
    }

    public JSONObject getAdditionalApplianceDetails() {
        return additionalApplianceDetails;
    }

    public void setAdditionalApplianceDetails(JSONObject additionalApplianceDetails) {
        this.additionalApplianceDetails = additionalApplianceDetails;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }

    public List<String> getNickNames() {
        return nickNames;
    }

    public void setNickNames(List<String> nickNames) {
        this.nickNames = nickNames;
    }

    @Override
    public String toString() {
        return String.format("[id=%d, user_id=%s, appliance_id=%s]", this.id, this.userId, this.applianceId);
    }
}
