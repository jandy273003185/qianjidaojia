package com.qifenqian.bms.accounting.reconciliationSummary.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.accounting.reconciliationSummary.bean.ReconDetail;
import com.qifenqian.bms.accounting.reconciliationSummary.bean.ReconDiff;
import com.qifenqian.bms.accounting.reconciliationSummary.bean.Summary;
import com.qifenqian.bms.accounting.reconciliationSummary.mapper.ReconciliationSummaryMapper;
import com.qifenqian.bms.platform.web.page.Page;

@Repository
public class ReconciliationSummaryDao {
	
	@Autowired
	private ReconciliationSummaryMapper reconciliationSummaryMapper;
	
	
	//对账汇总
	@Page
	public List<Summary> selectSummary(ReconDetail reconDetail){
		return reconciliationSummaryMapper.selectSummary(reconDetail);
	}
	
	//交易明细
	@Page
	public List<ReconDetail> selectByReconResult(ReconDetail reconDetail){
		return reconciliationSummaryMapper.selectByReconResult(reconDetail);
	}
	
	//差错列表
	@Page
	public List<ReconDiff> selectByErrorDisposal(ReconDiff reconDiff){
		return reconciliationSummaryMapper.selectByErrorDisposal(reconDiff);
	} 
}
