package com.sevenpay.agentmanager.biz.agent.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qifenqian.app.bean.Bank;
import com.qifenqian.app.bean.TbBankProvincesInfoBean;
import com.qifenqian.app.bean.TbProvincesInfoBean;
import com.qifenqian.app.bean.TdCustInfo;
import com.qifenqian.app.bean.TdCustScanCopy;
import com.qifenqian.app.bean.TdLoginUserInfo;
import com.qifenqian.app.bean.TdMerchantProductInfo;
import com.qifenqian.app.bean.dto.trade.TdOrderRateDetailCondition;
import com.qifenqian.app.common.BankInfoService;
import com.qifenqian.app.customer.MerchantInfoService;
import com.qifenqian.app.customer.MerchantStatusService;
import com.qifenqian.app.customer.SalesmanManagerService;
import com.qifenqian.app.merchant.CommercialService;
import com.qifenqian.app.product.ProductInfoService;
import com.qifenqian.app.user.TdLoginUserInfoService;
import com.sevenpay.agentmanager.biz.agent.bean.condition.MerchantCondition;
import com.sevenpay.agentmanager.biz.agent.service.AgentServiceImpl;
import com.sevenpay.agentmanager.common.pojo.Pager;
import com.sevenpay.agentmanager.common.utils.AddCustScanCopy;
import com.sevenpay.agentmanager.common.utils.AddTdMerchantProductInfo;
import com.sevenpay.agentmanager.core.bean.ResultData;
import com.sevenpay.agentmanager.core.controller.AbstractBaseController;
import com.sevenpay.agentmanager.core.exception.BizException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 服务商（管理员接口）/产品接口
 */
@RestController
@RequestMapping("merchant")
@Api(tags = "服务商（管理员接口）/产品接口")
public class AgentController extends AbstractBaseController {


    @Autowired
    private AgentServiceImpl agentService;
    @Reference(version="${app.service.version}")
    private MerchantInfoService merchantInfoService;
    @Reference(version="${app.service.version}")
    private CommercialService commerService;
    @Reference(version="${app.service.version}")
    private ProductInfoService productInfoService;
    @Reference(version="${app.service.version}")
    private MerchantStatusService merchantStatusService;
    @Reference(version="${app.service.version}")
    private SalesmanManagerService salesmanManagerService;
    @Reference(version="${app.service.version}")
    private BankInfoService bankInfoService;
    @Reference(version="${app.service.version}")
    private TdLoginUserInfoService tdLoginUserInfoService;

    @Value("${images.uri}")
    private String uri;
    @Value("${images.relativePath}")
    private String relativePath;


    /**
     * 查询商户审核信息
     *
     * @param userId            管理员/业务员id
     * @param custName          商户名
     * @param stateCode         客户状态：00 有效；01 待审核；02 注销；03 冻结；04 审核不通过.
     * @param filingAuditStatus 报备审核状态 00：成功 99：失败 01：待审核 02提交审核',
     * @param queryStartDate    起始时间
     * @param queryEndDate      结束时间
     * @return
     */
    @PostMapping("selectCommercialInfo")
    public ResultData selectCommercialInfo(String userId,
                                           String custName,
                                           String stateCode,
                                           String filingAuditStatus,
                                           String queryStartDate,
                                           String queryEndDate,
                                           int pageSize,
                                           int pageNum,
                                           String roleId) {
        int page = (pageNum - 1) * pageSize;

        Pager<TdCustInfo> pager = new Pager<>();
        List<TdCustInfo> list = commerService.selectCommercialInfo(userId, custName, stateCode, filingAuditStatus, queryStartDate, queryEndDate, pageSize, page, roleId);
        pager.setData(list);
        pager.setTotal(commerService.selectCommercialInfoCount(userId, custName, stateCode, filingAuditStatus, queryStartDate, queryEndDate, pageSize, page, roleId));
        return ResultData.success(pager);


    }

    /**
     * 统计服务商下的商户数据
     *
     * @param userId         管理员（服务商）id
     * @param queryStartDate 开始时间
     * @param queryEndDate   结束时间
     * @return
     */
    @PostMapping("getSPStatCommercial")
    public ResultData getSPStatCommercial(String userId,
                                          String queryStartDate,
                                          String queryEndDate) {
        logger.info("cust_id--------------------" + userId);
        Map<String, Object> map = commerService.getSPStatCommercial(userId, queryStartDate, queryEndDate);
        return ResultData.success(map);
    }

    /**
     * @description:
     * @author: LiBin
     * @params [userId, queryStartDate, queryEndDate]
     * @return: com.sevenpay.agentmanager.core.bean.ResultData
     * @date: 2020-01-02 10:26:05
     */
    @PostMapping(value = "getSPMerchantOrderList")
    @ApiOperation(value = "查询服务商下商户流水信息")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "mchId", value = "商户ID", dataType = "String", paramType = "query", defaultValue = ""),
//            @ApiImplicitParam(name = "queryStartDate", value = "开始时间", dataType = "String", paramType = "query", defaultValue = ""),
//            @ApiImplicitParam(name = "queryEndDate", value = "结束时间", dataType = "String", paramType = "query", defaultValue = ""),
//    })
    public ResultData getSPMerchantList(MerchantCondition merchantCondition) {
        if (StringUtils.isBlank(merchantCondition.getMchId())) {
            throw new BizException("商户ID不能为空!");
         }

        return agentService.getSPMerchantOrderList(merchantCondition);
    }


    /**
     * 统计服务商下的商户数据
     *
     * @param userId         业务员id
     * @param queryStartDate 开始时间
     * @param queryEndDate   结束时间
     * @return
     */
    @PostMapping("getStatCommercial")
    public ResultData getStatCommercial(String userId,
                                        String queryStartDate,
                                        String queryEndDate,
                                        String roleId) {
        Map<String, Object> map = commerService.getStatCommercial(userId, queryStartDate, queryEndDate, roleId);
        return ResultData.success(map);
    }

    /**
     * 交易排名
     *
     * @param userId         业务员
     * @param custName       商户名称
     * @param queryStartDate 查询起始时间
     * @param queryEndDate   查询终止时间
     * @param pageSize       页面条数
     * @param roleId         3是业务员
     * @param pageNum        当前页数
     * @param rankingCode    transactionNum desc      transactionNum asc
     * @return
     */
    @PostMapping("getDealRanking")
    public ResultData getDealRanking(String userId,
                                     String custName,
                                     String queryStartDate,
                                     String queryEndDate,
                                     String roleId,
                                     int pageSize,
                                     int pageNum,
                                     String rankingCode) {
        if (roleId == null) {
            throw new BizException("roleId不能为空");
        }
        int page = (pageNum - 1) * pageSize;
        Pager<Map<String, Object>> pager = new Pager<Map<String, Object>>();
        List<Map<String, Object>> list = commerService.getDealRanking(userId, custName, queryStartDate, queryEndDate, roleId, pageSize, page, rankingCode);
        pager.setData(list);
        pager.setTotal(commerService.getDealRankingCount(userId, custName, queryStartDate, queryEndDate, roleId, pageSize, page, rankingCode));
        return ResultData.success(pager);
    }

    @PostMapping("sentEmailByDealRanking")
    public ResultData sentEmailByDealRanking(String userId,
                                             String custName,
                                             String queryStartDate,
                                             String queryEndDate,
                                             String roleId,
                                             String rankingCode) {
        return this.agentService.sentEmailByDealRanking(userId, custName, queryStartDate, queryEndDate, roleId, rankingCode);
    }


    /**
     * 获取商户审核失败原因
     *
     * @param custId 商户编号
     * @return
     */
    @PostMapping("getCommerAuditCause")
    public ResultData getCommerAuditCause(String custId) {
        List<Map<String, Object>> commerAuditCause = commerService.getCommerAuditCause(custId);
        return ResultData.success(commerAuditCause);
    }


    /**
     * 新增/修改商户(进件)
     *
     * @param request
     * @param tdCustInfo
     * @return
     * @throws ParseException
     */
    @PostMapping("insertMerchant")
    public ResultData addMerchant(HttpServletRequest request,
                                  TdCustInfo tdCustInfo) throws ParseException {

        TdCustInfo queryResult = merchantInfoService.getMerchantById(tdCustInfo.getCustId());

        if (queryResult == null) {//保存到数据库待审核/待完善
            tdCustInfo.setCreateTime(new Date());
            String userId = request.getParameter("userId");
            tdCustInfo.setCreateId(userId);
            if (userId == null) {
                throw new BizException("该账号异常，请重新登陆");
            }
            tdCustInfo.setMerchantMobile(tdCustInfo.getMerchantAccount());
            Map<String, Object> map = merchantInfoService.merchantAdd(tdCustInfo);
            String custId = (String) map.get("custId");//商户编号
            Object authId = map.get("authId");//图片与商户对应的自增长Id
            int authId1 = Integer.parseInt((String) authId);
            if (custId != null) {
                //扫描件路径保存
                List<TdCustScanCopy> scanCopyList = AddCustScanCopy.add(request, custId);
                if (scanCopyList != null) {
                    for (TdCustScanCopy tdCustScanCopy : scanCopyList) {
                        tdCustScanCopy.setAuthId(authId1);
                        merchantInfoService.saveTdCustScanCopy(tdCustScanCopy);
                    }
                }
                //产品保存

                List<TdMerchantProductInfo> productList = AddTdMerchantProductInfo.add(request, custId);
                if (productList != null) {
                    for (TdMerchantProductInfo tdMerchantProductInfo : productList) {
                        productInfoService.saveTdMerchantProductInfo(tdMerchantProductInfo);
                    }
                }
                //进件完成
                return ResultData.success(custId);
            }
        } else { //完善后提交（修改操作）
            String custId1 = tdCustInfo.getCustId();//商户编号
            Integer authId = queryResult.getAuthId();
            if (custId1 == null) {
                throw new BizException("商户进件失败");
            }
            tdCustInfo.setMerchantMobile(tdCustInfo.getMerchantAccount());
            tdCustInfo.setModifyTime(new Date());//修改时间
            tdCustInfo.setModifyId(request.getParameter("userId"));//修改人
            String state = queryResult.getState();
            if (state != null) {
                if ("04".equals(state)) {
                    tdCustInfo.setAuthId(authId);
                    tdCustInfo.setState(queryResult.getState());
                }
            }
            merchantInfoService.updateMerchant(tdCustInfo);
            TdCustScanCopy tSC = new TdCustScanCopy();
            tSC.setCustId(custId1);
            //查询待完善图片
            List<TdCustScanCopy> tdCustScanCopies = merchantInfoService.selectCustScanCopy(tSC);
            if (tdCustScanCopies.size() > 0) {
                for (TdCustScanCopy tdCustScanCopy : tdCustScanCopies) {
                    tdCustScanCopy.setStatus("01");
                    merchantInfoService.updateTdCustScanCopy(tdCustScanCopy);
                }
            }
            //扫描件路径保存
            List<TdCustScanCopy> scanCopyList = AddCustScanCopy.add(request, custId1);
            if (scanCopyList.size() > 0) {
                for (TdCustScanCopy tdCustScanCopy : scanCopyList) {
                    tdCustScanCopy.setAuthId(authId);
                    tdCustScanCopy.setStatus("00");
                    merchantInfoService.saveTdCustScanCopy(tdCustScanCopy);
                }
            }
            //产品保存
            TdMerchantProductInfo MerchantProductInfo = new TdMerchantProductInfo();
            MerchantProductInfo.setMchCustId(custId1);
            List<TdMerchantProductInfo> productList = AddTdMerchantProductInfo.add(request, custId1);
            List<TdMerchantProductInfo> merchantProductInfos = productInfoService.selectOpenProductInfo(MerchantProductInfo);
            if (merchantProductInfos.size() > 0) {
                for (TdMerchantProductInfo merchantProductInfo : merchantProductInfos) {//删除旧的产品
                    merchantProductInfo.setMchCustId(custId1);
                    productInfoService.delMerchantProduct(merchantProductInfo);
                }
            }
            if (productList != null) {
                for (TdMerchantProductInfo tdMerchantProductInfo : productList) {//存储新的产品
                    productInfoService.saveTdMerchantProductInfo(tdMerchantProductInfo);
                }
            }
            //修改进件完成
            return ResultData.success(custId1);

        }
        return ResultData.error("商户进件失败");
    }

    /**
     * 查询未完善商户进件资料
     *
     * @param tdCustInfo (custId)查询
     * @return
     */
    @PostMapping("queryMerchant")
    public ResultData queryMerchant(TdCustInfo tdCustInfo) {
        Map<String, Object> map = new HashMap<>();
        //查询商户信息
        TdCustInfo custInfo = merchantInfoService.getCustInfo(tdCustInfo);
        map.put("custInfo", custInfo);
        //查询省市区
        TbProvincesInfoBean tbProvincesInfoBean = new TbProvincesInfoBean(custInfo.getProvince(), custInfo.getCity(), custInfo.getCountry());
        List<TbProvincesInfoBean> provinces = merchantInfoService.getProvinces(tbProvincesInfoBean);
        map.put("provinces", provinces);
        //查询银行省市
        TbBankProvincesInfoBean tbBankProvincesInfoBean = new TbBankProvincesInfoBean();
        tbBankProvincesInfoBean.setBankCityId(custInfo.getBankCityName());
        tbBankProvincesInfoBean.setBankProvinceId(custInfo.getBankProvinceName());
        List<TbBankProvincesInfoBean> bankProvinces = merchantInfoService.getBankProvinces(tbBankProvincesInfoBean);
        map.put("bankProvinces", bankProvinces);
        //查询银行总行支行信息
        Bank bank = bankInfoService.selectBankByBankCode(custInfo.getCompAcctBank());
        if (bank != null) {
            bank.setBranchBankCode(custInfo.getBranchBank());
            List<Bank> banks = bankInfoService.selectBranchBanks(bank);
            if (banks.size() > 0) {
                for (Bank bank1 : banks) {
                    bank1.setBankCode(bank.getBankCode());
                    bank1.setBankName(bank.getBankName());
                }
            }
            map.put("banks", banks);
        }
        //查询商户签约产品
        TdMerchantProductInfo tdMerchantProductInfo = new TdMerchantProductInfo();
        tdMerchantProductInfo.setMchCustId(tdCustInfo.getCustId());
        List<TdMerchantProductInfo> merchantProductInfos = productInfoService.selectOpenProductInfo(tdMerchantProductInfo);
        map.put("productInfoList", merchantProductInfos);
        //查询商户扫描件信息，并将路径转化为URI的形式
        TdCustScanCopy tdCustScanCopy = new TdCustScanCopy();
        tdCustScanCopy.setCustId(tdCustInfo.getCustId());
        List<TdCustScanCopy> tdCustScanCopies = merchantInfoService.selectCustScanCopy(tdCustScanCopy);
        map.put("uri", uri);
        map.put("url", relativePath);
        map.put("custScanInfoList", tdCustScanCopies);
        return ResultData.success(map);
    }

    /**
     * 添加产品
     *
     * @param request
     * @param custId
     * @return
     * @throws ParseException
     */
    @PostMapping("insertProduct")
    public ResultData insertProduct(HttpServletRequest request, String custId) throws ParseException, IOException {
        List<TdMerchantProductInfo> productInfos = AddTdMerchantProductInfo.add(request, custId);
        if (productInfos.size() > 0) {
            for (TdMerchantProductInfo productInfo : productInfos) {
                productInfoService.saveTdMerchantProductInfo(productInfo);
            }
            return ResultData.success("签约产品提交成功,待审核");
        }
        return ResultData.error("签约产品提交失败");
    }

    @PostMapping("delProduct")
    public ResultData delProduct(TdMerchantProductInfo tdMerchantProductInfo) {

        return ResultData.success();
    }

    /**
     * 查询商户已签约产品
     *
     * @param tdMerchantProductInfo
     * @return
     */
    @PostMapping("queryProduct")
    public ResultData queryProduct(TdMerchantProductInfo tdMerchantProductInfo) {
        if (StringUtils.isBlank(tdMerchantProductInfo.getMchCustId())) {
            throw new BizException("mchCustId不能为空");
        }
        List<TdMerchantProductInfo> merchantProductInfos = productInfoService.selectOpenProductInfo(tdMerchantProductInfo);
        return ResultData.success(merchantProductInfos);


    }

    /**
     * 查询商户信息
     *
     * @param custId
     * @return
     */
    @PostMapping("queryMerchantById")
    public ResultData queryProduct(String custId) {
        TdCustInfo custInfo = merchantInfoService.getMerchantById(custId);
        TdLoginUserInfo tdLoginUserInfo = tdLoginUserInfoService.queryMobileByCustId(custId);
        custInfo.setMerchantAccount(tdLoginUserInfo.getMobile());
        return ResultData.success(custInfo);
    }

    /**
     * 设备号校验
     *
     * @param sn 设备号
     * @return
     */
    @PostMapping("checkSn")
    public ResultData checkSn(String sn) {
        boolean result = commerService.isPertainToAgent(sn);
        if (result) {
            return ResultData.success("校验通过");
        }
        return ResultData.error("校验失败");
    }

    /**
     * 查询该商户手机号是否绑定过
     *
     * @param merchantAccount 手机账号字段
     * @return
     */
    @PostMapping("checkMobile")
    public ResultData checkMobile(String merchantAccount) {
        boolean result = merchantStatusService.isBindingMobile(merchantAccount);
        if (result) {
            throw new BizException("手机号已注册，如有疑问请联系客服！");
        }
        return ResultData.success();
    }

    @PostMapping("getAgentInfo")
    public ResultData getAgentInfo(TdCustInfo tdCustInfo) {
        //查询商户信息
        TdCustInfo custInfo = merchantInfoService.getCustInfo(tdCustInfo);
        if (custInfo == null) {
            throw new BizException("商户信息不存在！");
        }
        return ResultData.success(custInfo.getCustName());
    }

    @PostMapping("getShareProfit")
    public ResultData getShareProfit(TdOrderRateDetailCondition tdOrderRateDetailCondition) {
        if (StringUtils.isBlank(tdOrderRateDetailCondition.getCustId())) {
            throw new BizException("代理商ID不能为空!");
        }
        return this.agentService.getShareProfit(tdOrderRateDetailCondition);
    }

}
