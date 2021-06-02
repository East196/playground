package com.github.east196.starter.task.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 抽象JOB父类
 * 
 */
public abstract class AbsJob implements Job {
	private static final Logger LOGGER = LoggerFactory.getLogger(AbsJob.class);

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		String name = this.getClass().getName();
		LOGGER.info("Job Start: {}", name);

		try {
			// 业务处理
			process(context);
		} catch (Exception e) {
			LOGGER.info("业务执行异常：{}", e);
		}

		LOGGER.info("Job End: {}", name);
	}

	/**
	 * 业务处理
	 * 
	 * @param context
	 *            JOB上下文
	 */
	protected abstract void process(JobExecutionContext context);
}
