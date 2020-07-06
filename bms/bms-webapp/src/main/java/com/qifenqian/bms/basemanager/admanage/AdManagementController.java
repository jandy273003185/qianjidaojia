package com.qifenqian.bms.basemanager.admanage;


import java.util.HashMap;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.basemanager.admanage.bean.AdAllCustInfoVO;
import com.qifenqian.bms.basemanager.admanage.bean.AdCustInfoVO;
import com.qifenqian.bms.basemanager.admanage.bean.AdManagement;
import com.qifenqian.bms.basemanager.admanage.bean.ShopAdDO;
import com.qifenqian.bms.basemanager.admanage.bean.TdMachineAdvert;
import com.qifenqian.bms.basemanager.admanage.service.AdManagementService;
import com.qifenqian.bms.basemanager.admanage.service.ShopAdService;
import com.qifenqian.bms.basemanager.merchant.bean.Merchant;
import com.qifenqian.bms.basemanager.merchant.mapper.MerchantMapper;

@Controller
@RequestMapping(AdManagementPath.BASE)
public class AdManagementController {
    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(AdManagementController.class);

    @Value("${ADMANAGE_FILE_SAVE_PATH}")
    private String updatePath;


    @Autowired
    private AdManagementService adManagementService;
    @Autowired
    private ShopAdService shopAdService;
    @Autowired
	private MerchantMapper merchantMapper;
    /**
     * 显示广告信息列表
     *
     * @param queryBean
     * @return
     */
    @RequestMapping(AdManagementPath.LIST)
    public ModelAndView list(AdManagement queryBean) {
        ModelAndView mv = new ModelAndView(AdManagementPath.BASE + AdManagementPath.LIST);
        List<AdManagement> adManagementList = adManagementService.selectAdManagementListBy(queryBean);
        List<Merchant> merchantList = merchantMapper.selectMerchant();
        mv.addObject("adList", JSONObject.toJSON(adManagementList));
        mv.addObject("queryBean", queryBean);
        mv.addObject("merchantList", merchantList);
        return mv;
    }

    /**
     * 添加APP登录广告页信息
     */
    @RequestMapping(AdManagementPath.ADD)
    @ResponseBody
    public String saveAdManagement(AdManagement adManagement) {
        JSONObject json = new JSONObject();
        json.put("result", "FALSE");
        if (StringUtils.isBlank(adManagement.getAdName())) {
            json.put("message", "广告名称不能为空!");
            return json.toString();
        }
        if (StringUtils.isBlank(adManagement.getType())) {
            json.put("message", "广告类型不能为空!");
            return json.toString();
        }
        if (StringUtils.isBlank(adManagement.getShowTime())) {
            json.put("message", "广告播放时长不能为空!");
            return json.toString();
        }
        if (StringUtils.isBlank(adManagement.getImagePath())) {
            json.put("message", "上传路径不能为空!");
            return json.toString();
        }
        if (StringUtils.isBlank(adManagement.getUrl())) {
            json.put("message", "广告链接不能为空!");
            return json.toString();
        }
        String result = this.adManagementService.saveAdManagement(adManagement);
        json.put("result", result);
        return json.toString();
    }

    /**
     * 更新APP登录页广告信息
     */
    @RequestMapping(AdManagementPath.UPDATE)
    @ResponseBody
    public String updateAdManagement(AdManagement adManagement) {
        JSONObject json = new JSONObject();
        json.put("result", "FALSE");
        if (StringUtils.isBlank(adManagement.getAdName())) {
            json.put("message", "广告名称不能为空!");
            return json.toString();
        }
        if (StringUtils.isBlank(adManagement.getType())) {
            json.put("message", "广告类型不能为空!");
            return json.toString();
        }
        if (StringUtils.isBlank(adManagement.getShowTime())) {
            json.put("message", "广告播放时长不能为空!");
            return json.toString();
        }
        if (StringUtils.isBlank(adManagement.getImagePath())) {
            json.put("message", "上传路径不能为空!");
            return json.toString();
        }
        if (StringUtils.isBlank(adManagement.getUrl())) {
            json.put("message", "广告链接不能为空!");
            return json.toString();
        }
        String result = this.adManagementService.updateAdManagement(adManagement);
        json.put("result", result);
        return json.toString();
    }

    /**
     * 删除APP登录页广告信息
     *
     * @param adId
     * @return
     */
    @RequestMapping(AdManagementPath.DELETE)
    @ResponseBody
    public String deleteAdManageInfo(String adId) {
        JSONObject json = new JSONObject();
        json.put("result", new HashMap<>());
        return json.toString();
    }


    /**
     * @Author LiBin
     * @Description
     * @Param
     * @Return
     * @Date 2019/12/12 0012 15:36
     */
    @RequestMapping(AdManagementPath.DISTRIBUTION)
    @ResponseBody
    public String distributionShopAd(@RequestBody ShopAdDO shopAdDO) {
    	logger.info("开始下发广告----------------------");
        JSONObject json = new JSONObject();
        json.put("result", "FALSE");
        if (null == shopAdDO) {
            json.put("message", "参数不能为空!");
            return json.toString();
        }
        if (CollectionUtils.isEmpty(shopAdDO.getAdDOList())) {
            json.put("message", "广告信息不能为空!");
            return json.toString();
        }
        json =  this.shopAdService.distributionShopAd(shopAdDO);
        return json.toString();
    }

    /**
     * 分配广告给商户门店
     *
     * @return
     */
    @RequestMapping(AdManagementPath.ADCUSTOMLIST)
    @ResponseBody
    public String findAdCustomList(String customName) {
        JSONObject json = new JSONObject();
        List<AdCustInfoVO> adCustomList = this.shopAdService.findAdCustomList(customName);
        json.put("result", "SUCCESS");
        json.put("data", JSONObject.toJSON(adCustomList));
        return json.toString();
    }

    /**
     * 分配广告给商户门店
     *
     * @return
     */
    @RequestMapping(AdManagementPath.ADALLCUSTOMLIST)
    @ResponseBody
    public String findAdAllCustomList(String customName, String shopName) {
        JSONObject json = new JSONObject();
        if (null != customName) {
            customName = customName.trim();
        }
        if (null != shopName) {
            shopName = shopName.trim();
        }
        List<AdAllCustInfoVO> adAllCustInfoVOList = this.shopAdService.findAdAllCustomList(customName, shopName);
        json.put("result", "SUCCESS");
        json.put("data", JSONObject.toJSON(adAllCustInfoVOList));
        return json.toString();
    }
    
    /**
     * 显示广告信息列表
     *
     * @param queryBean
     * @return
     */
    @RequestMapping("/machineAdvertList")
    public ModelAndView list(TdMachineAdvert queryBean) {
    	
        ModelAndView mv = new ModelAndView();
    	String startTime = null;//起止最大时间
		String endTime = null;//结束时间
		if (!StringUtils.isBlank(queryBean.getStartTime())) {
			startTime = queryBean.getStartTime().trim();
			queryBean.setStartTime(startTime+" 00:00:00");
		}
		if (!StringUtils.isBlank(queryBean.getEndTime())) {
			endTime = queryBean.getEndTime().trim();
			queryBean.setEndTime(endTime+" 23:59:59");
		}
		if (!StringUtils.isBlank(queryBean.getCustName())) {
			queryBean.setCustName(queryBean.getCustName().trim());
		}
		if (!StringUtils.isBlank(queryBean.getCustId())) {
			queryBean.setCustId(queryBean.getCustId().trim());
		}
		
        List<TdMachineAdvert> adManagementAdvertList = adManagementService.selecTdMachineAdvertListBy(queryBean);
     //   List<Merchant> merchantList = merchantMapper.selectMerchant();
        mv.addObject("adManagementAdvertList", JSONObject.toJSON(adManagementAdvertList));
        queryBean.setStartTime(startTime);
        queryBean.setEndTime(endTime);
        mv.addObject("queryBean", queryBean);
     //   mv.addObject("merchantList", merchantList);
        return mv;
    }
    
}
