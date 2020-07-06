package com.qifenqian.bms.basemanager.merchant;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.basemanager.custInfo.service.TdCustInfoService;
import com.qifenqian.bms.basemanager.merchant.bean.CashierInfo;
import com.qifenqian.bms.basemanager.merchant.bean.Merchant;
import com.qifenqian.bms.basemanager.merchant.bean.StoreManage;
import com.qifenqian.bms.basemanager.merchant.mapper.MerchantMapper;
import com.qifenqian.bms.basemanager.merchant.service.CashierManageService;
import com.qifenqian.bms.basemanager.utils.GenSN;
import com.qifenqian.bms.common.util.RedisUtil;
import com.qifenqian.bms.platform.web.admin.utils.WebUtils;

import redis.clients.jedis.Jedis;

/**
 * 收银员管理
 *
 * @author Xun Sun
 */
@Controller
@RequestMapping("/merchant/cashierManage")
public class CashierManageController {

    @Autowired
    private CashierManageService cashierManageService;

    @Autowired
    private MerchantMapper merchantMapper;

    @Autowired
    private TdCustInfoService tdCustInfoService;

    @RequestMapping("/list")
    public ModelAndView list(CashierInfo queryBean) {
        ModelAndView mv = new ModelAndView(CashierManagePath.BASE + CashierManagePath.LIST);
        String userId = String.valueOf(WebUtils.getUserInfo().getUserId());
        // 是否有权限查看所有订单
        boolean isAllList = tdCustInfoService.isAllList(userId);
        List<CashierInfo> list = null;
        if (isAllList) {
            list = cashierManageService.getCashierList(queryBean);
        } else {
            queryBean.setUserId(userId);
            queryBean.setUserName(WebUtils.getUserInfo().getUserName());
            list = cashierManageService.getMyCashierList(queryBean);
        }
        List<Merchant> merchantList = merchantMapper.selectMerchant();
        mv.addObject("cashierInfoList", JSONObject.toJSON(list));
        mv.addObject("queryBean", queryBean);
        mv.addObject("merchantList", merchantList);
        return mv;
    }

    @RequestMapping(CashierManagePath.CHECK)
    @ResponseBody
    public String list(String custId) {
        JSONObject jo = new JSONObject();
        List<StoreManage> storeManageList = cashierManageService.selectStore(custId);
        if (storeManageList == null) {
            jo.put("result", "FAIL");
        } else {
            jo.put("result", "SUCCESS");
            jo.put("storeManageList", storeManageList);
        }
        return jo.toJSONString();
    }

    @RequestMapping("/add")
    @ResponseBody
    public String add(CashierInfo cashierInfo) {
        //TODO 添加约束条件
        JSONObject jo = new JSONObject();

        if (StringUtils.isBlank(cashierInfo.getLoginPw())) {
            jo.put("result", "FAIL");
            jo.put("message", "请设置登录密码！");
            return jo.toString();
        }
        if (StringUtils.isBlank(cashierInfo.getRefundPw())) {
            jo.put("result", "FAIL");
            jo.put("message", "请设置退款密码！");
            return jo.toString();
        }

        if (validateCashier(cashierInfo.getCashierMobile(),cashierInfo.getOnlyId())) {
            jo.put("result", "FAIL");
            jo.put("message", "该手机号不能设置为本店收银员, 请检查该手机是否已经成为别的门店收银员,或者商户帐号！");
            return jo.toString();
        }

//    TdLoginUserInfo loginUserInfo =  cashierManageService.getCustInfoByMobile(cashierInfo.getCashierMobile());
//    if (loginUserInfo == null) {
//      jo.put("result", "FAIL");
//      jo.put("message", "该手机号未注册为七分钱用户！");
//      return jo.toString();
//    }
        /**
         * 设置筛选有效的重复数据
         */
        cashierInfo.setStatus("1");
        List<CashierInfo> list = cashierManageService.getCashierList(cashierInfo);
        if (list != null && list.size() != 0) {
            jo.put("result", "FAIL");
            jo.put("message", "该手机号已注册成该商户的收银员！");
            return jo.toString();
        }
//    cashierInfo.setCashierCustId(loginUserInfo.getCustId());
        cashierInfo.setOnlyId(GenSN.getSN());
        cashierInfo.setCreateId(WebUtils.getUserInfo().getUserId() + "");
        try {
            cashierManageService.addCashier(cashierInfo);
            // merchantCustId 商户编号
            //this.delKey(cashierInfo.getMerchantCustId(), RedisUtil.MERCHANT_CASHIER_REF_DB);
            jo.put("result", "SUCCESS");
            jo.put("message", "添加成功");
        } catch (Exception e) {
            jo.put("result", "FAIL");
            jo.put("message", e.getMessage());
        }
        return jo.toString();
    }

    @RequestMapping(CashierManagePath.DELETE)
    @ResponseBody
    public String delete(String onlyId) {
        JSONObject jo = new JSONObject();
        try {

            CashierInfo cashierInfo = cashierManageService.getMyCashierRef(onlyId);
            if (cashierInfo != null) {

                cashierManageService.deleteCashier(onlyId);
//                this.delKey(cashierInfo.getMerchantCustId(), RedisUtil.MERCHANT_CASHIER_REF_DB);
                jo.put("result", "SUCCESS");
                jo.put("message", "删除成功！");
            } else {
                jo.put("result", "FAIL");
                jo.put("message", "收银员不存在！");
            }
            return jo.toString();
        } catch (Exception e) {
            jo.put("result", "FAIL");
            jo.put("message", e.getMessage());
        }
        return jo.toString();
    }

    @RequestMapping(CashierManagePath.UPDATE)
    @ResponseBody
    public String update(CashierInfo cashierInfo) {
        JSONObject jo = new JSONObject();

        String status = cashierInfo.getStatus();
        if (StringUtils.isBlank(status)) {
            jo.put("result", "FAIL");
            jo.put("message", "是否有效不能为空！");
            return jo.toString();
        }
        /**
         * 如果修改为有效数据,需校验手机号是否已有其他有效数据
         */
        if ("1".equalsIgnoreCase(status)) {
            if (validateCashier(cashierInfo.getCashierMobile(),cashierInfo.getOnlyId())) {
                jo.put("result", "FAIL");
                jo.put("message", "该手机号不能设置为本店收银员, 请检查该手机是否已经成为别的门店收银员,或者商户帐号！");
                return jo.toString();
            }
        }

        cashierInfo.setModifyId(WebUtils.getUserInfo().getUserId() + "");
        try {
//            TdLoginUserInfo loginUserInfo =
//                    cashierManageService.getCustInfoByMobile(cashierInfo.getCashierMobile());
//            if (loginUserInfo == null) {
//                jo.put("result", "FAIL");
//                jo.put("message", "该手机号未注册为七分钱用户！");
//                return jo.toString();
//            }
//            cashierInfo.setCashierCustId(loginUserInfo.getCustId());
//            List<CashierInfo> list = cashierManageService.getCashierListExcept(cashierInfo);
//            if (list != null && list.size() != 0) {
//                jo.put("result", "FAIL");
//                jo.put("message", "该手机号已注册成该商户的收银员！");
//                return jo.toString();
//            }
            cashierManageService.updateCashier(cashierInfo);
            if (cashierInfo != null)
//                this.delKey(cashierInfo.getMerchantCustId(), RedisUtil.MERCHANT_CASHIER_REF_DB);
            jo.put("result", "SUCCESS");
            jo.put("message", "修改成功！");
            return jo.toString();
        } catch (Exception e) {
            jo.put("result", "FAIL");
            jo.put("message", e.getMessage());
        }
        return jo.toString();
    }


    /**
     * 验证是否可以设置为收银员
     *
     * @param mobile
     * @return
     */
    private boolean validateCashier(String mobile,String onlyId) {
        return cashierManageService.validate(mobile,onlyId);
    }

    // XXXX.delkey(merchantId+"_cashier_ref",RedisUtil.MERCHANT_CASHIER_REF_DB)
    public void delKey(String key, int db) {
        Jedis jedis = RedisUtil.getJedis();
        try {


            jedis.select(db);
            /*
             * Set<String> str = jedis.keys(key+"_cashier_ref");
             * System.out.println(key+"_cashier_ref-------------str:"+str);
             */
            jedis.del(key + "_cashier_ref"); // 删除key
            /*
             * Set<String> strs = jedis.keys(key+"_cashier_ref");
             * System.out.println(key+"_cashier_ref-------------str:"+"strs:"+strs);
             */
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            RedisUtil.returnResource(jedis);
        }
    }
}
