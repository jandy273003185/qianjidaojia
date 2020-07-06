package com.qifenqian.bms.basemanager.protocolcontent.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.qifenqian.bms.accounting.utils.ExportExcel;
import com.qifenqian.bms.basemanager.Constant;
import com.qifenqian.bms.basemanager.custInfo.service.TdCustInfoService;
import com.qifenqian.bms.basemanager.merchant.bean.TdLoginUserInfo;
import com.qifenqian.bms.basemanager.merchant.mapper.TdLoginUserInfoMapper;
import com.qifenqian.bms.basemanager.protocolcontent.bean.ProtocolColumn;
import com.qifenqian.bms.basemanager.protocolcontent.bean.ProtocolContent;
import com.qifenqian.bms.basemanager.protocolcontent.bean.ProtocolContentExportBean;
import com.qifenqian.bms.basemanager.protocolcontent.bean.ProtocolInfo;
import com.qifenqian.bms.basemanager.protocolcontent.dao.ProtocolContentDao;
import com.qifenqian.bms.basemanager.protocolcontent.mapper.ProtocolContentMapper;
import com.qifenqian.bms.basemanager.utils.GenSN;
import com.qifenqian.bms.common.util.RedisUtil;
import com.qifenqian.bms.platform.web.admin.utils.WebUtils;

import redis.clients.jedis.Jedis;


@Service
public class ProtocolContentService {

	@Autowired
	private ProtocolContentDao protocolContentDao;

	@Autowired
	private ProtocolContentMapper protocolContentMapper;
	
	@Autowired
	private TdLoginUserInfoMapper tdLoginUserInfoMapper;
	
	@Autowired
	private TdCustInfoService tdCustInfoService;
	@Value("${EXPORT_EXCEL}")
	private String EXPORT_EXCEL;
	/***
	 * 
	 * @param queryBean
	 * @return
	 */
	public List<ProtocolContent> select(ProtocolContent queryBean) {
		return protocolContentDao.select(queryBean);
	}

	public List<ProtocolContent> selectMyProto(ProtocolContent queryBean){
		return protocolContentDao.selectMyProto(queryBean);
	}
	
	public List<ProtocolContent> selectMyAgentProto(ProtocolContent queryBean){
		return protocolContentDao.selectMyAgentProto(queryBean);
	}
	
	/**
	 * 审核协议
	 * @param content
	 */
	public void auditProl(ProtocolContent content){
		try {
			/**
			 * 查询当前待审核协议
			 */
			ProtocolContent auditbean = new ProtocolContent();
			auditbean.setId(content.getId());
			auditbean.setStatus("AUDIT");
			auditbean = protocolContentDao.selectByBean(auditbean);
			
			/**
			 * 查询该商户的有效协议
			 */
			ProtocolContent selectBean = new ProtocolContent();
			selectBean.setCustId(auditbean.getCustId());
			selectBean.setStatus("VALID");
			selectBean = protocolContentDao.selectByBean(selectBean);
			//审核通过
			if("pass".equals(content.getStatus())){
				
				content.setUpdateUser(String.valueOf(WebUtils.getUserInfo().getUserId()));
				content.setStatus("VALID");//审核通过即有效
				content.setValidDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
				int result = protocolContentDao.updateProt(content);
				if(result == 1){
					
					if(selectBean != null){
						//废弃之前的数据
						selectBean.setStatus("DISABLE");
						selectBean.setUpdateUser(String.valueOf(WebUtils.getUserInfo().getUserId()));
						selectBean.setDisableDate("notNull");
						protocolContentDao.updateProt(selectBean);
						
						//插入协议数据
						String[] contents = auditbean.getProtocolContent().split("@_@");
						for (int i = 0; i < contents.length; i++) {
							ProtocolColumn cols = new ProtocolColumn();
							cols.setColumnDesc(contents[i].split(":")[0]);
							cols.setColumnCode(contents[i].split(":")[1]);
							if(contents[i].split(":").length>2){
								cols.setColumnValue(contents[i].split(":")[2]);
							}
							cols.setInstUser(auditbean.getInstUser());
							cols.setStatus("VALID");
							cols.setId(GenSN.getOperateID());
							cols.setProtocolId(auditbean.getId());
							protocolContentMapper.insertColumn(cols);
						}
						//废弃之前的栏位
						ProtocolColumn column = new ProtocolColumn();
						column.setProtocolId(selectBean.getId());
						column.setStatus("DISABLE");
						protocolContentDao.updateColumn(column);
					}
					
					//清空redies缓存
					String custId = "CUST" + content.getCustId();
					String merchantCode = content.getMerchantCode();
					releaseResies(custId,merchantCode);
					}
					
			}
			
			//审核不通过
			if("noPass".equals(content.getStatus())){
				content.setUpdateUser(String.valueOf(WebUtils.getUserInfo().getUserId()));
				content.setStatus("AUDIT_NO");//不通过
				int result = protocolContentDao.updateProt(content);
				
				//将原来协议的is_update改为NO
				if(selectBean != null){
					selectBean.setIsUpdate("NO");
					protocolContentDao.updateProt(selectBean);
				}
				
			}
		} catch (Exception e) {
			throw e;
		}
		
	}
	
	/**
	 * 删除缓存
	 * @param custId
	 * @param merchantCode
	 * @return
	 */
	public void releaseResies(String custId,String merchantCode){
		Jedis jedis = null;
		try {
			jedis = RedisUtil.getJedis();
			jedis.select(5);
			jedis.del(custId);
			jedis.del(merchantCode);
			
		} catch (Exception e) {
			throw e;
		}finally{
			RedisUtil.returnResource(jedis);
		}
	}
	
	/**
	 * 修改协议
	 * @param editProl
	 */
	public void eidtProtocol(ProtocolContent editProl){
		if (null == editProl) {
			throw new IllegalArgumentException("修改协议对象为空");
		}
		if (StringUtils.isBlank(editProl.getProtocolContent())) {
			throw new IllegalArgumentException("修改协议对象为空");
		}
		//修改当前协议的状态为 updateing修改中
//		deleteProtocol(editProl);  审核通过后 原来协议状态变更
		ProtocolContent updateContent = new ProtocolContent();
		updateContent.setId(editProl.getId());
		updateContent.setIsUpdate("UPDATEING");
		protocolContentDao.updateProt(updateContent);
		
		String instUser = String.valueOf(WebUtils.getUserInfo().getUserId());
		String id = GenSN.getOperateID();
		editProl.setId(id);
		editProl.setInstUser(instUser);
		editProl.setStatus("AUDIT");//待审核
		
//		String[] contents = editProl.getProtocolContent().split("@_@");
//		for (int i = 0; i < contents.length; i++) {
//			ProtocolColumn cols = new ProtocolColumn();
//			cols.setColumnDesc(contents[i].split(":")[0]);
//			cols.setColumnCode(contents[i].split(":")[1]);
//			cols.setColumnValue(contents[i].split(":")[2]);
//			cols.setInstUser(instUser);
//			cols.setStatus("VALID");
//			cols.setId(GenSN.getOperateID());
//			cols.setProtocolId(editProl.getId());
//			protocolContentMapper.insertColumn(cols);
//		}
		editProl.setValidDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		editProl.setDisableDate("9999-01-01");
		protocolContentMapper.insertContent(editProl);
	}
	/***
	 * 
	 * @param insertBean
	 * @return
	 */
	public void insert(ProtocolContent insertBean) {

		if (null == insertBean) {
			throw new IllegalArgumentException("新增协议对象为空");
		}
		if (StringUtils.isBlank(insertBean.getProtocolContent())) {
			throw new IllegalArgumentException("新增协议对象为空");
		}
		if (StringUtils.isBlank(insertBean.getCustId())) {
			throw new IllegalArgumentException("商户编号对象为空");
		}
		
		deleteProtocol(insertBean.getCustId());
		String instUser = String.valueOf(WebUtils.getUserInfo().getUserId());
		String id = GenSN.getOperateID();
		insertBean.setId(id);
		insertBean.setInstUser(instUser);
		insertBean.setStatus("VALID");
		
		String[] contents = insertBean.getProtocolContent().split("@_@");
		for (int i = 0; i < contents.length; i++) {
			ProtocolColumn cols = new ProtocolColumn();
			cols.setColumnDesc(contents[i].split(":")[0]);
			cols.setColumnCode(contents[i].split(":")[1]);
			
			if(contents[i].split(":").length>3){
				int index = contents[i].indexOf(":", 0);
				index = contents[i].indexOf(":",index+1);
				cols.setColumnValue(contents[i].substring(index+1));
			}else if(contents[i].split(":").length>2){
				cols.setColumnValue(contents[i].split(":")[2]);
			}
			cols.setInstUser(instUser);
			cols.setStatus("VALID");
			cols.setId(GenSN.getOperateID());
			cols.setProtocolId(insertBean.getId());
			protocolContentMapper.insertColumn(cols);
		}
		int result = protocolContentMapper.insertContent(insertBean);
		if(result>0){
			TdLoginUserInfo info = new TdLoginUserInfo();
			info.setCustId(insertBean.getCustId());
			info.setState(Constant.LOGIN_STATE_NORMAL);
			tdLoginUserInfoMapper.updateLoginUserInfo(info);
		}
	}

	public int delete(ProtocolContent deleteBean) {
		if(null==deleteBean){
			
			throw new IllegalArgumentException("删除协议对象为空");
		}
		if(StringUtils.isBlank(deleteBean.getId())){
			throw new IllegalArgumentException("删除协议对象编号为空");
		}
		deleteBean.setUpdateUser(String.valueOf(WebUtils.getUserInfo().getUserId()));
		
		//删除缓存
		String custId = "CUST" + deleteBean.getCustId();
		String merchantCode = deleteBean.getMerchantCode();
		releaseResies(custId,merchantCode);
		return protocolContentMapper.delete(deleteBean);
	}
	
	public int deleteProtocol(String custId) {
	
		ProtocolContent deleteBean = new ProtocolContent();
		deleteBean.setCustId(custId);
		deleteBean.setUpdateUser(String.valueOf(WebUtils.getUserInfo().getUserId()));
		int resultnum = protocolContentMapper.deleteProtocol(deleteBean);
		return resultnum;
	}
	
	public ProtocolContent selectProto(String custId){
		return protocolContentDao.selectProto(custId);
	}

	public List<ProtocolContentExportBean> getProtocolContentExport(ProtocolContent queryBean) {
		
		return protocolContentDao.getProtocolContentExport(queryBean);
	}
	@SuppressWarnings("finally")
	public Map<String, String> exportExcel(List excels, String[] headers, String fileName, String title, HttpServletRequest request) {
		Map<String, String> fileInfo = new HashMap<String, String>();
		OutputStream out = null;
		try {

			String exportPath = EXPORT_EXCEL;
			File saveFile = new File(exportPath);
			if (!saveFile.exists()) {
				saveFile.mkdirs();
			}
			String filePath = saveFile + File.separator + fileName;
			fileInfo.put("fileName", fileName);
			fileInfo.put("filePath", filePath);
			out = new FileOutputStream(filePath);

			ExportExcel<T> ex = new ExportExcel<T>();

			ex.exportExcel(title, headers, excels, out);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return fileInfo;
		}

	}

	public List<ProtocolContent> selectAgent(ProtocolContent queryBean) {
		return protocolContentDao.selectAgent(queryBean);
	}

	public ProtocolInfo selectProtocolInfo(String custId){
		return protocolContentDao.selectProtocolInfo(custId);
	}
	
	public List<ProtocolContent> selectNewProtocolInfo(String custId){
		return protocolContentDao.selectNewProtocolInfo(custId);
	}
	
	public ProtocolInfo selectProtocolForEmail(String custId){
		return protocolContentDao.selectProtocolForEmail(custId);
	}
}
