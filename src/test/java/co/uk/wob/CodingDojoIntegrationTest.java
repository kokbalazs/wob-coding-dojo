package co.uk.wob;

import co.uk.wob.configuration.CustomBatchConfigurer;
import co.uk.wob.configuration.JobLauncherConfiguration;
import co.uk.wob.repository.RemoteTestRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.*;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.JobRepositoryTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBatchTest
@EnableAutoConfiguration
@ContextConfiguration(classes = {CustomBatchConfigurer.class, CodingDojoJobConfiguration.class, CodingDojoApplication.class})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class CodingDojoIntegrationTest {
//
//	@MockBean
//	@Qualifier("ftpUploaderTasklet")
//	private Tasklet ftpUploaderTasklet;
	
	@Autowired
	private JobLauncherTestUtils jobLauncherTestUtils;
	
	@Autowired
	private JobRepositoryTestUtils jobRepositoryTestUtils;
	
	@After
	public void cleanUp() {
		jobRepositoryTestUtils.removeJobExecutions();
	}
	
	private JobParameters defaultJobParameters() {
		JobParametersBuilder paramsBuilder = new JobParametersBuilder();
		paramsBuilder.addString("file.input", "TEST_INPUT");
		paramsBuilder.addString("file.output", "TEST_OUTPUT");
		return paramsBuilder.toJobParameters();
	}
	
	
//	@Before
//	public void init() throws Exception {
//		when(ftpUploaderTasklet.execute(any(), any())).thenReturn(RepeatStatus.FINISHED);
//	}
	
	@Test
	public void codingDojoIntegrationTest() throws Exception {
		JobParameters jobParameters = jobLauncherTestUtils.getUniqueJobParameters();
		JobExecution jobExecution = jobLauncherTestUtils.launchJob(jobParameters);
		
		assertEquals(BatchStatus.COMPLETED, jobExecution.getStatus());
		assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
	}
	
}
