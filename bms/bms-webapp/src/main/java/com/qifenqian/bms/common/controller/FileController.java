package com.qifenqian.bms.common.controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.basemanager.merchant.bean.CustScan;
import com.qifenqian.bms.basemanager.merchant.bean.MerchantVo;
import com.qifenqian.bms.basemanager.merchant.bean.PicturePath;
import com.qifenqian.bms.basemanager.merchant.mapper.CustScanMapper;
import com.qifenqian.bms.basemanager.merchant.mapper.MerchantMapper;
import com.qifenqian.bms.basemanager.merchant.service.MerchantEnterService;
import com.qifenqian.bms.platform.common.utils.DateUtils;

@Controller
@RequestMapping("/common/files")
public class FileController {

	@Autowired
	private CustScanMapper custScanMapper;
	@Autowired
	private MerchantMapper merchantMapper;
	@Autowired
	private MerchantEnterService merchantEnterService;
	
	private static final String PRE_PATH = "/data/nfsshare/upload/picture";
//	private static final String PRE_PATH = "D:\\data\\nfsshare\\upload\\picture";
	private static final Logger logger = LoggerFactory.getLogger(FileController.class);
	
	
    @Value("${images.relativePaths}")
    private String relativePaths;
    //存储路径
    @Value("${images.absolutePaths}")
    private String absolutePaths;
	
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> upload(MultipartFile file) {
		return fileUpload(file);
	}
	
	@RequestMapping(value = "/moveFile")
	@ResponseBody
	public Map<String, String> moveFile(String custId) {
		Map<String, String> result = new HashMap<String, String>();
		//移动文件
		String fileNameAndPath = "";
		
			List<CustScan> listTdCustScanCopy = custScanMapper.ListTdCustScanCopy(custId);
			for (CustScan custScan : listTdCustScanCopy) {
				if (custScan.getScanCopyPath().indexOf("/null") != -1) {
					continue;
				}
				String[] scanCopyPaths = custScan.getScanCopyPath().split(";");
				for (int i = 0; i < scanCopyPaths.length; i++) {
					fileNameAndPath = custScan.getCustId() + ":" + scanCopyPaths[i];
					Path sourcePath = Paths.get(scanCopyPaths[i]);
					//转换新路径
					String sourceFileName = sourcePath.getFileName().toString();
					String prefix = "";
					if(-1 == sourceFileName.lastIndexOf(".")) {
						prefix =".jpg";
					}else {
						prefix = sourceFileName.substring(sourceFileName.lastIndexOf("."));
					}
					String newFilename = DateUtils.getDateStr8()+"_"+UUID.randomUUID().toString().replaceAll("-","");
					StringBuilder filePath = new StringBuilder(PRE_PATH).append("/").append(newFilename).append(prefix);
					
					try {
						Path targetPath = Paths.get(filePath.toString());
						Path temp = Files.move(sourcePath, targetPath);
						if(temp != null) {
							CustScan newCustScan = new CustScan();
							BeanUtils.copyProperties(custScan, newCustScan);
							if (i == scanCopyPaths.length - 1) {
								//停用旧数据
								custScan.setStatus("01");
								custScanMapper.updateCustScan(custScan);
							}
							//插入新数据
							if (scanCopyPaths.length > 1) {
								newCustScan.setCertifyType(i == 0 ? "04" : "16");
							}
							newCustScan.setScanCopyPath(newFilename + prefix);
							newCustScan.setStatus("00");
							custScanMapper.insertCustScan(newCustScan);
							
							logger.info("移动文件成功,客户号custId：路径为{}", fileNameAndPath);
				        }else {
				        	logger.warn("移动文件失败,客户号custId：路径为{}", fileNameAndPath);
				        }
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("移动文件异常,客户号custId：路径为{};异常信息：{}", fileNameAndPath, e.getMessage());
					}
					
				}
			}
			result.put("code", "SUCCESS");
			result.put("message", "移动成功");
		
		return result;
	}

	/**
	 * 文件下载， 只支持图片类型的文件
	 * 
	 * @param path
	 * @param httpResponse
	 * @throws IOException
	 */
	@RequestMapping(value = "/get", method = RequestMethod.POST)
	@ResponseBody
	public void get(String path, HttpServletResponse httpResponse) throws IOException {

		File file = new File(path);

		if (!file.exists()) {
			String errorMessage = "Sorry. The file you are looking for does not exist";
			System.out.println(errorMessage);
			OutputStream outputStream = httpResponse.getOutputStream();
			outputStream.write(errorMessage.getBytes("UTF-8"));
			outputStream.close();
			return;
		}
		
		ImageIO.write(ImageIO.read(file), "JPEG", httpResponse.getOutputStream());

	}
	
	
	@RequestMapping(value = "/uploadPic", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> fileUpload(@RequestParam("file")MultipartFile file){
		Map<String, Object> result = new HashMap<String, Object>();
		logger.info("-------开始上传文件------file=="+file);
		// 获取文件名后缀名
        String suffix = file.getOriginalFilename();
        String prefix = suffix.substring(suffix.lastIndexOf("."));
        
        String Filename = DateUtils.getDateStr8()+"_"+UUID.randomUUID().toString().replaceAll("-","");
        if (!file.isEmpty()) {//文件不为空
            try {
                //上传路径
                StringBuilder filePath = new StringBuilder(PRE_PATH).append("/").append(Filename).append(prefix);
                File saveDir = new File(String.valueOf(filePath));
                // 转存文件
                file.transferTo(saveDir);
                result.put("imagePath",Filename+prefix);
//              jsonObject.put("uri",uri);
                result.put("path", saveDir.getAbsolutePath());
                result.put("url",new StringBuilder("/pic/").append(Filename).append(prefix));
            } catch (IOException e) {
                e.printStackTrace();
                logger.error("上传失败");
                result.put("message","网络延迟，请重新提交");
            }
        }else {
        	logger.error("上传文件为空");
        }
        
        return result;
        
        
        

    }
	
	/**
	 * 图片路径入库
	 * @param provinceBean
	 * @return
	 */
	@RequestMapping(value = "/getPicPath", method = RequestMethod.POST)
	@ResponseBody
	public String getPicPath(PicturePath  picturePath ,String custId) {
		JSONObject object = new JSONObject();
		CustScan custScan = new CustScan();
		MerchantVo merchantVo = merchantMapper.newFindMerchantInfo(custId);
		PicturePath picturePathOld = merchantEnterService.getPicPath(merchantVo);
		
		custScan.setCustId(custId);
		custScan.setAuthId(merchantVo.getAuthId());
		custScan.setCreateId(merchantVo.getCreateId());
		
		try {
			//银行卡照
			if(!StringUtils.isEmpty(picturePath.getBankCardPath())) {
				custScan.setCertifyType("07");
				if(!StringUtils.isEmpty(picturePathOld.getBankCardPath())) {
					custScan.setStatus("01");
					custScanMapper.updateCustScan(custScan);
				}
				custScan.setStatus("00");
				custScan.setScanCopyPath(picturePath.getBankCardPath());
				custScanMapper.insertCustScan(custScan);
			}
			//身份证正面照
			if(!StringUtils.isEmpty(picturePath.getIdCardOPath())) {
				custScan.setCertifyType("04");
				
				if(!StringUtils.isEmpty(picturePathOld.getIdCardOPath())) {
					custScan.setStatus("01");
					custScanMapper.updateCustScan(custScan);
				}
				
				custScan.setStatus("00");
				custScan.setScanCopyPath(picturePath.getIdCardOPath());
				custScanMapper.insertCustScan(custScan);
			}
			//身份证反面照
			if(!StringUtils.isEmpty(picturePath.getIdCardFPath())) {
				custScan.setCertifyType("16");
				
				if(!StringUtils.isEmpty(picturePathOld.getIdCardFPath())) {
					custScan.setStatus("01");
					custScanMapper.updateCustScan(custScan);
				}
				
				custScan.setScanCopyPath(picturePath.getIdCardFPath());
				custScan.setStatus("00");
				custScanMapper.insertCustScan(custScan);
			}
			//营业执照
			if(!StringUtils.isEmpty(picturePath.getBussinessPath())) {
				custScan.setCertifyType("02");
				
				if(!StringUtils.isEmpty(picturePathOld.getBussinessPath())) {
					custScan.setStatus("01");
					custScanMapper.updateCustScan(custScan);
				}
				
				custScan.setScanCopyPath(picturePath.getBussinessPath());
				custScan.setStatus("00");
				custScanMapper.insertCustScan(custScan);
			}
			//开户许可证
			if(!StringUtils.isEmpty(picturePath.getOpenAccountPath())) {
				custScan.setCertifyType("03");
				
				if(!StringUtils.isEmpty(picturePathOld.getOpenAccountPath())) {
					custScan.setStatus("01");
					custScanMapper.updateCustScan(custScan);
				}
				
				custScan.setScanCopyPath(picturePath.getOpenAccountPath());
				custScan.setStatus("00");
				custScanMapper.insertCustScan(custScan);
			}
			//门头照
			if(!StringUtils.isEmpty(picturePath.getDoorPhotoPath())) {
				custScan.setCertifyType("08");
				
				if(!StringUtils.isEmpty(picturePathOld.getDoorPhotoPath())) {
					custScan.setStatus("01");
					custScanMapper.updateCustScan(custScan);
				}
				
				custScan.setScanCopyPath(picturePath.getDoorPhotoPath());
				custScan.setStatus("00");
				custScanMapper.insertCustScan(custScan);
			}
			//结算人身份证正面
			if(!StringUtils.isEmpty(picturePath.getSettleCertAttribute1Path())) {
				custScan.setCertifyType("30");
				
				if(!StringUtils.isEmpty(picturePathOld.getSettleCertAttribute1Path())) {
					custScan.setStatus("01");
					custScanMapper.updateCustScan(custScan);
				}
				
				custScan.setScanCopyPath(picturePath.getSettleCertAttribute1Path());
				custScan.setStatus("00");
				custScanMapper.insertCustScan(custScan);
			}
			//结算人身份证反面
			if(!StringUtils.isEmpty(picturePath.getSettleCertAttribute2Path())) {
				custScan.setCertifyType("31");
				
				if(!StringUtils.isEmpty(picturePathOld.getSettleCertAttribute2Path())) {
					custScan.setStatus("01");
					custScanMapper.updateCustScan(custScan);
				}
				
				custScan.setScanCopyPath(picturePath.getSettleCertAttribute2Path());
				custScan.setStatus("00");
				custScanMapper.insertCustScan(custScan);
			}
			//特殊资质照
			if(!StringUtils.isEmpty(picturePath.getQualificationPath())) {
				custScan.setCertifyType("11");
				
				if(!StringUtils.isEmpty(picturePathOld.getQualificationPath())) {
					custScan.setStatus("01");
					custScanMapper.updateCustScan(custScan);
				}
				
				custScan.setScanCopyPath(picturePath.getQualificationPath());
				custScan.setStatus("00");
				custScanMapper.insertCustScan(custScan);
			}
			//公众号页面截图
			if(!StringUtils.isEmpty(picturePath.getMpAppScreenShotsPath())) {
				custScan.setCertifyType("32");
				
				if(!StringUtils.isEmpty(picturePathOld.getMpAppScreenShotsPath())) {
					custScan.setStatus("01");
					custScanMapper.updateCustScan(custScan);
				}
				
				custScan.setScanCopyPath(picturePath.getMpAppScreenShotsPath());
				custScan.setStatus("00");
				custScanMapper.insertCustScan(custScan);
			}
			//小程序页面截图
			if(!StringUtils.isEmpty(picturePath.getMiniprogramAppidPath())) {
				custScan.setCertifyType("33");
				
				if(!StringUtils.isEmpty(picturePathOld.getMiniprogramAppidPath())) {
					custScan.setStatus("01");
					custScanMapper.updateCustScan(custScan);
				}
				
				custScan.setScanCopyPath(picturePath.getMiniprogramAppidPath());
				custScan.setStatus("00");
				custScanMapper.insertCustScan(custScan);
			}
			//APP截图
			if(!StringUtils.isEmpty(picturePath.getAppAppidPath())) {
				custScan.setCertifyType("34");
				
				if(!StringUtils.isEmpty(picturePathOld.getAppAppidPath())) {
					custScan.setStatus("01");
					custScanMapper.updateCustScan(custScan);
				}
				
				custScan.setScanCopyPath(picturePath.getAppAppidPath());
				custScan.setStatus("00");
				custScanMapper.insertCustScan(custScan);
			}
			//网站授权函
			if(!StringUtils.isEmpty(picturePath.getWebUrlPath())) {
				custScan.setCertifyType("35");
				
				if(!StringUtils.isEmpty(picturePathOld.getWebUrlPath())) {
					custScan.setStatus("01");
					custScanMapper.updateCustScan(custScan);
				}
				
				custScan.setScanCopyPath(picturePath.getWebUrlPath());
				custScan.setStatus("00");
				custScanMapper.insertCustScan(custScan);
			}
			//23其他资料照1
			if(!StringUtils.isEmpty(picturePath.getOtherMaterialPath())) {
				custScan.setCertifyType("23");
				
				if(!StringUtils.isEmpty(picturePathOld.getOtherMaterialPath())) {
					custScan.setStatus("01");
					custScanMapper.updateCustScan(custScan);
				}
				
				custScan.setScanCopyPath(picturePath.getOtherMaterialPath());
				custScan.setStatus("00");
				custScanMapper.insertCustScan(custScan);
			}
			//18店内照
			if(!StringUtils.isEmpty(picturePath.getShopInteriorPath())) {
				custScan.setCertifyType("18");
				
				if(!StringUtils.isEmpty(picturePathOld.getShopInteriorPath())) {
					custScan.setStatus("01");
					custScanMapper.updateCustScan(custScan);
				}
				
				custScan.setScanCopyPath(picturePath.getShopInteriorPath());
				custScan.setStatus("00");
				custScanMapper.insertCustScan(custScan);
			}
			//15 非法人结算授权函
			if(!StringUtils.isEmpty(picturePath.getLetterOfAuthPath())) {
				custScan.setCertifyType("15");
				
				if(!StringUtils.isEmpty(picturePathOld.getLetterOfAuthPath())) {
					custScan.setStatus("01");
					custScanMapper.updateCustScan(custScan);
				}
				
				custScan.setScanCopyPath(picturePath.getLetterOfAuthPath());
				custScan.setStatus("00");
				custScanMapper.insertCustScan(custScan);
			}
			//37经营场所证明文件
			if(!StringUtils.isEmpty(picturePath.getBusinessPlacePath())) {
				custScan.setCertifyType("37");
				
				if(!StringUtils.isEmpty(picturePathOld.getBusinessPlacePath())) {
					custScan.setStatus("01");
					custScanMapper.updateCustScan(custScan);
				}
				
				custScan.setScanCopyPath(picturePath.getBusinessPlacePath());
				custScan.setStatus("00");
				custScanMapper.insertCustScan(custScan);
			}
			//13手持身份证照
			if(!StringUtils.isEmpty(picturePath.getHandIdCardPath())) {
				custScan.setCertifyType("13");
				
				if(!StringUtils.isEmpty(picturePathOld.getHandIdCardPath())) {
					custScan.setStatus("01");
					custScanMapper.updateCustScan(custScan);
				}
				
				custScan.setScanCopyPath(picturePath.getHandIdCardPath());
				custScan.setStatus("00");
				custScanMapper.insertCustScan(custScan);
			}
			//21店内前台照
			if(!StringUtils.isEmpty(picturePath.getShopCheckStandPath())) {
				custScan.setCertifyType("21");
				
				if(!StringUtils.isEmpty(picturePathOld.getShopCheckStandPath())) {
					custScan.setStatus("01");
					custScanMapper.updateCustScan(custScan);
				}
				
				custScan.setScanCopyPath(picturePath.getShopCheckStandPath());
				custScan.setStatus("00");
				custScanMapper.insertCustScan(custScan);
			}
			object.put("result", "SUCCESS");
			object.put("message", "新增图片成功");
		} catch (Exception e) {
			object.put("result", "FAIL");
			object.put("message", "新增图片失败");
		}
		return object.toString();
		
	}
}
