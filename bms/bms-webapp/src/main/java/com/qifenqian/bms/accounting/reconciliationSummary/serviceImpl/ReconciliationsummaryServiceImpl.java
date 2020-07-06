package com.qifenqian.bms.accounting.reconciliationSummary.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qifenqian.bms.accounting.reconciliationSummary.bean.ReconDetail;
import com.qifenqian.bms.accounting.reconciliationSummary.bean.ReconDiff;
import com.qifenqian.bms.accounting.reconciliationSummary.bean.Summary;
import com.qifenqian.bms.accounting.reconciliationSummary.dao.ReconciliationSummaryDao;
import com.qifenqian.bms.accounting.reconciliationSummary.mapper.ReconciliationSummaryMapper;
import com.qifenqian.bms.accounting.reconciliationSummary.service.ReconciliationsummaryService;

@Service
public class ReconciliationsummaryServiceImpl implements ReconciliationsummaryService{

	@Autowired
	private ReconciliationSummaryDao reconciliationSummaryDao;
	
	@Autowired
	private ReconciliationSummaryMapper reconciliationSummaryMapper;
	
	//对账汇总列表
	@Override
	public List<Summary> selectSummary(ReconDetail reconDetail) {
			// TODO Auto-generated method stub
		return reconciliationSummaryDao.selectSummary(reconDetail);
	}

	
	//交易明细列表
	@Override
	public List<ReconDetail> selectByReconResult(ReconDetail reconDetail) {
		return reconciliationSummaryDao.selectByReconResult(reconDetail);
	}

	//差错列表
	@Override
	public List<ReconDiff> selectByErrorDisposal(ReconDiff reconDiff){
		return reconciliationSummaryDao.selectByErrorDisposal(reconDiff);
	}
}
