package com.qifenqian.bms.merchant.reported.enums;
/**
 * WECHAT个体户费率ID编码
 * @author shili
 *
 */
public enum WeChatPerRateIdType {
	餐饮("719","餐饮"),
	食品生鲜("719","食品生鲜"),
	私立民营医院诊所("719","私立/民营医院/诊所"),
	保健器械医疗器械非处方药品("719","保健器械/医疗器械/非处方药品"),
	游艺厅KTV网吧("719","游艺厅/KTV/网吧"),
	机票机票代理("719","机票/机票代理"),
	宠物医院("719","宠物医院"),
	教育培训考试缴费学费("719","教育/培训/考试缴费/学费"),
	零售批发生活娱乐其他("719","零售批发/生活娱乐/其他"),
	话费通讯("720","话费通讯"),
	加油("720","加油");
	
	private String code;
	private String name;
	WeChatPerRateIdType(String code,String name){
		this.code = code;
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

}
