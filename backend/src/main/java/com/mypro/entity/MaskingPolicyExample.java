package com.mypro.entity;

import java.util.ArrayList;
import java.util.List;

public class MaskingPolicyExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MaskingPolicyExample() {
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

        public Criteria andPolicyIdIsNull() {
            addCriterion("policy_id is null");
            return (Criteria) this;
        }

        public Criteria andPolicyIdIsNotNull() {
            addCriterion("policy_id is not null");
            return (Criteria) this;
        }

        public Criteria andPolicyIdEqualTo(Integer value) {
            addCriterion("policy_id =", value, "policyId");
            return (Criteria) this;
        }

        public Criteria andPolicyIdNotEqualTo(Integer value) {
            addCriterion("policy_id <>", value, "policyId");
            return (Criteria) this;
        }

        public Criteria andPolicyIdGreaterThan(Integer value) {
            addCriterion("policy_id >", value, "policyId");
            return (Criteria) this;
        }

        public Criteria andPolicyIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("policy_id >=", value, "policyId");
            return (Criteria) this;
        }

        public Criteria andPolicyIdLessThan(Integer value) {
            addCriterion("policy_id <", value, "policyId");
            return (Criteria) this;
        }

        public Criteria andPolicyIdLessThanOrEqualTo(Integer value) {
            addCriterion("policy_id <=", value, "policyId");
            return (Criteria) this;
        }

        public Criteria andPolicyIdIn(List<Integer> values) {
            addCriterion("policy_id in", values, "policyId");
            return (Criteria) this;
        }

        public Criteria andPolicyIdNotIn(List<Integer> values) {
            addCriterion("policy_id not in", values, "policyId");
            return (Criteria) this;
        }

        public Criteria andPolicyIdBetween(Integer value1, Integer value2) {
            addCriterion("policy_id between", value1, value2, "policyId");
            return (Criteria) this;
        }

        public Criteria andPolicyIdNotBetween(Integer value1, Integer value2) {
            addCriterion("policy_id not between", value1, value2, "policyId");
            return (Criteria) this;
        }

        public Criteria andPolicyNameIsNull() {
            addCriterion("policy_name is null");
            return (Criteria) this;
        }

        public Criteria andPolicyNameIsNotNull() {
            addCriterion("policy_name is not null");
            return (Criteria) this;
        }

        public Criteria andPolicyNameEqualTo(String value) {
            addCriterion("policy_name =", value, "policyName");
            return (Criteria) this;
        }

        public Criteria andPolicyNameNotEqualTo(String value) {
            addCriterion("policy_name <>", value, "policyName");
            return (Criteria) this;
        }

        public Criteria andPolicyNameGreaterThan(String value) {
            addCriterion("policy_name >", value, "policyName");
            return (Criteria) this;
        }

        public Criteria andPolicyNameGreaterThanOrEqualTo(String value) {
            addCriterion("policy_name >=", value, "policyName");
            return (Criteria) this;
        }

        public Criteria andPolicyNameLessThan(String value) {
            addCriterion("policy_name <", value, "policyName");
            return (Criteria) this;
        }

        public Criteria andPolicyNameLessThanOrEqualTo(String value) {
            addCriterion("policy_name <=", value, "policyName");
            return (Criteria) this;
        }

        public Criteria andPolicyNameLike(String value) {
            addCriterion("policy_name like", value, "policyName");
            return (Criteria) this;
        }

        public Criteria andPolicyNameNotLike(String value) {
            addCriterion("policy_name not like", value, "policyName");
            return (Criteria) this;
        }

        public Criteria andPolicyNameIn(List<String> values) {
            addCriterion("policy_name in", values, "policyName");
            return (Criteria) this;
        }

        public Criteria andPolicyNameNotIn(List<String> values) {
            addCriterion("policy_name not in", values, "policyName");
            return (Criteria) this;
        }

        public Criteria andPolicyNameBetween(String value1, String value2) {
            addCriterion("policy_name between", value1, value2, "policyName");
            return (Criteria) this;
        }

        public Criteria andPolicyNameNotBetween(String value1, String value2) {
            addCriterion("policy_name not between", value1, value2, "policyName");
            return (Criteria) this;
        }

        public Criteria andTargetFieldIsNull() {
            addCriterion("target_field is null");
            return (Criteria) this;
        }

        public Criteria andTargetFieldIsNotNull() {
            addCriterion("target_field is not null");
            return (Criteria) this;
        }

        public Criteria andTargetFieldEqualTo(String value) {
            addCriterion("target_field =", value, "targetField");
            return (Criteria) this;
        }

        public Criteria andTargetFieldNotEqualTo(String value) {
            addCriterion("target_field <>", value, "targetField");
            return (Criteria) this;
        }

        public Criteria andTargetFieldGreaterThan(String value) {
            addCriterion("target_field >", value, "targetField");
            return (Criteria) this;
        }

        public Criteria andTargetFieldGreaterThanOrEqualTo(String value) {
            addCriterion("target_field >=", value, "targetField");
            return (Criteria) this;
        }

        public Criteria andTargetFieldLessThan(String value) {
            addCriterion("target_field <", value, "targetField");
            return (Criteria) this;
        }

        public Criteria andTargetFieldLessThanOrEqualTo(String value) {
            addCriterion("target_field <=", value, "targetField");
            return (Criteria) this;
        }

        public Criteria andTargetFieldLike(String value) {
            addCriterion("target_field like", value, "targetField");
            return (Criteria) this;
        }

        public Criteria andTargetFieldNotLike(String value) {
            addCriterion("target_field not like", value, "targetField");
            return (Criteria) this;
        }

        public Criteria andTargetFieldIn(List<String> values) {
            addCriterion("target_field in", values, "targetField");
            return (Criteria) this;
        }

        public Criteria andTargetFieldNotIn(List<String> values) {
            addCriterion("target_field not in", values, "targetField");
            return (Criteria) this;
        }

        public Criteria andTargetFieldBetween(String value1, String value2) {
            addCriterion("target_field between", value1, value2, "targetField");
            return (Criteria) this;
        }

        public Criteria andTargetFieldNotBetween(String value1, String value2) {
            addCriterion("target_field not between", value1, value2, "targetField");
            return (Criteria) this;
        }

        public Criteria andRuleIsNull() {
            addCriterion("rule is null");
            return (Criteria) this;
        }

        public Criteria andRuleIsNotNull() {
            addCriterion("rule is not null");
            return (Criteria) this;
        }

        public Criteria andRuleEqualTo(String value) {
            addCriterion("rule =", value, "rule");
            return (Criteria) this;
        }

        public Criteria andRuleNotEqualTo(String value) {
            addCriterion("rule <>", value, "rule");
            return (Criteria) this;
        }

        public Criteria andRuleGreaterThan(String value) {
            addCriterion("rule >", value, "rule");
            return (Criteria) this;
        }

        public Criteria andRuleGreaterThanOrEqualTo(String value) {
            addCriterion("rule >=", value, "rule");
            return (Criteria) this;
        }

        public Criteria andRuleLessThan(String value) {
            addCriterion("rule <", value, "rule");
            return (Criteria) this;
        }

        public Criteria andRuleLessThanOrEqualTo(String value) {
            addCriterion("rule <=", value, "rule");
            return (Criteria) this;
        }

        public Criteria andRuleLike(String value) {
            addCriterion("rule like", value, "rule");
            return (Criteria) this;
        }

        public Criteria andRuleNotLike(String value) {
            addCriterion("rule not like", value, "rule");
            return (Criteria) this;
        }

        public Criteria andRuleIn(List<String> values) {
            addCriterion("rule in", values, "rule");
            return (Criteria) this;
        }

        public Criteria andRuleNotIn(List<String> values) {
            addCriterion("rule not in", values, "rule");
            return (Criteria) this;
        }

        public Criteria andRuleBetween(String value1, String value2) {
            addCriterion("rule between", value1, value2, "rule");
            return (Criteria) this;
        }

        public Criteria andRuleNotBetween(String value1, String value2) {
            addCriterion("rule not between", value1, value2, "rule");
            return (Criteria) this;
        }

        public Criteria andPolicyDescIsNull() {
            addCriterion("policy_desc is null");
            return (Criteria) this;
        }

        public Criteria andPolicyDescIsNotNull() {
            addCriterion("policy_desc is not null");
            return (Criteria) this;
        }

        public Criteria andPolicyDescEqualTo(String value) {
            addCriterion("policy_desc =", value, "policyDesc");
            return (Criteria) this;
        }

        public Criteria andPolicyDescNotEqualTo(String value) {
            addCriterion("policy_desc <>", value, "policyDesc");
            return (Criteria) this;
        }

        public Criteria andPolicyDescGreaterThan(String value) {
            addCriterion("policy_desc >", value, "policyDesc");
            return (Criteria) this;
        }

        public Criteria andPolicyDescGreaterThanOrEqualTo(String value) {
            addCriterion("policy_desc >=", value, "policyDesc");
            return (Criteria) this;
        }

        public Criteria andPolicyDescLessThan(String value) {
            addCriterion("policy_desc <", value, "policyDesc");
            return (Criteria) this;
        }

        public Criteria andPolicyDescLessThanOrEqualTo(String value) {
            addCriterion("policy_desc <=", value, "policyDesc");
            return (Criteria) this;
        }

        public Criteria andPolicyDescLike(String value) {
            addCriterion("policy_desc like", value, "policyDesc");
            return (Criteria) this;
        }

        public Criteria andPolicyDescNotLike(String value) {
            addCriterion("policy_desc not like", value, "policyDesc");
            return (Criteria) this;
        }

        public Criteria andPolicyDescIn(List<String> values) {
            addCriterion("policy_desc in", values, "policyDesc");
            return (Criteria) this;
        }

        public Criteria andPolicyDescNotIn(List<String> values) {
            addCriterion("policy_desc not in", values, "policyDesc");
            return (Criteria) this;
        }

        public Criteria andPolicyDescBetween(String value1, String value2) {
            addCriterion("policy_desc between", value1, value2, "policyDesc");
            return (Criteria) this;
        }

        public Criteria andPolicyDescNotBetween(String value1, String value2) {
            addCriterion("policy_desc not between", value1, value2, "policyDesc");
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