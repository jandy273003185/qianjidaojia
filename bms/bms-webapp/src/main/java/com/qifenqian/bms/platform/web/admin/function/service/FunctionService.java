package com.qifenqian.bms.platform.web.admin.function.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qifenqian.bms.platform.web.admin.function.bean.Function;
import com.qifenqian.bms.platform.web.admin.function.dao.FunctionDAO;
import com.qifenqian.bms.platform.web.admin.function.mapper.FunctionMapper;
import com.qifenqian.bms.platform.web.admin.function.type.IsMenu;
import com.qifenqian.bms.platform.web.admin.function.type.IsValid;
import com.qifenqian.bms.platform.web.admin.utils.WebUtils;

/**
 * 功能菜单服务层
 * 
 * @project gyzb-platform-web-admin
 * @fileName FunctionService.java
 * @author huiquan.ma
 * @date 2015年11月25日
 * @memo
 */
@Service
public class FunctionService {

	@Autowired
	private FunctionMapper functionMapper;

	@Autowired
	private FunctionDAO functionDAO;

	/**
	 * 根据用户编号查询菜单功能树
	 * 
	 * @param userId
	 * @return
	 */
	public List<Function> selectMenuTreeByUserId(int userId) {
		if (userId < 1) {
			throw new IllegalArgumentException("用户编号为空");
		}
		// 先填充一级菜单
		List<Function> returnList = new ArrayList<Function>();

		List<Function> userFunctionList = functionMapper.selectFunctionListByUserId(userId);
		if (null != userFunctionList && !userFunctionList.isEmpty()) {
			for (Function function : userFunctionList) {
				// 有效并且为菜单的才加入
				if (function.getFunctionLevel() == 1 && function.getIsMenu() == IsMenu.Y
						&& function.getIsValid() == IsValid.Y) {
					returnList.add(function);
				}
			}
		}
		// 递归填充子菜单
		for (Function function : returnList) {
			function.setSubFunctionList(this.selectFunctionSubListById(userId, function.getFunctionId(), IsMenu.Y,
					IsValid.Y));
		}
		// 返回
		return returnList;
	}

	/**
	 * 根据用户编号查询功能列表
	 * 
	 * @param userId
	 * @return
	 */
	public List<Function> selectFunctionListByUserId(int userId) {
		if (userId < 1) {
			throw new IllegalArgumentException("用户编号为空");
		}
		List<Function> userFunctionList = functionMapper.selectFunctionListByUserId(userId);
		if (null != userFunctionList && !userFunctionList.isEmpty()) {
			for (Function function : userFunctionList) {
				function.setSubFunctionList(this.selectFunctionSubListById(userId, function.getFunctionId(), null,
						IsValid.Y));
				function.setParentFunctionList(this.selectFunctionParentListById(function.getFunctionId()));
			}
		}
		return userFunctionList;
	}

	/**
	 * 根据条件查询单个菜单功能
	 * 
	 * @param function
	 * @return
	 */
	public Function selectFunctionSingle(Function function) {
		Function resultFunction = functionMapper.selectFunctionSingle(function);
		if (null != resultFunction) {
			// 填充子菜单
			resultFunction.setSubFunctionList(this.selectFunctionSubListById(0, resultFunction.getFunctionId(), null,
					IsValid.Y));
			// 填充父菜单
			resultFunction.setParentFunctionList(this.selectFunctionParentListById(resultFunction.getFunctionId()));
		}
		return resultFunction;
	}

	/**
	 * 根据功能编号递归查询子功能菜单树
	 * 
	 * @param functionId
	 * @return
	 */
	public List<Function> selectFunctionParentListById(int functionId) {
		if (functionId < 1) {
			throw new IllegalArgumentException("功能编号为空");
		}
		List<Function> returnList = new ArrayList<Function>();
		Function parentFunction = functionMapper.selectFunctionParentById(functionId);
		while (null != parentFunction) {
			returnList.add(parentFunction);
			parentFunction = functionMapper.selectFunctionParentById(parentFunction.getFunctionId());
		}
		return returnList;
	}

	/**
	 * 根据功能编号递归查询子功能菜单树
	 * 
	 * @param userId
	 *            是否根据用户过滤
	 * @param functionId
	 * @param isMenu
	 * @param isValid
	 * @return
	 */
	private List<Function> selectFunctionSubListById(int userId, int functionId, IsMenu isMenu, IsValid isValid) {
		if (functionId < 1) {
			throw new IllegalArgumentException("功能编号为空");
		}
		List<Function> returnList = new ArrayList<Function>();
		List<Function> subFunctionList = functionMapper.selectFunctionSubListById(userId, functionId);
		if (null != subFunctionList && !subFunctionList.isEmpty()) {
			for (Function function : subFunctionList) {
				// if(null != isMenu && function.getIsMenu() != isMenu) {
				// continue;
				// }
				if (null != isValid && function.getIsValid() != isValid) {
					continue;
				}
				function.setSubFunctionList(this.selectFunctionSubListById(userId, function.getFunctionId(), isMenu,
						isValid));
				returnList.add(function);
			}
		}
		return returnList;
	}

	/**
	 * 查询所有菜单及子菜单
	 * 
	 * @return
	 */
	public List<Function> selectAllFuntion() {
		List<Function> allFunctions = functionMapper.selectAllFunctions();

		List<Function> firstFunctions = new ArrayList<Function>();

		if (null != allFunctions && !allFunctions.isEmpty()) {
			for (Function function : allFunctions) {
				if (function.getIsValid() != IsValid.Y || function.getIsMenu() != IsMenu.Y) {
					continue;
				}

				if (function.getFunctionLevel() == 1) {
					firstFunctions.add(function);
				}

			}
		}
		List<Function> parentFunction = new ArrayList<Function>();
		for (Function function : firstFunctions) {
			function.setSubFunctionList(this.selectFunctionSubListById2(function.getFunctionId(), IsMenu.Y, IsValid.Y));
			parentFunction.add(function);
		}

		return parentFunction;
	}

	/**
	 * 解析出被选中的菜单
	 * 
	 * @param checked
	 * @return
	 */
	public List<String> checkedFunction(String checked) {

		String[] check = checked.split(",");
		List<String> list = Arrays.asList(check);
		return list;
	}

	/**
	 * 根据功能编号递归查询子功能菜单树
	 * 
	 * @param functionId
	 * @param isMenu
	 * @param isValid
	 * @return
	 */
	private List<Function> selectFunctionSubListById2(int functionId, IsMenu isMenu, IsValid isValid) {
		if (functionId < 1) {
			throw new IllegalArgumentException("功能编号为空");
		}
		List<Function> returnList = new ArrayList<Function>();
		List<Function> subFunctionList = functionMapper.selectFunctionSubListById(0, functionId);
		if (null != subFunctionList && !subFunctionList.isEmpty()) {
			for (Function function : subFunctionList) {
				// if(null != isMenu && function.getIsMenu() != isMenu) {
				// continue;
				// }
				if (null != isValid && function.getIsValid() != isValid) {
					continue;
				}
				function.setSubFunctionList(this.selectFunctionSubListById(0, function.getFunctionId(), IsMenu.N,
						IsValid.Y));
				returnList.add(function);
			}
		}
		return returnList;
	}

	/**
	 * 根据组合条件查询菜单
	 * 
	 * @param function
	 * @return
	 */
	public List<Function> selectFunctionByIdAndName(Function function) {

		return functionDAO.selectFunctionByIdAndName(function);
	}

	/**
	 * 添加菜单
	 * 
	 * @param function
	 */
	public void insertFunction(Function function) {

		if (null == function) {
			throw new IllegalArgumentException("菜单对象为空！");
		}

		if (StringUtils.isEmpty(function.getFunctionCode())) {
			throw new IllegalArgumentException("菜单对象code为空！");
		}

		if (StringUtils.isEmpty(function.getFunctionName())) {
			throw new IllegalArgumentException("菜单名称为空！");
		}

		if (StringUtils.isBlank(function.getParentFunctionId())) {
			throw new IllegalArgumentException("上级菜单名称为空！");
		}

		if (StringUtils.isEmpty(String.valueOf(function.getFunctionLevel()))) {
			throw new IllegalArgumentException("菜单级别为空！");
		}

		if (StringUtils.isEmpty(function.getFunctionUrl())) {
			throw new IllegalArgumentException("url为空！");
		}

		if (null == function.getIsMenu()) {
			throw new IllegalArgumentException("是否菜单为空！");
		}

		if (null == function.getIsValid()) {
			throw new IllegalArgumentException("是否有效为空！");
		}

		if (null == function.getAccessType()) {
			throw new IllegalArgumentException("是否授权为空！");
		}

		if (StringUtils.isEmpty(String.valueOf(function.getFunctionOrder()))) {
			throw new IllegalArgumentException("菜单排序为空！");
		}

		functionMapper.insertFunction(function);
	}

	/**
	 * 修改菜单
	 * 
	 * @param function
	 */
	public void updateFunction(Function function) {
		if (null == function) {
			throw new IllegalArgumentException("菜单对象为空！");
		}

		if (function.getFunctionId() < 1) {
			throw new IllegalArgumentException("菜单对象Id为空！");
		}

		if (StringUtils.isEmpty(function.getFunctionCode())) {
			throw new IllegalArgumentException("菜单代码为空！");
		}

		if (StringUtils.isEmpty(function.getFunctionName())) {
			throw new IllegalArgumentException("菜单名称为空！");
		}

		if (StringUtils.isBlank(function.getParentFunctionId())) {
			throw new IllegalArgumentException("上级菜单名称为空！");
		}

		if (StringUtils.isEmpty(String.valueOf(function.getFunctionLevel()))) {
			throw new IllegalArgumentException("菜单级别为空！");
		}

		if (StringUtils.isEmpty(function.getFunctionUrl())) {
			throw new IllegalArgumentException("url为空！");
		}

		if (null == function.getIsMenu()) {
			throw new IllegalArgumentException("是否菜单为空！");
		}

		if (null == function.getIsValid()) {
			throw new IllegalArgumentException("是否有效为空！");
		}

		if (null == function.getAccessType()) {
			throw new IllegalArgumentException("是否授权为空！");
		}

		if (StringUtils.isEmpty(String.valueOf(function.getFunctionOrder()))) {
			throw new IllegalArgumentException("菜单排序为空！");
		}

		functionMapper.updateFunction(function);
	}

	/**
	 * 删除菜单
	 * 
	 * @param functionId
	 */
	public void deleteFunction(int functionId) {

		if (functionId < 1) {
			throw new IllegalArgumentException("菜单ID为空");
		}
		Function updateFunction = new Function();
		updateFunction.setFunctionId(functionId);
		updateFunction.setIsValid(IsValid.N);
		updateFunction.setLupdUser(WebUtils.getUserInfo().getUserId());
		functionMapper.updateFunction(updateFunction);
	}

	/**
	 * 
	 * @param functionCode
	 * @return
	 */
	public boolean functionCodeIsExists(String functionCode) {
		boolean flag = false;
		String functionCodeCount = functionMapper.functionCodeIsExists(functionCode);
		if (!functionCodeCount.equals("0")) {
			flag = true;
		}
		return flag;
	}

	/**
	 * 
	 * @param function
	 * @return
	 */
	public boolean functionCodeAndIdIsExists(Function function) {
		boolean flag = false;
		String functionCodeCount = functionMapper.functionCodeAndIdIsExists(function);
		if (!functionCodeCount.equals("0")) {
			flag = true;
		}
		return flag;
	}
}
