package com.qifenqian.bms.platform.web.admin.dept.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.platform.web.admin.dept.bean.Dept;
import com.qifenqian.bms.platform.web.admin.dept.mapper.DeptMapper;
import com.qifenqian.bms.platform.web.page.Page;
/**
 * 部门管理
 * 
 * @project gyzb-platform-web-admin
 * @fileName DeptDao.java
 * @author huiquan.ma
 * @date 2015年11月24日
 * @memo
 */
@Repository
public class DeptDao {

	@Autowired
	private DeptMapper deptMapper;
	
	/**
	 * 角色查询
	 * @param role
	 * @return
	 */
	@Page
	public List<Dept> selectDeptList(Dept role){
		return deptMapper.selectDeptList(role);
	}

}
