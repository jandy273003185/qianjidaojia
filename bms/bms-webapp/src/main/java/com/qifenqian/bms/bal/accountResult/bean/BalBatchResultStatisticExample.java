package com.qifenqian.bms.bal.accountResult.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BalBatchResultStatisticExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BalBatchResultStatisticExample() {
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

        public Criteria andStatisticIdIsNull() {
            addCriterion("statistic_id is null");
            return (Criteria) this;
        }

        public Criteria andStatisticIdIsNotNull() {
            addCriterion("statistic_id is not null");
            return (Criteria) this;
        }

        public Criteria andStatisticIdEqualTo(Integer value) {
            addCriterion("statistic_id =", value, "statisticId");
            return (Criteria) this;
        }

        public Criteria andStatisticIdNotEqualTo(Integer value) {
            addCriterion("statistic_id <>", value, "statisticId");
            return (Criteria) this;
        }

        public Criteria andStatisticIdGreaterThan(Integer value) {
            addCriterion("statistic_id >", value, "statisticId");
            return (Criteria) this;
        }

        public Criteria andStatisticIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("statistic_id >=", value, "statisticId");
            return (Criteria) this;
        }

        public Criteria andStatisticIdLessThan(Integer value) {
            addCriterion("statistic_id <", value, "statisticId");
            return (Criteria) this;
        }

        public Criteria andStatisticIdLessThanOrEqualTo(Integer value) {
            addCriterion("statistic_id <=", value, "statisticId");
            return (Criteria) this;
        }

        public Criteria andStatisticIdIn(List<Integer> values) {
            addCriterion("statistic_id in", values, "statisticId");
            return (Criteria) this;
        }

        public Criteria andStatisticIdNotIn(List<Integer> values) {
            addCriterion("statistic_id not in", values, "statisticId");
            return (Criteria) this;
        }

        public Criteria andStatisticIdBetween(Integer value1, Integer value2) {
            addCriterion("statistic_id between", value1, value2, "statisticId");
            return (Criteria) this;
        }

        public Criteria andStatisticIdNotBetween(Integer value1, Integer value2) {
            addCriterion("statistic_id not between", value1, value2, "statisticId");
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

        public Criteria andBalDateIsNull() {
            addCriterion("bal_date is null");
            return (Criteria) this;
        }

        public Criteria andBalDateIsNotNull() {
            addCriterion("bal_date is not null");
            return (Criteria) this;
        }

        public Criteria andBalDateEqualTo(String value) {
            addCriterion("bal_date =", value, "balDate");
            return (Criteria) this;
        }

        public Criteria andBalDateNotEqualTo(String value) {
            addCriterion("bal_date <>", value, "balDate");
            return (Criteria) this;
        }

        public Criteria andBalDateGreaterThan(String value) {
            addCriterion("bal_date >", value, "balDate");
            return (Criteria) this;
        }

        public Criteria andBalDateGreaterThanOrEqualTo(String value) {
            addCriterion("bal_date >=", value, "balDate");
            return (Criteria) this;
        }

        public Criteria andBalDateLessThan(String value) {
            addCriterion("bal_date <", value, "balDate");
            return (Criteria) this;
        }

        public Criteria andBalDateLessThanOrEqualTo(String value) {
            addCriterion("bal_date <=", value, "balDate");
            return (Criteria) this;
        }

        public Criteria andBalDateLike(String value) {
            addCriterion("bal_date like", value, "balDate");
            return (Criteria) this;
        }

        public Criteria andBalDateNotLike(String value) {
            addCriterion("bal_date not like", value, "balDate");
            return (Criteria) this;
        }

        public Criteria andBalDateIn(List<String> values) {
            addCriterion("bal_date in", values, "balDate");
            return (Criteria) this;
        }

        public Criteria andBalDateNotIn(List<String> values) {
            addCriterion("bal_date not in", values, "balDate");
            return (Criteria) this;
        }

        public Criteria andBalDateBetween(String value1, String value2) {
            addCriterion("bal_date between", value1, value2, "balDate");
            return (Criteria) this;
        }

        public Criteria andBalDateNotBetween(String value1, String value2) {
            addCriterion("bal_date not between", value1, value2, "balDate");
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

        public Criteria andDataOwnerIsNull() {
            addCriterion("data_owner is null");
            return (Criteria) this;
        }

        public Criteria andDataOwnerIsNotNull() {
            addCriterion("data_owner is not null");
            return (Criteria) this;
        }

        public Criteria andDataOwnerEqualTo(String value) {
            addCriterion("data_owner =", value, "dataOwner");
            return (Criteria) this;
        }

        public Criteria andDataOwnerNotEqualTo(String value) {
            addCriterion("data_owner <>", value, "dataOwner");
            return (Criteria) this;
        }

        public Criteria andDataOwnerGreaterThan(String value) {
            addCriterion("data_owner >", value, "dataOwner");
            return (Criteria) this;
        }

        public Criteria andDataOwnerGreaterThanOrEqualTo(String value) {
            addCriterion("data_owner >=", value, "dataOwner");
            return (Criteria) this;
        }

        public Criteria andDataOwnerLessThan(String value) {
            addCriterion("data_owner <", value, "dataOwner");
            return (Criteria) this;
        }

        public Criteria andDataOwnerLessThanOrEqualTo(String value) {
            addCriterion("data_owner <=", value, "dataOwner");
            return (Criteria) this;
        }

        public Criteria andDataOwnerLike(String value) {
            addCriterion("data_owner like", value, "dataOwner");
            return (Criteria) this;
        }

        public Criteria andDataOwnerNotLike(String value) {
            addCriterion("data_owner not like", value, "dataOwner");
            return (Criteria) this;
        }

        public Criteria andDataOwnerIn(List<String> values) {
            addCriterion("data_owner in", values, "dataOwner");
            return (Criteria) this;
        }

        public Criteria andDataOwnerNotIn(List<String> values) {
            addCriterion("data_owner not in", values, "dataOwner");
            return (Criteria) this;
        }

        public Criteria andDataOwnerBetween(String value1, String value2) {
            addCriterion("data_owner between", value1, value2, "dataOwner");
            return (Criteria) this;
        }

        public Criteria andDataOwnerNotBetween(String value1, String value2) {
            addCriterion("data_owner not between", value1, value2, "dataOwner");
            return (Criteria) this;
        }

        public Criteria andTransStatusIsNull() {
            addCriterion("trans_status is null");
            return (Criteria) this;
        }

        public Criteria andTransStatusIsNotNull() {
            addCriterion("trans_status is not null");
            return (Criteria) this;
        }

        public Criteria andTransStatusEqualTo(String value) {
            addCriterion("trans_status =", value, "transStatus");
            return (Criteria) this;
        }

        public Criteria andTransStatusNotEqualTo(String value) {
            addCriterion("trans_status <>", value, "transStatus");
            return (Criteria) this;
        }

        public Criteria andTransStatusGreaterThan(String value) {
            addCriterion("trans_status >", value, "transStatus");
            return (Criteria) this;
        }

        public Criteria andTransStatusGreaterThanOrEqualTo(String value) {
            addCriterion("trans_status >=", value, "transStatus");
            return (Criteria) this;
        }

        public Criteria andTransStatusLessThan(String value) {
            addCriterion("trans_status <", value, "transStatus");
            return (Criteria) this;
        }

        public Criteria andTransStatusLessThanOrEqualTo(String value) {
            addCriterion("trans_status <=", value, "transStatus");
            return (Criteria) this;
        }

        public Criteria andTransStatusLike(String value) {
            addCriterion("trans_status like", value, "transStatus");
            return (Criteria) this;
        }

        public Criteria andTransStatusNotLike(String value) {
            addCriterion("trans_status not like", value, "transStatus");
            return (Criteria) this;
        }

        public Criteria andTransStatusIn(List<String> values) {
            addCriterion("trans_status in", values, "transStatus");
            return (Criteria) this;
        }

        public Criteria andTransStatusNotIn(List<String> values) {
            addCriterion("trans_status not in", values, "transStatus");
            return (Criteria) this;
        }

        public Criteria andTransStatusBetween(String value1, String value2) {
            addCriterion("trans_status between", value1, value2, "transStatus");
            return (Criteria) this;
        }

        public Criteria andTransStatusNotBetween(String value1, String value2) {
            addCriterion("trans_status not between", value1, value2, "transStatus");
            return (Criteria) this;
        }

        public Criteria andTransTypeIsNull() {
            addCriterion("trans_type is null");
            return (Criteria) this;
        }

        public Criteria andTransTypeIsNotNull() {
            addCriterion("trans_type is not null");
            return (Criteria) this;
        }

        public Criteria andTransTypeEqualTo(String value) {
            addCriterion("trans_type =", value, "transType");
            return (Criteria) this;
        }

        public Criteria andTransTypeNotEqualTo(String value) {
            addCriterion("trans_type <>", value, "transType");
            return (Criteria) this;
        }

        public Criteria andTransTypeGreaterThan(String value) {
            addCriterion("trans_type >", value, "transType");
            return (Criteria) this;
        }

        public Criteria andTransTypeGreaterThanOrEqualTo(String value) {
            addCriterion("trans_type >=", value, "transType");
            return (Criteria) this;
        }

        public Criteria andTransTypeLessThan(String value) {
            addCriterion("trans_type <", value, "transType");
            return (Criteria) this;
        }

        public Criteria andTransTypeLessThanOrEqualTo(String value) {
            addCriterion("trans_type <=", value, "transType");
            return (Criteria) this;
        }

        public Criteria andTransTypeLike(String value) {
            addCriterion("trans_type like", value, "transType");
            return (Criteria) this;
        }

        public Criteria andTransTypeNotLike(String value) {
            addCriterion("trans_type not like", value, "transType");
            return (Criteria) this;
        }

        public Criteria andTransTypeIn(List<String> values) {
            addCriterion("trans_type in", values, "transType");
            return (Criteria) this;
        }

        public Criteria andTransTypeNotIn(List<String> values) {
            addCriterion("trans_type not in", values, "transType");
            return (Criteria) this;
        }

        public Criteria andTransTypeBetween(String value1, String value2) {
            addCriterion("trans_type between", value1, value2, "transType");
            return (Criteria) this;
        }

        public Criteria andTransTypeNotBetween(String value1, String value2) {
            addCriterion("trans_type not between", value1, value2, "transType");
            return (Criteria) this;
        }

        public Criteria andTransCurrencyIsNull() {
            addCriterion("trans_currency is null");
            return (Criteria) this;
        }

        public Criteria andTransCurrencyIsNotNull() {
            addCriterion("trans_currency is not null");
            return (Criteria) this;
        }

        public Criteria andTransCurrencyEqualTo(String value) {
            addCriterion("trans_currency =", value, "transCurrency");
            return (Criteria) this;
        }

        public Criteria andTransCurrencyNotEqualTo(String value) {
            addCriterion("trans_currency <>", value, "transCurrency");
            return (Criteria) this;
        }

        public Criteria andTransCurrencyGreaterThan(String value) {
            addCriterion("trans_currency >", value, "transCurrency");
            return (Criteria) this;
        }

        public Criteria andTransCurrencyGreaterThanOrEqualTo(String value) {
            addCriterion("trans_currency >=", value, "transCurrency");
            return (Criteria) this;
        }

        public Criteria andTransCurrencyLessThan(String value) {
            addCriterion("trans_currency <", value, "transCurrency");
            return (Criteria) this;
        }

        public Criteria andTransCurrencyLessThanOrEqualTo(String value) {
            addCriterion("trans_currency <=", value, "transCurrency");
            return (Criteria) this;
        }

        public Criteria andTransCurrencyLike(String value) {
            addCriterion("trans_currency like", value, "transCurrency");
            return (Criteria) this;
        }

        public Criteria andTransCurrencyNotLike(String value) {
            addCriterion("trans_currency not like", value, "transCurrency");
            return (Criteria) this;
        }

        public Criteria andTransCurrencyIn(List<String> values) {
            addCriterion("trans_currency in", values, "transCurrency");
            return (Criteria) this;
        }

        public Criteria andTransCurrencyNotIn(List<String> values) {
            addCriterion("trans_currency not in", values, "transCurrency");
            return (Criteria) this;
        }

        public Criteria andTransCurrencyBetween(String value1, String value2) {
            addCriterion("trans_currency between", value1, value2, "transCurrency");
            return (Criteria) this;
        }

        public Criteria andTransCurrencyNotBetween(String value1, String value2) {
            addCriterion("trans_currency not between", value1, value2, "transCurrency");
            return (Criteria) this;
        }

        public Criteria andTotalCountIsNull() {
            addCriterion("total_count is null");
            return (Criteria) this;
        }

        public Criteria andTotalCountIsNotNull() {
            addCriterion("total_count is not null");
            return (Criteria) this;
        }

        public Criteria andTotalCountEqualTo(Integer value) {
            addCriterion("total_count =", value, "totalCount");
            return (Criteria) this;
        }

        public Criteria andTotalCountNotEqualTo(Integer value) {
            addCriterion("total_count <>", value, "totalCount");
            return (Criteria) this;
        }

        public Criteria andTotalCountGreaterThan(Integer value) {
            addCriterion("total_count >", value, "totalCount");
            return (Criteria) this;
        }

        public Criteria andTotalCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("total_count >=", value, "totalCount");
            return (Criteria) this;
        }

        public Criteria andTotalCountLessThan(Integer value) {
            addCriterion("total_count <", value, "totalCount");
            return (Criteria) this;
        }

        public Criteria andTotalCountLessThanOrEqualTo(Integer value) {
            addCriterion("total_count <=", value, "totalCount");
            return (Criteria) this;
        }

        public Criteria andTotalCountIn(List<Integer> values) {
            addCriterion("total_count in", values, "totalCount");
            return (Criteria) this;
        }

        public Criteria andTotalCountNotIn(List<Integer> values) {
            addCriterion("total_count not in", values, "totalCount");
            return (Criteria) this;
        }

        public Criteria andTotalCountBetween(Integer value1, Integer value2) {
            addCriterion("total_count between", value1, value2, "totalCount");
            return (Criteria) this;
        }

        public Criteria andTotalCountNotBetween(Integer value1, Integer value2) {
            addCriterion("total_count not between", value1, value2, "totalCount");
            return (Criteria) this;
        }

        public Criteria andTotalAmtIsNull() {
            addCriterion("total_amt is null");
            return (Criteria) this;
        }

        public Criteria andTotalAmtIsNotNull() {
            addCriterion("total_amt is not null");
            return (Criteria) this;
        }

        public Criteria andTotalAmtEqualTo(BigDecimal value) {
            addCriterion("total_amt =", value, "totalAmt");
            return (Criteria) this;
        }

        public Criteria andTotalAmtNotEqualTo(BigDecimal value) {
            addCriterion("total_amt <>", value, "totalAmt");
            return (Criteria) this;
        }

        public Criteria andTotalAmtGreaterThan(BigDecimal value) {
            addCriterion("total_amt >", value, "totalAmt");
            return (Criteria) this;
        }

        public Criteria andTotalAmtGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("total_amt >=", value, "totalAmt");
            return (Criteria) this;
        }

        public Criteria andTotalAmtLessThan(BigDecimal value) {
            addCriterion("total_amt <", value, "totalAmt");
            return (Criteria) this;
        }

        public Criteria andTotalAmtLessThanOrEqualTo(BigDecimal value) {
            addCriterion("total_amt <=", value, "totalAmt");
            return (Criteria) this;
        }

        public Criteria andTotalAmtIn(List<BigDecimal> values) {
            addCriterion("total_amt in", values, "totalAmt");
            return (Criteria) this;
        }

        public Criteria andTotalAmtNotIn(List<BigDecimal> values) {
            addCriterion("total_amt not in", values, "totalAmt");
            return (Criteria) this;
        }

        public Criteria andTotalAmtBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("total_amt between", value1, value2, "totalAmt");
            return (Criteria) this;
        }

        public Criteria andTotalAmtNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("total_amt not between", value1, value2, "totalAmt");
            return (Criteria) this;
        }

        public Criteria andBalEqualCountIsNull() {
            addCriterion("bal_equal_count is null");
            return (Criteria) this;
        }

        public Criteria andBalEqualCountIsNotNull() {
            addCriterion("bal_equal_count is not null");
            return (Criteria) this;
        }

        public Criteria andBalEqualCountEqualTo(Integer value) {
            addCriterion("bal_equal_count =", value, "balEqualCount");
            return (Criteria) this;
        }

        public Criteria andBalEqualCountNotEqualTo(Integer value) {
            addCriterion("bal_equal_count <>", value, "balEqualCount");
            return (Criteria) this;
        }

        public Criteria andBalEqualCountGreaterThan(Integer value) {
            addCriterion("bal_equal_count >", value, "balEqualCount");
            return (Criteria) this;
        }

        public Criteria andBalEqualCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("bal_equal_count >=", value, "balEqualCount");
            return (Criteria) this;
        }

        public Criteria andBalEqualCountLessThan(Integer value) {
            addCriterion("bal_equal_count <", value, "balEqualCount");
            return (Criteria) this;
        }

        public Criteria andBalEqualCountLessThanOrEqualTo(Integer value) {
            addCriterion("bal_equal_count <=", value, "balEqualCount");
            return (Criteria) this;
        }

        public Criteria andBalEqualCountIn(List<Integer> values) {
            addCriterion("bal_equal_count in", values, "balEqualCount");
            return (Criteria) this;
        }

        public Criteria andBalEqualCountNotIn(List<Integer> values) {
            addCriterion("bal_equal_count not in", values, "balEqualCount");
            return (Criteria) this;
        }

        public Criteria andBalEqualCountBetween(Integer value1, Integer value2) {
            addCriterion("bal_equal_count between", value1, value2, "balEqualCount");
            return (Criteria) this;
        }

        public Criteria andBalEqualCountNotBetween(Integer value1, Integer value2) {
            addCriterion("bal_equal_count not between", value1, value2, "balEqualCount");
            return (Criteria) this;
        }

        public Criteria andBalEqualAmtIsNull() {
            addCriterion("bal_equal_amt is null");
            return (Criteria) this;
        }

        public Criteria andBalEqualAmtIsNotNull() {
            addCriterion("bal_equal_amt is not null");
            return (Criteria) this;
        }

        public Criteria andBalEqualAmtEqualTo(BigDecimal value) {
            addCriterion("bal_equal_amt =", value, "balEqualAmt");
            return (Criteria) this;
        }

        public Criteria andBalEqualAmtNotEqualTo(BigDecimal value) {
            addCriterion("bal_equal_amt <>", value, "balEqualAmt");
            return (Criteria) this;
        }

        public Criteria andBalEqualAmtGreaterThan(BigDecimal value) {
            addCriterion("bal_equal_amt >", value, "balEqualAmt");
            return (Criteria) this;
        }

        public Criteria andBalEqualAmtGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("bal_equal_amt >=", value, "balEqualAmt");
            return (Criteria) this;
        }

        public Criteria andBalEqualAmtLessThan(BigDecimal value) {
            addCriterion("bal_equal_amt <", value, "balEqualAmt");
            return (Criteria) this;
        }

        public Criteria andBalEqualAmtLessThanOrEqualTo(BigDecimal value) {
            addCriterion("bal_equal_amt <=", value, "balEqualAmt");
            return (Criteria) this;
        }

        public Criteria andBalEqualAmtIn(List<BigDecimal> values) {
            addCriterion("bal_equal_amt in", values, "balEqualAmt");
            return (Criteria) this;
        }

        public Criteria andBalEqualAmtNotIn(List<BigDecimal> values) {
            addCriterion("bal_equal_amt not in", values, "balEqualAmt");
            return (Criteria) this;
        }

        public Criteria andBalEqualAmtBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("bal_equal_amt between", value1, value2, "balEqualAmt");
            return (Criteria) this;
        }

        public Criteria andBalEqualAmtNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("bal_equal_amt not between", value1, value2, "balEqualAmt");
            return (Criteria) this;
        }

        public Criteria andBalDoubtCountIsNull() {
            addCriterion("bal_doubt_count is null");
            return (Criteria) this;
        }

        public Criteria andBalDoubtCountIsNotNull() {
            addCriterion("bal_doubt_count is not null");
            return (Criteria) this;
        }

        public Criteria andBalDoubtCountEqualTo(Integer value) {
            addCriterion("bal_doubt_count =", value, "balDoubtCount");
            return (Criteria) this;
        }

        public Criteria andBalDoubtCountNotEqualTo(Integer value) {
            addCriterion("bal_doubt_count <>", value, "balDoubtCount");
            return (Criteria) this;
        }

        public Criteria andBalDoubtCountGreaterThan(Integer value) {
            addCriterion("bal_doubt_count >", value, "balDoubtCount");
            return (Criteria) this;
        }

        public Criteria andBalDoubtCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("bal_doubt_count >=", value, "balDoubtCount");
            return (Criteria) this;
        }

        public Criteria andBalDoubtCountLessThan(Integer value) {
            addCriterion("bal_doubt_count <", value, "balDoubtCount");
            return (Criteria) this;
        }

        public Criteria andBalDoubtCountLessThanOrEqualTo(Integer value) {
            addCriterion("bal_doubt_count <=", value, "balDoubtCount");
            return (Criteria) this;
        }

        public Criteria andBalDoubtCountIn(List<Integer> values) {
            addCriterion("bal_doubt_count in", values, "balDoubtCount");
            return (Criteria) this;
        }

        public Criteria andBalDoubtCountNotIn(List<Integer> values) {
            addCriterion("bal_doubt_count not in", values, "balDoubtCount");
            return (Criteria) this;
        }

        public Criteria andBalDoubtCountBetween(Integer value1, Integer value2) {
            addCriterion("bal_doubt_count between", value1, value2, "balDoubtCount");
            return (Criteria) this;
        }

        public Criteria andBalDoubtCountNotBetween(Integer value1, Integer value2) {
            addCriterion("bal_doubt_count not between", value1, value2, "balDoubtCount");
            return (Criteria) this;
        }

        public Criteria andBalDoubtAmtIsNull() {
            addCriterion("bal_doubt_amt is null");
            return (Criteria) this;
        }

        public Criteria andBalDoubtAmtIsNotNull() {
            addCriterion("bal_doubt_amt is not null");
            return (Criteria) this;
        }

        public Criteria andBalDoubtAmtEqualTo(BigDecimal value) {
            addCriterion("bal_doubt_amt =", value, "balDoubtAmt");
            return (Criteria) this;
        }

        public Criteria andBalDoubtAmtNotEqualTo(BigDecimal value) {
            addCriterion("bal_doubt_amt <>", value, "balDoubtAmt");
            return (Criteria) this;
        }

        public Criteria andBalDoubtAmtGreaterThan(BigDecimal value) {
            addCriterion("bal_doubt_amt >", value, "balDoubtAmt");
            return (Criteria) this;
        }

        public Criteria andBalDoubtAmtGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("bal_doubt_amt >=", value, "balDoubtAmt");
            return (Criteria) this;
        }

        public Criteria andBalDoubtAmtLessThan(BigDecimal value) {
            addCriterion("bal_doubt_amt <", value, "balDoubtAmt");
            return (Criteria) this;
        }

        public Criteria andBalDoubtAmtLessThanOrEqualTo(BigDecimal value) {
            addCriterion("bal_doubt_amt <=", value, "balDoubtAmt");
            return (Criteria) this;
        }

        public Criteria andBalDoubtAmtIn(List<BigDecimal> values) {
            addCriterion("bal_doubt_amt in", values, "balDoubtAmt");
            return (Criteria) this;
        }

        public Criteria andBalDoubtAmtNotIn(List<BigDecimal> values) {
            addCriterion("bal_doubt_amt not in", values, "balDoubtAmt");
            return (Criteria) this;
        }

        public Criteria andBalDoubtAmtBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("bal_doubt_amt between", value1, value2, "balDoubtAmt");
            return (Criteria) this;
        }

        public Criteria andBalDoubtAmtNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("bal_doubt_amt not between", value1, value2, "balDoubtAmt");
            return (Criteria) this;
        }

        public Criteria andBalErrorCountIsNull() {
            addCriterion("bal_error_count is null");
            return (Criteria) this;
        }

        public Criteria andBalErrorCountIsNotNull() {
            addCriterion("bal_error_count is not null");
            return (Criteria) this;
        }

        public Criteria andBalErrorCountEqualTo(Integer value) {
            addCriterion("bal_error_count =", value, "balErrorCount");
            return (Criteria) this;
        }

        public Criteria andBalErrorCountNotEqualTo(Integer value) {
            addCriterion("bal_error_count <>", value, "balErrorCount");
            return (Criteria) this;
        }

        public Criteria andBalErrorCountGreaterThan(Integer value) {
            addCriterion("bal_error_count >", value, "balErrorCount");
            return (Criteria) this;
        }

        public Criteria andBalErrorCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("bal_error_count >=", value, "balErrorCount");
            return (Criteria) this;
        }

        public Criteria andBalErrorCountLessThan(Integer value) {
            addCriterion("bal_error_count <", value, "balErrorCount");
            return (Criteria) this;
        }

        public Criteria andBalErrorCountLessThanOrEqualTo(Integer value) {
            addCriterion("bal_error_count <=", value, "balErrorCount");
            return (Criteria) this;
        }

        public Criteria andBalErrorCountIn(List<Integer> values) {
            addCriterion("bal_error_count in", values, "balErrorCount");
            return (Criteria) this;
        }

        public Criteria andBalErrorCountNotIn(List<Integer> values) {
            addCriterion("bal_error_count not in", values, "balErrorCount");
            return (Criteria) this;
        }

        public Criteria andBalErrorCountBetween(Integer value1, Integer value2) {
            addCriterion("bal_error_count between", value1, value2, "balErrorCount");
            return (Criteria) this;
        }

        public Criteria andBalErrorCountNotBetween(Integer value1, Integer value2) {
            addCriterion("bal_error_count not between", value1, value2, "balErrorCount");
            return (Criteria) this;
        }

        public Criteria andBalErrorAmtIsNull() {
            addCriterion("bal_error_amt is null");
            return (Criteria) this;
        }

        public Criteria andBalErrorAmtIsNotNull() {
            addCriterion("bal_error_amt is not null");
            return (Criteria) this;
        }

        public Criteria andBalErrorAmtEqualTo(BigDecimal value) {
            addCriterion("bal_error_amt =", value, "balErrorAmt");
            return (Criteria) this;
        }

        public Criteria andBalErrorAmtNotEqualTo(BigDecimal value) {
            addCriterion("bal_error_amt <>", value, "balErrorAmt");
            return (Criteria) this;
        }

        public Criteria andBalErrorAmtGreaterThan(BigDecimal value) {
            addCriterion("bal_error_amt >", value, "balErrorAmt");
            return (Criteria) this;
        }

        public Criteria andBalErrorAmtGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("bal_error_amt >=", value, "balErrorAmt");
            return (Criteria) this;
        }

        public Criteria andBalErrorAmtLessThan(BigDecimal value) {
            addCriterion("bal_error_amt <", value, "balErrorAmt");
            return (Criteria) this;
        }

        public Criteria andBalErrorAmtLessThanOrEqualTo(BigDecimal value) {
            addCriterion("bal_error_amt <=", value, "balErrorAmt");
            return (Criteria) this;
        }

        public Criteria andBalErrorAmtIn(List<BigDecimal> values) {
            addCriterion("bal_error_amt in", values, "balErrorAmt");
            return (Criteria) this;
        }

        public Criteria andBalErrorAmtNotIn(List<BigDecimal> values) {
            addCriterion("bal_error_amt not in", values, "balErrorAmt");
            return (Criteria) this;
        }

        public Criteria andBalErrorAmtBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("bal_error_amt between", value1, value2, "balErrorAmt");
            return (Criteria) this;
        }

        public Criteria andBalErrorAmtNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("bal_error_amt not between", value1, value2, "balErrorAmt");
            return (Criteria) this;
        }

        public Criteria andSelfDoubtCountIsNull() {
            addCriterion("self_doubt_count is null");
            return (Criteria) this;
        }

        public Criteria andSelfDoubtCountIsNotNull() {
            addCriterion("self_doubt_count is not null");
            return (Criteria) this;
        }

        public Criteria andSelfDoubtCountEqualTo(Integer value) {
            addCriterion("self_doubt_count =", value, "selfDoubtCount");
            return (Criteria) this;
        }

        public Criteria andSelfDoubtCountNotEqualTo(Integer value) {
            addCriterion("self_doubt_count <>", value, "selfDoubtCount");
            return (Criteria) this;
        }

        public Criteria andSelfDoubtCountGreaterThan(Integer value) {
            addCriterion("self_doubt_count >", value, "selfDoubtCount");
            return (Criteria) this;
        }

        public Criteria andSelfDoubtCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("self_doubt_count >=", value, "selfDoubtCount");
            return (Criteria) this;
        }

        public Criteria andSelfDoubtCountLessThan(Integer value) {
            addCriterion("self_doubt_count <", value, "selfDoubtCount");
            return (Criteria) this;
        }

        public Criteria andSelfDoubtCountLessThanOrEqualTo(Integer value) {
            addCriterion("self_doubt_count <=", value, "selfDoubtCount");
            return (Criteria) this;
        }

        public Criteria andSelfDoubtCountIn(List<Integer> values) {
            addCriterion("self_doubt_count in", values, "selfDoubtCount");
            return (Criteria) this;
        }

        public Criteria andSelfDoubtCountNotIn(List<Integer> values) {
            addCriterion("self_doubt_count not in", values, "selfDoubtCount");
            return (Criteria) this;
        }

        public Criteria andSelfDoubtCountBetween(Integer value1, Integer value2) {
            addCriterion("self_doubt_count between", value1, value2, "selfDoubtCount");
            return (Criteria) this;
        }

        public Criteria andSelfDoubtCountNotBetween(Integer value1, Integer value2) {
            addCriterion("self_doubt_count not between", value1, value2, "selfDoubtCount");
            return (Criteria) this;
        }

        public Criteria andSelfDoubtAmtIsNull() {
            addCriterion("self_doubt_amt is null");
            return (Criteria) this;
        }

        public Criteria andSelfDoubtAmtIsNotNull() {
            addCriterion("self_doubt_amt is not null");
            return (Criteria) this;
        }

        public Criteria andSelfDoubtAmtEqualTo(BigDecimal value) {
            addCriterion("self_doubt_amt =", value, "selfDoubtAmt");
            return (Criteria) this;
        }

        public Criteria andSelfDoubtAmtNotEqualTo(BigDecimal value) {
            addCriterion("self_doubt_amt <>", value, "selfDoubtAmt");
            return (Criteria) this;
        }

        public Criteria andSelfDoubtAmtGreaterThan(BigDecimal value) {
            addCriterion("self_doubt_amt >", value, "selfDoubtAmt");
            return (Criteria) this;
        }

        public Criteria andSelfDoubtAmtGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("self_doubt_amt >=", value, "selfDoubtAmt");
            return (Criteria) this;
        }

        public Criteria andSelfDoubtAmtLessThan(BigDecimal value) {
            addCriterion("self_doubt_amt <", value, "selfDoubtAmt");
            return (Criteria) this;
        }

        public Criteria andSelfDoubtAmtLessThanOrEqualTo(BigDecimal value) {
            addCriterion("self_doubt_amt <=", value, "selfDoubtAmt");
            return (Criteria) this;
        }

        public Criteria andSelfDoubtAmtIn(List<BigDecimal> values) {
            addCriterion("self_doubt_amt in", values, "selfDoubtAmt");
            return (Criteria) this;
        }

        public Criteria andSelfDoubtAmtNotIn(List<BigDecimal> values) {
            addCriterion("self_doubt_amt not in", values, "selfDoubtAmt");
            return (Criteria) this;
        }

        public Criteria andSelfDoubtAmtBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("self_doubt_amt between", value1, value2, "selfDoubtAmt");
            return (Criteria) this;
        }

        public Criteria andSelfDoubtAmtNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("self_doubt_amt not between", value1, value2, "selfDoubtAmt");
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