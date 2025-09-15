package com.mypro.entity;

import java.util.Date;

public class AuditLog {
    private Integer auditId;

    private Integer auditorId;

    private Date auditTime;

    private String reportSummary;

    private Integer logId;

    public Integer getAuditId() {
        return auditId;
    }

    public void setAuditId(Integer auditId) {
        this.auditId = auditId;
    }

    public Integer getAuditorId() {
        return auditorId;
    }

    public void setAuditorId(Integer auditorId) {
        this.auditorId = auditorId;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public String getReportSummary() {
        return reportSummary;
    }

    public void setReportSummary(String reportSummary) {
        this.reportSummary = reportSummary == null ? null : reportSummary.trim();
    }

    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }
}