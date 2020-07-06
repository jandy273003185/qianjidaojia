package com.qifenqian.bms.v2.biz.customer.custinfo.service;

import com.github.pagehelper.PageInfo;
import com.qifenqian.bms.basemanager.Constant;
import com.qifenqian.bms.basemanager.custInfo.bean.TbPasswordModifyLog;
import com.qifenqian.bms.basemanager.custInfo.bean.TdCustInfo;
import com.qifenqian.bms.basemanager.custInfo.bean.TdCustInfoExcel;
import com.qifenqian.bms.basemanager.custInfo.bean.TdLoginUserInfo;
import com.qifenqian.bms.basemanager.custInfo.mapper.TbPasswordModifyLogMapper;
import com.qifenqian.bms.basemanager.custInfo.mapper.TdCustInfoMapper;
import com.qifenqian.bms.basemanager.custInfo.service.TdCustInfoService;
import com.qifenqian.bms.basemanager.trade.service.DownLoadUtil;
import com.qifenqian.bms.basemanager.trade.service.TradeBillService;
import com.qifenqian.bms.basemanager.utils.DatetimeUtils;
import com.qifenqian.bms.basemanager.utils.GenSN;
import com.qifenqian.bms.basemanager.utils.MD5Security;
import com.qifenqian.bms.expresspay.CommonService;
import com.qifenqian.bms.platform.web.admin.user.bean.User;
import com.qifenqian.bms.platform.web.admin.utils.WebUtils;
import com.qifenqian.bms.v2.biz.customer.custinfo.bean.SendMessageAO;
import com.qifenqian.bms.v2.common.mvc.bean.ResultData;
import com.qifenqian.bms.v2.common.mvc.bizexception.BizException;
import com.qifenqian.bms.v2.common.mvc.service.BaseService;
import com.sevenpay.invoke.transaction.querybankcard.bean.BankCard;
import com.sevenpay.plugin.IPlugin;
import com.sevenpay.plugin.message.bean.MessageBean;
import com.sevenpay.plugin.message.bean.MessageColumnValues;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @author LvFeng
 * @Description: 客户管理列表
 * @date 2020/4/28 18:40
 */
@Service
public class BizTdCustInfoService extends BaseService {
    @Autowired
    private TdCustInfoMapper custInfoMapper;

    @Autowired
    private TradeBillService tradeBillService;
    @Autowired
    private TdCustInfoService custInfoService;
    @Autowired
    private TbPasswordModifyLogMapper tbPasswordModifyLogMapper;
    @Autowired
    private CommonService commonService;


    public PageInfo<TdCustInfo> list(TdCustInfo custInfo) {
        List<TdCustInfo> tdCustInfos = custInfoMapper.selectCustInfos(custInfo);
        return new PageInfo<>(tdCustInfos);
    }

    public void exportExcel(TdCustInfo custInfo, HttpServletRequest request, HttpServletResponse response) {
        try {
            List<TdCustInfoExcel> excels = custInfoMapper.exportCustInfos(custInfo);

            String[] headers = {"客户编号", "客户名称", "客户邮箱", "手机号码", "实名认证等级", "状态", "创建时间", "七分宝账号", "七分钱账号", "证件名称", "证件号码", "地址", "备注", "权限创建时间", "权限编号"};
            String fileName = DatetimeUtils.dateSecond() + "_用户列表信息.xls";
            Map<String, String> fileInfo = tradeBillService.exportExcel(excels, headers, fileName, "消费者列表", request);
            DownLoadUtil.downLoadFile(fileInfo.get("filePath"), response, fileInfo.get("fileName"), "xls");
            logger.info("导出excel消费者列表成功");
        } catch (Exception e) {
            logger.error("导出excel消费者列表异常", e);
            throw new RuntimeException(e);
        }
    }


    public ResultData update(TdCustInfo custInfo) {
        try {
            custInfoService.editTdCustInfo(custInfo);
            logger.info("客户修改完成！");
        } catch (Exception e) {
            logger.error("修改客户未成功", e);
            throw new BizException(e.getMessage());
        }
        return ResultData.success();
    }

    public ResultData reset(TdCustInfo oldcustInfo) {
        ResultData resultData = null;
        TbPasswordModifyLog log = new TbPasswordModifyLog();
        try {
            TdCustInfo custInfo = custInfoService.selectById(oldcustInfo.getCustId());
            String tradeCode = GenSN.getRandomNum(6);
            String tradeCodeMD5 = MD5Security.getMD5String(oldcustInfo.getCustId() + tradeCode + custInfo.getAttachStr());
            custInfo.setTradePwd(tradeCodeMD5);

            /**客户密码修改记录*/
            User user = WebUtils.getUserInfo();
            String id = GenSN.getSN();
            log.setId(id);
            log.setCustId(custInfo.getCustId());
            log.setCreateId(String.valueOf(user.getUserId()));
            log.setStatus(Constant.PASSWORD_MODIFY_FAIL);
            tbPasswordModifyLogMapper.insert(log);

            int updateInt = custInfoService.updateInfo(custInfo);

            if (updateInt != 1) {
                resultData = ResultData.error("重置支付密码失败");
                log.setStatus(Constant.PASSWORD_MODIFY_FAIL);
            } else {
                resultData = ResultData.success(tradeCode);
                log.setStatus(Constant.PASSWORD_MODIFY_SUCCESS);
            }
            tbPasswordModifyLogMapper.updateByPrimaryKey(log);

        } catch (Exception e) {
            logger.error("重置支付密码异常", e);
            throw new BizException(e.getMessage());
        }
        return ResultData.success();
    }

    public ResultData sendmessage(SendMessageAO sendMessageAO) {
        IPlugin plugin = commonService.getIPlugin();
        MessageBean messageBean = new MessageBean();
        messageBean.setContent(sendMessageAO.getContent());
        messageBean.setMsgType(MessageColumnValues.MsgType.SMS);
        messageBean.setBusType(MessageColumnValues.busType.MANUAL_HANDLING);
        String[] tos = new String[]{sendMessageAO.getMobile()};
        messageBean.setTos(tos);

        boolean falg = plugin.sendMessage(MessageColumnValues.MsgType.SMS, messageBean);
        if (falg) {
            logger.info("发送信息成功！");
            return ResultData.success();
        } else {
            logger.error("发送信息失败");
            return ResultData.error("发送信息失败");
        }
    }

    public List<BankCard> bankCard(TdCustInfo custInfo) {
        List<BankCard> bankCardList = null;
        try {
            bankCardList = custInfoService.queryBankCardList(custInfo.getCustId());
        } catch (Exception e) {
            logger.error("查询客户信息未成功", e);
            throw new BizException(e.getMessage());
        }
        return bankCardList;
    }

    public ResultData syncSendMessage(SendMessageAO sendMessageAO) {
        TdCustInfo custInfo = custInfoService.selectById(sendMessageAO.getCustId());
        TdLoginUserInfo loginInfo = custInfoService.selectTdLoginInfo(sendMessageAO.getCustId());


        IPlugin plugin = commonService.getIPlugin();
        MessageBean messageBean = new MessageBean();
        if (null != loginInfo) {
            try {
                if ("bindingEmail".equals(sendMessageAO.getType())) {

                    if (StringUtils.isEmpty(loginInfo.getEmail())) {
                        throw new IllegalArgumentException("该用户未绑定邮箱");
                    }
                    messageBean.setMsgType(MessageColumnValues.MsgType.EMAIL);
                    messageBean.setBusType(MessageColumnValues.busType.RESET_TRADE_PWD);
                    messageBean.setContent("亲爱的七分钱用户：" + custInfo.getCustName() + ":你好,你的七分钱支付密码已重置为:" + sendMessageAO.getTradeCode() + ",请及时登陆七分钱更改密码,保障信息安全！");
                    String[] tos = new String[]{loginInfo.getEmail()};
                    messageBean.setTos(tos);
                    plugin.sendMessage(MessageColumnValues.MsgType.EMAIL, messageBean);

                }
                if ("byUser".equals(sendMessageAO.getType())) {
                    if (!StringUtils.isEmpty(sendMessageAO.getEmail())) {
                        messageBean.setMsgType(MessageColumnValues.MsgType.EMAIL);
                        messageBean.setBusType(MessageColumnValues.busType.RESET_TRADE_PWD);
                        messageBean.setContent("亲爱的七分钱用户：" + custInfo.getCustName() + ":你好,你的七分钱支付密码已重置为:" + sendMessageAO.getTradeCode() + ",请及时登陆七分钱更改密码,保障信息安全！");
                        String[] tos = new String[]{sendMessageAO.getEmail()};
                        messageBean.setTos(tos);
                        plugin.sendMessage(MessageColumnValues.MsgType.EMAIL, messageBean);
                    }

                    if (!StringUtils.isEmpty(sendMessageAO.getMobile())) {
                        messageBean.setMsgType(MessageColumnValues.MsgType.SMS);
                        messageBean.setBusType(MessageColumnValues.busType.RESET_TRADE_PWD);
                        messageBean.setContent("亲爱的七分钱用户：" + custInfo.getCustName() + ":你好,你的七分钱支付密码已重置为:" + sendMessageAO.getTradeCode() + ",请及时登陆七分钱更改密码,保障信息安全！");
                        String[] tos = new String[]{sendMessageAO.getMobile()};
                        messageBean.setTos(tos);
                        plugin.sendMessage(MessageColumnValues.MsgType.SMS, messageBean);
                    }
                }
                return ResultData.success();
            } catch (Exception e) {
                logger.error("重置密码信息通知异常", e);
                throw new BizException(e.getMessage());
            }

        } else {
            return ResultData.error();
        }
    }

    public TdCustInfo queryAccount(TdCustInfo queryBean) {
        TdCustInfo custInfo = null;
        try {
            custInfo = custInfoService.queryAccount(queryBean.getCustId());
        } catch (Exception e) {
            logger.error("查询客户信息未成功", e);
            throw new BizException(e.getMessage());
        }
        return custInfo;
    }

    public ResultData validateMobile(SendMessageAO sendMessageAO) {
        try {
            TdLoginUserInfo loginCustInfo = custInfoService.validateCustMobile(sendMessageAO.getMobile(), sendMessageAO.getCustId());
            if (null == loginCustInfo) {
                return ResultData.success();
            } else {
                return ResultData.error("此手机号已被使用");
            }
        } catch (Exception e) {
            throw new BizException(e.getMessage());
        }
    }

    public ResultData validateEmail(SendMessageAO sendMessageAO) {
        try {
            TdLoginUserInfo loginCustInfo = custInfoService.validateEmail(sendMessageAO.getEmail(), sendMessageAO.getCustId());
            if (null == loginCustInfo) {
                return ResultData.success();
            } else {
                return ResultData.error("此邮箱已被使用");
            }
        } catch (Exception e) {
            throw new BizException(e.getMessage());
        }
    }

}
