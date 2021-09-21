package co.uk.wob;

import co.uk.wob.configuration.CustomBatchConfigurer;
import co.uk.wob.configuration.IntegrationTestActiveProfilesResolver;
import co.uk.wob.configuration.JobLauncherConfiguration;
import co.uk.wob.repository.RemoteTestRepository;
import org.checkerframework.checker.units.qual.A;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ActiveProfiles(resolver = IntegrationTestActiveProfilesResolver.class)
@SpringBootTest(classes = CustomBatchConfigurer.class)
@DirtiesContext
@ContextConfiguration(classes = {CodingDojoApplication.class, CodingDojoJobConfiguration.class, JobLauncherConfiguration.class},
		initializers = ConfigFileApplicationContextInitializer.class)
public class CodingDojoIntegrationTest {
	
	@MockBean
	@Qualifier("ftpUploaderTasklet")
	private Tasklet ftpUploaderTasklet;
	
	@Autowired
	private JobLauncherTestUtils jobLauncherTestUtils;
	
	@Autowired
	private RemoteTestRepository repository;
	
	@Before
	public void init() throws Exception {
		when(ftpUploaderTasklet.execute(any(), any())).thenReturn(RepeatStatus.FINISHED);
	}
	
	@Test
	public void codingDojoIntegrationTest() throws Exception {
		JobParameters jobParameters = jobLauncherTestUtils.getUniqueJobParameters();
		JobExecution jobExecution = jobLauncherTestUtils.launchJob(jobParameters);
		
		assertEquals(BatchStatus.COMPLETED, jobExecution.getStatus());
		assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
	}
	
}
