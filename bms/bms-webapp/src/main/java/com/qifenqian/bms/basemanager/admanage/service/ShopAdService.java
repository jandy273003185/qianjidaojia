package com.qifenqian.bms.basemanager.admanage.service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.basemanager.admanage.bean.AdAllCustInfoVO;
import com.qifenqian.bms.basemanager.admanage.bean.AdCustInfoVO;
import com.qifenqian.bms.basemanager.admanage.bean.AdDO;
import com.qifenqian.bms.basemanager.admanage.bean.MchShopDO;
import com.qifenqian.bms.basemanager.admanage.bean.ShopAd;
import com.qifenqian.bms.basemanager.admanage.bean.ShopAdDO;
import com.qifenqian.bms.basemanager.admanage.bean.TdMachineAdvert;
import com.qifenqian.bms.basemanager.admanage.dao.ShopAdDao;
import com.qifenqian.bms.basemanager.utils.GenSN;
import com.qifenqian.bms.platform.web.admin.utils.WebUtils;

@Service
public class ShopAdService {

    @Autowired
    private ShopAdDao shopAdDao;
    
    @Value("${ADVERT_URL}")
	private String urlPath;

    public JSONObject distributionShopAd(ShopAdDO shopAdDO) {
    	JSONObject json = new JSONObject();
        /**
         * 取出广告ID
         */
        List<AdDO> adIds = shopAdDO.getAdDOList();
        /**
         * 取出商户门店信息
         */
        List<MchShopDO> mchShopDOList = shopAdDO.getMchShopDOList();
        if (CollectionUtils.isEmpty(mchShopDOList)) {
            mchShopDOList = this.findMchShopDOList(mchShopDOList);
        }
        if (CollectionUtils.isEmpty(mchShopDOList)) {
        	json.put("result", "FALSE");
        	json.put("message", "商户信息为空");
            return json;
        }
        ShopAd shopAd = null;
        List<ShopAd> list = new ArrayList<>();
        //将之前图片状态改为失效
        TdMachineAdvert tdMachineAdvert = new TdMachineAdvert();
        for (AdDO adDO : adIds) {
        	tdMachineAdvert.setCustId(mchShopDOList.get(0).getMchId());
        	if((adDO.getMachineType() != null) && (adDO.getSequence() != null)) {
        		tdMachineAdvert.setMachineType(adDO.getMachineType());
                tdMachineAdvert.setSequence(adDO.getSequence());
                tdMachineAdvert.setState("01");
        		shopAdDao.updateTdMachineAdvert(tdMachineAdvert);
        	}
        }
		//插入新的图片
        for (MchShopDO mchShopDO : mchShopDOList) {
        	
            for (AdDO adDO : adIds) {
                shopAd = new ShopAd();
                //设置ID为UUID生成的32位随机数
                shopAd.setShopAdId(GenSN.getSN());
                //设置当前时间为创建时间
                shopAd.setCreateTime(new Date());
                //将当前登录用户的ID设置放入创建者的字段中
                shopAd.setCreateId(String.valueOf(WebUtils.getUserInfo().getUserId()));
                //设置广告
                shopAd.setAdId(adDO.getAdId());
                shopAd.setSortNo(adDO.getSortNo());
                //设置商户
                shopAd.setMchId(mchShopDO.getMchId());
                //设置门店
                shopAd.setShopId(mchShopDO.getShopId());
                list.add(shopAd);
                //添加设备主页广告
                int machineAdvert =findTdMachineAdvertList(shopAd,adDO.getSequence(),adDO.getMachineType());
                if(machineAdvert < 1) {
                	json.put("result", "FALSE");
                	json.put("message", "下发失败");
                    return json;
                }
            }
        }
        if (CollectionUtils.isEmpty(list)) {
        	json.put("result", "FALSE");
        	json.put("message", "门店设置广告为空");
            return json;
        }
        try {
            shopAdDao.batchDeleteShopAd(mchShopDOList);
            shopAdDao.batchSaveShopAd(list);
            json.put("result", "SUCCESS");
            return json;
        } catch (Exception e) {
            e.printStackTrace();
            json.put("result", "FALSE");
        	json.put("message", "添加失败");
        }
        return json;
    }
    
    
    
    /**
     * 	根据广告ID获取查询图片详细信息
     * @param mchShopDO
     * @return
     */
    private int findTdMachineAdvertList(ShopAd shopAd,String sequence,String machineType){
    	TdMachineAdvert tdMachineAdvert = new TdMachineAdvert();
    	
        tdMachineAdvert.setCustId(shopAd.getMchId());
        //根据custId查询对应下面的设备
//        List<String> terminalNoList = this.shopAdDao.selectTerminalNoByCustId(tdMachineAdvert.getCustId());
//		if(terminalNoList.size() <= 0) {
//			return 10;
//		}
        //根据广告ID查询出广告路径
        String pictureUrl = this.shopAdDao.selectPictureUrlByAdId(shopAd.getAdId());
        String url = urlPath +pictureUrl;
        tdMachineAdvert.setPicture(url);
        tdMachineAdvert.setSequence(sequence);
        tdMachineAdvert.setMachineType(machineType);
        tdMachineAdvert.setCreator(String.valueOf(WebUtils.getUserInfo().getUserId()));
        tdMachineAdvert.setState("00");
        tdMachineAdvert.setCreateTime(new Date());
        int a = this.shopAdDao.saveTdMachineAdvert(tdMachineAdvert);
		/*
		 * for(int i=0;i<terminalNoList.size();i++) {
		 * tdMachineAdvert.setTerminalNo(terminalNoList.get(i)); int a =
		 * this.shopAdDao.saveTdMachineAdvert(tdMachineAdvert); }
		 */
        return a;
    	
    }
    /**
     * @Author LiBin
     * @Description 如果没有商户 则全选商户门店
     * @Param
     * @Return
     * @Date 2019/12/12 0012 22:08
     */
    private List<MchShopDO> findMchShopDOList(List<MchShopDO> mchShopDOList) {
        if (null == mchShopDOList) {
            mchShopDOList = new ArrayList<>();
        }
        List<AdAllCustInfoVO> adAllCustInfoVOList = findAdAllCustomList(null, null);
        if (CollectionUtils.isEmpty(adAllCustInfoVOList)) {
            return mchShopDOList;
        }
        MchShopDO mchShopDO = null;
        for (AdAllCustInfoVO adAllCustInfoVO : adAllCustInfoVOList) {
            mchShopDO = new MchShopDO();
            mchShopDO.setMchId(adAllCustInfoVO.getCustId());
            mchShopDO.setShopId(adAllCustInfoVO.getShopId());
            mchShopDOList.add(mchShopDO);
        }
        return mchShopDOList;
    }

    public List<AdCustInfoVO> findAdCustomList(String customName) {
        return this.shopAdDao.selectCustInfoOnDistributionAd(customName);
    }

    public List<AdAllCustInfoVO> findAdAllCustomList(String customName, String shopName) {
        return this.shopAdDao.findAdAllCustomList(customName, shopName);
    }
}
