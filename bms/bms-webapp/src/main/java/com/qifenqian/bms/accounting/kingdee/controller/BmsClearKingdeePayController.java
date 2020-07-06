package com.qifenqian.bms.accounting.kingdee.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qifenqian.bms.platform.common.utils.DatetimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.accounting.exception.OperationExceptionPath;
import com.qifenqian.bms.accounting.exception.dao.kingdeeclear.bean.KingdeePayEntry;
import com.qifenqian.bms.accounting.exception.service.TransQueryService;
import com.qifenqian.bms.accounting.kingdee.bean.BmsClearKingdeePayInfo;
import com.qifenqian.bms.accounting.kingdee.mapper.BmsClearKingdeePayMapper;
import com.qifenqian.bms.accounting.kingdee.service.BmsClearKingdeePayService;
import com.qifenqian.bms.basemanager.trade.service.DownLoadUtil;
import com.qifenqian.bms.basemanager.trade.service.TradeBillService;

@Controller
@RequestMapping(BmsClearKingdeePayPATH.BASE)
public class BmsClearKingdeePayController {
	
	private static Logger logger = LoggerFactory.getLogger(BmsClearKingdeePayController.class);
	
	@Autowired
	private BmsClearKingdeePayService bmsClearKingdeePayService;
	@Autowired
	private BmsClearKingdeePayMapper bmsClearKingdeePayMapper;
	@Autowired
	private TransQueryService transQueryService;
	@Autowired
	private TradeBillService tradeBillService;
	
	@RequestMapping(BmsClearKingdeePayPATH.LIST)
	public ModelAndView selectList(BmsClearKingdeePayInfo bean){
		ModelAndView mv = new ModelAndView(BmsClearKingdeePayPATH.BASE+BmsClearKingdeePayPATH.LIST);
		List<BmsClearKingdeePayInfo> list = bmsClearKingdeePayService.selectList(bean);
		mv.addObject("BmsClearKingdeePayList", list);
		mv.addObject("bmsClearKingdeePayBean", bean);
		return mv;
	}
	
	/**
	 * 金蝶待交易数据查询导出
	 * 
	 * @param requestBean
	 * @param request
	 * @param response
	 */
	@RequestMapping(BmsClearKingdeePayPATH.EXPORT)
	public void exportExcel(BmsClearKingdeePayInfo requestBean, HttpServletRequest request, HttpServletResponse response) {
		logger.info("导出金蝶待交易数据");
		
		try {
			List<BmsClearKingdeePayInfo> excels = bmsClearKingdeePayMapper.selectList(requestBean);

			String[] headers = { 
					"清算编号/单据编号",
					"业务类型",
					"业务编号",
					"业务日期",
					"往来单位名称",
					"往来单位编码",
					"往来单位类型",
					"收款单位名称",
					"收款单位编码",
					"收款单位类型",
					"是否期初单据",
					"七分钱会计日期",
					"七分钱交易发送日期",
					"七分钱交易发送时间HHmmss",
					"状态",
					"写入人",
					"写入日期",
					"写入时间",
					"返回日期",
					"返回时间",
					"返回ID",
					"返回number",
					"交易是否成功",
					"返回错误码",
					"返回信息",
					"返回堆栈跟踪",
					"返回错误信息列表：",
					"返回写入时间",
					"对账状态",
					"银行处理状态",
					"付款用途名称",
					"付款用途编码",
					"应付金额",
					"付款金额",
					"折后金额",
					"实付金额",
					"我方银行账号名称",
					"我方银行账号编码",
					"收款银行名称",
					"收款银行编码",
					"对方银行账号",
					"对方账户名称",
					"对方开户行",
					"开户行地址",
					"联行号",
					"费用项目编码",
					"付款金额本位币",
					"银行描述信息",
					"银行描述信息"};
			String fileName = DatetimeUtils.datetime()+"_金蝶待交易数据报表_.xls";
			Map<String, String> fileInfo = tradeBillService.exportExcel(excels, headers, fileName, "金蝶待交易数据报表", request);
			DownLoadUtil.downLoadFile(fileInfo.get("filePath"), response, fileInfo.get("fileName"), "xls");
			logger.info("导出金蝶待交易数据报表成功");
		} catch (Exception e) {
			logger.error("导出金蝶待交易数据报表异常", e);
			throw new RuntimeException(e);
		}
	}
	
	
	/**
	 * 金蝶交易列表
	 * 
	 * @param requestBean
	 */
	@RequestMapping(BmsClearKingdeePayPATH.KINGDEE_ENTRY_LIST)
	public ModelAndView queryKingdeeEntryList(BmsClearKingdeePayInfo requestBean) {
		logger.info("金蝶交易清算编号{}",requestBean.getClearId());
		ModelAndView mv = new ModelAndView(OperationExceptionPath.BASE + OperationExceptionPath.QUERY_KINGDEE_ENTRY_LIST);
		List<KingdeePayEntry> kingdeePayEntryList = transQueryService.queryKingdeeEntryList(requestBean.getClearId());
		mv.addObject("kingdeePayEntryList", JSONObject.toJSON(kingdeePayEntryList));
		return mv;
	}
}
