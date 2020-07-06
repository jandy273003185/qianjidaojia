package com.qifenqian.bms.unionPay.unionpayfileupload;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.accounting.checkquery.type.ChannelId;
import com.qifenqian.bms.expresspay.jgkjfileupload.bean.FileOffer;
import com.qifenqian.bms.expresspay.jgkjfileupload.mapper.FileOfferMapper;

@Controller
@RequestMapping(UnionpayFilePath.BASE)
public class UnionpayFileController {

	private Logger logger = LoggerFactory.getLogger(UnionpayFileController.class);

	@Autowired
	private UnionpayFileService unionpayFileService;
	@Autowired
	private FileOfferMapper fileOfferMapper;

	@RequestMapping(UnionpayFilePath.LIST)
	public ModelAndView list(FileOffer queryBean) {
		// 返回视图
		ModelAndView mv = new ModelAndView(UnionpayFilePath.BASE + UnionpayFilePath.LIST);
		// 列表
		queryBean.setChannelId(ChannelId.UNIONPAY.name());
		List<FileOffer> balFileList = unionpayFileService.selectByBean(queryBean);
		mv.addObject("balFileList", JSONObject.toJSON(balFileList));
		mv.addObject("queryBean", queryBean);
		// 返回
		return mv;
	}

	/**
	 * 文件上传
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(UnionpayFilePath.FILEUPLOAD)
	@ResponseBody
	public String fileUpload(HttpServletRequest request, HttpServletResponse response) {

		logger.info("对账/清算文件上传");
		JSONObject object = new JSONObject();
		String result = unionpayFileService.fileUpload(request, object);

		return result;
	}

	/**
	 * 文件删除
	 * 
	 * @param deleteBean
	 * @return
	 */
	@RequestMapping(UnionpayFilePath.DELETE)
	@ResponseBody
	public String delete(FileOffer deleteBean) {

		logger.info("银联对账/清算文件删除" + deleteBean.getFileId());
		JSONObject object = new JSONObject();
		FileOffer fileOffer = fileOfferMapper.selectByFileId(deleteBean.getFileId());
		logger.info("银联对账/清算文件 {}" + JSONObject.toJSONString(fileOffer, true));
		if(fileOffer!=null){
			if (unionpayFileService.delete(fileOffer)) {
				fileOfferMapper.deleteByFileId(deleteBean.getFileId());
				object.put("result", "SUCCESS");
			}else{
				object.put("result", "FAILE");
				object.put("message", "删除失败");
			}
		}
		return object.toJSONString();
	}

}
