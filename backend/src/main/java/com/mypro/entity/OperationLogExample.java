package com.mypro.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OperationLogExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OperationLogExample() {
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

        public Criteria andActionTypeIsNull() {
            addCriterion("action_type is null");
            return (Criteria) this;
        }

        public Criteria andActionTypeIsNotNull() {
            addCriterion("action_type is not null");
            return (Criteria) this;
        }

        public Criteria andActionTypeEqualTo(String value) {
            addCriterion("action_type =", value, "actionType");
            return (Criteria) this;
        }

        public Criteria andActionTypeNotEqualTo(String value) {
            addCriterion("action_type <>", value, "actionType");
            return (Criteria) this;
        }

        public Criteria andActionTypeGreaterThan(String value) {
            addCriterion("action_type >", value, "actionType");
            return (Criteria) this;
        }

        public Criteria andActionTypeGreaterThanOrEqualTo(String value) {
            addCriterion("action_type >=", value, "actionType");
            return (Criteria) this;
        }

        public Criteria andActionTypeLessThan(String value) {
            addCriterion("action_type <", value, "actionType");
            return (Criteria) this;
        }

        public Criteria andActionTypeLessThanOrEqualTo(String value) {
            addCriterion("action_type <=", value, "actionType");
            return (Criteria) this;
        }

        public Criteria andActionTypeLike(String value) {
            addCriterion("action_type like", value, "actionType");
            return (Criteria) this;
        }

        public Criteria andActionTypeNotLike(String value) {
            addCriterion("action_type not like", value, "actionType");
            return (Criteria) this;
        }

        public Criteria andActionTypeIn(List<String> values) {
            addCriterion("action_type in", values, "actionType");
            return (Criteria) this;
        }

        public Criteria andActionTypeNotIn(List<String> values) {
            addCriterion("action_type not in", values, "actionType");
            return (Criteria) this;
        }

        public Criteria andActionTypeBetween(String value1, String value2) {
            addCriterion("action_type between", value1, value2, "actionType");
            return (Criteria) this;
        }

        public Criteria andActionTypeNotBetween(String value1, String value2) {
            addCriterion("action_type not between", value1, value2, "actionType");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Integer value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Integer value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Integer value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Integer value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Integer> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Integer> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Integer value1, Integer value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andActionTimeIsNull() {
            addCriterion("action_time is null");
            return (Criteria) this;
        }

        public Criteria andActionTimeIsNotNull() {
            addCriterion("action_time is not null");
            return (Criteria) this;
        }

        public Criteria andActionTimeEqualTo(Date value) {
            addCriterion("action_time =", value, "actionTime");
            return (Criteria) this;
        }

        public Criteria andActionTimeNotEqualTo(Date value) {
            addCriterion("action_time <>", value, "actionTime");
            return (Criteria) this;
        }

        public Criteria andActionTimeGreaterThan(Date value) {
            addCriterion("action_time >", value, "actionTime");
            return (Criteria) this;
        }

        public Criteria andActionTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("action_time >=", value, "actionTime");
            return (Criteria) this;
        }

        public Criteria andActionTimeLessThan(Date value) {
            addCriterion("action_time <", value, "actionTime");
            return (Criteria) this;
        }

        public Criteria andActionTimeLessThanOrEqualTo(Date value) {
            addCriterion("action_time <=", value, "actionTime");
            return (Criteria) this;
        }

        public Criteria andActionTimeIn(List<Date> values) {
            addCriterion("action_time in", values, "actionTime");
            return (Criteria) this;
        }

        public Criteria andActionTimeNotIn(List<Date> values) {
            addCriterion("action_time not in", values, "actionTime");
            return (Criteria) this;
        }

        public Criteria andActionTimeBetween(Date value1, Date value2) {
            addCriterion("action_time between", value1, value2, "actionTime");
            return (Criteria) this;
        }

        public Criteria andActionTimeNotBetween(Date value1, Date value2) {
            addCriterion("action_time not between", value1, value2, "actionTime");
            return (Criteria) this;
        }

        public Criteria andActionDetailIsNull() {
            addCriterion("action_detail is null");
            return (Criteria) this;
        }

        public Criteria andActionDetailIsNotNull() {
            addCriterion("action_detail is not null");
            return (Criteria) this;
        }

        public Criteria andActionDetailEqualTo(String value) {
            addCriterion("action_detail =", value, "actionDetail");
            return (Criteria) this;
        }

        public Criteria andActionDetailNotEqualTo(String value) {
            addCriterion("action_detail <>", value, "actionDetail");
            return (Criteria) this;
        }

        public Criteria andActionDetailGreaterThan(String value) {
            addCriterion("action_detail >", value, "actionDetail");
            return (Criteria) this;
        }

        public Criteria andActionDetailGreaterThanOrEqualTo(String value) {
            addCriterion("action_detail >=", value, "actionDetail");
            return (Criteria) this;
        }

        public Criteria andActionDetailLessThan(String value) {
            addCriterion("action_detail <", value, "actionDetail");
            return (Criteria) this;
        }

        public Criteria andActionDetailLessThanOrEqualTo(String value) {
            addCriterion("action_detail <=", value, "actionDetail");
            return (Criteria) this;
        }

        public Criteria andActionDetailLike(String value) {
            addCriterion("action_detail like", value, "actionDetail");
            return (Criteria) this;
        }

        public Criteria andActionDetailNotLike(String value) {
            addCriterion("action_detail not like", value, "actionDetail");
            return (Criteria) this;
        }

        public Criteria andActionDetailIn(List<String> values) {
            addCriterion("action_detail in", values, "actionDetail");
            return (Criteria) this;
        }

        public Criteria andActionDetailNotIn(List<String> values) {
            addCriterion("action_detail not in", values, "actionDetail");
            return (Criteria) this;
        }

        public Criteria andActionDetailBetween(String value1, String value2) {
            addCriterion("action_detail between", value1, value2, "actionDetail");
            return (Criteria) this;
        }

        public Criteria andActionDetailNotBetween(String value1, String value2) {
            addCriterion("action_detail not between", value1, value2, "actionDetail");
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