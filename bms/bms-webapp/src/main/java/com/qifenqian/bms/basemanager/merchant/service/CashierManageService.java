package com.qifenqian.bms.basemanager.merchant.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.qifenqian.bms.basemanager.merchant.bean.TdShopStaffRef;
import com.qifenqian.bms.basemanager.utils.GenSN;
import com.qifenqian.bms.basemanager.utils.MD5Security;
import com.qifenqian.bms.platform.web.admin.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.basemanager.merchant.bean.CashierInfo;
import com.qifenqian.bms.basemanager.merchant.bean.MerchantVo;
import com.qifenqian.bms.basemanager.merchant.bean.StoreManage;
import com.qifenqian.bms.basemanager.merchant.bean.TdLoginUserInfo;
import com.qifenqian.bms.basemanager.merchant.mapper.CashierManageMapper;
import com.qifenqian.bms.basemanager.merchant.mapper.MerchantMapper;
import com.qifenqian.bms.message.service.MessageManager;
import com.qifenqian.bms.platform.web.page.Page;


/**
 * 商户收银员
 *
 * @author dayet
 * @data 2015-06-8
 */
@Service
public class CashierManageService {
    @Autowired
    private CashierManageMapper cashierManageMapper;

    @Autowired
    private MerchantMapper merchantMapper;

    @Autowired
    private MessageManager message;

    /**
     * 收银员管理
     */
    @Page
    public List<CashierInfo> getCashierList(CashierInfo cashierInfo) {
        return cashierManageMapper.getCashierList(cashierInfo);

    }

    @Page
    public List<CashierInfo> getMyCashierList(CashierInfo cashierInfo) {
        return cashierManageMapper.getMyCashierList(cashierInfo);
    }

    public void addCashier(CashierInfo cashierInfo) {
        /**
         * 获取登录密码随机盐值
         */
        String loginSalt = GenSN.getRandomChar(6);
        /**
         * 计算入库登录密码
         */
        String loginPw = MD5Security.getMD5String(cashierInfo.getLoginPw() + loginSalt);
        /**
         * 获取退款盐值
         */
        String refundSalt = GenSN.getRandomChar(6);
        /**
         * 计算入库退款密码
         */
        String refundPw = MD5Security.getMD5String(cashierInfo.getRefundPw() + refundSalt);

        cashierInfo.setLoginPw(loginPw);
        cashierInfo.setLoginSalt(loginSalt);
        cashierInfo.setRefundPw(refundPw);
        cashierInfo.setRefundSalt(refundSalt);
        cashierInfo.setCreateId(String.valueOf(WebUtils.getUserInfo().getUserId()));
        cashierManageMapper.addCashier(cashierInfo);
        TdShopStaffRef tdShopStaffRef = new TdShopStaffRef();
        tdShopStaffRef.setId(GenSN.getSN());
        tdShopStaffRef.setShopId(cashierInfo.getShopId());
//        tdShopStaffRef.setCashierId(cashierInfo.getCashierCustId());
        tdShopStaffRef.setCashierId(cashierInfo.getOnlyId());
        tdShopStaffRef.setCustId(cashierInfo.getMerchantCustId());
        tdShopStaffRef.setStatus("1");
        tdShopStaffRef.setCreateId(String.valueOf(WebUtils.getUserInfo().getUserId()));
        cashierManageMapper.addShopStaffRef(tdShopStaffRef);
        // TODO 向优喜发送一个设置成功的通知

//        message.noticeCashier(getMessage(cashierInfo));


    }

    public void deleteCashier(String onlyId) {
        cashierManageMapper.deleteCashier(onlyId);
    }

    public TdLoginUserInfo getCustInfoByMobile(String mobile) {
        return cashierManageMapper.getCustInfoByMobile(mobile);
    }

    public void updateCashier(CashierInfo cashierInfo) {
        cashierManageMapper.updateCashier(cashierInfo);
    }

    public CashierInfo getMyCashierRef(String onlyId) {
        return cashierManageMapper.getMyCashierRef(onlyId);
    }

    public List<CashierInfo> getCashierListExcept(CashierInfo cashierInfo) {
        return cashierManageMapper.getCashierListExcept(cashierInfo);
    }

    public List<StoreManage> selectStore(String custId) {
        return cashierManageMapper.selectStore(custId);
    }

    private String getMessage(CashierInfo cashierInfo) {

        Map<String, String> tempMap = new HashMap<String, String>();
        tempMap.put("to", cashierInfo.getMerchantCustId());
        tempMap.put("mchName", cashierInfo.getMerchantName());
        tempMap.put("shopId", cashierInfo.getShopId());
        tempMap.put("shopName", cashierInfo.getShopName());
        tempMap.put("mchId", cashierInfo.getMerchantCustId());
        tempMap.put("cashierCustId", cashierInfo.getCashierCustId());
        tempMap.put("isApp", "isApp");

        Map<String, String> formatMsgMap = new HashMap<String, String>();
        formatMsgMap.put("mchId", cashierInfo.getMerchantCustId());
        formatMsgMap.put("mchName", cashierInfo.getMerchantName());
        formatMsgMap.put("shopId", cashierInfo.getShopId());
        formatMsgMap.put("shopName", cashierInfo.getShopName());
        formatMsgMap.put("payAmt", "0"); // 交易金额
        formatMsgMap.put("payType", "支付"); // 支付方式
        formatMsgMap.put("paySn", "a"); // 订单号
        formatMsgMap.put("payTime", "afd"); // 交易时间
        formatMsgMap.put("payMemo", "扫码支付"); // 备注
        formatMsgMap.put("goodsName", "test"); // 商品名称
        formatMsgMap.put("payStatus", "交易成功"); // 交易状态
        formatMsgMap.put("msgType", "trade"); // 消息类型
        formatMsgMap.put("speakText", ""); // 语音提示

        tempMap.put("text", JSONObject.toJSONString(formatMsgMap));

        return JSON.toJSONString(tempMap);

    }

    public boolean validate(String mobile,String onlyId) {

        CashierInfo cashierInfo = new CashierInfo();
        cashierInfo.setCashierMobile(mobile);
        cashierInfo.setOnlyId(onlyId);
        /**
         * 校验有效数据
         */
        cashierInfo.setStatus("1");
        List<CashierInfo> li = cashierManageMapper.getCashierList(cashierInfo);
        if (li != null && li.size() > 0) {
            return true;
        }

        MerchantVo merchantVo = new MerchantVo();
        merchantVo.setMobile(mobile);

        List<MerchantVo> l = merchantMapper.list(merchantVo);
        if (l != null && l.size() > 0) {
            return true;
        }
        return false;
    }


}
