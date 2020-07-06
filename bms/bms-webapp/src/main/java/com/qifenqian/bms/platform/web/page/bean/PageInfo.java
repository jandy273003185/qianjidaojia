package com.qifenqian.bms.platform.web.page.bean;

import java.io.Serializable;

/**
 * 分页对象,分页对象不应该与任何web逻辑参杂在一起,因为桌面程序一样要用到分页
 * 
 * 页数从1开始计算
 * 
 * 默认总数据条目数为0
 * 默认当前页为第一页
 * 默认每页显示10条
 * 
 * @project gyzb-platform-web
 * @fileName PageInfo.java
 * @author huiquan.ma
 * @date 2015年11月18日
 * @memo
 */
public class PageInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	// 当前页,默认是第一页
	private int currentPage = 1;

	// 每页最多显示多少条, 默认10条
	private int pageSize = 10;

	// 总数据数
	private int rowCnt = 0;

	// 总页数,从总数据数和每页条目数计算而得
	private int pageCnt;
	
	// 分页的第一行在总数据表中的索引,由当前页和每页显示条目数计算而得
	private int firstIdx;

	// 分页的最后一行在总数据表中的索引,由当前页和每页显示条目数计算而得
	private int lastIdx;

	/**
	 * 计算
	 */
	private void caculate() {
		
		// 计算页数
		pageCnt = rowCnt / pageSize;
		if ((rowCnt % pageSize) != 0) {
			pageCnt++;
		}
		
		// 计算当前页首行下标
		firstIdx = (currentPage - 1) * pageSize + 1;
		
		// 计算当前页末行下标
		lastIdx = currentPage * pageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public int getPageCnt() {
		caculate();
		return pageCnt;
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getRowCnt() {
		return rowCnt;
	}

	public int getFirstIdx() {
		caculate();
		return firstIdx;
	}

	public int getLastIdx() {
		caculate();
		return lastIdx;
	}

	public void setCurrentPage(int currentPage) {
		if(currentPage <= 0){
			throw new IllegalArgumentException("传入[当前页]参数必须是正整数");
		}
		this.currentPage = currentPage;
	}

	public void setPageSize(int pageSize) {
		if(pageSize <= 0){
			throw new IllegalArgumentException("传入[每页显示条数]参数必须是正整数");
		}
		this.pageSize = pageSize;
	}

	public void setRowCnt(int rowCnt) {
		this.rowCnt = rowCnt;
	}

	public void setFirstIdx(int firstIdx) {
		this.firstIdx = firstIdx;
	}

	public void setLastIdx(int lastIdx) {
		this.lastIdx = lastIdx;
	}

}
