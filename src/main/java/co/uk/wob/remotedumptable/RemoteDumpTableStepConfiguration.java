package co.uk.wob.remotedumptable;

import co.uk.wob.remotedumptable.writer.RemoteDumpTableWriterConfiguration;
import co.uk.wob.model.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class RemoteDumpTableStepConfiguration {
	
	private final StepBuilderFactory stepBuilderFactory;
	private final ItemReader<Person> dumpTableItemReader;
	private final ItemWriter<Person> dumpTableItemWriter;
	private final RemoteDumpTableWriterConfiguration writerConfiguration;
	
	@Bean
	public Step remoteDumpTableStep() {
		return stepBuilderFactory
				.get("remoteDumpTableStep")
				.<Person, Person>chunk(100)
				.faultTolerant()
				.skipLimit(3)
				.skip(Exception.class)
				.reader(dumpTableItemReader)
				.writer(dumpTableItemWriter)
				.listener(writerConfiguration)
				.build();
	}
	
}
