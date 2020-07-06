package com.qifenqian.bms.basemanager.custInfo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.basemanager.Constant;
import com.qifenqian.bms.basemanager.custInfo.bean.TdCustInfo;
import com.qifenqian.bms.basemanager.custInfo.bean.TdCustInfoExcel;
import com.qifenqian.bms.basemanager.custInfo.bean.TdLoginUserInfo;
import com.qifenqian.bms.basemanager.custInfo.dao.TdCustInfoDao;
import com.qifenqian.bms.basemanager.custInfo.mapper.QueryAccountMapper;
import com.qifenqian.bms.basemanager.custInfo.mapper.TdCustInfoMapper;
import com.qifenqian.bms.basemanager.merchant.bean.CustScan;
import com.qifenqian.bms.basemanager.merchant.service.MerchantService;
import com.qifenqian.bms.basemanager.utils.GenSN;
import com.qifenqian.bms.platform.common.utils.SpringUtils;
import com.qifenqian.bms.platform.web.admin.user.bean.User;
import com.qifenqian.bms.platform.web.admin.utils.WebUtils;
import com.sevenpay.invoke.SevenpayCoreServiceInterface;
import com.sevenpay.invoke.common.message.request.RequestMessage;
import com.sevenpay.invoke.common.message.response.ResponseMessage;
import com.sevenpay.invoke.common.type.RequestColumnValues;
import com.sevenpay.invoke.transaction.editcust.EditAcctSevenCustRequest;
import com.sevenpay.invoke.transaction.editcust.EditAcctSevenCustResponse;
import com.sevenpay.invoke.transaction.querybankcard.bean.BankCard;

/**
 * 消费者管理
 * 
 * @author pc
 *
 */
@Service
public class TdCustInfoService {

	@Autowired
	private TdCustInfoMapper custInfoMapper;

	@Autowired
	private QueryAccountMapper aueryAccountMapper;

	@Autowired
	SevenpayCoreServiceInterface sevenpayCoreServiceInterface;
	
	@Autowired
	private MerchantService merchantService;

	@Autowired
	private TdCustInfoDao custInfoDao;
	private static final Logger logger = LoggerFactory.getLogger(TdCustInfoService.class);

	/**
	 * 消费者列表
	 * 
	 * @return
	 */
	public List<TdCustInfo> selectCustInfos(TdCustInfo custInfo) {
		logger.info("消费者列表查询  [{}]", JSONObject.toJSONString(custInfo, true));
		return custInfoDao.selectCustInfos(custInfo);
	}
	
	/**
	 * 导出消费者列表
	 * @param custInfo
	 * @return
	 */

	public List<TdCustInfoExcel> exportCustInfos(TdCustInfo custInfo) {
		logger.info("导出列表查询  [{}]", JSONObject.toJSONString(custInfo, true));
		return custInfoMapper.exportCustInfos(custInfo);
	}
	
	
	public   TdCustInfo   selectByBean(TdCustInfo tdCustInfo){
		return custInfoMapper.selectByBean(tdCustInfo);
	}
	/**
	 * 更新消费者信息
	 * 
	 * @param tdCustInfo
	 */
	public void updateCustInfo(TdCustInfo tdCustInfo) {
		if (null == tdCustInfo) {
			throw new IllegalArgumentException("客户对象为空");
		}
		if(null!=tdCustInfo.getCustName()){
			aueryAccountMapper.updateAcctNameByCustName(tdCustInfo);
		}
		custInfoMapper.updateCustInfo(tdCustInfo);
	}

	/**
	 * 重置支付密码
	 */
	public ResponseMessage<EditAcctSevenCustResponse> editPaypassword(TdCustInfo tdCustInfo, String newPin) {

		SevenpayCoreServiceInterface sevenpayCoreServiceInterface = (SevenpayCoreServiceInterface) SpringUtils.getBean("coreHttpInvokerProxy");

		logger.info("开始通知核心更新密码。");
		ResponseMessage<EditAcctSevenCustResponse> rsp = null;
		try {
			RequestMessage<EditAcctSevenCustRequest> requestMessage = new RequestMessage<EditAcctSevenCustRequest>();
			{
				requestMessage.setReqSysId(RequestColumnValues.ReqSysId.BMS);
				requestMessage.setReqDatetime(new Date());
				requestMessage.setReqSerialId(GenSN.getSN());
				requestMessage.setMsgType(RequestColumnValues.MsgType.ACCT_CUST_EDIT);
				requestMessage.setReqMsgNum(1);

				EditAcctSevenCustRequest request = new EditAcctSevenCustRequest();
				{
					// 客户信息
					request.setCustId(tdCustInfo.getCustId());
					request.setModifyType(RequestColumnValues.ModifyType.PIN_RESET);
					request.setNewPin(newPin);
					request.setIdCode(tdCustInfo.getCertifyNo());
					request.setIdType(RequestColumnValues.IdType.IDENTITY);
				}

				requestMessage.setRequest(request);

				logger.info("通知核心更新密码开始发送报文：" + JSONObject.toJSONString(requestMessage, true));

				rsp = sevenpayCoreServiceInterface.editAcctSevenCust(requestMessage);

				logger.info("通知核心更新密码接收结果报文：" + JSONObject.toJSONString(rsp, true));
			}
		} catch (Exception e) {
			logger.error("重置支付密码异常", e);
			throw e;
		}

		return rsp;
	}

	/**
	 * 根据ID查找消费者信息
	 * 
	 * @param id
	 * @return
	 */
	public TdCustInfo selectById(String id) {

		return custInfoMapper.selectById(id);
	}

	/**
	 * 修改客服信息
	 * @param tdCustInfo
	 */
	public void editTdCustInfo(TdCustInfo tdCustInfo){
		if(null == tdCustInfo){
			throw new IllegalArgumentException("修改客服信息对象为空");
		}
		
		User user = WebUtils.getUserInfo();
		// 更新注册信息
		updateCustInfo(tdCustInfo);
		// 更新登录信息
		updateCustLoginInfo(tdCustInfo);
		CustScan custScan = new CustScan();
		custScan.setCustId(tdCustInfo.getCustId());
		custScan.setCustName(tdCustInfo.getCustName());
		custScan.setModifyId(String.valueOf(user.getUserId()));
		merchantService.updateCustSanById(custScan);
	}
	
	/**
	 * 初始化状态集合
	 * 
	 * @return
	 */
	public List<TdCustInfo> getCustStates() {
		List<TdCustInfo> sustStates = new ArrayList<TdCustInfo>();
		TdCustInfo normal = new TdCustInfo();
		normal.setState(Constant.LOGIN_STATE_NORMAL);
		normal.setDesc("正常");
		TdCustInfo stop = new TdCustInfo();
		stop.setState(Constant.LOGIN_STATE_STOP);
		stop.setDesc("停用");
		TdCustInfo freeze = new TdCustInfo();
		freeze.setState(Constant.LOGIN_STATE_FREEZE);
		freeze.setDesc("登录账户冻结");
		TdCustInfo waitActivate = new TdCustInfo();
		waitActivate.setState(Constant.LOGIN_STATE_WAIT_ACTIVATE);
		waitActivate.setDesc("注册待激活");
		sustStates.add(normal);
		sustStates.add(stop);
		sustStates.add(freeze);
		sustStates.add(waitActivate);
		return sustStates;
	}

	/**
	 * 查询七分钱余额
	 * 
	 * @param custInfo
	 * @return
	 */
	public TdCustInfo queryAccount(String custId) {
		TdCustInfo info = new TdCustInfo();
		String qfqTotalAmt = "";
		if (StringUtils.isEmpty(custId)) {
			throw new IllegalArgumentException("客户Id为空");
		}
		try {
			info.setCustId(custId);
			List<TdCustInfo> getActSevenCustList = aueryAccountMapper.getActSevenCustList(info);
			if (getActSevenCustList.size() > 0) {
				qfqTotalAmt = getActSevenCustList.get(0).getQfqTotalAmt().toString();
				info.setQfqTotalAmt(qfqTotalAmt);
				info.setQfqAccId(getActSevenCustList.get(0).getQfqAccId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return info;
	}

	public int updateCustLoginInfo(TdCustInfo updateBean) {
		if (null == updateBean) {
			throw new IllegalArgumentException("修改客户登录对象为空");
		}
		if (StringUtils.isBlank(updateBean.getCustId())) {
			throw new IllegalArgumentException("修改客户登录对象为空");
		}
		return custInfoMapper.updateCustLoginInfo(updateBean);
	}

	public List<BankCard> queryBankCardList(String custId) {
		return aueryAccountMapper.queryBankCardList(custId);
	}

	public TdLoginUserInfo validateCustMobile(String mobile, String custId) {
		return custInfoMapper.validateCustMobile(mobile, custId);
	}

	public TdLoginUserInfo selectTdLoginInfo(String custId){
		return custInfoMapper.selectLoginInfo(custId);
	}
	public int updateInfo(TdCustInfo custInfo){
		if(null == custInfo){
			throw new IllegalArgumentException("修改对象为空");
		}
		if(StringUtils.isBlank(custInfo.getCustId())){
			throw new IllegalArgumentException("修改对象Id为空");
		}
		return custInfoMapper.updateInfo(custInfo);
	}
	public TdLoginUserInfo validateEmail(String email, String custId) {
		return custInfoMapper.validateEmail(email, custId);
	}

	public TdCustInfo validateMerchantName(String name) {
		return custInfoMapper.validateMerchantName(name);
	}
	
	/**
	 * 是否有权限读取所有订单
	 * @param userId
	 * @return
	 */
	public boolean isAllList(String userId){
		String result = custInfoDao.isAllList(userId);
		if(result == null){
			return false;
		}else{
			return true;
		}
	}

	/**
	 * 是否有权限新增商户信息
	 * @param userId
	 * @return
	 */
	public boolean isAddMerchant(String userId) {
		String result = custInfoDao.isAddMerchant(userId);
		if(result == null){
			return false;
		}else{
			return true;
		}
	}

	public TdCustInfo selectByMerchantCode(String merchantCode) {
		return custInfoDao.selectByMerchantCode(merchantCode);
	}
}
