package com.qifenqian.bms.basemanager.certify;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.basemanager.certify.bean.Certify;
import com.qifenqian.bms.basemanager.certify.bean.FileBean;
import com.qifenqian.bms.basemanager.certify.bean.IdentityDetailBean;
import com.qifenqian.bms.basemanager.certify.service.CertifyService;
import com.qifenqian.bms.platform.web.admin.utils.WebUtils;

@Controller
@RequestMapping(CertifyPath.BASE)
public class CertifyController {

	private static Logger logger = LoggerFactory.getLogger(CertifyController.class);

	@Autowired
	private CertifyService certifyService;

	/**
	 * 查询证件信息
	 * 
	 * @param certify
	 * @return
	 */
	@RequestMapping(CertifyPath.LIST)
	public ModelAndView list(Certify certify) {
		ModelAndView mv = new ModelAndView(CertifyPath.BASE + CertifyPath.LIST);
		List<Certify> certifys = certifyService.selectCertifys(certify);
		String certifyType = certify.getCertifyType();
		String certifyName = certify.getCertifyName();
		mv.addObject("certifyType", certifyType);
		mv.addObject("certifyName", certifyName);
		mv.addObject("certifys", certifys);
		mv.addObject("certifyList", JSONObject.toJSONString(certifys));
		return mv;
	}

	/**
	 * 删除证件信息
	 * 
	 * @param certifyType
	 * @return
	 */
	@RequestMapping(CertifyPath.DELETE)
	@ResponseBody
	public String delete(String certifyType) {

		JSONObject js = new JSONObject();
		try {
			logger.info("删除证件[{}]", certifyType);
			certifyService.deleteByCertifyType(certifyType);
			js.put("result", "success");
		} catch (Exception e) {
			logger.error("请求删除异常", e);
			js.put("result", "fail");
			js.put("message", e.getMessage());
		}

		return js.toJSONString();
	}

	/**
	 * 新增证件
	 * 
	 * @param certify
	 * @return
	 */
	@RequestMapping(CertifyPath.ADD)
	@ResponseBody
	public String add(Certify certify) {
		JSONObject js = new JSONObject();

		try {
			logger.info("新增证件");
			Certify queryBean = new Certify();
			queryBean.setCertifyType(certify.getCertifyType());
			List<Certify> certifyList = certifyService.selectCertifys(queryBean);
			if (certifyList.size() > 0) {
				js.put("result", "fail");
				js.put("message", "证件代码已存在!");
			} else {
				certifyService.addCertify(certify);
				js.put("result", "success");
			}
		} catch (Exception e) {
			logger.error("请求新增证件异常", e);
			js.put("result", "fail");
			js.put("message", e.getMessage());
		}

		return js.toJSONString();
	}

	/**
	 * 更新证件
	 * 
	 * @param certify
	 * @return
	 */
	@RequestMapping(CertifyPath.UPDATE)
	@ResponseBody
	public String update(Certify certify) {
		JSONObject js = new JSONObject();

		try {
			logger.info("更新证件[{}]", certify.getCertifyType());
			certifyService.updateCertify(certify);
			js.put("result", "success");
		} catch (Exception e) {
			logger.error("请求更新证件证件异常", e);
			js.put("result", "fail");
			js.put("message", e.getMessage());
		}

		return js.toJSONString();
	}

	/**
	 * 查询文件信息
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(CertifyPath.FILE_LIST)
	public ModelAndView getFileList(FileBean queryBean) {
		ModelAndView mv = new ModelAndView(CertifyPath.BASE + CertifyPath.FILE_LIST);
		List<FileBean> fileList = certifyService.getFileList(queryBean);
		mv.addObject("fileList", fileList);
		mv.addObject("queryBean", queryBean);
		return mv;
	}
	
	/**
	 * 查询验证明细
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(CertifyPath.IDENTITY_DETAIL)
	public ModelAndView identityDetail(IdentityDetailBean queryBean) {
		ModelAndView mv = new ModelAndView(CertifyPath.BASE + CertifyPath.IDENTITY_DETAIL);
		List<IdentityDetailBean> detailList = certifyService.getIdentityDetail(queryBean);
		mv.addObject("detailList", detailList);
		mv.addObject("queryBean", queryBean);
		return mv;
	}
	
	/**
	 * 处理验证明细
	 * 
	 * @param String
	 * @return
	 */
	@RequestMapping(CertifyPath.DEAL)
	@ResponseBody
	public String dealValidateDetail(String validateId,String fileId,String memo) {
		JSONObject js = new JSONObject();
		try {
			logger.info("更新验证明细[{}]", validateId,fileId,memo);
			String auditUserId  = String.valueOf(WebUtils.getUserInfo().getUserId());
			certifyService.dealValidateDetail(validateId,fileId,memo,auditUserId);
			js.put("result", "SUCCESS");
		} catch (Exception e) {
			logger.error("请求处理验证明细异常", e);
			js.put("result", "fail");
			js.put("message", e.getMessage());
		}

		return js.toJSONString();
	}
	
	/**
	 * 文件上传
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(CertifyPath.FILEUPLOAD)
	@ResponseBody
	public String fileUpload(HttpServletRequest request, HttpServletResponse response) {

		logger.info("实名认证请求/返回文件上传");
		JSONObject object = new JSONObject();

		String result = certifyService.fileUpload(request, object);

		return result;
	}
}
