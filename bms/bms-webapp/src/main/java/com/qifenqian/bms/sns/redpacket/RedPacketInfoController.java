package com.qifenqian.bms.sns.redpacket;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.sns.redpacket.bean.RedEnvelopeInfo;
import com.qifenqian.bms.sns.redpacket.service.RedPacketInfoService;
import com.qifenqian.bms.sns.redpacketdetail.bean.RedPacketDetail;

@Controller
@RequestMapping(RedPacketInfoPath.BASE)
public class RedPacketInfoController {
	
	private static Logger logger = LoggerFactory.getLogger(RedPacketInfoController.class);
	@Autowired
	private  RedPacketInfoService redPacketInfoService;
	
	/**
	 * 红包列表查詢
	 * 
	 * @param refundBill
	 * @return
	 */
	@RequestMapping(RedPacketInfoPath.LIST)
	public ModelAndView list(RedEnvelopeInfo queryBean) {
		logger.info("红包列表查詢请求对象：  [{}]", JSONObject.toJSONString(queryBean, true));
		ModelAndView mv = new ModelAndView(RedPacketInfoPath.BASE + RedPacketInfoPath.LIST);
		List<RedEnvelopeInfo> redEnvelopeList = redPacketInfoService.selectList(queryBean);
		mv.addObject("redEnvelopeList", JSONObject.toJSON(redEnvelopeList));
		mv.addObject("queryBean", queryBean);
		return mv;
	}
	
	
	/**
	 * 红包明细
	 * @param queryBean
	 * @return
	 */
	@RequestMapping(RedPacketInfoPath.QUERY_REDPACKET_DETAIL)
	public ModelAndView queryRedPacketDetail(RedPacketDetail queryBean) {
		logger.info("红包详细列表查詢请求对象：  [{}]", JSONObject.toJSONString(queryBean, true));
		if(StringUtils.isBlank(queryBean.getRedEnvId())){
			throw new IllegalArgumentException("红包编号为空");
		}
		ModelAndView mv = new ModelAndView(RedPacketInfoPath.BASE + RedPacketInfoPath.QUERY_REDPACKET_DETAIL);
		List<RedPacketDetail> detailList = redPacketInfoService.selectRedPacketDetailByRedEnvId(queryBean);
		mv.addObject("detailList", JSONObject.toJSON(detailList));
		mv.addObject("queryBean", queryBean);
		return mv;
	}
	
	
	/**
	 * 
	 * @param queryBean
	 * @return
	 */
	@RequestMapping(RedPacketInfoPath.REDPACKET_DETAIL_LIST)
	public ModelAndView redPacketDetailList(RedPacketDetail queryBean) {
		logger.info("红包详细列表查詢请求对象：  [{}]", JSONObject.toJSONString(queryBean, true));
		
		ModelAndView mv = new ModelAndView(RedPacketInfoPath.BASE + RedPacketInfoPath.REDPACKET_DETAIL_LIST);
		List<RedPacketDetail> redDetailList = redPacketInfoService.selectRedPacketDetailList(queryBean);
		mv.addObject("redDetailList", JSONObject.toJSON(redDetailList));
		mv.addObject("queryBean", queryBean);
		return mv;
	}
}
