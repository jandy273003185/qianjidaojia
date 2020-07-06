package com.qifenqian.bms.sms.message;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

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
import com.qifenqian.bms.accounting.exception.OperationExceptionController;
import com.qifenqian.bms.basemanager.Constant;
import com.qifenqian.bms.platform.common.utils.SpringUtils;
import com.qifenqian.bms.sms.message.bean.BaseMessage;
import com.qifenqian.bms.sms.message.dao.BaseMessageDao;
import com.qifenqian.bms.sms.message.mapper.BaseMessageMapper;
import com.qifenqian.bms.sms.message.service.BaseMessageService;
import com.qifenqian.bms.sms.message.service.BatchSendMessageThread;
import com.qifenqian.bms.sms.thread.BaseMessageThreadPool;

@Controller
@RequestMapping(BaseMessagePath.BASE)
public class BaseMessageController {

	private static final String INIT = "INIT";
	private static final String SUCCESS = "SUCCESS";

	private static Logger logger = LoggerFactory.getLogger(OperationExceptionController.class);

	@Autowired
	private BaseMessageService baseMessageService;

	@Autowired
	private BaseMessageDao baseMessageDao;

	@Autowired
	private BaseMessageMapper baseMessageMapper;

	@RequestMapping(BaseMessagePath.LIST)
	public ModelAndView list(BaseMessage queryBean) {
		logger.info("对比数据查询对象 {}", JSONObject.toJSONString(queryBean, true));

		ModelAndView mv = new ModelAndView(BaseMessagePath.BASE + BaseMessagePath.LIST);
		List<BaseMessage> baseMessageList = baseMessageDao.selectBaseMessageList(queryBean);
		mv.addObject("queryBean", queryBean);
		mv.addObject("baseMessageList", JSONObject.toJSON(baseMessageList));
		return mv;
	}

	/**
	 * 上传EXCEL
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	@RequestMapping(BaseMessagePath.IMPORT_EXCEL)
	@ResponseBody
	public String fileUpload(HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException,
			IOException {
		logger.info("导入Excel文件");
		JSONObject object = new JSONObject();
		object = baseMessageService.fileUpload(request, response);

		return object.toJSONString();
	}

	/**
	 * 删除
	 * 
	 * @param deleteBean
	 * @return
	 */
	@RequestMapping(BaseMessagePath.DELETE)
	@ResponseBody
	public String delete(BaseMessage deleteBean) {
		JSONObject json = new JSONObject();
		try {
			if (null == deleteBean) {
				throw new IllegalArgumentException("删除对象为空");
			}
			if (StringUtils.isBlank(deleteBean.getId())) {
				throw new IllegalArgumentException("编号为空");
			}
			baseMessageMapper.deleteBaseMessage(deleteBean);
			json.put("result", SUCCESS);

		} catch (Exception e) {
			logger.error("删除异常", e);
			json.put("result", "FAILURE");
			json.put("message", e.getMessage());
		}
		return json.toJSONString();
	}

	/**
	 * 批量删除
	 * 
	 * @param settleIds
	 * @return
	 */
	@RequestMapping(BaseMessagePath.BATCH_DELETE)
	@ResponseBody
	public String batchDelete(String deleteIds) {
		JSONObject jsonObject = new JSONObject();
		try {
			if (StringUtils.isBlank(deleteIds)) {
				throw new IllegalArgumentException("删除编号为空");
			}
			String[] idArray = deleteIds.split("\\*");

			for (int i = 0; i < idArray.length; i++) {
				BaseMessage deleteBean = new BaseMessage();
				String id = idArray[i];
				deleteBean.setId(id);
				baseMessageMapper.deleteBaseMessage(deleteBean);
			}
			jsonObject.put("result", "SUCCESS");

		} catch (Exception e) {
			logger.error("删除异常", e);
			jsonObject.put("result", "FAILURE");
			jsonObject.put("message", e.getMessage());
		}
		return jsonObject.toJSONString();
	}

	/**
	 * 信息单个发送
	 * 
	 * @param updateBean
	 * @return
	 */
	@RequestMapping(BaseMessagePath.SINGLE_SEND)
	@ResponseBody
	public String jgkjSingleRecharge(BaseMessage sendBean) {
		JSONObject json = new JSONObject();
		try {
			if (null == sendBean) {
				throw new IllegalArgumentException("发送对象为空");
			}
			if (StringUtils.isBlank(sendBean.getMobile())) {
				throw new IllegalArgumentException("手机号码为空");
			}
			if (StringUtils.isBlank(sendBean.getContent())) {
				throw new IllegalArgumentException("发送内容为空");
			}
			if (StringUtils.isBlank(sendBean.getCustName())) {
				throw new IllegalArgumentException("客户姓名为空");
			}
			if (StringUtils.isBlank(sendBean.getDateData())) {
				throw new IllegalArgumentException("数据日期为空");
			}
			String result = baseMessageService.sendMessage(sendBean);
			if (result.equals(SUCCESS)) {
				json.put("result", result);
			} else {
				json.put("result", result);
				json.put("message", "短信发送失败！");
			}
		} catch (Exception e) {
			logger.error("单个信息发送异常", e);
			json.put("result", "FAILURE");
			json.put("message", e.getMessage());
		}
		return json.toJSONString();
	}

	/**
	 * 批量发送短信
	 * 
	 * @param registerBean
	 * @return
	 */
	@RequestMapping(BaseMessagePath.BATCH_SEND)
	@ResponseBody
	public String establish(String sendIds) {
		JSONObject json = new JSONObject();
		try {
			if (StringUtils.isBlank(sendIds)) {
				throw new IllegalArgumentException("发送编号为空");
			}
			if (Constant.JGKJ_SUCC.equals(sendIds)) {
				BaseMessage queryBean = new BaseMessage();
				queryBean.setStatus(INIT);
				/** 待发送数据 **/
				List<BaseMessage> baseMessageList = baseMessageMapper.selectBaseMessageList(queryBean);
				logger.info("=========待发送数据数量========{}", baseMessageList.size());

				if (baseMessageList.size() > 0) {
					for (BaseMessage waitSendBean : baseMessageList) {
						BatchSendMessageThread batchSendMessageThread = (BatchSendMessageThread) SpringUtils
								.getBean("batchSendMessageThread");
						batchSendMessageThread.setBaseMessage(waitSendBean);
						BaseMessageThreadPool.getInstance().put(batchSendMessageThread);
					}
				}
			} else {
				String[] idArray = sendIds.split("\\*");
				for (int i = 0; i < idArray.length; i++) {
					BaseMessage queryBean = new BaseMessage();
					String id = idArray[i];
					queryBean.setId(id);
					BaseMessage sendBean = baseMessageMapper.selectBaseMessageById(queryBean);
					if (sendBean != null) {
						BatchSendMessageThread batchSendMessageThread = (BatchSendMessageThread) SpringUtils
								.getBean("batchSendMessageThread");
						batchSendMessageThread.setBaseMessage(sendBean);
						BaseMessageThreadPool.getInstance().put(batchSendMessageThread);
					}
				}
			}
			json.put("result", SUCCESS);
		} catch (Exception e) {
			logger.error("批量发送短信异常", e);
			json.put("result", "FAILURE");
			json.put("message", e.getMessage());
		}
		return json.toJSONString();
	}
}
