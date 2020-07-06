package com.qifenqian.bms.accounting.acctingworkdate;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.accounting.acctingworkdate.bean.AcctWorkDate;
import com.qifenqian.bms.accounting.acctingworkdate.dao.AcctWorkDateDao;
import com.qifenqian.bms.accounting.acctingworkdate.mapper.AcctWorkDateMapper;
import com.qifenqian.bms.platform.web.admin.user.bean.User;
import com.qifenqian.bms.platform.web.admin.utils.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(AcctWorkDatePath.BASE)
public class AcctWorkDateController {

	private static Logger logger = LoggerFactory.getLogger(AcctWorkDateController.class);

	@Autowired
	private AcctWorkDateDao acctWorkDateDao;

	@Autowired
	private AcctWorkDateMapper acctWorkDateMapper;

	/**
	 * 会计日期列表
	 * 
	 * @param queryBean
	 * @return
	 */
	@RequestMapping(AcctWorkDatePath.LIST)
	public ModelAndView list(AcctWorkDate queryBean) {
		logger.info("查询会计日期对象 {}", JSONObject.toJSONString(queryBean, true));
		ModelAndView mv = new ModelAndView(AcctWorkDatePath.BASE + AcctWorkDatePath.LIST);
		List<AcctWorkDate> acctWorkDateList = acctWorkDateDao.queryAcctWorkDatList(queryBean);
		mv.addObject("queryBean", queryBean);
		mv.addObject("acctWorkDateList", acctWorkDateList);

		return mv;
	}

	/**
	 * 修改
	 * 
	 * @param updateBean
	 * @return
	 */
	@RequestMapping(AcctWorkDatePath.EDIT)
	@ResponseBody
	public String eidt(AcctWorkDate updateBean) {

		logger.info("修改会计日期对象 {}", JSONObject.toJSONString(updateBean, true));
		JSONObject jsonObj = new JSONObject();

		if (null == updateBean || null == updateBean.getWorkDate()) {
			jsonObj.put("result", "FAILT");
			jsonObj.put("message", "修改对象为空");
			return jsonObj.toJSONString();
		}
		User user = WebUtils.getUserInfo();
		AcctWorkDate queryBean = new AcctWorkDate();
		try {
			List<AcctWorkDate> acctWorkDateList = acctWorkDateMapper.queryAcctWorkDatList(queryBean);

			AcctWorkDate workDateHis = acctWorkDateMapper.selectWorkDateHis(acctWorkDateList.get(0).getWorkDate());

			if (null == workDateHis) {
				AcctWorkDate insertBean = new AcctWorkDate();
				insertBean.setWorkDate(acctWorkDateList.get(0).getWorkDate());
				insertBean.setMemo(acctWorkDateList.get(0).getMemo());
				insertBean.setInstUser(String.valueOf(user.getUserId()));
				logger.info("AcctWorkDateMapper.insertWorkDateHis !");
				acctWorkDateMapper.insertWorkDateHis(insertBean);
			} else {
				logger.info("AcctWorkDateMapper.updateWorkDateHis !");
				acctWorkDateMapper.updateWorkDateHis(acctWorkDateList.get(0).getWorkDate());
			}

			logger.info("AcctWorkDateMapper.deleteWorkDate !");
			acctWorkDateMapper.deleteWorkDate();
			
			logger.info("AcctWorkDateMapper.insertWorkDate!");
			updateBean.setInstUser(String.valueOf(user.getUserId()));
			acctWorkDateMapper.insertWorkDate(updateBean);

			jsonObj.put("result", "SUCCESS");

		} catch (Exception e) {
			logger.error("会计日期修改异常 " + e.getMessage());
			jsonObj.put("result", "FAILT");
			jsonObj.put("message", "会计日期修改异常");
		}

		return jsonObj.toJSONString();

	}
}
