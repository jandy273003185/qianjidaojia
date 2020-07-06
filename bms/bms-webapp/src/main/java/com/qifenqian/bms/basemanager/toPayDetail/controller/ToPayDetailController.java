package com.qifenqian.bms.basemanager.toPayDetail.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.basemanager.Constant;
import com.qifenqian.bms.basemanager.city.bean.CityNew;
import com.qifenqian.bms.basemanager.toPay.service.TopayService;
import com.qifenqian.bms.basemanager.toPayDetail.bean.ToPaySingleDetail;
import com.qifenqian.bms.basemanager.toPayDetail.service.ToPayDetailService;
import com.qifenqian.bms.basemanager.trade.service.TradeBillService;
import com.qifenqian.bms.basemanager.utils.DatetimeUtils;
import com.qifenqian.bms.platform.utils.DownLoadUtils;
import com.qifenqian.bms.platform.utils.ExportExcelUtils;

/**
 * 代付Controller
 *
 */
@Controller
@RequestMapping(ToPayDetailPath.BASE)
public class ToPayDetailController {

	private static Logger logger = LoggerFactory.getLogger(ToPayDetailController.class);
	
	@Autowired
	private TopayService topayService;
	
	@Autowired
	private ToPayDetailService toPayDetailService;
	
	@Autowired
	private TradeBillService tradeBillService;
	
	/**
	 * 代付详细列表
	 * @param bean
	 * @return
	 */
	@RequestMapping(ToPayDetailPath.LIST)
	public ModelAndView initTopay(ToPaySingleDetail bean) {	
		ModelAndView mv = new ModelAndView(ToPayDetailPath.BASE + ToPayDetailPath.LIST);
		//添加查询详细列表方法
		List<ToPaySingleDetail> singleDetailList = toPayDetailService.listDetail(bean);
		mv.addObject("queryBean",bean);
		mv.addObject("audingList", singleDetailList);
		return mv;
	}
	
	
    /**
     * 导出报表	
     * 
     */
    @RequestMapping(ToPayDetailPath.EXPORTDETAIL)
    public void backExportExcel(ToPaySingleDetail toPaySingleDetail, HttpServletRequest request, HttpServletResponse response) {
    	
    		logger.info("导出后台代付列表信息");
    		
    		try {
    			
    			List<ToPaySingleDetail> excels = toPayDetailService.exportDetailList(toPaySingleDetail);
    			for(int i=0;i<excels.size();i++){
    				
    				String date = excels.get(i).getCreateTime().toString();
    				excels.get(i).setCreateTime2(formatDate1(date));
    				/*excels.get(i).setCreateTime(formatDate2(date));*/
    				
    				String status = excels.get(i).getTradeStatus();
    				
    				if("01".equals(status)){
    					excels.get(i).setTradeStatus("待确认");
    				}
    				if("02".equals(status)){
    					excels.get(i).setTradeStatus("已确认");
    				}
    				if("03".equals(status)){
    					excels.get(i).setTradeStatus("已提交");
    				}
    				if("04".equals(status)){
    					excels.get(i).setTradeStatus("提交成功");
    				}
    				if("05".equals(status)){
    					excels.get(i).setTradeStatus("提交失败");
    				}
    				if("06".equals(status)){
    					excels.get(i).setTradeStatus("代付处理中");
    				}
    				if("00".equals(status)){
    					excels.get(i).setTradeStatus("代付成功");
    				}
    				if("99".equals(status)){
    					excels.get(i).setTradeStatus("代付失败");
    				}
    			}
    			
    		
    			String[] headers = {"批次ID","详情ID", "付款账号","商户名称","代付金额", "手续费", "交易订单号", "通道订单号","交易状态", "创建时间","创建时间"};
    			String fileName = DatetimeUtils.datetime()+"_代付信息_.xls";
    			
    			Map<String, String> fileInfo = ExportExcelUtils.exportExcel(excels,
    					headers, fileName, "代付信息详细报表", request);
    			
    			DownLoadUtils.downLoadFile(fileInfo.get("filePath"), response, fileInfo.get("fileName"), "xls");
    			logger.info("导出代付信息详细报表成功");

    		} catch (Exception e) {
    			logger.error("导出代付信息详细报表异常", e);
    			e.printStackTrace();
    		}	
    			
    }
    
    public static String formatDate1(String dateStr) {
        String[] aStrings = dateStr.split(" ");
        // 5
        if (aStrings[1].equals("Jan")) {
            aStrings[1] = "01";
        }
        if (aStrings[1].equals("Feb")) {
            aStrings[1] = "02";
        }
        if (aStrings[1].equals("Mar")) {
            aStrings[1] = "03";
        }
        if (aStrings[1].equals("Apr")) {
            aStrings[1] = "04";
        }
        if (aStrings[1].equals("May")) {
            aStrings[1] = "05";
        }
        if (aStrings[1].equals("Jun")) {
            aStrings[1] = "06";
        }
        if (aStrings[1].equals("Jul")) {
            aStrings[1] = "07";
        }
        if (aStrings[1].equals("Aug")) {
            aStrings[1] = "08";
        }
        if (aStrings[1].equals("Sep")) {
            aStrings[1] = "09";
        }
        if (aStrings[1].equals("Oct")) {
            aStrings[1] = "10";
        }
        if (aStrings[1].equals("Nov")) {
            aStrings[1] = "11";
        }
        if (aStrings[1].equals("Dec")) {
            aStrings[1] = "12";
        }
        return aStrings[5] + "-" + aStrings[1] + "-" + aStrings[2] + " " + aStrings[3];
    }

    public static Date formatDate2(String dateStr) {
        String[] aStrings = dateStr.split(" ");
        // 5
        if (aStrings[1].equals("Jan")) {
            aStrings[1] = "01";
        }
        if (aStrings[1].equals("Feb")) {
            aStrings[1] = "02";
        }
        if (aStrings[1].equals("Mar")) {
            aStrings[1] = "03";
        }
        if (aStrings[1].equals("Apr")) {
            aStrings[1] = "04";
        }
        if (aStrings[1].equals("May")) {
            aStrings[1] = "05";
        }
        if (aStrings[1].equals("Jun")) {
            aStrings[1] = "06";
        }
        if (aStrings[1].equals("Jul")) {
            aStrings[1] = "07";
        }
        if (aStrings[1].equals("Aug")) {
            aStrings[1] = "08";
        }
        if (aStrings[1].equals("Sep")) {
            aStrings[1] = "09";
        }
        if (aStrings[1].equals("Oct")) {
            aStrings[1] = "10";
        }
        if (aStrings[1].equals("Nov")) {
            aStrings[1] = "11";
        }
        if (aStrings[1].equals("Dec")) {
            aStrings[1] = "12";
        }
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String date = aStrings[5] + "-" + aStrings[1] + "-" + aStrings[2] + " " + aStrings[3];
        Date datetime = null;
        
            try {
				datetime = df.parse(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        
        return datetime;
    }

	
	/**
	 * 根据省份code获取城市列表
	 */
	@ResponseBody
	public String listCityByProvinceCode(String provinceCode){
		JSONObject js = new JSONObject();
		try {
			List<CityNew> list = topayService.listCityByProvinceCode(provinceCode);
			js.put("cityList", list);
			js.put("result", "success");
			
		}catch(Exception e) {
			e.printStackTrace();
			js.put("result","fail");
			js.put("message", "查询城市列表出错");
		}
		return js.toJSONString();
	}
	
	/**
	 * 计算总金额
	 * @param sumAmt
	 * @param feeAmt
	 * @return
	 */
	public static String addMoney(String sumAmt,BigDecimal feeAmt){   
		BigDecimal b1 = new BigDecimal(sumAmt);   		
		return b1.add(feeAmt).toString();   
	}
	
	// 读取服务器图片
	@RequestMapping(ToPayDetailPath.GETPICTURE)
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String scanPath = request.getParameter("scanPath");
		String certifyType = request.getParameter("certifyType");
		String front = request.getParameter("front");

		if (scanPath != null) {
			String path[] = null;
			if (certifyType.equals(Constant.CERTIFY_TYPE_PERSON_IDCARD)) {
				scanPath =scanPath.split(";")[0] ;
			}
			
			if (certifyType.equals(Constant.CERTIFY_TYPE_OPEN)) {
				scanPath =scanPath.split(";")[0] ;
			}
			
			if(certifyType.equals(Constant.CERTIFY_TYPE_BUSINESS)){
				scanPath = scanPath.split(";")[0];
			}
			
			if(certifyType.equals(Constant.CERTIFY_TYPE_BANK_PAPERS)){
				scanPath = scanPath.split(";")[0];
			}
			if (!StringUtils.isEmpty(front)) {
				path = scanPath.split(";");
				if (front.equals("0")) {
					scanPath = path[0];
				} else {
					scanPath = path[1];
				}
			}

			OutputStream os = response.getOutputStream();
			File file = new File(scanPath);
			
			if (file.exists()) {
				FileInputStream fips = new FileInputStream(file);
				byte[] btImg = readStream(fips);
				os.write(btImg);
				os.flush();
				if (null != fips) {
					fips.close();
				}
				if (null != os) {
					os.close();

				}
			}

		}

	}

	/**
	 * 读取管道中的流数据
	 */
	public byte[] readStream(InputStream inStream) {
		ByteArrayOutputStream bops = new ByteArrayOutputStream();
		int data = -1;
		try {
			while ((data = inStream.read()) != -1) {
				bops.write(data);
			}
			return bops.toByteArray();
		} catch (Exception e) {
			return null;
		} finally {
			if (null != bops) {
				try {
					bops.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}	

}
