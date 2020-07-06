package com.qifenqian.bms.meeting.prizewin;

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
import com.qifenqian.bms.basemanager.trade.service.DownLoadUtil;
import com.qifenqian.bms.basemanager.trade.service.TradeBillService;
import com.qifenqian.bms.basemanager.utils.DatetimeUtils;
import com.qifenqian.bms.meeting.prizewin.bean.PrizeWinBean;
import com.qifenqian.bms.meeting.prizewin.bean.PrizeWinExcel;
import com.qifenqian.bms.meeting.prizewin.mapper.PrizeWinMapper;
import com.qifenqian.bms.meeting.prizewin.service.PrizeWinService;

@Controller
@RequestMapping(PrizeWinPath.BASE)
public class PrizeWinController {

	private static Logger logger = LoggerFactory.getLogger(PrizeWinController.class);

	@Autowired
	private PrizeWinService prizeWinService;
	
	@Autowired
	private PrizeWinMapper prizeWinMapper;

	@Autowired
	private TradeBillService tradeBillService;

	/**
	 * 幸运用户信息列表
	 * 
	 * @param ad
	 * @return
	 */
	@RequestMapping(PrizeWinPath.LIST)
	public ModelAndView list(PrizeWinBean queryBean) {
		logger.info("查询幸运用户信息对象{}", JSONObject.toJSONString(queryBean, true));
		ModelAndView mv = new ModelAndView(PrizeWinPath.BASE + PrizeWinPath.LIST);
		List<PrizeWinBean> lotteryList = prizeWinService.selectLotteryUserList(queryBean);
		mv.addObject("lotteryList", JSONObject.toJSON(lotteryList));
		mv.addObject("queryBean", queryBean);
		return mv;
	}

	/**
	 * 导出幸运用户信息
	 * 
	 * @param recharge
	 */
	@RequestMapping(PrizeWinPath.LOTTERYEXPORT)
	public void lotteryExport(PrizeWinBean queryBean, HttpServletRequest request, HttpServletResponse response) {

		logger.info("导出幸运用户信息");

		try {
			String[] headers = { "中奖流水", "奖项编号", "奖项名称","中奖客户号", "中奖人姓名", "中奖客户手机号码", "中奖金额", "有效截止时间","状态", "创建人", "活动日期","标识", 
					"创建时间", "更新人", "更新时间" };
			List<PrizeWinExcel> lotteryExcel = prizeWinMapper.selectPrizeWinExcel(queryBean);
			String fileName = DatetimeUtils.dateSecond() + "_幸运用户信息.xls";
			Map<String, String> fileInfo = tradeBillService.exportExcel(lotteryExcel, headers, fileName, "幸运用户信息",
					request);
			DownLoadUtil.downLoadFile(fileInfo.get("filePath"), response, fileInfo.get("fileName"), "xls");
			logger.info("导出excel幸运用户信息成功");
		} catch (Exception e) {
			logger.error("导出excel幸运用户信息异常", e);
		}

	}

	/**
	 * 幸运用户信息删除
	 * 
	 * @param requestUser
	 * @return
	 */
	@RequestMapping(PrizeWinPath.DELETE)
	@ResponseBody
	public String delete(PrizeWinBean editBean) {
		// 请求bean 打印
		logger.info("请求删除幸运用户信息：[{}]", JSONObject.toJSONString(editBean, true));

		JSONObject jsonObject = new JSONObject();
		try {
			

			prizeWinService.deleteLottery(editBean);
			jsonObject.put("result", "SUCCESS");
		} catch (Exception e) {
			logger.error("删除幸运用户信息异常", e);
			jsonObject.put("result", "FAIL");
			jsonObject.put("message", e.getMessage());
		}
		return jsonObject.toJSONString();
	}
}
