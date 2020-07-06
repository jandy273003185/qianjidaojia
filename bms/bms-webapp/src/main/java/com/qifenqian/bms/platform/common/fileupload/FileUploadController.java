package com.qifenqian.bms.platform.common.fileupload;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * @project sevenpay-bms-web
 * @fileName FileUploadController.java
 * @author huiquan.ma
 * @date 2015年6月16日
 * @memo 
 */
@Controller
public class FileUploadController {
	
   @RequestMapping(FileUploadPath.BASE)  
   public String handleFormUpload(@RequestParam("file") MultipartFile file) {  
	    //MultipartFile是对当前上传的文件的封装，当要同时上传多个文件时，可以给定多个MultipartFile参数  
	    if (!file.isEmpty()) {  
	    	System.out.println(file.getOriginalFilename());
//	        byte[] bytes = file.getBytes();  
	        // store the bytes somewhere  
	        //在这里就可以对file进行处理了，可以根据自己的需求把它存到数据库或者服务器的某个文件夹  
	        return "redirect:uploadSuccess";  
	   } else {  
	       return "redirect:uploadFailure";  
	   }  
	}  
}


