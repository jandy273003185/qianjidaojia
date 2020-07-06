package com.qifenqian.bms.sms.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class BaseMessageThreadPool {
	private static BaseMessageThreadPool instance = null;

	/** 线程池 **/
	private ThreadPoolExecutor pool = null;

	/** 线程池维护线程的最大数量 **/
	private final static int MAXIMUM_POOL_SIZE = 10;

	/** 线程池维护线程的最少数量 **/
	private final static int CORE_POOL_SIZE = 5;

	/** 线程池维护线程所允许的空闲时间 **/
	private final static int KEEP_ALIVE_TIME = 10;

	/** 线程队列大小 **/
	private final static int WORK_QUEUE = 15;

	/**
	 * 线程池对拒绝任务的处理策略:直接调用run方法并且阻塞执行
	 **/
	public BaseMessageThreadPool() {
		pool = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.SECONDS,
				new ArrayBlockingQueue<Runnable>(WORK_QUEUE), new ThreadPoolExecutor.CallerRunsPolicy());
	}

	/**
	 * 获取ThreadSendPool单例
	 */
	public static synchronized BaseMessageThreadPool getInstance() {
		if (null == instance) {
			instance = new BaseMessageThreadPool();
		}
		return instance;
	}

	public ThreadPoolExecutor getPool() {
		return pool;
	}

	/**
	 * 将指定线程放入线程池
	 */
	public void put(Thread thread) {
		pool.execute(thread);
	}

	/**
	 * 停止线程池的运行
	 */
	public void stop() {
		pool.shutdown();
	}

	public int getMaximumPoolSize() {
		return MAXIMUM_POOL_SIZE;
	}
}
