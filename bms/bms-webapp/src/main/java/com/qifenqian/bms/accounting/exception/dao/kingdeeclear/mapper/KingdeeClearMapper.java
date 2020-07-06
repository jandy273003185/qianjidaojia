package com.qifenqian.bms.accounting.exception.dao.kingdeeclear.mapper;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.accounting.exception.dao.kingdeeclear.bean.KingdeeClear;
import com.qifenqian.bms.accounting.exception.dao.kingdeeclear.bean.KingdeePayEntry;
/**
 * 
 * @author shen
 *
 */
@MapperScan
public interface KingdeeClearMapper {
	
	/***
	 * 根据订单号查询信息
	 * @param operId
	 * @return
	 */
	List<KingdeeClear> selectKingdeeClearByOperaId(String operId);
	
	/***
	 * 查询金蝶详细列表
	 * @param clearId
	 * @return
	 */
	List<KingdeePayEntry> queryKingdeeEntryList(String clearId);
	
	/**
	 * 根据清算编号查询信息
	 * @param clearId
	 * @return
	 */
	KingdeeClear selectKingdeeClearByClearId(String clearId);
	
	/***
	 * 提现金蝶重新执行
	 * @param clearId
	 * @return
	 */
	int updateWithdrawByReExcute(String clearId);
	
	/***
	 * 商户结算金蝶重新执行
	 * @param clearId
	 * @return
	 */
	int updateSettleByReExcute(String clearId);
	
	/***
	 * 修改清算信息
	 * @param kingdeeClear
	 * @return
	 */
	int updateKingdeeClear(KingdeeClear kingdeeClear);
	
}
