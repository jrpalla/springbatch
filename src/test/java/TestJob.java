/**
 * 
 */

/**
 * @author jrpalla
 *
 */


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/batch/jobs/job-config.xml"})
public class TestJob {
	
	@Autowired
	private JobLauncherTestUtils launcher;

	@Test
	public void testJob(){
		try {
			JobExecution execution = launcher.launchJob();
			Assert.assertEquals(BatchStatus.COMPLETED, execution.getStatus());
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}
	
/*	@Test
	public void testStep(){
		JobExecution execution = launcher.launchStep("batchStep");
		Assert.assertEquals(BatchStatus.COMPLETED, execution.getStatus());
	}*/
}