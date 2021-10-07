package co.uk.wob.ftp;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class FtpUploaderStepConfiguration {
	
	private final StepBuilderFactory stepBuilderFactory;
	private final FtpUploaderTasklet ftpUploaderTasklet;
	
	@Bean
	public Step ftpUploaderStep() {
		return stepBuilderFactory
				.get("ftpUploaderStep")
				.tasklet(ftpUploaderTasklet)
				.listener(ftpUploaderTasklet)
				.build();
	}
	
}