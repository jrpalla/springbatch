/**
 * 
 */
package com.ansri;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @author jrpalla
 *
 */
public class FinalizingTask implements Tasklet, ApplicationContextAware {

	private ThreadPoolTaskExecutor threadPoolTaskExecutor;

	/* (non-Javadoc)
	 * @see org.springframework.batch.core.step.tasklet.Tasklet#execute(org.springframework.batch.core.StepContribution, org.springframework.batch.core.scope.context.ChunkContext)
	 */
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		SourceDataManager.getInstance().closeScrollableResults();
		threadPoolTaskExecutor.shutdown();
		return RepeatStatus.FINISHED;
	}

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.threadPoolTaskExecutor=(ThreadPoolTaskExecutor)applicationContext.getBean(ThreadPoolTaskExecutor.class);
		
		
	}

}
