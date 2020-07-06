package com.qifenqian.bms.v2.biz.aggregatepayment.orderinfo.service;

import com.github.pagehelper.PageInfo;
import com.qifenqian.bms.basemanager.aggregatepayment.orderinfo.bean.RefundBean;
import com.qifenqian.bms.basemanager.aggregatepayment.orderinfo.bean.RefundQueryBean;
import com.qifenqian.bms.basemanager.aggregatepayment.orderinfo.mapper.OrderMapperSlave;
import com.qifenqian.bms.basemanager.aggregatepayment.orderinfo.service.OrderRefundService;
import com.qifenqian.bms.basemanager.trade.service.DownLoadUtil;
import com.qifenqian.bms.basemanager.utils.DatetimeUtils;
import com.qifenqian.bms.v2.biz.system.user.bean.domain.CurrentAccount;
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
import java.util.List;
import java.util.Map;

@Service
public class BizOrderRefundService extends BaseService {

    @Autowired
    private OrderMapperSlave orderMapperSlave;

    @Autowired
    private OrderRefundService orderRefundService;

    public PageInfo<RefundBean> list(CurrentAccount currentAccount, RefundQueryBean queryBean) {
        //是否有权限查看所有订单
//        boolean isAllList = tdCustInfoService.isAllList(userId);
        List<RefundBean> list = null;
        if (null != currentAccount.getLoginId())
            list = orderMapperSlave.getOrderRefundList(queryBean);
//        } else {
//            queryBean.setUserId(userId);
//            list = orderMapperSlave.queryMyRefundList(queryBean);
//        }
        return new PageInfo<>(list);
    }
/**
  * @description   
  * @param [currentAccount, queryBean, request, response]
  * @return void 
  * @author LvFeng
  * @date 2020/4/27 14:48 
  */
    public void exportRechargeExcel(CurrentAccount currentAccount, RefundQueryBean queryBean, HttpServletRequest request,
                                    HttpServletResponse response) {
        try {
            String[] headers = {"退款编号", "商户退款流水号", "原订单ID", "商户编号", "原订单总金额", "退款金额", "手续费", "中信交易号", "中信退款ID", "退款渠道", "渠道返回退款错误码", "渠道退款返回结果描述", "退款时间", "退款状态", "退款发起时间", "发起退款操作员", "原订单渠道", "产品名称"};
            List<RefundBean> list = null;
            //是否有权限查看所有订单
//            boolean isAllList = tdCustInfoService.isAllList(userId);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            if (StringUtils.isBlank(queryBean.getCreateTimeE())) {
                queryBean.setCreateTimeE(sdf.format(cal.getTime()));
            }
            if (StringUtils.isBlank(queryBean.getCreateTimeB())) {
                //默认开始日期为前三个月
                cal.add(Calendar.DAY_OF_YEAR, -90);
                queryBean.setCreateTimeB(sdf.format(cal.getTime()));
            }

            if (null != currentAccount.getLoginId())
                list = orderRefundService.getOrderRefundListExport(queryBean);
//            } else {
//                queryBean.setUserId(String.valueOf(currentAccount.getLoginId()));
//                list = orderRefundService.getMyOrderRefundListExport(queryBean);
//            }

            String fileName = DatetimeUtils.dateSecond() + "_退款信息.xls";
            Map<String, String> fileInfo = orderRefundService.exportExcel(list, headers, fileName, "退款信息", request);
            DownLoadUtil.downLoadFile(fileInfo.get("filePath"), response, fileInfo.get("fileName"), "xls");
            logger.info("导出excel退款信息成功");
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException(e.getMessage());
        }
    }
}
