package com.qifenqian.bms.basemanager.merchant.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BmsProtocolContentExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BmsProtocolContentExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andMerchantCodeIsNull() {
            addCriterion("merchant_code is null");
            return (Criteria) this;
        }

        public Criteria andMerchantCodeIsNotNull() {
            addCriterion("merchant_code is not null");
            return (Criteria) this;
        }

        public Criteria andMerchantCodeEqualTo(String value) {
            addCriterion("merchant_code =", value, "merchantCode");
            return (Criteria) this;
        }

        public Criteria andMerchantCodeNotEqualTo(String value) {
            addCriterion("merchant_code <>", value, "merchantCode");
            return (Criteria) this;
        }

        public Criteria andMerchantCodeGreaterThan(String value) {
            addCriterion("merchant_code >", value, "merchantCode");
            return (Criteria) this;
        }

        public Criteria andMerchantCodeGreaterThanOrEqualTo(String value) {
            addCriterion("merchant_code >=", value, "merchantCode");
            return (Criteria) this;
        }

        public Criteria andMerchantCodeLessThan(String value) {
            addCriterion("merchant_code <", value, "merchantCode");
            return (Criteria) this;
        }

        public Criteria andMerchantCodeLessThanOrEqualTo(String value) {
            addCriterion("merchant_code <=", value, "merchantCode");
            return (Criteria) this;
        }

        public Criteria andMerchantCodeLike(String value) {
            addCriterion("merchant_code like", value, "merchantCode");
            return (Criteria) this;
        }

        public Criteria andMerchantCodeNotLike(String value) {
            addCriterion("merchant_code not like", value, "merchantCode");
            return (Criteria) this;
        }

        public Criteria andMerchantCodeIn(List<String> values) {
            addCriterion("merchant_code in", values, "merchantCode");
            return (Criteria) this;
        }

        public Criteria andMerchantCodeNotIn(List<String> values) {
            addCriterion("merchant_code not in", values, "merchantCode");
            return (Criteria) this;
        }

        public Criteria andMerchantCodeBetween(String value1, String value2) {
            addCriterion("merchant_code between", value1, value2, "merchantCode");
            return (Criteria) this;
        }

        public Criteria andMerchantCodeNotBetween(String value1, String value2) {
            addCriterion("merchant_code not between", value1, value2, "merchantCode");
            return (Criteria) this;
        }

        public Criteria andTemplateIdIsNull() {
            addCriterion("template_id is null");
            return (Criteria) this;
        }

        public Criteria andTemplateIdIsNotNull() {
            addCriterion("template_id is not null");
            return (Criteria) this;
        }

        public Criteria andTemplateIdEqualTo(String value) {
            addCriterion("template_id =", value, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdNotEqualTo(String value) {
            addCriterion("template_id <>", value, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdGreaterThan(String value) {
            addCriterion("template_id >", value, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdGreaterThanOrEqualTo(String value) {
            addCriterion("template_id >=", value, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdLessThan(String value) {
            addCriterion("template_id <", value, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdLessThanOrEqualTo(String value) {
            addCriterion("template_id <=", value, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdLike(String value) {
            addCriterion("template_id like", value, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdNotLike(String value) {
            addCriterion("template_id not like", value, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdIn(List<String> values) {
            addCriterion("template_id in", values, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdNotIn(List<String> values) {
            addCriterion("template_id not in", values, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdBetween(String value1, String value2) {
            addCriterion("template_id between", value1, value2, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdNotBetween(String value1, String value2) {
            addCriterion("template_id not between", value1, value2, "templateId");
            return (Criteria) this;
        }

        public Criteria andProtocolContentIsNull() {
            addCriterion("protocol_content is null");
            return (Criteria) this;
        }

        public Criteria andProtocolContentIsNotNull() {
            addCriterion("protocol_content is not null");
            return (Criteria) this;
        }

        public Criteria andProtocolContentEqualTo(String value) {
            addCriterion("protocol_content =", value, "protocolContent");
            return (Criteria) this;
        }

        public Criteria andProtocolContentNotEqualTo(String value) {
            addCriterion("protocol_content <>", value, "protocolContent");
            return (Criteria) this;
        }

        public Criteria andProtocolContentGreaterThan(String value) {
            addCriterion("protocol_content >", value, "protocolContent");
            return (Criteria) this;
        }

        public Criteria andProtocolContentGreaterThanOrEqualTo(String value) {
            addCriterion("protocol_content >=", value, "protocolContent");
            return (Criteria) this;
        }

        public Criteria andProtocolContentLessThan(String value) {
            addCriterion("protocol_content <", value, "protocolContent");
            return (Criteria) this;
        }

        public Criteria andProtocolContentLessThanOrEqualTo(String value) {
            addCriterion("protocol_content <=", value, "protocolContent");
            return (Criteria) this;
        }

        public Criteria andProtocolContentLike(String value) {
            addCriterion("protocol_content like", value, "protocolContent");
            return (Criteria) this;
        }

        public Criteria andProtocolContentNotLike(String value) {
            addCriterion("protocol_content not like", value, "protocolContent");
            return (Criteria) this;
        }

        public Criteria andProtocolContentIn(List<String> values) {
            addCriterion("protocol_content in", values, "protocolContent");
            return (Criteria) this;
        }

        public Criteria andProtocolContentNotIn(List<String> values) {
            addCriterion("protocol_content not in", values, "protocolContent");
            return (Criteria) this;
        }

        public Criteria andProtocolContentBetween(String value1, String value2) {
            addCriterion("protocol_content between", value1, value2, "protocolContent");
            return (Criteria) this;
        }

        public Criteria andProtocolContentNotBetween(String value1, String value2) {
            addCriterion("protocol_content not between", value1, value2, "protocolContent");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("status like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("status not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andMemoIsNull() {
            addCriterion("memo is null");
            return (Criteria) this;
        }

        public Criteria andMemoIsNotNull() {
            addCriterion("memo is not null");
            return (Criteria) this;
        }

        public Criteria andMemoEqualTo(String value) {
            addCriterion("memo =", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotEqualTo(String value) {
            addCriterion("memo <>", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoGreaterThan(String value) {
            addCriterion("memo >", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoGreaterThanOrEqualTo(String value) {
            addCriterion("memo >=", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoLessThan(String value) {
            addCriterion("memo <", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoLessThanOrEqualTo(String value) {
            addCriterion("memo <=", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoLike(String value) {
            addCriterion("memo like", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotLike(String value) {
            addCriterion("memo not like", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoIn(List<String> values) {
            addCriterion("memo in", values, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotIn(List<String> values) {
            addCriterion("memo not in", values, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoBetween(String value1, String value2) {
            addCriterion("memo between", value1, value2, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotBetween(String value1, String value2) {
            addCriterion("memo not between", value1, value2, "memo");
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

        public Criteria andInstUserEqualTo(String value) {
            addCriterion("inst_user =", value, "instUser");
            return (Criteria) this;
        }

        public Criteria andInstUserNotEqualTo(String value) {
            addCriterion("inst_user <>", value, "instUser");
            return (Criteria) this;
        }

        public Criteria andInstUserGreaterThan(String value) {
            addCriterion("inst_user >", value, "instUser");
            return (Criteria) this;
        }

        public Criteria andInstUserGreaterThanOrEqualTo(String value) {
            addCriterion("inst_user >=", value, "instUser");
            return (Criteria) this;
        }

        public Criteria andInstUserLessThan(String value) {
            addCriterion("inst_user <", value, "instUser");
            return (Criteria) this;
        }

        public Criteria andInstUserLessThanOrEqualTo(String value) {
            addCriterion("inst_user <=", value, "instUser");
            return (Criteria) this;
        }

        public Criteria andInstUserLike(String value) {
            addCriterion("inst_user like", value, "instUser");
            return (Criteria) this;
        }

        public Criteria andInstUserNotLike(String value) {
            addCriterion("inst_user not like", value, "instUser");
            return (Criteria) this;
        }

        public Criteria andInstUserIn(List<String> values) {
            addCriterion("inst_user in", values, "instUser");
            return (Criteria) this;
        }

        public Criteria andInstUserNotIn(List<String> values) {
            addCriterion("inst_user not in", values, "instUser");
            return (Criteria) this;
        }

        public Criteria andInstUserBetween(String value1, String value2) {
            addCriterion("inst_user between", value1, value2, "instUser");
            return (Criteria) this;
        }

        public Criteria andInstUserNotBetween(String value1, String value2) {
            addCriterion("inst_user not between", value1, value2, "instUser");
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

        public Criteria andUpdateUserIsNull() {
            addCriterion("update_user is null");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIsNotNull() {
            addCriterion("update_user is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateUserEqualTo(String value) {
            addCriterion("update_user =", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNotEqualTo(String value) {
            addCriterion("update_user <>", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserGreaterThan(String value) {
            addCriterion("update_user >", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserGreaterThanOrEqualTo(String value) {
            addCriterion("update_user >=", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserLessThan(String value) {
            addCriterion("update_user <", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserLessThanOrEqualTo(String value) {
            addCriterion("update_user <=", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserLike(String value) {
            addCriterion("update_user like", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNotLike(String value) {
            addCriterion("update_user not like", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIn(List<String> values) {
            addCriterion("update_user in", values, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNotIn(List<String> values) {
            addCriterion("update_user not in", values, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserBetween(String value1, String value2) {
            addCriterion("update_user between", value1, value2, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNotBetween(String value1, String value2) {
            addCriterion("update_user not between", value1, value2, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateDatetimeIsNull() {
            addCriterion("update_datetime is null");
            return (Criteria) this;
        }

        public Criteria andUpdateDatetimeIsNotNull() {
            addCriterion("update_datetime is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateDatetimeEqualTo(Date value) {
            addCriterion("update_datetime =", value, "updateDatetime");
            return (Criteria) this;
        }

        public Criteria andUpdateDatetimeNotEqualTo(Date value) {
            addCriterion("update_datetime <>", value, "updateDatetime");
            return (Criteria) this;
        }

        public Criteria andUpdateDatetimeGreaterThan(Date value) {
            addCriterion("update_datetime >", value, "updateDatetime");
            return (Criteria) this;
        }

        public Criteria andUpdateDatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_datetime >=", value, "updateDatetime");
            return (Criteria) this;
        }

        public Criteria andUpdateDatetimeLessThan(Date value) {
            addCriterion("update_datetime <", value, "updateDatetime");
            return (Criteria) this;
        }

        public Criteria andUpdateDatetimeLessThanOrEqualTo(Date value) {
            addCriterion("update_datetime <=", value, "updateDatetime");
            return (Criteria) this;
        }

        public Criteria andUpdateDatetimeIn(List<Date> values) {
            addCriterion("update_datetime in", values, "updateDatetime");
            return (Criteria) this;
        }

        public Criteria andUpdateDatetimeNotIn(List<Date> values) {
            addCriterion("update_datetime not in", values, "updateDatetime");
            return (Criteria) this;
        }

        public Criteria andUpdateDatetimeBetween(Date value1, Date value2) {
            addCriterion("update_datetime between", value1, value2, "updateDatetime");
            return (Criteria) this;
        }

        public Criteria andUpdateDatetimeNotBetween(Date value1, Date value2) {
            addCriterion("update_datetime not between", value1, value2, "updateDatetime");
            return (Criteria) this;
        }

        public Criteria andProtocolNameIsNull() {
            addCriterion("protocol_name is null");
            return (Criteria) this;
        }

        public Criteria andProtocolNameIsNotNull() {
            addCriterion("protocol_name is not null");
            return (Criteria) this;
        }

        public Criteria andProtocolNameEqualTo(String value) {
            addCriterion("protocol_name =", value, "protocolName");
            return (Criteria) this;
        }

        public Criteria andProtocolNameNotEqualTo(String value) {
            addCriterion("protocol_name <>", value, "protocolName");
            return (Criteria) this;
        }

        public Criteria andProtocolNameGreaterThan(String value) {
            addCriterion("protocol_name >", value, "protocolName");
            return (Criteria) this;
        }

        public Criteria andProtocolNameGreaterThanOrEqualTo(String value) {
            addCriterion("protocol_name >=", value, "protocolName");
            return (Criteria) this;
        }

        public Criteria andProtocolNameLessThan(String value) {
            addCriterion("protocol_name <", value, "protocolName");
            return (Criteria) this;
        }

        public Criteria andProtocolNameLessThanOrEqualTo(String value) {
            addCriterion("protocol_name <=", value, "protocolName");
            return (Criteria) this;
        }

        public Criteria andProtocolNameLike(String value) {
            addCriterion("protocol_name like", value, "protocolName");
            return (Criteria) this;
        }

        public Criteria andProtocolNameNotLike(String value) {
            addCriterion("protocol_name not like", value, "protocolName");
            return (Criteria) this;
        }

        public Criteria andProtocolNameIn(List<String> values) {
            addCriterion("protocol_name in", values, "protocolName");
            return (Criteria) this;
        }

        public Criteria andProtocolNameNotIn(List<String> values) {
            addCriterion("protocol_name not in", values, "protocolName");
            return (Criteria) this;
        }

        public Criteria andProtocolNameBetween(String value1, String value2) {
            addCriterion("protocol_name between", value1, value2, "protocolName");
            return (Criteria) this;
        }

        public Criteria andProtocolNameNotBetween(String value1, String value2) {
            addCriterion("protocol_name not between", value1, value2, "protocolName");
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