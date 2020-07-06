package com.qifenqian.bms.bal.accountResult.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.servlet.ModelAndView;

import com.qifenqian.bms.bal.accountResult.bean.BalBatchResultStatistic;
import com.qifenqian.bms.bal.accountResult.bean.BalExternalData;
import com.qifenqian.bms.bal.accountResult.bean.BalExternalResultEqual;
import com.qifenqian.bms.bal.accountResult.bean.BalExternalResultException;
import com.qifenqian.bms.bal.accountResult.bean.BalInternalData;
import com.qifenqian.bms.bal.accountResult.bean.BalInternalResultEqual;
import com.qifenqian.bms.bal.accountResult.bean.BalInternalResultException;
import com.qifenqian.bms.bal.accountResult.bean.BaseChannel;
import com.qifenqian.bms.bal.accountResult.mapper.BalBatchResultStatisticMapper;
import com.qifenqian.bms.bal.accountResult.mapper.BalExternalDataMapper;
import com.qifenqian.bms.bal.accountResult.mapper.BalExternalResultEqualMapper;
import com.qifenqian.bms.bal.accountResult.mapper.BalExternalResultExceptionMapper;
import com.qifenqian.bms.bal.accountResult.mapper.BalInternalDataMapper;
import com.qifenqian.bms.bal.accountResult.mapper.BalInternalResultEqualMapper;
import com.qifenqian.bms.bal.accountResult.mapper.BalInternalResultExceptionMapper;
import com.qifenqian.bms.bal.accountResult.service.BalExternalResultEqualService;
import com.qifenqian.bms.bal.accountResult.service.BalExternalResultExceptionService;
import com.qifenqian.bms.bal.accountResult.service.BalInternalDataService;
import com.qifenqian.bms.bal.accountResult.service.BalInternalResultEqualService;
import com.qifenqian.bms.bal.accountResult.service.BalInternalResultExceptionService;
import com.qifenqian.bms.bal.accountResult.service.BatchExternalDataService;
import com.qifenqian.bms.bal.accountResult.service.BatchResultStatisticService;
import com.qifenqian.bms.platform.common.utils.DatetimeUtils;
import com.qifenqian.bms.platform.utils.DownLoadUtils;
import com.qifenqian.bms.platform.utils.ExportExcelUtils;

@Controller
@RequestMapping(AccountResultPath.BASE)
public class AccountResultController {

  private static Logger logger = LoggerFactory.getLogger(AccountResultController.class);

  @Autowired private BatchResultStatisticService batchResultStatisticService;
  @Autowired private BalBatchResultStatisticMapper balBatchResultStatisticMapper;

  @Autowired private BatchExternalDataService batchExternalDataService;
  @Autowired private BalExternalDataMapper balExternalDataMapper;

  @Autowired private BalExternalResultEqualService balExternalResultEqualService;
  @Autowired private BalExternalResultEqualMapper balExternalResultEqualMapper;

  @Autowired private BalExternalResultExceptionService balExternalResultExceptionService;
  @Autowired private BalExternalResultExceptionMapper balExternalResultExceptionMapper;

  @Autowired private BalInternalDataService balInternalDataService;
  @Autowired private BalInternalDataMapper balInternalDataMapper;

  @Autowired private BalInternalResultEqualService balInternalResultEqualService;
  @Autowired private BalInternalResultEqualMapper balInternalResultEqualMapper;

  @Autowired private BalInternalResultExceptionService balInternalResultExceptionService;
  @Autowired private BalInternalResultExceptionMapper balInternalResultExceptionMapper;

  /** 七分钱对账结果统计 */
  @RequestMapping(AccountResultPath.STATISTICSLIST)
  public ModelAndView statisticList(BalBatchResultStatistic balBatchResultStatistic) {

    ModelAndView mv = new ModelAndView(AccountResultPath.BASE + AccountResultPath.STATISTICSLIST);
    List<BalBatchResultStatistic> list =
        batchResultStatisticService.selectList(balBatchResultStatistic);
    List<BaseChannel> baseChannels = batchResultStatisticService.queryBalBaseChannel();

    mv.addObject("resultList", list);
    mv.addObject("balBatchResultStatistic", balBatchResultStatistic);
    mv.addObject("baseChannels", baseChannels);

    return mv;
  }

  /**
   * 七分钱对账结果统计报表导出
   *
   * @return
   */
  @RequestMapping(AccountResultPath.STATISTICSEXPORT)
  public void exportExcel(
      BalBatchResultStatistic requestBean,
      HttpServletRequest request,
      HttpServletResponse response) {
    logger.info("七分钱对账结果统计报表导出");

    String workDateDefault = DateFormatUtils.format(DateUtils.addDays(new Date(), -1), "yyyymmdd");

    if (StringUtils.isBlank(requestBean.getWorkDate())) {
      requestBean.setWorkDate(workDateDefault);
    }

    try {

      List<BalBatchResultStatistic> excels = balBatchResultStatisticMapper.selectList(requestBean);

      String[] headers = {
        "对账批次", "对账日期", "会计日期", "渠道", "文件编号", "交易结果", "业务类型", "数据平台", "总笔数", "总金额", "对账一致笔数",
        "对账一致总额", "对账存疑笔数", "对账存疑总额", "对账差错笔数", "对账差错总额", "自身异常笔数", "自身异常总额", "状态", "备注"
      };
      String fileName = DatetimeUtils.datetime() + "_对账结果统计报表_.xls";

      Map<String, String> fileInfo =
          ExportExcelUtils.exportExcel(excels, headers, fileName, "对账结果统计报表", request);

      DownLoadUtils.downLoadFile(
          fileInfo.get("filePath"), response, fileInfo.get("fileName"), "xls");
      logger.info("导出对账结果统计报表成功");

    } catch (Exception e) {
      logger.error("导出对账结果统计报表异常", e);
      e.printStackTrace();
    }
  }

  /** 外部源数据 */
  @RequestMapping(AccountResultPath.EXTERNALDATALIST)
  public ModelAndView externalDataList(BalExternalData balExternalData) {
    ModelAndView mv = new ModelAndView(AccountResultPath.BASE + AccountResultPath.EXTERNALDATALIST);
    List<BalExternalData> list = batchExternalDataService.selectList(balExternalData);
    List<BaseChannel> baseChannels = batchResultStatisticService.queryBalBaseChannel();

    mv.addObject("resultList", list);
    mv.addObject("balExternalData", balExternalData);
    mv.addObject("baseChannels", baseChannels);
    return mv;
  }

  /** 外部源数据报表导出 */
  @RequestMapping(AccountResultPath.EXTERNALDATAEXPORT)
  public void exportExcel(
      BalExternalData requestBean, HttpServletRequest request, HttpServletResponse response) {
    logger.info("外部源数据报表导出");

    try {
      List<BalExternalData> excels = balExternalDataMapper.selectList(requestBean);

      String[] headers = {
        "源数据编号", "渠道编号", "文件编号", "序号/行号", "清算编号", "会计日期(数据日期)", "初始写入人", "写入日期", "写入时间"
      };
      String fileName = DatetimeUtils.datetime() + "_外部源数据报表_.xls";
      Map<String, String> fileInfo =
          ExportExcelUtils.exportExcel(excels, headers, fileName, "外部源数据报表", request);
      DownLoadUtils.downLoadFile(
          fileInfo.get("filePath"), response, fileInfo.get("fileName"), "xls");
      logger.info("外部源数据报表导出成功");

    } catch (Exception e) {
      logger.info("外部源数据报表导出异常", e);
      throw new RuntimeException(e);
    }
  }

  /** 外部系统对账结果一致表 */
  @RequestMapping(AccountResultPath.EXTERNALRESULTEQUALLIST)
  public ModelAndView externalResultEqualList(BalExternalResultEqual balExternalResultEqual) {
    ModelAndView mv =
        new ModelAndView(AccountResultPath.BASE + AccountResultPath.EXTERNALRESULTEQUALLIST);
    List<BalExternalResultEqual> list =
        balExternalResultEqualService.selectList(balExternalResultEqual);
    List<BaseChannel> baseChannels = batchResultStatisticService.queryBalBaseChannel();
    mv.addObject("resultList", list);
    mv.addObject("balExternalResultEqual", balExternalResultEqual);
    mv.addObject("baseChannels", baseChannels);
    return mv;
  }

  /** 外部系统对账结果表导出 */
  @RequestMapping(AccountResultPath.EXTERNALRESULTREQUALEXPORT)
  public void exportExcel(
      BalExternalResultEqual requestBean,
      HttpServletRequest request,
      HttpServletResponse response) {
    List<BalExternalResultEqual> excels = balExternalResultEqualMapper.selectList(requestBean);

    try {
      String[] headers = {
        "一致编号",
        "数据源编号",
        "渠道编号",
        "文件编号",
        "序号/行号",
        "清算编号",
        "会计日期(数据日期)",
        "对账批次号",
        "对账结果：EQUAL_BAL/EQUAL_MORROW/EQUAL_HAND",
        "对账处理备注",
        "初始写入人",
        "写入日期:YYYY-MM-DD",
        "初始时间"
      };
      String fileName = DatetimeUtils.datetime() + "_外部系统对账结果表_.xls";
      Map<String, String> fileInfo =
          ExportExcelUtils.exportExcel(excels, headers, fileName, "外部系统对账结果", request);
      DownLoadUtils.downLoadFile(
          fileInfo.get("filePath"), response, fileInfo.get("fileName"), "xls");
      logger.info("外部系统对账结果表导出成功");

    } catch (Exception e) {
      logger.info("外部系统对账结果表导出异常", e);
      throw new RuntimeException(e);
    }
  }

  /** 外部系统对账异常表 */
  @RequestMapping(AccountResultPath.EXTERNALRESULTEXCEPTIONLIST)
  public ModelAndView externalResultExceptionList(
      BalExternalResultException balExternalResultException) {
    ModelAndView mv =
        new ModelAndView(AccountResultPath.BASE + AccountResultPath.EXTERNALRESULTEXCEPTIONLIST);
    List<BalExternalResultException> list =
        balExternalResultExceptionService.selectList(balExternalResultException);
    List<BaseChannel> baseChannels = batchResultStatisticService.queryBalBaseChannel();
    mv.addObject("baseChannels", baseChannels);
    mv.addObject("resultList", list);
    mv.addObject("balExternalResultException", balExternalResultException);
    return mv;
  }

  /** 外部系统对账异常表导出 */
  @RequestMapping(AccountResultPath.EXTERNALRESULTEXCEPTIONEXPORT)
  public void exportExcel(
      BalExternalResultException requestBean,
      HttpServletRequest request,
      HttpServletResponse response) {
    logger.info("导出外部系统对账结果异常表");

    try {
      List<BalExternalResultException> excels =
          balExternalResultExceptionMapper.selectList(requestBean);

      String[] headers = {
        "异常编号",
        "源数据编号",
        "渠道编号",
        "文件编号",
        "序号/行号",
        "清算编号",
        "会计日期(数据日期)",
        "对账批次号",
        "对账结果：SELF_DOUBT/Bal_DOUBT/Bal_ERROR",
        "对账处理备注",
        "初始写入人",
        "写入日期：YYYY-MM-DD",
        "初始时间",
        "异常处理标记：挂账/抹账/销账",
        "异常处理备注",
        "异常处理人",
        "异常处理时间"
      };
      String fileName = DatetimeUtils.datetime() + "_外部系统对账结果异常表_.xls";
      Map<String, String> fileInfo =
          ExportExcelUtils.exportExcel(excels, headers, fileName, "外部系统对账结果异常表", request);
      DownLoadUtils.downLoadFile(
          fileInfo.get("filePath"), response, fileInfo.get("fileName"), "xls");
      logger.info("导出外部系统对账结果异常表成功");
    } catch (Exception e) {
      logger.error("导出外部系统对账结果异常表异常", e);
      throw new RuntimeException(e);
    }
  }

  /**
   * 七分钱源数据表
   *
   * @return
   */
  @RequestMapping(AccountResultPath.BALINTERNALDATALIST)
  public ModelAndView balInternalDataList(BalInternalData balInternalData) {
    ModelAndView mv =
        new ModelAndView(AccountResultPath.BASE + AccountResultPath.BALINTERNALDATALIST);
    List<BalInternalData> list = balInternalDataService.selectList(balInternalData);
    List<BaseChannel> baseChannels = batchResultStatisticService.queryBalBaseChannel();
    mv.addObject("baseChannels", baseChannels);
    mv.addObject("resultList", list);
    mv.addObject("balInternalData", balInternalData);
    return mv;
  }

  /**
   * 导出七分钱源数据表
   *
   * @param requestBean
   * @param request
   * @param response
   */
  @RequestMapping(AccountResultPath.BALINTERNALDATAEXPORT)
  public void exportExcel(
      BalInternalData requestBean, HttpServletRequest request, HttpServletResponse response) {
    logger.info("导出BalInternalData");

    try {
      List<BalInternalData> excels = balInternalDataMapper.selectList(requestBean);

      String[] headers = {
        "源数据编号", "渠道编号", "清算编号", "会计日期(数据日期)", "初始写入人", "写入日期：YYYY-MM-DD", "写入时间"
      };
      String fileName = DatetimeUtils.datetime() + "_七分钱源数据表_.xls";
      Map<String, String> fileInfo =
          ExportExcelUtils.exportExcel(excels, headers, fileName, "七分钱源数据表", request);
      DownLoadUtils.downLoadFile(
          fileInfo.get("filePath"), response, fileInfo.get("fileName"), "xls");
      logger.info("导出七分钱源数据表成功");
    } catch (Exception e) {
      logger.error("导出七分钱源数据表异常", e);
      throw new RuntimeException(e);
    }
  }

  /**
   * 七分钱对账结果一致表
   *
   * @return
   */
  @RequestMapping(AccountResultPath.BALINTERNALRESULTEQUALLIST)
  public ModelAndView balInternalResultEqualList(BalInternalResultEqual balInternalResultEqual) {
    ModelAndView mv =
        new ModelAndView(AccountResultPath.BASE + AccountResultPath.BALINTERNALRESULTEQUALLIST);
    List<BalInternalResultEqual> list =
        balInternalResultEqualService.selectList(balInternalResultEqual);
    List<BaseChannel> baseChannels = batchResultStatisticService.queryBalBaseChannel();
    mv.addObject("baseChannels", baseChannels);
    mv.addObject("resultList", list);
    mv.addObject("balInternalData", balInternalResultEqual);
    return mv;
  }

  /**
   * 导出七分钱对账结果一致表
   *
   * @param requestBean
   * @param request
   * @param response
   */
  @RequestMapping(AccountResultPath.BALINTERNALRESULTEQUALEXPORT)
  public void exportExcel(
      BalInternalResultEqual requestBean,
      HttpServletRequest request,
      HttpServletResponse response) {
    logger.info("导出七分钱对账结果一致表");

    try {
      List<BalInternalResultEqual> excels = balInternalResultEqualMapper.selectList(requestBean);

      String[] headers = {
        "一致编号",
        "源数据编号",
        "渠道编号",
        "清算编号",
        "会计日期(数据日期)",
        "对账批次号",
        "对账结果：EQUAL_BAL/EQUAL_MORROW/EQUAL_HAND",
        "对账处理备注",
        "初始写入人",
        "写入日期：YYYY-MM-DD",
        "初始时间"
      };
      String fileName = DatetimeUtils.datetime() + "_七分钱对账结果一致表_.xls";
      Map<String, String> fileInfo =
          ExportExcelUtils.exportExcel(excels, headers, fileName, "七分钱对账结果一致表", request);
      DownLoadUtils.downLoadFile(
          fileInfo.get("filePath"), response, fileInfo.get("fileName"), "xls");
      logger.info("导出七分钱对账结果一致表成功");
    } catch (Exception e) {
      logger.error("导出七分钱对账结果一致表异常", e);
      throw new RuntimeException(e);
    }
  }

  /**
   * 七分钱对账结果异常表
   *
   * @return
   */
  @RequestMapping(AccountResultPath.BALINTERNALRESULTEXCEPTIONLIST)
  public ModelAndView balInternalResultExceptionList(
      BalInternalResultException balInternalResultException) {
    ModelAndView mv =
        new ModelAndView(AccountResultPath.BASE + AccountResultPath.BALINTERNALRESULTEXCEPTIONLIST);
    List<BalInternalResultException> list =
        balInternalResultExceptionService.selectList(balInternalResultException);
    List<BaseChannel> baseChannels = batchResultStatisticService.queryBalBaseChannel();
    mv.addObject("baseChannels", baseChannels);
    mv.addObject("resultList", list);
    mv.addObject("balInternalData", balInternalResultException);
    return mv;
  }

  /**
   * 导出七分钱对账结果异常表
   *
   * @param requestBean
   * @param request
   * @param response
   */
  @RequestMapping(AccountResultPath.BALINTERNALRESULTEXCEPTIONEXPORT)
  public void exportExcel(
      BalInternalResultException requestBean,
      HttpServletRequest request,
      HttpServletResponse response) {
    logger.info("导出七分钱对账结果异常表");

    try {
      List<BalInternalResultException> excels =
          balInternalResultExceptionMapper.selectList(requestBean);

      String[] headers = {
        "异常编号",
        "源数据编号",
        "渠道编号",
        "清算编号",
        "会计日期(数据日期)",
        "对账批次号",
        "对账结果：SELF_DOUBT/BAL_DOUBT/BAL_ERROR",
        "对账处理备注",
        "初始写入人",
        "写入日期：YYYY-MM-DD",
        "初始时间",
        "异常处理标记：挂账/抹账/销账",
        "异常处理备注",
        "异常处理人",
        "异常处理时间"
      };
      String fileName = DatetimeUtils.datetime() + "_七分钱对账结果异常表_.xls";
      Map<String, String> fileInfo =
          ExportExcelUtils.exportExcel(excels, headers, fileName, "七分钱对账结果异常表", request);
      DownLoadUtils.downLoadFile(
          fileInfo.get("filePath"), response, fileInfo.get("fileName"), "xls");
      logger.info("导出七分钱对账结果异常表成功");
    } catch (Exception e) {
      logger.error("导出七分钱对账结果异常表异常", e);
      throw new RuntimeException(e);
    }
  }
}
