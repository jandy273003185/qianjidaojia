package com.sevenpay.agentmanager.biz.old.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qifenqian.app.bean.TdCustInfo;
import com.qifenqian.app.bean.TdLoginUserInfo;
import com.qifenqian.app.bean.UserLoginRelate;
import com.qifenqian.app.bean.customer.FinanceInfo;
import com.qifenqian.app.bean.customer.RespBase;
import com.qifenqian.app.bean.customer.TdSalesmanInfo;
import com.qifenqian.app.bean.dto.UserDTO;
import com.qifenqian.app.bean.user.UpdatePwdReq;
import com.qifenqian.app.customer.MerchantInfoService;
import com.qifenqian.app.customer.MerchantStatusService;
import com.qifenqian.app.customer.SalesmanManagerService;
import com.qifenqian.app.enterprise.finance.FinanceManageService;
import com.qifenqian.app.login.UserLoginManagerService;
import com.qifenqian.app.user.TdLoginUserInfoService;
import com.qifenqian.app.user.UserManager;
import com.sevenpay.agentmanager.common.constants.CacheConstants;
import com.sevenpay.agentmanager.common.jwt.JWTUtil;
import com.sevenpay.agentmanager.common.pojo.LoginUser;
import com.sevenpay.agentmanager.common.utils.verfycode.VerifyInfoConstant;
import com.sevenpay.agentmanager.core.bean.ResultData;
import com.sevenpay.agentmanager.core.exception.BizException;
import com.sevenpay.agentmanager.core.service.BaseService;
import com.sevenpay.external.app.common.util.MD5Security;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * User: JIANGZONGLIN
 * Date: 2020/1/3
 * Time: 15:29
 */
@Service
public class LoginServiceImpl extends BaseService {

    @Reference(version = "${app.service.version}")
    private MerchantInfoService merchantInfoService;//查询商户接口
    @Reference(version = "${app.service.version}")
    private UserLoginManagerService loginManagerService;
    @Reference(version = "${app.service.version}")
    private UserManager userManager;
    @Reference(version = "${app.service.version}")
    private SalesmanManagerService salesmanManagerService;
    @Reference(version = "${app.service.version}")
    private MerchantStatusService merchantStatusService;
    @Reference(version = "${app.service.version}")
    private FinanceManageService financeManageService;//财务员接口

    @Reference(version = "${app.service.version}")
    private TdLoginUserInfoService tdLoginUserInfoService;


    /**
     * @description: 绑定账号时异常提示
     * @author: LiBin
     * @params [dateKey]
     * @date: 2020-01-14 15:02:21
     */
    private void returnMsgByFailDate(String dateKey) {
        Long total = redisUtils.getCacheObject(dateKey, Long.class);
        if (total != null) {
            long current = new Date().getTime();
            long m = (total - current / 1000) / 60;
            throw new BizException("请于" + m + "分钟后再试！");
        }
    }


    /**
     * @description: 绑定账号 针对openID
     * @author: LiBin
     * @params [userName, password, openId, roleCode]
     * @date: 2020-01-14 14:57:17
     */
    public ResultData loginBinding(String userName, String password, String openId, String roleCode) {
        /**
         * 分布式锁
         */
        String lockKey = CacheConstants.LOGIN_CHECK + roleCode + ":" + userName;
        String timeKey = CacheConstants.LOGIN_CHECK + roleCode + ":" + userName + ":" + "time";
        String dateKey = CacheConstants.LOGIN_CHECK + roleCode + ":" + userName + ":" + "date";
        Long dateValidate = 1800l;
        //TODO 提示剩余时间
        returnMsgByFailDate(dateKey);
        /**
         * 连点操作提示
         */
        boolean lock = redisUtils.addLock(lockKey, 60);
        if (!lock) {
            throw new BizException("请勿重复提交!");
        }
        Integer failTime = null;
        try {
            /**
             * 取出错误次数
             */
            failTime = redisUtils.getCacheObject(timeKey, Integer.class);
            /**
             * 如果为空初始化赋值五次
             */
            if (failTime == null) {
                failTime = 5;
                redisUtils.setCacheObject(timeKey, failTime, dateValidate);
            }
            if (failTime < 1) {
                long dateTime = new Date().getTime() + dateValidate * 1000l;
                redisUtils.setCacheObject(dateKey, String.valueOf(dateTime / 1000), dateValidate);
                returnMsgByFailDate(dateKey);
            }

            if ("agent".equalsIgnoreCase(roleCode)) {
                agentBanding(userName, password, openId, roleCode);
            } else if ("salesman".equalsIgnoreCase(roleCode)) {
                salesmanBanding(userName, password, openId, roleCode);
            } else if ("finance".equalsIgnoreCase(roleCode)) {
                financeBanding(userName, password, openId, roleCode);
            }
        } catch (Exception e) {
            failTime--;
            redisUtils.setCacheObject(timeKey, failTime, dateValidate);
            throw new BizException(e.getMessage());
        } finally {
            redisUtils.delCacheWith(lockKey);
        }
        redisUtils.delCacheWith(timeKey);
        redisUtils.delCacheWith(dateKey);
        return ResultData.success();
    }


    /**
     * 服务商绑定
     */
    public void agentBanding(String userName, String password, String openId, String roleCode) {
        /**
         * 校驗賬號密碼是否正確
         */
        UserDTO userInfo = userManager.login(userName, password, roleCode);
        if (!userInfo.isSuccess()) {
            throw new BizException("账号或密码错误");
        }
        //查询该账号是否绑定openId
        boolean isBinding = loginManagerService.LogincheckIsBinding(userName, roleCode);
        if (isBinding) {
            throw new BizException("该账号已经被绑定，请用之前微信登陆，如有疑问，请联系客服！");
        }

        UserLoginRelate ifbing = loginManagerService.selectEntOpenid(openId, roleCode, userName);
        if (ifbing != null) {
            ifbing.setIfUnbind("1");
            loginManagerService.updateBindingInfo(ifbing);
        } else {
            saveUserLoginRelate(userInfo.getCustId(), userInfo.getCustId(), openId, roleCode);
        }
    }

    /**
     * 业务员绑定
     */
    public void salesmanBanding(String userName, String password, String openId, String roleCode) {
        TdSalesmanInfo tdSalesmanInfo1 = new TdSalesmanInfo();
        tdSalesmanInfo1.setUserPhone(userName);
        tdSalesmanInfo1.setStatus("1");
        List<TdSalesmanInfo> tdSalesmanInfos = salesmanManagerService.listTdSalesmanInfos(tdSalesmanInfo1);
        if (CollectionUtils.isEmpty(tdSalesmanInfos)) {
            throw new BizException("该账号不存在！");
        }
        TdSalesmanInfo salesmanInfo = tdSalesmanInfos.get(0);
        TdSalesmanInfo userInfo = salesmanManagerService.checkSalesmanLogin(userName, password, salesmanInfo.getCustId());
        if (userInfo == null) {
            throw new BizException("账号或密码错误");
        }
        //查询该账号是否绑定openId
        boolean isBinding = loginManagerService.LogincheckIsBinding(userName, roleCode);
        if (isBinding) {
            throw new BizException("该账号已经被绑定，请用之前微信登陆，如有疑问，请联系客服！");
        }
        UserLoginRelate ifbing = loginManagerService.selectEntOpenid(openId, roleCode, userName);
        if (ifbing != null) {
            ifbing.setIfUnbind("1");
            loginManagerService.updateBindingInfo(ifbing);
        } else {
            saveUserLoginRelate(salesmanInfo.getSalesmanId(), salesmanInfo.getCustId(), openId, roleCode);
        }
    }

    private void saveUserLoginRelate(String userId, String custId, String openId, String roleCode) {
        UserLoginRelate ifbing = new UserLoginRelate();
        ifbing.setUserId(userId);
        ifbing.setCustId(custId);
        ifbing.setOpenId(openId);
        ifbing.setLoginType("1");
        ifbing.setUserType(roleCode);
        ifbing.setIfUnbind("1");
        loginManagerService.userBinding(ifbing);//用户绑定openId
    }

    /**
     * 财务员绑定
     */
    public void financeBanding(String userName, String password, String openId, String roleCode) {
        RespBase<FinanceInfo> financeInfoRespBase = financeManageService.financeLogin(userName, password);

        if (financeInfoRespBase == null) {
            throw new BizException("账号或密码错误");
        }

        FinanceInfo financeInfo = financeInfoRespBase.getObj();
        //查询该账号是否绑定openId
        boolean isBinding = loginManagerService.LogincheckIsBinding(userName, roleCode);
        if (isBinding) {
            throw new BizException("该账号已经被绑定，请用之前微信登陆，如有疑问，请联系客服！");
        }
        UserLoginRelate ifbing = loginManagerService.selectUserOpenidByRole(openId, roleCode, financeInfo.getFinanceId());//查询是否有绑定openId
        if (ifbing != null) {
            ifbing.setIfUnbind("1");
            loginManagerService.updateBindingInfo(ifbing);
        } else {
            saveUserLoginRelate(financeInfo.getFinanceId(), null, openId, roleCode);
        }
    }

    /**
     * 根据角色查询绑定集合（未冻结）
     */
    public ResultData getBindingList(String openId, String roleCode) {
        UserLoginRelate condition = new UserLoginRelate();
        condition.setOpenId(openId);
        condition.setUserType(roleCode);
        List<UserLoginRelate> userLoginRelates = null;
        switch (roleCode) {
            case "agent":
                userLoginRelates = loginManagerService.selectBindingInfo(condition);
                break;
            case "salesman":
                userLoginRelates = loginManagerService.selectSalesmanBindingInfo(condition);
                break;
            case "finance":
                userLoginRelates = loginManagerService.selectFinanceBindingInfo(condition);
                break;
            default:
                throw new BizException("角色异常,请重新进入页面！");
        }
        return ResultData.success(userLoginRelates);
    }

    /**
     * openId返回数据
     */
    public ResultData loginByOpenId(UserLoginRelate reqLoginBean) {
        List<UserLoginRelate> userLoginRelates = loginManagerService.selectUserOpenIdInfos(reqLoginBean);//查询是否有绑定openId

        if (CollectionUtils.isEmpty(userLoginRelates)) {
            throw new BizException("未绑定,无数据！");  //返回页面去登陆 进行绑定
        }

        //不同的角色获取不同的用户信息表
        LoginUser loginUser = new LoginUser();
        UserLoginRelate respInfo = userLoginRelates.get(0);
        loginUser.setUserId(respInfo.getUserId());
        if (respInfo == null) {
            throw new BizException("获取绑定信息失败,请重新进入页面!");
        }
        String userName = null;
        switch (reqLoginBean.getUserType()) {
            case "agent":
                TdCustInfo queryResult1 = merchantInfoService.getMerchantById(reqLoginBean.getUserId());
                userName = queryResult1.getCustName();
                loginUser.setUserInfo(queryResult1);
                break;
            case "salesman":
                TdSalesmanInfo salesmanInfo = salesmanManagerService.getTdSalesmanInfoById(respInfo.getUserId());
                userName = salesmanInfo.getUserName();
                loginUser.setUserInfo(salesmanInfo);
                break;
            case "finance":
                FinanceInfo financeInfo = financeManageService.queryFinanceInfoByFinanceId(respInfo.getUserId());
                userName = financeInfo.getFinanceName();
                loginUser.setUserInfo(financeInfo);
                break;
            default:
                throw new BizException("角色异常,请重新进入页面！");
        }
        loginUser.setUserName(userName);
        /**
         * 根据用户编号和密码加密生成token
         */
        String token = JWTUtil.sign(respInfo.getUserId(), respInfo.getOpenId());
        loginUser.setToken(token);

        String md5Token = MD5Security.getMD5String(token + CacheConstants.TOKEN_MD5_SECRET);
        redisUtils.setCacheObject(CacheConstants.TOKEN_MD5_KEY + md5Token, md5Token, 3600 * 24 * 2l);
        return ResultData.success(loginUser);
    }

    /**
     * 短信登陆绑定(agent)
     */
    public ResultData smsAgentBanding(String mobile, String roleCode, String openId, String verifyCode) {
        String code = redisUtils.getCacheObject(VerifyInfoConstant.LOGIN_VERIFY_CODE + mobile);
        if (code == null) {
            throw new BizException("验证码失效,请重新发送！");
        }
        //登陆校验
        if (!code.equals(verifyCode)) {
            return ResultData.error("验证码不正确！");
        }
        //查询是否有该服务商的信息
        UserDTO userInfo = userManager.getUserByEmailOrMobile(mobile, roleCode);
        if (userInfo == null) {
            throw new BizException("请检查手机号是否正确!");
        }
        //获取存入redis中的手机登录验证码
        LoginUser loginUser = new LoginUser();

        //查询该账号是否绑定openId
        boolean isBinding = loginManagerService.LogincheckIsBinding(mobile, roleCode);
        if (isBinding) {
            throw new BizException("该账号已经被绑定，请用之前微信登陆，如有疑问，请联系客服！");
        }
        UserLoginRelate ifbing = loginManagerService.selectUserOpenidByRole(openId, roleCode, userInfo.getCustId());//查询是否有绑定openId
        if (ifbing != null) {//修改绑定信息
            ifbing.setIfUnbind("1");
            loginManagerService.updateBindingInfo(ifbing);
            loginUser.setUserInfo(userInfo.getCustId());
            //根据用户编号和密码加密生成token
            String token = JWTUtil.sign(userInfo.getCustId(), openId);
            loginUser.setToken(token);
            redisUtils.del(VerifyInfoConstant.LOGIN_VERIFY_CODE + mobile);
        } else {//新增绑定信息

            saveUserLoginRelate(userInfo.getCustId(), userInfo.getCustId(), openId, roleCode);
            loginUser.setUserInfo(userInfo.getCustId());
            //根据用户编号和密码加密生成token
            String token = JWTUtil.sign(userInfo.getCustId(), openId);
            loginUser.setToken(token);
            redisUtils.del(VerifyInfoConstant.LOGIN_VERIFY_CODE + mobile);
        }
        return ResultData.success(loginUser);
    }

    /**
     * 短信登陆绑定(salesman)
     */
    public ResultData smsSalesmanBanding(String mobile, String roleCode, String openId, String verifyCode) {
        //4、判断该手机号验证码操作是否匹配
        String code = redisUtils.getCacheObject(VerifyInfoConstant.LOGIN_VERIFY_CODE + mobile);
        if (code == null) {
            throw new BizException("验证码失效,请重新发送！");
        }
        if (!code.equals(verifyCode)) {
            return ResultData.error("验证码不正确！");
        }
        LoginUser loginUser = new LoginUser();
        //1、查询是否有该业务员手机号
        TdSalesmanInfo tdSalesmanInfo = new TdSalesmanInfo();
        tdSalesmanInfo.setUserPhone(mobile);
        List<TdSalesmanInfo> tdSalesmanInfos = salesmanManagerService.listTdSalesmanInfos(tdSalesmanInfo);
        for (TdSalesmanInfo salesmanInfo : tdSalesmanInfos) {
            //2、查询该业务员在哪个商户下是启用状态
            if ("1".equals(salesmanInfo.getStatus())) {
                //3、查询该业务员账号是否有绑定过，如果有则修改
                boolean isBinding = loginManagerService.LogincheckIsBinding(mobile, roleCode);
                if (isBinding) {
                    throw new BizException("该账号已经被绑定，请用之前微信登陆，如有疑问，请联系客服！");
                }
                UserLoginRelate ifbing = loginManagerService.selectUserOpenidByRole(openId, roleCode, salesmanInfo.getSalesmanId());//查询是否有绑定过openId

                //5、修改绑定信息
                if (ifbing != null) {
                    ifbing.setIfUnbind("1");
                    loginManagerService.updateBindingInfo(ifbing);

                } else {
                    saveUserLoginRelate(salesmanInfo.getSalesmanId(), salesmanInfo.getCustId(), openId, roleCode);
                }
                loginUser.setUserId(salesmanInfo.getSalesmanId());
                //根据用户编号和密码加密生成token
                String token = JWTUtil.sign(salesmanInfo.getSalesmanId(), openId);
                loginUser.setToken(token);
                loginUser.setPhoneCode(salesmanInfo.getUserPhone());
                redisUtils.del(VerifyInfoConstant.LOGIN_VERIFY_CODE + mobile);
                return ResultData.success(loginUser);
            }
        }
        return ResultData.error("请检查业务员手机号是否输入正确！");
    }

    /**
     * 短信登陆绑定(finance)
     */
    public ResultData smsFinanceBanding(String mobile, String roleCode, String openId, String verifyCode) {
        //4、判断该手机号验证码操作是否匹配
        String code = redisUtils.getCacheObject(VerifyInfoConstant.LOGIN_VERIFY_CODE + mobile);
        if (code == null) {
            throw new BizException("验证码失效,请重新发送！");
        }
        if (!code.equals(verifyCode)) {
            return ResultData.error("验证码不正确！");
        }
        //1、校验账号是否存在且有效
        boolean validate = financeManageService.validate(mobile, null);
        if (!validate) {
            return ResultData.error("手机号不正确，请重新输入");
        }
        //登录成功
        FinanceInfo f = new FinanceInfo();
        f.setFinanceMobile(mobile);
        List<FinanceInfo> cashierList = financeManageService.getFinanceList(f);
        for (FinanceInfo financeInfo : cashierList) {
            if ("1".equals(financeInfo.getStatus())) {
                //返回参数
                LoginUser loginUser = new LoginUser();
                //生成token访问权限
                String token = JWTUtil.sign(financeInfo.getFinanceId(), mobile);//财务编号+账号
                //查询财务员信息
                FinanceInfo financeInfo1 = financeManageService.queryFinanceInfoByFinanceId(financeInfo.getFinanceId());
                loginUser.setPhoneCode(mobile);
                loginUser.setToken(token);
                loginUser.setUserId(financeInfo.getFinanceId());
                loginUser.setUserInfo(financeInfo1);
//                TdCustInfo merchantById = merchantInfoService.getMerchantById(financeInfo1.getCustId());
                //查询该账号是否绑定openId
                /**
                 * 暂时只允许一个微信绑定
                 */
                boolean isBinding = loginManagerService.LogincheckIsBinding(mobile, roleCode);
                if (isBinding) {
                    throw new BizException("该账号已经被绑定，请用之前微信登陆，如有疑问，请联系客服！");
                }
                //查询
                UserLoginRelate ifbing = loginManagerService.selectUserOpenidByRole(openId, roleCode, financeInfo.getFinanceId());//查询是否有绑定openId
                if (ifbing != null) {
                    ifbing.setIfUnbind("1");
                    loginManagerService.updateBindingInfo(ifbing);
                } else {
                    saveUserLoginRelate(financeInfo1.getFinanceId(), null, openId, roleCode);
                }
                return ResultData.success(loginUser);
            }
        }
        return ResultData.error("请检查该账号是否正确或冻结");
    }

    public ResultData delTokenCache(String token) {
        String md5Token = MD5Security.getMD5String(token + CacheConstants.TOKEN_MD5_SECRET);
        redisUtils.delCacheWith(CacheConstants.TOKEN_MD5_KEY + md5Token);
        return ResultData.success();
    }

    public ResultData userLoginRelate(String token, String userId, String userType) {
        try {
            UserLoginRelate userLoginRelate = new UserLoginRelate();
            userLoginRelate.setIfUnbind("0");
            userLoginRelate.setUserId(userId);
            userLoginRelate.setUserType(userType);
            loginManagerService.updateBindingInfo(userLoginRelate);
            this.delTokenCache(token);
            return ResultData.success("201", "解绑成功!");
        } catch (Exception e) {
            return ResultData.error();
        }
    }

    public ResultData userLoginPw(String token, String userId, String userType, String loginPw, String loginNewPw) {
        try {
            boolean result = false;
            if ("finance".equalsIgnoreCase(userType)) {
                result = financeManageService.updateFinancePw(userId, null, null, loginPw, loginNewPw);
            } else if ("salesman".equalsIgnoreCase(userType)) {
                int m = this.salesmanManagerService.updateTdSalesmanInfoPassword(userId, loginPw, loginNewPw);
                if (m > 0) {
                    result = true;
                }
            } else if ("agent".equalsIgnoreCase(userType)) {
                /**
                 * userId === 手机号
                 */
                TdLoginUserInfo tdLoginUserInfo = tdLoginUserInfoService.queryMobileByCustId(userId);
                if (tdLoginUserInfo != null) {
                    UpdatePwdReq updatePwdReq = new UpdatePwdReq();
                    updatePwdReq.setCustId(userId);
                    updatePwdReq.setNewPwd(loginNewPw);
                    updatePwdReq.setOldPwd(loginPw);
                    updatePwdReq.setRole("agent");
                    Integer pw = tdLoginUserInfoService.updateLoginPwd(updatePwdReq);
                    if (pw == 1) {
                        result = true;
                    } else {
                        return ResultData.error("原密码错误");
                    }
                }

            }
            if (result) {
                delTokenCache(token);
                return ResultData.success("201", "修改密码成功!");
            } else {
                return ResultData.error("原密码错误");

            }
        } catch (Exception e) {
            return ResultData.error(e.getMessage());
        }
    }
}
