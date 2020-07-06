package com.qifenqian.bms.v2.biz.aggregatepayment.refund.controller;

import com.github.pagehelper.PageInfo;
import com.qifenqian.bms.basemanager.aggregatepayment.merchant.bean.TdRefund;
import com.qifenqian.bms.basemanager.aggregatepayment.merchant.service.TdRefundService;
import com.qifenqian.bms.basemanager.trade.service.DownLoadUtil;
import com.qifenqian.bms.basemanager.utils.DatetimeUtils;
import com.qifenqian.bms.v2.biz.aggregatepayment.refund.service.BizRefundService;
import com.qifenqian.bms.v2.common.mvc.bean.PageQuery;
import com.qifenqian.bms.v2.common.mvc.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @author LiBin
 * @Description: 商户退款
 * @date 2020/4/24 17:12
 */
@RestController
@Api(tags = "商户退款管理")
public class BizRefundController extends BaseController {
    @Autowired
    private BizRefundService bizRefundService;
    @Autowired
    private TdRefundService tdRefundService;

    @PostMapping(value = "/refund/list")
    @ApiOperation("商户退款列表")
    public PageInfo<TdRefund> list(PageQuery pageQuery, @RequestBody TdRefund refund) {
        return this.bizRefundService.findRefundList(refund);
    }

    @PostMapping(value = "/refund/export")
    @ApiOperation("商户退款列表")
    public void export(@RequestBody TdRefund queryBean, HttpServletRequest request, HttpServletResponse response) {
        logger.info("导出退款信息");
        try {
            String[] headers = {"退款编号", "退款流水号", "原订单编号", "商户编号", "原订单总金额", "退款金额", "中信交易号", "中信退款号", "退款渠道", "渠道返回退款错误码", "渠道退款返回结果描述", "退款时间", "退款状态", "退款发起时间", "退款人编号"};
            List<TdRefund> list = tdRefundService.getRefundExport(queryBean);
            String fileName = DatetimeUtils.dateSecond() + "_退款信息.xls";
            Map<String, String> fileInfo = tdRefundService.exportExcel(list, headers, fileName, "退款信息", request);
            DownLoadUtil.downLoadFile(fileInfo.get("filePath"), response, fileInfo.get("fileName"), "xls");
            logger.info("导出excel退款信息成功");
        } catch (Exception e) {
            logger.error("导出excel退款信息异常", e);
        }

    }
}
