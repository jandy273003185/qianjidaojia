package com.qifenqian.bms.basemanager.merchantincontrol;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.basemanager.merchant.bean.Merchant;
import com.qifenqian.bms.basemanager.merchant.mapper.MerchantMapper;
import com.qifenqian.bms.basemanager.merchantincontrol.bean.MerchantInControl;
import com.qifenqian.bms.basemanager.merchantincontrol.service.TdMerchantInControlService;

@Controller
@RequestMapping(TdMerchantInControPath.BASE)
public class TdMerchantInController {
  private static Logger logger = LoggerFactory.getLogger(TdMerchantInController.class);

  @Autowired
  private TdMerchantInControlService service;
  @Autowired
  private MerchantMapper merchantMapper;

  @RequestMapping(TdMerchantInControPath.LIST)
  public ModelAndView list(MerchantInControl queryBean) {
    ModelAndView mv = new ModelAndView(TdMerchantInControPath.BASE + TdMerchantInControPath.LIST);
    mv.addObject("merchantInControlList",
        JSONObject.toJSON(service.selectMerchantInControlList(queryBean)));
    List<Merchant> merchantList = merchantMapper.selectMerchant();
    mv.addObject("merchantList", merchantList);
    mv.addObject("queryBean", queryBean);
    return mv;
  }

  @RequestMapping(TdMerchantInControPath.ADD)
  @ResponseBody
  public String add(MerchantInControl merchantInControl) {
    logger.info("增加商户交易渠道控制参数");
    JSONObject jsonObject = new JSONObject();
    try {
      service.insert(merchantInControl);
      jsonObject.put("result", "success");
    } catch (Exception e) {
      logger.error("增加商户交易渠道控制参数：", e);
      jsonObject.put("result", "fail");
      jsonObject.put("message", e.getMessage());
    }
    return jsonObject.toJSONString();
  }

  @RequestMapping(TdMerchantInControPath.UPDATE)
  @ResponseBody
  public String update(MerchantInControl merchantInControl) {
    logger.info("修改商户交易渠道控制参数");
    JSONObject jsonObject = new JSONObject();
    try {
      service.update(merchantInControl);
      jsonObject.put("result", "success");
    } catch (Exception e) {
      logger.error("修改商户交易渠道控制参数：", e);
      jsonObject.put("result", "fail");
      jsonObject.put("message", e.getMessage());
    }
    return jsonObject.toJSONString();
  }

  @RequestMapping(TdMerchantInControPath.DELETE)
  @ResponseBody
  public String delete(String custId) {
    logger.info("删除商户交易渠道控制参数");
    JSONObject jsonObject = new JSONObject();
    try {
      MerchantInControl merchantInControl = new MerchantInControl();
      merchantInControl.setCustId(custId);
      service.delete(merchantInControl);
      jsonObject.put("result", "success");
    } catch (Exception e) {
      logger.error("删除商户交易渠道控制参数：", e);
      jsonObject.put("result", "fail");
      jsonObject.put("message", e.getMessage());
    }
    return jsonObject.toJSONString();
  }

  @RequestMapping(TdMerchantInControPath.VALIDATE)
  @ResponseBody
  public String verify(MerchantInControl merchantInControl) {
    logger.info("校验商家账号");
    JSONObject jsonObject = new JSONObject();
    try {
      if (service.verify(merchantInControl) > 0) {
        jsonObject.put("result", "fail");
        jsonObject.put("message", "商户网关维护已存在");
      } else if (service.exists(merchantInControl) < 1) {
        jsonObject.put("result", "fail");
        jsonObject.put("message", "商户编号在系统中不存在");
      } else {
        jsonObject.put("result", "success");
      }

    } catch (Exception e) {
      logger.error("商家账号：", e);
      jsonObject.put("result", "fail");
      jsonObject.put("message", e.getMessage());
    }
    return jsonObject.toJSONString();
  }
}
