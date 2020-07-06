package com.qifenqian.bms.accounting.refund;

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
import com.qifenqian.bms.accounting.refund.bean.RefundBill;
import com.qifenqian.bms.accounting.refund.bean.RefundExcel;
import com.qifenqian.bms.accounting.refund.bean.RefundRecord;
import com.qifenqian.bms.accounting.refund.mapper.RebackRecordMapper;
import com.qifenqian.bms.accounting.refund.service.RefundBillService;
import com.qifenqian.bms.basemanager.Constant;
import com.qifenqian.bms.basemanager.acctsevenbuss.bean.AcctSevenBuss;
import com.qifenqian.bms.basemanager.trade.service.DownLoadUtil;
import com.qifenqian.bms.basemanager.trade.service.TradeBillService;
import com.qifenqian.bms.basemanager.utils.DatetimeUtils;
import com.qifenqian.bms.platform.web.admin.utils.WebUtils;


/**
 * 退款
 * 
 * @author shen
 */
@Controller
@RequestMapping(RefundBillPath.BASE)
public class RefundBillController {

	private static Logger logger = LoggerFactory.getLogger(RefundBillController.class);

	@Autowired
	private RefundBillService refundBillService;

	@Autowired
	private TradeBillService tradeBillService;

	@Autowired
	private RebackRecordMapper rebackRecordMapper;

	/**
	 * 退款申請查詢
	 * 
	 * @param refundBill
	 * @return
	 */
	@RequestMapping(RefundBillPath.LIST)
	public ModelAndView list(RefundBill refundBill) {
		logger.info("退款申請查詢  [{}]", JSONObject.toJSONString(refundBill, true));
		ModelAndView mv = new ModelAndView(RefundBillPath.BASE + RefundBillPath.LIST);

		List<RefundBill> refundBillList = refundBillService.select(refundBill);
		mv.addObject("refundBillList", refundBillList);
		mv.addObject("refundBean", refundBill);
		return mv; 
	}

	/**
	 * 退货审批
	 * 
	 * @param refundBill
	 * @return
	 */
	@RequestMapping(value=RefundBillPath.APPROVAL)
	@ResponseBody
	public String approval(RefundBill refundBill) {
		logger.info("退货审批对象 [{}]", JSONObject.toJSONString(refundBill, true));
		JSONObject object = new JSONObject();

		if (null == refundBill) {
			throw new IllegalArgumentException("退款对象为空");
		}
		if (StringUtils.isBlank(refundBill.getOrderId())) {
			throw new IllegalArgumentException("七分钱订单号不可为空");
		}
		if (StringUtils.isBlank(refundBill.getOriginOrderId())) {
			throw new IllegalArgumentException("七分钱原交易订单号不可为空");
		}
		if (StringUtils.isBlank(refundBill.getRefundCustId())) {
			throw new IllegalArgumentException("客户编号不可为空");
		}
		if (StringUtils.isBlank(refundBill.getOriginCoreSn())) {
			throw new IllegalArgumentException("原交易流水号不可为空");
		}
		if (null == refundBill.getOriginTransAmt()) {
			throw new IllegalArgumentException("原交易金额不可为空");
		}
		if (null == refundBill.getRefundAmt()) {
			throw new IllegalArgumentException("退款金额不可为空");
		}
		if (StringUtils.isBlank(refundBill.getAuditState())) {
			throw new IllegalArgumentException("审核信息为空");
		}
		if (StringUtils.isBlank(refundBill.getMerchantCustId())) {
			throw new IllegalArgumentException("商户编号不能为空");
		}

		try {
			// 审核通过
			if (refundBill.getAuditState().equals(Constant.WITHDRAW_AUDIT_SUCCESS)) {
				logger.info("订单" + refundBill.getOrderId() + "审核,调用核心！");
				// 校验是否有正在处理的数据
				object.put("result", "FAIL");
				int count = rebackRecordMapper.countOriginRelateSuccessOrUnknowTrans(refundBill.getOriginCoreSn());
				if (count > 0) {
					object.put("message", "已有正在处理的交易");
					return object.toJSONString();
				}
				// 校验交易总额
				RefundRecord refundRecord = rebackRecordMapper.countRebackAmtSuccessOrUnknowTrans(refundBill.getOriginCoreSn());
				if (refundRecord != null) {
					if (refundBill.getOriginTransAmt().compareTo(
							refundRecord.getRefundAmt().add(refundBill.getRefundAmt())) < 0) {
						object.put("message", "退款总额大于原交易金额");
						return object.toJSONString();
					}
				}
				// 校验商户金额
				AcctSevenBuss acctBuss = rebackRecordMapper.queryAcctBussByMerchantCode(refundBill.getMerchantCustId());
				if (acctBuss.getUsableAmt().compareTo(refundBill.getRefundAmt()) < 0) {
					object.put("message", "商户余额不足");
					return object.toJSONString();
				}

				object = refundBillService.refundBill(refundBill);

				// 审核不通过
			} else if (refundBill.getAuditState().equals(Constant.WITHDRAW_AUDIT_FAIL)) {
				logger.info("订单" + refundBill.getOrderId() + "被驳回,更新状态！");
				RefundBill updateBean = new RefundBill();
				updateBean.setAuditState(refundBill.getAuditState());
				updateBean.setRefundState(Constant.REFUND_STATE_AUDIT_REJECT);
				updateBean.setOrderId(refundBill.getOrderId());
				updateBean.setModifyId(String.valueOf(WebUtils.getUserInfo().getUserId()));
				refundBillService.auditRefund(updateBean);
				object.put("result", "SUCCESS");
			}

		} catch (Exception e) {
			object.put("result", "FAIL");
			object.put("message", e.getMessage());
		}

		return object.toJSONString();
	}

	/**
	 * 导出退款信息
	 * 
	 * @param recharge
	 */
	@RequestMapping(RefundBillPath.REFUNDEXPORT)
	public void refundExport(RefundBill refundBill, HttpServletRequest request, HttpServletResponse response) {

		try {
			List<RefundExcel> refundExcel = refundBillService.selectRefundExcel(refundBill);
			String[] headers = { "七分钱退款编号", "原始七分钱订单号", "渠道订单号", "退款类型", "原商户订单号", "客户账号", "原始交易金额", "原始交易时间", "商户编号",
					"商户名称", "退款金额", "退款申请时间", "账期", "手续费", "付手续费方", "退款状态", "审核状态", "审核人", "审核时间", "核销状态", "核销人",
					"核销时间" };
			String fileName = DatetimeUtils.dateSecond() + "_退款信息.xls";
			Map<String, String> fileInfo = tradeBillService
					.exportExcel(refundExcel, headers, fileName, "退款信息", request);
			DownLoadUtil.downLoadFile(fileInfo.get("filePath"), response, fileInfo.get("fileName"), "xls");
			logger.info("导出excel退款信息成功");
		} catch (Exception e) {
			logger.error("导出excel退款信息异常", e);
		}

	}

	/**
	 * 提现核销
	 */
	@RequestMapping(RefundBillPath.VERIFICATION)
	@ResponseBody
	public String refundVerification(RefundBill refundBill) {
		logger.info("退款核销");
		JSONObject json = new JSONObject();

		if (null == refundBill || refundBill.getOrderId().equals("")) {
			json.put("result", "FAIL");
			json.put("message", "核销对象不存在");
			return json.toJSONString();

		}
		try {
			refundBillService.refundVerification(refundBill);
			json.put("result", "SUCCESS");
		} catch (Exception e) {
			logger.info("提现核销异常：" + e.getMessage());
			json.put("message", e.getMessage());
		}
		return json.toJSONString();
	}

	/**
	 * 退款申请记录
	 * 
	 * @param orderId
	 * @return
	 */
	@RequestMapping(RefundBillPath.REFUNDRECORD)
	public ModelAndView getRefundRecord(String orderId) {
		ModelAndView mv = new ModelAndView(RefundBillPath.BASE + RefundBillPath.REFUNDRECORD);
		List<RefundRecord> list = refundBillService.selectRefundRecordByOrderId(orderId);
		mv.addObject("refundRecordList", list);
		return mv;

	}
}
