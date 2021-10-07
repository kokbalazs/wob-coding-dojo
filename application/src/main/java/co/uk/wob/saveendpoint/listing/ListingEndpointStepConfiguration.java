package co.uk.wob.saveendpoint.listing;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ListingEndpointStepConfiguration {
	
	private final StepBuilderFactory stepBuilderFactory;
	private final ListingEndpointTasklet listingEndpointTasklet;
	
	@Bean
	public Step listingEndpointStep() {
		return stepBuilderFactory
				.get("listingEndpointStep")
				.tasklet(listingEndpointTasklet)
				.build();
	}
	
}
