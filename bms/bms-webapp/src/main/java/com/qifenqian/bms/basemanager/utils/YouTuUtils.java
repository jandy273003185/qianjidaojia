package com.qifenqian.bms.basemanager.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.basemanager.utils.youtu.Youtu;
import com.youtu.sign.Base64Util;

/**
 * 腾讯优图工具:
 * 
 * 解析身份证图片;
 * 解析营业执照图片;
 * 
 * 
 * @project STC_Web
 * @fileName youTuUtils.java
 * @author huiquan.ma
 * @date 2015年4月20日
 * @memo
 */
public class YouTuUtils {
	private Logger logger = LoggerFactory.getLogger(YouTuUtils.class);
	
	
	// appid, secretid secretkey请到http://open.youtu.qq.com/获取
	// 请正确填写把下面的APP_ID、SECRET_ID和SECRET_KEY
	public static final String APP_ID = "10115053";
	public static final String SECRET_ID = "AKID72Z0FcZt5MWXMNis0zlMp7D950u1nudA";
	public static final String SECRET_KEY = "TmGehdS8cv3QhBYlXEl6FoT2QfjbHl38";
		
	/****
	 * 解析图片
	 * @param url -- 图片地址
	 * @param flag-- 标识（0-身份证  3-营业执照）
	 * @return
	 */
	public JSONObject youTu(String url,String flag) {
		logger.info("********************开始解析图片内容********************");
		JSONObject object = new JSONObject();
		Youtu faceYoutu = new Youtu(APP_ID, SECRET_ID, SECRET_KEY,Youtu.API_YOUTU_END_POINT);
		try {
			StringBuffer image_data = new StringBuffer("");
//			GetBase64FromFile(url,image_data);
			
			org.json.JSONObject respose = null;
			//respose= faceYoutu.FaceCompareUrl("http://open.youtu.qq.com/content/img/slide-1.jpg","http://open.youtu.qq.com/content/img/slide-1.jpg");
			if(flag.equals("certAttribute1")){//身份证解析
				respose = faceYoutu.IdCardOcrMySelf(url,0);//0--正面  1--反面
				object.put("cardId", respose.get("id"));
				object.put("cardName", respose.get("name"));
				object.put("cardAddress", respose.get("address"));
				object.put("result", "SUCCESS");
				object.put("message", "图片解析成功");
			}else if(flag.equals("certAttribute2")){//身份证解析
				respose = faceYoutu.IdCardOcrMySelf(url,1);//0--正面  1--反面
				object.put("cardValidDate", respose.get("valid_date"));
				object.put("result", "SUCCESS");
				object.put("message", "图片解析成功");
			}else if(flag.equals("businessPhoto")){//营业执照解析
				respose =faceYoutu.bissPic(url, APP_ID, SECRET_ID);
				com.alibaba.fastjson.JSONObject mapType = com.alibaba.fastjson.JSONObject.parseObject(respose.toString());
				List<Map<String, Object>> listObjectFir = (List<Map<String, Object>>) JSONArray.parse(mapType.get("items").toString());
				 for (int i = 0; i < listObjectFir.size(); i++) {
					 Map<String, Object> map = listObjectFir.get(i);
					 for (int j = 0;j < map.size();j++) {
						 if("注册号".equals(map.get("item"))){
							 object.put("businessLicense", map.get("itemstring"));//公司名称
							 break;
						 }else if("法定代表人".equals(map.get("item"))){
							 object.put("legalPerson", map.get("itemstring"));//法定代表人
							 break;
						 }else if("地址".equals(map.get("item"))){
							 object.put("legalAddress", map.get("itemstring"));//地址
							 break;
						 }else if("公司名称".equals(map.get("item"))){
							 object.put("companyName", map.get("itemstring"));//地址
							 break;
						 }else if("营业期限".equals(map.get("item"))){
							 String [] strs = map.get("itemstring").toString().split("至");
							 SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy年MM月dd日" );
							 SimpleDateFormat sdf_ =   new SimpleDateFormat( "yyyy-MM-dd" );
							 Date date = sdf.parse(strs[0]);
							 object.put("businessTermStart", sdf_.format(date));//营业期限
							 if("长期".equals(strs[1])){
								 object.put("businessTermEnd", "长期");//营业期限
							 }else{
								 Date date_ = sdf.parse(strs[1]);
								 object.put("businessTermEnd", sdf_.format(date_));//营业期限
							 }
							 break;
						 }
					 }
				}
				object.put("result", "SUCCESS");
				object.put("message", "图片解析成功");
				
			}else if(flag.equals("bankCardPhoto")){
				respose = faceYoutu.creditCard(url, APP_ID, SECRET_ID);
				com.alibaba.fastjson.JSONObject mapType = com.alibaba.fastjson.JSONObject.parseObject(respose.toString());
				List<Map<String, Object>> listObjectFir = (List<Map<String, Object>>) JSONArray.parse(mapType.get("items").toString());
				 if(listObjectFir==null||listObjectFir.size()<=0){
					logger.error("图片解析出现问题");
					object.put("result", "FAIL");
					object.put("message", "图片解析出现问题,结果为null");
				 }else{
					 for (int i = 0; i < listObjectFir.size(); i++) {
						 Map<String, Object> map = listObjectFir.get(i);
						 for (int j = 0;j < map.size();j++) {
							 if("卡号".equals(map.get("item"))){
								 object.put("creditCardId", map.get("itemstring"));//公司名称
								 break;
							 }else if("银行信息".equals(map.get("item"))){
								 String str = map.get("itemstring").toString();
								 String cardNo = str.substring(str.indexOf("(")+1, str.lastIndexOf(")"));
								 String regex = "(^0*)|(0*$)";//(以 零个或者多个 0 结尾)   或者(以 零个或者多个 0 开头)
								 object.put("cardNo",cardNo.replaceAll(regex, ""));//银行编号
								 break;
							 }
						 }
					 }
					 object.put("result", "SUCCESS");
					 object.put("message", "图片解析成功");
					 
				 }
				
				
			}
		
		} catch (Exception e) {
			logger.error("图片解析出现问题" + e);
			object.put("result", "FAIL");
			object.put("message", e.getMessage());
		}
		return object;
	}
	
	//解析二进制流
	private void GetBase64FromFile(String filePath, StringBuffer base64)
			throws IOException {
				File imageFile = new File(filePath);
				if (imageFile.exists()) {
					InputStream in = new FileInputStream(imageFile);
					byte data[] = new byte[(int) imageFile.length()]; // 创建合适文件大小的数组
					in.read(data); // 读取文件中的内容到b[]数组
					in.close();
					base64.append(Base64Util.encode(data));

				} else {
					throw new FileNotFoundException(filePath + " not exist");
				}

	}
	

	//字符串转化成日期
	public Date StringFormatDate(Date date,String str){
		DateFormat format = new SimpleDateFormat("yyyyMMdd");
		if(null!=str&&!("".equals(str))){
			try {
				date = format.parse(str);
			} catch (ParseException e) {
				logger.info("日期转换异常");
				e.printStackTrace();
			}
			return date;
		}else{
			return null;
		}
		
		
	}
	
	//日期转化
	public String DateFormatString(Date date,String str){
		
		if(null!=date){
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			str = format.format(date);
			return str;
		}else{
			return "";
		}
		
		
	}
	

	
}
