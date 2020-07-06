package com.qifenqian.bms.platform.web.scheduler.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.platform.utils.SequenceUtils;
import com.qifenqian.bms.platform.web.scheduler.bean.SchedulerJob;
import com.qifenqian.bms.platform.web.scheduler.dao.SchedulerJobDao;
import com.qifenqian.bms.platform.web.scheduler.mapper.SchedulerJobMapper;
import com.qifenqian.bms.platform.web.schedulerLog.bean.SchedulerLog;
import com.qifenqian.bms.platform.web.schedulerLog.mapper.SchedulerLogMapper;
import com.qifenqian.bms.platform.third.scheduler.ISchedulerServlet;
/**
 * 任务调度配置
 * @author pc
 *
 */
@Service
public class SchedulerJobService {
	@Autowired
	private SchedulerJobMapper schedulerJobMapper;
	@Autowired
	private SchedulerJobDao schedulerJobDao;
	@Autowired
	private SchedulerLogMapper schedulerLogMapper;
	
	private Logger logger = LoggerFactory.getLogger(SchedulerJobService.class);
	
	/**
	 * 查询所有任务
	 * @param job
	 * @return
	 */
	public List<SchedulerJob> seleteSchedulerJob(SchedulerJob job){
		return schedulerJobDao.seleteSchedulerJob(job);
	}

	public void addSchedulerJob(SchedulerJob job) {
		if (null == job) {
			throw new IllegalArgumentException("任务对象为空");
		}
		if (StringUtils.isEmpty(job.getJobName())) {
			throw new IllegalArgumentException("任务名称为空");
		}
		if (StringUtils.isEmpty(job.getHostName())) {
			throw new IllegalArgumentException("端口名称为空");
		}
		if (StringUtils.isEmpty(job.getCron())) {
			throw new IllegalArgumentException("执行时间为空");
		}
		if (StringUtils.isEmpty(job.getClassPath())) {
			throw new IllegalArgumentException("执行类路径为空");
		}

		// 保存
		schedulerJobMapper.addSchedulerJob(job);		
	}
	
	/**
	 *删除
	 * 
	 */
	public void deleteSchedulerJob(SchedulerJob job) {
		
		if (StringUtils.isEmpty(job.getId())) {
			throw new IllegalArgumentException("任务id为空");
		}
		if (StringUtils.isEmpty(job.getLastUpdateUser())) {
			throw new IllegalArgumentException("任务id为空");
		}
		
		schedulerJobMapper.deleteSchedulerJob(job);
		
	}
	
	/***
	 * 修改
	 * @param job
	 */
	public void updateSchedulerJob(SchedulerJob job) {
		if (null == job) {
			throw new IllegalArgumentException("任务对象为空");
		}
		if (StringUtils.isEmpty(job.getJobName())) {
			throw new IllegalArgumentException("任务名称为空");
		}
		if (StringUtils.isEmpty(job.getHostName())) {
			throw new IllegalArgumentException("端口名称为空");
		}
		if (StringUtils.isEmpty(job.getCron())) {
			throw new IllegalArgumentException("执行时间为空");
		}
		if (StringUtils.isEmpty(job.getClassPath())) {
			throw new IllegalArgumentException("执行类路径为空");
		}
		
		schedulerJobMapper.updateSchedulerJob(job);
		
	}
	
	public void execute(SchedulerJob job) {
		SchedulerLog log = new SchedulerLog();
		URL url;
		HttpURLConnection conn = null;
		String line = null;
		String executeFlag = "EXECUTING";
		String Url = null;
		// 若已有参数则直接后面追加
		String parameterAppendType = "?";
		try {
			
			if(StringUtils.isNotEmpty(job.getParameter()) && job.getParameter().contains("?")) {
				parameterAppendType = "&";
			}
			Url = job.getParameter() + parameterAppendType + "parameter=" + job.getExecuteParameter();
			// 从调度配置里面取远程业务地址；
			/*String Url = job.getParameter() + parameterAppendType + "parameter=" + job.getExecuteParameter();
			log.setLogId(SequenceUtils.getSequence("JOB"));
			log.setJobCode(job.getId());
			log.setHostname(job.getHostName());
			log.setParametr(Url);
			log.setExecuteType("HANDLE");
			log.setExecuteFlag(executeFlag);
			// 插入一条记录到调度任务日志记录表
			schedulerLogMapper.addSchedulerLog(log);*/
			
			
			if ((Url == null) || (Url.length() == 0)) {
				line = " execute URL(parameter) is null";
				throw new IllegalArgumentException("execute URL(parameter) is null");
			} 
				
			url = new URL(Url);
			conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setUseCaches(false);
			// 传递的认证参数，在服务端父类里面实现校验,取动态的token(根据各模块动态生成token)
			// conn.setRequestProperty("token", "SSDF#@$$%#");
			conn.connect();
			conn.setConnectTimeout(1000); // 设置连接超时1s
			// conn.setRequestMethod("POST"); // 设定请求方式
			OutputStream outStrm = conn.getOutputStream();
			// 给服务端传递的正文
			outStrm.write("核心模块心跳验证".getBytes());
			outStrm.flush();
			outStrm.close();
			// 获取http调用返回码：200 成功，401未授权，404找不到，500服务端内部异常
			int code = conn.getResponseCode();
			logger.info("MasterJob.java 调用URL：" + Url + ",Code:" + code);
			if (200 == code) {
				InputStream input = conn.getInputStream();
				InputStreamReader isr = new InputStreamReader(input);
				BufferedReader br = new BufferedReader(isr);
				if (null != br) {
					line = br.readLine();
					logger.info("MasterJob.java 调用的返回报文：" + line );
					JSONObject jsonObject = JSONObject.parseObject( line);
					if(null == jsonObject) {
						throw new IllegalArgumentException("执行返回对象jsonObject为空");
					}
					executeFlag = jsonObject.getString(ISchedulerServlet.EXECUTE_FLAG);
					line = jsonObject.getString(ISchedulerServlet.EXECUTE_DESC);
					if(!ISchedulerServlet.EXECUTE_SUCCESS.equals(executeFlag)) {
						throw new IllegalArgumentException(line);
					}
				} 
			} else {
				line = code + ":" + conn.getResponseMessage();
				throw new IllegalArgumentException(code + ":" + conn.getResponseMessage());
			}
			
			executeFlag = ISchedulerServlet.EXECUTE_SUCCESS;
		} catch (Exception e) {
			// 记录异常历史表，方便运维人员分析定位
			logger.error("*****com.sevenpay.scheduler.jobs.utils.MasterJob Exception:", e);			
			executeFlag = ISchedulerServlet.EXECUTE_FAILURE;
			line = e.getMessage();
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				conn.disconnect(); // 中断连接
			}
			if(ISchedulerServlet.EXECUTE_FAILURE.equalsIgnoreCase(executeFlag)){
				
				log.setLogId(SequenceUtils.getSequence("JOB"));
				log.setJobCode(job.getId());
				log.setHostname(job.getHostName());
				log.setParametr(Url);
				log.setExecuteType("HANDLE");
				log.setExecuteFlag(executeFlag);
				
				log.setExecuteDesc(line);
				schedulerLogMapper.addSchedulerLog(log);
			}
			// 更新调度任务日志表记录
			//schedulerLogMapper.updateSchedulerLog(log);
		}
	}

}
