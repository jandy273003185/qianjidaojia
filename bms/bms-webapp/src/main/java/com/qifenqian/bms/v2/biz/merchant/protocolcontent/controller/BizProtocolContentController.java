package com.qifenqian.bms.v2.biz.merchant.protocolcontent.controller;

import com.github.pagehelper.PageInfo;
import com.qifenqian.bms.basemanager.protocolcontent.bean.ProtocolContent;
import com.qifenqian.bms.basemanager.protocolcontent.bean.ProtocolContentExportBean;
import com.qifenqian.bms.basemanager.protocolcontent.service.ProtocolContentService;
import com.qifenqian.bms.basemanager.trade.service.DownLoadUtil;
import com.qifenqian.bms.basemanager.utils.DatetimeUtils;
import com.qifenqian.bms.v2.biz.merchant.protocolcontent.service.BizProtocolContentService;
import com.qifenqian.bms.v2.biz.system.user.bean.domain.CurrentAccount;
import com.qifenqian.bms.v2.common.mvc.bean.PageQuery;
import com.qifenqian.bms.v2.common.mvc.bean.ResultData;
import com.qifenqian.bms.v2.common.mvc.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @author LiBin
 * @Description: 商户协议管理
 * @date 2020/4/20 15:28
 */
@RestController
@Api(tags = "商户协议管理")
public class BizProtocolContentController extends BaseController {

    @Autowired
    private ProtocolContentService protocolContentService;
    @Autowired
    private BizProtocolContentService bizProtocolContentService;

    @PostMapping(value = "/protocolcontent/list")
    @ApiOperation(value = "商户网关列表")
    public PageInfo<ProtocolContent> list(CurrentAccount currentAccount, PageQuery pageQuery, @RequestBody ProtocolContent queryBean) {
        queryBean.setUserId(currentAccount.getLoginId().toString());
        return this.bizProtocolContentService.findProtocolContentList(queryBean);
    }

    @PostMapping(value = "/protocolcontent/agentlistagentlist")
    @ApiOperation(value = "商户网关列表")
    public PageInfo<ProtocolContent> agentlist(CurrentAccount currentAccount, PageQuery pageQuery, @RequestBody ProtocolContent queryBean) {
        queryBean.setUserId(currentAccount.getLoginId().toString());
        return this.bizProtocolContentService.findAgentList(queryBean);
    }

    @PostMapping(value = "/protocolcontent/add")
    @ApiOperation(value = "商户网关添加")
    public ResultData add(@RequestBody ProtocolContent protocolContent) {
        return this.bizProtocolContentService.add(protocolContent);
    }

    @PostMapping(value = "/protocolcontent/update")
    @ApiOperation(value = "商户网关修改")
    public ResultData update(@RequestBody ProtocolContent protocolContent) {
        return this.bizProtocolContentService.update(protocolContent);
    }

    @PostMapping(value = "/protocolcontent/audit")
    @ApiOperation(value = "商户网关审核")
    public ResultData audit(@RequestBody ProtocolContent protocolContent) {
        return this.bizProtocolContentService.audit(protocolContent);
    }

    @PostMapping(value = "/protocolcontent/queryTemplate")
    @ApiOperation(value = "商户网关查询协议")
    public ResultData queryTemplate(@RequestBody ProtocolContent protocolContent) {
        return this.bizProtocolContentService.queryTemplate(protocolContent);
    }

    @PostMapping(value = "/protocolcontent/delete")
    @ApiOperation(value = "商户网关查询协议")
    public ResultData delete(@RequestBody ProtocolContent protocolContent) {
        return this.bizProtocolContentService.delete(protocolContent);
    }

    @GetMapping(value = "/protocolcontent/protocolContentExport")
    public void exportRechargeExcel(@RequestBody ProtocolContent queryBean, HttpServletRequest request, HttpServletResponse response) {
        logger.info("导出交易信息");
        try {
            String[] headers = {"协议编号", "商户编号", "商户名称", "协议模板", "结算周期", "是否跳过节假日", "结算卡号", "结算户名", "结算卡开户行及支行信息", "开户行地址", "联行号", "服务商", "客户经理", "H5支付_费率", "APP支付_费率", "扫码支付_费率", "网关支付_费率", "原生H5支付_费率", "创建人", "失效日期", "状态", "更新日期", "创建日期"};
            List<ProtocolContentExportBean> list = protocolContentService.getProtocolContentExport(queryBean);
            String fileName = DatetimeUtils.dateSecond() + "_协议信息.xls";
//            fileName = URLEncoder.encode(fileName, "UTF-8");
            Map<String, String> fileInfo = protocolContentService.exportExcel(list, headers, fileName, "协议信息", request);
            DownLoadUtil.downLoadFile(fileInfo.get("filePath"), response, fileInfo.get("fileName"), "xls");
            logger.info("导出excel协议信息成功");
        } catch (Exception e) {
            logger.error("导出excel协议信息异常", e);
        }
    }
}
