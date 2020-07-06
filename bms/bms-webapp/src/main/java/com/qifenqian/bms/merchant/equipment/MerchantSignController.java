package com.qifenqian.bms.merchant.equipment;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.basemanager.custInfo.service.TdCustInfoService;
import com.qifenqian.bms.basemanager.merchant.bean.Merchant;
import com.qifenqian.bms.basemanager.merchant.mapper.MerchantMapper;
import com.qifenqian.bms.materiel.bean.Materiel;
import com.qifenqian.bms.materiel.mapper.MaterielMapper;
import com.qifenqian.bms.materiel.service.MaterielService;
import com.qifenqian.bms.merchant.equipment.bean.DeviceLogin;
import com.qifenqian.bms.merchant.equipment.bean.MerchantSign;
import com.qifenqian.bms.merchant.equipment.mapper.MerchantSignMapper;
import com.qifenqian.bms.merchant.equipment.service.MerchantSignService;

/**
 * 商户设备信息控制层
 * 
 * @project qifenqian-bms
 * @fileName MerchantSignController.java
 * @author wuzz
 * @date 2019年11月9日
 * @memo
 */
@Controller
@RequestMapping("/merchant/equipment")
public class MerchantSignController {

	private Logger logger = LoggerFactory.getLogger(MerchantSignController.class);

	@Autowired
	private MerchantSignService merchantSignService;
	
	@Autowired
	private MerchantMapper merchantMapper;
	
	@Autowired
	private MaterielService materielService;
	
	@Autowired
	private MaterielMapper materielMapper;
	
	@Autowired
	private MerchantSignMapper merchantSignMapper;
	@Autowired 
	private TdCustInfoService  custInfoService;
	/**
	 * 进入商户设备列表页面
	 * 
	 * @return
	 */
	@RequestMapping("/list")
	public ModelAndView list(MerchantSign merchantSign) {
		// 返回视图
		ModelAndView mv = new ModelAndView("/merchant/equipment/list");
		mv.addObject("merchantSignList", JSONObject.toJSON(merchantSignService.selectMerchantSignListByPage(merchantSign)));
		mv.addObject("merchantSign", merchantSign);
		//商户信息
		List<Merchant> merchantList = merchantMapper.selectMerchant();
		mv.addObject("merchantList", merchantList);
		//设备信息
		List<Materiel> materielList = materielMapper.selectMaterielList(new Materiel());
		mv.addObject("materielList", materielList);
		// 返回
		return mv;
	}
	
	/**
	 * 进入商户设备显示列表页面   zhanggc  新 
	 * 
	 * @return
	 */
	@RequestMapping("/newList")
	public ModelAndView newList(DeviceLogin deviceLogina) {
		// 返回视图
		ModelAndView mv = new ModelAndView("/merchant/equipment/newList");
		if (null != deviceLogina.getCustId() && !"".equals(deviceLogina.getCustId()) ) {
			deviceLogina.setCustId(deviceLogina.getCustId().trim());
		}
		if (StringUtils.isNotBlank(deviceLogina.getMerchantCode()) ) {
			deviceLogina.setMerchantCode(deviceLogina.getMerchantCode().trim());
		}
		if (null != deviceLogina.getMac() &&  !"".equals(deviceLogina.getMac()) ) {
			deviceLogina.setMac(deviceLogina.getMac().trim());
		}
		if (StringUtils.isNotBlank(deviceLogina.getMerchantName()) ) {
			deviceLogina.setMerchantName(deviceLogina.getMerchantName().trim());
		}
		if (StringUtils.isNotBlank(deviceLogina.getClientType()) ) {
			deviceLogina.setClientType(deviceLogina.getClientType().trim());
		}
		List<DeviceLogin> deviceLogin = merchantSignService.selectDeviceLoginById(deviceLogina);
		mv.addObject("materielList",  JSONObject.toJSON(deviceLogin));
		mv.addObject("deviceLogina", deviceLogina);
		// 返回
		return mv;
	}
	/**
	 * @param deviceLogina  zhanggc  查询设备详情
	 * @return
	 */
	@RequestMapping("/showDeviceLogin")
	public ModelAndView showDeviceLogin(DeviceLogin deviceLogina,String merchantName) {
		// 返回视图
		ModelAndView mv = new ModelAndView();
		List<DeviceLogin> deviceLogin = merchantSignService.selectDeviceLoginById(deviceLogina);
		mv.addObject("materielList",  JSONObject.toJSON(deviceLogin));
		mv.addObject("merchantName", merchantName);
		return mv;
	}
	
	/**
	 * 商户设备新增
	 * 
	 * @param merchantSign
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public String add(MerchantSign merchantSign) {
		// 请求bean 打印
		logger.info("请求保存MerchantSign：[{}]", JSONObject.toJSONString(merchantSign, true));
		
		JSONObject jsonObject = new JSONObject();
		try {
			MerchantSign merSign = merchantSignService.selectMerchantSignByMerIdAndTerNo(merchantSign);
			if (merSign !=null) {
				jsonObject.put("result", "FAILURE");
				jsonObject.put("message", "该商户设备已经存在");
			} else {
				//校验新增的设备编号是否存在
				Materiel materiel = materielService.selectMaterielByMachineId(merchantSign.getTerminalNo());
				if (materiel == null) {
					jsonObject.put("result", "FAILURE");
					jsonObject.put("message", "该设备编号："+merchantSign.getTerminalNo()+"不存在");
				}else {
					merchantSign.setMerchantPublicKey("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDYB797teRacGp9qlEEURlvl9ZHaDRYijWbG3vpRmfZ4XjJT55yL/UDO7DjqQBe23g1bmw6tUDIa8aHTEVvLzuy9kx8SDfKM4sz9k6u8dE4SnNvr6YlSz++iWr80p+alwh7yVAywrg3RAwaOOdOHtna2FcwBHqMBVQb7wWEP46AFwIDAQAB");
					merchantSignService.insertMerchantSignSingle(merchantSign);
					jsonObject.put("result", "SUCCESS");
				}								
			}
		} catch (Exception e) {
			logger.error("新增商户设备产品异常", e);
			jsonObject.put("result", "FAILURE");
			jsonObject.put("message", e.getMessage());
		}
		return jsonObject.toJSONString();
	}

	/**
	 * 商户设备信息修改
	 * 
	 * @param merchantProduct
	 * @return
	 */
	@RequestMapping("/update")
	@ResponseBody
	public String edit(MerchantSign merchantSign) {
		// 请求bean 打印
		logger.info("请求修改merchantSign：[{}]", JSONObject.toJSONString(merchantSign, true));

		JSONObject jsonObject = new JSONObject();	
		if (null == merchantSign) {
			throw new IllegalArgumentException("商户设备对象为空");
		}		
		try {
			//查询需要修改的商户产品信息是否存在
			MerchantSign merSign = merchantSignService.selectMerchantSignById(merchantSign.getId());
			if (merSign == null) {
				jsonObject.put("result", "FAILURE");
				jsonObject.put("message", "商户设备信息不存在");
			} else {				
				merchantSignService.updateMerchantSign(merchantSign);
				jsonObject.put("result", "SUCCESS");					
			}
		} catch (Exception e) {
			logger.error("修改商户设备异常", e);
			jsonObject.put("result", "FAILURE");
			jsonObject.put("message", e.getMessage());
		}
		return jsonObject.toJSONString();
	}
	
	
	/**
	 * 商户设备删除
	 * 
	 * @param merchantProduct
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public String delete(int id) {
		logger.info("请求商户设备ID：[{}]", id);

		JSONObject jsonObject = new JSONObject();
		try {
			// 删除
			merchantSignService.deleteMerchantSign(id);
			jsonObject.put("result", "SUCCESS");
		} catch (Exception e) {
			logger.error("删除商户设备信息异常", e);
			jsonObject.put("result", "FAILURE");
			jsonObject.put("message", e.getMessage());
		}
		return jsonObject.toJSONString();
	}
	


}
