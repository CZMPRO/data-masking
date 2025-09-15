package com.mypro.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AuditLogExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AuditLogExample() {
        oredCriteria = new ArrayList<>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andAuditIdIsNull() {
            addCriterion("audit_id is null");
            return (Criteria) this;
        }

        public Criteria andAuditIdIsNotNull() {
            addCriterion("audit_id is not null");
            return (Criteria) this;
        }

        public Criteria andAuditIdEqualTo(Integer value) {
            addCriterion("audit_id =", value, "auditId");
            return (Criteria) this;
        }

        public Criteria andAuditIdNotEqualTo(Integer value) {
            addCriterion("audit_id <>", value, "auditId");
            return (Criteria) this;
        }

        public Criteria andAuditIdGreaterThan(Integer value) {
            addCriterion("audit_id >", value, "auditId");
            return (Criteria) this;
        }

        public Criteria andAuditIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("audit_id >=", value, "auditId");
            return (Criteria) this;
        }

        public Criteria andAuditIdLessThan(Integer value) {
            addCriterion("audit_id <", value, "auditId");
            return (Criteria) this;
        }

        public Criteria andAuditIdLessThanOrEqualTo(Integer value) {
            addCriterion("audit_id <=", value, "auditId");
            return (Criteria) this;
        }

        public Criteria andAuditIdIn(List<Integer> values) {
            addCriterion("audit_id in", values, "auditId");
            return (Criteria) this;
        }

        public Criteria andAuditIdNotIn(List<Integer> values) {
            addCriterion("audit_id not in", values, "auditId");
            return (Criteria) this;
        }

        public Criteria andAuditIdBetween(Integer value1, Integer value2) {
            addCriterion("audit_id between", value1, value2, "auditId");
            return (Criteria) this;
        }

        public Criteria andAuditIdNotBetween(Integer value1, Integer value2) {
            addCriterion("audit_id not between", value1, value2, "auditId");
            return (Criteria) this;
        }

        public Criteria andAuditorIdIsNull() {
            addCriterion("auditor_id is null");
            return (Criteria) this;
        }

        public Criteria andAuditorIdIsNotNull() {
            addCriterion("auditor_id is not null");
            return (Criteria) this;
        }

        public Criteria andAuditorIdEqualTo(Integer value) {
            addCriterion("auditor_id =", value, "auditorId");
            return (Criteria) this;
        }

        public Criteria andAuditorIdNotEqualTo(Integer value) {
            addCriterion("auditor_id <>", value, "auditorId");
            return (Criteria) this;
        }

        public Criteria andAuditorIdGreaterThan(Integer value) {
            addCriterion("auditor_id >", value, "auditorId");
            return (Criteria) this;
        }

        public Criteria andAuditorIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("auditor_id >=", value, "auditorId");
            return (Criteria) this;
        }

        public Criteria andAuditorIdLessThan(Integer value) {
            addCriterion("auditor_id <", value, "auditorId");
            return (Criteria) this;
        }

        public Criteria andAuditorIdLessThanOrEqualTo(Integer value) {
            addCriterion("auditor_id <=", value, "auditorId");
            return (Criteria) this;
        }

        public Criteria andAuditorIdIn(List<Integer> values) {
            addCriterion("auditor_id in", values, "auditorId");
            return (Criteria) this;
        }

        public Criteria andAuditorIdNotIn(List<Integer> values) {
            addCriterion("auditor_id not in", values, "auditorId");
            return (Criteria) this;
        }

        public Criteria andAuditorIdBetween(Integer value1, Integer value2) {
            addCriterion("auditor_id between", value1, value2, "auditorId");
            return (Criteria) this;
        }

        public Criteria andAuditorIdNotBetween(Integer value1, Integer value2) {
            addCriterion("auditor_id not between", value1, value2, "auditorId");
            return (Criteria) this;
        }

        public Criteria andAuditTimeIsNull() {
            addCriterion("audit_time is null");
            return (Criteria) this;
        }

        public Criteria andAuditTimeIsNotNull() {
            addCriterion("audit_time is not null");
            return (Criteria) this;
        }

        public Criteria andAuditTimeEqualTo(Date value) {
            addCriterion("audit_time =", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeNotEqualTo(Date value) {
            addCriterion("audit_time <>", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeGreaterThan(Date value) {
            addCriterion("audit_time >", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("audit_time >=", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeLessThan(Date value) {
            addCriterion("audit_time <", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeLessThanOrEqualTo(Date value) {
            addCriterion("audit_time <=", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeIn(List<Date> values) {
            addCriterion("audit_time in", values, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeNotIn(List<Date> values) {
            addCriterion("audit_time not in", values, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeBetween(Date value1, Date value2) {
            addCriterion("audit_time between", value1, value2, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeNotBetween(Date value1, Date value2) {
            addCriterion("audit_time not between", value1, value2, "auditTime");
            return (Criteria) this;
        }

        public Criteria andReportSummaryIsNull() {
            addCriterion("report_summary is null");
            return (Criteria) this;
        }

        public Criteria andReportSummaryIsNotNull() {
            addCriterion("report_summary is not null");
            return (Criteria) this;
        }

        public Criteria andReportSummaryEqualTo(String value) {
            addCriterion("report_summary =", value, "reportSummary");
            return (Criteria) this;
        }

        public Criteria andReportSummaryNotEqualTo(String value) {
            addCriterion("report_summary <>", value, "reportSummary");
            return (Criteria) this;
        }

        public Criteria andReportSummaryGreaterThan(String value) {
            addCriterion("report_summary >", value, "reportSummary");
            return (Criteria) this;
        }

        public Criteria andReportSummaryGreaterThanOrEqualTo(String value) {
            addCriterion("report_summary >=", value, "reportSummary");
            return (Criteria) this;
        }

        public Criteria andReportSummaryLessThan(String value) {
            addCriterion("report_summary <", value, "reportSummary");
            return (Criteria) this;
        }

        public Criteria andReportSummaryLessThanOrEqualTo(String value) {
            addCriterion("report_summary <=", value, "reportSummary");
            return (Criteria) this;
        }

        public Criteria andReportSummaryLike(String value) {
            addCriterion("report_summary like", value, "reportSummary");
            return (Criteria) this;
        }

        public Criteria andReportSummaryNotLike(String value) {
            addCriterion("report_summary not like", value, "reportSummary");
            return (Criteria) this;
        }

        public Criteria andReportSummaryIn(List<String> values) {
            addCriterion("report_summary in", values, "reportSummary");
            return (Criteria) this;
        }

        public Criteria andReportSummaryNotIn(List<String> values) {
            addCriterion("report_summary not in", values, "reportSummary");
            return (Criteria) this;
        }

        public Criteria andReportSummaryBetween(String value1, String value2) {
            addCriterion("report_summary between", value1, value2, "reportSummary");
            return (Criteria) this;
        }

        public Criteria andReportSummaryNotBetween(String value1, String value2) {
            addCriterion("report_summary not between", value1, value2, "reportSummary");
            return (Criteria) this;
        }

        public Criteria andLogIdIsNull() {
            addCriterion("log_id is null");
            return (Criteria) this;
        }

        public Criteria andLogIdIsNotNull() {
            addCriterion("log_id is not null");
            return (Criteria) this;
        }

        public Criteria andLogIdEqualTo(Integer value) {
            addCriterion("log_id =", value, "logId");
            return (Criteria) this;
        }

        public Criteria andLogIdNotEqualTo(Integer value) {
            addCriterion("log_id <>", value, "logId");
            return (Criteria) this;
        }

        public Criteria andLogIdGreaterThan(Integer value) {
            addCriterion("log_id >", value, "logId");
            return (Criteria) this;
        }

        public Criteria andLogIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("log_id >=", value, "logId");
            return (Criteria) this;
        }

        public Criteria andLogIdLessThan(Integer value) {
            addCriterion("log_id <", value, "logId");
            return (Criteria) this;
        }

        public Criteria andLogIdLessThanOrEqualTo(Integer value) {
            addCriterion("log_id <=", value, "logId");
            return (Criteria) this;
        }

        public Criteria andLogIdIn(List<Integer> values) {
            addCriterion("log_id in", values, "logId");
            return (Criteria) this;
        }

        public Criteria andLogIdNotIn(List<Integer> values) {
            addCriterion("log_id not in", values, "logId");
            return (Criteria) this;
        }

        public Criteria andLogIdBetween(Integer value1, Integer value2) {
            addCriterion("log_id between", value1, value2, "logId");
            return (Criteria) this;
        }

        public Criteria andLogIdNotBetween(Integer value1, Integer value2) {
            addCriterion("log_id not between", value1, value2, "logId");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}