package com.qifenqian.bms.merchant.reports;

import com.qifenqian.bms.common.AbstractBaseController;
import com.qifenqian.bms.merchant.reported.bean.TdMerchantReportInfo;
import com.qifenqian.bms.merchant.reports.constants.ChannelTypeConstants;
import com.qifenqian.bms.merchant.reports.service.TdMerchantReportService;
import com.qifenqian.bms.v2.common.mvc.bean.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @description:
 * @author: LiBin
 * @date: 2019/12/25 0025 18:25
 */
@Controller
@RequestMapping("tdMerchant/report")
public class TdMerchantReportController extends AbstractBaseController {
    @Autowired
    private TdMerchantReportService tdMerchantReportService;

    @RequestMapping(value = "/add")
    @ResponseBody
    public ResultData testAdd() {
        TdMerchantReportInfo tdMerchantReport = new TdMerchantReportInfo();
        tdMerchantReport.setChannelNo(ChannelTypeConstants.ALIPAY);
        String alipayStr = "{\"merchantCode\":\"123\",\"account\":\"zhifub\"}";
        return this.tdMerchantReportService.addReport(tdMerchantReport, alipayStr);
    }

    @RequestMapping(value = "/list")
    @ResponseBody
    public ResultData list(String channel, String params) {
        return this.tdMerchantReportService.findMerchantDetailList(channel, params);
    }
}
