package com.sevenpay.agentmanager.common.utils;


import com.alibaba.fastjson.JSONArray;
import com.qifenqian.app.bean.TdCustScanCopy;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.List;

/**
 * 进件添加图片路径转换
 */
public class AddCustScanCopy{

    public static List<TdCustScanCopy> add(HttpServletRequest request,String custId) throws ParseException {
        return addCustScanCopy(request,custId);
    }

    private static List<TdCustScanCopy> addCustScanCopy(HttpServletRequest request,String custId) throws ParseException {
        //01 税务登记证  02 营业执照 03 开户证件 04法人身份证正面  05 银行卡扫描件 06 其他证件 07 银行卡正面 08 店面门头照
    	//11行业资质照 12电子签名照 13 手持身份证正面 14  银行卡反面  15授权函   16 法人身份证反面  18店内照21 店面前台照  22 合作证明函 23 其他资料照1 
    	//24 其他资料照2 30结算人身份证正面 31结算人身份证反面32公众号页面截图33小程序页面截图34APP截图35网站授权函36联系人信息确认二维码
    	//37经营场所证明文件 
        String custScanCopys = request.getParameter("custScanCopys");
        if (custScanCopys == null) {
            return null;
        }
        List<TdCustScanCopy> tdCustScanCopies = jsonToList(custScanCopys, TdCustScanCopy.class);//类型certifyType和路径scanCopyPath及证件号certifyNo
        for (TdCustScanCopy tdCustScanCopy : tdCustScanCopies) {
            tdCustScanCopy.setCustId(custId);//商户编号
            tdCustScanCopy.setCreateId(request.getParameter("userId"));//创建人（服务商、业务员）
            tdCustScanCopy.setCertifyEndTime(request.getParameter("certifyEndTime"));//证件有效期
            tdCustScanCopy.setStatus("00");//状态
        }
        return tdCustScanCopies;
    }
    /**
     * json 转 List<T>
     */
    public static <T> List<T> jsonToList(String jsonString, Class<T> clazz) {
        List<T> ts = (List<T>) JSONArray.parseArray(jsonString, clazz);
        return ts;
    }
}
