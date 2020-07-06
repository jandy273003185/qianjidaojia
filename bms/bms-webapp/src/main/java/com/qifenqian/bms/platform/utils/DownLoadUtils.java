package com.qifenqian.bms.platform.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class DownLoadUtils {

	private static Logger logger = LoggerFactory.getLogger(DownLoadUtils.class);

	public static boolean downLoadFile(String filePath, HttpServletResponse response, String fileName, String fileType)
			throws Exception {
		logger.info("*************the file path to download is :**************" + filePath);
		File file = new File(filePath); // 根据文件路径获得File文件
		if ("pdf".equals(fileType)) {
			response.setContentType("application/pdf;charset=GBK");
		} else if ("xls".equals(fileType)) {
			response.setContentType("application/msexcel;charset=GBK");
		} else if ("doc".equals(fileType)) {
			response.setContentType("application/msword;charset=GBK");
		}
		// 文件名
		response.setHeader("Content-Disposition", "attachment;filename=\""
				+ new String(fileName.getBytes(), "ISO8859-1") + "\"");
		response.setContentLength((int) file.length());
		byte[] buffer = new byte[4096];// 缓冲区
		BufferedOutputStream output = null;
		BufferedInputStream input = null;
		try {
			output = new BufferedOutputStream(response.getOutputStream());
			input = new BufferedInputStream(new FileInputStream(file));
			int n = -1;
			// 遍历，开始下载
			while ((n = input.read(buffer, 0, 4096)) > -1) {
				output.write(buffer, 0, n);
			}
			output.flush();
			response.flushBuffer();
		} catch (Exception e) {
			logger.info("***************write file error***************", e);
			e.printStackTrace();
			// 异常自己捕捉
		} finally {
			// 关闭流
			if (input != null)
				input.close();
			if (output != null)
				output.close();
		}
		return false;
	}
}
