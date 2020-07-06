package com.qifenqian.bms.merchant.channel.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import com.qifenqian.bms.merchant.channel.bean.ChannelMerRegist;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.basemanager.custInfo.bean.TdCustInfo;
import com.qifenqian.bms.basemanager.custInfo.service.TdCustInfoService;
import com.qifenqian.bms.merchant.channel.bean.ChannelBean;
import com.qifenqian.bms.merchant.channel.bean.ChannelDetailBean;
import com.qifenqian.bms.merchant.channel.bean.ChannelStatus;
import com.qifenqian.bms.merchant.channel.service.ChannelService;
import com.seven.micropay.channel.enums.ChannelCode;
import com.seven.micropay.channel.enums.PayType;

/**
 * 商户在上级渠道通道信息的操作
 * 
 * @author Sun Xun
 *
 */
@Controller("MerchantChannelController")
@RequestMapping("/merchant/channel/")
public class ChannelController {

  private static Logger LOGGER = LoggerFactory.getLogger(ChannelController.class);

  @Autowired
  private ChannelService channelService;

  @Autowired
  private TdCustInfoService ts;

  /***
   * 渠道信息列表
   * 
   * @param queryBean
   * @return
   */
  @RequestMapping("list")
  public ModelAndView list(ChannelBean queryBean) {

    ModelAndView mv = new ModelAndView("/merchant/channel/channelList");

    String merchantCode = (queryBean == null || queryBean.getMerchantCode() == null) ? ""
        : queryBean.getMerchantCode();
    
    String custName = (queryBean == null || queryBean.getCustName() == null) ? ""
            : queryBean.getCustName();
    
    
    //商户编号查询
//    if (!"".equals(merchantCode)) {
//      TdCustInfo custInfo = new TdCustInfo();
//      custInfo.setMerchantCode(queryBean.getMerchantCode());
//      TdCustInfo tc = ts.selectByBean(custInfo);
//      if (tc != null) {
//        queryBean.setCustId(tc.getCustId());
//      } else {
//        return mv;
//      }
//    }else if(!"".equals(custName)) {
//		TdCustInfo custInfo = new TdCustInfo();
//	    custInfo.setCustName(queryBean.getCustName());
//	    TdCustInfo tc = ts.selectByBean(custInfo);
//	    if (tc != null) {
//	      queryBean.setCustId(tc.getCustId());
//	    } else {
//	      return mv;
//	    }
//    }

    List<ChannelBean> result = channelService.getChannels(queryBean);
    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug("Find Channel JSON: {}", JSONObject.toJSON(result));
    }
    mv.addObject("list", result);
    mv.addObject("queryBean", queryBean);
    mv.addObject("channelNames", getValidChannel());
    return mv;
  }

  /***
   * 渠道详情列表
   * 
   * @param queryBean
   * @return
   */
  @RequestMapping("detail")
  public ModelAndView details(ChannelDetailBean queryBean) {

    ModelAndView mv = new ModelAndView("/merchant/channel/channelDetailList");

    String merchantCode = queryBean == null ? ""
        : (queryBean.getMerchantCode() == null ? "" : queryBean.getMerchantCode());

    if (!"".equals(merchantCode)) {
      TdCustInfo custInfo = new TdCustInfo();
      custInfo.setMerchantCode(queryBean.getMerchantCode());
      TdCustInfo tc = ts.selectByBean(custInfo);
      if (tc != null) {
        queryBean.setCustId(tc.getCustId());
      } else {
        return mv;
      }
    }

    mv.addObject("list", channelService.getChannelDetails(queryBean));
    mv.addObject("queryBean", queryBean);
    return mv;
  }

  /***
   * 编辑渠道列表
   * 
   * @param queryBean
   * @return
   */
  @RequestMapping("edit")
  public ModelAndView edit(ChannelBean queryChannel) {

    ModelAndView mv = new ModelAndView("/merchant/channel/edit");

    /*
     * 查询出某个商户的通道信息 新增时 ,传入为值为空
     */
    String custId = queryChannel.getCustId();
    ChannelMerRegist channelName = queryChannel.getChannelName();
    String merchantChannelId = queryChannel.getMerchantChannelId();

    if (!isNullOrEmpty(custId) && !isNullOrEmpty(channelName.toString())
        && !isNullOrEmpty(merchantChannelId)) {

      ChannelBean channel =
          channelService.getChannel(custId, channelName.toString(), merchantChannelId);
      mv.addObject("channel", channel);
    }

    mv.addObject("channelNames", getValidChannel());
    mv.addObject("PayType", getValidPayType());
    mv.addObject("subPayType", PayType.values());

    return mv;
  }

  /**
   * 得到正在使用的支付类型
   * 
   * @return
   */
  private List<ChannelCode> getValidPayType() {
    List<ChannelCode> retVal = new ArrayList<ChannelCode>();
    retVal.add(ChannelCode.ALIPAY);
    retVal.add(ChannelCode.WEIXIN);
    retVal.add(ChannelCode.BESTPAY);
    // ChannelCode.values();
    return retVal;
  }

  /**
   * 得到有效果的渠道名
   * 
   * @return
   */
  private List<ChannelMerRegist> getValidChannel() {

    List<ChannelMerRegist> retValue = new ArrayList<ChannelMerRegist>();
		/*
		 * retValue.add(ChannelMerRegist.FM_COMBINEDPAY);
		 * retValue.add(ChannelMerRegist.KFT_PAY);
		 * retValue.add(ChannelMerRegist.QM_PAY); retValue.add(ChannelMerRegist.XL_PAY);
		 * retValue.add(ChannelMerRegist.PING_AN); retValue.add(ChannelMerRegist.GHXB);
		 * retValue.add(ChannelMerRegist.CR_ULOPAY);
		 * retValue.add(ChannelMerRegist.BEST_PAY);
		 * retValue.add(ChannelMerRegist.SUIXING_PAY);
		 * retValue.add(ChannelMerRegist.FM_UNIONPAY);
		 * retValue.add(ChannelMerRegist.SUM_PAY); retValue.add(ChannelMerRegist.YQB);
		 * retValue.add(ChannelMerRegist.ALIPAY); retValue.add(ChannelMerRegist.WX);
		 */
    for( ChannelMerRegist item:ChannelMerRegist.values()) {
    	retValue.add(item);
    };
    return retValue;
  }

  /***
   * 保存(更新)渠道信息
   * 
   * @param queryBean
   * @return
   */
  @RequestMapping("save")
  @ResponseBody
  public String save(HttpServletRequest request) {

    Map<String, String> result = new HashMap<String, String>();
    ChannelBean oldChannel = null;

    String data = getParam("data", request);
    String oldData = getParam("oldData", request);

    if (isNullOrEmpty(data)) {
      result.put("flag", "false");
      result.put("data", "请求数据为空");
      return JSON.toJSONString(result);
    }

    ChannelBean bean = JSON.parseObject(data, ChannelBean.class);

    if (!validationData(bean, result)) {
      return JSON.toJSONString(result);
    }

    if (!isNullOrEmpty(oldData)) {
      oldChannel = JSON.parseObject(oldData, ChannelBean.class);
      oldChannel = channelService.getChannel(oldChannel.getCustId(),
          oldChannel.getChannelName().getCode(), oldChannel.getMerchantChannelId());
    }

    if (!channelService.saveOrupdateChannel(bean, oldChannel)) {
      // 更新保存失败
      result.put("flag", "false");
      result.put("data", "保存或更新失败,请联系管理员");
      return JSON.toJSONString(result);
    }

    result.put("flag", "true");
    result.put("data", "保存或更新成功");

    return JSON.toJSONString(result);
  }

  /***
   * 激活通道
   * 
   * @param queryBean
   * @return
   */
  @RequestMapping("activate")
  @ResponseBody
  public String activate(HttpServletRequest request) {

    Map<String, String> result = new HashMap<String, String>();

    String data = getParam("data", request);

    if (isNullOrEmpty(data)) {
      result.put("flag", "false");
      result.put("data", "请求数据为空");
      return JSON.toJSONString(result);
    }

    ChannelBean bean = JSON.parseObject(data, ChannelBean.class);

    ChannelBean oldChannel = channelService.getChannel(bean.getCustId(),
        bean.getChannelName().name(), bean.getMerchantChannelId());

    if (!validationData(oldChannel, result)) {
      return JSON.toJSONString(result);
    }

    /*
     * if (hasActivatedChannel(oldChannel.getCustId())) { result.put("flag", "false");
     * result.put("data", "已经拥有一条已激活的通道!"); return JSON.toJSONString(result); }
     */

    if (!channelService.activeChannel(oldChannel)) {
      result.put("flag", "false");
      result.put("data", "激活通道失败,请联系管理员");
      return JSON.toJSONString(result);
    }

    result.put("flag", "true");
    result.put("data", "成功激活");

    return JSON.toJSONString(result);
  }


  @SuppressWarnings("unused")
  private boolean hasActivatedChannel(String custId) {
    boolean retval = false;

    ChannelBean bean = new ChannelBean();
    bean.setCustId(custId);
    List<ChannelBean> channels = channelService.getChannels(bean);
    for (ChannelBean item : channels) {
      if (item.getStatus() == ChannelStatus.NORMAL) {
        retval = true;
        break;
      }
    }
    return retval;
  }

  /***
   * 通道下线
   * 
   * @param queryBean
   * @return
   */
  @RequestMapping("deactivate")
  @ResponseBody
  public String deactivate(HttpServletRequest request) {

    Map<String, String> result = new HashMap<String, String>();

    String data = getParam("data", request);

    if (isNullOrEmpty(data)) {
      result.put("flag", "false");
      result.put("data", "请求数据为空");
      return JSON.toJSONString(result);
    }

    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug("deactivate request data:{}", data);
    }

    ChannelBean bean = JSON.parseObject(data, ChannelBean.class);

    ChannelBean oldChannel = channelService.getChannel(bean.getCustId(),
        bean.getChannelName().name(), bean.getMerchantChannelId());

    /*
     * if (!validationData(oldChannel, result)) { return JSON.toJSONString(result); }
     */

    if (!channelService.deactiveChannel(oldChannel)) {
      result.put("flag", "false");
      result.put("data", "下线通道失败,请联系管理员");

    }

    result.put("flag", "true");
    result.put("data", "成功反激活");

    return JSON.toJSONString(result);
  }

  /**
   * 验证并且 填充一些数据
   * 
   * @param bean
   * @return
   */
  private boolean validationData(ChannelBean bean, Map<String, String> result) {

    boolean retVal = true;

    if (isNullOrEmpty(bean.getMerchantCode())) {
      retVal = false;
      result.put("flag", "false");
      result.put("data", "商户号不为能空");
      return retVal;
    }

    if (isNullOrEmpty(bean.getChannelName().toString())) {
      retVal = false;
      result.put("flag", "false");
      result.put("data", "请选择通道");
      return retVal;
    }

    if (isNullOrEmpty(bean.getMerchantChannelId())) {
      retVal = false;
      result.put("flag", "false");
      result.put("data", "商户编号(渠道)不能为空");
      return retVal;
    }

    /**
     * 验证时排除几个通道. 华兴没有key , 翼支付也没有key
     */
    final ChannelMerRegist channelName = bean.getChannelName();
    if ( ( channelName != ChannelMerRegist.GHXB )
        || (channelName != ChannelMerRegist.BEST_PAY )  ) {
      if (isNullOrEmpty(bean.getMerchantChannelKey())) {
        retVal = false;
        result.put("flag", "false");
        result.put("data", "商户KEY(渠道)不能为空");
        return retVal;
      }
    }



    TdCustInfo queryBean = new TdCustInfo();
    queryBean.setMerchantCode(bean.getMerchantCode());
    TdCustInfo tc = ts.selectByBean(queryBean);
    if (tc != null) {
      bean.setCustId(tc.getCustId());
    } else {
      retVal = false;
      result.put("flag", "false");
      result.put("data", "无此商户号");
    }

    return retVal;
  }

  /**
   * 判断字符串 是否为空
   * 
   * @param str
   * @return
   */
  private boolean isNullOrEmpty(String str) {
    return str == null || "".equals(str);
  }

  private String getParam(String param, HttpServletRequest request) {

    if (!isNullOrEmpty(param)) {
      return request.getParameter(param);
    }
    return null;
  }

}
