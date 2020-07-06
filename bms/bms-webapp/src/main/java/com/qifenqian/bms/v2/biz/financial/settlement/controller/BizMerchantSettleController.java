package com.qifenqian.bms.v2.biz.financial.settlement.controller;

import com.github.pagehelper.PageInfo;
import com.qifenqian.bms.basemanager.trade.service.DownLoadUtil;
import com.qifenqian.bms.basemanager.trade.service.TradeBillService;
import com.qifenqian.bms.basemanager.utils.DatetimeUtils;
import com.qifenqian.bms.merchant.settle.bean.MerchantSettle;
import com.qifenqian.bms.merchant.settle.bean.MerchantSettleExport;
import com.qifenqian.bms.merchant.settle.service.MerchantSettleService;
import com.qifenqian.bms.v2.biz.financial.settlement.bean.domain.BatchSettleAO;
import com.qifenqian.bms.v2.biz.financial.settlement.bean.domain.MerchantSettleAO;
import com.qifenqian.bms.v2.biz.financial.settlement.service.BizMerchantSettleService;
import com.qifenqian.bms.v2.biz.system.user.bean.domain.CurrentAccount;
import com.qifenqian.bms.v2.common.mvc.bean.PageQuery;
import com.qifenqian.bms.v2.common.mvc.bean.ResultData;
import com.qifenqian.bms.v2.common.mvc.bizexception.BizException;
import com.qifenqian.bms.v2.common.mvc.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
 * @author LiBin
 * @Description: 商户结算
 * @date 2020/5/6 15:46
 */
@RestController
@Api(tags = "商户结算管理")
public class BizMerchantSettleController extends BaseController {

    @Autowired
    private BizMerchantSettleService bizMerchantSettleService;
    @Autowired
    private MerchantSettleService merchantSettleService;
    @Autowired
    private TradeBillService tradeBillService;

    @PostMapping(value = "/settlement/list")
    @ApiOperation("商户结算列表")
    public PageInfo<MerchantSettle> list(PageQuery pageQuery, @RequestBody MerchantSettle merchantSettle) {
        return this.bizMerchantSettleService.findMerchantSettleList(merchantSettle);
    }


    @PostMapping(value = "/settlement/export")
    @ApiOperation("商户结算表导出")
    public void export(@RequestBody MerchantSettle requestBean, HttpServletRequest request, HttpServletResponse response) {
        logger.info("导出excel商户结算表");
        try {
            List<MerchantSettleExport> excels = merchantSettleService.exportSettle(requestBean);
            String[] headers = {"编号", "结算申请编号", "批次号", "金蝶清算编号", "商户编号", "商户名称", "七分钱执行", "结算开始日", "结算结束日", "收款笔数", "收款总额",
                    "收款总费用", "撤销笔数", "撤销总额", "撤销总费用", "退款笔数", "退款总额", "退款总费用", "提现笔数", "提现总额", "提现总费用", "转入笔数", "转入总额", "转入总费用", "转出笔数", "转出总额", "转出总费用",
                    "商户应收金", "商户应付金", "结算金额", "完成日期", "状态", "协议编号", "银行卡号", "银行卡名", "银行信息", "备注", "生成人", "记账日期",
                    "生成时间", "复核人", "复核时间", "核销人", "核销时间"};
            String fileName = DatetimeUtils.dateSecond() + "_商户结算.xls";
            fileName = URLEncoder.encode(fileName, "UTF8");
            Map<String, String> fileInfo = tradeBillService.exportExcel(excels, headers, fileName, "商户结算表", request);
            DownLoadUtil.downLoadFile(fileInfo.get("filePath"), response, fileInfo.get("fileName"), "xls");
            logger.info("导出excel商户结算表成功");
        } catch (Exception e) {
            logger.error("导出excel商户结算表异常", e);
            e.printStackTrace();
        }
    }

    @PostMapping(value = "/settlement/together")
    @ApiOperation("商户结算联合")
    public ResultData together(CurrentAccount currentAccount, @RequestBody MerchantSettleAO merchantSettleAO) {
        if (CollectionUtils.isEmpty(merchantSettleAO.getTogetherIds())) {
            throw new BizException("Ids不能为空!");
        }
        return this.bizMerchantSettleService.together(currentAccount, merchantSettleAO);
    }

    @PostMapping(value = "/settlement/separate")
    @ApiOperation("商户结算分离")
    public ResultData separate(CurrentAccount currentAccount, @RequestBody MerchantSettleAO merchantSettleAO) {
        if (CollectionUtils.isEmpty(merchantSettleAO.getTogetherIds())) {
            throw new BizException("Ids不能为空!");
        }
        return this.bizMerchantSettleService.separate(currentAccount, merchantSettleAO);
    }

    @PostMapping(value = "/settlement/batch/review")
    @ApiOperation("商户结算批量复核")
    public ResultData batchReview(CurrentAccount currentAccount, @RequestBody BatchSettleAO batchSettleAO) {
        if (CollectionUtils.isEmpty(batchSettleAO.getMerchantSettleAOS())) {
            throw new BizException("Ids不能为空!");
        }
        return this.bizMerchantSettleService.batchReview(currentAccount, batchSettleAO);
    }

    @PostMapping(value = "/settlement/batch/verified")
    @ApiOperation("商户结算批量核销")
    public ResultData batchVerified(CurrentAccount currentAccount, @RequestBody BatchSettleAO batchSettleAO) {
        if (CollectionUtils.isEmpty(batchSettleAO.getMerchantSettleAOS())) {
            throw new BizException("Ids不能为空!");
        }
        return this.bizMerchantSettleService.batchVerified(currentAccount, batchSettleAO);
    }

}
