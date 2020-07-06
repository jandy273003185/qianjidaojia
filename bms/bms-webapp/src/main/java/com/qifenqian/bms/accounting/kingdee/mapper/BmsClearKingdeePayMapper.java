package com.qifenqian.bms.accounting.kingdee.mapper;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.accounting.kingdee.bean.BmsClearKingdeePayInfo;

@MapperScan
public interface BmsClearKingdeePayMapper {
    
    /**
     * 
     * @param bmsClearKingdeePayInfo
     * @return
     */
    List<BmsClearKingdeePayInfo> selectList(BmsClearKingdeePayInfo bmsClearKingdeePayInfo);
}