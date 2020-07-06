package com.qifenqian.bms.merchant.channel.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.qifenqian.bms.merchant.channel.controller.ChannelController;

@RunWith(SpringJUnit4ClassRunner.class) // 调用Spring单元测试类
@WebAppConfiguration // 调用Java Web组件，如自动注入ServletContext Bean等
@ContextConfiguration(locations = { "classpath*:/spring/spring-*.xml" }) // 加载Spring配置文
public class ChannelControllerTest {
	@Autowired
	private ChannelController controller;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void Ctest() throws Exception {
		
		
		String json  = "{details:{{ChannelCode:ALIPAY,subCode:SM,wxAppId:,wxAppsecret:},{ChannelCode:ALIPAY,subCode:SM,wxAppId:,wxAppsecret:},{ChannelCode:ALIPAY,subCode:SM,wxAppId:,wxAppsecret:},{ChannelCode:ALIPAY,subCode:SM,wxAppId:,wxAppsecret:}},merCode:df,channelName:XL_PAY,merchantChannelId:b,merchantChannelKey:c}";
		ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.post("/merchant/channel/save").param(json));
		MvcResult mvcResult = resultActions.andReturn();
		String result = mvcResult.getResponse().getContentAsString();
		System.out.println("=====客户端获得反馈数据:" + result);
		// 也可以从response里面取状态码，header,cookies...
		// System.out.println(mvcResult.getResponse().getStatus());
	}
}