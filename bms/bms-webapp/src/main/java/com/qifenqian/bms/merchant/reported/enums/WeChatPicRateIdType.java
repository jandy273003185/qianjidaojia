package com.qifenqian.bms.merchant.reported.enums;
/**
 * WECHANT党政机关以及其他组织费率ID编码
 * @author shili
 *
 */
public enum WeChatPicRateIdType {
	其他缴费("725","其他缴费"),
	公共事业水电煤气("722","公共事业（水电煤气）"),
	交通罚款("723","交通罚款"),
	公立医院("724","公立医院"),
	公立学校("724","公立学校"),
	挂号平台("724","挂号平台"),
	宗教组织("727","宗教组织"),
	机票机票代理("727","机票/机票代理"),
	私立民营医院诊所("727","私立/民营医院/诊所"),
	咨询娱乐票务其他("727","咨询/娱乐票务/其他"),
	民办中小学及幼儿园("738","民办中小学及幼儿园"),
	民办大学及学院("738","民办大学及学院"),
	公益("726","公益");
	
	private String code;
	private String name;
	WeChatPicRateIdType(String code,String name){
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
