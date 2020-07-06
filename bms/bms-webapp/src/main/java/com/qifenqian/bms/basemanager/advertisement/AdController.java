package com.qifenqian.bms.basemanager.advertisement;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.basemanager.advertisement.bean.Ad;
import com.qifenqian.bms.basemanager.advertisement.service.AdService;
import com.qifenqian.bms.basemanager.bank.BankController;

@Controller
@RequestMapping(AdPath.BASE)
public class AdController {
	private static Logger logger = LoggerFactory.getLogger(BankController.class);

	@Autowired
	private AdService service;

	@Value("${AD_FILE_SAVE_PATH}")
	private String AD_FILE_SAVE_PATH;
	
	/**
	 * 显示广告信息列表
	 * 
	 * @param ad
	 * @return
	 */
	@RequestMapping(AdPath.LIST)
	public ModelAndView list(Ad queryBean) {
		ModelAndView mv = new ModelAndView(AdPath.BASE + AdPath.LIST);
		List<Ad> list = service.selectAdList(queryBean);
		mv.addObject("adList", JSONObject.toJSON(list));
		mv.addObject("queryBean", queryBean);
		return mv;
	}

	/**
	 * 广告
	 * 
	 * @param ad
	 * @return
	 */
	@RequestMapping(AdPath.ADD)
	@ResponseBody
	public String add(Ad insertBean) {
		logger.info("增加广告");
		JSONObject jsonObject = new JSONObject();
		
		try {
			service.addAd(insertBean);
			jsonObject.put("result", "success");
		} catch (Exception e) {
			logger.error("增加广告出错：", e);
			jsonObject.put("result", "fail");
			jsonObject.put("message", e.getMessage());
		}
		return jsonObject.toJSONString();
	}

	/**
	 * 更新广告
	 * 
	 * @param ad
	 * @return
	 */
	@RequestMapping(AdPath.UPDATE)
	@ResponseBody
	public String update(Ad updateBean) {
		logger.info("更新广告信息");
		JSONObject jsonObject = new JSONObject();
		try {
			
			if (null!=updateBean.getImagePath2()&&!updateBean.getImagePath2().equals("")) {
				delImagFile(updateBean.getImagePath2());
			}
			service.updateAd(updateBean);
			
			jsonObject.put("result", "success");
			logger.info("广告更新完成！");
		} catch (Exception e) {
			logger.error("更新广告未成功", e);
			jsonObject.put("result", "fail");
			jsonObject.put("message", e.getMessage());
		}
		// 删除服务器上传重复图片
		
		return jsonObject.toJSONString();

	}

	// 删除服务器图片
	public void delImagFile(String path) {
		// path=path.replaceAll("\\\\","\\\\\\\\");
		File imgFile = new File(path);
		if (imgFile.exists()) {
			imgFile.delete();
		}
	}

	/**
	 * 文件上传
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(AdPath.FILEUPLOAD)
	@ResponseBody
	public String fileUpload(HttpServletRequest request, HttpServletResponse response) {
		
		JSONObject object = new JSONObject();
		String pathName= "";
		try {
			// 使用Apache文件上传组件处理文件上传步骤：
			// 创建一个DiskFileItemFactory工厂
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// 创建一个文件上传解析器
			ServletFileUpload upload = new ServletFileUpload(factory);
			// 解决上传文件名的中文乱码
			upload.setHeaderEncoding("UTF-8");
			List<FileItem> list = upload.parseRequest(request);

			InputStream in = null;
			
			for (FileItem item : list) {
				String filename = null;
				if (!item.isFormField()) {
					filename = item.getName();

					String type = filename.substring(filename.lastIndexOf("."));
					String[] photoTypes = { ".jpg", ".jpeg", ".gif", ".bmp", ".png" };
					boolean isType = false;
					for (int i = 0; i < photoTypes.length; i++) {
						if (photoTypes[i].equalsIgnoreCase(type)) {
							isType = true;
							break;
						}
					}
					if (!isType) {
						object.put("result", "FAIL");
						object.put("message", "文件类型不匹配");

						return object.toJSONString();

					}
					filename = filename.substring(filename.lastIndexOf(File.separator) + 1);
					
					filename = System.currentTimeMillis() + filename;
					in = item.getInputStream();

					
					pathName = AD_FILE_SAVE_PATH + File.separator + filename;

					File saveFile = new File(AD_FILE_SAVE_PATH);
					if (!saveFile.exists()) {
						saveFile.mkdirs();
					}
					FileOutputStream out = new FileOutputStream(pathName);

					byte buffer[] = new byte[1024];
					int len = 0;
					while ((len = in.read(buffer)) > 0) {
						out.write(buffer, 0, len);
					}
					in.close();
					out.close();
					item.delete();
				}

			}
			
			logger.info("上传路径****" + pathName);
			object.put("result", "SUCCESS");
			object.put("message", "上传成功");
			object.put("pathName", pathName);

		} catch (Exception e) {
			logger.error("上传失败", e);
			object.put("result", "fail");
			object.put("message", e.getMessage());
		}
		return object.toJSONString();
	}

	// 读取服务器图片
	@RequestMapping(AdPath.IMAGE)
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String adId = request.getParameter("adId");
		String imagePath = "";
		Ad ad = service.selectAdById(adId);
		imagePath = ad.getImagePath();
		if (imagePath != null) {
			OutputStream os = response.getOutputStream();
			File file = new File(imagePath);
			FileInputStream fips = new FileInputStream(file);
			if (file.exists()) {
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
