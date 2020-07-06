package com.qifenqian.bms.accounting.adjust.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AccountingAdjustMainExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AccountingAdjustMainExample() {
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

        public Criteria andProcessInstanceIdIsNull() {
            addCriterion("process_instance_id is null");
            return (Criteria) this;
        }

        public Criteria andProcessInstanceIdIsNotNull() {
            addCriterion("process_instance_id is not null");
            return (Criteria) this;
        }

        public Criteria andProcessInstanceIdEqualTo(String value) {
            addCriterion("process_instance_id =", value, "processInstanceId");
            return (Criteria) this;
        }

        public Criteria andProcessInstanceIdNotEqualTo(String value) {
            addCriterion("process_instance_id <>", value, "processInstanceId");
            return (Criteria) this;
        }

        public Criteria andProcessInstanceIdGreaterThan(String value) {
            addCriterion("process_instance_id >", value, "processInstanceId");
            return (Criteria) this;
        }

        public Criteria andProcessInstanceIdGreaterThanOrEqualTo(String value) {
            addCriterion("process_instance_id >=", value, "processInstanceId");
            return (Criteria) this;
        }

        public Criteria andProcessInstanceIdLessThan(String value) {
            addCriterion("process_instance_id <", value, "processInstanceId");
            return (Criteria) this;
        }

        public Criteria andProcessInstanceIdLessThanOrEqualTo(String value) {
            addCriterion("process_instance_id <=", value, "processInstanceId");
            return (Criteria) this;
        }

        public Criteria andProcessInstanceIdLike(String value) {
            addCriterion("process_instance_id like", value, "processInstanceId");
            return (Criteria) this;
        }

        public Criteria andProcessInstanceIdNotLike(String value) {
            addCriterion("process_instance_id not like", value, "processInstanceId");
            return (Criteria) this;
        }

        public Criteria andProcessInstanceIdIn(List<String> values) {
            addCriterion("process_instance_id in", values, "processInstanceId");
            return (Criteria) this;
        }

        public Criteria andProcessInstanceIdNotIn(List<String> values) {
            addCriterion("process_instance_id not in", values, "processInstanceId");
            return (Criteria) this;
        }

        public Criteria andProcessInstanceIdBetween(String value1, String value2) {
            addCriterion("process_instance_id between", value1, value2, "processInstanceId");
            return (Criteria) this;
        }

        public Criteria andProcessInstanceIdNotBetween(String value1, String value2) {
            addCriterion("process_instance_id not between", value1, value2, "processInstanceId");
            return (Criteria) this;
        }

        public Criteria andHandleDatetimeIsNull() {
            addCriterion("handle_datetime is null");
            return (Criteria) this;
        }

        public Criteria andHandleDatetimeIsNotNull() {
            addCriterion("handle_datetime is not null");
            return (Criteria) this;
        }

        public Criteria andHandleDatetimeEqualTo(Date value) {
            addCriterion("handle_datetime =", value, "handleDatetime");
            return (Criteria) this;
        }

        public Criteria andHandleDatetimeNotEqualTo(Date value) {
            addCriterion("handle_datetime <>", value, "handleDatetime");
            return (Criteria) this;
        }

        public Criteria andHandleDatetimeGreaterThan(Date value) {
            addCriterion("handle_datetime >", value, "handleDatetime");
            return (Criteria) this;
        }

        public Criteria andHandleDatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("handle_datetime >=", value, "handleDatetime");
            return (Criteria) this;
        }

        public Criteria andHandleDatetimeLessThan(Date value) {
            addCriterion("handle_datetime <", value, "handleDatetime");
            return (Criteria) this;
        }

        public Criteria andHandleDatetimeLessThanOrEqualTo(Date value) {
            addCriterion("handle_datetime <=", value, "handleDatetime");
            return (Criteria) this;
        }

        public Criteria andHandleDatetimeIn(List<Date> values) {
            addCriterion("handle_datetime in", values, "handleDatetime");
            return (Criteria) this;
        }

        public Criteria andHandleDatetimeNotIn(List<Date> values) {
            addCriterion("handle_datetime not in", values, "handleDatetime");
            return (Criteria) this;
        }

        public Criteria andHandleDatetimeBetween(Date value1, Date value2) {
            addCriterion("handle_datetime between", value1, value2, "handleDatetime");
            return (Criteria) this;
        }

        public Criteria andHandleDatetimeNotBetween(Date value1, Date value2) {
            addCriterion("handle_datetime not between", value1, value2, "handleDatetime");
            return (Criteria) this;
        }

        public Criteria andHandlerUidIsNull() {
            addCriterion("handler_uid is null");
            return (Criteria) this;
        }

        public Criteria andHandlerUidIsNotNull() {
            addCriterion("handler_uid is not null");
            return (Criteria) this;
        }

        public Criteria andHandlerUidEqualTo(String value) {
            addCriterion("handler_uid =", value, "handlerUid");
            return (Criteria) this;
        }

        public Criteria andHandlerUidNotEqualTo(String value) {
            addCriterion("handler_uid <>", value, "handlerUid");
            return (Criteria) this;
        }

        public Criteria andHandlerUidGreaterThan(String value) {
            addCriterion("handler_uid >", value, "handlerUid");
            return (Criteria) this;
        }

        public Criteria andHandlerUidGreaterThanOrEqualTo(String value) {
            addCriterion("handler_uid >=", value, "handlerUid");
            return (Criteria) this;
        }

        public Criteria andHandlerUidLessThan(String value) {
            addCriterion("handler_uid <", value, "handlerUid");
            return (Criteria) this;
        }

        public Criteria andHandlerUidLessThanOrEqualTo(String value) {
            addCriterion("handler_uid <=", value, "handlerUid");
            return (Criteria) this;
        }

        public Criteria andHandlerUidLike(String value) {
            addCriterion("handler_uid like", value, "handlerUid");
            return (Criteria) this;
        }

        public Criteria andHandlerUidNotLike(String value) {
            addCriterion("handler_uid not like", value, "handlerUid");
            return (Criteria) this;
        }

        public Criteria andHandlerUidIn(List<String> values) {
            addCriterion("handler_uid in", values, "handlerUid");
            return (Criteria) this;
        }

        public Criteria andHandlerUidNotIn(List<String> values) {
            addCriterion("handler_uid not in", values, "handlerUid");
            return (Criteria) this;
        }

        public Criteria andHandlerUidBetween(String value1, String value2) {
            addCriterion("handler_uid between", value1, value2, "handlerUid");
            return (Criteria) this;
        }

        public Criteria andHandlerUidNotBetween(String value1, String value2) {
            addCriterion("handler_uid not between", value1, value2, "handlerUid");
            return (Criteria) this;
        }

        public Criteria andCheckDatetimeIsNull() {
            addCriterion("check_datetime is null");
            return (Criteria) this;
        }

        public Criteria andCheckDatetimeIsNotNull() {
            addCriterion("check_datetime is not null");
            return (Criteria) this;
        }

        public Criteria andCheckDatetimeEqualTo(Date value) {
            addCriterion("check_datetime =", value, "checkDatetime");
            return (Criteria) this;
        }

        public Criteria andCheckDatetimeNotEqualTo(Date value) {
            addCriterion("check_datetime <>", value, "checkDatetime");
            return (Criteria) this;
        }

        public Criteria andCheckDatetimeGreaterThan(Date value) {
            addCriterion("check_datetime >", value, "checkDatetime");
            return (Criteria) this;
        }

        public Criteria andCheckDatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("check_datetime >=", value, "checkDatetime");
            return (Criteria) this;
        }

        public Criteria andCheckDatetimeLessThan(Date value) {
            addCriterion("check_datetime <", value, "checkDatetime");
            return (Criteria) this;
        }

        public Criteria andCheckDatetimeLessThanOrEqualTo(Date value) {
            addCriterion("check_datetime <=", value, "checkDatetime");
            return (Criteria) this;
        }

        public Criteria andCheckDatetimeIn(List<Date> values) {
            addCriterion("check_datetime in", values, "checkDatetime");
            return (Criteria) this;
        }

        public Criteria andCheckDatetimeNotIn(List<Date> values) {
            addCriterion("check_datetime not in", values, "checkDatetime");
            return (Criteria) this;
        }

        public Criteria andCheckDatetimeBetween(Date value1, Date value2) {
            addCriterion("check_datetime between", value1, value2, "checkDatetime");
            return (Criteria) this;
        }

        public Criteria andCheckDatetimeNotBetween(Date value1, Date value2) {
            addCriterion("check_datetime not between", value1, value2, "checkDatetime");
            return (Criteria) this;
        }

        public Criteria andCheckerUidIsNull() {
            addCriterion("checker_uid is null");
            return (Criteria) this;
        }

        public Criteria andCheckerUidIsNotNull() {
            addCriterion("checker_uid is not null");
            return (Criteria) this;
        }

        public Criteria andCheckerUidEqualTo(String value) {
            addCriterion("checker_uid =", value, "checkerUid");
            return (Criteria) this;
        }

        public Criteria andCheckerUidNotEqualTo(String value) {
            addCriterion("checker_uid <>", value, "checkerUid");
            return (Criteria) this;
        }

        public Criteria andCheckerUidGreaterThan(String value) {
            addCriterion("checker_uid >", value, "checkerUid");
            return (Criteria) this;
        }

        public Criteria andCheckerUidGreaterThanOrEqualTo(String value) {
            addCriterion("checker_uid >=", value, "checkerUid");
            return (Criteria) this;
        }

        public Criteria andCheckerUidLessThan(String value) {
            addCriterion("checker_uid <", value, "checkerUid");
            return (Criteria) this;
        }

        public Criteria andCheckerUidLessThanOrEqualTo(String value) {
            addCriterion("checker_uid <=", value, "checkerUid");
            return (Criteria) this;
        }

        public Criteria andCheckerUidLike(String value) {
            addCriterion("checker_uid like", value, "checkerUid");
            return (Criteria) this;
        }

        public Criteria andCheckerUidNotLike(String value) {
            addCriterion("checker_uid not like", value, "checkerUid");
            return (Criteria) this;
        }

        public Criteria andCheckerUidIn(List<String> values) {
            addCriterion("checker_uid in", values, "checkerUid");
            return (Criteria) this;
        }

        public Criteria andCheckerUidNotIn(List<String> values) {
            addCriterion("checker_uid not in", values, "checkerUid");
            return (Criteria) this;
        }

        public Criteria andCheckerUidBetween(String value1, String value2) {
            addCriterion("checker_uid between", value1, value2, "checkerUid");
            return (Criteria) this;
        }

        public Criteria andCheckerUidNotBetween(String value1, String value2) {
            addCriterion("checker_uid not between", value1, value2, "checkerUid");
            return (Criteria) this;
        }

        public Criteria andApprovalDatetimeIsNull() {
            addCriterion("approval_datetime is null");
            return (Criteria) this;
        }

        public Criteria andApprovalDatetimeIsNotNull() {
            addCriterion("approval_datetime is not null");
            return (Criteria) this;
        }

        public Criteria andApprovalDatetimeEqualTo(Date value) {
            addCriterion("approval_datetime =", value, "approvalDatetime");
            return (Criteria) this;
        }

        public Criteria andApprovalDatetimeNotEqualTo(Date value) {
            addCriterion("approval_datetime <>", value, "approvalDatetime");
            return (Criteria) this;
        }

        public Criteria andApprovalDatetimeGreaterThan(Date value) {
            addCriterion("approval_datetime >", value, "approvalDatetime");
            return (Criteria) this;
        }

        public Criteria andApprovalDatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("approval_datetime >=", value, "approvalDatetime");
            return (Criteria) this;
        }

        public Criteria andApprovalDatetimeLessThan(Date value) {
            addCriterion("approval_datetime <", value, "approvalDatetime");
            return (Criteria) this;
        }

        public Criteria andApprovalDatetimeLessThanOrEqualTo(Date value) {
            addCriterion("approval_datetime <=", value, "approvalDatetime");
            return (Criteria) this;
        }

        public Criteria andApprovalDatetimeIn(List<Date> values) {
            addCriterion("approval_datetime in", values, "approvalDatetime");
            return (Criteria) this;
        }

        public Criteria andApprovalDatetimeNotIn(List<Date> values) {
            addCriterion("approval_datetime not in", values, "approvalDatetime");
            return (Criteria) this;
        }

        public Criteria andApprovalDatetimeBetween(Date value1, Date value2) {
            addCriterion("approval_datetime between", value1, value2, "approvalDatetime");
            return (Criteria) this;
        }

        public Criteria andApprovalDatetimeNotBetween(Date value1, Date value2) {
            addCriterion("approval_datetime not between", value1, value2, "approvalDatetime");
            return (Criteria) this;
        }

        public Criteria andManagerUidIsNull() {
            addCriterion("manager_uid is null");
            return (Criteria) this;
        }

        public Criteria andManagerUidIsNotNull() {
            addCriterion("manager_uid is not null");
            return (Criteria) this;
        }

        public Criteria andManagerUidEqualTo(String value) {
            addCriterion("manager_uid =", value, "managerUid");
            return (Criteria) this;
        }

        public Criteria andManagerUidNotEqualTo(String value) {
            addCriterion("manager_uid <>", value, "managerUid");
            return (Criteria) this;
        }

        public Criteria andManagerUidGreaterThan(String value) {
            addCriterion("manager_uid >", value, "managerUid");
            return (Criteria) this;
        }

        public Criteria andManagerUidGreaterThanOrEqualTo(String value) {
            addCriterion("manager_uid >=", value, "managerUid");
            return (Criteria) this;
        }

        public Criteria andManagerUidLessThan(String value) {
            addCriterion("manager_uid <", value, "managerUid");
            return (Criteria) this;
        }

        public Criteria andManagerUidLessThanOrEqualTo(String value) {
            addCriterion("manager_uid <=", value, "managerUid");
            return (Criteria) this;
        }

        public Criteria andManagerUidLike(String value) {
            addCriterion("manager_uid like", value, "managerUid");
            return (Criteria) this;
        }

        public Criteria andManagerUidNotLike(String value) {
            addCriterion("manager_uid not like", value, "managerUid");
            return (Criteria) this;
        }

        public Criteria andManagerUidIn(List<String> values) {
            addCriterion("manager_uid in", values, "managerUid");
            return (Criteria) this;
        }

        public Criteria andManagerUidNotIn(List<String> values) {
            addCriterion("manager_uid not in", values, "managerUid");
            return (Criteria) this;
        }

        public Criteria andManagerUidBetween(String value1, String value2) {
            addCriterion("manager_uid between", value1, value2, "managerUid");
            return (Criteria) this;
        }

        public Criteria andManagerUidNotBetween(String value1, String value2) {
            addCriterion("manager_uid not between", value1, value2, "managerUid");
            return (Criteria) this;
        }

        public Criteria andOrgIdIsNull() {
            addCriterion("org_id is null");
            return (Criteria) this;
        }

        public Criteria andOrgIdIsNotNull() {
            addCriterion("org_id is not null");
            return (Criteria) this;
        }

        public Criteria andOrgIdEqualTo(String value) {
            addCriterion("org_id =", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdNotEqualTo(String value) {
            addCriterion("org_id <>", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdGreaterThan(String value) {
            addCriterion("org_id >", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdGreaterThanOrEqualTo(String value) {
            addCriterion("org_id >=", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdLessThan(String value) {
            addCriterion("org_id <", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdLessThanOrEqualTo(String value) {
            addCriterion("org_id <=", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdLike(String value) {
            addCriterion("org_id like", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdNotLike(String value) {
            addCriterion("org_id not like", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdIn(List<String> values) {
            addCriterion("org_id in", values, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdNotIn(List<String> values) {
            addCriterion("org_id not in", values, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdBetween(String value1, String value2) {
            addCriterion("org_id between", value1, value2, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdNotBetween(String value1, String value2) {
            addCriterion("org_id not between", value1, value2, "orgId");
            return (Criteria) this;
        }

        public Criteria andFinishedFlagIsNull() {
            addCriterion("finished_flag is null");
            return (Criteria) this;
        }

        public Criteria andFinishedFlagIsNotNull() {
            addCriterion("finished_flag is not null");
            return (Criteria) this;
        }

        public Criteria andFinishedFlagEqualTo(String value) {
            addCriterion("finished_flag =", value, "finishedFlag");
            return (Criteria) this;
        }

        public Criteria andFinishedFlagNotEqualTo(String value) {
            addCriterion("finished_flag <>", value, "finishedFlag");
            return (Criteria) this;
        }

        public Criteria andFinishedFlagGreaterThan(String value) {
            addCriterion("finished_flag >", value, "finishedFlag");
            return (Criteria) this;
        }

        public Criteria andFinishedFlagGreaterThanOrEqualTo(String value) {
            addCriterion("finished_flag >=", value, "finishedFlag");
            return (Criteria) this;
        }

        public Criteria andFinishedFlagLessThan(String value) {
            addCriterion("finished_flag <", value, "finishedFlag");
            return (Criteria) this;
        }

        public Criteria andFinishedFlagLessThanOrEqualTo(String value) {
            addCriterion("finished_flag <=", value, "finishedFlag");
            return (Criteria) this;
        }

        public Criteria andFinishedFlagLike(String value) {
            addCriterion("finished_flag like", value, "finishedFlag");
            return (Criteria) this;
        }

        public Criteria andFinishedFlagNotLike(String value) {
            addCriterion("finished_flag not like", value, "finishedFlag");
            return (Criteria) this;
        }

        public Criteria andFinishedFlagIn(List<String> values) {
            addCriterion("finished_flag in", values, "finishedFlag");
            return (Criteria) this;
        }

        public Criteria andFinishedFlagNotIn(List<String> values) {
            addCriterion("finished_flag not in", values, "finishedFlag");
            return (Criteria) this;
        }

        public Criteria andFinishedFlagBetween(String value1, String value2) {
            addCriterion("finished_flag between", value1, value2, "finishedFlag");
            return (Criteria) this;
        }

        public Criteria andFinishedFlagNotBetween(String value1, String value2) {
            addCriterion("finished_flag not between", value1, value2, "finishedFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteDatetimeIsNull() {
            addCriterion("delete_datetime is null");
            return (Criteria) this;
        }

        public Criteria andDeleteDatetimeIsNotNull() {
            addCriterion("delete_datetime is not null");
            return (Criteria) this;
        }

        public Criteria andDeleteDatetimeEqualTo(Date value) {
            addCriterion("delete_datetime =", value, "deleteDatetime");
            return (Criteria) this;
        }

        public Criteria andDeleteDatetimeNotEqualTo(Date value) {
            addCriterion("delete_datetime <>", value, "deleteDatetime");
            return (Criteria) this;
        }

        public Criteria andDeleteDatetimeGreaterThan(Date value) {
            addCriterion("delete_datetime >", value, "deleteDatetime");
            return (Criteria) this;
        }

        public Criteria andDeleteDatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("delete_datetime >=", value, "deleteDatetime");
            return (Criteria) this;
        }

        public Criteria andDeleteDatetimeLessThan(Date value) {
            addCriterion("delete_datetime <", value, "deleteDatetime");
            return (Criteria) this;
        }

        public Criteria andDeleteDatetimeLessThanOrEqualTo(Date value) {
            addCriterion("delete_datetime <=", value, "deleteDatetime");
            return (Criteria) this;
        }

        public Criteria andDeleteDatetimeIn(List<Date> values) {
            addCriterion("delete_datetime in", values, "deleteDatetime");
            return (Criteria) this;
        }

        public Criteria andDeleteDatetimeNotIn(List<Date> values) {
            addCriterion("delete_datetime not in", values, "deleteDatetime");
            return (Criteria) this;
        }

        public Criteria andDeleteDatetimeBetween(Date value1, Date value2) {
            addCriterion("delete_datetime between", value1, value2, "deleteDatetime");
            return (Criteria) this;
        }

        public Criteria andDeleteDatetimeNotBetween(Date value1, Date value2) {
            addCriterion("delete_datetime not between", value1, value2, "deleteDatetime");
            return (Criteria) this;
        }

        public Criteria andRelationOpIdIsNull() {
            addCriterion("relation_op_id is null");
            return (Criteria) this;
        }

        public Criteria andRelationOpIdIsNotNull() {
            addCriterion("relation_op_id is not null");
            return (Criteria) this;
        }

        public Criteria andRelationOpIdEqualTo(String value) {
            addCriterion("relation_op_id =", value, "relationOpId");
            return (Criteria) this;
        }

        public Criteria andRelationOpIdNotEqualTo(String value) {
            addCriterion("relation_op_id <>", value, "relationOpId");
            return (Criteria) this;
        }

        public Criteria andRelationOpIdGreaterThan(String value) {
            addCriterion("relation_op_id >", value, "relationOpId");
            return (Criteria) this;
        }

        public Criteria andRelationOpIdGreaterThanOrEqualTo(String value) {
            addCriterion("relation_op_id >=", value, "relationOpId");
            return (Criteria) this;
        }

        public Criteria andRelationOpIdLessThan(String value) {
            addCriterion("relation_op_id <", value, "relationOpId");
            return (Criteria) this;
        }

        public Criteria andRelationOpIdLessThanOrEqualTo(String value) {
            addCriterion("relation_op_id <=", value, "relationOpId");
            return (Criteria) this;
        }

        public Criteria andRelationOpIdLike(String value) {
            addCriterion("relation_op_id like", value, "relationOpId");
            return (Criteria) this;
        }

        public Criteria andRelationOpIdNotLike(String value) {
            addCriterion("relation_op_id not like", value, "relationOpId");
            return (Criteria) this;
        }

        public Criteria andRelationOpIdIn(List<String> values) {
            addCriterion("relation_op_id in", values, "relationOpId");
            return (Criteria) this;
        }

        public Criteria andRelationOpIdNotIn(List<String> values) {
            addCriterion("relation_op_id not in", values, "relationOpId");
            return (Criteria) this;
        }

        public Criteria andRelationOpIdBetween(String value1, String value2) {
            addCriterion("relation_op_id between", value1, value2, "relationOpId");
            return (Criteria) this;
        }

        public Criteria andRelationOpIdNotBetween(String value1, String value2) {
            addCriterion("relation_op_id not between", value1, value2, "relationOpId");
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