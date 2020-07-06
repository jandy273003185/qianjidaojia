package com.qifenqian.bms.accounting.exception.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.accounting.exception.base.bean.Operation;
import com.qifenqian.bms.accounting.exception.base.bean.OperationExcel;
import com.qifenqian.bms.accounting.exception.base.type.DealType;
import com.qifenqian.bms.accounting.exception.dao.OperationExceptionDao;
import com.qifenqian.bms.accounting.exception.dao.operdeal.bean.OperationException;
import com.qifenqian.bms.accounting.exception.dao.operdeal.mapper.OperationExceptionMapper;
import com.qifenqian.bms.accounting.exception.dao.operdealflow.bean.OperationExceptionFlow;
import com.qifenqian.bms.accounting.exception.dao.operdealflow.mapper.OperationExceptionFlowMapper;
import com.qifenqian.bms.basemanager.utils.GenSN;
import com.qifenqian.bms.platform.web.admin.utils.WebUtils;
import com.sevenpay.invoke.common.type.RequestColumnValues;

/**
 * @project sevenpay-bms-web
 * @fileName OperationExceptionService.java
 * @author huiquan.ma
 * @date 2015年10月27日
 * @memo
 */
@Service
public class OperationExceptionService {

	private static Logger logger = LoggerFactory.getLogger(OperationExceptionService.class);

	@Autowired
	private OperationExceptionDao operationExceptionDao;

	@Autowired
	private OperationExceptionMapper operationExceptionMapper;

	@Autowired
	private OperationExceptionFlowMapper operationExceptionFlowMapper;

	/**
	 * 查询各场景异常列表
	 * 
	 * @param requestBean
	 * @return
	 */
	public List<Operation> selectOperationExceptionListByPage(Operation requestBean) {
		logger.info("账务异常列表查询对象:{}", JSONObject.toJSONString(requestBean, true));
		if (requestBean.getStatus() == null && StringUtils.isBlank(requestBean.getOperId())
				&& requestBean.getOperType() == null && StringUtils.isBlank(requestBean.getPayCustId())
				&& StringUtils.isBlank(requestBean.getRcvCustId()) && StringUtils.isBlank(requestBean.getPayCustName())
				&& StringUtils.isBlank(requestBean.getOperDateMin())
				&& StringUtils.isBlank(requestBean.getOperDateMax())) {
			requestBean.setStatusOther("SUCCESS_OR_CANCEL");
		} else {
			requestBean.setStatusOther(null);
		}
		return operationExceptionDao.selectOperationExceptionListByPage(requestBean);
	}

	/**
	 * 记录业务异常处理信息
	 * 
	 * @param operationException
	 */
	public void insertOperationException(OperationException operationException) {
		if (null == operationException) {
			throw new UnsupportedOperationException("业务异常处理对象为空");
		}
		try {
			String operator = String.valueOf(WebUtils.getUserInfo().getUserId());
			String currentDate = DateFormatUtils.format(new Date(), "yyyy-MM-dd");
			String id = GenSN.getSN();
			operationException.setId(id);
			operationException.setInstUser(operator);
			operationException.setDealUser(operator);
			operationException.setDealDate(currentDate);
			if (!StringUtils.isBlank(operationException.getDealMemo())
					&& operationException.getDealMemo().length() > 500) {
				operationException.setDealMemo(operationException.getDealMemo().substring(0, 500));
			}
			String operationCount = operationExceptionMapper.selectOperationExceptionByOperationId(operationException
					.getOperationId());

			if (operationCount.equals("0")) {
				operationExceptionMapper.insertOperationException(operationException);

			} else {
				operationExceptionMapper.updateOperationException(operationException);
			}
			OperationExceptionFlow operationFlow = new OperationExceptionFlow();
			operationFlow.setDealResult(operationException.getDealResult());
			operationFlow.setDealType(DealType.OPERATION.name());
			operationFlow.setTransStep(operationException.getOperationType());
			operationFlow.setExecuteStatus(RequestColumnValues.RtnResult.SUCCESS.name());
			operationFlow.setOperationId(operationException.getOperationId());
			operationFlow.setDealMemo(operationException.getDealMemo());
			insertOperationExceptionFlow(operationFlow);

		} catch (Exception e) {
			logger.error("记录业务异常处理信息异常 " + e);
			throw new UnsupportedOperationException("记录业务异常处理信息异常 " + e);
		}

	}

	/**
	 * 记录业务异常处理流水信息
	 * 
	 * @param operation
	 */
	public void insertOperationExceptionFlow(OperationExceptionFlow operationFlow) {
		if (null == operationFlow) {
			throw new UnsupportedOperationException("业务异常处理流水对象为空");
		}
		try {

			int operationCount = 0;
			operationCount = operationExceptionFlowMapper.selectExceptionFlowByOperationId(operationFlow
					.getOperationId());

			if (operationCount > 0) {
				operationCount = operationCount + 1;
			} else {
				operationCount = 1;
			}
			String operator = String.valueOf(WebUtils.getUserInfo().getUserId());
			String currentDate = DateFormatUtils.format(new Date(), "yyyy-MM-dd");
			String id = GenSN.getSN();
			operationFlow.setDealId(id);
			operationFlow.setDealSeq(String.valueOf(operationCount));
			operationFlow.setDealUser(operator);
			operationFlow.setDealDate(currentDate);
			if (!StringUtils.isBlank(operationFlow.getExecuteMemo()) && operationFlow.getExecuteMemo().length() > 500) {
				operationFlow.setExecuteMemo(operationFlow.getExecuteMemo().substring(0, 500));
			}
			operationExceptionFlowMapper.insertExceptionFlow(operationFlow);

		} catch (Exception e) {
			logger.error("记录业务异常处理流水信息异常 " + e);
			throw new UnsupportedOperationException("记录业务异常处理流水信息异常 " + e);
		}
	}

	/**
	 * 查询操作记录
	 * 
	 * @param queryBean
	 * @return
	 */
	public List<OperationExceptionFlow> queryOperationRecord(Operation queryBean) {
		return operationExceptionDao.queryOperationRecord(queryBean.getOperId());
	}

	/***
	 * 导出异常列表
	 * 
	 * @param requestBean
	 * @return
	 */
	public List<OperationExcel> exportOperationListExcel(Operation requestBean) {
		logger.info("导出账务异常列表查询对象:{}", JSONObject.toJSONString(requestBean, true));
		if (requestBean.getStatus() == null && StringUtils.isBlank(requestBean.getOperId())
				&& requestBean.getOperType() == null && StringUtils.isBlank(requestBean.getPayCustId())
				&& StringUtils.isBlank(requestBean.getRcvCustId()) && StringUtils.isBlank(requestBean.getPayCustName())
				&& StringUtils.isBlank(requestBean.getOperDateMin())
				&& StringUtils.isBlank(requestBean.getOperDateMax())) {
			requestBean.setStatusOther("SUCCESS_OR_CANCEL");
		} else {
			requestBean.setStatusOther(null);
		}
		return operationExceptionMapper.exportOperationListExcel(requestBean);
	}
}
