package com.qifenqian.bms.accounting.cncbdatasource;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.accounting.cncbdatasource.bean.BalCncbDataSource;
import com.qifenqian.bms.accounting.cncbdatasource.mapper.BalCncbDataSourceMapper;
import com.qifenqian.bms.accounting.cncbdatasource.service.BalCncbDataSourceService;
import com.qifenqian.bms.basemanager.trade.service.DownLoadUtil;
import com.qifenqian.bms.basemanager.trade.service.TradeBillService;
import com.qifenqian.bms.basemanager.utils.DatetimeUtils;




@Controller
@RequestMapping(BalCncbDataSourcePath.BASE)
public class BalCncbDataSourceController {

	
	private static Logger logger = LoggerFactory.getLogger(BalCncbDataSourceController.class);
	
	@Autowired
	private BalCncbDataSourceService service;
	
	@Autowired
	private BalCncbDataSourceMapper mapper;
	
	@Autowired
	private TradeBillService tradeBillService;
	
	/**
	 * 中信对账源数据
	 * @param request
	 * @param queryBean
	 * @return
	 */
	@RequestMapping(BalCncbDataSourcePath.CNCBLIST)
	public ModelAndView cncbDataSourceList(HttpServletRequest request, BalCncbDataSource queryBean) {

		logger.info("中信对账源数据查询对象： {}", JSONObject.toJSONString(queryBean, true));

		ModelAndView mv = new ModelAndView(BalCncbDataSourcePath.BASE + BalCncbDataSourcePath.CNCBLIST);
		List<BalCncbDataSource> cncbDataSourceList = service.selectCncbDataSourceList(queryBean);
		mv.addObject("queryBean", queryBean);
		mv.addObject("cncbDataSourceList", cncbDataSourceList);
		return mv;
	}
	
	
	
	@RequestMapping(BalCncbDataSourcePath.CNCBEXPORT)
	public void exportCncbExcel(BalCncbDataSource queryBean, HttpServletRequest request,
			HttpServletResponse response) {
		logger.info("导出中信对账源数据查询对象： {}", JSONObject.toJSONString(queryBean, true));

		try {
			List<BalCncbDataSource> excels = mapper.selectCncbDataSourceList(queryBean);
			String headers[] = { "订单号", "数据平台","交易类型", "交易状态","货币种类", "总金额", "用户标识", "商户号","商品名称", "交易时间", 
					"实收金额","会计时间" };
			String fileName = DatetimeUtils.dateSecond() + "_中信对账源数据报表.xls";
			Map<String, String> fileInfo = tradeBillService
					.exportExcel(excels, headers, fileName, "中信对账源数据", request);
			DownLoadUtil.downLoadFile(fileInfo.get("filePath"), response, fileInfo.get("fileName"), "xls");
			logger.info("导出中信对账源数据成功");
		} catch (Exception e) {
			logger.error("导出中信对账源数据异常", e);
		}
	}
	
}
