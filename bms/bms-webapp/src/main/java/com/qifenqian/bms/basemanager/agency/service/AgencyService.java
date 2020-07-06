package com.qifenqian.bms.basemanager.agency.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.basemanager.Constant;
import com.qifenqian.bms.basemanager.acctsevenbuss.bean.AcctSevenBuss;
import com.qifenqian.bms.basemanager.acctsevenbuss.mapper.AcctSevenBussMapper;
import com.qifenqian.bms.basemanager.agency.bean.AgenReport;
import com.qifenqian.bms.basemanager.agency.bean.AgencyExport;
import com.qifenqian.bms.basemanager.agency.bean.AgentMerchantReport;
import com.qifenqian.bms.basemanager.agency.dao.AgencyDao;
import com.qifenqian.bms.basemanager.agency.mapper.AgencyMapper;
import com.qifenqian.bms.basemanager.custInfo.bean.TdCustInfo;
import com.qifenqian.bms.basemanager.custInfo.mapper.TdCustInfoMapper;
import com.qifenqian.bms.basemanager.merchant.bean.CustScan;
import com.qifenqian.bms.basemanager.merchant.bean.MerchantVo;
import com.qifenqian.bms.basemanager.merchant.mapper.CustScanMapper;
import com.qifenqian.bms.platform.web.admin.utils.WebUtils;

@Service
public class AgencyService {

  @Autowired private AgencyDao agencyDao;
  @Autowired private AgencyMapper agencyMapper;
  @Autowired private CustScanMapper custScanMapper;
  @Autowired private TdCustInfoMapper tdCustInfoMapper;
  @Autowired private AcctSevenBussMapper acctSevenBussMapper;

  @Value("${CF_FILE_SAVE_PATH}")
  private String CF_FILE_SAVE_PATH;

  private static final Logger logger = LoggerFactory.getLogger(AgencyService.class);

  /**
   * 代理商报表
   *
   * @param bean
   * @return
   */
  public List<AgenReport> getAgenReportList(AgenReport bean) {
    return agencyDao.getAgenReportList(bean);
  }

  /**
   * 代理商商户报表
   *
   * @param bean
   * @return
   */
  public List<AgentMerchantReport> getAgentMerchantReport(AgentMerchantReport bean) {
    return agencyDao.getAgentMerchantReport(bean);
  }

  /**
   * 导出代理商报表
   *
   * @param bean
   * @return
   */
  public List<AgenReport> exportAgenReportList(AgenReport bean) {
    return agencyDao.exportAgenReportList(bean);
  }
  /** 代理商列表查询 * */
  public List<MerchantVo> selectAgencys(MerchantVo merchantVo) {
    return agencyDao.agencyList(merchantVo);
  }

  /** 自己权限代理商列表查询 * */
  public List<MerchantVo> myAgencyList(MerchantVo merchantVo) {
    return agencyDao.myAgencyList(merchantVo);
  }

  /** 导出代理商信息 */
  public List<AgencyExport> exportAgencysInfo(MerchantVo merchantVo) {
    return agencyMapper.exportAgencysList(merchantVo);
  }

  /** 修改代理商信息，带事务 * */
  public void updateAgencyInfo(MerchantVo merchantVo, Map<String, String> filePath) {
    if (null == merchantVo) {
      throw new IllegalArgumentException("代理商对象为空");
    }
    try {
      /** 修改代理商信息 * */
      agencyMapper.updateMerchant(merchantVo);

      TdCustInfo custInfo = new TdCustInfo();
      custInfo.setCustId(merchantVo.getCustId());
      custInfo.setMobile(merchantVo.getMobile());
      tdCustInfoMapper.updateCustLoginInfo(custInfo);
      /** 修改客户扫描件审核表 * */
      updateCustScanInfo(merchantVo.getCustId(), merchantVo, filePath);
      /** 修改核心账户表 */
      AcctSevenBuss acctSevenBuss = new AcctSevenBuss();
      acctSevenBuss.setCustId(merchantVo.getCustId());
      acctSevenBuss.setAcctName(merchantVo.getCustName());
      acctSevenBussMapper.updateAcctSevenBussByCustId(acctSevenBuss);
    } catch (Exception e) {
      logger.error("修改异常", e);
      throw e;
    }
  }

  /** 修改客户扫描件审核表 * */
  private void updateCustScanInfo(
      String custId, MerchantVo merchant, Map<String, String> fileNames) {
    if (StringUtils.isEmpty(custId)) {
      throw new IllegalArgumentException("商户ID为空");
    }
    logger.info("修改商户证件信息[{}]", JSONObject.toJSONString(merchant));
    String businessType = fileNames.get("businessType");
    String idCardType_1 = fileNames.get("idCardType_1");
    String idCardType_2 = fileNames.get("idCardType_2");
    String settlEmentCard = fileNames.get("settlEmentCard");

    try {
      /** 更新营业执照扫描件 * */
      /** 由于营业执照注册号和营业执照扫描件不是必须提交的，所以这里做一次查询，判断 * */
      CustScan custScan = null;
      custScan = new CustScan();
      custScan.setCertifyType(Constant.CERTIFY_TYPE_BUSINESS);
      custScan.setCustId(merchant.getCustId());
      int count = custScanMapper.findCountCustScanInfo(custScan);
      if (count > 0) {
        if (!StringUtils.isBlank(businessType)) {
          this.updateScanPath( //
              custId,
              Constant.CERTIFY_TYPE_BUSINESS, //
              CF_FILE_SAVE_PATH
                  + File.separator
                  + Constant.CERTIFY_TYPE_BUSINESS
                  + File.separator
                  + custId
                  + File.separator
                  + businessType, //
              merchant.getCustName(),
              merchant.getBusinessLicense());
        }
      } else {
        if (!StringUtils.isBlank(businessType)) {
          this.insertScanPath(
              custId,
              Constant.CERTIFY_TYPE_BUSINESS, //
              CF_FILE_SAVE_PATH
                  + File.separator
                  + Constant.CERTIFY_TYPE_BUSINESS
                  + File.separator
                  + custId
                  + File.separator
                  + businessType, //
              merchant.getCustName(),
              merchant.getBusinessLicense());
        }
      }
      /** 更新身份证扫描件 * */
      custScan = new CustScan();
      custScan.setCustId(custId);
      custScan.setCertifyType(Constant.CERTIFY_TYPE_MERCHANT_IDCARD); // 扫描件类型  04-商户身份信息
      String idcardPath = custScanMapper.findPathByIdAndType(custScan); // 根据客户编号和扫描件类型查找
      String path[] = idcardPath.split(";");
      String idCard_1 = path[0];
      if (!StringUtils.isBlank(idCardType_1)) {
        idCard_1 =
            CF_FILE_SAVE_PATH
                + File.separator
                + Constant.CERTIFY_TYPE_MERCHANT_IDCARD
                + File.separator
                + custId
                + File.separator
                + idCardType_1;
      }
      String idCard_2 = path[1];
      if (!StringUtils.isBlank(idCardType_2)) {
        idCard_2 =
            CF_FILE_SAVE_PATH
                + File.separator
                + Constant.CERTIFY_TYPE_MERCHANT_IDCARD
                + File.separator
                + custId
                + File.separator
                + idCardType_2;
      }
      idcardPath = idCard_1 + ";" + idCard_2; // 拼接身份证号图片路径
      this.updateScanPath(
          custId,
          Constant.CERTIFY_TYPE_MERCHANT_IDCARD,
          idcardPath,
          merchant.getCustName(),
          merchant.getCertifyNo());
      // 更新结算银行卡照片
      if (!StringUtils.isBlank(settlEmentCard)) {
        this.updateScanPath(
            custId,
            Constant.CERTIFY_TYPE_MERCHANT_SETTLEMENT,
            CF_FILE_SAVE_PATH
                + File.separator
                + Constant.CERTIFY_TYPE_MERCHANT_SETTLEMENT
                + File.separator
                + custId
                + File.separator
                + settlEmentCard,
            merchant.getCustName(),
            null);
      }

    } catch (Exception e) {
      logger.error("更新证件信息异常", e);
      throw e;
    }
  }

  /** 修改扫描件信息 * */
  private void updateScanPath(
      String custId, String certifyType, String path, String custName, String certifyNo) {
    CustScan custScan = new CustScan();
    custScan.setCustId(custId); // 客户编号
    custScan.setCertifyType(certifyType); // 扫描件类型
    custScan.setScanCopyPath(path); // 扫描件路径
    custScan.setModifyId(String.valueOf(WebUtils.getUserInfo().getUserId())); // 	修改人
    custScan.setCertifyNo(certifyNo); // 身份证号号码/证件号码
    custScan.setCustName(custName); // 客户名称
    custScanMapper.updateCustScan(custScan);
  }

  /** 插入证件/身份证号路径 * */
  private void insertScanPath(
      String custId, String certifyType, String path, String custName, String certifyNo) {
    /** TD_CUST_INFO * */
    TdCustInfo tdCustInfo = tdCustInfoMapper.selectById(custId);
    CustScan custScan = new CustScan();
    custScan.setCustId(custId); // 客户编号
    custScan.setAuthId(tdCustInfo.getAuthId()); // 证件审核ID
    custScan.setCertifyType(certifyType); // 扫描件类型
    custScan.setScanCopyPath(path); // 扫描件路径
    custScan.setCreateId(String.valueOf(WebUtils.getUserInfo().getUserId())); // 	修改人
    custScan.setCertifyNo(certifyNo); // 身份证号码
    custScan.setCustName(custName); // 客户名称
    custScanMapper.insertCustScan(custScan);
  }

  /** 代理商文件上传 * */
  public Map<String, String> agencyFileUpload(HttpServletRequest request, String custId) {
    logger.info("*********代理商文件上传开始*********");
    Map<String, String> result = new HashMap<String, String>();
    try {
      DiskFileItemFactory factory = new DiskFileItemFactory(); // 创建一个DiskFileItemFactory工厂
      ServletFileUpload upload = new ServletFileUpload(factory); // 创建一个文件上传解析器
      upload.setHeaderEncoding("UTF-8"); // 解决上传文件名的中文乱码
      List<FileItem> list = upload.parseRequest(request);
      InputStream inputStream = null;
      HashMap<String, String> nameType = new HashMap<String, String>();
      for (FileItem item : list) {
        String filename = null; //
        if (!item.isFormField()) { // 是否是表单项
          filename = item.getName();
          String filedName = item.getFieldName();
          if (StringUtils.isEmpty(filename)) {
            continue;
          }
          /** 验证图片后缀名 * */
          String type = filename.substring(filename.lastIndexOf("."));
          String[] photoTypes = {".jpg", ".jpeg", ".gif", ".bmp", ".png"};
          boolean isType = false;
          for (int i = 0; i < photoTypes.length; i++) {
            if (photoTypes[i].equalsIgnoreCase(type)) {
              isType = true;
              break;
            }
          }
          if (!isType) {
            result.put("result", "FAIL");
            result.put("message", "文件类型不匹配");
            return result;
          }
          inputStream = item.getInputStream();
          String fileUploadPath = CF_FILE_SAVE_PATH;
          /** 根据文件名生成路径规则 * */
          switch (filedName) {
            case "businessPhoto":
              filename = "attach1" + type;
              /** /data/nfsshare/upload/certificate/02/custId * */
              fileUploadPath =
                  fileUploadPath
                      + File.separator
                      + Constant.CERTIFY_TYPE_BUSINESS
                      + File.separator
                      + custId;
              nameType.put("businessType", filename);
              break;
            case "certAttribute1":
              filename = "attach1" + type;
              /** /data/nfsshare/upload/certificate/04/custId * */
              fileUploadPath =
                  fileUploadPath
                      + File.separator
                      + Constant.CERTIFY_TYPE_MERCHANT_IDCARD
                      + File.separator
                      + custId;
              nameType.put("idCardType_1", filename);
              break;
            case "certAttribute2":
              filename = "attach2" + type;
              /** /data/nfsshare/upload/certificate/04/custId * */
              fileUploadPath =
                  fileUploadPath
                      + File.separator
                      + Constant.CERTIFY_TYPE_MERCHANT_IDCARD
                      + File.separator
                      + custId;
              nameType.put("idCardType_2", filename);
              break;
              //					case "bankCard":
              //						filename = "attach1" + type;
              //						fileUploadPath = fileUploadPath + File.separator +
              // Constant.CERTIFY_TYPE_BANK_CARD + File.separator + custId;
              //						nameType.put("bankCard", filename);
              //						break;
              //					case "otherPapers":
              //						filename = "attach1" + type;
              //						fileUploadPath = fileUploadPath + File.separator +
              // Constant.CERTIFY_TYPE_OTHER_PAPERS + File.separator + custId;
              //						nameType.put("otherPapers", filename);
              //						break;
            default:
              break;
          }
          File saveFile = new File(fileUploadPath);
          if (!saveFile.exists()) {
            saveFile.mkdirs();
          }
          delImagFile(fileUploadPath + File.separator + filename); // 删除服务器图片
          FileOutputStream fileOutputStream =
              new FileOutputStream(fileUploadPath + File.separator + filename);
          byte buffer[] = new byte[1024];
          int len = 0;
          while ((len = inputStream.read(buffer)) > 0) {
            fileOutputStream.write(buffer, 0, len);
          }
          /** 刷新此输出流并强制写出所有缓冲的输出字节 * */
          fileOutputStream.flush();
          inputStream.close();
          fileOutputStream.close();
          /**
           * Deletes the underlying storage for a file item, including deleting any associated
           * temporary disk file. *
           */
          item.delete();
        }
      }
      result.put("result", "SUCCESS");
      result.put("message", "上传成功");
      result.putAll(nameType);
      logger.info("文件上传成功");
    } catch (Exception e) {
      logger.error("上传失败", e.getMessage());
      result.put("result", "fail");
      result.put("message", e.getMessage());
    }
    return result;
  }

  // 删除服务器图片
  public void delImagFile(String path) {
    File imgFile = new File(path);
    if (imgFile.exists()) {
      imgFile.delete();
    }
  }

  /** 代理商查找图片路径 */
  public String findAgencyImagePathById(String custId, String certifyType) {
    CustScan custScan = new CustScan();
    custScan.setCustId(custId);
    custScan.setCertifyType(certifyType);
    return agencyDao.findPath(custScan);
  }
}
