package com.github.east196.starter.task.job;

import org.quartz.JobExecutionContext;

/**
 * 每小时执行
 *
 */
public class EveryHourJob extends AbsJob {

	@Override
	protected void process(JobExecutionContext context) {
		System.out.println("每小时任务");
	}

}
