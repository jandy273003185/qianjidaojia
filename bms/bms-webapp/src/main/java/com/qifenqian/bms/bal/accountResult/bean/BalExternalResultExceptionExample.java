package com.qifenqian.bms.bal.accountResult.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BalExternalResultExceptionExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BalExternalResultExceptionExample() {
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

        public Criteria andExceptionIdIsNull() {
            addCriterion("exception_id is null");
            return (Criteria) this;
        }

        public Criteria andExceptionIdIsNotNull() {
            addCriterion("exception_id is not null");
            return (Criteria) this;
        }

        public Criteria andExceptionIdEqualTo(Integer value) {
            addCriterion("exception_id =", value, "exceptionId");
            return (Criteria) this;
        }

        public Criteria andExceptionIdNotEqualTo(Integer value) {
            addCriterion("exception_id <>", value, "exceptionId");
            return (Criteria) this;
        }

        public Criteria andExceptionIdGreaterThan(Integer value) {
            addCriterion("exception_id >", value, "exceptionId");
            return (Criteria) this;
        }

        public Criteria andExceptionIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("exception_id >=", value, "exceptionId");
            return (Criteria) this;
        }

        public Criteria andExceptionIdLessThan(Integer value) {
            addCriterion("exception_id <", value, "exceptionId");
            return (Criteria) this;
        }

        public Criteria andExceptionIdLessThanOrEqualTo(Integer value) {
            addCriterion("exception_id <=", value, "exceptionId");
            return (Criteria) this;
        }

        public Criteria andExceptionIdIn(List<Integer> values) {
            addCriterion("exception_id in", values, "exceptionId");
            return (Criteria) this;
        }

        public Criteria andExceptionIdNotIn(List<Integer> values) {
            addCriterion("exception_id not in", values, "exceptionId");
            return (Criteria) this;
        }

        public Criteria andExceptionIdBetween(Integer value1, Integer value2) {
            addCriterion("exception_id between", value1, value2, "exceptionId");
            return (Criteria) this;
        }

        public Criteria andExceptionIdNotBetween(Integer value1, Integer value2) {
            addCriterion("exception_id not between", value1, value2, "exceptionId");
            return (Criteria) this;
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

        public Criteria andFileIdIsNull() {
            addCriterion("file_id is null");
            return (Criteria) this;
        }

        public Criteria andFileIdIsNotNull() {
            addCriterion("file_id is not null");
            return (Criteria) this;
        }

        public Criteria andFileIdEqualTo(String value) {
            addCriterion("file_id =", value, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdNotEqualTo(String value) {
            addCriterion("file_id <>", value, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdGreaterThan(String value) {
            addCriterion("file_id >", value, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdGreaterThanOrEqualTo(String value) {
            addCriterion("file_id >=", value, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdLessThan(String value) {
            addCriterion("file_id <", value, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdLessThanOrEqualTo(String value) {
            addCriterion("file_id <=", value, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdLike(String value) {
            addCriterion("file_id like", value, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdNotLike(String value) {
            addCriterion("file_id not like", value, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdIn(List<String> values) {
            addCriterion("file_id in", values, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdNotIn(List<String> values) {
            addCriterion("file_id not in", values, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdBetween(String value1, String value2) {
            addCriterion("file_id between", value1, value2, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdNotBetween(String value1, String value2) {
            addCriterion("file_id not between", value1, value2, "fileId");
            return (Criteria) this;
        }

        public Criteria andSeqIsNull() {
            addCriterion("seq is null");
            return (Criteria) this;
        }

        public Criteria andSeqIsNotNull() {
            addCriterion("seq is not null");
            return (Criteria) this;
        }

        public Criteria andSeqEqualTo(Integer value) {
            addCriterion("seq =", value, "seq");
            return (Criteria) this;
        }

        public Criteria andSeqNotEqualTo(Integer value) {
            addCriterion("seq <>", value, "seq");
            return (Criteria) this;
        }

        public Criteria andSeqGreaterThan(Integer value) {
            addCriterion("seq >", value, "seq");
            return (Criteria) this;
        }

        public Criteria andSeqGreaterThanOrEqualTo(Integer value) {
            addCriterion("seq >=", value, "seq");
            return (Criteria) this;
        }

        public Criteria andSeqLessThan(Integer value) {
            addCriterion("seq <", value, "seq");
            return (Criteria) this;
        }

        public Criteria andSeqLessThanOrEqualTo(Integer value) {
            addCriterion("seq <=", value, "seq");
            return (Criteria) this;
        }

        public Criteria andSeqIn(List<Integer> values) {
            addCriterion("seq in", values, "seq");
            return (Criteria) this;
        }

        public Criteria andSeqNotIn(List<Integer> values) {
            addCriterion("seq not in", values, "seq");
            return (Criteria) this;
        }

        public Criteria andSeqBetween(Integer value1, Integer value2) {
            addCriterion("seq between", value1, value2, "seq");
            return (Criteria) this;
        }

        public Criteria andSeqNotBetween(Integer value1, Integer value2) {
            addCriterion("seq not between", value1, value2, "seq");
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

        public Criteria andBatchIdIsNull() {
            addCriterion("batch_id is null");
            return (Criteria) this;
        }

        public Criteria andBatchIdIsNotNull() {
            addCriterion("batch_id is not null");
            return (Criteria) this;
        }

        public Criteria andBatchIdEqualTo(String value) {
            addCriterion("batch_id =", value, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdNotEqualTo(String value) {
            addCriterion("batch_id <>", value, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdGreaterThan(String value) {
            addCriterion("batch_id >", value, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdGreaterThanOrEqualTo(String value) {
            addCriterion("batch_id >=", value, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdLessThan(String value) {
            addCriterion("batch_id <", value, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdLessThanOrEqualTo(String value) {
            addCriterion("batch_id <=", value, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdLike(String value) {
            addCriterion("batch_id like", value, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdNotLike(String value) {
            addCriterion("batch_id not like", value, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdIn(List<String> values) {
            addCriterion("batch_id in", values, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdNotIn(List<String> values) {
            addCriterion("batch_id not in", values, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdBetween(String value1, String value2) {
            addCriterion("batch_id between", value1, value2, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdNotBetween(String value1, String value2) {
            addCriterion("batch_id not between", value1, value2, "batchId");
            return (Criteria) this;
        }

        public Criteria andBalResultIsNull() {
            addCriterion("bal_result is null");
            return (Criteria) this;
        }

        public Criteria andBalResultIsNotNull() {
            addCriterion("bal_result is not null");
            return (Criteria) this;
        }

        public Criteria andBalResultEqualTo(String value) {
            addCriterion("bal_result =", value, "balResult");
            return (Criteria) this;
        }

        public Criteria andBalResultNotEqualTo(String value) {
            addCriterion("bal_result <>", value, "balResult");
            return (Criteria) this;
        }

        public Criteria andBalResultGreaterThan(String value) {
            addCriterion("bal_result >", value, "balResult");
            return (Criteria) this;
        }

        public Criteria andBalResultGreaterThanOrEqualTo(String value) {
            addCriterion("bal_result >=", value, "balResult");
            return (Criteria) this;
        }

        public Criteria andBalResultLessThan(String value) {
            addCriterion("bal_result <", value, "balResult");
            return (Criteria) this;
        }

        public Criteria andBalResultLessThanOrEqualTo(String value) {
            addCriterion("bal_result <=", value, "balResult");
            return (Criteria) this;
        }

        public Criteria andBalResultLike(String value) {
            addCriterion("bal_result like", value, "balResult");
            return (Criteria) this;
        }

        public Criteria andBalResultNotLike(String value) {
            addCriterion("bal_result not like", value, "balResult");
            return (Criteria) this;
        }

        public Criteria andBalResultIn(List<String> values) {
            addCriterion("bal_result in", values, "balResult");
            return (Criteria) this;
        }

        public Criteria andBalResultNotIn(List<String> values) {
            addCriterion("bal_result not in", values, "balResult");
            return (Criteria) this;
        }

        public Criteria andBalResultBetween(String value1, String value2) {
            addCriterion("bal_result between", value1, value2, "balResult");
            return (Criteria) this;
        }

        public Criteria andBalResultNotBetween(String value1, String value2) {
            addCriterion("bal_result not between", value1, value2, "balResult");
            return (Criteria) this;
        }

        public Criteria andBalMemoIsNull() {
            addCriterion("bal_memo is null");
            return (Criteria) this;
        }

        public Criteria andBalMemoIsNotNull() {
            addCriterion("bal_memo is not null");
            return (Criteria) this;
        }

        public Criteria andBalMemoEqualTo(String value) {
            addCriterion("bal_memo =", value, "balMemo");
            return (Criteria) this;
        }

        public Criteria andBalMemoNotEqualTo(String value) {
            addCriterion("bal_memo <>", value, "balMemo");
            return (Criteria) this;
        }

        public Criteria andBalMemoGreaterThan(String value) {
            addCriterion("bal_memo >", value, "balMemo");
            return (Criteria) this;
        }

        public Criteria andBalMemoGreaterThanOrEqualTo(String value) {
            addCriterion("bal_memo >=", value, "balMemo");
            return (Criteria) this;
        }

        public Criteria andBalMemoLessThan(String value) {
            addCriterion("bal_memo <", value, "balMemo");
            return (Criteria) this;
        }

        public Criteria andBalMemoLessThanOrEqualTo(String value) {
            addCriterion("bal_memo <=", value, "balMemo");
            return (Criteria) this;
        }

        public Criteria andBalMemoLike(String value) {
            addCriterion("bal_memo like", value, "balMemo");
            return (Criteria) this;
        }

        public Criteria andBalMemoNotLike(String value) {
            addCriterion("bal_memo not like", value, "balMemo");
            return (Criteria) this;
        }

        public Criteria andBalMemoIn(List<String> values) {
            addCriterion("bal_memo in", values, "balMemo");
            return (Criteria) this;
        }

        public Criteria andBalMemoNotIn(List<String> values) {
            addCriterion("bal_memo not in", values, "balMemo");
            return (Criteria) this;
        }

        public Criteria andBalMemoBetween(String value1, String value2) {
            addCriterion("bal_memo between", value1, value2, "balMemo");
            return (Criteria) this;
        }

        public Criteria andBalMemoNotBetween(String value1, String value2) {
            addCriterion("bal_memo not between", value1, value2, "balMemo");
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

        public Criteria andDealFlagIsNull() {
            addCriterion("deal_flag is null");
            return (Criteria) this;
        }

        public Criteria andDealFlagIsNotNull() {
            addCriterion("deal_flag is not null");
            return (Criteria) this;
        }

        public Criteria andDealFlagEqualTo(String value) {
            addCriterion("deal_flag =", value, "dealFlag");
            return (Criteria) this;
        }

        public Criteria andDealFlagNotEqualTo(String value) {
            addCriterion("deal_flag <>", value, "dealFlag");
            return (Criteria) this;
        }

        public Criteria andDealFlagGreaterThan(String value) {
            addCriterion("deal_flag >", value, "dealFlag");
            return (Criteria) this;
        }

        public Criteria andDealFlagGreaterThanOrEqualTo(String value) {
            addCriterion("deal_flag >=", value, "dealFlag");
            return (Criteria) this;
        }

        public Criteria andDealFlagLessThan(String value) {
            addCriterion("deal_flag <", value, "dealFlag");
            return (Criteria) this;
        }

        public Criteria andDealFlagLessThanOrEqualTo(String value) {
            addCriterion("deal_flag <=", value, "dealFlag");
            return (Criteria) this;
        }

        public Criteria andDealFlagLike(String value) {
            addCriterion("deal_flag like", value, "dealFlag");
            return (Criteria) this;
        }

        public Criteria andDealFlagNotLike(String value) {
            addCriterion("deal_flag not like", value, "dealFlag");
            return (Criteria) this;
        }

        public Criteria andDealFlagIn(List<String> values) {
            addCriterion("deal_flag in", values, "dealFlag");
            return (Criteria) this;
        }

        public Criteria andDealFlagNotIn(List<String> values) {
            addCriterion("deal_flag not in", values, "dealFlag");
            return (Criteria) this;
        }

        public Criteria andDealFlagBetween(String value1, String value2) {
            addCriterion("deal_flag between", value1, value2, "dealFlag");
            return (Criteria) this;
        }

        public Criteria andDealFlagNotBetween(String value1, String value2) {
            addCriterion("deal_flag not between", value1, value2, "dealFlag");
            return (Criteria) this;
        }

        public Criteria andDealMemoIsNull() {
            addCriterion("deal_memo is null");
            return (Criteria) this;
        }

        public Criteria andDealMemoIsNotNull() {
            addCriterion("deal_memo is not null");
            return (Criteria) this;
        }

        public Criteria andDealMemoEqualTo(String value) {
            addCriterion("deal_memo =", value, "dealMemo");
            return (Criteria) this;
        }

        public Criteria andDealMemoNotEqualTo(String value) {
            addCriterion("deal_memo <>", value, "dealMemo");
            return (Criteria) this;
        }

        public Criteria andDealMemoGreaterThan(String value) {
            addCriterion("deal_memo >", value, "dealMemo");
            return (Criteria) this;
        }

        public Criteria andDealMemoGreaterThanOrEqualTo(String value) {
            addCriterion("deal_memo >=", value, "dealMemo");
            return (Criteria) this;
        }

        public Criteria andDealMemoLessThan(String value) {
            addCriterion("deal_memo <", value, "dealMemo");
            return (Criteria) this;
        }

        public Criteria andDealMemoLessThanOrEqualTo(String value) {
            addCriterion("deal_memo <=", value, "dealMemo");
            return (Criteria) this;
        }

        public Criteria andDealMemoLike(String value) {
            addCriterion("deal_memo like", value, "dealMemo");
            return (Criteria) this;
        }

        public Criteria andDealMemoNotLike(String value) {
            addCriterion("deal_memo not like", value, "dealMemo");
            return (Criteria) this;
        }

        public Criteria andDealMemoIn(List<String> values) {
            addCriterion("deal_memo in", values, "dealMemo");
            return (Criteria) this;
        }

        public Criteria andDealMemoNotIn(List<String> values) {
            addCriterion("deal_memo not in", values, "dealMemo");
            return (Criteria) this;
        }

        public Criteria andDealMemoBetween(String value1, String value2) {
            addCriterion("deal_memo between", value1, value2, "dealMemo");
            return (Criteria) this;
        }

        public Criteria andDealMemoNotBetween(String value1, String value2) {
            addCriterion("deal_memo not between", value1, value2, "dealMemo");
            return (Criteria) this;
        }

        public Criteria andDealUserIsNull() {
            addCriterion("deal_user is null");
            return (Criteria) this;
        }

        public Criteria andDealUserIsNotNull() {
            addCriterion("deal_user is not null");
            return (Criteria) this;
        }

        public Criteria andDealUserEqualTo(Integer value) {
            addCriterion("deal_user =", value, "dealUser");
            return (Criteria) this;
        }

        public Criteria andDealUserNotEqualTo(Integer value) {
            addCriterion("deal_user <>", value, "dealUser");
            return (Criteria) this;
        }

        public Criteria andDealUserGreaterThan(Integer value) {
            addCriterion("deal_user >", value, "dealUser");
            return (Criteria) this;
        }

        public Criteria andDealUserGreaterThanOrEqualTo(Integer value) {
            addCriterion("deal_user >=", value, "dealUser");
            return (Criteria) this;
        }

        public Criteria andDealUserLessThan(Integer value) {
            addCriterion("deal_user <", value, "dealUser");
            return (Criteria) this;
        }

        public Criteria andDealUserLessThanOrEqualTo(Integer value) {
            addCriterion("deal_user <=", value, "dealUser");
            return (Criteria) this;
        }

        public Criteria andDealUserIn(List<Integer> values) {
            addCriterion("deal_user in", values, "dealUser");
            return (Criteria) this;
        }

        public Criteria andDealUserNotIn(List<Integer> values) {
            addCriterion("deal_user not in", values, "dealUser");
            return (Criteria) this;
        }

        public Criteria andDealUserBetween(Integer value1, Integer value2) {
            addCriterion("deal_user between", value1, value2, "dealUser");
            return (Criteria) this;
        }

        public Criteria andDealUserNotBetween(Integer value1, Integer value2) {
            addCriterion("deal_user not between", value1, value2, "dealUser");
            return (Criteria) this;
        }

        public Criteria andDealDatetimeIsNull() {
            addCriterion("deal_datetime is null");
            return (Criteria) this;
        }

        public Criteria andDealDatetimeIsNotNull() {
            addCriterion("deal_datetime is not null");
            return (Criteria) this;
        }

        public Criteria andDealDatetimeEqualTo(Date value) {
            addCriterion("deal_datetime =", value, "dealDatetime");
            return (Criteria) this;
        }

        public Criteria andDealDatetimeNotEqualTo(Date value) {
            addCriterion("deal_datetime <>", value, "dealDatetime");
            return (Criteria) this;
        }

        public Criteria andDealDatetimeGreaterThan(Date value) {
            addCriterion("deal_datetime >", value, "dealDatetime");
            return (Criteria) this;
        }

        public Criteria andDealDatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("deal_datetime >=", value, "dealDatetime");
            return (Criteria) this;
        }

        public Criteria andDealDatetimeLessThan(Date value) {
            addCriterion("deal_datetime <", value, "dealDatetime");
            return (Criteria) this;
        }

        public Criteria andDealDatetimeLessThanOrEqualTo(Date value) {
            addCriterion("deal_datetime <=", value, "dealDatetime");
            return (Criteria) this;
        }

        public Criteria andDealDatetimeIn(List<Date> values) {
            addCriterion("deal_datetime in", values, "dealDatetime");
            return (Criteria) this;
        }

        public Criteria andDealDatetimeNotIn(List<Date> values) {
            addCriterion("deal_datetime not in", values, "dealDatetime");
            return (Criteria) this;
        }

        public Criteria andDealDatetimeBetween(Date value1, Date value2) {
            addCriterion("deal_datetime between", value1, value2, "dealDatetime");
            return (Criteria) this;
        }

        public Criteria andDealDatetimeNotBetween(Date value1, Date value2) {
            addCriterion("deal_datetime not between", value1, value2, "dealDatetime");
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