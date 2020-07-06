package com.sevenpay.agentmanager.biz.old.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.qifenqian.app.bean.UserLoginRelate;
import com.qifenqian.app.bean.customer.TdSalesmanInfo;
import com.qifenqian.app.bean.dto.UserDTO;
import com.qifenqian.app.customer.MerchantStatusService;
import com.qifenqian.app.customer.SalesmanManagerService;
import com.qifenqian.app.enterprise.finance.FinanceManageService;
import com.qifenqian.app.login.UserLoginManagerService;
import com.qifenqian.app.user.UserManager;
import com.sevenpay.agentmanager.biz.old.service.LoginServiceImpl;
import com.sevenpay.agentmanager.common.constants.CacheConstants;
import com.sevenpay.agentmanager.common.utils.redis.RedisUtils;
import com.sevenpay.agentmanager.common.utils.verfycode.VerifyInfoConstant;
import com.sevenpay.agentmanager.core.bean.ResultData;
import com.sevenpay.agentmanager.core.controller.AbstractBaseController;
import com.sevenpay.agentmanager.core.exception.BizException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 服务商/业务员
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户登陆相关Api")
public class LoginController extends AbstractBaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private LoginServiceImpl loginService;

    @Reference(version="${app.service.version}")
    private UserLoginManagerService loginManagerService;
    @Reference(version="${app.service.version}")
    private UserManager userManager;
    @Reference(version="${app.service.version}")
    private SalesmanManagerService salesmanManagerService;
    @Reference(version="${app.service.version}")
    private MerchantStatusService merchantStatusService;
    @Resource(name = "redisTemplate")
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    RedisUtils redisUtils;
    @Reference(version="${app.service.version}")
    FinanceManageService financeManageService;

    @ApiOperation(value = "清除登录失败缓存接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", paramType = "query", value = "账号", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", paramType = "query", value = "密码", required = true, dataType = "String"),
            @ApiImplicitParam(name = "openId", paramType = "query", value = "openId", required = true, dataType = "String"),
            @ApiImplicitParam(name = "roleCode", paramType = "query", value = "角色", required = true, dataType = "String")
    })
    @PostMapping(value = "/cleanBinding")
    public ResultData cleanBinding(String userName, String password, String openId, String roleCode) {
        String lockKey = CacheConstants.LOGIN_CHECK + roleCode + ":" + userName;
        String timeKey = CacheConstants.LOGIN_CHECK + roleCode + ":" + userName + ":" + "time";
        String dateKey = CacheConstants.LOGIN_CHECK + roleCode + ":" + userName + ":" + "date";
        redisUtils.delCacheWith(lockKey);
        redisUtils.delCacheWith(timeKey);
        redisUtils.delCacheWith(dateKey);
        return ResultData.success();
    }

    /**
     * 登陆
     *
     * @param userName 账号
     * @param password 密码
     * @param openId   微信用户唯一标识
     * @param roleCode 用户角色agent管理员（服务商）/salesman业务员
     * @return
     */
    @PostMapping("/loginBinding")
    public ResultData loginBinding(String userName, String password, String openId, String roleCode) {

        if (StringUtils.isBlank(openId)) {
            throw new BizException("参数openId不能为空");
        }
        if (StringUtils.isBlank(roleCode)) {
            throw new BizException("角色不能为空");
        }
        if (StringUtils.isBlank(userName)) {
            throw new BizException("手机号码不能为空");
        }
        if (StringUtils.isBlank(password)) {
            throw new BizException("密码不能为空");
        }
        return this.loginService.loginBinding(userName, password, openId, roleCode);
//
//        //分布式锁
////        String lockKey = CacheConstants.LOGIN_CHECK + roleCode + ":" + userName;
//        String timeKey = CacheConstants.LOGIN_CHECK + roleCode + ":" + userName + ":" + "time";
//        String dateKey = CacheConstants.LOGIN_CHECK + roleCode + ":" + userName + ":" + "date";
//
//        /**
//         * 判断是否之前已经操作错误
//         */
//        if (redisUtils.hasKey(dateKey)) {
//            //TODO 提示剩余时间
//            long total = Long.valueOf(redisUtils.getCacheObject(dateKey));
//            long current = new Date().getTime();
//            long m = (total - current / 1000) / 60;
//            return ResultData.error("请于" + m + "分钟后再试！");
//        }
//
//        /**
//         * 初始化登录校验失败次数
//         */
//        if (!redisUtils.hasKey(timeKey)) {
//            redisUtils.setCacheObject(timeKey, String.valueOf(5), 60 * 60 * 24l);
//        }
//        Integer time = Integer.valueOf(redisUtils.getCacheObject(timeKey));
//        /**
//         * 判断当前此时小于1，提示出冻结时间
//         */
//        if (time < 1) {
//            long dateTime = new Date().getTime() + 60 * 60 * 24 * 1000l;
//            redisUtils.setCacheObject(dateKey, String.valueOf(dateTime / 1000), dateTime / 1000);
//            return null;
//        }
//        boolean flag = true;
//        String msg = null;
//        try {
//            if ("agent".equalsIgnoreCase(roleCode)) {
//                loginService.agentBanding(userName, password, openId, roleCode);
//            } else if ("salesman".equalsIgnoreCase(roleCode)) {
//                loginService.salesmanBanding(userName, password, openId, roleCode);
//            } else if ("finance".equalsIgnoreCase(roleCode)) {
//                loginService.financeBanding(userName, password, openId, roleCode);
//            }
//        } catch (Exception e) {
//            msg = e.getMessage();
//            /**
//             * 记录登录校验错误次数
//             */
//            flag = false;
//            time--;
//            redisUtils.setCacheObject(timeKey, String.valueOf(time), 60 * 60 * 24l);
//            throw new BizException(msg);
//        } finally {
//            /**
//             * 清除登录者的key
//             */
////            redisUtils.delCacheWith(lockKey);
//        }
//        /**
//         * 登录成功清除锁
//         */
//        if (flag) {
//            redisUtils.delCacheWith(timeKey);
//            redisUtils.delCacheWith(dateKey);
//        }
//        return ResultData.success();
    }

    /**
     * 第一步登陆
     */
    @PostMapping("/login")
    public ResultData loginByOpenId(UserLoginRelate reqLoginBean) {
        if (StringUtils.isBlank(reqLoginBean.getOpenId())) {
            throw new BizException("openId不能为空!");
        }
        if (StringUtils.isBlank(reqLoginBean.getUserType())) {
            throw new BizException("角色异常,重新进入页面!");
        }
        if (StringUtils.isBlank(reqLoginBean.getUserId())) {
            throw new BizException("用户Id不能为空！");
        }
        //openId获取信息
        return loginService.loginByOpenId(reqLoginBean);
    }

    /**
     * 短信登陆
     *
     * @param mobile   管理员（服务商）手机号
     * @param roleCode 角色 agent
     * @param openId   openId
     */
    @PostMapping("/smsSpLogin")
    public ResultData smsSpLoginBanding(String mobile, String roleCode, String openId, String verifyCode) {
        if (StringUtils.isBlank(mobile)) {
            throw new BizException("手机号不能为空！");
        }
        if (StringUtils.isBlank(roleCode)) {
            throw new BizException("角色异常,请重新进入页面！");
        }
        if (StringUtils.isBlank(openId)) {
            throw new BizException("参数openId不能为空！");
        }
        if (StringUtils.isBlank(verifyCode)) {
            throw new BizException("验证码不能为空！");
        }
        return loginService.smsAgentBanding(mobile, roleCode, openId, verifyCode);
    }


    /**
     * 短信登陆
     *
     * @param mobile   财务员（服务商）手机号
     * @param roleCode 角色 finance
     * @param openId   openId
     */
    @PostMapping("/financeLogin")
    public ResultData financeLoginBanding(String mobile, String roleCode, String openId, String verifyCode) {
        if (StringUtils.isBlank(mobile)) {
            throw new BizException("手机号不能为空！");
        }
        if (StringUtils.isBlank(roleCode)) {
            throw new BizException("角色异常,请重新进入页面！");
        }
        if (StringUtils.isBlank(openId)) {
            throw new BizException("参数openId不能为空！");
        }
        if (StringUtils.isBlank(verifyCode)) {
            throw new BizException("验证码不能为空！");
        }
        return loginService.smsAgentBanding(mobile, roleCode, openId, verifyCode);
    }


    /**
     * 业务员短信登陆
     *
     * @param mobile   业务员手机号
     * @param roleCode 角色 salesman
     * @param openId   openId
     */
    @PostMapping("/smsSmLogin")
    public ResultData smsSmLoginBanding(String mobile, String roleCode, String openId, String verifyCode) {
        if (StringUtils.isBlank(mobile)) {
            throw new BizException("手机号不能为空！");
        }
        if (StringUtils.isBlank(roleCode)) {
            throw new BizException("角色异常,请重新进入页面！");
        }
        if (StringUtils.isBlank(openId)) {
            throw new BizException("参数openId不能为空！");
        }
        if (StringUtils.isBlank(verifyCode)) {
            throw new BizException("验证码不能为空！");
        }
        return loginService.smsSalesmanBanding(mobile, roleCode, openId, verifyCode);
    }


    /**
     * 根据角色查询绑定集合（未冻结）
     */
    @PostMapping(value = "/getBindingList")
    public ResultData getBindingList(String openId, String roleCode) {
        if (StringUtils.isBlank(openId)) {
            throw new BizException("openId不能为空！");
        }
        if (StringUtils.isBlank(roleCode)) {
            throw new BizException("角色异常,请重新进入页面！");
        }
        return loginService.getBindingList(openId, roleCode);
    }


    /**
     * 管理员（服务商）修改密码接口
     *
     * @param mobile   管理员（服务商）手机账号
     * @param roleCode 管理员角色标识（agent）
     * @param request
     * @return
     */
    @PostMapping("forgetPassword")
    public ResultData forgetPassword(String mobile, String roleCode, HttpServletRequest request) {
        if ("agent".equals(roleCode)) {
            //查询是否有该服务商的信息
            UserDTO userInfo = userManager.getUserByEmailOrMobile(mobile, roleCode);

            if (userInfo.getCustId() != null) {
                //获取redis中相对应的验证码
                String code = (String) redisTemplate.opsForValue().get(VerifyInfoConstant.FORGETPASSWORD_VERIFY_CODE + mobile);
                if (code == null) {
                    throw new BizException("验证码失效,请重新发送！");
                }
                String verifyCode = request.getParameter("verifyCode");
                if (StringUtils.isEmpty(verifyCode)) {
                    throw new BizException("请输入验证码");
                }
                if (code.equals(verifyCode)) {
                    //删除redis中对应的key
                    redisTemplate.delete(VerifyInfoConstant.FORGETPASSWORD_VERIFY_CODE + mobile);
                    return ResultData.success("验证码正确，开始修改密码");
                }
            }
            return ResultData.error("请检查管理员手机号是否输入正确！");
        } else if ("salesman".equals(roleCode)) {
            boolean b = salesmanManagerService.checkPhone(mobile, null);
            if (b) {//不存在
                throw new BizException("业务员手机号不正确");
            }
            //获取redis中相对应的验证码
            String code = (String) redisTemplate.opsForValue().get(VerifyInfoConstant.FORGETPASSWORD_VERIFY_CODE + mobile);
            if (code == null) {
                throw new BizException("验证码失效,请重新发送！");
            }
            String verifyCode = request.getParameter("verifyCode");
            if (StringUtils.isEmpty(verifyCode)) {
                throw new BizException("请输入验证码");
            }
            if (code.equals(verifyCode)) {
                //删除redis中对应的key
                redisTemplate.delete(VerifyInfoConstant.FORGETPASSWORD_VERIFY_CODE + mobile);
                return ResultData.success("验证码正确，开始修改密码");
            }
        }
        return ResultData.error("请重新进入登录页");
    }

    /**
     * 公共修改密码接口
     *
     * @param mobile   手机号
     * @param newPw    新密码
     * @param roleCode agent/salesman
     * @return
     */
    @PostMapping("roleCodeModifyPwd")
    public ResultData roleCodeModifyPwd(String mobile, String newPw, String roleCode) {
        if ("agent".equals(roleCode)) {
            String pw = userManager.updateUserPasswordByMobile(mobile, newPw, roleCode);
            if (pw == null) {
                return ResultData.error("修改密码失败");
            }
            return ResultData.success("修改密码成功");
        } else if ("salesman".equals(roleCode)) {
            TdSalesmanInfo tdSalesmanInfo = new TdSalesmanInfo();
            tdSalesmanInfo.setUserPhone(mobile);
            tdSalesmanInfo.setStatus("1");
            //查询手机号状态为启动的
            List<TdSalesmanInfo> tdSalesmanInfos = salesmanManagerService.listTdSalesmanInfos(tdSalesmanInfo);
            if (tdSalesmanInfos != null || tdSalesmanInfos.size() > 0) {
                TdSalesmanInfo t = tdSalesmanInfos.get(0);
                tdSalesmanInfo.setPassword(newPw);
                tdSalesmanInfo.setSalesmanId(t.getSalesmanId());
                Integer result = salesmanManagerService.updateTdSalesmanInfo(tdSalesmanInfo);
                if (result > 0) {
                    return ResultData.success("修改密码成功");
                }
            }
            return ResultData.error("修改密码失败");
        }
        return ResultData.error("请重新操作忘记密码");
    }

    @PostMapping("userLoginRelate")
    public ResultData userLoginRelate(HttpServletRequest request, String userId, String userType) {
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)) {
            throw new BizException("401", "token数据异常!");
        }
        if (StringUtils.isBlank(userId)) {
            throw new BizException("用户ID不能为空!");
        }
        if (StringUtils.isBlank(userType)) {
            throw new BizException("用户角色不能为空!");
        }
        return this.loginService.userLoginRelate(token, userId, userType);
    }

    @PostMapping("userLoginPw")
    public ResultData userLoginPw(HttpServletRequest request, String userId, String userType, String loginPw,
                                  String loginNewPw) {
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)) {
            throw new BizException("401", "token数据异常!");
        }
        if (StringUtils.isBlank(userId)) {
            throw new BizException("用户ID不能为空!");
        }
        if (StringUtils.isBlank(userType)) {
            throw new BizException("用户角色不能为空!");
        }
        if (StringUtils.isBlank(loginPw)) {
            throw new BizException("原密码不能为空~");
        }
        if (StringUtils.isBlank(loginNewPw)) {
            throw new BizException("新密码不能为空~");
        }
        return this.loginService.userLoginPw(token, userId, userType, loginPw, loginNewPw);
    }

    /**
     * 退出登录。
     *
     * @param request
     * @return
     */
    @PostMapping("loginOut")
    public ResultData loginOut(HttpServletRequest request) {
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)) {
            throw new BizException("401", "token数据异常");
        }
        return this.loginService.delTokenCache(token);
    }
}
