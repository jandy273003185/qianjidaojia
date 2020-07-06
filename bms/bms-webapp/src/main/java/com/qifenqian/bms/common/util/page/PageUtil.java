package com.qifenqian.bms.common.util.page;

import com.qifenqian.bms.platform.common.utils.ThreadUtils;
import com.qifenqian.bms.platform.web.Constants;
import com.qifenqian.bms.platform.web.page.bean.PageInfo;

public class PageUtil {
	
	/**
	 * @param pageInfo zgc 设置分页码
	 */
	public static void SetPageInfo(PageInfo pageInfo) {
		ThreadUtils.put(Constants.Platform.$_PAGEINFO, pageInfo);
	}
	
	/**
	 * @return zgc 回去分页工具类 
	 */
	public static PageInfo getPageInfo() {
		return	ThreadUtils.get(Constants.Platform.$_PAGEINFO, PageInfo.class);
	}
}
