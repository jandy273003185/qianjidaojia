package com.qifenqian.bms.accounting.adjust.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qifenqian.bms.accounting.adjust.bean.AccountingAdjustDetail;
import com.qifenqian.bms.accounting.adjust.bean.AccountingAdjustDetailExample;
import com.qifenqian.bms.accounting.adjust.bean.AccountingAdjustHistoryListBean;
import com.qifenqian.bms.accounting.adjust.bean.AccountingAdjustMain;
import com.qifenqian.bms.accounting.adjust.bean.AccountingAdjustMainExample;
import com.qifenqian.bms.accounting.adjust.bean.AccountingAdjustSingleHistoryListBean;
import com.qifenqian.bms.accounting.adjust.bean.AccountingSingleAdjustDetail;
import com.qifenqian.bms.accounting.adjust.bean.AccountingSingleAdjustDetailExample;
import com.qifenqian.bms.accounting.adjust.dao.AccountingAdjustDetailMapper;
import com.qifenqian.bms.accounting.adjust.dao.AccountingAdjustMainMapper;
import com.qifenqian.bms.accounting.adjust.dao.AccountingSingleAdjustDetailMapper;
import com.qifenqian.bms.platform.web.page.Page;

/**
 * 调账Service事务辅助类
 * 
 * @project sevenpay-bms-web
 * @fileName AdjustServiceSupport.java
 * @author kunwang.li
 * @date 2015年7月25日
 * @memo
 * 
 */
@Service
public class AdjustServiceSupport {

	private static Logger logger = LoggerFactory.getLogger(AdjustServiceSupport.class);

	@Autowired
	private AccountingAdjustMainMapper accountingAdjustMainMapper;

	@Autowired
	private AccountingAdjustDetailMapper accountingAdjustDetailMapper;

	@Autowired
	private AccountingSingleAdjustDetailMapper accountingSingleAdjustDetailMapper;

	// activiti服务组件
	@Autowired
	private RuntimeService runtimeService;

	// activiti服务组件
	@Autowired
	private TaskService taskService;

	public AccountingAdjustMainMapper getAccountingAdjustMainMapper() {
		return accountingAdjustMainMapper;
	}

	public AccountingAdjustDetailMapper getAccountingAdjustDetailMapper() {
		return accountingAdjustDetailMapper;
	}

	public AccountingSingleAdjustDetailMapper getAccountingSingleAdjustDetailMapper() {
		return accountingSingleAdjustDetailMapper;
	}

	public RuntimeService getRuntimeService() {
		return runtimeService;
	}

	public TaskService getTaskService() {
		return taskService;
	}

	public void setAccountingAdjustMainMapper(AccountingAdjustMainMapper accountingAdjustMainMapper) {
		this.accountingAdjustMainMapper = accountingAdjustMainMapper;
	}

	public void setAccountingAdjustDetailMapper(AccountingAdjustDetailMapper accountingAdjustDetailMapper) {
		this.accountingAdjustDetailMapper = accountingAdjustDetailMapper;
	}

	public void setAccountingSingleAdjustDetailMapper(AccountingSingleAdjustDetailMapper accountingSingleAdjustDetailMapper) {
		this.accountingSingleAdjustDetailMapper = accountingSingleAdjustDetailMapper;
	}

	public void setRuntimeService(RuntimeService runtimeService) {
		this.runtimeService = runtimeService;
	}

	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}

	/**
	 * 调账经办服务
	 * 
	 * @param mBean
	 * @param dBean
	 * @return 业务编号
	 * 
	 */
	public void handle(AccountingAdjustMain mBean, List<AccountingAdjustDetail> dlist) {

		logger.info("保存调账业务数据 opId[{}]", mBean.getOpId());

		accountingAdjustMainMapper.insert(mBean);
		for (AccountingAdjustDetail dbean : dlist) {
			accountingAdjustDetailMapper.insert(dbean);
		}

		logger.debug("初始化工作流程");
		Map<String, Object> paras = new HashMap<String, Object>();
		paras.put("opId", mBean.getOpId());
		paras.put("handlerUid", mBean.getHandlerUid());
		Authentication.setAuthenticatedUserId(mBean.getHandlerUid());
		ProcessInstance pi = runtimeService.startProcessInstanceByKey("process.accounting.adjust", paras);
		logger.debug("ProcessInstance: [{}]", pi.getProcessInstanceId());

		logger.debug("驱动工作流完成第一个task");
		Task task = taskService.createTaskQuery().processInstanceId(pi.getProcessInstanceId()).taskDefinitionKey("usertask1").taskAssignee(mBean.getHandlerUid()).singleResult();
		if (null == task) {
			throw new RuntimeException("此任务已被处理, 请刷新任务列表.");
		}
		taskService.addComment(task.getId(), task.getProcessInstanceId(), mBean.getMemo());
		taskService.complete(task.getId());

		logger.debug("更新ProcessInstanceId");
		AccountingAdjustMain updateMBean = new AccountingAdjustMain();
		updateMBean.setOpId(mBean.getOpId());
		updateMBean.setProcessInstanceId(pi.getProcessInstanceId());
		accountingAdjustMainMapper.updateByPrimaryKeySelective(updateMBean);

	}

	/**
	 * 单边调账经办
	 * 
	 * @param mBean
	 * @param dlist
	 */
	public void handleSingle(AccountingAdjustMain mBean, List<AccountingSingleAdjustDetail> dlist) {

		logger.info("保存调账业务数据 opId[{}]", mBean.getOpId());

		accountingAdjustMainMapper.insert(mBean);
		for (AccountingSingleAdjustDetail dbean : dlist) {
			accountingSingleAdjustDetailMapper.insert(dbean);
		}

		logger.debug("初始化工作流程");
		Map<String, Object> paras = new HashMap<String, Object>();
		paras.put("opId", mBean.getOpId());
		paras.put("handlerUid", mBean.getHandlerUid());
		Authentication.setAuthenticatedUserId(mBean.getHandlerUid());
		ProcessInstance pi = runtimeService.startProcessInstanceByKey("process.accounting.adjust", paras);
		logger.debug("ProcessInstance: [{}]", pi.getProcessInstanceId());

		logger.debug("驱动工作流完成第一个task");
		Task task = taskService.createTaskQuery().processInstanceId(pi.getProcessInstanceId()).taskDefinitionKey("usertask1").taskAssignee(mBean.getHandlerUid()).singleResult();
		if (null == task) {
			throw new RuntimeException("此任务已被处理, 请刷新任务列表.");
		}
		taskService.addComment(task.getId(), task.getProcessInstanceId(), mBean.getMemo());
		taskService.complete(task.getId());

		logger.debug("更新ProcessInstanceId");
		AccountingAdjustMain updateMBean = new AccountingAdjustMain();
		updateMBean.setOpId(mBean.getOpId());
		updateMBean.setProcessInstanceId(pi.getProcessInstanceId());
		accountingAdjustMainMapper.updateByPrimaryKeySelective(updateMBean);

	}

	/**
	 * 查询指定人的待修改调账列表
	 * 
	 * @param handleUid
	 * @return
	 */
	public List<AccountingAdjustMain> editList(String handleUid, int firstResult, int maxResults) {

		logger.debug("查询待修改的调账列表handleUid[{}], firstResult[{}], maxResults[{}]", handleUid, firstResult, maxResults);
		List<Task> tasks = taskService.createTaskQuery().includeProcessVariables().processDefinitionKey("process.accounting.adjust").taskDefinitionKey("usertask4").taskAssignee(handleUid).orderByTaskId().asc().listPage(firstResult, maxResults);
		if (null == tasks || tasks.size() == 0) {
			return null;
		} else {
			List<String> opIds = new ArrayList<String>(tasks.size());
			for (Task task : tasks) {
				opIds.add(task.getProcessVariables().get("opId").toString());
			}

			AccountingAdjustMainExample example = new AccountingAdjustMainExample();
			example.createCriteria().andOpIdIn(opIds);
			return accountingAdjustMainMapper.selectByExample(example);
		}

	}

	/**
	 * 获取待核查列表
	 * 
	 * @param checkUid
	 * @param firstResult
	 * @param maxResults
	 * @return
	 */
	public List<AccountingAdjustMain> checkList(String checkUid, int firstResult, int maxResults) {

		logger.debug("查询待核查的调账列表checkUid[{}], firstResult[{}], maxResults[{}]", checkUid, firstResult, maxResults);

		// 经办人如果也有审批的角色, 不能审批自己的经办件
		List<Task> tasks = taskService.createTaskQuery().includeProcessVariables().processDefinitionKey("process.accounting.adjust").taskDefinitionKey("usertask2").processVariableValueNotEquals("handlerUid", checkUid).orderByTaskId().asc().listPage(firstResult, maxResults);

		if (null == tasks || tasks.size() == 0) {
			return null;
		} else {
			List<String> opIds = new ArrayList<String>(tasks.size());
			for (Task task : tasks) {
				opIds.add(task.getProcessVariables().get("opId").toString());
			}

			AccountingAdjustMainExample example = new AccountingAdjustMainExample();
			example.createCriteria().andOpIdIn(opIds);
			return accountingAdjustMainMapper.selectByExample(example);
		}

	}

	/**
	 * 查询总经理审批列表
	 * 
	 * @param checkUid
	 * @param firstResult
	 * @param maxResults
	 * @return
	 */
	public List<AccountingAdjustMain> approveList(String checkUid, int firstResult, int maxResults) {

		logger.debug("查询待核查的调账列表checkUid[{}], firstResult[{}], maxResults[{}]", checkUid, firstResult, maxResults);

		// 经办人如果也有审批的角色, 不能审批自己的经办件
		List<Task> tasks = taskService.createTaskQuery().includeProcessVariables().processDefinitionKey("process.accounting.adjust").taskDefinitionKey("usertask3").processVariableValueNotEquals("handlerUid", checkUid).orderByTaskId().asc().listPage(firstResult, maxResults);

		if (null == tasks || tasks.size() == 0) {
			return null;
		} else {
			List<String> opIds = new ArrayList<String>(tasks.size());
			for (Task task : tasks) {
				opIds.add(task.getProcessVariables().get("opId").toString());
			}

			AccountingAdjustMainExample example = new AccountingAdjustMainExample();
			example.createCriteria().andOpIdIn(opIds);
			return accountingAdjustMainMapper.selectByExample(example);
		}

	}

	/**
	 * 通过流程实例ID查询AccountingAdjustMain
	 * 
	 * @param processInstanceId
	 * @return AccountingAdjustMain
	 */
	public AccountingAdjustMain queryAccountingAdjustMain(String processInstanceId) {
		AccountingAdjustMainExample example = new AccountingAdjustMainExample();
		example.createCriteria().andProcessInstanceIdEqualTo(processInstanceId);
		List<AccountingAdjustMain> list = accountingAdjustMainMapper.selectByExample(example);
		if (null != list && list.size() > 0) {
			return list.get(0);
		} else if (null == list || list.size() == 0) {
			return null;
		} else {
			throw new RuntimeException("查询到过多的匹配项");
		}
	}

	/**
	 * 查询分录列表
	 * 
	 * @param opId
	 * @return 返回调账分录列表
	 */
	public List<AccountingAdjustDetail> queryEntryList(String opId) {
		logger.debug("查询调账分录, opId[{}]", opId);
		AccountingAdjustDetailExample example = new AccountingAdjustDetailExample();
		example.createCriteria().andOpIdEqualTo(opId);
		example.setOrderByClause(" entry_id ");
		return accountingAdjustDetailMapper.selectByExample(example);
	}

	/**
	 * 查询单边调账分录
	 * 
	 * @param opId
	 * @return
	 */
	public List<AccountingSingleAdjustDetail> querySingleEntryList(String opId) {
		logger.debug("查询调账分录, opId[{}]", opId);
		AccountingSingleAdjustDetailExample example = new AccountingSingleAdjustDetailExample();
		example.createCriteria().andOpIdEqualTo(opId);
		example.setOrderByClause(" entry_id ");
		return accountingSingleAdjustDetailMapper.selectByExample(example);
	}

	/**
	 * 查询审批明细信息
	 * 
	 * @param opId
	 * @return
	 */
	public List<Comment> queryApproveCommentList(String opId) {

		logger.debug("查询调账记录审批列表, opId[{}]", opId);
		AccountingAdjustMain mBean = accountingAdjustMainMapper.selectByPrimaryKey(opId);
		List<Comment> list = taskService.getProcessInstanceComments(mBean.getProcessInstanceId());

		return list;
	}

	/**
	 * 查询审批明细信息
	 * 
	 * @param opId
	 * @return 返回各级审批信息列表
	 */
	public List<Comment> queryApproveCommentList(String opId, String processInstanceId) {

		logger.debug("查询调账记录审批列表, opId[{}]", opId);

		List<Comment> list = taskService.getProcessInstanceComments(processInstanceId);

		return list;
	}

	/**
	 * 更新调账记录后再次提交复核
	 * 
	 * @param mBean
	 * @param dlist
	 */
	public void update4Recheck(AccountingAdjustMain mBean, List<AccountingAdjustDetail> dlist) {

		logger.debug("更新业务数据");
		AccountingAdjustMain accountingAdjustMain = accountingAdjustMainMapper.selectByPrimaryKey(mBean.getOpId());

		AccountingAdjustDetailExample example = new AccountingAdjustDetailExample();
		example.createCriteria().andOpIdEqualTo(mBean.getOpId());
		accountingAdjustDetailMapper.deleteByExample(example);
		for (AccountingAdjustDetail dbean : dlist) {
			accountingAdjustDetailMapper.insert(dbean);
		}

		logger.debug("驱动工作流");
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("resubmit", "true");
		Task task = taskService.createTaskQuery().processInstanceId(accountingAdjustMain.getProcessInstanceId()).taskDefinitionKey("usertask4").singleResult();
		if (null == task) {
			throw new RuntimeException("此任务已被处理, 请刷新任务列表.");
		}
		Authentication.setAuthenticatedUserId(mBean.getHandlerUid());
		taskService.addComment(task.getId(), task.getProcessInstanceId(), "经办修改重新提交");
		taskService.complete(task.getId(), variables);
	}

	/**
	 * 单边调账更新记录后再次提交复核
	 * 
	 * @param mBean
	 * @param dlist
	 */
	public void update4RecheckSingle(AccountingAdjustMain mBean, List<AccountingSingleAdjustDetail> dlist) {

		logger.debug("更新业务数据");
		AccountingAdjustMain accountingAdjustMain = accountingAdjustMainMapper.selectByPrimaryKey(mBean.getOpId());

		AccountingSingleAdjustDetailExample example = new AccountingSingleAdjustDetailExample();
		example.createCriteria().andOpIdEqualTo(mBean.getOpId());
		accountingSingleAdjustDetailMapper.deleteByExample(example);
		for (AccountingSingleAdjustDetail dbean : dlist) {
			accountingSingleAdjustDetailMapper.insert(dbean);
		}

		logger.debug("驱动工作流");
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("resubmit", "true");
		Task task = taskService.createTaskQuery().processInstanceId(accountingAdjustMain.getProcessInstanceId()).taskDefinitionKey("usertask4").singleResult();
		if (null == task) {
			throw new RuntimeException("此任务已被处理, 请刷新任务列表.");
		}
		Authentication.setAuthenticatedUserId(mBean.getHandlerUid());
		taskService.addComment(task.getId(), task.getProcessInstanceId(), "经办修改重新提交");
		taskService.complete(task.getId(), variables);
	}

	/**
	 * 删除调账记录(并不是物理删除, 只是改变状态)
	 * 
	 * @param opId
	 */
	public void delete(String opId) {

		AccountingAdjustMain mBean = accountingAdjustMainMapper.selectByPrimaryKey(opId);

		AccountingAdjustMain updateBean = new AccountingAdjustMain();
		updateBean.setOpId(opId);
		updateBean.setDeleteDatetime(new Date());
		updateBean.setFinishedFlag("2");
		accountingAdjustMainMapper.updateByPrimaryKeySelective(updateBean);

		logger.debug("驱动工作流");
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("resubmit", "false");
		Task task = taskService.createTaskQuery().processInstanceId(mBean.getProcessInstanceId()).taskDefinitionKey("usertask4").singleResult();
		if (null == task) {
			throw new RuntimeException("此任务已被处理, 请刷新任务列表.");
		}
		Authentication.setAuthenticatedUserId(mBean.getHandlerUid());
		taskService.addComment(task.getId(), mBean.getProcessInstanceId(), "删除调账记录");
		taskService.complete(task.getId(), variables);
	}

	/**
	 * 将待复核任务指定到特定的人名下<br>
	 * 
	 * @param opId
	 * @param checkerUid
	 */
	public void claim4Check(String opId, String checkerUid) {
		AccountingAdjustMain mBean = accountingAdjustMainMapper.selectByPrimaryKey4Lock(opId);
		Task task = taskService.createTaskQuery().processInstanceId(mBean.getProcessInstanceId()).taskDefinitionKey("usertask2").singleResult();
		if (null == task) {
			throw new RuntimeException("此任务已被处理, 请刷新任务列表.");
		} else if (StringUtils.isNotBlank(task.getAssignee()) && !StringUtils.equals(checkerUid, task.getAssignee())) {
			throw new RuntimeException("此任务已在其他审批人员名下, 您无权审批");
		} else if (StringUtils.isBlank(task.getAssignee())){
			taskService.claim(task.getId(), checkerUid);
		}
	}

	/**
	 * 复核人员复核
	 * 
	 * @param opId
	 *            业务编号
	 * @param checkResult
	 *            复核结果
	 * @param memo
	 *            备注
	 * @param checkerUid
	 *            复核人员UID
	 */
	public void check(String opId, boolean checkResult, boolean approve, String memo, String checkerUid) {

		logger.info("复核调账业务 opId[{}], checkResult[{}]", opId, checkResult);

		AccountingAdjustMain mBean = accountingAdjustMainMapper.selectByPrimaryKey(opId);

		AccountingAdjustMain updateBean = new AccountingAdjustMain();
		updateBean.setOpId(opId);
		updateBean.setCheckerUid(checkerUid);
		updateBean.setCheckDatetime(new Date());
		updateBean.setFinishedFlag((checkResult && !approve) ? "1" : "0");
		accountingAdjustMainMapper.updateByPrimaryKeySelective(updateBean);

		logger.debug("驱动工作流");
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("checkPass", checkResult);
		variables.put("approve", approve);
		Task task = taskService.createTaskQuery().processInstanceId(mBean.getProcessInstanceId()).taskDefinitionKey("usertask2").taskAssignee(checkerUid).singleResult();
		if (null == task) {
			throw new RuntimeException("此任务已被处理, 请刷新任务列表.");
		}
		// 给comment增加UserId(此设置仅仅在线程中有效)
		Authentication.setAuthenticatedUserId(checkerUid);

		taskService.addComment(task.getId(), mBean.getProcessInstanceId(), memo);
		taskService.complete(task.getId(), variables);
	}

	public void claim4Approve(String opId, String checkerUid) {
		AccountingAdjustMain mBean = accountingAdjustMainMapper.selectByPrimaryKey4Lock(opId);
		Task task = taskService.createTaskQuery().processInstanceId(mBean.getProcessInstanceId()).taskDefinitionKey("usertask3").singleResult();
		if (null == task) {
			throw new RuntimeException("此任务已被处理, 请刷新任务列表.");
		} else if (StringUtils.isNotBlank(task.getAssignee())&& !StringUtils.equals(checkerUid, task.getAssignee())) {
			throw new RuntimeException("此任务已在其他审批人员名下, 您无权审批");
		} else if (StringUtils.isBlank(task.getAssignee())){
			taskService.claim(task.getId(), checkerUid);
		}
	}
	
	/**
	 * 总经理审批
	 * 
	 * @param opId
	 *            业务编号
	 * @param checkResult
	 *            审批结果
	 * @param memo
	 *            备注
	 * @param checkerUid
	 *            总经理UID
	 */
	public void approve(String opId, boolean checkResult, String memo, String checkerUid) {

		logger.info("总经理审批调账业务 opId[{}], checkResult[{}]", opId, checkResult);

		AccountingAdjustMain mBean = accountingAdjustMainMapper.selectByPrimaryKey(opId);

		AccountingAdjustMain updateBean = new AccountingAdjustMain();
		updateBean.setOpId(opId);
		updateBean.setCheckerUid(checkerUid);
		updateBean.setCheckDatetime(new Date());
		updateBean.setFinishedFlag(checkResult ? "1" : "0");
		accountingAdjustMainMapper.updateByPrimaryKeySelective(updateBean);

		logger.debug("驱动工作流");
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("checkPass", checkResult);
		Task task = taskService.createTaskQuery().processInstanceId(mBean.getProcessInstanceId()).taskDefinitionKey("usertask3").taskAssignee(checkerUid).singleResult();
		if (null == task) {
			throw new RuntimeException("此任务已被处理, 请刷新任务列表.");
		}
		// 给comment增加UserId(此设置仅仅在线程中有效)
		Authentication.setAuthenticatedUserId(checkerUid);

		taskService.addComment(task.getId(), mBean.getProcessInstanceId(), memo);
		taskService.complete(task.getId(), variables);
	}

	@Page
	public List<AccountingAdjustHistoryListBean> historyList(AccountingAdjustHistoryListBean listBean) {
		return accountingAdjustMainMapper.selectHistoryList(listBean);
	}

	public List<AccountingAdjustHistoryListBean> historyListReport(AccountingAdjustHistoryListBean listBean) {
		return accountingAdjustMainMapper.selectHistoryList(listBean);
	}

	@Page
	public List<AccountingAdjustSingleHistoryListBean> singleHistoryList(AccountingAdjustSingleHistoryListBean listBean) {
		return accountingAdjustMainMapper.selectSingleHistoryList(listBean);
	}

	public List<AccountingAdjustSingleHistoryListBean> singleHistoryListReport(AccountingAdjustSingleHistoryListBean listBean) {
		return accountingAdjustMainMapper.selectSingleHistoryList(listBean);
	}
}
