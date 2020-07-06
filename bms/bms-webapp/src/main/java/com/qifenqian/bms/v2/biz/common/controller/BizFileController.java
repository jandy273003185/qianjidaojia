package com.qifenqian.bms.v2.biz.common.controller;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.basemanager.merchant.bean.CustScan;
import com.qifenqian.bms.basemanager.merchant.bean.MerchantVo;
import com.qifenqian.bms.basemanager.merchant.bean.PicturePath;
import com.qifenqian.bms.basemanager.merchant.mapper.CustScanMapper;
import com.qifenqian.bms.basemanager.merchant.mapper.MerchantMapper;
import com.qifenqian.bms.basemanager.merchant.service.MerchantEnterService;
import com.qifenqian.bms.platform.common.utils.DateUtils;
import com.qifenqian.bms.v2.common.mvc.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@Api(tags = "公共接口管理")
public class BizFileController extends BaseController {

	@Autowired
	private CustScanMapper custScanMapper;
	@Autowired
	private MerchantMapper merchantMapper;
	@Autowired
	private MerchantEnterService merchantEnterService;
	
//	private static final String PRE_PATH = "/data/nfsshare/upload/picture";
	private static final Logger logger = LoggerFactory.getLogger(BizFileController.class);
	
	
    @Value("${images.relativePaths}")
    private String relativePaths;
    //存储路径
    @Value("${images.absolutePaths}")
    private String absolutePaths;
	
	

	@PostMapping(value = "/common/files/upload")
	@ApiOperation(value = "上传文件")
	public Map<String, Object> upload(@RequestBody MultipartFile file) {
		return fileUpload(file);
	}

	/**
	 * 文件下载， 只支持图片类型的文件
	 * 
	 * @param path
	 * @param httpResponse
	 * @throws IOException
	 */
	@PostMapping(value = "/common/files/get")
	@ApiOperation(value = "文件下载")
	public void get(@RequestBody String path, HttpServletResponse httpResponse) throws IOException {

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
	

	@PostMapping(value = "/common/files/uploadPic")
	@ApiOperation(value = "上传文件")
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
                StringBuilder filePath = new StringBuilder(absolutePaths).append("/").append(Filename).append(prefix);
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
	@PostMapping(value = "/common/files/getPicPath")
	@ApiOperation(value = "图片路径入库")
	public String getPicPath(@RequestBody PicturePath  picturePath , String custId) {
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
