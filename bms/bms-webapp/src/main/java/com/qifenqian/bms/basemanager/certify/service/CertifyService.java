package com.qifenqian.bms.basemanager.certify.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.UnsupportedEncodingException;
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
import com.qifenqian.bms.basemanager.Constant;
import com.qifenqian.bms.basemanager.certify.bean.Certify;
import com.qifenqian.bms.basemanager.certify.bean.FileBean;
import com.qifenqian.bms.basemanager.certify.bean.IdentityDetailBean;
import com.qifenqian.bms.basemanager.certify.mapper.CertifyMapper;
import com.qifenqian.bms.basemanager.dictData.mapper.DictDataMapper;
import com.qifenqian.bms.platform.common.utils.SpringUtils;
import com.qifenqian.bms.platform.utils.BusinessUtils;
import com.qifenqian.bms.platform.web.page.Page;
import com.qifenqian.bms.sms.thread.BaseMessageThreadPool;
import com.sevenpay.invoke.common.type.RequestColumnValues;

/**
 * 证件服务层
 * 
 * @project sevenpay-bms-web
 * @fileName CertifyService.java
 * @author Dayet
 * @date 2015年5月12日
 * @memo
 */
@Service
public class CertifyService {

	/** 交广科技清算 **/
	public static final String IDENTITY_REQ_FILENAME = "sevenpay-{yyyyMMdd}_req";
	/** 交广科技对账 **/
	public static final String IDENTITY_RES_FILENAME = "sevenpay-{yyyyMMdd}_res";

	public static Logger logger = LoggerFactory.getLogger(CertifyService.class);

	@Autowired
	private CertifyMapper certifyMapper;

	@Autowired
	private DictDataMapper dictDataMapper;
	
	@Value("${IDENTITY_FILE_SAVE_PATH}")
	private String IDENTITY_FILE_SAVE_PATH;

	/**
	 * 查询所有证件信息
	 * 
	 * @return
	 */
	public List<Certify> selectCertifys(Certify certify) {
		return certifyMapper.selectCertifys(certify);
	}

	/**
	 * 删除指定的证件
	 * 
	 * @param certifyType
	 */
	public void deleteByCertifyType(String certifyType) {

		if (StringUtils.isEmpty(certifyType)) {
			throw new IllegalArgumentException("证件编号为空");
		}
		certifyMapper.deleteBankByBankCode(certifyType);
	}

	/**
	 * 增加证件信息
	 * 
	 * @param certify
	 */
	public void addCertify(Certify certify) {

		if (null == certify) {
			throw new IllegalArgumentException("证件对象为空");
		}

		if (StringUtils.isEmpty(certify.getCertifyName())) {
			throw new IllegalArgumentException("证件名称为空");
		}

		if (StringUtils.isEmpty(certify.getCertifyType())) {
			throw new IllegalArgumentException("证件类型为空");
		}
		certifyMapper.insertCertify(certify);
	}

	/**
	 * 更新证件
	 * 
	 * @param certify
	 */
	public void updateCertify(Certify certify) {

		if (null == certify) {
			throw new IllegalArgumentException("证件对象为空");
		}

		if (StringUtils.isEmpty(certify.getCertifyName())) {
			throw new IllegalArgumentException("证件名称为空");
		}

		if (StringUtils.isEmpty(certify.getCertifyType())) {
			throw new IllegalArgumentException("证件类型为空");
		}
		certifyMapper.updateCertify(certify);
	}

	/**
	 * 查询实名认证文件信息
	 * 
	 * @param queryBean
	 * @return
	 */
	@Page
	public List<FileBean> getFileList(FileBean queryBean) {
		List<FileBean> fileList = certifyMapper.getFileList(queryBean);
		return fileList;
	}

	/**
	 * 查询实名认证验证详情
	 * 
	 * @param queryBean
	 * @return
	 */
	@Page
	public List<IdentityDetailBean> getIdentityDetail(IdentityDetailBean queryBean) {
		List<IdentityDetailBean> detailList = certifyMapper.getIdentityDetail(queryBean);
		return detailList;
	}

	/**
	 * 处理验证明细
	 * 
	 * @param validateId
	 * @param fileId
	 * @param memo
	 */

	public void dealValidateDetail(String validateId, String fileId, String memo, String auditUserId) {
		certifyMapper.dealValidateDetail(validateId, fileId, memo, auditUserId);
	}

	/**
	 * 实名认证请求/返回文件上传
	 * 
	 * @param request
	 * @param object
	 * @return
	 */
	public String fileUpload(HttpServletRequest request, JSONObject object) {

		Date now = new Date();
		InputStream in = null;
		FileOutputStream out = null;
		LineNumberReader lnr = null;
		String nowShortDatetime = DateFormatUtils.format(now, "yyyyMMddHHmmss");
		try {

			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			String verificationFileEncoding = dictDataMapper.getDataValueByPath("GI.VERIFICATION.CHARSET");
			upload.setHeaderEncoding(verificationFileEncoding);
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
			String identityFilePath = IDENTITY_FILE_SAVE_PATH;

			if (fileType.equals(Constant.REQUEST_FILE_TYPE)) {

				fileName = IDENTITY_REQ_FILENAME.replace("{yyyyMMdd}", workDate);

			} else if (fileType.equals(Constant.RESPONSE_FILE_TYPE)) {

				fileName = IDENTITY_RES_FILENAME.replace("{yyyyMMdd}", workDate);

			}

			identityFilePath = identityFilePath + File.separator + workDate;

			logger.info("实名认证请求/返回文件上传文件名：{} 文件路径: {}", fileName, identityFilePath);

			String filePathName = identityFilePath + File.separator + fileName;

			/** 验证文件数据 **/
			lnr = new LineNumberReader(new InputStreamReader(item.getInputStream(), verificationFileEncoding));

			String value = lnr.readLine();
			int totalCount = 0;
			if (null != value) {
				while (value != null) {
					totalCount++;
					value = lnr.readLine();
				}
			}

			logger.info("上传文件有效行数：{}", totalCount);

			/** 判断文件是否存在 **/
			FileBean fileOffer = certifyMapper.selectByFileName(fileName);

			logger.info("查询旧文件结果：{}", JSONObject.toJSONString(fileOffer, true));
			if (null != fileOffer) {

				if (fileOffer.getStatus().equals("UPLOAD_ING")) {
					object.put("result", "FAIL");
					object.put("message", "存在正在上传的文件，请稍后重试");
					return object.toJSONString();
				}

				String originalFilenameBak = fileName + "." + Constant.UPLOAD_FILE_STATUS_INVALID + "-" + nowShortDatetime;
				renameFile(fileOffer.getStoragePath() + File.separator + fileName, fileOffer.getStoragePath() + File.separator + originalFilenameBak);
				FileBean updateBean = new FileBean();
				updateBean.setFileId(fileOffer.getFileId());
				updateBean.setFileName(originalFilenameBak);
				updateBean.setStatus(Constant.UPLOAD_FILE_STATUS_INVALID);
				certifyMapper.updateFile(updateBean);

				if (fileOffer.getFileType().equals(Constant.REQUEST_FILE_TYPE)) {
					IdentityDetailBean update = new IdentityDetailBean();
					update.setFileId(fileOffer.getFileId());
					certifyMapper.deleteIdentityDetailBean(update);
				}

			}

			File saveFile = new File(identityFilePath);
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

			FileBean insertBean = new FileBean();
			String fileId = BusinessUtils.getMsgId();
			insertBean.setFileId(fileId);
			insertBean.setFileType(fileType);
			insertBean.setWorkDate(workDate);
			insertBean.setFileName(fileName);
			insertBean.setStoragePath(identityFilePath);
			insertBean.setTotalCount(totalCount);
			insertBean.setStatus(Constant.UPLOAD_FILE_STATUS_SUCCESS);
			certifyMapper.insertFile(insertBean);

			FileBean file = certifyMapper.selectByFileName(fileName);
			File uploadFile = new File(file.getStoragePath(), fileName);

			if (!uploadFile.exists()) {
				throw new RuntimeException(uploadFile.getAbsolutePath() + "插入的对账文件在本地不存在");
			}

			LineNumberReader reqLnr = null;
			try {
				reqLnr = new LineNumberReader(new InputStreamReader(new FileInputStream(uploadFile), verificationFileEncoding));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			List<IdentityDetailBean> identityList = new ArrayList<IdentityDetailBean>();

			if (file.getFileType().equals(Constant.REQUEST_FILE_TYPE)) {

				insertAndStorage(reqLnr, file.getTotalCount(), fileId, workDate, identityList);

				certifyMapper.insertIdentityDetail(identityList);

			} else if (file.getFileType().equals(Constant.RESPONSE_FILE_TYPE)) {

				updateAndStorage(reqLnr, file.getTotalCount(), workDate, identityList);
				IdentityDetailBean update = new IdentityDetailBean();
				update.setWorkDate(workDate);
				certifyMapper.updateIdentityDetialList(update);

			}

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

	/***
	 * 请求数据处理
	 * 
	 * @param lnr
	 * @param readCount
	 * @param fileId
	 * @param workDate
	 * @param identityList
	 */
	private void insertAndStorage(LineNumberReader lnr, long readCount, String fileId, String workDate, List<IdentityDetailBean> identityList) {

		if (null == lnr) {
			throw new IllegalArgumentException("读取流为空");
		}
		if (readCount < 1) {
			throw new IllegalArgumentException("读取总数非法|" + readCount);
		}
		try {
			long forCount = Math.min(5000000, readCount);

			for (int i = 0; i < forCount; i++) {
				String readStr = lnr.readLine();
				if (readStr != null) {
					String[] str = readStr.split(",");
					String validateId = str[0];
					String custName = str[1];
					String certifyNo = str[2];

					if (StringUtils.isBlank(validateId) || StringUtils.isBlank(custName) || StringUtils.isBlank(certifyNo)) {
						logger.error("数据异常" + readStr);
					} else {
						IdentityDetailBean insertBean = new IdentityDetailBean();
						insertBean.setFileId(fileId);
						insertBean.setValidateId(validateId);
						insertBean.setCustName(custName);
						insertBean.setCertifyNo(certifyNo);
						insertBean.setWorkDate(workDate);
						identityList.add(insertBean);
					}
				}
			}
			long leftCount = readCount - forCount;
			if (leftCount > 0) {
				this.insertAndStorage(lnr, readCount, fileId, workDate, identityList);
			}
		} catch (Exception e) {
			logger.error("遍历读取异常", e);
			throw new RuntimeException("遍历读取异常", e);
		}
	}

	/***
	 * 返回数据处理
	 * 
	 * @param lnr
	 * @param readCount
	 * @param identityPath
	 * @param fileName
	 * @param workDate
	 * @param identityList
	 */
	private void updateAndStorage(LineNumberReader lnr, long readCount, String workDate, List<IdentityDetailBean> identityList) {
		if (null == lnr) {
			throw new IllegalArgumentException("读取流为空");
		}
		if (readCount < 1) {
			throw new IllegalArgumentException("读取总数非法|" + readCount);
		}
		try {
			long forCount = Math.min(5000000, readCount);

			for (int i = 0; i < forCount; i++) {
				String readStr = lnr.readLine();
				if (readStr != null) {
					String[] str = readStr.split(",", -1);
					String validateId = str[0];
					String rtnCode = str[1];
					String rtnMsg = str[2];

					if (StringUtils.isBlank(validateId) || StringUtils.isBlank(rtnCode)) {
						logger.error("数据异常" + readStr);
					} else {
						IdentityDetailBean updateBean = new IdentityDetailBean();
						updateBean.setValidateId(validateId);
						updateBean.setWorkDate(workDate);
						updateBean.setRtnCode(rtnCode);
						updateBean.setRtnMsg(rtnMsg);

						if (rtnCode.equals(Constant.IDENTITYVERIFICATION_STATUS_SUCCESS)) {
							/** 成功 **/
							updateBean.setStatus(Constant.VALIDATE_STATUS_SUCCESS);
							updateBean.setDealStatus("02");
						} else if (rtnCode.equals(Constant.IDENTITYVERIFICATION_STATUS_FAILURE)) {
							/** 失败 **/
							updateBean.setStatus(Constant.VALIDATE_STATUS_FAILURE);
						} else if (rtnCode.equals(Constant.IDENTITYVERIFICATION_STATUS_NONE)) {
							/** 不匹配 **/
							updateBean.setStatus(Constant.VALIDATE_STATUS_FAILURE);
						}
						CertifyTread certifyTread = (CertifyTread) SpringUtils.getBean("certifyTread");
						certifyTread.setIdentityDetailBean(updateBean);
						BaseMessageThreadPool.getInstance().put(certifyTread);

					}
				}
			}
			long leftCount = readCount - forCount;
			if (leftCount > 0) {
				this.updateAndStorage(lnr, readCount, workDate, identityList);
			}
		} catch (Exception e) {
			logger.error("遍历读取异常", e);
			throw new RuntimeException("遍历读取异常", e);
		}
	}

	public void updateIdentityDetial(IdentityDetailBean updateBean) {
		certifyMapper.updateIdentityDetial(updateBean);
	}

}
