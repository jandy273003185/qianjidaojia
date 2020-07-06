package com.qifenqian.bms.sms.message.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.basemanager.Constant;
import com.qifenqian.bms.sms.message.bean.BaseMessage;
import com.qifenqian.bms.sms.message.mapper.BaseMessageMapper;

@Service
@Scope("prototype")
public class BatchSaveMessageThread extends Thread {

	private static Logger logger = LoggerFactory.getLogger(BatchSaveMessageThread.class);

	private HSSFRow hssfRow = null;

	private String sendMessageContent = null;
	private String dictpath = null;

	private String sheetName = null;

	private String instUser = null;

	private static final String INIT = "INIT";

	@Autowired
	private BaseMessageMapper baseMessageMapper;

	public void setHssfRow(HSSFRow hssfRow) {
		this.hssfRow = hssfRow;
	}

	public void setSendMessageContent(String sendMessageContent) {
		this.sendMessageContent = sendMessageContent;
	}

	public void setInstUser(String instUser) {
		this.instUser = instUser;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}
	

	public String getDictpath() {
		return dictpath;
	}

	public void setDictpath(String dictpath) {
		this.dictpath = dictpath;
	}

	/***
	 * 保存数据
	 * 
	 * @param filePath
	 * @throws IOException
	 */
	public void run() {
		if (null == hssfRow) {
			throw new RuntimeException("发送对象为空");
		}
		if (StringUtils.isBlank(sendMessageContent)) {
			throw new RuntimeException("发送内容为空");
		}
		if (StringUtils.isBlank(instUser)) {
			throw new RuntimeException("创建人为空");
		}

		BaseMessage messageBean = new BaseMessage();
		String datedata = dataToString(sheetName);
		if(dictpath.equals(Constant.SEND_BATCH_MESSAGE)){
				HSSFCell name = hssfRow.getCell(1);
				HSSFCell amt = hssfRow.getCell(2);
				HSSFCell mobile = hssfRow.getCell(3);				
				
				if (!StringUtils.isBlank(getValue(name)) && !StringUtils.isBlank(getValue(amt))
						&& !StringUtils.isBlank(getValue(mobile))) {
					messageBean.setCustName(getValue(name));
					messageBean.setCustAmt(new BigDecimal(getValue(amt)));
					messageBean.setMobile(getValue(mobile));
					messageBean.setInstUser(instUser);
					messageBean.setDateData(sheetName);
					messageBean.setStatus(INIT);
					String content = sendMessageContent.replace("{custName}", getValue(name))
							.replace("{custAmt}", getValue(amt)).replace("{dateData}", datedata);
					messageBean.setContent(content);
					logger.info("上传数据对象========={}", JSONObject.toJSONString(messageBean));
					baseMessageMapper.insertBaseMessage(messageBean);
				}
		}else{
			HSSFCell name = hssfRow.getCell(1);
			HSSFCell mobile = hssfRow.getCell(2);
			if (!StringUtils.isBlank(getValue(name)) 
					&& !StringUtils.isBlank(getValue(mobile))) {	
			messageBean.setCustName(getValue(name));
			messageBean.setMobile(getValue(mobile));
			messageBean.setCustAmt(new BigDecimal(0.00));
			messageBean.setInstUser(instUser);
			messageBean.setDateData(sheetName);
			messageBean.setStatus(INIT);
			messageBean.setContent(sendMessageContent);
			logger.info("上传数据对象========={}", JSONObject.toJSONString(messageBean));
			baseMessageMapper.insertBaseMessage(messageBean);
			}
		}
	}

	/***
	 * 获取值
	 * 
	 * @param hssfCell
	 * @return
	 */
	@SuppressWarnings({ "static-access" })
	private String getValue(HSSFCell hssfCell) {
		if (hssfCell.getCellType() == hssfCell.CELL_TYPE_FORMULA) {
			/** 返回布尔类型的值 **/
			return String.valueOf(hssfCell.getCellFormula());
		} else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
			/** 返回数值类型的值 **/
			return new DecimalFormat("0").format(hssfCell.getNumericCellValue());
		} else {
			/** 返回字符串类型的值 **/
			return String.valueOf(hssfCell.getStringCellValue());
		}
	}
	/***
	 * 转换字符串
	 * @param str
	 * @return
	 */
	public static String dataToString(String str) {
		SimpleDateFormat toDataSdf = new SimpleDateFormat("yyyy-MM-dd");
		String result = "";
		try {
			Date date = toDataSdf.parse(str);
			SimpleDateFormat toStrSdf = new SimpleDateFormat("yyyy年MM月dd日");
			result = toStrSdf.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}
}
