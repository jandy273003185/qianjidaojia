package com.qifenqian.bms.v2.biz.common.service;

import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import com.qifenqian.bms.basemanager.aggregatepayment.agent.bean.PayChannelBean;
import com.qifenqian.bms.basemanager.aggregatepayment.agent.bean.PayProdBean;
import com.qifenqian.bms.basemanager.aggregatepayment.agent.mapper.AgentMapper;
import com.qifenqian.bms.basemanager.bank.bean.Bank;
import com.qifenqian.bms.basemanager.bank.mapper.BankMapper;
import com.qifenqian.bms.basemanager.city.mapper.CityMapper;
import com.qifenqian.bms.basemanager.merchant.bean.Merchant;
import com.qifenqian.bms.basemanager.merchant.mapper.MerchantMapper;
import com.qifenqian.bms.basemanager.rule.bean.Rule;
import com.qifenqian.bms.basemanager.rule.mapper.RuleMapper;
import com.qifenqian.bms.common.bean.BranchBankInfo;
import com.qifenqian.bms.common.dao.CommonInfoMapper;
import com.qifenqian.bms.materiel.bean.Materiel;
import com.qifenqian.bms.materiel.mapper.MaterielMapper;
import com.qifenqian.bms.merchant.channel.bean.ChannelStatus;
import com.qifenqian.bms.merchant.product.bean.Product;
import com.qifenqian.bms.merchant.product.mapper.MerchantProductMapper;
import com.qifenqian.bms.platform.web.admin.function.mapper.FunctionMapper;
import com.qifenqian.bms.v2.biz.common.bean.vo.ProvinceVO;
import com.qifenqian.bms.v2.biz.system.function.bean.vo.FunctionTreeVO;
import com.qifenqian.bms.v2.common.constant.CacheConstants;
import com.qifenqian.bms.v2.common.mvc.bean.ResultData;
import com.qifenqian.bms.v2.common.mvc.service.BaseService;
import com.seven.micropay.channel.enums.ChannelCode;
import com.seven.micropay.channel.enums.ChannelMerRegist;
import com.seven.micropay.channel.enums.PayType;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author LiBin
 * @Description: 公告接口类
 * @date 2020/4/17 12:34
 */
@Service
public class BizCommonService extends BaseService {

    @Autowired
    private CityMapper cityMapper;
    @Autowired
    private MerchantMapper merchantMapper;
    @Autowired
    private BankMapper bankMapper;
    @Autowired
    private RuleMapper ruleMapper;
    @Autowired
    private MerchantProductMapper merchantProductMapper;
    @Autowired
    private AgentMapper agentMapper;
    @Autowired
    private FunctionMapper functionMapper;
    @Autowired
    private MaterielMapper materielMapper;
    @Autowired
    private CommonInfoMapper commonInfoMapper;

    public List<Bank> selectBanks(Bank bank) {
        return bankMapper.selectBanks(bank);
    }

    public List<BranchBankInfo> branchBankList(BranchBankInfo queryBean, String channelCode) {
        List<BranchBankInfo> branchBankList = new ArrayList<BranchBankInfo>();
        //七分钱
        if (StringUtils.isBlank(channelCode)) {
            branchBankList = commonInfoMapper.branchBankList(queryBean);
            if (0 == branchBankList.size()) {
                queryBean.setCityCode(-1);
                branchBankList = commonInfoMapper.branchBankList(queryBean);
            }
        } else if ("SUIXING_PAY".equals(channelCode)) {
            branchBankList = commonInfoMapper.suiXingBranchBankList(queryBean);
        } else if ("ALLIN_PAY".equals(channelCode)) {
            branchBankList = commonInfoMapper.allinPayBranchBankList(queryBean);
        }
        return branchBankList;
    }

    public List<Product> getAllProduct() {
        return merchantProductMapper.selectProduct();
    }

    public List<PayProdBean> getPayProdList() {
        return agentMapper.getPayProdList(null);
    }

    public List<PayChannelBean> getPayChannelList() {
        return agentMapper.getPayChannelList(null);
    }

    public List<Merchant> getMerchantList() {
        return merchantMapper.selectMerchant();
    }

    public List<Materiel> getMaterielList() {
        return materielMapper.selectMaterielList(null);
    }

    //通道名
    public ResultData getChannelNameList() {
        Map<String, String> channelMerRegists = new HashMap<>();
        for (ChannelMerRegist value : ChannelMerRegist.values()) {
            channelMerRegists.put(value.getCode(), value.getText());
        }
        return ResultData.success(channelMerRegists);
    }

    //通道状态
    public ResultData getChannelStatusList() {
        Map<String, String> channelStatus = new HashMap<>();
        for (ChannelStatus value : ChannelStatus.values()) {
            channelStatus.put(value.getCode(), value.getDescribe());
        }
        return ResultData.success(channelStatus);
    }

    //支付通道编码
    public ResultData getChannelCodeList() {
        Map<String, String> channelCode = new HashMap<>();
        for (ChannelCode value : ChannelCode.values()) {
            channelCode.put(value.toString(), value.getText());
        }
        return ResultData.success(channelCode);
    }

    //支付通道编码
    public ResultData getPayTypeList() {
        Map<String, String> payType = new HashMap<>();
        for (PayType value : PayType.values()) {
            payType.put(value.getHeCode(), value.getText());
        }
        return ResultData.success(payType);
    }

    public List<ProvinceVO> findProvinceTree() {
        List<ProvinceVO> provinceVOS = redisTemplateUtil.getCacheList(CacheConstants.REDIS_AREA_KEY_ALL, ProvinceVO.class);
        if (CollectionUtils.isEmpty(provinceVOS)) {
            provinceVOS = cityMapper.selectProvinceTree();
            redisTemplateUtil.setCacheObject(CacheConstants.REDIS_AREA_KEY_ALL, provinceVOS, CacheConstants.REDIS_AREA_KEY_EXPIRED);
        }
        return provinceVOS;
    }

    public List<Rule> findRuleList(Rule rule) {
        return this.ruleMapper.selectRules(rule);
    }

    public List<Merchant> findAgentList() {
        return this.merchantMapper.selectAgent();
    }

    @Cached(name = "base:function:", cacheType = CacheType.REMOTE, expire = 3600, timeUnit = TimeUnit.SECONDS)
    public List<FunctionTreeVO> findFunctionTree(String s) {
        return functionMapper.findFunctionTree(0);
    }
}
