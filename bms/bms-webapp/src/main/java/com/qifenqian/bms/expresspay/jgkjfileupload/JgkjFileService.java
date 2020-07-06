package com.qifenqian.bms.expresspay.jgkjfileupload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.accounting.checkquery.type.ChannelId;
import com.qifenqian.bms.expresspay.jgkjfileupload.bean.FileOffer;
import com.qifenqian.bms.expresspay.jgkjfileupload.dao.FileOfferDao;
import com.qifenqian.bms.expresspay.jgkjfileupload.mapper.FileOfferMapper;
import com.sevenpay.invoke.common.type.RequestColumnValues;

/**
 * 交广科技对账/清算文件上传
 * 
 * @author shen
 * 
 */
@Service
public class JgkjFileService {

	public static Logger logger = LoggerFactory.getLogger(JgkjFileService.class);

	@Autowired
	private FileOfferDao fileOfferDao;

	@Autowired
	private FileOfferMapper fileOfferMapper;

	/** 交广科技清算 **/
	public static final String JGKJ_SETTLE_FILENAME = "Accountant-00000000000000000035-CUPEVE-{yyyyMMdd}";
	/** 交广科技对账 **/
	public static final String JGKJ_BALANCE_FILENAME = "00000000000000000035-TRANS-{yyyyMMdd}";
	
	@Value("${JGKJ_FILE_PATH}")
	private String JGKJ_FILE_PATH;

	/**
	 * 交广科技文件上传
	 * 
	 * @param request
	 * @param object
	 * @return
	 */
	public String fileUpload(HttpServletRequest request, JSONObject object) {
		Date now = new Date();
		long totalCount = 0;
		InputStream in = null;
		FileOutputStream out = null;
		LineNumberReader lnr = null;
		String nowShortDatetime = DateFormatUtils.format(now, "yyyyMMddHHmmss");
		try {

			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setHeaderEncoding("utf-8");
			List<FileItem> list = upload.parseRequest(request);
			List<String> paths = new ArrayList<String>();
			FileItem item = list.get(0);

			if (item.isFormField()) {
				object.put("result", "FAIL");
				object.put("message", "文件类型不匹配");
				return object.toJSONString();
			}
			String fileName = item.getName();
			String filedName = item.getFieldName();
			String name[] = filedName.split("@");
			String fileType = name[0];
			String workDate = name[1];

			/** 读取配置文件 获取保存路径 **/
			String jgkj_path = JGKJ_FILE_PATH; //p.getProperty("JGKJ_FILE_PATH");

			if (fileType.equals("BALANCE")) {

				fileName = JGKJ_BALANCE_FILENAME.replace("{yyyyMMdd}", workDate);

			} else if (fileType.equals("SETTLE")) {

				fileName = JGKJ_SETTLE_FILENAME.replace("{yyyyMMdd}", workDate);
			}

			jgkj_path = jgkj_path + File.separator + workDate;

			String filePathName = jgkj_path + File.separator + fileName;

			/** 验证文件数据 **/
			lnr = new LineNumberReader(new InputStreamReader(item.getInputStream(), "utf-8"));
			String value = lnr.readLine();
			if (StringUtils.isBlank(value)) {
				throw new RuntimeException("汇总数记录不存在");
			}
			String totalCountStr = value.substring(0, 10).replaceAll(" ", "");

			if (!totalCountStr.matches("\\d+")) {
				throw new RuntimeException("汇总数记录格式非法");
			}
			int count = 0;
			if (null != value) {
				while (value != null) {
					count++;
					value = lnr.readLine();
				}
			}

			totalCount = count - 1;
			logger.info("上传文件有效行数：{}", totalCount);

			if (totalCount != Long.parseLong(totalCountStr)) {
				throw new RuntimeException("汇总数记录数与实际条数不符");
			}

			/** 判断文件是否存在 **/
			FileOffer fileOffer = fileOfferMapper.selectByFileName(fileName);

			logger.info("查询旧文件结果：{}", JSONObject.toJSONString(fileOffer, true));
			if (null != fileOffer) {

				if (fileOffer.getStatus().equals("BAL_ING")) {
					object.put("result", "FAIL");
					object.put("message", "存在正在对账的文件，请稍后重试");
					return object.toJSONString();
				}

				String originalFilenameBak = fileName + ".INVALID-" + nowShortDatetime;
				renameFile(fileOffer.getStoragePath() + File.separator + fileName, fileOffer.getStoragePath()
						+ File.separator + originalFilenameBak);
				FileOffer updateBean = new FileOffer();
				updateBean.setFileId(fileOffer.getFileId());
				updateBean.setFileName(originalFilenameBak);
				updateBean.setStatus("INVALID");
				fileOfferMapper.update(updateBean);
			}

			File saveFile = new File(jgkj_path);
			if (!saveFile.exists()) {
				saveFile.mkdirs();
			}
			out = new FileOutputStream(filePathName);

			paths.add(filePathName);

			in = item.getInputStream();
			byte buffer[] = new byte[1024];
			int len = 0;
			while ((len = in.read(buffer)) > 0) {
				out.write(buffer, 0, len);
			}
			item.delete();
			out.flush();

			FileOffer insertBean = new FileOffer();
			insertBean.setFileId(ChannelId.JGKJ.name() + "-" + fileType + "-FILE-"
					+ DateFormatUtils.format(now, "yyyyMMddHHmmss"));
			insertBean.setChannelId(ChannelId.JGKJ.name());
			insertBean.setFileType(fileType);
			insertBean.setReceiveDate(DateFormatUtils.format(now, "yyyy-MM-dd"));
			insertBean.setWorkDate(workDate);
			insertBean.setFileName(fileName);
			insertBean.setStoragePath(jgkj_path);
			insertBean.setTotalCount(totalCount);
			insertBean.setStatus(RequestColumnValues.MsgStatus.INIT.name());
			fileOfferMapper.insert(insertBean);
			object.put("result", RequestColumnValues.MsgStatus.SUCCESS.name());
			object.put("message", "上传成功");
			object.put("paths", JSONObject.toJSONString(paths));

		} catch (Exception e) {
			object.put("result", "FAIL");
			object.put("message", e.getMessage());
		} finally {
			try {
				if (null != in) {
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if (null != out) {
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		return object.toJSONString();
	}

	/**
	 * 交广科技文件列表
	 * 
	 * @param queryBean
	 * @return
	 */
	public List<FileOffer> selectByBean(FileOffer queryBean) {
		return fileOfferDao.selectByBean(queryBean);
	}

	/**
	 * 重命名
	 * 
	 * @param oldName
	 * @param newName
	 */
	public static void renameFile(String oldName, String newName) {
		File originalFile = new File(oldName);
		File newFile = new File(newName);
		if (!originalFile.exists()) {
			throw new RuntimeException("文件不存在");
		}
		boolean renameIsSuccess = originalFile.renameTo(newFile);
		if (renameIsSuccess) {
			logger.info("####备份文件成功");
		} else {
			try {
				FileUtils.copyFile(originalFile, newFile);
				originalFile.delete();
				logger.info("@@@@备份文件成功");
			} catch (IOException e) {
				throw new RuntimeException("备份文件失败" + e.getMessage());
			}
		}
	}

	public boolean delete(FileOffer fileOffer) {
		boolean flag = false;
		String sPath = fileOffer.getStoragePath() + File.separator + fileOffer.getFileName();
		File file = new File(sPath);
		if (!file.exists()) { // 不存在返回 false
			flag = true;
		} else {
			// 判断是否为文件
			if (file.isFile()) {
				// 为文件时调用删除文件方法
				file.delete();
				flag = true;
			}else{
				flag = false;	
			}
		}
		return flag;
	}
}
