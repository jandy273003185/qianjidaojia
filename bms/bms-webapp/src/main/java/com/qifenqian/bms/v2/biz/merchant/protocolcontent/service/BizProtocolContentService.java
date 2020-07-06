package com.qifenqian.bms.v2.biz.merchant.protocolcontent.service;

import com.github.pagehelper.PageInfo;
import com.qifenqian.bms.basemanager.Constant;
import com.qifenqian.bms.basemanager.aggregatepayment.agent.bean.PayProdBean;
import com.qifenqian.bms.basemanager.aggregatepayment.agent.mapper.AgentMapper;
import com.qifenqian.bms.basemanager.custInfo.bean.TdCustInfo;
import com.qifenqian.bms.basemanager.custInfo.mapper.TdCustInfoMapper;
import com.qifenqian.bms.basemanager.merchant.bean.TdLoginUserInfo;
import com.qifenqian.bms.basemanager.merchant.mapper.TdLoginUserInfoMapper;
import com.qifenqian.bms.basemanager.protocolcontent.bean.ProtocolContent;
import com.qifenqian.bms.basemanager.protocolcontent.mapper.ProtocolContentMapper;
import com.qifenqian.bms.basemanager.protocolcontent.service.ProtocolContentService;
import com.qifenqian.bms.basemanager.protocoltemplate.bean.ProtocolTemplate;
import com.qifenqian.bms.basemanager.protocoltemplate.mapper.ProtocolTemplateMapper;
import com.qifenqian.bms.v2.common.mvc.bean.ResultData;
import com.qifenqian.bms.v2.common.mvc.bizexception.BizException;
import com.qifenqian.bms.v2.common.mvc.service.BaseService;
import com.sevenpay.plugin.IPlugin;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author LiBin
 * @Description: 商户协议管理
 * @date 2020/4/20 15:39
 */
@Service
public class BizProtocolContentService extends BaseService {
    @Autowired
    private ProtocolContentService protocolContentService;
    @Autowired
    private TdCustInfoMapper custInfoMapper;
    @Autowired
    private ProtocolContentMapper protocolContentMapper;
    @Autowired
    private TdLoginUserInfoMapper tdLoginUserInfoMapper;
    @Autowired
    private ProtocolTemplateMapper protocolTemplateMapper;
    @Autowired
    private IPlugin plugin;
    @Autowired
    private AgentMapper agentMapper;

    public PageInfo<ProtocolContent> findProtocolContentList(ProtocolContent queryBean) {
        String userId = queryBean.getUserId();
        List<ProtocolContent> contentList;
        if (StringUtils.isBlank(userId)) {
            contentList = protocolContentMapper.select(queryBean);
        } else {
            contentList = protocolContentMapper.selectMyProto(queryBean);
        }
        return new PageInfo<>(contentList);
    }

    public PageInfo<ProtocolContent> findAgentList(ProtocolContent queryBean) {
        String userId = queryBean.getUserId();
        List<ProtocolContent> contentList;
        if (StringUtils.isBlank(userId)) {
            contentList = protocolContentMapper.selectAgent(queryBean);
        } else {
            contentList = protocolContentMapper.selectMyAgentProto(queryBean);
        }
        return new PageInfo<>(contentList);
    }

    public ResultData add(ProtocolContent protocolContent) {
        TdCustInfo custInfo = new TdCustInfo();
        custInfo.setMerchantCode(protocolContent.getMerchantCode());
        custInfo = custInfoMapper.selectByBean(custInfo);
        if (custInfo == null) {
            throw new BizException("商户不存在，请核对商户编号是否正确!");
        }
        String custId = custInfo.getCustId();
        TdLoginUserInfo loginInfo = tdLoginUserInfoMapper.selectLoginUserInfo(custId);
        if (loginInfo != null) {
            throw new BizException("商户正在审核中,不能添加协议!");
        }
        List<ProtocolContent> protocolContents = protocolContentMapper.selectNewProtocolInfo(custId);
        if (CollectionUtils.isNotEmpty(protocolContents)) {
            throw new BizException("商户存在可用或待审核协议,不能添加协议!");
        }
        protocolContent.setCustId(custInfo.getCustId());
        protocolContent.setDisableDate("9999-01-01");//无限期
        protocolContentService.insert(protocolContent);
        return ResultData.success();
    }

    public ResultData update(ProtocolContent protocolContent) {
        TdCustInfo custInfo = new TdCustInfo();
        custInfo.setMerchantCode(protocolContent.getMerchantCode());
        custInfo = custInfoMapper.selectByBean(custInfo);
        if (custInfo == null) {
            throw new BizException("商户不存在，请核对商户编号是否正确!");
        }
        String custId = custInfo.getCustId();
        protocolContent.setCustId(custId);
        protocolContentService.eidtProtocol(protocolContent);
        return ResultData.success();
    }

    public ResultData audit(ProtocolContent protocolContent) {
        try {
            protocolContentService.auditProl(protocolContent);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException("商户网关审核异常! " + e.getMessage());
        }
        return ResultData.success();
    }

    public ResultData queryTemplate(ProtocolContent protocolContent) {
        String templateId = protocolContent.getTemplateId();
        ProtocolTemplate protocolTemplate = protocolTemplateMapper.selectTemplateById(templateId);
        if (protocolTemplate == null) {
            throw new BizException("");
        }
        String content = plugin.findDictByPath(Constant.FIX_CONTENT_PATH).trim();
        if (StringUtils.isEmpty(protocolTemplate.getProtocolTemplate().trim())) {
            protocolTemplate.setProtocolTemplate(content);
        } else {
            protocolTemplate.setProtocolTemplate(content + "," + protocolTemplate.getProtocolTemplate());
        }
        List<PayProdBean> prodList = agentMapper.getPayProdList(null);
        StringBuffer temp = new StringBuffer();
        temp.append(protocolTemplate.getProtocolTemplate());
        if (Constant.NORMAL_MERCHANT.equals(protocolTemplate.getMerchantType())) {
            for (PayProdBean payProdBean : prodList) {
                temp.append("," + payProdBean.getProdCode() + "_rate:" + payProdBean.getProdName() + "_费率:" + payProdBean.getStandardRate());
            }
        } else {
            for (PayProdBean payProdBean : prodList) {
                temp.append("," + payProdBean.getProdCode() + "_rate:" + payProdBean.getProdName() + "_底价费率:" + payProdBean.getAgentBaseRate());
            }
        }
        return ResultData.success(temp);
    }

    public ResultData delete(ProtocolContent protocolContent) {
        try {
            protocolContentService.delete(protocolContent);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException("商户网关删除异常! " + e.getMessage());
        }
        return ResultData.success();
    }
}
