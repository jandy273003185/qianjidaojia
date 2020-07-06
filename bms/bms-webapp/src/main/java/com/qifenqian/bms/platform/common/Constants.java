package com.qifenqian.bms.platform.common;

/**
 * 常量
 * 
 * @project gyzb-platform-common
 * @fileName Constants.java
 * @author huiquan.ma
 * @date 2015年11月18日
 * @memo
 */
public final class Constants {
	
	/** 操作系统*/
	public final static class OS {
		/** 换行符*/
		public static final String NEW_LINE = System.getProperty("line.separator");
		/** 文件路径分隔符 / \\*/
		public static final String FILE_SEPARATOR = System.getProperty("file.separator");
		/** 路径分隔符  ; */
		public static final String PATH_SEPARATOR = System.getProperty("path.separator");
	}
	
}


