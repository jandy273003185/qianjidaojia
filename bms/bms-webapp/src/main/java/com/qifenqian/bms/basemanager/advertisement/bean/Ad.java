package com.qifenqian.bms.basemanager.advertisement.bean;

import java.io.Serializable;

public class Ad implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4804947986706608062L;
		private String adId; //广告id
		private String adName; //广告名
		private String imagePath; //图片地址
		private String imagePath2;
		public String getImagePath2() {
			return imagePath2;
		}
		public void setImagePath2(String imagePath2) {
			this.imagePath2 = imagePath2;
		}
		private String imagePath3;
		
		private String url; //（url）
		private String isValid ;//是否有效,1 有效，0无效
		private String createId; //创建人
		private String createTime; //创建时间
		private String modifyId; //修改人
		private String modifyTime; //修改时间

		public String getImagePath3() {
			return imagePath3;
		}
		public void setImagePath3(String imagePath3) {
			this.imagePath3 = imagePath3;
		}
		public String getAdId() {
			return adId;
		}
		public void setAdId(String adId) {
			this.adId = adId;
		}
		public String getAdName() {
			return adName;
		}
		public void setAdName(String adName) {
			this.adName = adName;
		}
		public String getImagePath() {
			return imagePath;
		}
		public void setImagePath(String imagePath) {
			this.imagePath = imagePath;
		}
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
		public String getIsValid() {
			return isValid;
		}
		public void setIsValid(String isValid) {
			this.isValid = isValid;
		}
		public String getCreateId() {
			return createId;
		}
		public void setCreateId(String createId) {
			this.createId = createId;
		}
		public String getCreateTime() {
			return createTime;
		}
		public void setCreateTime(String createTime) {
			this.createTime = createTime;
		}
		public String getModifyId() {
			return modifyId;
		}
		public void setModifyId(String modifyId) {
			this.modifyId = modifyId;
		}
		public String getModifyTime() {
			return modifyTime;
		}
		public void setModifyTime(String modifyTime) {
			this.modifyTime = modifyTime;
		}
	
	
}
