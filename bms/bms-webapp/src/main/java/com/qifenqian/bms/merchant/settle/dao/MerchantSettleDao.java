package com.qifenqian.bms.merchant.settle.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.merchant.settle.bean.MerchantSettle;
import com.qifenqian.bms.merchant.settle.mapper.MerchantSettleMapper;
import com.qifenqian.bms.merchant.settle.type.MerchantSettleStatus;
import com.qifenqian.bms.platform.web.admin.utils.WebUtils;
import com.qifenqian.bms.platform.web.page.Page;

/**
 * @project sevenpay-bms-web
 * @fileName MerchantSettleDao.java
 * @author huiquan.ma
 * @date 2015年10月13日
 * @memo 
 */
@Repository
public class MerchantSettleDao {

	private static Logger logger = LoggerFactory.getLogger(MerchantSettleDao.class);
	
	@Autowired
	private MerchantSettleMapper merchantSettleMapper;
	
	@Page
	public List<MerchantSettle> selectListByPage(MerchantSettle selectBean) {
		return merchantSettleMapper.selectList(selectBean);
	}
	public String selectTdloginUserInfoByMouble(String custId){
		return merchantSettleMapper.selectTdloginUserInfoByMouble(custId);
	}
	@Page
	public List<MerchantSettle> selectAgencyList(MerchantSettle selectBean) {
		return merchantSettleMapper.selectAgencyList(selectBean);
	}
	
	/**
	 * 复核事后处理
	 * @param updateBean
	 * @throws Exception
	 */
	public void dealAfterAudit(MerchantSettle updateBean) throws Exception {
		// 若是结算金额为0，不需要核销
		if(updateBean.getSettleAmt().compareTo(BigDecimal.ZERO) == 0 && MerchantSettleStatus.AUDIT_PASS == updateBean.getStatus()) {
			Date now = new Date();
			String nowDate = DateFormatUtils.format(now, "yyyy-MM-dd");
			// 更新结算表状态为已结算
			// 更新
			MerchantSettle updateSettleBean = new MerchantSettle();
			updateSettleBean.setCustId(updateBean.getCustId());
			updateSettleBean.setSettleBeginDate(DateFormatUtils.format(DateUtils.parseDate(updateBean.getSettleBeginDate(), "yyyy-MM-dd"), "yyyyMMdd"));
			updateSettleBean.setSettleEndDate(DateFormatUtils.format(DateUtils.parseDate(updateBean.getSettleEndDate(), "yyyy-MM-dd"), "yyyyMMdd"));
			updateSettleBean.setFinishDate(nowDate);
			merchantSettleMapper.updateToSettle(updateSettleBean);
			
			updateBean.setVerifiedUser(String.valueOf(WebUtils.getUserInfo().getUserId()));
			updateBean.setVerifiedDatetime(now);
			updateBean.setStatus(MerchantSettleStatus.VERIFIED);
			updateBean.setFinishDate(nowDate);
		} 
			
		merchantSettleMapper.updateById(updateBean);
	}
	
	/**
	 * 核销事后处理
	 * @param updateBean
	 * @throws Exception
	 */
	public void dealAfterVerified(MerchantSettle updateBean) throws Exception {
		
		logger.info("核销事后处理Bean : {}",JSONObject.toJSONString(updateBean,true));
		
		merchantSettleMapper.updateById(updateBean);
		// 更新结算表状态为已结算
		if(MerchantSettleStatus.VERIFIED == updateBean.getStatus()) {
			String nowDate = DateFormatUtils.format(new Date(), "yyyy-MM-dd");
			// 更新
			// 获取联合列表
			List<MerchantSettle> detailList = merchantSettleMapper.selectDetailListByTogetherId(updateBean.getId());
			if(detailList.size() > 0) {
				// 联合的,分次更新
				for(MerchantSettle detail : detailList) {
					MerchantSettle updateSettleBean = new MerchantSettle();
					updateSettleBean.setCustId(detail.getCustId());
					updateSettleBean.setSettleBeginDate(DateFormatUtils.format(DateUtils.parseDate(detail.getSettleBeginDate(), "yyyy-MM-dd"), "yyyyMMdd"));
					updateSettleBean.setSettleEndDate(DateFormatUtils.format(DateUtils.parseDate(detail.getSettleEndDate(), "yyyy-MM-dd"), "yyyyMMdd"));
					updateSettleBean.setFinishDate(nowDate);
					merchantSettleMapper.updateToSettle(updateSettleBean);
				}
			} else {
				// 若是为空则非联合
				MerchantSettle updateSettleBean = new MerchantSettle();
				updateSettleBean.setCustId(updateBean.getCustId());
				updateSettleBean.setSettleBeginDate(DateFormatUtils.format(DateUtils.parseDate(updateBean.getSettleBeginDate(), "yyyy-MM-dd"), "yyyyMMdd"));
				updateSettleBean.setSettleEndDate(DateFormatUtils.format(DateUtils.parseDate(updateBean.getSettleEndDate(), "yyyy-MM-dd"), "yyyyMMdd"));
				updateSettleBean.setFinishDate(nowDate);
				merchantSettleMapper.updateToSettle(updateSettleBean);
			}
		}
	}
}


