package com.qifenqian.bms.v2.biz.merchant.reported.bean;

import com.qifenqian.bms.merchant.reported.bean.TdMerchantReportInfo;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

import java.io.Serializable;

/**
 * @author LvFeng
 * @Description: 商户报备参数
 * @date 2020/5/25 16:34
 */
@ApiOperation(value = "商户报备")
public class MerchantReportedAO implements Serializable {

    private static final long serialVersionUID = -9066750330610404451L;

    @ApiModelProperty(value = "报备主体")
    private TdMerchantReportInfo tdMerchantReport;
    @ApiModelProperty(value = "报备明细")
    private String reportDetailInfo;

    public TdMerchantReportInfo getTdMerchantReport() {
        return tdMerchantReport;
    }

    public void setTdMerchantReport(TdMerchantReportInfo tdMerchantReport) {
        this.tdMerchantReport = tdMerchantReport;
    }

    public String getReportDetailInfo() {
        return reportDetailInfo;
    }

    public void setReportDetailInfo(String reportDetailInfo) {
        this.reportDetailInfo = reportDetailInfo;
    }
}
