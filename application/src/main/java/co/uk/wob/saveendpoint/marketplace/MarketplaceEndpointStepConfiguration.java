package co.uk.wob.saveendpoint.marketplace;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class MarketplaceEndpointStepConfiguration {
	
	private final StepBuilderFactory stepBuilderFactory;
	private final MarketplaceEndpointTasklet tasklet;
	
	@Bean
	public Step marketplaceEndpointStep() {
		return stepBuilderFactory
				.get("marketplaceEndpointStep")
				.tasklet(tasklet)
				.build();
	}
	
}
