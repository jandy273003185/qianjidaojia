package com.qifenqian.bms.v2.biz.aggregatepayment.merchanttrade.controller;

import com.github.pagehelper.PageInfo;
import com.qifenqian.bms.basemanager.aggregatepayment.merchant.bean.MerchantTradeQueryBean;
import com.qifenqian.bms.basemanager.aggregatepayment.merchant.bean.OrderSummaryBean;
import com.qifenqian.bms.basemanager.aggregatepayment.merchant.service.MerchantTradeService;
import com.qifenqian.bms.basemanager.trade.service.DownLoadUtil;
import com.qifenqian.bms.basemanager.utils.DatetimeUtils;
import com.qifenqian.bms.v2.biz.aggregatepayment.merchanttrade.bean.vo.OrderSummaryTotal;
import com.qifenqian.bms.v2.biz.aggregatepayment.merchanttrade.service.BizMerchantTradeService;
import com.qifenqian.bms.v2.common.mvc.bean.PageQuery;
import com.qifenqian.bms.v2.common.mvc.bizexception.BizException;
import com.qifenqian.bms.v2.common.mvc.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author LiBin
 * @Description: 商户交易汇总
 * @date 2020/4/23 17:28
 */
@RestController
@Api(tags = "商户交易汇总管理")
public class BizMerchantTradeController extends BaseController {

    @Autowired
    private MerchantTradeService merchantTradeService;
    @Autowired
    private BizMerchantTradeService bizMerchantTradeService;

    @PostMapping(value = "/merchanttrade/list")
    @ApiOperation(value = "商户交易汇总列表")
    public PageInfo<OrderSummaryBean> list(PageQuery pageQuery, @RequestBody MerchantTradeQueryBean queryBean) {
        if (StringUtils.isBlank(queryBean.getTradeType())) {
            queryBean.setTradeType("consume");
        }
        if (StringUtils.isBlank(queryBean.getCreateTimeB()) && StringUtils.isBlank(queryBean.getCreateTimeE())) {
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, -1);
            queryBean.setCreateTimeB(new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime()));
            queryBean.setCreateTimeE(new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime()));

        }
        return this.bizMerchantTradeService.findList(queryBean);
    }

    @PostMapping(value = "/merchanttrade/total")
    @ApiOperation(value = "商户交易汇总")
    public OrderSummaryTotal total(@RequestBody MerchantTradeQueryBean queryBean) {
        if (StringUtils.isBlank(queryBean.getTradeType())) {
            queryBean.setTradeType("consume");
        }
        if (StringUtils.isBlank(queryBean.getCreateTimeB()) && StringUtils.isBlank(queryBean.getCreateTimeE())) {
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, -1);
            queryBean.setCreateTimeB(new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime()));
            queryBean.setCreateTimeE(new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime()));
        }
        return this.bizMerchantTradeService.total(queryBean);
    }

    @PostMapping(value = "/merchanttrade/export")
    @ApiOperation(value = "商户交易汇总导出")
    public void export(@RequestBody MerchantTradeQueryBean queryBean, HttpServletRequest request,
                       HttpServletResponse response) {
        if (StringUtils.isBlank(queryBean.getMerchantName())) {
            throw new BizException("商户名称不能为空!");
        }
        logger.info("导出excel交易汇总表");
        try {
            //get方法提交的表单，要转码
            queryBean.setMerchantName(new String(queryBean.getMerchantName().getBytes("ISO-8859-1"), "UTF-8"));
            List<OrderSummaryBean> excels;

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            if (queryBean.getCreateTimeE() == "" && queryBean.getCreateTimeE() == null) {
                queryBean.setCreateTimeE(sdf.format(cal.getTime()));
            }
            if (queryBean.getCreateTimeB() == "" && queryBean.getCreateTimeB() == null) {
                //默认开始日期为前三个月
                cal.add(Calendar.DAY_OF_YEAR, -90);
                queryBean.setCreateTimeB(sdf.format(cal.getTime()));
            }

            List<OrderSummaryBean> tradeBills = merchantTradeService.getMerchantExportList(queryBean);
            if (queryBean.getTradeType() != null && queryBean.getTradeType().equals("refund")) {
                List<OrderSummaryBean> refundList = merchantTradeService.getMerchantRefundExportList(queryBean);
                excels = refundList;
            } else {
                excels = tradeBills;
            }
            for (OrderSummaryBean bean : excels) {
                bean.setCreateTimeB(queryBean.getCreateTimeB());
                bean.setCreateTimeE(queryBean.getCreateTimeE());
                if (queryBean.getTradeType().equals("consume")) {
                    bean.setTradeType("消费");
                } else if (queryBean.getTradeType().equals("refund")) {
                    bean.setTradeType("退款");
                }
            }
            String[] headers = {"开始账期", "结束账期", "商户编号", "商户名称", "交易渠道", "交易笔数", "交易金额", "客户编号", "交易类型", "商户结算金额"};
            String fileName = DatetimeUtils.dateSecond() + "_交易汇总.xls";
            Map<String, String> fileInfo = merchantTradeService.exportExcel(excels, headers, fileName, "交易汇总表", request);
            DownLoadUtil.downLoadFile(fileInfo.get("filePath"), response, fileInfo.get("fileName"), "xls");
            logger.info("导出excel交易汇总表成功");
        } catch (Exception e) {
            logger.error("导出excel交易汇总表异常", e);
            e.printStackTrace();
        }

    }
}
