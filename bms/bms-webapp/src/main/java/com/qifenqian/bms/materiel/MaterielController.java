package com.qifenqian.bms.materiel;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.materiel.bean.Materiel;
import com.qifenqian.bms.materiel.service.MaterielService;
import com.qifenqian.bms.platform.web.admin.user.bean.User;
import com.qifenqian.bms.platform.web.admin.user.service.UserService;
import com.qifenqian.bms.platform.web.admin.utils.WebUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 * 物料管理控制层
 * 
 * @project qifenqian-bms
 * @fileName MaterielController.java
 * @author wuzz
 * @date 2019年11月4日
 * @memo
 */
@Controller
@RequestMapping("/materiel")
public class MaterielController {

	private Logger logger = LoggerFactory.getLogger(MaterielController.class);


	@Autowired
	private UserService userService;
	@Autowired
	private MaterielService materielService;
	
	
	
	
	/**
	 * 批量导入物料
	 * 
	 * @param file
	 * @return
	 */
	@RequestMapping("/upload")
	@ResponseBody
	public JSONObject export(@RequestParam("file") MultipartFile file) {
				
		  JSONObject jsonObject = new JSONObject(); 
		  try {
			  materielService.importExcel(file); 
			  jsonObject.put("result", "SUCCESS"); 
			  }catch (Exception e) { 
			  	logger.error("批量导入物料异常", e); 
			  	jsonObject.put("result","FAILURE"); 
			  	jsonObject.put("message", e.getMessage()); 
			  } 
		  return jsonObject;
		
	}


	

	/**
	 * 进入物料列表页面
	 * 
	 * @return
	 */
	@RequestMapping("/list")
	public ModelAndView list(Materiel requestMateriel) {
		// 返回视图
		ModelAndView mv = new ModelAndView("/materiel/list");	
		mv.addObject("materielList", JSONObject.toJSON(materielService.selectMaterielListByPage(requestMateriel)));
		mv.addObject("materiel", requestMateriel);
		mv.addObject("userlist", userService.getUserList(new User()));
		// 返回
		return mv;
	}
	
	
	/**
	 * 物料新增
	 * 
	 * @param requestMateriel
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public String add(Materiel requestMateriel) {
		// 请求bean 打印
		logger.info("请求保存Materiel：[{}]", JSONObject.toJSONString(requestMateriel, true));

		JSONObject jsonObject = new JSONObject();
		try {
			Materiel materiel = materielService.selectMaterielByMachineId(requestMateriel.getMachineId());
			if (materiel !=null) {
				jsonObject.put("result", "FAILURE");
				jsonObject.put("message", "机器编号已存在");
			} else {
				// 获取登录用户信息
				requestMateriel.setCreator(WebUtils.getUserInfo().getUserName());
				materielService.insertMaterielSingle(requestMateriel);
				jsonObject.put("result", "SUCCESS");
			}
		} catch (Exception e) {
			logger.error("新增物料信息异常", e);
			jsonObject.put("result", "FAILURE");
			jsonObject.put("message", e.getMessage());
		}
		return jsonObject.toJSONString();
	}

	/**
	 * 物料删除
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public String delete(int id) {
		logger.info("请求物料编号：[{}]", id);

		JSONObject jsonObject = new JSONObject();
		try {
			// 删除
			materielService.deleteMaterielById(id);
			jsonObject.put("result", "SUCCESS");
		} catch (Exception e) {
			logger.error("删除物料异常", e);
			jsonObject.put("result", "FAILURE");
			jsonObject.put("message", e.getMessage());
		}
		return jsonObject.toJSONString();
	}

	/**
	 * 物料修改
	 * 
	 * @param requestMateriel
	 * @return
	 */
	@RequestMapping("/update")
	@ResponseBody
	public String edit(Materiel requestMateriel) {
		// 请求bean 打印
		logger.info("请求修改Materiel：[{}]", JSONObject.toJSONString(requestMateriel, true));

		JSONObject jsonObject = new JSONObject();

		if (StringUtils.isEmpty(requestMateriel.getMachineId())) {
			throw new IllegalArgumentException("物料编码为空");
		}
		try {
			//查询需要修改的物料信息是否存在
			Materiel materiel = materielService.selectMaterielById(requestMateriel.getId());
			if (materiel == null) {
				jsonObject.put("result", "FAILURE");
				jsonObject.put("message", "物料信息不存在");
			} else {
				//查询修改的物料信息机器编码是否已经存在
				Materiel materiel1 = materielService.selectMaterielByMachineId(requestMateriel.getMachineId());
				if (materiel1 !=null && !(requestMateriel.getId().equals(materiel1.getId()))) {
					jsonObject.put("result", "FAILURE");
					jsonObject.put("message", "修改的机器编号已经存在");
				}else {
					requestMateriel.setLastupdateUser(WebUtils.getUserInfo().getUserName());
					materielService.updateMateriel(requestMateriel);
					jsonObject.put("result", "SUCCESS");
				}	
				
			}
		} catch (Exception e) {
			logger.error("修改物料异常", e);
			jsonObject.put("result", "FAILURE");
			jsonObject.put("message", e.getMessage());
		}
		return jsonObject.toJSONString();
	}


}
