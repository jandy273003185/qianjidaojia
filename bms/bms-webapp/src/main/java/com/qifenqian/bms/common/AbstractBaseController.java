package com.qifenqian.bms.common;

import com.alibaba.fastjson.JSON;
import com.qifenqian.bms.basemanager.merchant.bean.MerchantVo;
import com.qifenqian.bms.basemanager.merchant.bean.PicturePath;
import com.qifenqian.bms.basemanager.merchant.service.MerchantEnterService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 公共基础controller
 */
public abstract class AbstractBaseController {
    @Autowired
    protected MerchantEnterService merchantEnterService;


    /**
     * 获取商户的图片信息
     * @param custId
     * @param authId
     * @return
     */
    protected PicturePath getPicturePath(String custId, String authId) {

        MerchantVo merchantVo = new MerchantVo();
        merchantVo.setAuthId(authId);
        merchantVo.setCustId(custId);
        PicturePath picturePath = merchantEnterService.getPicPath(merchantVo);
        if (picturePath == null) {
            picturePath = new PicturePath();
        }
        return picturePath;
    }

    protected String getPicturePathJsonStr(String custId, String authId) {
        PicturePath picturePath = this.getPicturePath(custId,authId);
        return JSON.toJSONString(picturePath);
    }

}
