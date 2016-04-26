/**
 * 
 */
package com.ansri;

import org.hibernate.SessionFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

/**
 * @author jrpalla
 *
 */
public class InitializingTask implements Tasklet {
	private String table="SourceReport";
	private SessionFactory sessionFactory;
	/* (non-Javadoc)
	 * @see org.springframework.batch.core.step.tasklet.Tasklet#execute(org.springframework.batch.core.StepContribution, org.springframework.batch.core.scope.context.ChunkContext)
	 */
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		if(sessionFactory==null){
			throw new IllegalArgumentException("SessionFactory cannot be null");
		}
		if(table==null){
			throw new IllegalArgumentException("Source Table cannot be null");
		}
		SourceDataManager.getInstance().initializeScrollableResults(sessionFactory, table);
		return RepeatStatus.FINISHED;
	}
	
	public void setTable(String table) {
		this.table = table;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
