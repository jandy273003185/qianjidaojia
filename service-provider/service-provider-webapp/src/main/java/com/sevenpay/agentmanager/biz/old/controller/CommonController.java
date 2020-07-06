package com.sevenpay.agentmanager.biz.old.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qifenqian.app.bean.Bank;
import com.qifenqian.app.bean.TbBankProvincesInfoBean;
import com.qifenqian.app.bean.TbProvincesInfoBean;
import com.qifenqian.app.bean.customer.TdSalesmanInfo;
import com.qifenqian.app.bean.dto.MailConfig;
import com.qifenqian.app.bean.dto.MessageDTO;
import com.qifenqian.app.bean.dto.UserDTO;
import com.qifenqian.app.common.BankInfoService;
import com.qifenqian.app.common.MessageManager;
import com.qifenqian.app.customer.MerchantInfoService;
import com.qifenqian.app.customer.SalesmanManagerService;
import com.qifenqian.app.user.UserManager;
import com.sevenpay.agentmanager.core.bean.ResultData;
import com.sevenpay.agentmanager.common.utils.GenSN;
import com.sevenpay.agentmanager.common.utils.verfycode.VerifyInfoConstant;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * User: JIANGZONGLIN
 * Date: 2019/11/14
 * Time: 19:59
 */
@RestController
@RequestMapping("common")
public class CommonController {

    @Reference(version="${app.service.version}")
    private MerchantInfoService merchantInfoService;

    @Reference(version="${app.service.version}")
    private BankInfoService bankInfoService;

    @Reference(version="${app.service.version}")
    private MessageManager messageManager;

    @Reference(version="${app.service.version}")
    private UserManager userManager;

    @Reference(version="${app.service.version}")
    private SalesmanManagerService salesmanManagerService;

    @Resource(name = "redisTemplate")
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 省市县三级下拉
     *
     * @param tbProvincesInfoBean
     * @return
     */
    @PostMapping("province")
    public ResultData provinceQuery(TbProvincesInfoBean tbProvincesInfoBean) {
        List<TbProvincesInfoBean> provinces = merchantInfoService.getProvinces(tbProvincesInfoBean);
        return ResultData.success(provinces);
    }

    /**
     * 银行省市二级下拉
     *
     * @param tbBankProvincesInfoBean
     * @return
     */
    @PostMapping("bankProvince")
    public ResultData provinceQuery(TbBankProvincesInfoBean tbBankProvincesInfoBean) {
        List<TbBankProvincesInfoBean> bankProvinces = merchantInfoService.getBankProvinces(tbBankProvincesInfoBean);
        return ResultData.success(bankProvinces);
    }

    /**
     * 查询总行（带模糊）
     *
     * @param bank
     * @return
     */
    @PostMapping("bankHeadOffice")
    public ResultData bankHeadOffice(Bank bank) {
        List<Bank> banks = bankInfoService.selectBanks(bank);
        return ResultData.success(banks);
    }

    /**
     * 查询支行（带模糊）
     *
     * @param bank
     * @return
     */
    @PostMapping("bankBranch")
    public ResultData bankBranch(Bank bank) {
        List<Bank> banks = bankInfoService.selectBranchBanks(bank);
        if (CollectionUtils.isEmpty(banks)) {
            Bank bankHeadOffice = bankInfoService.selectBankByBankCode(bank.getBankCode());
            bankHeadOffice.setBranchBankCode(bankHeadOffice.getBankCode());
            bankHeadOffice.setBranchBankName(bankHeadOffice.getBankName());
            banks.add(bankHeadOffice);
        }
        return ResultData.success(banks);
    }

    /**
     * 发送短信验证码登陆接口
     *
     * @param request
     * @param messageDTO
     * @return
     */
    @PostMapping("verifyCode")
    public ResultData verifyCode(HttpServletRequest request, MessageDTO messageDTO) {
        String mobile = request.getParameter("mobile");//手机号
        String roleCode = request.getParameter("roleCode");//角色（agent/salesman）
        String clientIp = request.getRemoteAddr();
        //生成验证码
        String smsVerifyCode = GenSN.getRandomNum(6);
        messageDTO.setFlag("login");
        messageDTO.setCode(smsVerifyCode);
        messageDTO.setClientIp(clientIp);
        messageDTO.setAddressee(mobile);
        if ("agent".equals(roleCode)) {
            //查询是否有该手机账号
            UserDTO userInfo = userManager.getUserByEmailOrMobile(mobile, roleCode);

            if (userInfo.getCustId() != null) {
                redisTemplate.opsForValue().set(VerifyInfoConstant.LOGIN_VERIFY_CODE + mobile, smsVerifyCode, 3 * 60, TimeUnit.SECONDS);
                MessageDTO m = messageManager.sendMobileCode(messageDTO);
                return ResultData.success("短信发送成功");
            }
        } else if ("salesman".equals(roleCode)) {
            //登录查询该业务员手机号是否有启用（只能有一个，该操作在业务员管理做了限制）
            TdSalesmanInfo tdSalesmanInfo1 = new TdSalesmanInfo();
            tdSalesmanInfo1.setUserPhone(mobile);
            List<TdSalesmanInfo> tdSalesmanInfos = salesmanManagerService.listTdSalesmanInfos(tdSalesmanInfo1);
            if (tdSalesmanInfos.size() > 0) {
                for (TdSalesmanInfo salesmanInfo : tdSalesmanInfos) {//假如多个服务商下都有该业务员
                    if ("1".equals(salesmanInfo.getStatus())) {
                        redisTemplate.opsForValue().set(VerifyInfoConstant.LOGIN_VERIFY_CODE + mobile, smsVerifyCode, 3 * 60, TimeUnit.SECONDS);
                        MessageDTO m = messageManager.sendMobileCode(messageDTO);
                        return ResultData.success("短信发送成功");
                    }
                }
                return ResultData.error("该业务员账号无效");
            }
        }

        return ResultData.error("请检查管理员手机号是否输入正确！");
    }

    /**
     * 管理员/业务员
     * 忘记密码接口
     *
     * @param request
     * @param messageDTO
     * @return
     */
    @PostMapping("modifyPassword")
    public ResultData modifyPassword(HttpServletRequest request, MessageDTO messageDTO) {
        String mobile = request.getParameter("mobile");//服务商（管理员）手机号
        String roleCode = request.getParameter("roleCode");//角色（agent）
        String clientIp = request.getRemoteAddr();
        //生成验证码
        String smsVerifyCode = GenSN.getRandomNum(6);
        messageDTO.setFlag("motify");
        messageDTO.setCode(smsVerifyCode);
        messageDTO.setClientIp(clientIp);
        messageDTO.setAddressee(mobile);
        messageDTO.setType("SMSLOGIN");
        if ("agent".equals(roleCode)) {
            //查询是否有该手机账号
            UserDTO userInfo = userManager.getUserByEmailOrMobile(mobile, roleCode);
            if (userInfo.getCustId() != null) {
                //redis存储验证码
                redisTemplate.opsForValue().set(VerifyInfoConstant.FORGETPASSWORD_VERIFY_CODE + mobile, smsVerifyCode, 3 * 60, TimeUnit.SECONDS);
                MessageDTO m = messageManager.sendMobileCode(messageDTO);
                return ResultData.success("短信发送成功");
            }
            return ResultData.error("请检查管理员手机号是否输入正确！");
        }
        if ("salesman".equals(roleCode)) {
            //登录查询该业务员手机号是否有启用（只能有一个，该操作在业务员管理做了限制）
            TdSalesmanInfo tdSalesmanInfo1 = new TdSalesmanInfo();
            tdSalesmanInfo1.setUserPhone(mobile);
            List<TdSalesmanInfo> tdSalesmanInfos = salesmanManagerService.listTdSalesmanInfos(tdSalesmanInfo1);
            for (TdSalesmanInfo tdSalesmanInfo : tdSalesmanInfos) {
                if ("1".equals(tdSalesmanInfo.getStatus())) {
                    //redis存储验证码
                    redisTemplate.opsForValue().set(VerifyInfoConstant.FORGETPASSWORD_VERIFY_CODE + mobile, smsVerifyCode, 3 * 60, TimeUnit.SECONDS);
                    MessageDTO m = messageManager.sendMobileCode(messageDTO);
                    return ResultData.success("短信发送成功");
                }
            }
            return ResultData.error("请检查业务员手机号是否输入正确！");
        }
        return ResultData.error("短信发送失败");
    }

    /**
     * 用户反馈中心
     *
     * @param request
     * @param response
     * @return
     */
    @PostMapping("cashierAdviseMsg")
    public ResultData processRequest(HttpServletRequest request, HttpServletResponse response, String adviseContent) {
        String adviserMobile = request.getParameter("adviserMobile");
        String email = "kefu@szgyzb.com";

        String[] tos = new String[]{email};
        MailConfig config = new MailConfig();
        config.setTos(tos);
        config.setSubject("七分钱服务商-业务员建议留言");
        config.setContent("<html><body><p>客户手机号码：" + adviserMobile + "</p><p> 客户建议留言:"
                + adviseContent + "</p>" + "</body></html>");
        boolean b = messageManager.doMailSend(config);
        if (b) {
            return ResultData.success("提交成功");
        }
        return ResultData.error("提交失败");

    }
}
