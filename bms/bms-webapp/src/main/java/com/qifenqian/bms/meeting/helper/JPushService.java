package com.qifenqian.bms.meeting.helper;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qifenqian.bms.accounting.utils.DictionaryUtils;
import com.qifenqian.bms.meeting.helper.pushrelation.bean.PushRelation;
import com.qifenqian.bms.meeting.helper.pushrelation.mapper.PushRelationMapper;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

@Service
public class JPushService {

	private static Logger LOG = Logger.getLogger(JPushService.class); 
	
	@Autowired
	private PushRelationMapper pushRelationMapper;
	@Autowired
	private DictionaryUtils dictionaryUtils;
	
	private static String masterSecret = "25d428b1971901576c5e7f2e";
	
	private static String appKey = "c2928c70db3d9808e527de5e";
	
	private static String JPush_registerId;
	
	@PostConstruct
	public void postConstruct() {
		masterSecret = dictionaryUtils.getDataValueByPath("meeting.JPush.masterSecret");
		appKey = dictionaryUtils.getDataValueByPath("meeting.JPush.appKey");
		JPush_registerId = dictionaryUtils.getDataValueByPath("meeting.JPush.registerId");
	}
	
	public boolean sendDepart(String title, String content){
		PushPayload pushPayloadIos = PushPayload.newBuilder()
	              .setPlatform(Platform.ios())
	              .setAudience(Audience.newBuilder().setAll(true).build())
	              .setNotification(Notification.newBuilder()
	                      .addPlatformNotification(IosNotification.newBuilder()
	                      	    .setAlert(content)
	                      	    .setBadge(5)
	                      	    .setSound("happy.caf")
	                      	    .addExtra("from", "JPush")
	                      	    .build())
	                      .build())
	               .setMessage(Message.content(content))
	               .setOptions(Options.newBuilder()
	                       .setApnsProduction(true)
	                       .build())
	               .build();
		send(pushPayloadIos);
		
		PushPayload pushPayloadAndroid = PushPayload.newBuilder()
	              .setPlatform(Platform.android())
	              .setAudience(Audience.newBuilder().setAll(true).build())
	              .setNotification(Notification.android(content, title, null))
	              .build();
			
		send(pushPayloadAndroid);
		return true;
	}

	
	// 向指定用户推送消息
	public boolean sendToSpecifiedUser(String custId,String title, String content){
		
		String registerId = getRegisterId(custId);
		
		if(null == registerId || "".equals(registerId) || JPush_registerId.equals(registerId)){
			return false;
		}
		
		PushPayload pushPayload = PushPayload.newBuilder()
				.setAudience(Audience.registrationId(registerId))
				.setPlatform(Platform.all())
				.setNotification(Notification.alert(content))
				.build();
		
		return send(pushPayload);
	}
	
	public static void main(String[] args) {
		
		new JPushService().sendDepart("TEST", "测试！");
		
	}
	
	// 向所有用户推送消息
	public boolean sendToAll(String title, String content){
		
		PushPayload pushPayload = PushPayload.alertAll(content);
		
		return send(pushPayload);
	}
	
	// 获取客户注册号
	private String getRegisterId(String custId){
		
		PushRelation pushRelation = pushRelationMapper.selectSingleById(custId);
		
		if(null != pushRelation){
			return pushRelation.getRegisterId();
		}
		
		return null;
	}
	
//	// 指定对象
//	public static PushPayload buildPushObject_register_id(String registrationId){
//		return PushPayload.newBuilder().setAudience(Audience.registrationId(registrationId)).build();
//	}
//    
//
//    // 快捷地构建推送对象：所有平台，所有设备，内容为 "ALERT" 的通知。
//    public static PushPayload buildPushObject_all_all_alert() {
//        return PushPayload.alertAll("ALERT");
//    }
//
//    
//
//    // 构建推送对象：所有平台，推送目标是别名为 "alias1"，通知内容为 "ALERT"。
//    public static PushPayload buildPushObject_all_alias_alert() {
//        return PushPayload.newBuilder()
//                .setPlatform(Platform.all())
//                .setAudience(Audience.alias("alias1"))
//                .setNotification(Notification.alert("ALERT"))
//                .build();
//    }
//
//    
//
//    // 构建推送对象：平台是 Android，目标是 tag 为 "tag1" 的设备，内容是 Android 通知 "ALERT"，并且标题为 TITLE。
//    public static PushPayload buildPushObject_android_tag_alertWithTitle() {
//        return PushPayload.newBuilder()
//                .setPlatform(Platform.android())
//                .setAudience(Audience.tag("tag1"))
//                .setNotification(Notification.android("ALERT", "TITLE", null))
//                .build();
//    }
//    
//    
//
//    // 构建推送对象：平台是 iOS，推送目标是 "tag1", "tag_all" 的交集，推送内容同时包括通知与消息 - 通知信息是 "ALERT"，角标数字为 5，通知声音为 "happy"，并且附加字段 from = "JPush"；消息内容是 MSG_CONTENT。通知是 APNs 推送通道的，消息是 JPush 应用内消息通道的。APNs 的推送环境是“生产”（如果不显式设置的话，Library 会默认指定为开发）
//    public static PushPayload buildPushObject_ios_tagAnd_alertWithExtrasAndMessage() {
//    	
//        return PushPayload.newBuilder()
//                .setPlatform(Platform.ios())
//                .setAudience(Audience.tag_and("tag1", "tag_all"))
//                .setNotification(Notification.newBuilder()
//                        .addPlatformNotification(IosNotification.newBuilder()
//                        	    .setAlert("ALERT")
//                        	    .setBadge(5)
//                        	    .setSound("happy.caf")
//                        	    .addExtra("from", "JPush")
//                        	    .build())
//                        .build())
//                 .setMessage(Message.content("MSG_CONTENT"))
//                 .setOptions(Options.newBuilder()
//                         .setApnsProduction(true)
//                         .build())
//                 .build();
//    }
//    
//    
//
//
//    // 构建推送对象：平台是 Andorid 与 iOS，推送目标是 （"tag1" 与 "tag2" 的并集）且（"alias1" 与 "alias2" 的并集），推送内容是 - 内容为 MSG_CONTENT 的消息，并且附加字段 from = JPush。
//    public static PushPayload buildPushObject_ios_audienceMore_messageWithExtras() {
//        return PushPayload.newBuilder()
//                .setPlatform(Platform.android_ios())
//                .setAudience(Audience.newBuilder()
//                        .addAudienceTarget(AudienceTarget.tag("tag1", "tag2"))
//                        .addAudienceTarget(AudienceTarget.alias("alias1", "alias2"))
//                        .build())
//                .setMessage(Message.newBuilder()
//                        .setMsgContent("MSG_CONTENT")
//                        .addExtra("from", "JPush")
//                        .build())
//                .build();
//    }

    
    public static boolean send(PushPayload pushPayload){
		JPushClient jpushClient = new JPushClient(masterSecret, appKey, 3);

	    try {
	        PushResult result = jpushClient.sendPush(pushPayload);
	        LOG.info("Got result - " + result);
	        
	        return true;
	    } catch (APIConnectionException e) {
	        // Connection error, should retry later
	        LOG.error("Connection error, should retry later", e);

	    } catch (APIRequestException e) {
	        // Should review the error, and fix the request
	        LOG.error("Should review the error, and fix the request", e);
	        LOG.info("HTTP Status: " + e.getStatus());
	        LOG.info("Error Code: " + e.getErrorCode());
	        LOG.info("Error Message: " + e.getErrorMessage());
	    }
	    
		return false;
	}

}
