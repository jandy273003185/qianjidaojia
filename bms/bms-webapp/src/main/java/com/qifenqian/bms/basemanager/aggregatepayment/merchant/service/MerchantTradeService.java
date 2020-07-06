package com.qifenqian.bms.basemanager.aggregatepayment.merchant.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.qifenqian.bms.accounting.utils.ExportExcel;
import com.qifenqian.bms.basemanager.aggregatepayment.merchant.bean.MerchantTradeQueryBean;
import com.qifenqian.bms.basemanager.aggregatepayment.merchant.bean.OrderSummaryBean;
import com.qifenqian.bms.basemanager.aggregatepayment.merchant.mapper.MerchantTradeMapper;
import com.qifenqian.bms.platform.web.page.Page;

@Service
public class MerchantTradeService {
  @Autowired private MerchantTradeMapper mapper;
  
  @Value("${EXPORT_EXCEL}")
  private String EXPORT_EXCEL;

  @Page
  public List<OrderSummaryBean> getMerchantTradeList(MerchantTradeQueryBean queryBean) {
    return mapper.getMerchantTradeList(queryBean);
  }

  @Page
  public List<OrderSummaryBean> getMerchantRefundList(MerchantTradeQueryBean queryBean) {
    return mapper.getMerchantRefundList(queryBean);
  }

  public List<OrderSummaryBean> getMerchantExportList(MerchantTradeQueryBean queryBean) {
    return mapper.getMerchantTradeList(queryBean);
  }

  public List<OrderSummaryBean> getMerchantRefundExportList(MerchantTradeQueryBean queryBean) {

    return mapper.getMerchantRefundList(queryBean);
  }

  public Map<String, String> exportExcel(
      List excels, String[] headers, String fileName, String title, HttpServletRequest request) {
    Map<String, String> fileInfo = new HashMap<String, String>();
    OutputStream out = null;
    try {
      String exportPath = EXPORT_EXCEL;
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
      return fileInfo;
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
    }

    return null;
  }

  public List<BigDecimal> getTotal(List<OrderSummaryBean> tradeBills) {
    BigDecimal allCount = new BigDecimal(0.00);
    BigDecimal allSum = new BigDecimal(0.00);
    BigDecimal allSettle = new BigDecimal(0.00);
    List<BigDecimal> list = new ArrayList<BigDecimal>();
    for (OrderSummaryBean orderSummaryBean : tradeBills) {
      if (orderSummaryBean.getCountTrade() != null
          && !"".equals(orderSummaryBean.getCountTrade())) {
        allCount = allCount.add(new BigDecimal(orderSummaryBean.getCountTrade()));
      }
      if (orderSummaryBean.getSumTrade() != null && !"".equals(orderSummaryBean.getSumTrade())) {
        allSum = allSum.add(new BigDecimal(orderSummaryBean.getSumTrade()));
      }
      if (orderSummaryBean.getSettleAmt() != null && !"".equals(orderSummaryBean.getSettleAmt())) {
        allSettle = allSettle.add(new BigDecimal(orderSummaryBean.getSettleAmt()));
      }
    }
    list.add(allCount);
    list.add(allSum);
    list.add(allSettle);
    return list;
  }
}
