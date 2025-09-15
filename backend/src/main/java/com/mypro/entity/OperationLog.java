package com.mypro.entity;

import java.util.Date;

public class OperationLog {
    private Integer logId;

    private String actionType;

    private Integer userId;

    private Date actionTime;

    private String actionDetail;

    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType == null ? null : actionType.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getActionTime() {
        return actionTime;
    }

    public void setActionTime(Date actionTime) {
        this.actionTime = actionTime;
    }

    public String getActionDetail() {
        return actionDetail;
    }

    public void setActionDetail(String actionDetail) {
        this.actionDetail = actionDetail == null ? null : actionDetail.trim();
    }
}