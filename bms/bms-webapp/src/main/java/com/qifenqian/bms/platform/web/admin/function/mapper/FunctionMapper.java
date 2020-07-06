package com.qifenqian.bms.platform.web.admin.function.mapper;

import java.util.List;

import com.qifenqian.bms.v2.biz.system.function.bean.vo.FunctionTreeVO;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.platform.web.admin.function.bean.Function;

/**
 * mybatis层
 * 
 * @project gyzb-platform-web-admin
 * @fileName FunctionMapper.java
 * @author huiquan.ma
 * @date 2015年11月25日
 * @memo
 */
@MapperScan
public interface FunctionMapper {

	/**
	 * 根据用户编号查询功能列表
	 * 
	 * @param userId
	 * @return
	 */
	public List<Function> selectFunctionListByUserId(@Param("userId") int userId);

	/**
	 * 查询登陆信息
	 * 
	 * @param userId
	 * @return
	 */
	public List<Function> selectFunctionListByLogin(@Param("userId") int userId);

	/**
	 * 根据角色编号查询功能列表
	 * 
	 * @param roleId
	 * @return
	 */
	public List<Function> selectFunctionListByRoleId(@Param("roleId") int roleId);

	/**
	 * 根据功能编号查询下一级的菜单
	 * 
	 * @param functionId
	 * @return
	 */
	public List<Function> selectFunctionSubListById(@Param("userId") int userId, @Param("functionId") int functionId);

	/**
	 * 根据功能编号查询下一级的菜单(登陆)
	 * 
	 * @param functionId
	 * @return
	 */
	public List<Function> selectFunctionSubListByLogin(@Param("userId") int userId, @Param("functionId") int functionId);

	/**
	 * 根据条件查询单个菜单
	 * 
	 * @param function
	 * @return
	 */
	public Function selectFunctionSingle(Function function);

	/**
	 * 根据编号查询上层功能
	 * 
	 * @param functionId
	 * @return
	 */
	public Function selectFunctionParentById(@Param("functionId") int functionId);

	/**
	 * 查询所有菜单
	 * 
	 * @return
	 */
	public List<Function> selectAllFunctions();

	/**
	 * 根据组合条件查询菜单
	 * 
	 * @param function
	 * @return
	 */
	public List<Function> selectFunctionsByIdAndName(Function function);

	/**
	 * 添加菜单
	 * 
	 * @param function
	 */
	public void insertFunction(Function function);

	/**
	 * 修改菜单
	 * 
	 * @param function
	 */
	public void updateFunction(Function function);

	/**
	 * 
	 * @param functionCode
	 * @return
	 */
	public String functionCodeIsExists(String functionCode);

	/**
	 * 
	 * @param function
	 * @return
	 */
	public String functionCodeAndIdIsExists(Function function);

    List<FunctionTreeVO> findMenuTreeByUserId(@Param("userId") Long userId, @Param("parentFunctionId") Long parentFunctionId);

	/**
	 * 查询角色菜单权限树
	 * @param roleId
	 * @param parentFunctionId
	 * @return
	 */
	List<FunctionTreeVO> findRoleFunctionTree(@Param("roleId") Integer roleId, @Param("parentFunctionId") Integer parentFunctionId);

	/**
	 *
	 * @param parentFunctionId
	 * @return
	 */
	List<FunctionTreeVO> findFunctionTree(@Param("parentFunctionId") Integer parentFunctionId);
}
