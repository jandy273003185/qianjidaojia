package com.qifenqian.bms.accounting.jgkjdatasource;

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
import com.qifenqian.bms.accounting.jgkjdatasource.bean.BalJgkjDataSource;
import com.qifenqian.bms.accounting.jgkjdatasource.mapper.BalJgkjDataSourceMapper;
import com.qifenqian.bms.accounting.jgkjdatasource.service.BalJgkjDataSourceService;
import com.qifenqian.bms.basemanager.trade.service.DownLoadUtil;
import com.qifenqian.bms.basemanager.trade.service.TradeBillService;
import com.qifenqian.bms.basemanager.utils.DatetimeUtils;

/**
 * 交广科技对账源数据
 * 
 * @author shen
 * 
 */

@Controller
@RequestMapping(BalJgkjDataSourcePath.BASE)
public class BalJgkjDataSourceController {

	private static Logger logger = LoggerFactory.getLogger(BalJgkjDataSourceController.class);

	@Autowired
	private BalJgkjDataSourceService service;
	@Autowired
	private BalJgkjDataSourceMapper mapper;
	@Autowired
	private TradeBillService tradeBillService;

	/**
	 * 交广科技对账源数据
	 * 
	 * @param request
	 * @param zytDataSource
	 * @return
	 */
	@RequestMapping(BalJgkjDataSourcePath.JGKJLIST)
	public ModelAndView sevenDataSourceList(HttpServletRequest request, BalJgkjDataSource queryBean) {

		logger.info("交广科技对账源数据查询对象： {}", JSONObject.toJSONString(queryBean, true));

		ModelAndView mv = new ModelAndView(BalJgkjDataSourcePath.BASE + BalJgkjDataSourcePath.JGKJLIST);
		List<BalJgkjDataSource> zytDataSourceList = service.selectZytDataSourceList(queryBean);
		mv.addObject("queryBean", queryBean);
		mv.addObject("zytDataSourceList", zytDataSourceList);
		return mv;
	}

	/***
	 * 导出交广科技对账源数据
	 * 
	 * @param zytDataSource
	 * @param request
	 * @param response
	 */
	@RequestMapping(BalJgkjDataSourcePath.JGKJEXPORT)
	public void exportZytExcel(BalJgkjDataSource queryBean, HttpServletRequest request,
			HttpServletResponse response) {
		logger.info("导出交广科技对账源数据查询对象： {}", JSONObject.toJSONString(queryBean, true));

		try {
			List<BalJgkjDataSource> excels = mapper.selectZytDataSourceList(queryBean);
			String headers[] = { "文件编号", "序号", "会计日期", "渠道流水号", "合作机构流水号", "平台流水号", "主账号", "交易传输时间", "交易返回时间", "交易金额",
					"交易类型", "交易状态", "预留字段", "写入日期", "写入时间" };
			String fileName = DatetimeUtils.dateSecond() + "_交广科技对账源数据报表.xls";
			Map<String, String> fileInfo = tradeBillService
					.exportExcel(excels, headers, fileName, "交广科技对账源数据", request);
			DownLoadUtil.downLoadFile(fileInfo.get("filePath"), response, fileInfo.get("fileName"), "xls");
			logger.info("导出交广科技对账源数据成功");
		} catch (Exception e) {
			logger.error("导出交广科技对账源数据异常", e);
		}
	}
}
