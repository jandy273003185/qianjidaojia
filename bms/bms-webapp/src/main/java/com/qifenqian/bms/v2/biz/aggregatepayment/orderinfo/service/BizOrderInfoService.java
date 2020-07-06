package com.qifenqian.bms.v2.biz.aggregatepayment.orderinfo.service;

import com.github.pagehelper.PageInfo;
import com.qifenqian.bms.basemanager.Constant;
import com.qifenqian.bms.basemanager.aggregatepayment.orderinfo.bean.DealOperation;
import com.qifenqian.bms.basemanager.aggregatepayment.orderinfo.bean.ExportOrderInfoBean;
import com.qifenqian.bms.basemanager.aggregatepayment.orderinfo.bean.OrderInfoBean;
import com.qifenqian.bms.basemanager.aggregatepayment.orderinfo.bean.OrderInfoQueryBean;
import com.qifenqian.bms.basemanager.aggregatepayment.orderinfo.mapper.OrderMapperSlave;
import com.qifenqian.bms.basemanager.aggregatepayment.orderinfo.service.OrderInfoService;
import com.qifenqian.bms.basemanager.trade.service.DownLoadUtil;
import com.qifenqian.bms.basemanager.utils.DatetimeUtils;
import com.qifenqian.bms.v2.biz.system.user.bean.domain.CurrentAccount;
import com.qifenqian.bms.v2.common.mvc.bean.ResultData;
import com.qifenqian.bms.v2.common.mvc.bizexception.BizException;
import com.qifenqian.bms.v2.common.mvc.service.BaseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.List;


@Service
public class BizOrderInfoService extends BaseService {

    @Autowired
    private OrderInfoService orderInfoService;

    @Autowired
    private OrderMapperSlave orderMapperSlave;


    public PageInfo<OrderInfoBean> list(CurrentAccount currentAccount, OrderInfoQueryBean queryBean) {
        String createTimeBv = queryBean.getCreateTimeB();
        if (StringUtils.isNotBlank(createTimeBv)) {
            queryBean.setCreateTimeBv(createTimeBv);
            Date createTimeB = DatetimeUtils.stringForDate(createTimeBv, "yyyy-MM-dd");
            queryBean.setCreateTimeB(DatetimeUtils.dateFormat(createTimeB, "yyyy-MM-dd 00:00:00"));
        }
        String createTimeEv = queryBean.getCreateTimeE();
        if (StringUtils.isNotBlank(createTimeEv)) {
            Date createTimeE = DatetimeUtils.stringForDate(createTimeEv, "yyyy-MM-dd");
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(createTimeE);
            calendar.add(Calendar.DATE, 1); // 把日期往后增加一天,整数 往后推,负数往前移动
            createTimeE = calendar.getTime();
            queryBean.setCreateTimeE(DatetimeUtils.dateFormat(createTimeE, "yyyy-MM-dd 00:00:00"));
        }
//        String userId = String.valueOf(currentAccount.getLoginId());
        // 是否有权限查看所有订单
//        boolean isAllList = tdCustInfoService.isAllList(userId);
        List<OrderInfoBean> list = null;
        if (null != currentAccount.getLoginId())
            list = orderMapperSlave.getOrderInfoList(queryBean);
//        } else {
//            queryBean.setUserName(currentAccount.getName());
//            queryBean.setUserId(userId);
//            list = orderInfoService.queryMylist(queryBean);
//        }
        return new PageInfo<>(list);
    }

    public void exportRechargeExcel(CurrentAccount currentAccount, OrderInfoQueryBean queryBean, HttpServletRequest request,
                                    HttpServletResponse response) {
//        String userId = String.valueOf(currentAccount.getLoginId());
        try {
            // 是否有权限查看所有订单
//            boolean isAllList = tdCustInfoService.isAllList(userId);
            List<ExportOrderInfoBean> list = null;

            String[] headers = {"订单号", "商户编号", "商户名", "产品号", "渠道", "下级渠道", "中信订单号", "渠道订单号", "网关版本", "订单金额", "付款金额",
                    "结算金额", "代金券", "商品描述", "付款方式", "订单时间", "订单超时时间", "订单失效时间", "签名类型", "签名字符串", "订单状态", "通知时间", "通知次数",
                    "通知结果(0成功500失败)", "订单创建时间", "修改时间", "修改人", "买家联系方式", "买家联系人", "订单支付成功时间", "订单退款时间", "提交核心状态",
                    "核心流水号", "核心返回信息", "核心返回码", "核心返回时间", "下级渠道名", "支付产品"};

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            if (StringUtils.isBlank(queryBean.getCreateTimeE())) {
                queryBean.setCreateTimeE(sdf.format(cal.getTime()));
            }
            if (StringUtils.isBlank(queryBean.getCreateTimeB())) {
                // 默认开始日期为前三个月
                cal.add(Calendar.DAY_OF_YEAR, -90);
                queryBean.setCreateTimeB(sdf.format(cal.getTime()));
            }

            if (null != currentAccount.getLoginId())
                list = orderMapperSlave.getOrderInfoListExport(queryBean);
//            } else {
//                queryBean.setUserId(userId);
//                list = orderInfoService.getMyOrderInfoListExport(queryBean);
//            }
            String fileName = DatetimeUtils.dateSecond() + "_交易信息.xls";
            Map<String, String> fileInfo = orderInfoService.exportExcel(list, headers, fileName, "交易信息", request);
            DownLoadUtil.downLoadFile(fileInfo.get("filePath"), response, fileInfo.get("fileName"), "xls");
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException(e.getMessage());
        }
    }

    public PageInfo<OrderInfoBean> orderExceptionList(CurrentAccount currentAccount, OrderInfoQueryBean queryBean) {
        List<OrderInfoBean> orderExceptionList = null;
        try {
            orderExceptionList = orderMapperSlave.getOrderExceptionList(queryBean);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException(e.getMessage());
        }
        return new PageInfo<>(orderExceptionList);
    }

    public DealOperation detailException(OrderInfoQueryBean queryBean) {
        // 查询订单信息
        DealOperation operation = null;
        switch (queryBean.getOrderType()) {
            case Constant.COMBINED_TYPE_PAY: // 支付订单
                operation = orderMapperSlave.getOrderInfoDetail(queryBean.getOrderId());
                break;
            case Constant.COMBINED_TYPE_REFUND: // 退款订单
                operation = orderMapperSlave.getRefundDetail(queryBean.getRefundId());
                break;
        }
        return operation;
    }

    public ResultData nextStep(OrderInfoQueryBean queryBean) {
        try {
            orderInfoService.nextStep(queryBean);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException(e.getMessage());
        }
        return ResultData.success();
    }

}
