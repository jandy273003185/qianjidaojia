package com.qifenqian.bms.meeting.redPacket.controller;

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
import com.qifenqian.bms.basemanager.utils.DatetimeUtils;
import com.qifenqian.bms.meeting.redPacket.bean.MeetRedPacketBill;
import com.qifenqian.bms.meeting.redPacket.bean.MeetRedPacketBillConditionBean;
import com.qifenqian.bms.meeting.redPacket.service.RedPacketService;

@Controller
@RequestMapping(RedPacketPath.BASE)
public class RedPacketController {
	private static Logger logger = LoggerFactory.getLogger(RedPacketController.class);
	
	@Autowired
	private RedPacketService redPacketService;
	
	@Autowired
	private TradeBillService tradeBillService;
	
	@RequestMapping(RedPacketPath.LIST)
	public ModelAndView list(MeetRedPacketBillConditionBean bill){
		logger.info("红包流水查询");
		ModelAndView model = new ModelAndView(RedPacketPath.BASE+RedPacketPath.LIST);
		List<MeetRedPacketBill> list = redPacketService.selectRedPacket(bill);
		
		model.addObject("redPacketBillList", JSONObject.toJSON(list));
		model.addObject("redPacket",bill);
		return model;
	}
	
	@RequestMapping(RedPacketPath.EXPORT)
	public void exportExcel(MeetRedPacketBillConditionBean bean, HttpServletRequest request, HttpServletResponse response) {
		logger.info("导出excel交易汇总表");

		try {
			List<MeetRedPacketBill> excels = redPacketService.exportRedPacket(bean);
			String[] headers = { "红包流水号", "收方客户编号", "收方客户姓名", "收方客户手机号", "发方客户编号", "红包类型", "红包名称","红包金额","红包状态","备注","关联id","发送时间","领取时间" };
			String fileName = DatetimeUtils.dateSecond() + "_红包流水.xls";
			Map<String, String> fileInfo = tradeBillService.exportExcel(excels, headers, fileName, "红包流水表", request);
			DownLoadUtil.downLoadFile(fileInfo.get("filePath"), response, fileInfo.get("fileName"), "xls");
			logger.info("导出excel红包流水成功");
		} catch (Exception e) {
			logger.error("导出excel红包流水表异常", e);
			e.printStackTrace();
		}

	}
}
