package com.qifenqian.bms.bal.accountResult.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BalInternalDataExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BalInternalDataExample() {
        oredCriteria = new ArrayList<Criteria>();
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
            criteria = new ArrayList<Criterion>();
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

        public Criteria andDataIdIsNull() {
            addCriterion("data_id is null");
            return (Criteria) this;
        }

        public Criteria andDataIdIsNotNull() {
            addCriterion("data_id is not null");
            return (Criteria) this;
        }

        public Criteria andDataIdEqualTo(Integer value) {
            addCriterion("data_id =", value, "dataId");
            return (Criteria) this;
        }

        public Criteria andDataIdNotEqualTo(Integer value) {
            addCriterion("data_id <>", value, "dataId");
            return (Criteria) this;
        }

        public Criteria andDataIdGreaterThan(Integer value) {
            addCriterion("data_id >", value, "dataId");
            return (Criteria) this;
        }

        public Criteria andDataIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("data_id >=", value, "dataId");
            return (Criteria) this;
        }

        public Criteria andDataIdLessThan(Integer value) {
            addCriterion("data_id <", value, "dataId");
            return (Criteria) this;
        }

        public Criteria andDataIdLessThanOrEqualTo(Integer value) {
            addCriterion("data_id <=", value, "dataId");
            return (Criteria) this;
        }

        public Criteria andDataIdIn(List<Integer> values) {
            addCriterion("data_id in", values, "dataId");
            return (Criteria) this;
        }

        public Criteria andDataIdNotIn(List<Integer> values) {
            addCriterion("data_id not in", values, "dataId");
            return (Criteria) this;
        }

        public Criteria andDataIdBetween(Integer value1, Integer value2) {
            addCriterion("data_id between", value1, value2, "dataId");
            return (Criteria) this;
        }

        public Criteria andDataIdNotBetween(Integer value1, Integer value2) {
            addCriterion("data_id not between", value1, value2, "dataId");
            return (Criteria) this;
        }

        public Criteria andChannelIdIsNull() {
            addCriterion("channel_id is null");
            return (Criteria) this;
        }

        public Criteria andChannelIdIsNotNull() {
            addCriterion("channel_id is not null");
            return (Criteria) this;
        }

        public Criteria andChannelIdEqualTo(Integer value) {
            addCriterion("channel_id =", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdNotEqualTo(Integer value) {
            addCriterion("channel_id <>", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdGreaterThan(Integer value) {
            addCriterion("channel_id >", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("channel_id >=", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdLessThan(Integer value) {
            addCriterion("channel_id <", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdLessThanOrEqualTo(Integer value) {
            addCriterion("channel_id <=", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdIn(List<Integer> values) {
            addCriterion("channel_id in", values, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdNotIn(List<Integer> values) {
            addCriterion("channel_id not in", values, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdBetween(Integer value1, Integer value2) {
            addCriterion("channel_id between", value1, value2, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdNotBetween(Integer value1, Integer value2) {
            addCriterion("channel_id not between", value1, value2, "channelId");
            return (Criteria) this;
        }

        public Criteria andClearIdIsNull() {
            addCriterion("clear_id is null");
            return (Criteria) this;
        }

        public Criteria andClearIdIsNotNull() {
            addCriterion("clear_id is not null");
            return (Criteria) this;
        }

        public Criteria andClearIdEqualTo(String value) {
            addCriterion("clear_id =", value, "clearId");
            return (Criteria) this;
        }

        public Criteria andClearIdNotEqualTo(String value) {
            addCriterion("clear_id <>", value, "clearId");
            return (Criteria) this;
        }

        public Criteria andClearIdGreaterThan(String value) {
            addCriterion("clear_id >", value, "clearId");
            return (Criteria) this;
        }

        public Criteria andClearIdGreaterThanOrEqualTo(String value) {
            addCriterion("clear_id >=", value, "clearId");
            return (Criteria) this;
        }

        public Criteria andClearIdLessThan(String value) {
            addCriterion("clear_id <", value, "clearId");
            return (Criteria) this;
        }

        public Criteria andClearIdLessThanOrEqualTo(String value) {
            addCriterion("clear_id <=", value, "clearId");
            return (Criteria) this;
        }

        public Criteria andClearIdLike(String value) {
            addCriterion("clear_id like", value, "clearId");
            return (Criteria) this;
        }

        public Criteria andClearIdNotLike(String value) {
            addCriterion("clear_id not like", value, "clearId");
            return (Criteria) this;
        }

        public Criteria andClearIdIn(List<String> values) {
            addCriterion("clear_id in", values, "clearId");
            return (Criteria) this;
        }

        public Criteria andClearIdNotIn(List<String> values) {
            addCriterion("clear_id not in", values, "clearId");
            return (Criteria) this;
        }

        public Criteria andClearIdBetween(String value1, String value2) {
            addCriterion("clear_id between", value1, value2, "clearId");
            return (Criteria) this;
        }

        public Criteria andClearIdNotBetween(String value1, String value2) {
            addCriterion("clear_id not between", value1, value2, "clearId");
            return (Criteria) this;
        }

        public Criteria andWorkDateIsNull() {
            addCriterion("work_date is null");
            return (Criteria) this;
        }

        public Criteria andWorkDateIsNotNull() {
            addCriterion("work_date is not null");
            return (Criteria) this;
        }

        public Criteria andWorkDateEqualTo(String value) {
            addCriterion("work_date =", value, "workDate");
            return (Criteria) this;
        }

        public Criteria andWorkDateNotEqualTo(String value) {
            addCriterion("work_date <>", value, "workDate");
            return (Criteria) this;
        }

        public Criteria andWorkDateGreaterThan(String value) {
            addCriterion("work_date >", value, "workDate");
            return (Criteria) this;
        }

        public Criteria andWorkDateGreaterThanOrEqualTo(String value) {
            addCriterion("work_date >=", value, "workDate");
            return (Criteria) this;
        }

        public Criteria andWorkDateLessThan(String value) {
            addCriterion("work_date <", value, "workDate");
            return (Criteria) this;
        }

        public Criteria andWorkDateLessThanOrEqualTo(String value) {
            addCriterion("work_date <=", value, "workDate");
            return (Criteria) this;
        }

        public Criteria andWorkDateLike(String value) {
            addCriterion("work_date like", value, "workDate");
            return (Criteria) this;
        }

        public Criteria andWorkDateNotLike(String value) {
            addCriterion("work_date not like", value, "workDate");
            return (Criteria) this;
        }

        public Criteria andWorkDateIn(List<String> values) {
            addCriterion("work_date in", values, "workDate");
            return (Criteria) this;
        }

        public Criteria andWorkDateNotIn(List<String> values) {
            addCriterion("work_date not in", values, "workDate");
            return (Criteria) this;
        }

        public Criteria andWorkDateBetween(String value1, String value2) {
            addCriterion("work_date between", value1, value2, "workDate");
            return (Criteria) this;
        }

        public Criteria andWorkDateNotBetween(String value1, String value2) {
            addCriterion("work_date not between", value1, value2, "workDate");
            return (Criteria) this;
        }

        public Criteria andInstUserIsNull() {
            addCriterion("inst_user is null");
            return (Criteria) this;
        }

        public Criteria andInstUserIsNotNull() {
            addCriterion("inst_user is not null");
            return (Criteria) this;
        }

        public Criteria andInstUserEqualTo(Integer value) {
            addCriterion("inst_user =", value, "instUser");
            return (Criteria) this;
        }

        public Criteria andInstUserNotEqualTo(Integer value) {
            addCriterion("inst_user <>", value, "instUser");
            return (Criteria) this;
        }

        public Criteria andInstUserGreaterThan(Integer value) {
            addCriterion("inst_user >", value, "instUser");
            return (Criteria) this;
        }

        public Criteria andInstUserGreaterThanOrEqualTo(Integer value) {
            addCriterion("inst_user >=", value, "instUser");
            return (Criteria) this;
        }

        public Criteria andInstUserLessThan(Integer value) {
            addCriterion("inst_user <", value, "instUser");
            return (Criteria) this;
        }

        public Criteria andInstUserLessThanOrEqualTo(Integer value) {
            addCriterion("inst_user <=", value, "instUser");
            return (Criteria) this;
        }

        public Criteria andInstUserIn(List<Integer> values) {
            addCriterion("inst_user in", values, "instUser");
            return (Criteria) this;
        }

        public Criteria andInstUserNotIn(List<Integer> values) {
            addCriterion("inst_user not in", values, "instUser");
            return (Criteria) this;
        }

        public Criteria andInstUserBetween(Integer value1, Integer value2) {
            addCriterion("inst_user between", value1, value2, "instUser");
            return (Criteria) this;
        }

        public Criteria andInstUserNotBetween(Integer value1, Integer value2) {
            addCriterion("inst_user not between", value1, value2, "instUser");
            return (Criteria) this;
        }

        public Criteria andInstDateIsNull() {
            addCriterion("inst_date is null");
            return (Criteria) this;
        }

        public Criteria andInstDateIsNotNull() {
            addCriterion("inst_date is not null");
            return (Criteria) this;
        }

        public Criteria andInstDateEqualTo(String value) {
            addCriterion("inst_date =", value, "instDate");
            return (Criteria) this;
        }

        public Criteria andInstDateNotEqualTo(String value) {
            addCriterion("inst_date <>", value, "instDate");
            return (Criteria) this;
        }

        public Criteria andInstDateGreaterThan(String value) {
            addCriterion("inst_date >", value, "instDate");
            return (Criteria) this;
        }

        public Criteria andInstDateGreaterThanOrEqualTo(String value) {
            addCriterion("inst_date >=", value, "instDate");
            return (Criteria) this;
        }

        public Criteria andInstDateLessThan(String value) {
            addCriterion("inst_date <", value, "instDate");
            return (Criteria) this;
        }

        public Criteria andInstDateLessThanOrEqualTo(String value) {
            addCriterion("inst_date <=", value, "instDate");
            return (Criteria) this;
        }

        public Criteria andInstDateLike(String value) {
            addCriterion("inst_date like", value, "instDate");
            return (Criteria) this;
        }

        public Criteria andInstDateNotLike(String value) {
            addCriterion("inst_date not like", value, "instDate");
            return (Criteria) this;
        }

        public Criteria andInstDateIn(List<String> values) {
            addCriterion("inst_date in", values, "instDate");
            return (Criteria) this;
        }

        public Criteria andInstDateNotIn(List<String> values) {
            addCriterion("inst_date not in", values, "instDate");
            return (Criteria) this;
        }

        public Criteria andInstDateBetween(String value1, String value2) {
            addCriterion("inst_date between", value1, value2, "instDate");
            return (Criteria) this;
        }

        public Criteria andInstDateNotBetween(String value1, String value2) {
            addCriterion("inst_date not between", value1, value2, "instDate");
            return (Criteria) this;
        }

        public Criteria andInstDatetimeIsNull() {
            addCriterion("inst_datetime is null");
            return (Criteria) this;
        }

        public Criteria andInstDatetimeIsNotNull() {
            addCriterion("inst_datetime is not null");
            return (Criteria) this;
        }

        public Criteria andInstDatetimeEqualTo(Date value) {
            addCriterion("inst_datetime =", value, "instDatetime");
            return (Criteria) this;
        }

        public Criteria andInstDatetimeNotEqualTo(Date value) {
            addCriterion("inst_datetime <>", value, "instDatetime");
            return (Criteria) this;
        }

        public Criteria andInstDatetimeGreaterThan(Date value) {
            addCriterion("inst_datetime >", value, "instDatetime");
            return (Criteria) this;
        }

        public Criteria andInstDatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("inst_datetime >=", value, "instDatetime");
            return (Criteria) this;
        }

        public Criteria andInstDatetimeLessThan(Date value) {
            addCriterion("inst_datetime <", value, "instDatetime");
            return (Criteria) this;
        }

        public Criteria andInstDatetimeLessThanOrEqualTo(Date value) {
            addCriterion("inst_datetime <=", value, "instDatetime");
            return (Criteria) this;
        }

        public Criteria andInstDatetimeIn(List<Date> values) {
            addCriterion("inst_datetime in", values, "instDatetime");
            return (Criteria) this;
        }

        public Criteria andInstDatetimeNotIn(List<Date> values) {
            addCriterion("inst_datetime not in", values, "instDatetime");
            return (Criteria) this;
        }

        public Criteria andInstDatetimeBetween(Date value1, Date value2) {
            addCriterion("inst_datetime between", value1, value2, "instDatetime");
            return (Criteria) this;
        }

        public Criteria andInstDatetimeNotBetween(Date value1, Date value2) {
            addCriterion("inst_datetime not between", value1, value2, "instDatetime");
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