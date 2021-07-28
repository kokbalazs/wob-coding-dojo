package co.uk.wob;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class JobConfiguration {
	
	protected final Logger logger = LoggerFactory.getLogger(JobConfiguration.class);
	
	@Value("${spring.application.name}")
	private String applicationName;
	private final JobBuilderFactory jobBuilderFactory;
	private final Step loadCsvToTableStep;
	
	@Bean
	public Job csvLoaderJob() {
		logger.info("Creating Job...");
		return jobBuilderFactory
				.get(applicationName)
				.incrementer(new RunIdIncrementer())
				.start(loadCsvToTableStep)
				.build();
	}
	
}
