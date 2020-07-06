package com.qifenqian.bms.basemanager.toPayProduct.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.basemanager.toPayProduct.bean.ToPayProduct;
import com.qifenqian.bms.basemanager.toPayProduct.dao.ToPayProductDao;


@Service
public class ToPayProductService {

	private static Logger logger = LoggerFactory.getLogger(ToPayProductService.class);
	
	@Autowired
	private ToPayProductDao topayProductDao;

	public List<ToPayProduct> listProduct(ToPayProduct bean) {
		
		return topayProductDao.listProduct(bean);
	}

	public JSONObject updateProductRate(ToPayProduct bean) {
		JSONObject json = new JSONObject();
		try {
			if("99".equals(bean.getProductStatus())){
				json.put("result","FAIL");
				json.put("message", "此条商户代付产品记录异常,不能修改费率");
				return json;
			}else if("09".equals(bean.getProductStatus())){
				json.put("result","FAIL");
				json.put("message", "此条商户代付产品记录未开通,不能修改费率");
				return json;
			}
			//查询出该产品下的费率并改变状态
			this.updateRate(bean);
			//新插入一条费率数据
			this.saveProductRate(bean);
										
			
		} catch (Exception e) {
			logger.error(">>>>>>>>>修改代付费率异常 ：" + e.getMessage(), e);				
			json.put("result","FAIL");
			json.put("message", e.getMessage());
		}
		
		json.put("result", "SUCCESS");
		
		return json;
	}

	private void saveProductRate(ToPayProduct bean) {
		
		String id = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+RandomStringUtils.randomNumeric(18);
		bean.setProductStatus("00");
		bean.setId(id);
		topayProductDao.saveProductRate(bean);
		
	}

	private void updateRate(ToPayProduct bean) {
		
		bean.setProductStatus("99");
		topayProductDao.updateRate(bean);
		
	}
}
