package co.uk.wob;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class CodingDojoJobConfiguration {
	
	@Value("${spring.application.name}")
	private String applicationName;
	private final JobBuilderFactory jobBuilderFactory;
	private final Step remoteDumpTableStep;
	private final Step remoteLoadStep;
//	private final Step dumpTableStep;
//	private final Step loadCsvToTableStep;
	
	@Bean
	public Job csvLoaderJob() {
		log.info("Creating Job...");
		return jobBuilderFactory
				.get(applicationName)
				.incrementer(new RunIdIncrementer())
				.start(remoteDumpTableStep)
				.next(remoteLoadStep)
//				.next(dumpTableStep)
//				.next(loadCsvToTableStep)
				.build();
	}
	
}
