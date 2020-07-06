package com.qifenqian.bms.accounting.adjust.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class AccountingAdjustDetailExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AccountingAdjustDetailExample() {
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

        public Criteria andDebitAcctTypeIsNull() {
            addCriterion("debit_acct_type is null");
            return (Criteria) this;
        }

        public Criteria andDebitAcctTypeIsNotNull() {
            addCriterion("debit_acct_type is not null");
            return (Criteria) this;
        }

        public Criteria andDebitAcctTypeEqualTo(String value) {
            addCriterion("debit_acct_type =", value, "debitAcctType");
            return (Criteria) this;
        }

        public Criteria andDebitAcctTypeNotEqualTo(String value) {
            addCriterion("debit_acct_type <>", value, "debitAcctType");
            return (Criteria) this;
        }

        public Criteria andDebitAcctTypeGreaterThan(String value) {
            addCriterion("debit_acct_type >", value, "debitAcctType");
            return (Criteria) this;
        }

        public Criteria andDebitAcctTypeGreaterThanOrEqualTo(String value) {
            addCriterion("debit_acct_type >=", value, "debitAcctType");
            return (Criteria) this;
        }

        public Criteria andDebitAcctTypeLessThan(String value) {
            addCriterion("debit_acct_type <", value, "debitAcctType");
            return (Criteria) this;
        }

        public Criteria andDebitAcctTypeLessThanOrEqualTo(String value) {
            addCriterion("debit_acct_type <=", value, "debitAcctType");
            return (Criteria) this;
        }

        public Criteria andDebitAcctTypeLike(String value) {
            addCriterion("debit_acct_type like", value, "debitAcctType");
            return (Criteria) this;
        }

        public Criteria andDebitAcctTypeNotLike(String value) {
            addCriterion("debit_acct_type not like", value, "debitAcctType");
            return (Criteria) this;
        }

        public Criteria andDebitAcctTypeIn(List<String> values) {
            addCriterion("debit_acct_type in", values, "debitAcctType");
            return (Criteria) this;
        }

        public Criteria andDebitAcctTypeNotIn(List<String> values) {
            addCriterion("debit_acct_type not in", values, "debitAcctType");
            return (Criteria) this;
        }

        public Criteria andDebitAcctTypeBetween(String value1, String value2) {
            addCriterion("debit_acct_type between", value1, value2, "debitAcctType");
            return (Criteria) this;
        }

        public Criteria andDebitAcctTypeNotBetween(String value1, String value2) {
            addCriterion("debit_acct_type not between", value1, value2, "debitAcctType");
            return (Criteria) this;
        }

        public Criteria andDebitAcctNoIsNull() {
            addCriterion("debit_acct_no is null");
            return (Criteria) this;
        }

        public Criteria andDebitAcctNoIsNotNull() {
            addCriterion("debit_acct_no is not null");
            return (Criteria) this;
        }

        public Criteria andDebitAcctNoEqualTo(String value) {
            addCriterion("debit_acct_no =", value, "debitAcctNo");
            return (Criteria) this;
        }

        public Criteria andDebitAcctNoNotEqualTo(String value) {
            addCriterion("debit_acct_no <>", value, "debitAcctNo");
            return (Criteria) this;
        }

        public Criteria andDebitAcctNoGreaterThan(String value) {
            addCriterion("debit_acct_no >", value, "debitAcctNo");
            return (Criteria) this;
        }

        public Criteria andDebitAcctNoGreaterThanOrEqualTo(String value) {
            addCriterion("debit_acct_no >=", value, "debitAcctNo");
            return (Criteria) this;
        }

        public Criteria andDebitAcctNoLessThan(String value) {
            addCriterion("debit_acct_no <", value, "debitAcctNo");
            return (Criteria) this;
        }

        public Criteria andDebitAcctNoLessThanOrEqualTo(String value) {
            addCriterion("debit_acct_no <=", value, "debitAcctNo");
            return (Criteria) this;
        }

        public Criteria andDebitAcctNoLike(String value) {
            addCriterion("debit_acct_no like", value, "debitAcctNo");
            return (Criteria) this;
        }

        public Criteria andDebitAcctNoNotLike(String value) {
            addCriterion("debit_acct_no not like", value, "debitAcctNo");
            return (Criteria) this;
        }

        public Criteria andDebitAcctNoIn(List<String> values) {
            addCriterion("debit_acct_no in", values, "debitAcctNo");
            return (Criteria) this;
        }

        public Criteria andDebitAcctNoNotIn(List<String> values) {
            addCriterion("debit_acct_no not in", values, "debitAcctNo");
            return (Criteria) this;
        }

        public Criteria andDebitAcctNoBetween(String value1, String value2) {
            addCriterion("debit_acct_no between", value1, value2, "debitAcctNo");
            return (Criteria) this;
        }

        public Criteria andDebitAcctNoNotBetween(String value1, String value2) {
            addCriterion("debit_acct_no not between", value1, value2, "debitAcctNo");
            return (Criteria) this;
        }

        public Criteria andDebitAcctNameIsNull() {
            addCriterion("debit_acct_name is null");
            return (Criteria) this;
        }

        public Criteria andDebitAcctNameIsNotNull() {
            addCriterion("debit_acct_name is not null");
            return (Criteria) this;
        }

        public Criteria andDebitAcctNameEqualTo(String value) {
            addCriterion("debit_acct_name =", value, "debitAcctName");
            return (Criteria) this;
        }

        public Criteria andDebitAcctNameNotEqualTo(String value) {
            addCriterion("debit_acct_name <>", value, "debitAcctName");
            return (Criteria) this;
        }

        public Criteria andDebitAcctNameGreaterThan(String value) {
            addCriterion("debit_acct_name >", value, "debitAcctName");
            return (Criteria) this;
        }

        public Criteria andDebitAcctNameGreaterThanOrEqualTo(String value) {
            addCriterion("debit_acct_name >=", value, "debitAcctName");
            return (Criteria) this;
        }

        public Criteria andDebitAcctNameLessThan(String value) {
            addCriterion("debit_acct_name <", value, "debitAcctName");
            return (Criteria) this;
        }

        public Criteria andDebitAcctNameLessThanOrEqualTo(String value) {
            addCriterion("debit_acct_name <=", value, "debitAcctName");
            return (Criteria) this;
        }

        public Criteria andDebitAcctNameLike(String value) {
            addCriterion("debit_acct_name like", value, "debitAcctName");
            return (Criteria) this;
        }

        public Criteria andDebitAcctNameNotLike(String value) {
            addCriterion("debit_acct_name not like", value, "debitAcctName");
            return (Criteria) this;
        }

        public Criteria andDebitAcctNameIn(List<String> values) {
            addCriterion("debit_acct_name in", values, "debitAcctName");
            return (Criteria) this;
        }

        public Criteria andDebitAcctNameNotIn(List<String> values) {
            addCriterion("debit_acct_name not in", values, "debitAcctName");
            return (Criteria) this;
        }

        public Criteria andDebitAcctNameBetween(String value1, String value2) {
            addCriterion("debit_acct_name between", value1, value2, "debitAcctName");
            return (Criteria) this;
        }

        public Criteria andDebitAcctNameNotBetween(String value1, String value2) {
            addCriterion("debit_acct_name not between", value1, value2, "debitAcctName");
            return (Criteria) this;
        }

        public Criteria andDebitCustIdIsNull() {
            addCriterion("debit_cust_id is null");
            return (Criteria) this;
        }

        public Criteria andDebitCustIdIsNotNull() {
            addCriterion("debit_cust_id is not null");
            return (Criteria) this;
        }

        public Criteria andDebitCustIdEqualTo(String value) {
            addCriterion("debit_cust_id =", value, "debitCustId");
            return (Criteria) this;
        }

        public Criteria andDebitCustIdNotEqualTo(String value) {
            addCriterion("debit_cust_id <>", value, "debitCustId");
            return (Criteria) this;
        }

        public Criteria andDebitCustIdGreaterThan(String value) {
            addCriterion("debit_cust_id >", value, "debitCustId");
            return (Criteria) this;
        }

        public Criteria andDebitCustIdGreaterThanOrEqualTo(String value) {
            addCriterion("debit_cust_id >=", value, "debitCustId");
            return (Criteria) this;
        }

        public Criteria andDebitCustIdLessThan(String value) {
            addCriterion("debit_cust_id <", value, "debitCustId");
            return (Criteria) this;
        }

        public Criteria andDebitCustIdLessThanOrEqualTo(String value) {
            addCriterion("debit_cust_id <=", value, "debitCustId");
            return (Criteria) this;
        }

        public Criteria andDebitCustIdLike(String value) {
            addCriterion("debit_cust_id like", value, "debitCustId");
            return (Criteria) this;
        }

        public Criteria andDebitCustIdNotLike(String value) {
            addCriterion("debit_cust_id not like", value, "debitCustId");
            return (Criteria) this;
        }

        public Criteria andDebitCustIdIn(List<String> values) {
            addCriterion("debit_cust_id in", values, "debitCustId");
            return (Criteria) this;
        }

        public Criteria andDebitCustIdNotIn(List<String> values) {
            addCriterion("debit_cust_id not in", values, "debitCustId");
            return (Criteria) this;
        }

        public Criteria andDebitCustIdBetween(String value1, String value2) {
            addCriterion("debit_cust_id between", value1, value2, "debitCustId");
            return (Criteria) this;
        }

        public Criteria andDebitCustIdNotBetween(String value1, String value2) {
            addCriterion("debit_cust_id not between", value1, value2, "debitCustId");
            return (Criteria) this;
        }

        public Criteria andDebitSubjectIdIsNull() {
            addCriterion("debit_subject_id is null");
            return (Criteria) this;
        }

        public Criteria andDebitSubjectIdIsNotNull() {
            addCriterion("debit_subject_id is not null");
            return (Criteria) this;
        }

        public Criteria andDebitSubjectIdEqualTo(String value) {
            addCriterion("debit_subject_id =", value, "debitSubjectId");
            return (Criteria) this;
        }

        public Criteria andDebitSubjectIdNotEqualTo(String value) {
            addCriterion("debit_subject_id <>", value, "debitSubjectId");
            return (Criteria) this;
        }

        public Criteria andDebitSubjectIdGreaterThan(String value) {
            addCriterion("debit_subject_id >", value, "debitSubjectId");
            return (Criteria) this;
        }

        public Criteria andDebitSubjectIdGreaterThanOrEqualTo(String value) {
            addCriterion("debit_subject_id >=", value, "debitSubjectId");
            return (Criteria) this;
        }

        public Criteria andDebitSubjectIdLessThan(String value) {
            addCriterion("debit_subject_id <", value, "debitSubjectId");
            return (Criteria) this;
        }

        public Criteria andDebitSubjectIdLessThanOrEqualTo(String value) {
            addCriterion("debit_subject_id <=", value, "debitSubjectId");
            return (Criteria) this;
        }

        public Criteria andDebitSubjectIdLike(String value) {
            addCriterion("debit_subject_id like", value, "debitSubjectId");
            return (Criteria) this;
        }

        public Criteria andDebitSubjectIdNotLike(String value) {
            addCriterion("debit_subject_id not like", value, "debitSubjectId");
            return (Criteria) this;
        }

        public Criteria andDebitSubjectIdIn(List<String> values) {
            addCriterion("debit_subject_id in", values, "debitSubjectId");
            return (Criteria) this;
        }

        public Criteria andDebitSubjectIdNotIn(List<String> values) {
            addCriterion("debit_subject_id not in", values, "debitSubjectId");
            return (Criteria) this;
        }

        public Criteria andDebitSubjectIdBetween(String value1, String value2) {
            addCriterion("debit_subject_id between", value1, value2, "debitSubjectId");
            return (Criteria) this;
        }

        public Criteria andDebitSubjectIdNotBetween(String value1, String value2) {
            addCriterion("debit_subject_id not between", value1, value2, "debitSubjectId");
            return (Criteria) this;
        }

        public Criteria andCreditAcctTypeIsNull() {
            addCriterion("credit_acct_type is null");
            return (Criteria) this;
        }

        public Criteria andCreditAcctTypeIsNotNull() {
            addCriterion("credit_acct_type is not null");
            return (Criteria) this;
        }

        public Criteria andCreditAcctTypeEqualTo(String value) {
            addCriterion("credit_acct_type =", value, "creditAcctType");
            return (Criteria) this;
        }

        public Criteria andCreditAcctTypeNotEqualTo(String value) {
            addCriterion("credit_acct_type <>", value, "creditAcctType");
            return (Criteria) this;
        }

        public Criteria andCreditAcctTypeGreaterThan(String value) {
            addCriterion("credit_acct_type >", value, "creditAcctType");
            return (Criteria) this;
        }

        public Criteria andCreditAcctTypeGreaterThanOrEqualTo(String value) {
            addCriterion("credit_acct_type >=", value, "creditAcctType");
            return (Criteria) this;
        }

        public Criteria andCreditAcctTypeLessThan(String value) {
            addCriterion("credit_acct_type <", value, "creditAcctType");
            return (Criteria) this;
        }

        public Criteria andCreditAcctTypeLessThanOrEqualTo(String value) {
            addCriterion("credit_acct_type <=", value, "creditAcctType");
            return (Criteria) this;
        }

        public Criteria andCreditAcctTypeLike(String value) {
            addCriterion("credit_acct_type like", value, "creditAcctType");
            return (Criteria) this;
        }

        public Criteria andCreditAcctTypeNotLike(String value) {
            addCriterion("credit_acct_type not like", value, "creditAcctType");
            return (Criteria) this;
        }

        public Criteria andCreditAcctTypeIn(List<String> values) {
            addCriterion("credit_acct_type in", values, "creditAcctType");
            return (Criteria) this;
        }

        public Criteria andCreditAcctTypeNotIn(List<String> values) {
            addCriterion("credit_acct_type not in", values, "creditAcctType");
            return (Criteria) this;
        }

        public Criteria andCreditAcctTypeBetween(String value1, String value2) {
            addCriterion("credit_acct_type between", value1, value2, "creditAcctType");
            return (Criteria) this;
        }

        public Criteria andCreditAcctTypeNotBetween(String value1, String value2) {
            addCriterion("credit_acct_type not between", value1, value2, "creditAcctType");
            return (Criteria) this;
        }

        public Criteria andCreditAcctNoIsNull() {
            addCriterion("credit_acct_no is null");
            return (Criteria) this;
        }

        public Criteria andCreditAcctNoIsNotNull() {
            addCriterion("credit_acct_no is not null");
            return (Criteria) this;
        }

        public Criteria andCreditAcctNoEqualTo(String value) {
            addCriterion("credit_acct_no =", value, "creditAcctNo");
            return (Criteria) this;
        }

        public Criteria andCreditAcctNoNotEqualTo(String value) {
            addCriterion("credit_acct_no <>", value, "creditAcctNo");
            return (Criteria) this;
        }

        public Criteria andCreditAcctNoGreaterThan(String value) {
            addCriterion("credit_acct_no >", value, "creditAcctNo");
            return (Criteria) this;
        }

        public Criteria andCreditAcctNoGreaterThanOrEqualTo(String value) {
            addCriterion("credit_acct_no >=", value, "creditAcctNo");
            return (Criteria) this;
        }

        public Criteria andCreditAcctNoLessThan(String value) {
            addCriterion("credit_acct_no <", value, "creditAcctNo");
            return (Criteria) this;
        }

        public Criteria andCreditAcctNoLessThanOrEqualTo(String value) {
            addCriterion("credit_acct_no <=", value, "creditAcctNo");
            return (Criteria) this;
        }

        public Criteria andCreditAcctNoLike(String value) {
            addCriterion("credit_acct_no like", value, "creditAcctNo");
            return (Criteria) this;
        }

        public Criteria andCreditAcctNoNotLike(String value) {
            addCriterion("credit_acct_no not like", value, "creditAcctNo");
            return (Criteria) this;
        }

        public Criteria andCreditAcctNoIn(List<String> values) {
            addCriterion("credit_acct_no in", values, "creditAcctNo");
            return (Criteria) this;
        }

        public Criteria andCreditAcctNoNotIn(List<String> values) {
            addCriterion("credit_acct_no not in", values, "creditAcctNo");
            return (Criteria) this;
        }

        public Criteria andCreditAcctNoBetween(String value1, String value2) {
            addCriterion("credit_acct_no between", value1, value2, "creditAcctNo");
            return (Criteria) this;
        }

        public Criteria andCreditAcctNoNotBetween(String value1, String value2) {
            addCriterion("credit_acct_no not between", value1, value2, "creditAcctNo");
            return (Criteria) this;
        }

        public Criteria andCreditAcctNameIsNull() {
            addCriterion("credit_acct_name is null");
            return (Criteria) this;
        }

        public Criteria andCreditAcctNameIsNotNull() {
            addCriterion("credit_acct_name is not null");
            return (Criteria) this;
        }

        public Criteria andCreditAcctNameEqualTo(String value) {
            addCriterion("credit_acct_name =", value, "creditAcctName");
            return (Criteria) this;
        }

        public Criteria andCreditAcctNameNotEqualTo(String value) {
            addCriterion("credit_acct_name <>", value, "creditAcctName");
            return (Criteria) this;
        }

        public Criteria andCreditAcctNameGreaterThan(String value) {
            addCriterion("credit_acct_name >", value, "creditAcctName");
            return (Criteria) this;
        }

        public Criteria andCreditAcctNameGreaterThanOrEqualTo(String value) {
            addCriterion("credit_acct_name >=", value, "creditAcctName");
            return (Criteria) this;
        }

        public Criteria andCreditAcctNameLessThan(String value) {
            addCriterion("credit_acct_name <", value, "creditAcctName");
            return (Criteria) this;
        }

        public Criteria andCreditAcctNameLessThanOrEqualTo(String value) {
            addCriterion("credit_acct_name <=", value, "creditAcctName");
            return (Criteria) this;
        }

        public Criteria andCreditAcctNameLike(String value) {
            addCriterion("credit_acct_name like", value, "creditAcctName");
            return (Criteria) this;
        }

        public Criteria andCreditAcctNameNotLike(String value) {
            addCriterion("credit_acct_name not like", value, "creditAcctName");
            return (Criteria) this;
        }

        public Criteria andCreditAcctNameIn(List<String> values) {
            addCriterion("credit_acct_name in", values, "creditAcctName");
            return (Criteria) this;
        }

        public Criteria andCreditAcctNameNotIn(List<String> values) {
            addCriterion("credit_acct_name not in", values, "creditAcctName");
            return (Criteria) this;
        }

        public Criteria andCreditAcctNameBetween(String value1, String value2) {
            addCriterion("credit_acct_name between", value1, value2, "creditAcctName");
            return (Criteria) this;
        }

        public Criteria andCreditAcctNameNotBetween(String value1, String value2) {
            addCriterion("credit_acct_name not between", value1, value2, "creditAcctName");
            return (Criteria) this;
        }

        public Criteria andCreditCustIdIsNull() {
            addCriterion("credit_cust_id is null");
            return (Criteria) this;
        }

        public Criteria andCreditCustIdIsNotNull() {
            addCriterion("credit_cust_id is not null");
            return (Criteria) this;
        }

        public Criteria andCreditCustIdEqualTo(String value) {
            addCriterion("credit_cust_id =", value, "creditCustId");
            return (Criteria) this;
        }

        public Criteria andCreditCustIdNotEqualTo(String value) {
            addCriterion("credit_cust_id <>", value, "creditCustId");
            return (Criteria) this;
        }

        public Criteria andCreditCustIdGreaterThan(String value) {
            addCriterion("credit_cust_id >", value, "creditCustId");
            return (Criteria) this;
        }

        public Criteria andCreditCustIdGreaterThanOrEqualTo(String value) {
            addCriterion("credit_cust_id >=", value, "creditCustId");
            return (Criteria) this;
        }

        public Criteria andCreditCustIdLessThan(String value) {
            addCriterion("credit_cust_id <", value, "creditCustId");
            return (Criteria) this;
        }

        public Criteria andCreditCustIdLessThanOrEqualTo(String value) {
            addCriterion("credit_cust_id <=", value, "creditCustId");
            return (Criteria) this;
        }

        public Criteria andCreditCustIdLike(String value) {
            addCriterion("credit_cust_id like", value, "creditCustId");
            return (Criteria) this;
        }

        public Criteria andCreditCustIdNotLike(String value) {
            addCriterion("credit_cust_id not like", value, "creditCustId");
            return (Criteria) this;
        }

        public Criteria andCreditCustIdIn(List<String> values) {
            addCriterion("credit_cust_id in", values, "creditCustId");
            return (Criteria) this;
        }

        public Criteria andCreditCustIdNotIn(List<String> values) {
            addCriterion("credit_cust_id not in", values, "creditCustId");
            return (Criteria) this;
        }

        public Criteria andCreditCustIdBetween(String value1, String value2) {
            addCriterion("credit_cust_id between", value1, value2, "creditCustId");
            return (Criteria) this;
        }

        public Criteria andCreditCustIdNotBetween(String value1, String value2) {
            addCriterion("credit_cust_id not between", value1, value2, "creditCustId");
            return (Criteria) this;
        }

        public Criteria andCreditSubjectIdIsNull() {
            addCriterion("credit_subject_id is null");
            return (Criteria) this;
        }

        public Criteria andCreditSubjectIdIsNotNull() {
            addCriterion("credit_subject_id is not null");
            return (Criteria) this;
        }

        public Criteria andCreditSubjectIdEqualTo(String value) {
            addCriterion("credit_subject_id =", value, "creditSubjectId");
            return (Criteria) this;
        }

        public Criteria andCreditSubjectIdNotEqualTo(String value) {
            addCriterion("credit_subject_id <>", value, "creditSubjectId");
            return (Criteria) this;
        }

        public Criteria andCreditSubjectIdGreaterThan(String value) {
            addCriterion("credit_subject_id >", value, "creditSubjectId");
            return (Criteria) this;
        }

        public Criteria andCreditSubjectIdGreaterThanOrEqualTo(String value) {
            addCriterion("credit_subject_id >=", value, "creditSubjectId");
            return (Criteria) this;
        }

        public Criteria andCreditSubjectIdLessThan(String value) {
            addCriterion("credit_subject_id <", value, "creditSubjectId");
            return (Criteria) this;
        }

        public Criteria andCreditSubjectIdLessThanOrEqualTo(String value) {
            addCriterion("credit_subject_id <=", value, "creditSubjectId");
            return (Criteria) this;
        }

        public Criteria andCreditSubjectIdLike(String value) {
            addCriterion("credit_subject_id like", value, "creditSubjectId");
            return (Criteria) this;
        }

        public Criteria andCreditSubjectIdNotLike(String value) {
            addCriterion("credit_subject_id not like", value, "creditSubjectId");
            return (Criteria) this;
        }

        public Criteria andCreditSubjectIdIn(List<String> values) {
            addCriterion("credit_subject_id in", values, "creditSubjectId");
            return (Criteria) this;
        }

        public Criteria andCreditSubjectIdNotIn(List<String> values) {
            addCriterion("credit_subject_id not in", values, "creditSubjectId");
            return (Criteria) this;
        }

        public Criteria andCreditSubjectIdBetween(String value1, String value2) {
            addCriterion("credit_subject_id between", value1, value2, "creditSubjectId");
            return (Criteria) this;
        }

        public Criteria andCreditSubjectIdNotBetween(String value1, String value2) {
            addCriterion("credit_subject_id not between", value1, value2, "creditSubjectId");
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