package com.qifenqian.bms.meeting.redPacket.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MeetRedPacketBillExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MeetRedPacketBillExample() {
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

        public Criteria andRedPacketSnIsNull() {
            addCriterion("RED_PACKET_SN is null");
            return (Criteria) this;
        }

        public Criteria andRedPacketSnIsNotNull() {
            addCriterion("RED_PACKET_SN is not null");
            return (Criteria) this;
        }

        public Criteria andRedPacketSnEqualTo(String value) {
            addCriterion("RED_PACKET_SN =", value, "redPacketSn");
            return (Criteria) this;
        }

        public Criteria andRedPacketSnNotEqualTo(String value) {
            addCriterion("RED_PACKET_SN <>", value, "redPacketSn");
            return (Criteria) this;
        }

        public Criteria andRedPacketSnGreaterThan(String value) {
            addCriterion("RED_PACKET_SN >", value, "redPacketSn");
            return (Criteria) this;
        }

        public Criteria andRedPacketSnGreaterThanOrEqualTo(String value) {
            addCriterion("RED_PACKET_SN >=", value, "redPacketSn");
            return (Criteria) this;
        }

        public Criteria andRedPacketSnLessThan(String value) {
            addCriterion("RED_PACKET_SN <", value, "redPacketSn");
            return (Criteria) this;
        }

        public Criteria andRedPacketSnLessThanOrEqualTo(String value) {
            addCriterion("RED_PACKET_SN <=", value, "redPacketSn");
            return (Criteria) this;
        }

        public Criteria andRedPacketSnLike(String value) {
            addCriterion("RED_PACKET_SN like", value, "redPacketSn");
            return (Criteria) this;
        }

        public Criteria andRedPacketSnNotLike(String value) {
            addCriterion("RED_PACKET_SN not like", value, "redPacketSn");
            return (Criteria) this;
        }

        public Criteria andRedPacketSnIn(List<String> values) {
            addCriterion("RED_PACKET_SN in", values, "redPacketSn");
            return (Criteria) this;
        }

        public Criteria andRedPacketSnNotIn(List<String> values) {
            addCriterion("RED_PACKET_SN not in", values, "redPacketSn");
            return (Criteria) this;
        }

        public Criteria andRedPacketSnBetween(String value1, String value2) {
            addCriterion("RED_PACKET_SN between", value1, value2, "redPacketSn");
            return (Criteria) this;
        }

        public Criteria andRedPacketSnNotBetween(String value1, String value2) {
            addCriterion("RED_PACKET_SN not between", value1, value2, "redPacketSn");
            return (Criteria) this;
        }

        public Criteria andReceiveCustIdIsNull() {
            addCriterion("RECEIVE_CUST_ID is null");
            return (Criteria) this;
        }

        public Criteria andReceiveCustIdIsNotNull() {
            addCriterion("RECEIVE_CUST_ID is not null");
            return (Criteria) this;
        }

        public Criteria andReceiveCustIdEqualTo(String value) {
            addCriterion("RECEIVE_CUST_ID =", value, "receiveCustId");
            return (Criteria) this;
        }

        public Criteria andReceiveCustIdNotEqualTo(String value) {
            addCriterion("RECEIVE_CUST_ID <>", value, "receiveCustId");
            return (Criteria) this;
        }

        public Criteria andReceiveCustIdGreaterThan(String value) {
            addCriterion("RECEIVE_CUST_ID >", value, "receiveCustId");
            return (Criteria) this;
        }

        public Criteria andReceiveCustIdGreaterThanOrEqualTo(String value) {
            addCriterion("RECEIVE_CUST_ID >=", value, "receiveCustId");
            return (Criteria) this;
        }

        public Criteria andReceiveCustIdLessThan(String value) {
            addCriterion("RECEIVE_CUST_ID <", value, "receiveCustId");
            return (Criteria) this;
        }

        public Criteria andReceiveCustIdLessThanOrEqualTo(String value) {
            addCriterion("RECEIVE_CUST_ID <=", value, "receiveCustId");
            return (Criteria) this;
        }

        public Criteria andReceiveCustIdLike(String value) {
            addCriterion("RECEIVE_CUST_ID like", value, "receiveCustId");
            return (Criteria) this;
        }

        public Criteria andReceiveCustIdNotLike(String value) {
            addCriterion("RECEIVE_CUST_ID not like", value, "receiveCustId");
            return (Criteria) this;
        }

        public Criteria andReceiveCustIdIn(List<String> values) {
            addCriterion("RECEIVE_CUST_ID in", values, "receiveCustId");
            return (Criteria) this;
        }

        public Criteria andReceiveCustIdNotIn(List<String> values) {
            addCriterion("RECEIVE_CUST_ID not in", values, "receiveCustId");
            return (Criteria) this;
        }

        public Criteria andReceiveCustIdBetween(String value1, String value2) {
            addCriterion("RECEIVE_CUST_ID between", value1, value2, "receiveCustId");
            return (Criteria) this;
        }

        public Criteria andReceiveCustIdNotBetween(String value1, String value2) {
            addCriterion("RECEIVE_CUST_ID not between", value1, value2, "receiveCustId");
            return (Criteria) this;
        }

        public Criteria andGiveCustIdIsNull() {
            addCriterion("GIVE_CUST_ID is null");
            return (Criteria) this;
        }

        public Criteria andGiveCustIdIsNotNull() {
            addCriterion("GIVE_CUST_ID is not null");
            return (Criteria) this;
        }

        public Criteria andGiveCustIdEqualTo(String value) {
            addCriterion("GIVE_CUST_ID =", value, "giveCustId");
            return (Criteria) this;
        }

        public Criteria andGiveCustIdNotEqualTo(String value) {
            addCriterion("GIVE_CUST_ID <>", value, "giveCustId");
            return (Criteria) this;
        }

        public Criteria andGiveCustIdGreaterThan(String value) {
            addCriterion("GIVE_CUST_ID >", value, "giveCustId");
            return (Criteria) this;
        }

        public Criteria andGiveCustIdGreaterThanOrEqualTo(String value) {
            addCriterion("GIVE_CUST_ID >=", value, "giveCustId");
            return (Criteria) this;
        }

        public Criteria andGiveCustIdLessThan(String value) {
            addCriterion("GIVE_CUST_ID <", value, "giveCustId");
            return (Criteria) this;
        }

        public Criteria andGiveCustIdLessThanOrEqualTo(String value) {
            addCriterion("GIVE_CUST_ID <=", value, "giveCustId");
            return (Criteria) this;
        }

        public Criteria andGiveCustIdLike(String value) {
            addCriterion("GIVE_CUST_ID like", value, "giveCustId");
            return (Criteria) this;
        }

        public Criteria andGiveCustIdNotLike(String value) {
            addCriterion("GIVE_CUST_ID not like", value, "giveCustId");
            return (Criteria) this;
        }

        public Criteria andGiveCustIdIn(List<String> values) {
            addCriterion("GIVE_CUST_ID in", values, "giveCustId");
            return (Criteria) this;
        }

        public Criteria andGiveCustIdNotIn(List<String> values) {
            addCriterion("GIVE_CUST_ID not in", values, "giveCustId");
            return (Criteria) this;
        }

        public Criteria andGiveCustIdBetween(String value1, String value2) {
            addCriterion("GIVE_CUST_ID between", value1, value2, "giveCustId");
            return (Criteria) this;
        }

        public Criteria andGiveCustIdNotBetween(String value1, String value2) {
            addCriterion("GIVE_CUST_ID not between", value1, value2, "giveCustId");
            return (Criteria) this;
        }

        public Criteria andRedPacketTypeIsNull() {
            addCriterion("RED_PACKET_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andRedPacketTypeIsNotNull() {
            addCriterion("RED_PACKET_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andRedPacketTypeEqualTo(String value) {
            addCriterion("RED_PACKET_TYPE =", value, "redPacketType");
            return (Criteria) this;
        }

        public Criteria andRedPacketTypeNotEqualTo(String value) {
            addCriterion("RED_PACKET_TYPE <>", value, "redPacketType");
            return (Criteria) this;
        }

        public Criteria andRedPacketTypeGreaterThan(String value) {
            addCriterion("RED_PACKET_TYPE >", value, "redPacketType");
            return (Criteria) this;
        }

        public Criteria andRedPacketTypeGreaterThanOrEqualTo(String value) {
            addCriterion("RED_PACKET_TYPE >=", value, "redPacketType");
            return (Criteria) this;
        }

        public Criteria andRedPacketTypeLessThan(String value) {
            addCriterion("RED_PACKET_TYPE <", value, "redPacketType");
            return (Criteria) this;
        }

        public Criteria andRedPacketTypeLessThanOrEqualTo(String value) {
            addCriterion("RED_PACKET_TYPE <=", value, "redPacketType");
            return (Criteria) this;
        }

        public Criteria andRedPacketTypeLike(String value) {
            addCriterion("RED_PACKET_TYPE like", value, "redPacketType");
            return (Criteria) this;
        }

        public Criteria andRedPacketTypeNotLike(String value) {
            addCriterion("RED_PACKET_TYPE not like", value, "redPacketType");
            return (Criteria) this;
        }

        public Criteria andRedPacketTypeIn(List<String> values) {
            addCriterion("RED_PACKET_TYPE in", values, "redPacketType");
            return (Criteria) this;
        }

        public Criteria andRedPacketTypeNotIn(List<String> values) {
            addCriterion("RED_PACKET_TYPE not in", values, "redPacketType");
            return (Criteria) this;
        }

        public Criteria andRedPacketTypeBetween(String value1, String value2) {
            addCriterion("RED_PACKET_TYPE between", value1, value2, "redPacketType");
            return (Criteria) this;
        }

        public Criteria andRedPacketTypeNotBetween(String value1, String value2) {
            addCriterion("RED_PACKET_TYPE not between", value1, value2, "redPacketType");
            return (Criteria) this;
        }

        public Criteria andRedPacketNameIsNull() {
            addCriterion("RED_PACKET_NAME is null");
            return (Criteria) this;
        }

        public Criteria andRedPacketNameIsNotNull() {
            addCriterion("RED_PACKET_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andRedPacketNameEqualTo(String value) {
            addCriterion("RED_PACKET_NAME =", value, "redPacketName");
            return (Criteria) this;
        }

        public Criteria andRedPacketNameNotEqualTo(String value) {
            addCriterion("RED_PACKET_NAME <>", value, "redPacketName");
            return (Criteria) this;
        }

        public Criteria andRedPacketNameGreaterThan(String value) {
            addCriterion("RED_PACKET_NAME >", value, "redPacketName");
            return (Criteria) this;
        }

        public Criteria andRedPacketNameGreaterThanOrEqualTo(String value) {
            addCriterion("RED_PACKET_NAME >=", value, "redPacketName");
            return (Criteria) this;
        }

        public Criteria andRedPacketNameLessThan(String value) {
            addCriterion("RED_PACKET_NAME <", value, "redPacketName");
            return (Criteria) this;
        }

        public Criteria andRedPacketNameLessThanOrEqualTo(String value) {
            addCriterion("RED_PACKET_NAME <=", value, "redPacketName");
            return (Criteria) this;
        }

        public Criteria andRedPacketNameLike(String value) {
            addCriterion("RED_PACKET_NAME like", value, "redPacketName");
            return (Criteria) this;
        }

        public Criteria andRedPacketNameNotLike(String value) {
            addCriterion("RED_PACKET_NAME not like", value, "redPacketName");
            return (Criteria) this;
        }

        public Criteria andRedPacketNameIn(List<String> values) {
            addCriterion("RED_PACKET_NAME in", values, "redPacketName");
            return (Criteria) this;
        }

        public Criteria andRedPacketNameNotIn(List<String> values) {
            addCriterion("RED_PACKET_NAME not in", values, "redPacketName");
            return (Criteria) this;
        }

        public Criteria andRedPacketNameBetween(String value1, String value2) {
            addCriterion("RED_PACKET_NAME between", value1, value2, "redPacketName");
            return (Criteria) this;
        }

        public Criteria andRedPacketNameNotBetween(String value1, String value2) {
            addCriterion("RED_PACKET_NAME not between", value1, value2, "redPacketName");
            return (Criteria) this;
        }

        public Criteria andRedPacketAmtIsNull() {
            addCriterion("RED_PACKET_AMT is null");
            return (Criteria) this;
        }

        public Criteria andRedPacketAmtIsNotNull() {
            addCriterion("RED_PACKET_AMT is not null");
            return (Criteria) this;
        }

        public Criteria andRedPacketAmtEqualTo(BigDecimal value) {
            addCriterion("RED_PACKET_AMT =", value, "redPacketAmt");
            return (Criteria) this;
        }

        public Criteria andRedPacketAmtNotEqualTo(BigDecimal value) {
            addCriterion("RED_PACKET_AMT <>", value, "redPacketAmt");
            return (Criteria) this;
        }

        public Criteria andRedPacketAmtGreaterThan(BigDecimal value) {
            addCriterion("RED_PACKET_AMT >", value, "redPacketAmt");
            return (Criteria) this;
        }

        public Criteria andRedPacketAmtGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("RED_PACKET_AMT >=", value, "redPacketAmt");
            return (Criteria) this;
        }

        public Criteria andRedPacketAmtLessThan(BigDecimal value) {
            addCriterion("RED_PACKET_AMT <", value, "redPacketAmt");
            return (Criteria) this;
        }

        public Criteria andRedPacketAmtLessThanOrEqualTo(BigDecimal value) {
            addCriterion("RED_PACKET_AMT <=", value, "redPacketAmt");
            return (Criteria) this;
        }

        public Criteria andRedPacketAmtIn(List<BigDecimal> values) {
            addCriterion("RED_PACKET_AMT in", values, "redPacketAmt");
            return (Criteria) this;
        }

        public Criteria andRedPacketAmtNotIn(List<BigDecimal> values) {
            addCriterion("RED_PACKET_AMT not in", values, "redPacketAmt");
            return (Criteria) this;
        }

        public Criteria andRedPacketAmtBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("RED_PACKET_AMT between", value1, value2, "redPacketAmt");
            return (Criteria) this;
        }

        public Criteria andRedPacketAmtNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("RED_PACKET_AMT not between", value1, value2, "redPacketAmt");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("STATUS is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("STATUS =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("STATUS <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("STATUS >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("STATUS >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("STATUS <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("STATUS <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("STATUS like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("STATUS not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("STATUS in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("STATUS not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("STATUS between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("STATUS not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andMemoIsNull() {
            addCriterion("MEMO is null");
            return (Criteria) this;
        }

        public Criteria andMemoIsNotNull() {
            addCriterion("MEMO is not null");
            return (Criteria) this;
        }

        public Criteria andMemoEqualTo(String value) {
            addCriterion("MEMO =", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotEqualTo(String value) {
            addCriterion("MEMO <>", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoGreaterThan(String value) {
            addCriterion("MEMO >", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoGreaterThanOrEqualTo(String value) {
            addCriterion("MEMO >=", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoLessThan(String value) {
            addCriterion("MEMO <", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoLessThanOrEqualTo(String value) {
            addCriterion("MEMO <=", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoLike(String value) {
            addCriterion("MEMO like", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotLike(String value) {
            addCriterion("MEMO not like", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoIn(List<String> values) {
            addCriterion("MEMO in", values, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotIn(List<String> values) {
            addCriterion("MEMO not in", values, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoBetween(String value1, String value2) {
            addCriterion("MEMO between", value1, value2, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotBetween(String value1, String value2) {
            addCriterion("MEMO not between", value1, value2, "memo");
            return (Criteria) this;
        }

        public Criteria andRelationIdIsNull() {
            addCriterion("RELATION_ID is null");
            return (Criteria) this;
        }

        public Criteria andRelationIdIsNotNull() {
            addCriterion("RELATION_ID is not null");
            return (Criteria) this;
        }

        public Criteria andRelationIdEqualTo(String value) {
            addCriterion("RELATION_ID =", value, "relationId");
            return (Criteria) this;
        }

        public Criteria andRelationIdNotEqualTo(String value) {
            addCriterion("RELATION_ID <>", value, "relationId");
            return (Criteria) this;
        }

        public Criteria andRelationIdGreaterThan(String value) {
            addCriterion("RELATION_ID >", value, "relationId");
            return (Criteria) this;
        }

        public Criteria andRelationIdGreaterThanOrEqualTo(String value) {
            addCriterion("RELATION_ID >=", value, "relationId");
            return (Criteria) this;
        }

        public Criteria andRelationIdLessThan(String value) {
            addCriterion("RELATION_ID <", value, "relationId");
            return (Criteria) this;
        }

        public Criteria andRelationIdLessThanOrEqualTo(String value) {
            addCriterion("RELATION_ID <=", value, "relationId");
            return (Criteria) this;
        }

        public Criteria andRelationIdLike(String value) {
            addCriterion("RELATION_ID like", value, "relationId");
            return (Criteria) this;
        }

        public Criteria andRelationIdNotLike(String value) {
            addCriterion("RELATION_ID not like", value, "relationId");
            return (Criteria) this;
        }

        public Criteria andRelationIdIn(List<String> values) {
            addCriterion("RELATION_ID in", values, "relationId");
            return (Criteria) this;
        }

        public Criteria andRelationIdNotIn(List<String> values) {
            addCriterion("RELATION_ID not in", values, "relationId");
            return (Criteria) this;
        }

        public Criteria andRelationIdBetween(String value1, String value2) {
            addCriterion("RELATION_ID between", value1, value2, "relationId");
            return (Criteria) this;
        }

        public Criteria andRelationIdNotBetween(String value1, String value2) {
            addCriterion("RELATION_ID not between", value1, value2, "relationId");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("CREATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("CREATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("CREATE_TIME =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("CREATE_TIME <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("CREATE_TIME >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("CREATE_TIME >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("CREATE_TIME <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("CREATE_TIME <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("CREATE_TIME in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("CREATE_TIME not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("CREATE_TIME between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("CREATE_TIME not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIsNull() {
            addCriterion("MODIFY_TIME is null");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIsNotNull() {
            addCriterion("MODIFY_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andModifyTimeEqualTo(Date value) {
            addCriterion("MODIFY_TIME =", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotEqualTo(Date value) {
            addCriterion("MODIFY_TIME <>", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeGreaterThan(Date value) {
            addCriterion("MODIFY_TIME >", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("MODIFY_TIME >=", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeLessThan(Date value) {
            addCriterion("MODIFY_TIME <", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeLessThanOrEqualTo(Date value) {
            addCriterion("MODIFY_TIME <=", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIn(List<Date> values) {
            addCriterion("MODIFY_TIME in", values, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotIn(List<Date> values) {
            addCriterion("MODIFY_TIME not in", values, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeBetween(Date value1, Date value2) {
            addCriterion("MODIFY_TIME between", value1, value2, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotBetween(Date value1, Date value2) {
            addCriterion("MODIFY_TIME not between", value1, value2, "modifyTime");
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