package co.uk.wob.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JobListener extends JobExecutionListenerSupport {
	
	@Override
	public void afterJob(JobExecution jobExecution) {
		log.info("Job finished."); // To demonstrate after job feature.
	}
	
}