package com.qifenqian.bms.accounting.exception;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.accounting.exception.base.bean.Operation;
import com.qifenqian.bms.accounting.exception.base.bean.OperationExcel;
import com.qifenqian.bms.accounting.exception.base.bean.TransAction;
import com.qifenqian.bms.accounting.exception.dao.kingdeeclear.bean.KingdeePayEntry;
import com.qifenqian.bms.accounting.exception.dao.operdealflow.bean.OperationExceptionFlow;
import com.qifenqian.bms.accounting.exception.service.OperationDealService;
import com.qifenqian.bms.accounting.exception.service.OperationExceptionService;
import com.qifenqian.bms.accounting.exception.service.TransDealService;
import com.qifenqian.bms.accounting.exception.service.TransQueryService;
import com.qifenqian.bms.basemanager.trade.service.DownLoadUtil;
import com.qifenqian.bms.basemanager.trade.service.TradeBillService;
import com.qifenqian.bms.basemanager.utils.DatetimeUtils;

/**
 * 账务异常处理控制层
 * 
 * @project sevenpay-bms-web
 * @fileName OperationExceptionController.java
 * @author huiquan.ma
 * @date 2015年10月19日
 * @memo
 */
@Controller
@RequestMapping(OperationExceptionPath.BASE)
public class OperationExceptionController {

	private static Logger logger = LoggerFactory.getLogger(OperationExceptionController.class);

	@Autowired
	private OperationExceptionService operationExceptionService;

	@Autowired
	private OperationDealService operationDealService;

	@Autowired
	private TransDealService transDealService;

	@Autowired
	private TransQueryService transQueryService;

	@Autowired
	private TradeBillService tradeBillService;

	/**
	 * 查询异常列表
	 * 
	 * @param requestBean
	 * @return
	 */
	@RequestMapping(OperationExceptionPath.LIST_OPERATION)
	public ModelAndView listOperation(Operation requestBean) {
		ModelAndView modelAndView = new ModelAndView(OperationExceptionPath.BASE
				+ OperationExceptionPath.LIST_OPERATION);

		modelAndView.addObject("exceptionList",
				operationExceptionService.selectOperationExceptionListByPage(requestBean));
		modelAndView.addObject("requestBean", requestBean);

		return modelAndView;
	}

	/**
	 * 导出异常列表
	 * 
	 * @param recharge
	 */
	@RequestMapping(OperationExceptionPath.EXPORTEXCEL)
	public void exportExcel(Operation requestBean, HttpServletRequest request, HttpServletResponse response) {
		logger.info("导出异常管理列表");
		try {
			List<OperationExcel> operationListExcel = operationExceptionService.exportOperationListExcel(requestBean);
			String[] headers = { "业务编号", "业务类型", "付方用户编号", "付方用户名称", "金额", "收方用户编号", "收方用户名称", "状态", "订单日期", "订单时间" };
			String fileName = DatetimeUtils.dateSecond() + "_异常管理列表.xls";
			Map<String, String> fileInfo = tradeBillService.exportExcel(operationListExcel, headers, fileName,
					"异常管理列表", request);
			DownLoadUtil.downLoadFile(fileInfo.get("filePath"), response, fileInfo.get("fileName"), "xls");
			logger.info("导出异常管理列表成功");
		} catch (Exception e) {
			logger.error("导出异常管理列表异常", e);
		}

	}

	/**
	 * 进入异常处理明细页面
	 * 
	 * @param requestBean
	 * @return
	 */
	@RequestMapping(OperationExceptionPath.DETAIL_OPERATION)
	public ModelAndView detailOperation(Operation requestBean) {
		return operationDealService.selectOperationDetail(requestBean);
	}

	/**
	 * 结束流程
	 * 
	 * @param operation
	 */

	@RequestMapping(OperationExceptionPath.CLOSURE_OPERATION)
	@ResponseBody
	public String closureOperation(Operation operation) {

		logger.info("异常处理结束流程对象 {}", JSONObject.toJSONString(operation, true));

		if (StringUtils.isBlank(operation.getOperId())) {
			throw new RuntimeException("业务编号不能为空");
		}
		if (null == operation.getOperType()) {
			throw new RuntimeException("业务类型不能为空");
		}

		JSONObject json = new JSONObject();
		try {
			operationDealService.closureOperation(operation);
			json.put("result", "SUCCESS");
		} catch (Exception e) {
			logger.error("流程结束异常:" + e.getMessage());
			json.put("result", "FAIL");
			json.put("message", "流程结束异常:" + e.getMessage());
		}
		return json.toJSONString();
	}

	/**
	 * 实时结果查询
	 * 
	 * @param requestBean
	 */
	@RequestMapping(OperationExceptionPath.QUERY_RESULT_TRANS)
	public ModelAndView queryResultTrans(TransAction requestBean) {
		ModelAndView mv = new ModelAndView(OperationExceptionPath.BASE + OperationExceptionPath.QUERY_RESULT_TRANS);
		List<TransAction> queryResult = transQueryService.queryResultTrans(requestBean);
		mv.addObject("queryResult", queryResult);
		return mv;
	}

	/**
	 * 确认成功
	 * 
	 * @param requestBean
	 */
	@RequestMapping(OperationExceptionPath.CONFIRM_SUCCESS_TRANS)
	@ResponseBody
	public String confirmSuccessTrans(TransAction requestBean) {

		logger.info("异常处理确认成功对象 {}", JSONObject.toJSONString(requestBean, true));

		if (StringUtils.isBlank(requestBean.getOperationId())) {
			throw new RuntimeException("业务编号不能为空");
		}
		if (null == requestBean.getTransFlowOperate()) {
			throw new RuntimeException("操作步骤不能为空");
		}
		if (null == requestBean.getMsgType()) {
			throw new RuntimeException("业务类型不能为空");
		}
		if (StringUtils.isBlank(requestBean.getTransFlowId())) {
			throw new RuntimeException("流水编号不能为空");
		}

		JSONObject json = new JSONObject();
		try {
			transDealService.confirmSuccess(requestBean);
			json.put("result", "SUCCESS");
		} catch (Exception e) {
			logger.error("确认成功异常:" + e.getMessage());
			json.put("result", "FAIL");
			json.put("message", "确认成功异常:" + e.getMessage());
		}
		return json.toJSONString();
	}

	/**
	 * 确认失败
	 * 
	 * @param requestBean
	 */
	@RequestMapping(OperationExceptionPath.CONFIRM_FAILURE_TRANS)
	@ResponseBody
	public String confirmFailureTrans(TransAction requestBean) {

		logger.info("异常处理确认失败对象 {}", JSONObject.toJSONString(requestBean, true));

		if (StringUtils.isBlank(requestBean.getOperationId())) {
			throw new RuntimeException("业务编号不能为空");
		}
		if (null == requestBean.getTransFlowOperate()) {
			throw new RuntimeException("操作步骤不能为空");
		}
		if (null == requestBean.getMsgType()) {
			throw new RuntimeException("业务类型不能为空");
		}
		if (StringUtils.isBlank(requestBean.getTransFlowId())) {
			throw new RuntimeException("流水编号不能为空");
		}
		JSONObject json = new JSONObject();
		try {
			transDealService.confirmFailure(requestBean);
			json.put("result", "SUCCESS");
		} catch (Exception e) {
			logger.error("确认失败异常:" + e.getMessage());
			json.put("result", "FAIL");
			json.put("message", "确认失败异常:" + e.getMessage());
		}
		return json.toJSONString();
	}

	/**
	 * 撤销当前步骤
	 * 
	 * @param requestBean
	 */
	@RequestMapping(OperationExceptionPath.REVOKE_TRANS)
	@ResponseBody
	public String revokeTrans(TransAction requestBean) {

		logger.info("异常处理撤销当前步骤对象 {}", JSONObject.toJSONString(requestBean, true));

		if (StringUtils.isBlank(requestBean.getOperationId())) {
			throw new RuntimeException("业务编号不能为空");
		}
		if (null == requestBean.getTransFlowOperate()) {
			throw new RuntimeException("操作步骤不能为空");
		}
		if (null == requestBean.getMsgType()) {
			throw new RuntimeException("业务类型不能为空");
		}
		if (StringUtils.isBlank(requestBean.getTransFlowId())) {
			throw new RuntimeException("流水编号不能为空");
		}
		if (StringUtils.isBlank(requestBean.getMsgId())) {
			throw new RuntimeException("核心报文编号不能为空");
		}
		JSONObject json = new JSONObject();
		try {
			json = transDealService.revoke(requestBean);
		} catch (Exception e) {
			logger.error("撤销当前步骤异常:" + e.getMessage());
			json.put("result", "FAIL");
			json.put("message", "撤销当前步骤异常:" + e.getMessage());

		}
		return json.toJSONString();
	}

	/***
	 * 重新执行单步
	 */
	@RequestMapping(OperationExceptionPath.REXECUTE_TRANS)
	@ResponseBody
	public String rexecuteTrans(TransAction transAction) {

		logger.info("异常处理重新执行单步对象 {}", JSONObject.toJSONString(transAction, true));
		if (StringUtils.isBlank(transAction.getOperationId())) {
			throw new RuntimeException("业务编号不能为空");
		}
		if (null == transAction.getTransFlowOperate()) {
			throw new RuntimeException("操作步骤不能为空");
		}
		if (null == transAction.getMsgType()) {
			throw new RuntimeException("业务类型不能为空");
		}
		if (StringUtils.isBlank(transAction.getTransFlowId())) {
			throw new RuntimeException("流水编号不能为空");
		}
		if (StringUtils.isBlank(transAction.getMsgId())) {
			throw new RuntimeException("核心报文编号不能为空");
		}
		JSONObject json = new JSONObject();
		try {
			json = transDealService.reExcute(transAction);
		} catch (Exception e) {
			logger.error("重新执行异常:" + e.getMessage());
			json.put("result", "FAIL");
			json.put("message", "重新执行异常:" + e.getMessage());
		}
		return json.toJSONString();
	}

	/**
	 * 回退当前步骤
	 * 
	 * @param operation
	 */
	@RequestMapping(OperationExceptionPath.ROLLBACK_OPERATION)
	@ResponseBody
	public String rollbackOperation(TransAction requestBean) {

		logger.info("异常处理回退流程对象 {}", JSONObject.toJSONString(requestBean, true));
		if (StringUtils.isBlank(requestBean.getOperationId())) {
			throw new RuntimeException("业务编号不能为空");
		}
		if (null == requestBean.getTransFlowOperate()) {
			throw new RuntimeException("操作步骤不能为空");
		}
		if (null == requestBean.getMsgType()) {
			throw new RuntimeException("业务类型不能为空");
		}
		if (StringUtils.isBlank(requestBean.getTransFlowId())) {
			throw new RuntimeException("流水编号不能为空");
		}
		JSONObject json = new JSONObject();
		try {
			json = transDealService.rollback(requestBean);
		} catch (Exception e) {
			logger.error("回退流程异常:" + e.getMessage());
			json.put("result", "FAIL");
			json.put("message", "回退流程异常:" + e.getMessage());
		}
		return json.toJSONString();
	}

	/**
	 * 续作下一步
	 * 
	 * @param transAction
	 */
	@RequestMapping(OperationExceptionPath.SEQUEL_NEXT_STEP_OPERATION)
	@ResponseBody
	public String sequelNextStepOperation(TransAction transAction) {

		logger.info("异常处理续作下一步对象 {}", JSONObject.toJSONString(transAction, true));
		if (StringUtils.isBlank(transAction.getOperationId())) {
			throw new RuntimeException("业务编号不能为空");
		}
		if (null == transAction.getTransFlowOperate()) {
			throw new RuntimeException("操作步骤不能为空");
		}
		if (null == transAction.getMsgType()) {
			throw new RuntimeException("业务类型不能为空");
		}
		if (StringUtils.isBlank(transAction.getTransFlowId())) {
			throw new RuntimeException("流水编号不能为空");
		}
		if (StringUtils.isBlank(transAction.getMsgId())) {
			throw new RuntimeException("核心报文编号不能为空");
		}
		JSONObject json = new JSONObject();
		try {
			json = transDealService.sequelNextStep(transAction);

		} catch (Exception e) {
			logger.error("续作下一步异常:" + e.getMessage());
			json.put("result", "FAIL");
			json.put("message", "续作下一步异常:" + e.getMessage());
		}
		return json.toJSONString();
	}

	/**
	 * 续作整个流程
	 * 
	 * @param operation
	 */
	@RequestMapping(OperationExceptionPath.RESTART_OPERATION)
	@ResponseBody
	public String restartOperation(TransAction requestBean) {

		logger.info("异常处理续作整个流程对象 {}", JSONObject.toJSONString(requestBean, true));
		if (StringUtils.isBlank(requestBean.getOperationId())) {
			throw new RuntimeException("业务编号不能为空");
		}
		if (null == requestBean.getTransFlowOperate()) {
			throw new RuntimeException("操作步骤不能为空");
		}
		if (null == requestBean.getMsgType()) {
			throw new RuntimeException("业务类型不能为空");
		}
		if (StringUtils.isBlank(requestBean.getTransFlowId())) {
			throw new RuntimeException("流水编号不能为空");
		}
		JSONObject json = new JSONObject();
		try {
			json = transDealService.restart(requestBean);
		} catch (Exception e) {
			logger.error("续作整个流程异常:" + e.getMessage());
			json.put("result", "FAIL");
			json.put("message", "续作整个流程异常:" + e.getMessage());
		}
		return json.toJSONString();
	}

	/**
	 * 操作记录
	 * 
	 * @param queryBean
	 * @return
	 */
	@RequestMapping(OperationExceptionPath.QUERY_OPERATION_RECORD)
	public ModelAndView queryOperationRecord(Operation requestBean) {
		ModelAndView mv = new ModelAndView(OperationExceptionPath.BASE + OperationExceptionPath.QUERY_OPERATION_RECORD);
		List<OperationExceptionFlow> operationRecord = operationExceptionService.queryOperationRecord(requestBean);
		mv.addObject("operationRecord", operationRecord);
		return mv;
	}
	
	/**
	 * 金蝶交易列表
	 * 
	 * @param requestBean
	 */
	@RequestMapping(OperationExceptionPath.QUERY_KINGDEE_ENTRY_LIST)
	public ModelAndView queryKingdeeEntryList(TransAction requestBean) {
		ModelAndView mv = new ModelAndView(OperationExceptionPath.BASE + OperationExceptionPath.QUERY_KINGDEE_ENTRY_LIST);
		List<KingdeePayEntry> kingdeePayEntryList = transQueryService.queryKingdeeEntryList(requestBean.getTransFlowId());
		mv.addObject("kingdeePayEntryList", JSONObject.toJSON(kingdeePayEntryList));
		return mv;
	}

}
