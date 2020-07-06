package com.qifenqian.bms.basemanager.transfer;

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
import com.qifenqian.bms.basemanager.trade.service.DownLoadUtil;
import com.qifenqian.bms.basemanager.trade.service.TradeBillService;
import com.qifenqian.bms.basemanager.transfer.bean.TransferBean;
import com.qifenqian.bms.basemanager.transfer.bean.TransferExcel;
import com.qifenqian.bms.basemanager.transfer.service.TransferService;
import com.qifenqian.bms.basemanager.utils.DatetimeUtils;

@Controller
@RequestMapping(TransferPath.BASE)
public class TransferController {

	private static Logger logger = LoggerFactory.getLogger(TransferController.class);

	
	@Autowired
	private TransferService transferService;
	@Autowired
	private TradeBillService tradeBillService;

	/**
	 * 查询转账信息
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(TransferPath.LIST)
	public ModelAndView list(TransferBean queryBean) {
		logger.info("查询转账信息对象  [{}]", JSONObject.toJSONString(queryBean, true));
		ModelAndView model = new ModelAndView(TransferPath.BASE + TransferPath.LIST);
		List<TransferBean> transferList = transferService.selectTransfer(queryBean);
		model.addObject("queryBean", queryBean);
		model.addObject("transferList", JSONObject.toJSON(transferList));
		return model;
	}
	
	
	
	/**
	 * 导出转账信息
	 * @param recharge
	 */
	@RequestMapping(TransferPath.TRANSFEREXPORT)
	public void exportTransferExcel(TransferBean transferBean ,HttpServletRequest request,HttpServletResponse response){
		
		logger.info("导出转账信息对象  [{}]", JSONObject.toJSONString(transferBean, true));
		
		try {
			List<TransferExcel> transferExcel = transferService.selectTransferExcel(transferBean);
			String[] headers = { "七分钱订单号", "交广科技订单号", "订单名称", "订单描述", "付方客户手机号","付方客户名称","收方客户手机号","收方客户名称","订单开始时间","订单金额","订单状态","账期"};
			String fileName =DatetimeUtils.dateSecond()+"_转账信息.xls";
			Map<String,String> fileInfo= tradeBillService.exportExcel(transferExcel,headers,fileName,"转账信息",request);
			DownLoadUtil.downLoadFile(fileInfo.get("filePath"), response, fileInfo.get("fileName"), "xls");
			logger.info("导出excel转账信息成功");
		} catch (Exception e) {
			logger.error("导出excel转账信息异常",e);
		}
	}
	
}
