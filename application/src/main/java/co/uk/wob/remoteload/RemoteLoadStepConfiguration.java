package co.uk.wob.remoteload;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class RemoteLoadStepConfiguration {
	
	private final StepBuilderFactory stepBuilderFactory;
	private final Tasklet remoteLoadTasklet;
	
	@Bean
	public Step remoteLoadStep() {
		return stepBuilderFactory
				.get("remoteLoadStep")
				.tasklet(remoteLoadTasklet)
				.listener(remoteLoadTasklet)
				.build();
	}
	
}
