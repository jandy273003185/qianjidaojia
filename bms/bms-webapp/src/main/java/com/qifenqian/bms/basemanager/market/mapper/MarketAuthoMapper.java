package com.qifenqian.bms.basemanager.market.mapper;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.basemanager.market.bean.MarketRole;

@MapperScan
public interface MarketAuthoMapper {
	/**
	 * 根据查询条件获取用户详情列表
	 */
	public List<MarketRole> listInfo(MarketRole queryBean);
	/**
	 * 保存用户信息
	 */
	public void saveInfo(MarketRole marketRole);
	/**
	 * 更新用户信息
	 */
	public void updateInfo(MarketRole marketRole);
	/**
	 * 删除用户信息
	 */
	public void deleteInfo(MarketRole marketRole);
	/**
	 * 根据用户Id查询用户的角色Id集合
	 */
	public List<Integer> listRoleId(Integer userId);
	/**
	 * 根据角色名获取角色id
	 */
	public Integer getRoleIdByName(String roleName);
	/**
	 * 根据角色id和用户id删除相应的角色信息
	 */
	public void deleteRoleId(Integer roleId,Integer userId);
}
