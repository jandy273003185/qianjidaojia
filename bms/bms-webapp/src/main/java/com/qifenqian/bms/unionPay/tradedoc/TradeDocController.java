package com.qifenqian.bms.unionPay.tradedoc;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.qifenqian.bms.unionPay.tradedoc.bean.TradeDoc;
import com.qifenqian.bms.unionPay.tradedoc.mapper.TradeDocMapper;

@Controller
@RequestMapping(TradeDocPath.BASE)
public class TradeDocController {

	@Autowired
	private TradeDocMapper tradeDocMapper;

	@RequestMapping(TradeDocPath.LIST)
	public ModelAndView list(TradeDoc tradeDoc) {

		if (null == tradeDoc.getWorkDate() || "".equals(tradeDoc.getWorkDate())) {
			tradeDoc.setWorkDate(shorYesterDaytDate());
		}
		ModelAndView mv = new ModelAndView(TradeDocPath.BASE + TradeDocPath.LIST);
		List<TradeDoc> docList = tradeDocMapper.selectDocList(tradeDoc);
		mv.addObject("queryBean", tradeDoc);
		mv.addObject("docList", docList);
		return mv;
	}

	/**
	 * 
	 * @return
	 */
	public String shorYesterDaytDate() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		String yesterday = new SimpleDateFormat("yyyyMMdd ").format(cal.getTime());
		return yesterday;
	}
}
