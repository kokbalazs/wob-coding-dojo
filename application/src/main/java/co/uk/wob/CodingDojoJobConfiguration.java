package co.uk.wob;

import co.uk.wob.listener.JobListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.job.flow.support.SimpleFlow;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static co.uk.wob.common.CustomExitStatus.FTP_ALLOWED;
import static org.springframework.batch.core.ExitStatus.COMPLETED;

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
	private final Step targetDumpTableStep;
	private final Step loadCsvToTableStep;
	private final Step listingEndpointStep;
	private final Step marketplaceEndpointStep;
	private final Step ftpUploaderStep;
	private final JobListener jobListener;
	
	@Bean
	public Job csvLoaderJob() {
		log.info("Creating Job...");
		return jobBuilderFactory
				.get(applicationName)
				.incrementer(new RunIdIncrementer())
				.start(remoteDumpTableStep)
				.next(remoteLoadStep)
				.on(FTP_ALLOWED.getExitCode()).to(ftpUploaderStep).from(remoteLoadStep)
				.on(COMPLETED.getExitCode()).to(ftpNotAllowed()).from(remoteLoadStep)
				.end()
				.listener(jobListener)
				.build();
	}
	
	private Flow ftpNotAllowed() {
		return new FlowBuilder<SimpleFlow>("smart-ftp-flow")
				.start(targetDumpTableStep)
				.next(loadCsvToTableStep)
				.next(listingEndpointStep)
				.next(marketplaceEndpointStep)
				.end();
	}
	
}
