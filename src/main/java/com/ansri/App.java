/**
 * 
 */
package com.ansri;

/**
 * @author jrpalla
 *
 */
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

public class App {
  public static void main(String[] args) {

	String[] springConfig  = 
		{	
			"spring/batch/jobs/job-config.xml" 
		};
		
	ApplicationContext context = 
			new ClassPathXmlApplicationContext(springConfig);
		
	JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
	//Job job = (Job) context.getBean("paginatedJob");
	Job job = (Job) context.getBean("tableDataLoadJob");
	

	
	try {

		long time1=System.currentTimeMillis();
		JobExecution execution = jobLauncher.run(job, new JobParameters());
		long time2=System.currentTimeMillis();
		long timeTaken=(time2-time1)/1000;
		System.out.println("Exit Status : " + execution.getStatus()+ " time taken "+ timeTaken +" secs");
		/*execution = jobLauncher.run(job, new JobParametersBuilder()
				  .addLong("time",System.currentTimeMillis()).toJobParameters());
		System.out.println("Exit Status : " + execution.getStatus());*/
	/*	ThreadPoolTaskExecutor threadPoolTaskExecutor=(ThreadPoolTaskExecutor)context.getBean(ThreadPoolTaskExecutor.class);
		threadPoolTaskExecutor.shutdown();*/
	
	} catch (Exception e) {
		e.printStackTrace();
	}

	System.out.println("Done");

  }
}
