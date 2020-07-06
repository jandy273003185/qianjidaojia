package com.qifenqian.bms.basemanager.busstransfer;

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
import com.qifenqian.bms.basemanager.busstransfer.bean.BussTransferBean;
import com.qifenqian.bms.basemanager.busstransfer.bean.BussTransferExcel;
import com.qifenqian.bms.basemanager.busstransfer.service.BussTransferService;
import com.qifenqian.bms.basemanager.trade.service.DownLoadUtil;
import com.qifenqian.bms.basemanager.trade.service.TradeBillService;
import com.qifenqian.bms.basemanager.utils.DatetimeUtils;

@Controller
@RequestMapping(BussTransferPath.BASE)
public class BussTransferController {

	private static Logger logger = LoggerFactory.getLogger(BussTransferController.class);

	
	@Autowired
	private BussTransferService bussTransferService;
	@Autowired
	private TradeBillService tradeBillService;

	/**
	 * 查询商户转账信息
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(BussTransferPath.LIST)
	public ModelAndView list(BussTransferBean queryBean) {
		logger.info("查询商户转账信息对象  [{}]", JSONObject.toJSONString(queryBean, true));
		ModelAndView model = new ModelAndView(BussTransferPath.BASE + BussTransferPath.LIST);
		List<BussTransferBean> bussTransferList = bussTransferService.selectTransfer(queryBean);
		model.addObject("queryBean", queryBean);
		model.addObject("transferList", JSONObject.toJSON(bussTransferList));
		return model;
	}
	
	
	
	/**
	 * 导出转账信息
	 * @param recharge
	 */
	@RequestMapping(BussTransferPath.BUSSTRANSFEREXPORT)
	public void exportTransferExcel(BussTransferBean bussTransferBean ,HttpServletRequest request,HttpServletResponse response){
		
		logger.info("导出商户转账信息对象  [{}]", JSONObject.toJSONString(bussTransferBean, true));
		
		try {
			List<BussTransferExcel> bussTransferExcel = bussTransferService.selectTransferExcel(bussTransferBean);
			String[] headers = { "七分钱订单号", "订单名称", "订单描述", "付方商户账号","付方商户名称","收方商户账号","收方商户名称","订单开始时间","订单金额","订单状态","账期"};
			String fileName =DatetimeUtils.dateSecond()+"_商户转账信息.xls";
			Map<String,String> fileInfo= tradeBillService.exportExcel(bussTransferExcel,headers,fileName,"商户转账信息",request);
			DownLoadUtil.downLoadFile(fileInfo.get("filePath"), response, fileInfo.get("fileName"), "xls");
			logger.info("导出excel商户转账信息成功");
		} catch (Exception e) {
			logger.error("导出excel商户转账信息异常",e);
		}
	}
	
}
