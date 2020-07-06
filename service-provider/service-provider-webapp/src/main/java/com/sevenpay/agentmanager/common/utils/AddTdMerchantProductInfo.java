package com.sevenpay.agentmanager.common.utils;

import com.alibaba.fastjson.JSONArray;
import com.qifenqian.app.bean.TdMerchantProductInfo;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * 进件产品参数转换
 */
public class AddTdMerchantProductInfo {

    public static List<TdMerchantProductInfo> add(HttpServletRequest request,String custId) throws ParseException {
        return addTdMerchantProductInfo(request,custId);
    }

        private static List<TdMerchantProductInfo> addTdMerchantProductInfo(HttpServletRequest request,String custId) throws ParseException {
        //扫码
            String productInfos = request.getParameter("productInfos");
            if (productInfos == null) {
                return null;
            }
            List<TdMerchantProductInfo> merchantProductInfos = jsonToList(productInfos, TdMerchantProductInfo.class);//productId、productRate、sn
            for (TdMerchantProductInfo tdMerchantProductInfo : merchantProductInfos) {
                tdMerchantProductInfo.setMchCustId(custId);//商户编号
                tdMerchantProductInfo.setModifyTime(new Date());
                tdMerchantProductInfo.setCreateTime(new Date());//创建时间
                tdMerchantProductInfo.setProductStatus("01");//默认开通待审核状态 01
            }
        return merchantProductInfos;
    }
    /**
     * json 转 List<T>
     */
    public static <T> List<T> jsonToList(String jsonString, Class<T> clazz) {
        List<T> ts = (List<T>) JSONArray.parseArray(jsonString, clazz);
        return ts;
    }

}
