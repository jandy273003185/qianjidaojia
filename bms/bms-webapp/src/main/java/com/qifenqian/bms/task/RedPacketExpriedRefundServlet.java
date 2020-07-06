package com.qifenqian.bms.task;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qifenqian.bms.platform.common.utils.SpringUtils;
import com.qifenqian.bms.platform.third.scheduler.ISchedulerServlet;
import com.qifenqian.bms.sns.redpacket.bean.RedEnvelopeInfo;
import com.qifenqian.bms.task.service.RedPacketExpriedRefundService;
import com.qifenqian.bms.task.service.RedPacketExpriedRefundTread;

/**
 * * 红包过期自动退款
 *
 * @author shen
 */
@WebServlet(
    name = "RedPacketExpriedRefundServlet",
    urlPatterns = {TaskPath.BASE + TaskPath.REDPACKET_EXPRIED_REFUND})
public class RedPacketExpriedRefundServlet extends ISchedulerServlet {

  /** */
  private static final long serialVersionUID = -6207621112804295762L;

  private Logger logger = LoggerFactory.getLogger(RedPacketExpriedRefundServlet.class);

  private RedPacketExpriedRefundService redPacketExpriedRefundService =
      SpringUtils.getBean(RedPacketExpriedRefundService.class);

  ExecutorService expiredRedPacketExecutors = Executors.newFixedThreadPool(10);

  @Override
  public String Subject(HttpServletRequest request, HttpServletResponse response) {
    try {
      logger.info("============红包过期自动退款 Start======");

      List<RedEnvelopeInfo> expriedInfoList =
          redPacketExpriedRefundService.selectExpriedRedPacket();

      logger.info("红包过期待退款的订单数量：" + expriedInfoList.size());
      if (expriedInfoList.size() > 0) {
        for (RedEnvelopeInfo expriedInfo : expriedInfoList) {
          RedPacketExpriedRefundTread redPacketExpriedRefundTread =
              (RedPacketExpriedRefundTread) SpringUtils.getBean("redPacketExpriedRefundTread");
          redPacketExpriedRefundTread.setRedEnvelopeInfo(expriedInfo);
          expiredRedPacketExecutors.execute(redPacketExpriedRefundTread);
        }
      }
      logger.info("============红包过期自动退款 Start======");
      request.setAttribute(EXECUTE_DESC, "RedPacketExpriedRefundServlet.java Subject() SUCCESS");
    } catch (Exception e) {
      logger.error("处理超时未支付的订单结果异常:", e);
      request.setAttribute(EXECUTE_DESC, "RedPacketExpriedRefundServlet.java Subject() ERROR：" + e);
      return EXECUTE_FAILURE;
    }
    return EXECUTE_SUCCESS;
  }

  @Override
  public void destroy() {
    super.destroy();
    expiredRedPacketExecutors.shutdown();
  }
}
