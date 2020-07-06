package com.qifenqian.bms.accounting.adjust.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.accounting.adjust.bean.AccountingAdjustHistoryListBean;
import com.qifenqian.bms.accounting.adjust.bean.AccountingAdjustMain;
import com.qifenqian.bms.accounting.adjust.bean.AccountingAdjustMainExample;
import com.qifenqian.bms.accounting.adjust.bean.AccountingAdjustSingleHistoryListBean;



@MapperScan
public interface AccountingAdjustMainMapper {
    int countByExample(AccountingAdjustMainExample example);

    int deleteByExample(AccountingAdjustMainExample example);

    int deleteByPrimaryKey(String opId);

    int insert(AccountingAdjustMain record);

    int insertSelective(AccountingAdjustMain record);

    List<AccountingAdjustMain> selectByExample(AccountingAdjustMainExample example);

    AccountingAdjustMain selectByPrimaryKey(String opId);
    
    AccountingAdjustMain selectByPrimaryKey4Lock(String opId);

    int updateByExampleSelective(@Param("record") AccountingAdjustMain record, @Param("example") AccountingAdjustMainExample example);

    int updateByExample(@Param("record") AccountingAdjustMain record, @Param("example") AccountingAdjustMainExample example);

    int updateByPrimaryKeySelective(AccountingAdjustMain record);

    int updateByPrimaryKey(AccountingAdjustMain record);
    
    List<AccountingAdjustHistoryListBean> selectHistoryList(AccountingAdjustHistoryListBean listBean);
    
    List<AccountingAdjustSingleHistoryListBean> selectSingleHistoryList(AccountingAdjustSingleHistoryListBean listBean);
}
