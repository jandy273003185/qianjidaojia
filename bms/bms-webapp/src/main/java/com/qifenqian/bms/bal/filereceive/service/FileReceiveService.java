package com.qifenqian.bms.bal.filereceive.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.aspectj.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.bal.filereceive.bean.FileReceive;
import com.qifenqian.bms.bal.filereceive.dao.FileReceiveDao;
import com.qifenqian.bms.merchant.channel.bean.AgentMerInfo;
import com.qifenqian.bms.merchant.channel.dao.AgentMerDao;



@Service
public class FileReceiveService {
	
	private static Logger logger = LoggerFactory.getLogger(FileReceiveService.class);
	
	@Autowired
	private FileReceiveDao  fileReceiveDao;
	
	@Autowired
	private AgentMerDao agentMerDao;
		
	public List<FileReceive> selectFileReceiveList(FileReceive queryBean) {
		
		return fileReceiveDao.selectFileReceiveList(queryBean);
	}
	
	public String fileUpload(HttpServletRequest request, JSONObject object) {
		
		InputStream in = null;
		FileOutputStream out = null;
		try {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setHeaderEncoding("utf-8");
			List<FileItem> list=upload.parseRequest(request);
			FileItem item = list.get(0);
			
			if(item.isFormField()){
				object.put("result", "FAIL");
				object.put("message", "文件类型不匹配");
				return object.toJSONString();
			}
			
			String fileName = item.getName();
			String filedName = item.getFieldName();
			String name[] = filedName.split("@");
			String fileType = name[0];
			String workDate = name[1];
			String channelId = name[2];
			
			FileReceive inFileReceive =new FileReceive();
			inFileReceive.setFileName(fileName);
			inFileReceive.setFileType(fileType);
			inFileReceive.setWorkDate(workDate);
			inFileReceive.setChannelId(channelId);
			
			/** 读取配置文件，获取保存路径**/
			
				
		} catch (Exception e) {
			object.put("result", "FAIL");
			object.put("message", e.getMessage());
		}finally {
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
	 * @param oldName
	 * @param newName
	 */
	public static void renameFile(String oldName, String newName){
		
		File originalFile = new File(oldName);
		File newFile = new File(newName);
		
		if(!originalFile.exists()){
			throw new RuntimeException("文件不存在");
		}
		
		boolean renameIsSuccess =originalFile.renameTo(newFile);
		if(renameIsSuccess){
			logger.info("文件备份成功");
		}else {
			/*copy文件*/
			try {
				FileUtil.copyFile(originalFile,newFile);
				originalFile.delete();
				logger.info("文件备份成功");
			}catch (IOException e) {
				throw new RuntimeException("文件备份失败" + e.getMessage());
			}
		}
	}
	
	public List<AgentMerInfo> getMechantTypeInfo(String merchantId) {
		 AgentMerInfo agentMerInfo = new AgentMerInfo();
		 agentMerInfo.setMerNo(merchantId);
		// return fileReceiveDao.selectAgentMerInfo(agentMerInfo);
		 return agentMerDao.selectChannleAgentMerInfoByPrimaryKey(agentMerInfo);
	}
	
	
}
