package com.sevenpay.agentmanager.biz.old.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.sevenpay.agentmanager.common.utils.wx.AuthUtil;
import com.sevenpay.agentmanager.core.bean.ResultData;

/**
 * User: JIANGZONGLIN Date: 2019/11/19 Time: 17:35
 */
@Controller
@RequestMapping("wx")
public class WxController {

	private final static Logger LOGGER = LoggerFactory.getLogger(WxController.class);

	@Value("${wxauth.appid}")
	private String appid;
	@Value("${wxauth.appsecret}")
	private String appsecret;
	@Value("${wxauth.token}")
	private String token;

	/**
	 * 切换开发者模式
	 *
	 * @param signature
	 * @param timestamp
	 * @param nonce
	 * @param echostr
	 * @param response
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 */
	@GetMapping("/get")
	public void get(String signature, String timestamp, String nonce, String echostr, HttpServletResponse response)
			throws IOException, NoSuchAlgorithmException {
		// 将token、timestamp、nonce三个参数进行字典序排序

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("signature:{}, timestamp:{}, nonce:{}, echostr:{}, TOKEN:{}", signature, timestamp, nonce,
					echostr, token);
		}

		String[] params = new String[] { token, timestamp, nonce };
		Arrays.sort(params);
		// 将三个参数字符串拼接成一个字符串进行sha1加密
		String clearText = params[0] + params[1] + params[2];
		String algorithm = "SHA-1";
		String sign = new String(org.apache.commons.codec.binary.Hex
				.encodeHex(MessageDigest.getInstance(algorithm).digest((clearText).getBytes()), true));
		// 开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
		if (signature.equals(sign)) {
			response.getWriter().print(echostr);
		}
	}

	/**
	 * 公众号微信登录授权
	 *
	 * @return
	 */
	@GetMapping("/accredit")
	public String wxAccredit() throws UnsupportedEncodingException {
		// url回调地址
		String backUrl = "https://sp.qifenqian.com/wx/callback";
		// 1、用户同意授权，获取code
		String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + appid + "&redirect_uri="
				+ URLEncoder.encode(backUrl, "UTF-8") + "&response_type=code" + "&scope=snsapi_userinfo"
				+ "&state=STATE#wechat_redirect";
		// logger.info("forward重定向地址{" + url + "}");
		return "redirect:" + url;// 必须重定向，否则不能成功
	}

	/**
	 * 公众号微信登录授权回调函数
	 *
	 * @param req
	 * @return
	 */
	@RequestMapping("/callback")
	@ResponseBody
	@SuppressWarnings("unchecked")
	public ResultData callBack(HttpServletRequest req) throws Exception {
		// 1、获取微信用户的基本信息
		String code = req.getParameter("code");
		// 2、通过code获取网页授权access_token
		String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + appid + "&secret=" + appsecret
				+ "&code=" + code + "&grant_type=authorization_code";
		String result = AuthUtil.getUrlData(url);
		// 3、获取用户的openid和access_token
		Map<String, Object> data = JSONObject.parseObject(result, Map.class);
		LOGGER.info("get wx Data: {}" + result);
		String openId = data.get("openid").toString();

		// String token=data.get("access_token").toString();

		return ResultData.success(openId);
	}

}
