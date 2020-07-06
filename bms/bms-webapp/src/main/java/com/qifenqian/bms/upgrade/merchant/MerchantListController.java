package com.qifenqian.bms.upgrade.merchant;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
import com.qifenqian.bms.basemanager.merchant.MerchantPath;
import com.qifenqian.bms.basemanager.merchant.auding.bean.TbBankCode;
import com.qifenqian.bms.basemanager.merchant.bean.MerchantVo;
import com.qifenqian.bms.basemanager.trade.service.DownLoadUtil;
import com.qifenqian.bms.basemanager.trade.service.TradeBillService;
import com.qifenqian.bms.basemanager.utils.DatetimeUtils;
import com.qifenqian.bms.basemanager.utils.GenSN;
import com.qifenqian.bms.basemanager.utils.YouTuUtils;
import com.qifenqian.bms.platform.web.admin.utils.WebUtils;
import com.qifenqian.bms.upgrade.merchant.bean.MerchantAuditExport;
import com.qifenqian.bms.upgrade.merchant.bean.MerchantRegisterInfo;
import com.qifenqian.bms.upgrade.merchant.bean.TdAuditRecodeInfo;
import com.qifenqian.bms.upgrade.merchant.service.MerchantListService;

@Controller
@RequestMapping(MerchantListPath.BASE)
public class MerchantListController {
  @Autowired private MerchantListService merchantListService;

  @Autowired private TradeBillService tradeBillService;

  private Logger logger = LoggerFactory.getLogger(MerchantListController.class);

  /**
   * 获取代理商商户列表
   *
   * @param queryBean
   * @return
   */
  @RequestMapping(MerchantListPath.LIST)
  public ModelAndView merchantList(MerchantVo queryBean) {
    logger.info("查询代理商商户列表信息:{}", JSONObject.toJSONString(queryBean, true));
    ModelAndView mv = new ModelAndView(MerchantListPath.BASE + MerchantListPath.LIST);
    List<MerchantVo> list = merchantListService.listMerchant(queryBean);
    mv.addObject("merchantList", JSONObject.toJSON(list));
    mv.addObject("queryBean", queryBean);
    return mv;
  }

  /**
   * 获取商户信息
   *
   * @param merchentCode 商户编号
   * @return
   */
  /*
  @RequestMapping(MerchantListPath.PREVIEW)
  @ResponseBody
  public String queryMerchantInfo(String merchantCode) {
  	JSONObject json = new JSONObject();
  	logger.info("获取商户编号为："+merchantCode+"的商户信息");
  	try {
  		List<MerchantRegisterInfo> list = merchantListService.queryMerchantInfo(merchantCode);
  		MerchantRegisterInfo merchantInfo = null;
  		merchantInfo = list.get(0);
  		if(merchantInfo!=null) {
  			splitIdCardPath(merchantInfo);
  			String idCardstartTime = null;
  			String idCardendtime = null;

  			YouTuUtils util = new YouTuUtils();
  			Date date = util.StringFormatDate(new Date(), merchantInfo.getCertifyBeginTime());
  			idCardstartTime = util.DateFormatString(date,new String());

  			if("idforever".equals(merchantInfo.getCertifyEndTime())) {
  				idCardendtime = "长期有效";
  			}else {
  				Date date2 = util.StringFormatDate(new Date(), merchantInfo.getCertifyEndTime());
  				idCardendtime = util.DateFormatString(date2,new String());
  			}
  			json.put("merchantInfo", merchantInfo);
  			json.put("idCardstartTime", idCardstartTime);
  			json.put("idCardendtime", idCardendtime);
  			json.put("result", "SUCCESS");

  		}else {
  			json.put("reslut","NULL");
  		}
  	}catch(Exception e) {
  		json.put("reslut","EXCEPTION");
  		logger.error("获取商户编号为："+merchantCode+"的商户信息出现异常,异常信息为:",e.toString());
  	}
  	return json.toString();
  }*/

  /**
   * 获取代理商商户信息
   *
   * @param merchentCode 商户编号
   * @return
   */
  @RequestMapping(MerchantListPath.PREVIEW)
  public ModelAndView queryMerchantInfo(String merchantCode) {
    ModelAndView json = new ModelAndView(MerchantListPath.BASE + MerchantListPath.PREVIEW);
    logger.info("获取商户编号为：" + merchantCode + "的商户信息");
    try {
      List<MerchantRegisterInfo> list = merchantListService.queryMerchantInfo(merchantCode);
      MerchantRegisterInfo merchantInfo = null;
      merchantInfo = list.get(0);
      if (merchantInfo != null) {
        splitIdCardPath(merchantInfo);
        String idCardstartTime = null;
        String idCardendtime = null;

        YouTuUtils util = new YouTuUtils();
        Date date = util.StringFormatDate(new Date(), merchantInfo.getCertifyBeginTime());
        idCardstartTime = util.DateFormatString(date, new String());

        if ("idforever".equals(merchantInfo.getCertifyEndTime())) {
          idCardendtime = "长期有效";
        } else {
          Date date2 = util.StringFormatDate(new Date(), merchantInfo.getCertifyEndTime());
          idCardendtime = util.DateFormatString(date2, new String());
        }
        TbBankCode bank = merchantListService.selBankByBankCode(merchantInfo.getCompAcctBank());

        if (null != bank) {
          json.addObject("bankName", bank.getBankName());
        }
        json.addObject("merchantInfo", merchantInfo);
        json.addObject("idCardstartTime", idCardstartTime);
        json.addObject("idCardendtime", idCardendtime);
        String state = merchantListService.selectStateOfMerchant(merchantCode);
        String method = null;
        if ("01".equals(state)) {
          method = "audit";
        }
        json.addObject("method", method);

      } else {
        json.addObject("reslut", "NULL");
      }
    } catch (Exception e) {
      json.addObject("reslut", "EXCEPTION");
      logger.error("获取商户编号为：" + merchantCode + "的商户信息出现异常,异常信息为:", e.toString());
    }
    return json;
  }

  /**
   * 导出代理商商户审核列表信息
   *
   * @param requestBean
   * @param request
   * @param response
   */
  @RequestMapping(MerchantPath.EXPORTMERCHANTINFO)
  public void exportExcel(
      MerchantVo merchantVo, HttpServletRequest request, HttpServletResponse response) {
    logger.info("导出代理商商户审核列表信息");

    try {
      List<MerchantAuditExport> excels = merchantListService.exportlist(merchantVo);

      String[] headers = {
        "商户编号", "邮箱账号", "手机账号", "商户名称", "注册时间", "银行开户名", "商户状态", "审核状态", "审核信息", "审核人"
      };
      String fileName = DatetimeUtils.dateSecond() + "_代理商商户审核列表信息.xls";
      Map<String, String> fileInfo =
          tradeBillService.exportExcel(excels, headers, fileName, "代理商商户审核列表", request);
      DownLoadUtil.downLoadFile(
          fileInfo.get("filePath"), response, fileInfo.get("fileName"), "xls");
      logger.info("导出excel代理商商户审核列表成功");
    } catch (Exception e) {
      logger.error("导出excel代理商商户审核列表异常", e);
      throw new RuntimeException(e);
    }
  }
  /*public void exportExcel(MerchantVo merchantVo, HttpServletRequest request, HttpServletResponse response) {
  	logger.info("导出商户列表信息");

  	try {
  		List<MerchantExport> excels = merchantListService.exportlist(merchantVo);

  		String[] headers = { "商户编号", "商户名称", "交易密码", "附加串", "错误密码次数",
  		"客户类型：0 个人1 企业", "商户状态", "账号", "商户标志：0 商户，1 非商户", "客户积分", "客户等级", "实名认证等级", "实名认证审核状态", "客户信息完整度，分几级：0、1",
  				"地址", "邮编", "营业执照编号（企业专用）", "税务登记证号（企业专用）", "法人证件类型（企业专用）", "法人证件号码（企业专用）", "法人名称（企业专用）",
  				"注册资本（企业专用）", "企业类型", "所属行业", "年营业额", "商户网站地址", "客户状态", "是否证书认证", "是否短信认证", "七分钱账户ID", "创建人",
  				"创建时间", "修改人", "修改时间", "营业期限", "营业执照注册所在地", "企业联系电话", "来往单位编号", "组织机构代码", "法人代表归属地", "法人手机",
  				"代理人真实姓名", "代理人身份证类型", "代理人身份证号码", "代理人手机号码", "公司对公账号", "公司对公账号所属行", "支付密码冻结时间", "公司汇款校验金额",
  				"支行信息", "银行开户名", "备注" };
  		String fileName = DatetimeUtils.dateSecond() + "_商户列表信息.xls";
  		Map<String, String> fileInfo = tradeBillService.exportExcel(excels, headers, fileName, "消费者列表", request);
  		DownLoadUtil.downLoadFile(fileInfo.get("filePath"), response, fileInfo.get("fileName"), "xls");
  		logger.info("导出excel商户列表成功");
  	} catch (Exception e) {
  		logger.error("导出excel商户列表异常", e);
  		throw new RuntimeException(e);
  	}
  }*/
  /**
   * 审核
   *
   * @param merchantId 商户编号
   * @param result 审核结果
   * @param auditInfo 审核信息
   * @return
   */
  @RequestMapping(MerchantListPath.AUDIT)
  @ResponseBody
  public String auditResult(String merchantId, String result, String auditInfo) {
    logger.info("开始写入商户编号为" + merchantId + "的审核结果：" + result + ",审核信息：" + auditInfo);
    JSONObject json = new JSONObject();

    TdAuditRecodeInfo auditResult = new TdAuditRecodeInfo();
    // 设置审核者的id
    auditResult.setUserId(String.valueOf(String.valueOf(WebUtils.getUserInfo().getUserId())));
    // 设置审核信息
    auditResult.setAuditInfo(auditInfo);
    auditResult.setAuditTime(new Date());
    // 将审核类型01代表注册
    auditResult.setAuditType("01");
    // 将id设置32位的UUID
    auditResult.setId(GenSN.getSN());
    auditResult.setMerchantCode(merchantId);
    if ("noPass".equals(result)) {
      auditResult.setStatus("99");
    } else if ("pass".equals(result)) {
      auditResult.setStatus("00");
    }

    try {
      String updateResult =
          merchantListService.updateResultOfAudit(result, merchantId, auditResult);
      if ("success".equals(updateResult)) {
        json.put("result", updateResult);
      }

    } catch (Exception e) {
      json.put("result", "false");
      json.put("message", e.toString());
      logger.error(
          "写入商户编号为"
              + merchantId
              + "的审核结果："
              + result
              + ",审核信息："
              + auditInfo
              + "发生异常,异常信息为:"
              + e.toString());
    }

    return json.toString();
  }
  // 写文件流到<img>
  @RequestMapping(MerchantListPath.SHOWIMAGE)
  public void showImageByType(String src, HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    InputStream inputStream = null;
    OutputStream writer = null;
    try {
      if (!"".equals(src) && src != null) {
        inputStream = new FileInputStream(new File(src));
        writer = response.getOutputStream();

        byte[] buf = new byte[1024];
        int len = 0;
        while ((len = inputStream.read(buf)) != -1) {
          writer.write(buf, 0, len); // 写
        }
        inputStream.close();
      }
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
    } finally {
      try {
        if (inputStream != null) {
          inputStream.close();
        }
        if (writer != null) {
          writer.close();
        }
      } catch (IOException e) {
        logger.error(e.getMessage(), e);
      }
    }
  }

  private void splitIdCardPath(MerchantRegisterInfo mer) {
    //	/data/nfsshare/upload/certify\04\49739bb10c2147b4a94b75360d4dee80\idCardF.png;/data/nfsshare/upload/certify\04\49739bb10c2147b4a94b75360d4dee80\idCardO.png

    if (null != mer.getIdCardFPath() && !("".equals(mer.getIdCardFPath()))) {
      String path = mer.getIdCardFPath();
      String paths[] = path.split(";");
      for (int i = 0; i < paths.length; i++) {
        if (paths[i].indexOf("idCardF") > -1) {
          mer.setIdCardFPath(paths[i]);
        }
        if (paths[i].indexOf("idCardO") > -1) {
          mer.setIdCardOPath(paths[i]);
        }
      }
    }
  }
}
