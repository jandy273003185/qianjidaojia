package com.qifenqian.bms.accounting.jgkjsevendatasource;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.accounting.jgkjsevendatasource.bean.BalJgkjSevenDataSource;
import com.qifenqian.bms.accounting.jgkjsevendatasource.mapper.BalJgkjSevenDataSourceMapper;
import com.qifenqian.bms.accounting.jgkjsevendatasource.service.BalJgkjSevenDataSourceService;
import com.qifenqian.bms.basemanager.trade.service.DownLoadUtil;
import com.qifenqian.bms.basemanager.trade.service.TradeBillService;
import com.qifenqian.bms.basemanager.utils.DatetimeUtils;

/**
 * 七分钱交广科技对账原数据
 * 
 * @author shen
 * 
 */
@Repository
@RequestMapping(BalJgkjSevenDataSourcePath.BASE)
public class BalJgkjSevenDataSourceController {

	private static Logger logger = LoggerFactory.getLogger(BalJgkjSevenDataSourceController.class);

	@Autowired
	private BalJgkjSevenDataSourceService service;

	@Autowired
	private BalJgkjSevenDataSourceMapper mapper;

	@Autowired
	private TradeBillService tradeBillService;

	/**
	 * 七分钱交广科技对账原数据
	 * 
	 * @param request
	 * @param sevenDataSource
	 * @return
	 */
	@RequestMapping(BalJgkjSevenDataSourcePath.LIST)
	public ModelAndView sevenDataSourceList(HttpServletRequest request, BalJgkjSevenDataSource queryBean) {

		logger.info("七分钱交广科技对账原数据查询对象：{}", JSONObject.toJSONString(queryBean, true));

		ModelAndView mv = new ModelAndView(BalJgkjSevenDataSourcePath.BASE + BalJgkjSevenDataSourcePath.LIST);
		List<BalJgkjSevenDataSource> sevenDataSourceList = service.selectSevenDataSourceList(queryBean);
		mv.addObject("queryBean", queryBean);
		mv.addObject("sevenDataSourceList", sevenDataSourceList);
		return mv;
	}
	
	/***
	 * 导出七分钱交广科技对账原数据
	 * @param queryBean
	 * @param request
	 * @param response
	 */
	@RequestMapping(BalJgkjSevenDataSourcePath.EXPORT)
	public void exportZytExcel(BalJgkjSevenDataSource queryBean, HttpServletRequest request,
			HttpServletResponse response) {

		logger.info("导出七分钱交广科技对账原数据对象：{}", JSONObject.toJSONString(queryBean, true));

		try {
			List<BalJgkjSevenDataSource> excels = mapper.selectSevenDataSourceList(queryBean);
			String headers[] = { "交易清算流水号", "七分钱流水号", "交易编号", "银联流水号", "交易类型", "交广科技交易代码", "交广科技卡号", "币别", "金额",
					"七分钱会计日期", "七分钱交易发送日期", "七分钱交易发送时间", "交易状态", "交广科技返回日期", "交广科技返回时间", "交广科技平台流水号", "交广科技返回交易结果代码",
					"交广科技返回描述", "写入日期", "写入时间" };
			
			String fileName = DatetimeUtils.dateSecond() + "_七分钱交广科技银联对账原数据报表.xls";
			Map<String, String> fileInfo = tradeBillService.exportExcel(excels, headers, fileName, "七分钱交广科技对账原数据",
					request);
			DownLoadUtil.downLoadFile(fileInfo.get("filePath"), response, fileInfo.get("fileName"), "xls");
			logger.info("导出七分钱交广科技对账原数据成功");
		} catch (Exception e) {
			logger.error("导出七分钱交广科技对账原数据异常", e);
		}
	}
}
