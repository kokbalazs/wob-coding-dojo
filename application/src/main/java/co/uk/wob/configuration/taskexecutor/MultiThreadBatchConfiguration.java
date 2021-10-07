package co.uk.wob.configuration.taskexecutor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

@Configuration
@Slf4j
public class MultiThreadBatchConfiguration {
	
	@Value("#{new Integer('${thread-count}')}")
	private Integer concurrencyLimit;
	
	@Bean
	public TaskExecutor multiThreadTaskExecutor() {
		log.info("Concurrency limit: {}", concurrencyLimit);
		SimpleAsyncTaskExecutor asyncTaskExecutor = new SimpleAsyncTaskExecutor("spring-batch-worker-");
		asyncTaskExecutor.setConcurrencyLimit(concurrencyLimit);
		return asyncTaskExecutor;
	}
	
}
