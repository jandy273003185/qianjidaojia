package com.qifenqian.bms.basemanager.photo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.basemanager.fee.service.FeeService;
import com.qifenqian.bms.basemanager.photo.bean.CertificateAuth;
import com.qifenqian.bms.basemanager.photo.bean.Photo;
import com.qifenqian.bms.basemanager.photo.service.PhotoService;
import com.qifenqian.bms.platform.web.admin.user.bean.User;
import com.qifenqian.bms.platform.web.admin.utils.WebUtils;

@Controller
@RequestMapping(PhotoPath.BASE)
public class PhotoController {

	private static Logger logger = LoggerFactory.getLogger(PhotoController.class);

	@Autowired
	private FeeService feeService;

	@Autowired
	private PhotoService photoService;

	/**
	 * 证件审核列表展示
	 * 
	 * @param fee
	 * @return
	 */
	@RequestMapping(PhotoPath.LIST)
	public ModelAndView list(CertificateAuth queryBean) {

		logger.info("证件审核列表查询对象{}", JSONObject.toJSONString(queryBean, true));

		ModelAndView mv = new ModelAndView(PhotoPath.BASE + PhotoPath.LIST);

		List<CertificateAuth> authList = photoService.list(queryBean);
		mv.addObject("queryBean", queryBean);
		mv.addObject("authList", JSONObject.toJSON(authList));
		return mv;
	}

	/**
	 * 查看用户证件详情
	 * 
	 * @param queryBean
	 * @return
	 */
	@RequestMapping(PhotoPath.QUERY_CERTIFICATE)
	@ResponseBody
	public JSONObject queryCertificate(CertificateAuth queryBean) {
		JSONObject json = new JSONObject();
		logger.info("查看用户证件详情查询对象{}", JSONObject.toJSONString(queryBean, true));
		if (queryBean.getCustType().equals("0")) {
			Photo photo = photoService.selectCustCertificate(queryBean.getAuthId());
			if (photo != null) {
				json.put("result", photo);
			} else {
				throw new UnsupportedOperationException("此用户证件不存在！");
			}
		} else {
			throw new UnsupportedOperationException("暂不支持");
		}
		return json;
	}

	/**
	 * 读取图片
	 * 
	 * @param request
	 * @param path
	 */

	@RequestMapping(PhotoPath.SHOW)
	public void showPicture(HttpServletRequest request, HttpServletResponse response, String path) {

		ServletOutputStream out = null;
		FileInputStream ips = null;
		try {
			ips = new FileInputStream(new File(path.replaceAll("\\\\", "\\\\\\\\")));
			response.setContentType("image/*");
			out = response.getOutputStream();
			int i = ips.available();// 文件大小
			byte[] date = new byte[i];
			ips.read(date);
			ips.close();
			out.write(date);
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	
	
	
	/**
	 * 证件审核
	 * 
	 * @param queryBean
	 * @return
	 */
	@RequestMapping(PhotoPath.AUDIT)
	@ResponseBody
	@Transactional
	public String audit(CertificateAuth certificate) {
		JSONObject json = new JSONObject();
		logger.info("查看证件审核对象{}", JSONObject.toJSONString(certificate, true));

		if (null == certificate) {
			throw new IllegalArgumentException("证件审核对象为空");
		}
		if (StringUtils.isEmpty(certificate.getCustId())) {
			throw new IllegalArgumentException("用户编号为空");
		}
		if (certificate.getAuthId() < 1) {
			throw new IllegalArgumentException("审核编号为空");
		}
		if (StringUtils.isEmpty(certificate.getCertificateState())) {
			throw new IllegalArgumentException("审核认证状态为空");
		}
		if (StringUtils.isEmpty(certificate.getCustName())) {
			throw new IllegalArgumentException("用户姓名为空");
		}
		try {
			User user = WebUtils.getUserInfo();
			certificate.setModifyId(String.valueOf(user.getUserId()));
			certificate.setCertifyUser(String.valueOf(user.getUserId()));
			if (certificate.getCertificateState().equals("0")) {
				String result = photoService.auditYes(certificate);
				if("SUCCESS".equals(result)){
					json.put("result", "SUCCESS");
				}else{
					json.put("result", "FAIL");
				}
			} else if (certificate.getCertificateState().equals("2")) {
				photoService.auditNo(certificate);
				json.put("result", "SUCCESS");
			}
		} catch (Exception e) {
			logger.error("请求更新审核信息异常", e);
			json.put("result", "fail");
			json.put("message", e.getMessage());
		}
		return json.toJSONString();

	}
}
