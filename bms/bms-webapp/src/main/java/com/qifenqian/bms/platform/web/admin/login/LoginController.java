package com.qifenqian.bms.platform.web.admin.login;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.platform.common.utils.CipherUtils;
import com.qifenqian.bms.platform.common.utils.DateUtils;
import com.qifenqian.bms.platform.web.admin.function.service.FunctionService;
import com.qifenqian.bms.platform.web.admin.login.service.LoginService;
import com.qifenqian.bms.platform.web.admin.role.service.RoleService;
import com.qifenqian.bms.platform.web.admin.user.bean.User;
import com.qifenqian.bms.platform.web.admin.user.service.UserService;
import com.qifenqian.bms.platform.web.admin.utils.WebUtils;

/**
 * 登陆
 * 
 * @project gyzb-platform-web-admin
 * @fileName LoginController.java
 * @author huiquan.ma
 * @date 2015年11月25日
 * @memo
 */
@SuppressWarnings("unused")
@Controller
public class LoginController {

	private static Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private LoginService loginService;

	@Autowired
	private FunctionService functionService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;

	/**
	 * 登陆
	 * 
	 * @param userId
	 * @param password
	 * @param pw
	 */
	@RequestMapping(LoginPath.LOGIN)
	@ResponseBody
	public String login(@RequestParam("userCode") String userCode, @RequestParam("password") String password) {

		logger.info("登陆用户{}于{}尝试登陆", userCode, DateUtils.getDateTimeStr());
		// 转换成大写

		JSONObject jsonObject = new JSONObject();

		User loginUser = null;

		try {
			// 检查用户是否正常
			loginUser = loginService.checkUser(userCode, password);

			loginService.login(loginUser);

			// 确认登录成功
			jsonObject.put("result", "SUCCESS");
		} catch (Exception e) {
			logger.error("用户登录处理出现异常", e);

			jsonObject.put("result", "FAILURE");
			jsonObject.put("message", e.getMessage());
		}
		logger.info("登陆结果：{}", JSONObject.toJSONString(jsonObject, true));
		// 返回用户信息
		return jsonObject.toJSONString();
	}

	/**
	 * 进入主页
	 * 
	 * @return
	 */
	@RequestMapping(LoginPath.MAIN)
	public ModelAndView main() {
		return new ModelAndView(LoginPath.MAIN);
	}

	/**
	 * 登出
	 * 
	 * @param pw
	 */
	@RequestMapping(LoginPath.LOGOUT)
	@ResponseBody
	public String logout() {

		JSONObject jsonObject = new JSONObject();

		User user = WebUtils.getUserInfo();

		logger.info("登陆用户{}于{}退出系统", user.getUserId(), DateUtils.getDateTimeStr());

		try {
			// 注销session
			WebUtils.getHttpSession().invalidate();
			// session invalidate方法
			// 会自动触发session失效事件（即自动触发SessionListener.sessionDestroyed()）
			// 故这里不在更新用户状态

			jsonObject.put("result", "SUCCESS");

		} catch (Exception e) {
			logger.error("用户[" + user.getUserId() + "]退出系统处理出现异常", e);

			jsonObject.put("result", "FAILURE");
			jsonObject.put("message", e.getMessage());
		}
		return jsonObject.toJSONString();
	}

	/**
	 * 首页
	 * 
	 * @return
	 */
	@RequestMapping(LoginPath.ROOT)
	public ModelAndView root() {

		User user = WebUtils.getUserInfo();
		if (null != user) {
			// 如果 已经登陆 直接跳转登陆后页面
			return new ModelAndView(LoginPath.MAIN);
		} else {
			// 反之跳转到首页
			return new ModelAndView(LoginPath.INDEX);
		}
	}

	/**
	 * 进入修改页面
	 * 
	 * @return
	 */
	@RequestMapping(LoginPath.TOUPDATEPWD)
	public ModelAndView toUpdatePwd() {
		User user = WebUtils.getUserInfo();
		ModelAndView modelAndView = new ModelAndView(LoginPath.UPDATEPWD);
		modelAndView.addObject("userCode", user.getUserCode());
		return modelAndView;
	}

	/**
	 * 修改登录密码
	 * 
	 * @param changUser
	 * @return
	 */

	@RequestMapping(LoginPath.UPDATEPWD)
	@ResponseBody
	private String updatePwd(User changeUser) {
		logger.info("修改登录密码");
		JSONObject jsonObject = new JSONObject();
		User user = WebUtils.getUserInfo();
		try {
			logger.info("修改登录密码用户" + user.getUserId());
			// 查询用户密码
			User loginUser = userService.selectUserSingleById(user.getUserId());
			jsonObject.put("result", "FAIL");
			if (StringUtils.isEmpty(changeUser.getUserPassword())) {
				jsonObject.put("message", "当前用户密码不能为空");
			} else if (StringUtils.isEmpty(changeUser.getNewPassword())) {
				jsonObject.put("message", "新密码不能为空");
			} else if (StringUtils.isEmpty(changeUser.getConfirmPassword())) {
				jsonObject.put("message", "确认密码不能为空");
			} else if (!changeUser.getNewPassword().equals(changeUser.getConfirmPassword())) {
				jsonObject.put("message", "两次输入的密码不一致");
			} else if (changeUser.getUserPassword().equals(changeUser.getNewPassword())) {
				jsonObject.put("message", "新登录密码必须与当前登录密码不一致");
			} else if (!loginUser.getPassword()
					.equals(CipherUtils.encryptPassword(user.getUserCode(), changeUser.getUserPassword()))) {
				jsonObject.put("result", "userPasswordFail");
				jsonObject.put("message", "输入的用户密码不正确");
			} else {
				user.setPassword(changeUser.getNewPassword());
				userService.changePassword(user);
				jsonObject.put("result", "SUCCESS");
				jsonObject.put("message", "修改成功！点击返回登录页面");
				logger.info("修改登录密码成功");
			}

		} catch (Exception e) {
			logger.error("用户[" + user.getUserId() + "]修改密码处理出现异常", e);
			jsonObject.put("result", "FAIL");
			jsonObject.put("message", e.getMessage());
		}
		return jsonObject.toJSONString();
	}
}
