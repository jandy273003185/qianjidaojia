package com.qifenqian.bms.accounting.adjust.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class AccountingSingleAdjustDetailExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AccountingSingleAdjustDetailExample() {
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

        public Criteria andOpIdIsNull() {
            addCriterion("op_id is null");
            return (Criteria) this;
        }

        public Criteria andOpIdIsNotNull() {
            addCriterion("op_id is not null");
            return (Criteria) this;
        }

        public Criteria andOpIdEqualTo(String value) {
            addCriterion("op_id =", value, "opId");
            return (Criteria) this;
        }

        public Criteria andOpIdNotEqualTo(String value) {
            addCriterion("op_id <>", value, "opId");
            return (Criteria) this;
        }

        public Criteria andOpIdGreaterThan(String value) {
            addCriterion("op_id >", value, "opId");
            return (Criteria) this;
        }

        public Criteria andOpIdGreaterThanOrEqualTo(String value) {
            addCriterion("op_id >=", value, "opId");
            return (Criteria) this;
        }

        public Criteria andOpIdLessThan(String value) {
            addCriterion("op_id <", value, "opId");
            return (Criteria) this;
        }

        public Criteria andOpIdLessThanOrEqualTo(String value) {
            addCriterion("op_id <=", value, "opId");
            return (Criteria) this;
        }

        public Criteria andOpIdLike(String value) {
            addCriterion("op_id like", value, "opId");
            return (Criteria) this;
        }

        public Criteria andOpIdNotLike(String value) {
            addCriterion("op_id not like", value, "opId");
            return (Criteria) this;
        }

        public Criteria andOpIdIn(List<String> values) {
            addCriterion("op_id in", values, "opId");
            return (Criteria) this;
        }

        public Criteria andOpIdNotIn(List<String> values) {
            addCriterion("op_id not in", values, "opId");
            return (Criteria) this;
        }

        public Criteria andOpIdBetween(String value1, String value2) {
            addCriterion("op_id between", value1, value2, "opId");
            return (Criteria) this;
        }

        public Criteria andOpIdNotBetween(String value1, String value2) {
            addCriterion("op_id not between", value1, value2, "opId");
            return (Criteria) this;
        }

        public Criteria andEntryIdIsNull() {
            addCriterion("entry_id is null");
            return (Criteria) this;
        }

        public Criteria andEntryIdIsNotNull() {
            addCriterion("entry_id is not null");
            return (Criteria) this;
        }

        public Criteria andEntryIdEqualTo(String value) {
            addCriterion("entry_id =", value, "entryId");
            return (Criteria) this;
        }

        public Criteria andEntryIdNotEqualTo(String value) {
            addCriterion("entry_id <>", value, "entryId");
            return (Criteria) this;
        }

        public Criteria andEntryIdGreaterThan(String value) {
            addCriterion("entry_id >", value, "entryId");
            return (Criteria) this;
        }

        public Criteria andEntryIdGreaterThanOrEqualTo(String value) {
            addCriterion("entry_id >=", value, "entryId");
            return (Criteria) this;
        }

        public Criteria andEntryIdLessThan(String value) {
            addCriterion("entry_id <", value, "entryId");
            return (Criteria) this;
        }

        public Criteria andEntryIdLessThanOrEqualTo(String value) {
            addCriterion("entry_id <=", value, "entryId");
            return (Criteria) this;
        }

        public Criteria andEntryIdLike(String value) {
            addCriterion("entry_id like", value, "entryId");
            return (Criteria) this;
        }

        public Criteria andEntryIdNotLike(String value) {
            addCriterion("entry_id not like", value, "entryId");
            return (Criteria) this;
        }

        public Criteria andEntryIdIn(List<String> values) {
            addCriterion("entry_id in", values, "entryId");
            return (Criteria) this;
        }

        public Criteria andEntryIdNotIn(List<String> values) {
            addCriterion("entry_id not in", values, "entryId");
            return (Criteria) this;
        }

        public Criteria andEntryIdBetween(String value1, String value2) {
            addCriterion("entry_id between", value1, value2, "entryId");
            return (Criteria) this;
        }

        public Criteria andEntryIdNotBetween(String value1, String value2) {
            addCriterion("entry_id not between", value1, value2, "entryId");
            return (Criteria) this;
        }

        public Criteria andAcctNoIsNull() {
            addCriterion("acct_no is null");
            return (Criteria) this;
        }

        public Criteria andAcctNoIsNotNull() {
            addCriterion("acct_no is not null");
            return (Criteria) this;
        }

        public Criteria andAcctNoEqualTo(String value) {
            addCriterion("acct_no =", value, "acctNo");
            return (Criteria) this;
        }

        public Criteria andAcctNoNotEqualTo(String value) {
            addCriterion("acct_no <>", value, "acctNo");
            return (Criteria) this;
        }

        public Criteria andAcctNoGreaterThan(String value) {
            addCriterion("acct_no >", value, "acctNo");
            return (Criteria) this;
        }

        public Criteria andAcctNoGreaterThanOrEqualTo(String value) {
            addCriterion("acct_no >=", value, "acctNo");
            return (Criteria) this;
        }

        public Criteria andAcctNoLessThan(String value) {
            addCriterion("acct_no <", value, "acctNo");
            return (Criteria) this;
        }

        public Criteria andAcctNoLessThanOrEqualTo(String value) {
            addCriterion("acct_no <=", value, "acctNo");
            return (Criteria) this;
        }

        public Criteria andAcctNoLike(String value) {
            addCriterion("acct_no like", value, "acctNo");
            return (Criteria) this;
        }

        public Criteria andAcctNoNotLike(String value) {
            addCriterion("acct_no not like", value, "acctNo");
            return (Criteria) this;
        }

        public Criteria andAcctNoIn(List<String> values) {
            addCriterion("acct_no in", values, "acctNo");
            return (Criteria) this;
        }

        public Criteria andAcctNoNotIn(List<String> values) {
            addCriterion("acct_no not in", values, "acctNo");
            return (Criteria) this;
        }

        public Criteria andAcctNoBetween(String value1, String value2) {
            addCriterion("acct_no between", value1, value2, "acctNo");
            return (Criteria) this;
        }

        public Criteria andAcctNoNotBetween(String value1, String value2) {
            addCriterion("acct_no not between", value1, value2, "acctNo");
            return (Criteria) this;
        }

        public Criteria andAcctNameIsNull() {
            addCriterion("acct_name is null");
            return (Criteria) this;
        }

        public Criteria andAcctNameIsNotNull() {
            addCriterion("acct_name is not null");
            return (Criteria) this;
        }

        public Criteria andAcctNameEqualTo(String value) {
            addCriterion("acct_name =", value, "acctName");
            return (Criteria) this;
        }

        public Criteria andAcctNameNotEqualTo(String value) {
            addCriterion("acct_name <>", value, "acctName");
            return (Criteria) this;
        }

        public Criteria andAcctNameGreaterThan(String value) {
            addCriterion("acct_name >", value, "acctName");
            return (Criteria) this;
        }

        public Criteria andAcctNameGreaterThanOrEqualTo(String value) {
            addCriterion("acct_name >=", value, "acctName");
            return (Criteria) this;
        }

        public Criteria andAcctNameLessThan(String value) {
            addCriterion("acct_name <", value, "acctName");
            return (Criteria) this;
        }

        public Criteria andAcctNameLessThanOrEqualTo(String value) {
            addCriterion("acct_name <=", value, "acctName");
            return (Criteria) this;
        }

        public Criteria andAcctNameLike(String value) {
            addCriterion("acct_name like", value, "acctName");
            return (Criteria) this;
        }

        public Criteria andAcctNameNotLike(String value) {
            addCriterion("acct_name not like", value, "acctName");
            return (Criteria) this;
        }

        public Criteria andAcctNameIn(List<String> values) {
            addCriterion("acct_name in", values, "acctName");
            return (Criteria) this;
        }

        public Criteria andAcctNameNotIn(List<String> values) {
            addCriterion("acct_name not in", values, "acctName");
            return (Criteria) this;
        }

        public Criteria andAcctNameBetween(String value1, String value2) {
            addCriterion("acct_name between", value1, value2, "acctName");
            return (Criteria) this;
        }

        public Criteria andAcctNameNotBetween(String value1, String value2) {
            addCriterion("acct_name not between", value1, value2, "acctName");
            return (Criteria) this;
        }

        public Criteria andAcctTypeIsNull() {
            addCriterion("acct_type is null");
            return (Criteria) this;
        }

        public Criteria andAcctTypeIsNotNull() {
            addCriterion("acct_type is not null");
            return (Criteria) this;
        }

        public Criteria andAcctTypeEqualTo(String value) {
            addCriterion("acct_type =", value, "acctType");
            return (Criteria) this;
        }

        public Criteria andAcctTypeNotEqualTo(String value) {
            addCriterion("acct_type <>", value, "acctType");
            return (Criteria) this;
        }

        public Criteria andAcctTypeGreaterThan(String value) {
            addCriterion("acct_type >", value, "acctType");
            return (Criteria) this;
        }

        public Criteria andAcctTypeGreaterThanOrEqualTo(String value) {
            addCriterion("acct_type >=", value, "acctType");
            return (Criteria) this;
        }

        public Criteria andAcctTypeLessThan(String value) {
            addCriterion("acct_type <", value, "acctType");
            return (Criteria) this;
        }

        public Criteria andAcctTypeLessThanOrEqualTo(String value) {
            addCriterion("acct_type <=", value, "acctType");
            return (Criteria) this;
        }

        public Criteria andAcctTypeLike(String value) {
            addCriterion("acct_type like", value, "acctType");
            return (Criteria) this;
        }

        public Criteria andAcctTypeNotLike(String value) {
            addCriterion("acct_type not like", value, "acctType");
            return (Criteria) this;
        }

        public Criteria andAcctTypeIn(List<String> values) {
            addCriterion("acct_type in", values, "acctType");
            return (Criteria) this;
        }

        public Criteria andAcctTypeNotIn(List<String> values) {
            addCriterion("acct_type not in", values, "acctType");
            return (Criteria) this;
        }

        public Criteria andAcctTypeBetween(String value1, String value2) {
            addCriterion("acct_type between", value1, value2, "acctType");
            return (Criteria) this;
        }

        public Criteria andAcctTypeNotBetween(String value1, String value2) {
            addCriterion("acct_type not between", value1, value2, "acctType");
            return (Criteria) this;
        }

        public Criteria andCurcdeIsNull() {
            addCriterion("curcde is null");
            return (Criteria) this;
        }

        public Criteria andCurcdeIsNotNull() {
            addCriterion("curcde is not null");
            return (Criteria) this;
        }

        public Criteria andCurcdeEqualTo(String value) {
            addCriterion("curcde =", value, "curcde");
            return (Criteria) this;
        }

        public Criteria andCurcdeNotEqualTo(String value) {
            addCriterion("curcde <>", value, "curcde");
            return (Criteria) this;
        }

        public Criteria andCurcdeGreaterThan(String value) {
            addCriterion("curcde >", value, "curcde");
            return (Criteria) this;
        }

        public Criteria andCurcdeGreaterThanOrEqualTo(String value) {
            addCriterion("curcde >=", value, "curcde");
            return (Criteria) this;
        }

        public Criteria andCurcdeLessThan(String value) {
            addCriterion("curcde <", value, "curcde");
            return (Criteria) this;
        }

        public Criteria andCurcdeLessThanOrEqualTo(String value) {
            addCriterion("curcde <=", value, "curcde");
            return (Criteria) this;
        }

        public Criteria andCurcdeLike(String value) {
            addCriterion("curcde like", value, "curcde");
            return (Criteria) this;
        }

        public Criteria andCurcdeNotLike(String value) {
            addCriterion("curcde not like", value, "curcde");
            return (Criteria) this;
        }

        public Criteria andCurcdeIn(List<String> values) {
            addCriterion("curcde in", values, "curcde");
            return (Criteria) this;
        }

        public Criteria andCurcdeNotIn(List<String> values) {
            addCriterion("curcde not in", values, "curcde");
            return (Criteria) this;
        }

        public Criteria andCurcdeBetween(String value1, String value2) {
            addCriterion("curcde between", value1, value2, "curcde");
            return (Criteria) this;
        }

        public Criteria andCurcdeNotBetween(String value1, String value2) {
            addCriterion("curcde not between", value1, value2, "curcde");
            return (Criteria) this;
        }

        public Criteria andAmtIsNull() {
            addCriterion("amt is null");
            return (Criteria) this;
        }

        public Criteria andAmtIsNotNull() {
            addCriterion("amt is not null");
            return (Criteria) this;
        }

        public Criteria andAmtEqualTo(BigDecimal value) {
            addCriterion("amt =", value, "amt");
            return (Criteria) this;
        }

        public Criteria andAmtNotEqualTo(BigDecimal value) {
            addCriterion("amt <>", value, "amt");
            return (Criteria) this;
        }

        public Criteria andAmtGreaterThan(BigDecimal value) {
            addCriterion("amt >", value, "amt");
            return (Criteria) this;
        }

        public Criteria andAmtGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("amt >=", value, "amt");
            return (Criteria) this;
        }

        public Criteria andAmtLessThan(BigDecimal value) {
            addCriterion("amt <", value, "amt");
            return (Criteria) this;
        }

        public Criteria andAmtLessThanOrEqualTo(BigDecimal value) {
            addCriterion("amt <=", value, "amt");
            return (Criteria) this;
        }

        public Criteria andAmtIn(List<BigDecimal> values) {
            addCriterion("amt in", values, "amt");
            return (Criteria) this;
        }

        public Criteria andAmtNotIn(List<BigDecimal> values) {
            addCriterion("amt not in", values, "amt");
            return (Criteria) this;
        }

        public Criteria andAmtBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("amt between", value1, value2, "amt");
            return (Criteria) this;
        }

        public Criteria andAmtNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("amt not between", value1, value2, "amt");
            return (Criteria) this;
        }

        public Criteria andUsableAmtIsNull() {
            addCriterion("usable_amt is null");
            return (Criteria) this;
        }

        public Criteria andUsableAmtIsNotNull() {
            addCriterion("usable_amt is not null");
            return (Criteria) this;
        }

        public Criteria andUsableAmtEqualTo(BigDecimal value) {
            addCriterion("usable_amt =", value, "usableAmt");
            return (Criteria) this;
        }

        public Criteria andUsableAmtNotEqualTo(BigDecimal value) {
            addCriterion("usable_amt <>", value, "usableAmt");
            return (Criteria) this;
        }

        public Criteria andUsableAmtGreaterThan(BigDecimal value) {
            addCriterion("usable_amt >", value, "usableAmt");
            return (Criteria) this;
        }

        public Criteria andUsableAmtGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("usable_amt >=", value, "usableAmt");
            return (Criteria) this;
        }

        public Criteria andUsableAmtLessThan(BigDecimal value) {
            addCriterion("usable_amt <", value, "usableAmt");
            return (Criteria) this;
        }

        public Criteria andUsableAmtLessThanOrEqualTo(BigDecimal value) {
            addCriterion("usable_amt <=", value, "usableAmt");
            return (Criteria) this;
        }

        public Criteria andUsableAmtIn(List<BigDecimal> values) {
            addCriterion("usable_amt in", values, "usableAmt");
            return (Criteria) this;
        }

        public Criteria andUsableAmtNotIn(List<BigDecimal> values) {
            addCriterion("usable_amt not in", values, "usableAmt");
            return (Criteria) this;
        }

        public Criteria andUsableAmtBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("usable_amt between", value1, value2, "usableAmt");
            return (Criteria) this;
        }

        public Criteria andUsableAmtNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("usable_amt not between", value1, value2, "usableAmt");
            return (Criteria) this;
        }

        public Criteria andOnwayAmtIsNull() {
            addCriterion("onway_amt is null");
            return (Criteria) this;
        }

        public Criteria andOnwayAmtIsNotNull() {
            addCriterion("onway_amt is not null");
            return (Criteria) this;
        }

        public Criteria andOnwayAmtEqualTo(BigDecimal value) {
            addCriterion("onway_amt =", value, "onwayAmt");
            return (Criteria) this;
        }

        public Criteria andOnwayAmtNotEqualTo(BigDecimal value) {
            addCriterion("onway_amt <>", value, "onwayAmt");
            return (Criteria) this;
        }

        public Criteria andOnwayAmtGreaterThan(BigDecimal value) {
            addCriterion("onway_amt >", value, "onwayAmt");
            return (Criteria) this;
        }

        public Criteria andOnwayAmtGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("onway_amt >=", value, "onwayAmt");
            return (Criteria) this;
        }

        public Criteria andOnwayAmtLessThan(BigDecimal value) {
            addCriterion("onway_amt <", value, "onwayAmt");
            return (Criteria) this;
        }

        public Criteria andOnwayAmtLessThanOrEqualTo(BigDecimal value) {
            addCriterion("onway_amt <=", value, "onwayAmt");
            return (Criteria) this;
        }

        public Criteria andOnwayAmtIn(List<BigDecimal> values) {
            addCriterion("onway_amt in", values, "onwayAmt");
            return (Criteria) this;
        }

        public Criteria andOnwayAmtNotIn(List<BigDecimal> values) {
            addCriterion("onway_amt not in", values, "onwayAmt");
            return (Criteria) this;
        }

        public Criteria andOnwayAmtBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("onway_amt between", value1, value2, "onwayAmt");
            return (Criteria) this;
        }

        public Criteria andOnwayAmtNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("onway_amt not between", value1, value2, "onwayAmt");
            return (Criteria) this;
        }

        public Criteria andIsAdjustJGKJIsNull() {
            addCriterion("is_adjust_jgkj is null");
            return (Criteria) this;
        }

        public Criteria andIsAdjustJGKJIsNotNull() {
            addCriterion("is_adjust_jgkj is not null");
            return (Criteria) this;
        }

        public Criteria andIsAdjustJGKJEqualTo(String value) {
            addCriterion("is_adjust_jgkj =", value, "IsAdjustJGKJ");
            return (Criteria) this;
        }

        public Criteria andIsAdjustJGKJNotEqualTo(String value) {
            addCriterion("is_adjust_jgkj <>", value, "IsAdjustJGKJ");
            return (Criteria) this;
        }

        public Criteria andIsAdjustJGKJGreaterThan(String value) {
            addCriterion("is_adjust_jgkj >", value, "IsAdjustJGKJ");
            return (Criteria) this;
        }

        public Criteria andIsAdjustJGKJGreaterThanOrEqualTo(String value) {
            addCriterion("is_adjust_jgkj >=", value, "IsAdjustJGKJ");
            return (Criteria) this;
        }

        public Criteria andIsAdjustJGKJLessThan(String value) {
            addCriterion("is_adjust_jgkj <", value, "IsAdjustJGKJ");
            return (Criteria) this;
        }

        public Criteria andIsAdjustJGKJLessThanOrEqualTo(String value) {
            addCriterion("is_adjust_jgkj <=", value, "IsAdjustJGKJ");
            return (Criteria) this;
        }

        public Criteria andIsAdjustJGKJLike(String value) {
            addCriterion("is_adjust_jgkj like", value, "IsAdjustJGKJ");
            return (Criteria) this;
        }

        public Criteria andIsAdjustJGKJNotLike(String value) {
            addCriterion("is_adjust_jgkj not like", value, "IsAdjustJGKJ");
            return (Criteria) this;
        }

        public Criteria andIsAdjustJGKJIn(List<String> values) {
            addCriterion("is_adjust_jgkj in", values, "IsAdjustJGKJ");
            return (Criteria) this;
        }

        public Criteria andIsAdjustJGKJNotIn(List<String> values) {
            addCriterion("is_adjust_jgkj not in", values, "IsAdjustJGKJ");
            return (Criteria) this;
        }

        public Criteria andIsAdjustJGKJBetween(String value1, String value2) {
            addCriterion("is_adjust_jgkj between", value1, value2, "IsAdjustJGKJ");
            return (Criteria) this;
        }

        public Criteria andIsAdjustJGKJNotBetween(String value1, String value2) {
            addCriterion("is_adjust_jgkj not between", value1, value2, "IsAdjustJGKJ");
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