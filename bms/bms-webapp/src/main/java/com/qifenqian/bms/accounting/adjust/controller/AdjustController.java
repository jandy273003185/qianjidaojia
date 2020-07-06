package com.qifenqian.bms.accounting.adjust.controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.task.Comment;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.accounting.adjust.bean.AccountingAdjustDetail;
import com.qifenqian.bms.accounting.adjust.bean.AccountingAdjustHistoryListBean;
import com.qifenqian.bms.accounting.adjust.bean.AccountingAdjustMain;
import com.qifenqian.bms.accounting.adjust.bean.AccountingAdjustSingleHistoryListBean;
import com.qifenqian.bms.accounting.adjust.bean.AccountingSingleAdjustDetail;
import com.qifenqian.bms.accounting.adjust.bean.AdjustApproveRequestBean;
import com.qifenqian.bms.accounting.adjust.bean.AdjustDetailResponseBean;
import com.qifenqian.bms.accounting.adjust.bean.AdjustHandleRequestBean;
import com.qifenqian.bms.accounting.adjust.bean.QueryAcctInfoResponseBean;
import com.qifenqian.bms.accounting.adjust.bean.Result;
import com.qifenqian.bms.accounting.adjust.bean.SingleAdjustHandleRequestBean;
import com.qifenqian.bms.accounting.adjust.service.AdjustService;
import com.qifenqian.bms.basemanager.trade.service.DownLoadUtil;
import com.qifenqian.bms.basemanager.trade.service.TradeBillService;
import com.qifenqian.bms.basemanager.utils.DatetimeUtils;
import com.qifenqian.bms.platform.web.admin.utils.WebUtils;
import com.sevenpay.invoke.common.message.response.ResponseMessage;
import com.sevenpay.invoke.common.type.RequestColumnValues;
import com.sevenpay.invoke.common.type.RequestColumnValues.AcctType;
import com.sevenpay.invoke.transaction.queryacctseven.QueryAcctSevenResponse;

/**
 * 调账管理控制层
 * 
 * @project sevenpay-bms-web
 * @fileName AdjustController.java
 * @author kunwang.li
 * @date 2015年7月25日
 * @memo
 */
@Controller
@RequestMapping(AdjustPath.BASE)
public class AdjustController {

	private Logger logger = LoggerFactory.getLogger(AdjustController.class);

	@Autowired
	private AdjustService adjustService;

	@Autowired
	private TradeBillService tradeBillService;

	/**
	 * 进入经办页面
	 * 
	 * @param requestUser
	 * @return 账务经办页面
	 */
	@RequestMapping(AdjustPath.HANDLE)
	// @DuplicateSubmit(Type.SET)
	public ModelAndView handle() {
		// 返回视图
		ModelAndView mv = new ModelAndView(AdjustPath.BASE + AdjustPath.HANDLE);

		return mv;
	}

	/**
	 * 经办账务调整提交
	 * 
	 * @param requestUser
	 * @param role
	 * @return result and errorReason
	 */
	@RequestMapping(AdjustPath.HANDLE_SUBMIT)
	// @DuplicateSubmit(Type.CHECK)
	public ModelAndView handleSubmit(AdjustHandleRequestBean request) {
		// 请求bean 打印
		logger.info("请求经办的调账记录：[{}]", JSONObject.toJSONString(request, true));
		String handleUid = String.valueOf(String.valueOf(WebUtils.getUserInfo().getUserId()));
		AccountingAdjustMain mBean = request.getMainInfo();
		mBean.setHandlerUid(handleUid);

		List<AccountingAdjustDetail> dList = request.getEntryList();

		adjustService.handle(mBean, dList);

		ModelAndView mv = new ModelAndView("success");
		return mv;

	}

	/**
	 * 查询调账明细
	 * 
	 * @param opId
	 * @return 账务分录以及审批明细
	 */
	@RequestMapping(AdjustPath.DETAIL)
	@ResponseBody
	public String detail(String opId) {

		AdjustDetailResponseBean response = new AdjustDetailResponseBean();
		try {
			List<AccountingAdjustDetail> entryList = adjustService.queryEntryList(opId);
			List<Comment> comments = adjustService.queryCommentList(opId);

			response.setEntryList(entryList);
			response.setCommentList(comments);

			response.setResult(Result.SUCCESS);
		} catch (Exception e) {
			response.setResult(Result.FAILURE);
			response.setMessage(e.getMessage());
		}

		return JSONObject.toJSONString(response);
	}

	/**
	 * 根据工作流的TaskId查询业务明细
	 * 
	 * @param taskId
	 * @return
	 */
	@RequestMapping(AdjustPath.DETAIL_BY_PID)
	@ResponseBody
	public ModelAndView detailByTaskId(String processInstanceId) {
		AccountingAdjustMain adjustMain = adjustService.queryAccountingAdjustMainByPid(processInstanceId);
		String opId = adjustMain.getOpId();
		ModelAndView mv = null;
		if ("0".equals(adjustMain.getSingleFlag())) {
			mv = new ModelAndView(AdjustPath.BASE + AdjustPath.DETAIL);
			List<AccountingAdjustDetail> entryList = adjustService.queryEntryList(opId);
			List<Comment> commentList = adjustService.queryCommentList(opId);
			mv.addObject("entryList", entryList);
			mv.addObject("commentList", commentList);
			mv.addObject("opId", opId);
		} else {
			mv = new ModelAndView(AdjustPath.BASE + AdjustPath.DETAIL_SINGLE);
			List<AccountingSingleAdjustDetail> entryList = adjustService.querySingleEntryList(opId);
			List<Comment> commentList = adjustService.queryCommentList(opId);
			mv.addObject("entryList", entryList);
			mv.addObject("commentList", commentList);
			mv.addObject("opId", opId);
		}
		return mv;
	}

	/**
	 * 查询出待修改的调账记录
	 * 
	 * @param opId
	 * @return 返回待修改的调账记录
	 */
	@RequestMapping(AdjustPath.PREEDIT)
	@ResponseBody
	public ModelAndView preedit(String taskId) {

		AccountingAdjustMain adjustMain = adjustService.queryAccountingAdjustMain(taskId);
		String opId = adjustMain.getOpId();
		ModelAndView mv = null;
		if ("0".equals(adjustMain.getSingleFlag())) {
			mv = new ModelAndView(AdjustPath.BASE + AdjustPath.EDIT);
			List<AccountingAdjustDetail> entryList = adjustService.queryEntryList(opId);
			List<Comment> commentList = adjustService.queryCommentList(opId);
			mv.addObject("entryList", entryList);
			mv.addObject("commentList", commentList);
			mv.addObject("opId", opId);
		} else {
			mv = new ModelAndView(AdjustPath.BASE + AdjustPath.EDIT_SINGLE);
			List<AccountingSingleAdjustDetail> entryList = adjustService.querySingleEntryList(opId);
			List<Comment> commentList = adjustService.queryCommentList(opId);
			mv.addObject("entryList", entryList);
			mv.addObject("commentList", commentList);
			mv.addObject("opId", opId);
		}

		return mv;
	}

	/**
	 * 修改提交
	 * 
	 * @param request
	 * @return 返回 result and errorReason
	 */
	@RequestMapping(AdjustPath.EDIT)
	@ResponseBody
	public ModelAndView edit(AdjustHandleRequestBean request) {

		String handleUid = String.valueOf(String.valueOf(WebUtils.getUserInfo().getUserId()));
		request.getMainInfo().setHandlerUid(handleUid);
		// 请求bean 打印
		if (request.isResubmit()) {
			adjustService.update4Recheck(request.getMainInfo(), request.getEntryList());
		} else {
			adjustService.delete(request.getMainInfo().getOpId());
		}
		ModelAndView mv = new ModelAndView("success");
		return mv;
	}

	@RequestMapping(AdjustPath.EDIT_SINGLE)
	@ResponseBody
	public ModelAndView editSingle(SingleAdjustHandleRequestBean request) {

		String handleUid = String.valueOf(String.valueOf(WebUtils.getUserInfo().getUserId()));
		request.getMainInfo().setHandlerUid(handleUid);
		// 请求bean 打印
		if (request.isResubmit()) {
			adjustService.update4RecheckSingle(request.getMainInfo(), request.getEntryList());
		} else {
			adjustService.delete(request.getMainInfo().getOpId());
		}
		ModelAndView mv = new ModelAndView("success");
		return mv;
	}

	/**
	 * 删除调账记录
	 * 
	 * @param opId
	 * @return 返回result and errorReason
	 */
	@RequestMapping(AdjustPath.DELETE)
	@ResponseBody
	public ModelAndView delete(String opId) {
		adjustService.delete(opId);
		ModelAndView mv = new ModelAndView("success");
		return mv;
	}

	/**
	 * 查询调账明细信息
	 * 
	 * @param opId
	 * @return 待复合的调账明细信息
	 */
	@RequestMapping(AdjustPath.PRECHECK)
	@ResponseBody
	public ModelAndView precheck(String taskId) {
		AccountingAdjustMain adjustMain = adjustService.queryAccountingAdjustMain(taskId);
		String opId = adjustMain.getOpId();
		ModelAndView mv = null;
		if ("0".equals(adjustMain.getSingleFlag())) {
			mv = new ModelAndView(AdjustPath.BASE + AdjustPath.CHECK);
			List<AccountingAdjustDetail> entryList = adjustService.queryEntryList(opId);
			List<Comment> commentList = adjustService.queryCommentList(opId);
			mv.addObject("entryList", entryList);
			mv.addObject("commentList", commentList);
			mv.addObject("opId", opId);
		} else {
			mv = new ModelAndView(AdjustPath.BASE + AdjustPath.CHECK_SINGLE);
			List<AccountingSingleAdjustDetail> entryList = adjustService.querySingleEntryList(opId);
			List<Comment> commentList = adjustService.queryCommentList(opId);
			mv.addObject("entryList", entryList);
			mv.addObject("commentList", commentList);
			mv.addObject("opId", opId);
		}
		return mv;
	}

	/**
	 * 复核
	 * 
	 * @param approveBean
	 * @return
	 */
	@RequestMapping(AdjustPath.CHECK)
	@ResponseBody
	public ModelAndView check(AdjustApproveRequestBean approveBean) {
		// 请求bean 打印
		String checkerUid = String.valueOf(String.valueOf(WebUtils.getUserInfo().getUserId()));

		adjustService.check(approveBean.getOpId(), approveBean.isCheckPass(), approveBean.getMemo(), checkerUid);

		ModelAndView mv = new ModelAndView("success");
		return mv;
	}

	/**
	 * 查询待总经理审批的调账明细数据
	 * 
	 * @param opId
	 * @return
	 */
	@RequestMapping(AdjustPath.PREAPPROVE)
	@ResponseBody
	public ModelAndView preapprove(String taskId) {
		AccountingAdjustMain adjustMain = adjustService.queryAccountingAdjustMain(taskId);
		String opId = adjustMain.getOpId();
		ModelAndView mv = null;
		if ("0".equals(adjustMain.getSingleFlag())) {
			mv = new ModelAndView(AdjustPath.BASE + AdjustPath.APPROVE);
			List<AccountingAdjustDetail> entryList = adjustService.queryEntryList(opId);
			List<Comment> commentList = adjustService.queryCommentList(opId);
			mv.addObject("entryList", entryList);
			mv.addObject("commentList", commentList);
			mv.addObject("opId", opId);
		} else {
			mv = new ModelAndView(AdjustPath.BASE + AdjustPath.APPROVE_SINGLE);
			List<AccountingSingleAdjustDetail> entryList = adjustService.querySingleEntryList(opId);
			List<Comment> commentList = adjustService.queryCommentList(opId);
			mv.addObject("entryList", entryList);
			mv.addObject("commentList", commentList);
			mv.addObject("opId", opId);
		}
		return mv;
	}

	/**
	 * 总经理审批提交
	 * 
	 * @param approveBean
	 * @return 返回result and errorReason
	 */
	@RequestMapping(AdjustPath.APPROVE)
	@ResponseBody
	public ModelAndView approve(AdjustApproveRequestBean approveBean) {
		// 请求bean 打印
		String checkerUid = String.valueOf(String.valueOf(WebUtils.getUserInfo().getUserId()));

		adjustService.approve(approveBean.getOpId(), approveBean.isCheckPass(), approveBean.getMemo(), checkerUid);
		ModelAndView mv = new ModelAndView("success");
		return mv;
	}

	/**
	 * 查询账号信息<br>
	 * 
	 * @param acctId
	 * @return
	 */
	@RequestMapping(AdjustPath.QUERY_ACCT_INFO)
	@ResponseBody
	public String queryActInfo(String acctId, AcctType acctType) {
		logger.info("请求对象acctId[{}], acctType[{}]", acctId, acctType);
		ResponseMessage<QueryAcctSevenResponse> actInfo = adjustService.queryActInfo(acctId, acctType);
		QueryAcctInfoResponseBean res = new QueryAcctInfoResponseBean();
		if (RequestColumnValues.RtnResult.SUCCESS == actInfo.getRtnResult()) {
			res.setAcctSeven(actInfo.getResponse().getAcctSeven());
			res.setResult(Result.SUCCESS);
			res.setMessage("查询成功");
		} else {
			res.setResult(Result.FAILURE);
			res.setMessage(res.getMessage());
		}
		return JSONObject.toJSONString(res);
	}

	/**
	 * 单边调账进入经办页面
	 * 
	 * @param requestUser
	 * @return 账务经办页面
	 */
	@RequestMapping(AdjustPath.HANDLE_SINGLE)
	// @DuplicateSubmit(Type.SET)
	public ModelAndView singleHandle() {
		// 返回视图
		ModelAndView mv = new ModelAndView(AdjustPath.BASE + AdjustPath.HANDLE_SINGLE);

		return mv;
	}

	/**
	 * 单边调账经办账务调整提交
	 * 
	 * @param requestUser
	 * @param role
	 * @return result and errorReason
	 */
	@RequestMapping(AdjustPath.HANDLE_SINGLE_SUBMIT)
	// @DuplicateSubmit(Type.CHECK) TODO
	public ModelAndView singleHandleSubmit(SingleAdjustHandleRequestBean request) {
		// 请求bean 打印
		logger.info("请求经办的调账记录：[{}]", JSONObject.toJSONString(request, true));
		String handleUid = String.valueOf(String.valueOf(WebUtils.getUserInfo().getUserId()));
		AccountingAdjustMain mBean = request.getMainInfo();
		mBean.setHandlerUid(handleUid);

		List<AccountingSingleAdjustDetail> dList = request.getEntryList();

		adjustService.handle(mBean, dList);

		ModelAndView mv = new ModelAndView("success");
		return mv;

	}

	/**
	 * 调账历史查询
	 * 
	 * @param queryBean
	 * @return
	 */
	@RequestMapping(AdjustPath.HISTORY_LIST)
	public ModelAndView historyList(AccountingAdjustHistoryListBean queryBean) {

		ModelAndView mv = new ModelAndView(AdjustPath.BASE + AdjustPath.HISTORY_LIST);
		if (StringUtils.equals(queryBean.getFirstFlag(), "1")) {
			queryBean.setHandleDateStart(DatetimeUtils.dateFormat(new Date(), "yyyy-MM-dd"));
			queryBean.setHandleDateEnd(queryBean.getHandleDateStart());
		}
		List<AccountingAdjustHistoryListBean> list = adjustService.historyList(queryBean);
		mv.addObject("queryBean", queryBean);
		mv.addObject("list", list);
		return mv;
	}

	/**
	 * 调账历史查询
	 * 
	 * @param listBean
	 * @return
	 */
	@RequestMapping(AdjustPath.SINGLE_HISTORY_LIST)
	public ModelAndView singleHistoryList(AccountingAdjustSingleHistoryListBean queryBean) {

		ModelAndView mv = new ModelAndView(AdjustPath.BASE + AdjustPath.SINGLE_HISTORY_LIST);
		if (StringUtils.equals(queryBean.getFirstFlag(), "1")) {
			queryBean.setHandleDateStart(DatetimeUtils.dateFormat(new Date(), "yyyy-MM-dd"));
			queryBean.setHandleDateEnd(queryBean.getHandleDateStart());
		}
		List<AccountingAdjustSingleHistoryListBean> list = adjustService.singleHistoryList(queryBean);
		mv.addObject("queryBean", queryBean);
		mv.addObject("list", list);
		return mv;
	}

	/**
	 * 调账历史导出Excel
	 * 
	 * @param queryBean
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(AdjustPath.HISTORY_LIST_EXCEL)
	public void historyListExcel(AccountingAdjustHistoryListBean queryBean, HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		System.out.println("======================" + queryBean.getCreditAcctNo());
		try {
			List<AccountingAdjustHistoryListBean> list = adjustService.historyListReport(queryBean);
			String[] headers = { "业务编号", "借方账户类型", "借方账号", "借方账户名称", "贷方账户类型", "贷方账号", "贷方账户名称", "币别", "金额", "经办日期",
					"经办人", "关联业务编号", "备注" };
			String fileName = DatetimeUtils.dateSecond() + "_调账历史.xls";
			Map<String, String> fileInfo = tradeBillService.exportExcel(list, headers, fileName, "调账历史表", request);
			DownLoadUtil.downLoadFile(fileInfo.get("filePath"), response, fileInfo.get("fileName"), "xls");

			logger.info("调账历史导出Excel成功");
		} catch (Exception e) {
			logger.error("单调账历史导出Excel异常", e);
			e.printStackTrace();
		}

	}

	/**
	 * 单边调账历史导出Excel
	 * 
	 * @param queryBean
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(AdjustPath.SINGLE_HISTORY_LIST_EXCEL)
	public void singleHistoryListExcel(AccountingAdjustSingleHistoryListBean queryBean, HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		try {
			List<AccountingAdjustSingleHistoryListBean> list = adjustService.singleHistoryListReport(queryBean);

			String[] headers = { "业务编号", "账户类型", "账号", "账户名称", "币别", "金额", "可用金额", "在途金额", "经办日期", "经办人", "关联业务编号",
					"备注" };
			String fileName = DatetimeUtils.dateSecond() + "_单边调账历史.xls";
			Map<String, String> fileInfo = tradeBillService.exportExcel(list, headers, fileName, "单边调账历史表", request);
			DownLoadUtil.downLoadFile(fileInfo.get("filePath"), response, fileInfo.get("fileName"), "xls");

			logger.info("单边调账历史导出Excel成功");
		} catch (Exception e) {
			logger.error("单边调账历史导出Excel异常", e);
			e.printStackTrace();
		}
	}
}
