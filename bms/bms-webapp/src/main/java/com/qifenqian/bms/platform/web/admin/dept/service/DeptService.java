package com.qifenqian.bms.platform.web.admin.dept.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qifenqian.bms.platform.web.admin.dept.bean.Dept;
import com.qifenqian.bms.platform.web.admin.dept.dao.DeptDao;
import com.qifenqian.bms.platform.web.admin.dept.mapper.DeptMapper;

/**
 * 部门服务
 * 
 * @project gyzb-platform-web-admin
 * @fileName DeptService.java
 * @author huiquan.ma
 * @date 2015年11月24日
 * @memo
 */
@Service
public class DeptService {

	@Autowired
	private DeptMapper deptMapper;
	@Autowired
	private DeptDao deptDao;

	/**
	 * 根据条件查询部门列表
	 * 
	 * @param dept
	 * @return
	 */
	public List<Dept> selectDeptList(Dept dept) {
		return deptDao.selectDeptList(dept);
	}

	/**
	 * 根据条件查询部门列表
	 * 
	 * @param dept
	 * @return
	 */
	public void addDept(Dept dept) {
		deptMapper.insertDept(dept);
	}

	/**
	 * 根据条件查询部门列表
	 * 
	 * @param dept
	 * @return
	 */
	public void editDept(Dept dept) {
		deptMapper.updateDept(dept);
	}

	/**
	 * 根据条件查询部门列表
	 * 
	 * @param dept
	 * @return
	 */
	public void deleteDept(Dept dept) {
		deptMapper.deleteDept(dept);

	}

	/**
	 * 验证部门编号是否存在
	 * 
	 * @param deptCode
	 * @return
	 */
	public boolean deptCodeIsExists(String deptCode) {
		boolean flag = false;
		String codeCount = deptMapper.deptCodeIsExists(deptCode);
		if (!codeCount.equals("0")) {
			flag = true;
		}
		return flag;
	}

	/**
	 * 
	 * @param dept
	 * @return
	 */
	public boolean deptCodeAndIdIsExists(Dept dept) {
		boolean flag = false;
		String codeCount = deptMapper.deptCodeAndIdIsExists(dept);
		if (!codeCount.equals("0")) {
			flag = true;
		}
		return flag;
	}
}
