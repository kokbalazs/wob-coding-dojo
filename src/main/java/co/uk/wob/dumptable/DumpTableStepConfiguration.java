package co.uk.wob.dumptable;

import co.uk.wob.model.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
@RequiredArgsConstructor
public class DumpTableStepConfiguration {
	
	private final StepBuilderFactory stepBuilderFactory;
	private final ItemReader<Person> dumpTableItemReader;
	private final ItemWriter<Person> dumpTableItemWriter;
	
//	@Bean
	public Step dumpTableStep() {
		return stepBuilderFactory
				.get("dumpTableStep")
				.<Person, Person>chunk(100)
				.faultTolerant()
				.skipLimit(3)
				.skip(Exception.class)
				.reader(dumpTableItemReader)
				.writer(dumpTableItemWriter)
				.build();
	}
	
}
