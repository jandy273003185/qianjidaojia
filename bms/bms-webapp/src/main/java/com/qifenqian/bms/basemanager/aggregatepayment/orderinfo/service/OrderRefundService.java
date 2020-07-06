package com.qifenqian.bms.basemanager.aggregatepayment.orderinfo.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.qifenqian.bms.accounting.utils.ExportExcel;
import com.qifenqian.bms.basemanager.aggregatepayment.orderinfo.bean.RefundBean;
import com.qifenqian.bms.basemanager.aggregatepayment.orderinfo.bean.RefundQueryBean;
import com.qifenqian.bms.basemanager.aggregatepayment.orderinfo.dao.OrderRefundDAO;



@Service
public class OrderRefundService {
	@Autowired
	private OrderRefundDAO orderRefundDAO;
	
	@Value("${EXPORT_EXCEL}")
	private String EXPORT_EXCEL;

	/** 查询所有交易 */
	public List<RefundBean> getOrderRefundList(RefundQueryBean queryBean) {

		return orderRefundDAO.getOrderRefundList(queryBean);
	}

	public List<RefundBean> getOrderRefundListExport(RefundQueryBean queryBean) {

		return orderRefundDAO.getOrderRefundListExport(queryBean);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map<String, String> exportExcel(List excels, String[] headers, String fileName, String title,
			HttpServletRequest request) {
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
		}

		return fileInfo;
	}

	/**
	 * 查询退款详细信息
	 * 
	 * @param refundId
	 * @return
	 */
	public RefundBean getRefundDetail(String refundId) {
		return orderRefundDAO.getRefundDetail(refundId);
	}

	/**
	 * 导出自己权限的退款订单
	 * 
	 * @param queryBean
	 * @return
	 */
	public List<RefundBean> getMyOrderRefundListExport(RefundQueryBean queryBean) {
		return orderRefundDAO.getMyOrderRefundListExport(queryBean);
	}
}