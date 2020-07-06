package com.qifenqian.bms.v2.biz.merchant.merchantregister.service;

import com.github.pagehelper.PageInfo;
import com.qifenqian.bms.basemanager.custInfo.bean.TdCustInfo;
import com.qifenqian.bms.basemanager.custInfo.mapper.TdCustInfoMapper;
import com.qifenqian.bms.basemanager.custInfo.service.TdCustInfoService;
import com.qifenqian.bms.basemanager.merchant.bean.Merchant;
import com.qifenqian.bms.basemanager.merchant.bean.MerchantVo;
import com.qifenqian.bms.basemanager.merchant.mapper.MerchantMapper;
import com.qifenqian.bms.basemanager.merchant.service.MerchantService;
import com.qifenqian.bms.basemanager.merchant.service.MerchantWorkFlowAuditService;
import com.qifenqian.bms.basemanager.utils.GenSN;
import com.qifenqian.bms.platform.web.admin.utils.WebUtils;
import com.qifenqian.bms.upgrade.merchant.bean.TdAuditRecodeInfo;
import com.qifenqian.bms.upgrade.merchant.service.MerchantListService;
import com.qifenqian.bms.v2.biz.merchant.merchantregister.mapper.MerchantRegisterMapper;
import com.qifenqian.bms.v2.biz.system.user.bean.domain.CurrentAccount;
import com.qifenqian.bms.v2.common.mvc.bean.ResultData;
import com.qifenqian.bms.v2.common.mvc.bizexception.BizException;
import com.qifenqian.bms.v2.common.mvc.service.BaseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 */
@Service
public class MerchantRegisterService extends BaseService {
    @Autowired
    private TdCustInfoMapper custInfoMapper;
    @Autowired
    private MerchantMapper merchantMapper;
    @Autowired
    private TdCustInfoService tdCustInfoService;
    @Autowired
    private MerchantService merchantService;
    @Autowired
    private MerchantRegisterMapper merchantRegisterMapper;
    @Autowired
    private MerchantWorkFlowAuditService merchantWorkFlowAuditService;
    @Autowired
    private MerchantListService merchantListService;
    /**
     * 商户列表
     * @param merchantVo
     * @return
     */
    public PageInfo<MerchantVo> findMerchantList(MerchantVo merchantVo) {
        List<MerchantVo> merchantVos;
        //判断是否有权限
        if (StringUtils.isBlank(merchantVo.getUserId())) {
            merchantVos = merchantRegisterMapper.myMerchantRegisterList(merchantVo);
        } else {
            merchantVos = merchantRegisterMapper.merchantRegisterList(merchantVo);
        }
        return new PageInfo<>(merchantVos);
    }

    /**
     * 商户详细
     * @param custId
     * @return
     */
    public MerchantVo findMerchantInfo(String custId) {
        return merchantRegisterMapper.findMerchantInfo(custId);
    }

    /**
     * 商户新增
     * @param currentAccount
     * @param merchant
     */
    public void add(CurrentAccount currentAccount, Merchant merchant) {
        try {
            String userId = currentAccount.getLoginId().toString();
            boolean isAddMerchant = tdCustInfoService.isAddMerchant(userId);
            if (!isAddMerchant) {
                throw new BizException("没有新增权限");
            }
            // 设置商户custId
            String merchantCode = null;
            if (merchant.getBusinessLicense() == null || "".equals(merchant.getBusinessLicense())) {
                //表示个人
                merchantCode = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + GenSN.getRandomNum(3);
            } else {
                merchant.setMerchantCode("P" + GenSN.getRandomNum(18));
//				merchant  Code = BusinessUtils.getMerchantId(merchant.getBusinessLicense());
            }
            if ("".equals(merchant.getMerchantCode()) || null == merchant.getMerchantCode()) {
                merchant.setMerchantCode(GenSN.getRandomNum(19));
            }

            String custId = GenSN.getSN();

            merchant.setCustId(custId);
            // 任务ID
            merchantService.addMerchant(merchant, null);
            // 流程到下一步
            merchantService.startProcess(custId, merchant.getTaskId(), merchant.getAuditName());
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException(e.getMessage());
        }

    }

    /**
     * 商户更新
     * @param merchantVo
     */
    public void update(MerchantVo merchantVo) {
        merchantService.updateMerchantEnter(merchantVo);
    }


    /**
     * 商户审核
     * @param tdAuditRecodeInfo
     * @return
     */
    public ResultData audit(TdAuditRecodeInfo tdAuditRecodeInfo) {
        String result = null;
        if ("00".equals(tdAuditRecodeInfo.getStatus())){
            //审核通过
            result = "pass";
        }else {
            //审核未通过
            result = "noPass";
        }
        logger.info("开始写入商户编号为"+tdAuditRecodeInfo.getMerchantCode()+"的审核结果："+result+",审核信息："+tdAuditRecodeInfo.getAuditInfo());

        //设置审核者的id
        tdAuditRecodeInfo.setUserId(String.valueOf(String.valueOf(WebUtils.getUserInfo().getUserId())));
        //设置审核信息
        tdAuditRecodeInfo.setAuditTime(new Date());
        //将审核类型01代表注册
        tdAuditRecodeInfo.setAuditType("01");
        //将id设置32位的UUID
        tdAuditRecodeInfo.setId(GenSN.getSN());

        TdCustInfo tdCustInfo = custInfoMapper.selectByMerchantCode(tdAuditRecodeInfo.getMerchantCode());
        MerchantVo merchantVo = new MerchantVo();
        merchantVo.setCustId(tdCustInfo.getCustId());
        if("noPass".equals(result)) {
            tdAuditRecodeInfo.setStatus("99");
            merchantVo.setFilingAuditStatus("99");
            //更改审核状态
            merchantWorkFlowAuditService.updateAuditStatus(tdCustInfo.getAuthId(), tdAuditRecodeInfo.getAuditInfo(), "2");
            merchantMapper.updateMerchantEnter(merchantVo);
        }else if("pass".equals(result)) {
            tdAuditRecodeInfo.setStatus("00");
            merchantVo.setFilingAuditStatus("01");
            merchantWorkFlowAuditService.updateAuditStatus(tdCustInfo.getAuthId(), tdAuditRecodeInfo.getAuditInfo(), "0");
            merchantMapper.updateMerchantEnter(merchantVo);
        }

        try {
            String updateResult = merchantListService.updateResultOfAudit(result, tdAuditRecodeInfo.getMerchantCode(), tdAuditRecodeInfo);
        }catch(Exception e) {
            logger.error("写入商户编号为"+tdAuditRecodeInfo.getMerchantCode()+"的审核结果："+result+",审核信息："+tdAuditRecodeInfo.getAuditInfo()+"发生异常,异常信息为:"+e.toString());
            return  ResultData.error(e.toString());
        }

        return ResultData.success();
    }

}
