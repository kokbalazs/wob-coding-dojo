package co.uk.wob.saveendpoint.marketplace;

import co.uk.wob.repository.SaveEndpointRepository;
import co.uk.wob.saveendpoint.client.EndpointClient;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MarketplaceEndpointTasklet implements Tasklet {
	
	private final SaveEndpointRepository repository;
	private final EndpointClient client;
	
	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
		client.marketplace().forEach(repository::storeMarketplaceToDatabase);
		return RepeatStatus.FINISHED;
	}
	
}
