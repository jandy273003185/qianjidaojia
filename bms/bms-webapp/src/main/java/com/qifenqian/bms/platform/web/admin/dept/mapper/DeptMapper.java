package com.qifenqian.bms.platform.web.admin.dept.mapper;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.platform.web.admin.dept.bean.Dept;

/**
 * 部门mapper
 * 
 * @project gyzb-platform-web-admin
 * @fileName DeptMapper.java
 * @author huiquan.ma
 * @date 2015年11月24日
 * @memo
 */
@MapperScan
public interface DeptMapper {

	/**
	 * 查询部门列表
	 * 
	 * @param dept
	 * @return
	 */
	public List<Dept> selectDeptList(Dept dept);

	/**
	 * 查询单个部门
	 *
	 * @param dept
	 * @return
	 */
	public Dept selectDeptSingle(Dept dept);

	/**
	 * 部门新增
	 * 
	 * @param dept
	 * @return
	 */
	public void insertDept(Dept dept);

	/**
	 * 部门修改
	 * 
	 * @param dept
	 * @return
	 */
	public void updateDept(Dept dept);

	/**
	 * 部门删除
	 * 
	 * @param dept
	 * @return
	 */
	public void deleteDept(Dept dept);

	/**
	 * 
	 * @param deptCode
	 * @return
	 */
	public String deptCodeIsExists(String deptCode);

	/**
	 * 
	 * @param dept
	 * @return
	 */
	public String deptCodeAndIdIsExists(Dept dept);
}
