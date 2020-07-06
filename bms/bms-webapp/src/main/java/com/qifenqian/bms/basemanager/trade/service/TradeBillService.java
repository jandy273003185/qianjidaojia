package com.qifenqian.bms.basemanager.trade.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.qifenqian.bms.accounting.utils.ExportExcel;
import com.qifenqian.bms.basemanager.trade.bean.AllTradeBill;
import com.qifenqian.bms.basemanager.trade.bean.TdTradeBillMainVO;
import com.qifenqian.bms.basemanager.trade.bean.TradeExcel;
import com.qifenqian.bms.basemanager.trade.bean.TradeSummaryExcel;
import com.qifenqian.bms.basemanager.trade.dao.TradeBillDAO;
import com.qifenqian.bms.basemanager.trade.mapper.TdTradeBillMainMapper;

@Service
public class TradeBillService {

	@Autowired
	private TradeBillDAO tradeBillDAO;

	@Autowired
	private TdTradeBillMainMapper mapper;
	
	@Value("${EXPORT_EXCEL}")
	private String EXPORT_EXCEL;

	/**
	 * 汇总查询
	 * 
	 * @param tdTradeBillMainVO
	 * @return
	 */
	public List<TdTradeBillMainVO> selectTdradeBillMainSummary(TdTradeBillMainVO tdTradeBillMainVO) {
		return tradeBillDAO.selectTdradeBillMainSummary(tdTradeBillMainVO);
	}

	/**
	 * 查询汇总记录 （不分页）
	 * 
	 * @param tdTradeBillMainVO
	 * @return
	 */
	public List<TradeSummaryExcel> selectTradeSummaryExcel(TdTradeBillMainVO tdTradeBillMainVO) {
		List<TradeSummaryExcel> excels = mapper.selectTradeSummaryExcel(tdTradeBillMainVO);
		if (StringUtils.isEmpty(tdTradeBillMainVO.getBeginTime())) {
			for (TradeSummaryExcel vo : excels) {
				vo.setBeginTime(vo.getMinDate().substring(0, 10));
			}
		} else {
			for (TradeSummaryExcel vo : excels) {
				vo.setBeginTime(tdTradeBillMainVO.getBeginTime());
			}
		}
		if (StringUtils.isEmpty(tdTradeBillMainVO.getEndCreateTime())) {
			for (TradeSummaryExcel vo : excels) {
				vo.setEndCreateTime(vo.getMaxDate().substring(0, 10));
			}
		} else {
			for (TradeSummaryExcel vo : excels) {
				vo.setEndCreateTime(tdTradeBillMainVO.getEndCreateTime());
			}
		}

		return excels;
	}

	/**
	 * 导出消费列表
	 * 
	 * @param tdTradeBillMainVO
	 * @return
	 */
	public List<TradeExcel> selectTradeConsumeExcel(TdTradeBillMainVO tdTradeBillMainVO) {
		List<TradeExcel> excels = mapper.selectTradeConsumeExcel(tdTradeBillMainVO);
		return excels;
	}

	/**
	 * 导出excel
	 * 
	 * @param excels
	 *            excel对象集合
	 * @param headers
	 *            excel列表头信息
	 * @param fileName
	 *            保存文件名称
	 * @param request
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes", "finally" })
	public Map<String, String> exportExcel(List excels, String[] headers, String fileName, String title, HttpServletRequest request) {
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
			return fileInfo;
		}

	}

	/**
	 * 查询消费
	 * 
	 * @param tdTradeBillMainVO
	 * @return
	 */
	public List<TdTradeBillMainVO> selectConsume(TdTradeBillMainVO tdTradeBillMainVO) {
		return tradeBillDAO.selectConsume(tdTradeBillMainVO);
	}

	/** 查询所有交易*/
	public List<AllTradeBill> getAllTradeBill(String bank,String payProd,String payChannel) {
		// TODO Auto-generated method stub
		return tradeBillDAO.getAllTradeBill(bank,payProd,payChannel);
	}

	public List<AllTradeBill> getAllTradeBillExport(String bank,
			String payProd, String payChannel) {
		// TODO Auto-generated method stub
		return tradeBillDAO.getAllTradeBillExport(bank,payProd,payChannel);
	}

}
