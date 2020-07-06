package com.qifenqian.bms.basemanager.market.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qifenqian.bms.basemanager.market.bean.MarketRole;
import com.qifenqian.bms.basemanager.market.bean.UpdateMarketRole;
import com.qifenqian.bms.basemanager.market.dao.MarketAuthoDao;
import com.qifenqian.bms.platform.web.admin.user.bean.User;
import com.qifenqian.bms.platform.web.admin.userRole.bean.UserRole;
import com.qifenqian.bms.platform.web.admin.utils.WebUtils;

@Service
public class MarketAuthoService {
	private Logger logger = LoggerFactory.getLogger(MarketAuthoService.class);
	@Autowired
	private MarketAuthoDao marketAuthoDao;
	
	/**
	 * 根据查询条件获取用户详情列表
	 */
	public List<MarketRole> listInfo(MarketRole queryBean){
		return marketAuthoDao.listInfo(queryBean);
	}
	/**
	 * 获取角色id
	 */
	public Integer getRoleIdByName(String roleName) {
		return marketAuthoDao.getRoleIdByName(roleName);	
	}
	/**
	 * 保存用户信息
	 */
	public String saveInfo(MarketRole marketRole) {
		logger.info("市场部权限保存用户");
		try {
			String result = updateMarketRole(marketRole);
			if("SUCCESS".equals(result)) {
			marketAuthoDao.saveInfo(marketRole);
			}
			return result;
		}catch(Exception e) {
			logger.equals("市场部权限保存用户异常"+e.toString());
			e.printStackTrace();
			return e.toString();
		}
		
	}
	
	/**
	 * 删除用户信息
	 */
	public String deleteInfo(MarketRole marketRole) {
		logger.info("市场部权限删除用户");
		try {
			Integer roleId =0;
			if(marketRole.getUserRole() != null) {
				roleId = Integer.parseInt(marketRole.getUserRole());
			}
			User user = marketAuthoDao.selectUserByCode(marketRole.getUserCode());
			if(user !=null) {
				Integer userId = user.getUserId();	
				marketAuthoDao.deleteRoleId(roleId, userId);
			}	
			marketAuthoDao.deleteInfo(marketRole);
			return "SUCCESS";
		}catch(Exception e) {
			e.printStackTrace();
			logger.error("市场部权限删除用户异常"+e.toString());
			return e.toString();
		}
	}
	
	
	/**
	 * 根据用户名为用户添加市场部总裁或市场部副总裁的角色
	 */
	public String updateMarketRole(MarketRole marketRole) {
		String message;
		List<Integer> roleIds = null;
		User user = marketAuthoDao.selectUserByCode(marketRole.getUserCode());
		if(user != null) {
			//如果该账号存在，则验证输入的用户姓名与数据库的用户姓名是否一致，若不一致，返回错误提示信息
			if(!marketRole.getUserName().trim().equals(user.getUserName().trim())) {
				message = "该账号对应的用户姓名为"+user.getUserName().trim()+",请重新输入";
				return message;
			}
			//如果该账号存在，并且姓名匹配一致，则取出该用户的角色，新增市场部总裁或者副总裁的角色
			Integer userId = user.getUserId();
			if(userId != null) {
				//根据用户ID取出该角色的ID集合
				roleIds = marketAuthoDao.listRoleIds(userId);	
			}
			if(roleIds.contains(Integer.parseInt(marketRole.getUserRole()))) {
				return "SUCCESS";
			}
			//将要添加的角色ID加入到集合当中
			roleIds.add(Integer.parseInt(marketRole.getUserRole()));
			//删除用户角色
			marketAuthoDao.deleteUserRole(userId);
			//修改为最新的角色
			updateUserRole(roleIds,user);
			message = "SUCCESS";
			return message;
		}
		message = "该账号不存在，请联系管理员到系统管理->用户管理中新建";
		return message;
	}
	
	/**
	 * 修改用户角色
	 */
	public void updateUserRole(List<Integer> roleList,User user) {
		Integer[] roleIdArray = roleList.toArray(new Integer[0]);
		for (Integer roleId : roleIdArray) {
			UserRole userRole = new UserRole();
			userRole.setUserId(user.getUserId());
			userRole.setRoleId(roleId);
			userRole.setInstUser(WebUtils.getUserInfo().getUserId());
			marketAuthoDao.insertUserRole(userRole);
		}
	}
	
	/**
	 * 更新用户信息
	 * @param marketRole
	 * @return
	 */
	public String updateInfo(UpdateMarketRole marketRole) {
		
		String message ;
		
		User user = marketAuthoDao.selectUserByCode(marketRole.getUserCode());
		User newUser = new User();
		
		MarketRole newMarketRole = new MarketRole();
		newMarketRole.setUserCode(marketRole.getUserCode());
		newMarketRole.setUserName(marketRole.getUserName());
		newMarketRole.setUserRole(marketRole.getUserRole());
		
		String newUserName = marketRole.getNewUserName();
		
		//名字有改动,更新SYS_USER当中的userName
		if(newUserName!=null && !"".equals(newUserName)) {
			newUser.setUserId(user.getUserId());
			newUser.setUserName(newUserName);
			
			marketAuthoDao.updateUserName(newUser);
			
			newMarketRole.setUserName(newUserName);
		}
		
		//角色有改变，更新SYS_ROLE	
		
		if(marketRole.getNewUserRole()!=null && !"".equals(marketRole.getNewUserRole())) {
			Integer newRoleId = Integer.parseInt(marketRole.getNewUserRole());
			Integer oldRoleId = Integer.parseInt(marketRole.getUserRole());
			List<Integer> roleIds = marketAuthoDao.listRoleIds(user.getUserId());
			List<Integer> newRoleIds = new ArrayList<Integer>();
			for (Integer integer : roleIds) {
				if(integer != oldRoleId) {
					newRoleIds.add(integer);
				}
			}
			if(!newRoleIds.contains(newRoleId)) {
				newRoleIds.add(newRoleId);
			}
			//删除用户角色
			marketAuthoDao.deleteUserRole(user.getUserId());
			
			//更新用户角色
			updateUserRole(newRoleIds,user);
			
			newMarketRole.setUserRole(newRoleId.toString());
		}
		
		marketAuthoDao.updateInfo(newMarketRole);
		return "SUCCESS";
		
	}
	
	
}
