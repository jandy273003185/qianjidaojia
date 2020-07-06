package com.qifenqian.bms.v2.biz.common.controller;

import com.qifenqian.bms.basemanager.aggregatepayment.agent.bean.PayChannelBean;
import com.qifenqian.bms.basemanager.aggregatepayment.agent.bean.PayProdBean;
import com.qifenqian.bms.basemanager.bank.bean.Bank;
import com.qifenqian.bms.basemanager.merchant.bean.Merchant;
import com.qifenqian.bms.basemanager.merchant.bean.MerchantVo;
import com.qifenqian.bms.basemanager.merchant.bean.PicturePath;
import com.qifenqian.bms.basemanager.merchant.service.MerchantEnterService;
import com.qifenqian.bms.basemanager.rule.bean.Rule;
import com.qifenqian.bms.common.bean.BranchBankInfo;
import com.qifenqian.bms.materiel.bean.Materiel;
import com.qifenqian.bms.merchant.product.bean.Product;
import com.qifenqian.bms.platform.web.admin.user.bean.User;
import com.qifenqian.bms.platform.web.admin.user.service.UserService;
import com.qifenqian.bms.v2.biz.common.bean.ao.BranchBankInfoAO;
import com.qifenqian.bms.v2.biz.common.bean.vo.ProvinceVO;
import com.qifenqian.bms.v2.biz.common.service.BizCommonService;
import com.qifenqian.bms.v2.common.mvc.bean.ResultData;
import com.qifenqian.bms.v2.common.mvc.bizexception.BizException;
import com.qifenqian.bms.v2.common.mvc.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author LiBin
 * @Description: 公共接口
 * @date 2020/4/17 11:13
 */
@RestController
@Api(tags = "公共接口管理")
public class CommonController extends BaseController {
    @Autowired
    private BizCommonService bizCommonService;
    @Autowired
    private MerchantEnterService merchantEnterService;
    @Autowired
    private UserService userService;

    @PostMapping(value = "/common/serviceParenter/province/list")
    @ApiOperation(value = "服务商省份列表")
    public List<ProvinceVO> provinceList() {
        return bizCommonService.findProvinceTree();
    }

    @PostMapping("/common/product/list")
    @ApiOperation(value = "产品信息")
    public List<Product> getAllProduct() {
        return bizCommonService.getAllProduct();
    }

    @PostMapping("/common/payprod/list")
    @ApiOperation(value = "支付产品信息")
    public List<PayProdBean> getPayProdList() {
        return bizCommonService.getPayProdList();
    }

    @PostMapping("/common/paychannel/list")
    @ApiOperation(value = "支付渠道信息")
    public List<PayChannelBean> getPayChannelList() {
        return bizCommonService.getPayChannelList();
    }

    @PostMapping("/common/merchant/list")
    @ApiOperation(value = "商户信息")
    public List<Merchant> getMerchantList() {
        return bizCommonService.getMerchantList();
    }

    @PostMapping("/common/materiel/list")
    @ApiOperation(value = "设备信息")
    public List<Materiel> getMaterielList() {
        return bizCommonService.getMaterielList();
    }

    @PostMapping("/common/channelname/list")
    @ApiOperation(value = "通道名信息")
    public ResultData getChannelNameList() {
        return bizCommonService.getChannelNameList();
    }

    @PostMapping("/common/channelstatus/list")
    @ApiOperation(value = "通道状态信息")
    public ResultData getChannelStatusList() {
        return bizCommonService.getChannelStatusList();
    }

    @PostMapping("/common/channelcode/list")
    @ApiOperation(value = "支付通道编码信息")
    public ResultData getChannelCodeList() {
        return bizCommonService.getChannelCodeList();
    }

    @PostMapping("/common/paytype/list")
    @ApiOperation(value = "支付通道产品编码信息")
    public ResultData getPayTypeList() {
        return bizCommonService.getPayTypeList();
    }

    @PostMapping(value = "/common/bank/list")
    @ApiOperation(value = "银行列表")
    public List<Bank> bankList(@RequestBody Bank bank) {
        return bizCommonService.selectBanks(bank);
    }

    @PostMapping(value = "/common/branchbank/list")
    @ApiOperation(value = "银行所属支行信息")
    public List<BranchBankInfo> branchBankList(@RequestBody BranchBankInfoAO branchBankInfoAO) {
        if (null == branchBankInfoAO.getQueryBean())
            throw new BizException("银行信息不能为空");
        return bizCommonService.branchBankList(branchBankInfoAO.getQueryBean(), branchBankInfoAO.getChannelCode());
    }

    @PostMapping(value = "/common/picPath")
    @ApiOperation(value = "图片地址信息")
    public PicturePath picPath(@RequestBody MerchantVo merchantVo) {
        return merchantEnterService.getPicPath(merchantVo);
    }

    @PostMapping(value = "/common/rule/list")
    @ApiOperation(value = "费率信息")
    public List<Rule> ruleList(@RequestBody Rule rule) {
        rule.setStatus("VALID");
        return bizCommonService.findRuleList(rule);
    }

    @PostMapping(value = "/common/user/list")
    @ApiOperation(value = "用户列表")
    public List<User> userList(@RequestBody User user) {
        return userService.getUserList(user);
    }

    @PostMapping(value = "/common/agent/list")
    @ApiOperation(value = "服务商列表")
    public List<Merchant> agentList() {
        return bizCommonService.findAgentList();
    }
}
