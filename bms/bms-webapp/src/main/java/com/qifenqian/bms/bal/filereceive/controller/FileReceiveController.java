package com.qifenqian.bms.bal.filereceive.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.bal.accountResult.bean.BaseChannel;
import com.qifenqian.bms.bal.accountResult.service.BatchResultStatisticService;
import com.qifenqian.bms.bal.filereceive.bean.FileReceive;
import com.qifenqian.bms.bal.filereceive.service.FileReceiveService;
import com.qifenqian.bms.merchant.channel.bean.AgentMerInfo;
import com.qifenqian.bms.merchant.reported.bean.ChannlInfo;
import com.qifenqian.bms.merchant.reported.service.CrIncomeService;
import com.seven.micropay.base.domain.ChannelResult;
import com.seven.micropay.channel.domain.weixin.CheckReq;
import com.seven.micropay.channel.enums.CheckType;
import com.seven.micropay.channel.enums.PaychannelType;
import com.seven.micropay.channel.service.api.ICombineApiService;
import com.seven.micropay.commons.util.DateUtil;

@Controller
@RequestMapping(FileReceivePath.BASE)
public class FileReceiveController {

	private static Logger logger = LoggerFactory.getLogger(FileReceiveController.class);

	@Autowired
	private FileReceiveService fileReceiveService;

	@Autowired
	private BatchResultStatisticService batchResultStatisticService;

	@Autowired
	private ICombineApiService checkService;

	@Autowired
	private CrIncomeService crIncomeService;

	/**
	 * 对账文件列表
	 * 
	 * @param queryBean
	 * @return
	 */
	@RequestMapping(FileReceivePath.LIST)
	public ModelAndView fileList(FileReceive queryBean, BaseChannel channelId) {

		logger.info("对账文件列表对象{}" + JSONObject.toJSONString(queryBean, true));

		ModelAndView mv = new ModelAndView(FileReceivePath.BASE + FileReceivePath.LIST);
		List<FileReceive> fileReceiveList = fileReceiveService.selectFileReceiveList(queryBean);

		List<BaseChannel> baseChannels = batchResultStatisticService.queryBalBaseChannel();
		mv.addObject("fileReceiveList", JSONObject.toJSON(fileReceiveList));
		mv.addObject("queryBean", queryBean);
		mv.addObject("baseChannels", baseChannels);
		return mv;
	}

	/**
	 * 文件上传
	 */
	@RequestMapping(FileReceivePath.FILEUPLOAD)
	@ResponseBody
	public String fileUpload(HttpServletRequest request, HttpServletResponse response) {

		logger.info("对账/清算文件上传");
		JSONObject object = new JSONObject();

		String result = fileReceiveService.fileUpload(request, object);

		return result;

	}

	@RequestMapping(FileReceivePath.DOWNPAGE)
	public ModelAndView paymentPage(HttpServletRequest request) {
		List<ChannlInfo> infoList = crIncomeService.getChannlInfoList();
		ModelAndView mv = new ModelAndView(FileReceivePath.BASE + FileReceivePath.DOWNPAGE);
		mv.addObject("channlInfoList", infoList);
		return mv;
	}

	@RequestMapping(FileReceivePath.DOWNFILE)
	@ResponseBody
	public String downFile(HttpServletRequest request, HttpServletResponse response) {
		logger.debug("开始下载对账文件");
		JSONObject object = new JSONObject();
		try {
			// 默认是前一天
			String fileDate = DateFormatUtils.format(DateUtils.addDays(new Date(), -1), "yyyyMMdd");
			String merchantCode = request.getParameter("merchantCode");
			String channlCode = request.getParameter("channlCode");

			/** 若有传则取传来的日期，则使用 **/
			if (StringUtils.isNotBlank(request.getParameter("workDate"))) {
				fileDate = request.getParameter("workDate");
				if (!fileDate.matches("\\d{8}")) {
					throw new IllegalArgumentException("下载文件名日期格式非法,应该为{yyyyMMdd}");
				}
			}
			CheckReq checkReq = new CheckReq();
			PaychannelType paychannelType = PaychannelType.valueOf(channlCode);
			checkReq.setPaychannelType(paychannelType);
			/*
			 * if("iCr".equals(channlCode)){//华润
			 * checkReq.setPaychannelType(PaychannelType.CR_ULOPAY); }else
			 * if("helipay".equals(channlCode)){//合利宝
			 * checkReq.setPaychannelType(PaychannelType.HELIPAY); }else
			 * if("kftPay".equals(channlCode)){//快付通
			 * checkReq.setPaychannelType(PaychannelType.KFT_PAY); }else
			 * if("fmPay".equals(channlCode)){
			 * checkReq.setPaychannelType(PaychannelType.FM_COMBINEDPAY); }
			 */

			checkReq.setDate(DateUtil.parse(fileDate, DateUtil.YYYYMMDD));
			checkReq.setCheckType(CheckType.AUTO);
			checkReq.setMerNo(merchantCode);

			long startT = System.currentTimeMillis();

			ChannelResult result = checkService.check(checkReq);
			long endT = System.currentTimeMillis();
			logger.info(" bal Check cost time:{}", (endT - startT));
			

			// String fileDir = p.getProperty("FM_PROD_CHECK_FILE") + File.separator +
			// fileDate;
			String fileDir = result.getData().get("filePath").toString();
			try {

				// 创建临时压缩文件
				String zipName = channlCode + "_" + fileDate + ".zip";
				// String filePath =
				// p.getProperty("FM_PROD_CHECK_FILE")+File.separator+fileDate+File.separator+"FM_"+fileDate+".zip";
				String filePath = fileDir + zipName;
				File proFile = new File(filePath);
				if (!new File(fileDir).exists()) {
					response.reset();
					response.setCharacterEncoding("utf-8");
					response.setContentType("text/html; charset=UTF-8");
					response.getWriter().print(fileDate + "日对账文件不存在，请稍候下载");
					object.put("result", "FAIL");
					object.put("message", "日对账文件不存在，请稍候下载");
					return null;
				}
				// 创建临时压缩文件
				BufferedOutputStream bos = null;
				ZipOutputStream zos = null;
				try {
					File dowFile = new File(fileDir);
					File fa[] = dowFile.listFiles();
					bos = new BufferedOutputStream(new FileOutputStream(proFile));
					zos = new ZipOutputStream(bos);
					ZipEntry ze = null;
					for (int i = 0; i < fa.length; i++) {// 将所有需要下载的文件都写入临时zip文件
						BufferedInputStream bis = new BufferedInputStream(new FileInputStream(fa[i]));
						ze = new ZipEntry(fa[i].getName());
						zos.putNextEntry(ze);
						int s = -1;
						while ((s = bis.read()) != -1) {
							zos.write(s);
						}
						bis.close();
					}
					zos.flush();
					zos.close();
				} catch (IOException e) {
					e.printStackTrace();
					object.put("result", "FAIL");
					object.put("message", "对账文件下载失败，请稍候下载");
					return object.toJSONString();
				} finally {
					bos.close();
					zos.close();
				}

				// 以上，临时压缩文件创建完成

				// 进行浏览器下载
				// 获得浏览器代理信息
				final String userAgent = request.getHeader("USER-AGENT");
				// 判断浏览器代理并分别设置响应给浏览器的编码格式
				String finalFileName = null;
				if (StringUtils.contains(userAgent, "MSIE") || StringUtils.contains(userAgent, "Trident")) {// IE浏览器
					finalFileName = URLEncoder.encode(zipName, "UTF8");
					System.out.println("IE浏览器");
				} else if (StringUtils.contains(userAgent, "Mozilla")) {// google,火狐浏览器
					finalFileName = new String(zipName.getBytes(), "ISO8859-1");
				} else {
					finalFileName = URLEncoder.encode(zipName, "UTF8");// 其他浏览器
				}
				response.setContentType("application/x-download");// 告知浏览器下载文件，而不是直接打开，浏览器默认为打开
				response.setHeader("Content-Disposition", "attachment;filename=\"" + finalFileName + "\"");// 下载文件的名称

				ServletOutputStream servletOutputStream = response.getOutputStream();
				DataOutputStream temps = new DataOutputStream(servletOutputStream);

				DataInputStream in = new DataInputStream(new FileInputStream(filePath));// 浏览器下载文件的路径
				byte[] b = new byte[2048];
				File reportZip = new File(filePath);// 之后用来删除临时压缩文件
				try {
					while ((in.read(b)) != -1) {
						temps.write(b);
					}
					temps.flush();
				} catch (Exception e) {
					logger.error("文件读取输出异常", e);
					object.put("result", "FAIL");
					object.put("message", "文件读取输出异常，请联系管理员");
					return object.toJSONString();
				} finally {
					if (temps != null)
						temps.close();
					if (in != null)
						in.close();
					if (reportZip != null)
						reportZip.delete();// 删除服务器本地产生的临时压缩文件
					servletOutputStream.close();
				}
			} catch (FileNotFoundException e) {
				logger.error("获取文件异常", e);
				object.put("result", "FAIL");
				object.put("message", "获取文件异常，请联系管理员");
				return object.toJSONString();
			}
		} catch (Exception e) {
			logger.error("文件下载异常", e);
			object.put("result", "FAIL");
			object.put("message", "文件下载异常，请联系管理员");
			return object.toJSONString();
		}
		logger.error("文件下载完成");
		object.put("result", "SUCCESS");
		object.put("message", "文件下载成功");
		return object.toString();
	}

	/**
	 * 查询商户报备信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(FileReceivePath.QUERYMECHANTINFO)
	@ResponseBody
	public String queryMerchantCategoryInfo(HttpServletRequest request) {
		String merchantId = request.getParameter("merchantId");
		logger.debug("查询商户报备信息[{}]", merchantId);
		JSONObject object = new JSONObject();
		try {
			if (!"".equals(merchantId)) {
				List<AgentMerInfo> info = fileReceiveService.getMechantTypeInfo(merchantId);
				if (info != null && info.size() > 0) {
					object.put("result", "SUCCESS");
					object.put("info", info);
				} else {
					object.put("result", "FAIL");
				}
			} else {
				return null;
			}

		} catch (Exception e) {
			logger.error("查询商户报备信息异常", e);
			object.put("result", "FAIL");
		}
		return object.toJSONString();
	}

}
