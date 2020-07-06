package com.qifenqian.bms.platform.web.admin.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.platform.web.admin.user.bean.User;

/**
 * 用户持久层
 * 
 * @project gyzb-platform-web-admin
 * @fileName UserMapper.java
 * @author huiquan.ma
 * @date 2015年11月24日
 * @memo
 */
@MapperScan
public interface UserMapper {

	/**
	 * 新增单个用户
	 * 
	 * @param user
	 * @return
	 */
	public int insertUserSingle(User user);
	
	/**
	 * 新增单点登录用户
	 * @param user
	 * @return
	 */
	public int insertUserByCas(User user);

	/**
	 * 根据用户编号查询单个用户
	 * 
	 * @param userId
	 * @return
	 */
	public User selectUserSingleById(@Param("userId") int userId);

	/**
	 * 根据userCode查询单个用户
	 * @param userCode
	 * @return
	 */
	public User selectUserSingleByCode(@Param("userCode") String userCode);

	/**
	 * 查询用户列表
	 * 
	 * @return
	 */
	public List<User> selectUserList(User user);

	/**
	 * 修改用户信息
	 * 
	 * @param user
	 * @return
	 */
	public int updateUserById(User user);

	/**
	 * 登出所有用户
	 * 
	 * @return
	 */
	public int loginOutAllUsers();

	/**
	 * 
	 * @param user
	 * @return
	 */
	public int updateUserByCas(User user);

	/**
	 * 
	 * @param userName
	 * @return
	 */
	public String selectUserNameIsExists(String userName);

	/**
	 * 
	 * @param requestUser
	 * @return
	 */
	public String userNameAndIdIsExists(User requestUser);
}
