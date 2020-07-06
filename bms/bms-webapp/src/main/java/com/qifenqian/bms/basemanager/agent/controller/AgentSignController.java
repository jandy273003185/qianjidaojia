package com.qifenqian.bms.basemanager.agent.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
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
import com.qifenqian.bms.basemanager.agent.bean.AgentSignBean;
import com.qifenqian.bms.basemanager.agent.bean.AgentSignVO;
import com.qifenqian.bms.basemanager.agent.service.AgentSignService;
import com.qifenqian.bms.platform.web.admin.utils.WebUtils;


@Controller
@RequestMapping(AgentSignPath.BASE)
public class AgentSignController {
	
	private static Logger logger = LoggerFactory.getLogger(AgentSignController.class);
	
	@Autowired
	private AgentSignService agentSignService;
	
	/***
	 * 签约信息列表
	 * 
	 * @param queryBean
	 * @return
	 */
	@RequestMapping(AgentSignPath.LIST)
	public ModelAndView list(AgentSignBean signBean) {
		logger.info("查询代理商申请信息列表");
		ModelAndView mv = new ModelAndView(AgentSignPath.BASE + AgentSignPath.LIST);
		List<AgentSignBean> signList = agentSignService.getSignList(signBean);
		mv.addObject("signList", signList);
		mv.addObject("queryBean", signBean);
		return mv;
	}
	
	/***
	 * 审核代理商申请
	 *
	 * 
	 * @param queryBean
	 * @return
	 */
	@RequestMapping(AgentSignPath.AUDIT)
	@ResponseBody
	public String audit(){
		logger.info("审核代理商申请");
		JSONObject jsonObject = new JSONObject();
		
		return jsonObject.toJSONString();
	}
	
	/***
	 * 查找签约信息
	 * @param custId
	 * @return
	 */
	@RequestMapping(AgentSignPath.FINDSIGNINFO)
	@ResponseBody
	public String findSignInfo(String custId){
		logger.info("查找签约信息--------------------");
		JSONObject jsonObject = new JSONObject();
		AgentSignVO signVO = agentSignService.findSignInfo(custId);
		String[] btypes = signVO.getBusinessType().split(",");
		StringBuffer ttype = new StringBuffer();
		
		for (int i = 0; i < btypes.length; i++) {
			switch (btypes[i]) {
			case "01":
				ttype.append("b2b");
				break;
			case "02":
				ttype.append(",b2c");
				break;
			case "03":
				ttype.append(",快捷");
				break;
			case "04":
				ttype.append(",实名");
				break;
			case "05":
				ttype.append(",预付卡");
				break;
			case "06":
				ttype.append(",代收");
				break;
			case "07":
				ttype.append("代付");
				break;
			default:
				break;
			}
		}
		signVO.setBusinessType(ttype.toString());
		jsonObject.put("agentSign", signVO);
		return jsonObject.toJSONString();
	}
	
	
	
	/***
	 * 签约申请不通过
	 * @param queryBean
	 * @return
	 */
	@RequestMapping(AgentSignPath.AUDITNOTPASS)
	@ResponseBody
	public String auditNotPass(String custId,String memo){
		logger.info("签约申请不通过--------------------");
		JSONObject jsonObject = new JSONObject();
		try {
			String auditUserId  = String.valueOf(WebUtils.getUserInfo().getUserId());
			agentSignService.auditNotPass(custId, memo,auditUserId);
			jsonObject.put("result", "SUCCESS");
			jsonObject.put("message", "代理商申请不通过");
		} catch (Exception e) {
			jsonObject.put("result", "FAIL");
			jsonObject.put("message", e.getMessage());
		}
		
		
		
		return jsonObject.toJSONString();
	}
	/***
	 * 签约申请通过
	 * @param queryBean
	 * @return
	 */
	@RequestMapping(AgentSignPath.AUDITPASS)
	@ResponseBody
	public String auditPass(String custId){
		logger.info("签约申请通过--------------------");
		JSONObject jsonObject = new JSONObject();
		try {
			String auditUserId  = String.valueOf(WebUtils.getUserInfo().getUserId());
			agentSignService.auditPass(custId,auditUserId);
			jsonObject.put("result", "SUCCESS");
			jsonObject.put("message", "签约申请不通过");
		} catch (Exception e) {
			jsonObject.put("result", "FAIL");
			jsonObject.put("message", e.getMessage());
		}
		
		return jsonObject.toJSONString();
	}
	

	/***
	 * 查询图片
	 * @param custId
	 * @return
	 */
	@RequestMapping(AgentSignPath.IMAGE)
	protected void image(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("服务器查找域名图片--------------------");
		String custId = request.getParameter("custId");
		OutputStream os = response.getOutputStream();
	    String scanPath=agentSignService.findScanPath(custId);
		File file = new File(scanPath);

		if (file.exists()) {
			FileInputStream fips = new FileInputStream(file);
			byte[] btImg = readStream(fips);
			os.write(btImg);
			os.flush();
			if (null != fips) {
				fips.close();
			}
			if (null != os) {
				os.close();

			}
		}
	}
	
	/**
	 * 读取管道中的流数据
	 */
	public byte[] readStream(InputStream inStream) {
		ByteArrayOutputStream bops = new ByteArrayOutputStream();
		int data = -1;
		try {
			while ((data = inStream.read()) != -1) {
				bops.write(data);
			}
			return bops.toByteArray();
		} catch (Exception e) {
			return null;
		} finally {
			if (null != bops) {
				try {
					bops.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}

	
}
