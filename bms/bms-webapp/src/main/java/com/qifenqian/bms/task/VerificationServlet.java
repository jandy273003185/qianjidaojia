package com.qifenqian.bms.task;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qifenqian.bms.accounting.withdraw.bean.WithdrawChild;
import com.qifenqian.bms.accounting.withdraw.service.WithdrawService;
import com.qifenqian.bms.basemanager.merchantwithdraw.service.MerchantWithdrawService;
import com.qifenqian.bms.platform.common.utils.SpringUtils;
import com.qifenqian.bms.platform.third.scheduler.ISchedulerServlet;

/**
 * 提现核销
 * @author pc
 *
 */
@WebServlet(name = "VerificationServlet", urlPatterns = { TaskPath.BASE + TaskPath.VERIFICATIONSERVLET })
public class VerificationServlet extends ISchedulerServlet {

	private static final long serialVersionUID = 6982090545082010417L;
	

	private Logger logger = LoggerFactory.getLogger(VerificationServlet.class);
	
	private WithdrawService withdrawService = SpringUtils.getBean(WithdrawService.class);
	
	 private MerchantWithdrawService merchantWithdrawService = SpringUtils.getBean(MerchantWithdrawService.class);
	
	@Override
	public String Subject(HttpServletRequest request, HttpServletResponse response) {
		
		logger.info(">>>>>>>>>>>>>>>>>>提现核销<<<<<<<<<<<<<<<<<<<");
		try {
			//用户提现核销流水
			List<WithdrawChild> withdrawCustomer =  withdrawService.selectCustomerVerification();
			
			if(null != withdrawCustomer && withdrawCustomer.size()>0){
				List<String> userSN = new ArrayList<>();
				for(WithdrawChild withdrawChild: withdrawCustomer){
					userSN.add(withdrawChild.getWithdrawSn());
				}
				logger.info(">>>>>>>>>>>>>>>>更改用户提现流水核销状态<<<<<<<<<<<<<<<<<<<<<<");
				withdrawService.updateCustomerWithdraw(userSN);
			}
			
			
			//商户提现核销流水
			List<WithdrawChild> withdrawMerchant =  withdrawService.selectMerchantVerification();
			
			if(null != withdrawMerchant && withdrawMerchant.size()>0){
				List<String> merchantSN = new ArrayList<>();
				for(WithdrawChild withdrawChild: withdrawMerchant){
					merchantSN.add(withdrawChild.getWithdrawSn());
				}
				
				logger.info(">>>>>>>>>>>>>>>>更改商户提现流水核销状态<<<<<<<<<<<<<<<<<<<<<<");
				merchantWithdrawService.updateMerchantWithdraw(merchantSN);
			}
			request.setAttribute(EXECUTE_DESC,
					"VerificationServlet.java Subject() SUCCESS");
			
			logger.info(">>>>>>>>>>>>>>>>更改提现流水核销状态成功<<<<<<<<<<<<<<<<<<<<<<");
		} catch (Exception e) {
			logger.error("提现核销任务失败：" , e);
			request.setAttribute(EXECUTE_DESC,
					"VerificationServlet.java Subject() ERROR：" + e);
			return EXECUTE_FAILURE;
		}
		return EXECUTE_SUCCESS;
	}

}
