package com.qifenqian.bms.platform.web.admin.rolefunction.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qifenqian.bms.platform.web.admin.rolefunction.bean.RoleFunction;
import com.qifenqian.bms.platform.web.admin.rolefunction.mapper.RoleFunctionMapper;

/**
 * 
 * @project gyzb-platform-web-admin
 * @fileName RoleFunctionService.java
 * @author huiquan.ma
 * @date 2015年11月25日
 * @memo
 */
@Service
public class RoleFunctionService {
	
	@Autowired
	private RoleFunctionMapper roleFunctionMapper;
	
	/**
	 * 角色增加菜单
	 * @param roleFunction
	 */
	public void insertList(List<RoleFunction> roleFunction){
		if(null == roleFunction){
			throw new IllegalArgumentException("角色增加的功能菜单集合为空");
		}
		roleFunctionMapper.insertList(roleFunction);
	}
	
	
	/**
	 * 获取角色菜单集合
	 * @param roleId
	 * @param functions
	 * @param instrUser
	 * @return
	 */
	public List<RoleFunction> roleFunctionList(int roleId, List<String> functions, int instrUser){
		
		if(roleId < 1){
			throw new IllegalArgumentException("角色id为空");
		}
		
		if(null == functions){
			throw new IllegalArgumentException("角色菜单集合为空");
		}
		
		List<RoleFunction> list = new ArrayList<RoleFunction>();
		for(String functionId : functions ){
			
			RoleFunction roleFunction = new RoleFunction();
			roleFunction.setFunctionId(Integer.parseInt(functionId));
			roleFunction.setRoleId(roleId);
			roleFunction.setInstUser(instrUser);
			
			list.add(roleFunction);
			
		}
		
		return list;
		
	}
	
	/**
	 * 根据ID查出所有角色菜单
	 * @param roleId
	 * @return
	 */
	public List<RoleFunction> selectList(int roleId){
		
		if(roleId < 1){
			throw new IllegalArgumentException("角色id为空");
		}
		
		return roleFunctionMapper.selectList(roleId);
	}
	
	/**
	 * 根据角色ID删除角色菜单
	 */
	public void deleteRoleFunction( int roleId){
		
		if(roleId < 1){
			throw new IllegalArgumentException("角色id为空");
		}
		
		roleFunctionMapper.deleteByRoleId(roleId);
	}
	
	/**
	 * 根据功能菜单ID删除角色菜单
	 * @param functionId
	 */
	public void deleteRoleFunctionbyFunctionId(int functionId){
		
		if(functionId < 1){
			throw new IllegalArgumentException("菜单id为空");
		}
		roleFunctionMapper.deleteRoleFunctionbyFunctionId(functionId);
	}
	
	
	
	
}
