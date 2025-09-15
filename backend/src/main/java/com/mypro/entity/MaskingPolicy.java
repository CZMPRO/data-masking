package com.mypro.entity;

public class MaskingPolicy {
    private Integer policyId;

    private String policyName;

    private String targetField;

    private String rule;

    private String policyDesc;

    @Override
    public String toString() {
        return "MaskingPolicy{" +
                "policyId=" + policyId +
                ", policyName='" + policyName + '\'' +
                ", targetField='" + targetField + '\'' +
                ", rule='" + rule + '\'' +
                ", policyDesc='" + policyDesc + '\'' +
                '}';
    }

    public Integer getPolicyId() {
        return policyId;
    }

    public void setPolicyId(Integer policyId) {
        this.policyId = policyId;
    }

    public String getPolicyName() {
        return policyName;
    }

    public void setPolicyName(String policyName) {
        this.policyName = policyName == null ? null : policyName.trim();
    }

    public String getTargetField() {
        return targetField;
    }

    public void setTargetField(String targetField) {
        this.targetField = targetField == null ? null : targetField.trim();
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule == null ? null : rule.trim();
    }

    public String getPolicyDesc() {
        return policyDesc;
    }

    public void setPolicyDesc(String policyDesc) {
        this.policyDesc = policyDesc == null ? null : policyDesc.trim();
    }


}