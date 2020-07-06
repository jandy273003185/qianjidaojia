package com.qifenqian.bms.platform.third.scheduler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

/**
 * 封装给各应用模块的调度类
 * 
 * @project ***
 * @fileName ISchedulerServlet.java
 * @author ganzheng.ge
 * @date 2015年8月11日
 * @memo
 */
public abstract class ISchedulerServlet extends HttpServlet {

	private static final long serialVersionUID = 8768454533617995150L;
	
	public static final String EXECUTE_SUCCESS = "SUCCESS";

	public static final String EXECUTE_FAILURE = "FAILURE";

	public static final String EXECUTE_FLAG = "EXECUTEFLAG";

	public static final String EXECUTE_DESC = "EXECUTEDESC";

	public static final String ENCODING = "UTF-8";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.execute(request, response);
	}

	/***
	 * 验证用户访问权限：从请求里面取token，校验是否有效； 封装返回消息格式：返回消息不涉及各模块业务，写入到scheduler平台日志；
	 * 提供业务实现方法：各模块填充具体功能实现；
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.execute(request, response);
	}

	private void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		JSONObject jsonObject = new JSONObject();
		// String token = request.getHeader("token");
		// 获取请求头里面的token和配置里面比较
		// if("SSDF#@$$%#".equals(token)){
		// 权限校验通过后，调用业务实现方法
		String subject = Subject(request, response);
		String desc = (String) request.getAttribute(EXECUTE_DESC);
		if (desc.length() > 200) {
			desc = (String) desc.subSequence(0, 200);
		}
		jsonObject.put(EXECUTE_FLAG, subject);
		jsonObject.put(EXECUTE_DESC, desc);

		// 指定编码
		response.setCharacterEncoding(ENCODING);

		response.getWriter().print(jsonObject.toJSONString());
		// }else{
		// logger.info("没有权限访问！");
		// }
	}

	/***
	 * 实现各业务模块方法，业务实现用try/catch包围 异常信息封装格式:request.setAttribute("Msg",
	 * "业务内容描述："+详细异常信息);
	 * 
	 * @return 成功返回SUCCESS,失败返回FAILURE
	 */
	public abstract String Subject(HttpServletRequest request, HttpServletResponse response);

}
