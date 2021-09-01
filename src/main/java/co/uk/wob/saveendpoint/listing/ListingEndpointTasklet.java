package co.uk.wob.saveendpoint.listing;

import co.uk.wob.repository.SaveEndpointRepository;
import co.uk.wob.saveendpoint.client.EndpointClient;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ListingEndpointTasklet implements Tasklet {
	
	private final EndpointClient client;
	private final SaveEndpointRepository repository;
	
	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
		client.listingStatus().forEach(repository::storeListingStatusToDatabase);
		return RepeatStatus.FINISHED;
	}
}
