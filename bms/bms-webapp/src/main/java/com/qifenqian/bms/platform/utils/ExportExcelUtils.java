package com.qifenqian.bms.platform.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.qifenqian.bms.accounting.utils.ExportExcel;



@Service
public class ExportExcelUtils {
	
	@Value("${EXPORT_EXCEL}")
	private static String EXPORT_EXCEL;
	
	/**
	 * 导出excel
	 * 
	 * @param excels
	 *            excel对象集合
	 * @param headers
	 *            excel列表头信息
	 * @param fileName
	 *            保存文件名称
	 * @param request
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes", "finally" })
	public static Map<String, String> exportExcel(List excels, String[] headers, 
			String fileName, String title,HttpServletRequest request) {
		Map<String, String> fileInfo = new HashMap<String, String>();
		OutputStream out = null;
		try {

			//Properties p = PropertiesUtil.getProperties();
			String exportPath = EXPORT_EXCEL; //p.getProperty("EXPORT_EXCEL");
			File saveFile = new File(exportPath);
			if (!saveFile.exists()) {
				saveFile.mkdirs();
			}
			String filePath = saveFile + File.separator + fileName;
			fileInfo.put("fileName", fileName);
			fileInfo.put("filePath", filePath);
			out = new FileOutputStream(filePath);

			ExportExcel<T> ex = new ExportExcel<T>();

			ex.exportExcel(title, headers, excels, out);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return fileInfo;
		}

	}
}
