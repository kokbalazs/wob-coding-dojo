package co.uk.wob.step;

import co.uk.wob.model.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class LoadCsvToTableStepConfiguration {
	
	private final StepBuilderFactory stepBuilderFactory;
	private final ItemReader<Person> loadCsvToTableReader;
	private final ItemProcessor<Person, Person> loadCsvToTableProcessor;
	private final ItemWriter<Person> loadCsvToTableWriter;
	
	@Bean
	public Step loadCsvToTableStep() {
		return stepBuilderFactory
				.get("loadCsvToTableStep")
				.<Person, Person>chunk(100)
				.reader(loadCsvToTableReader)
				.processor(loadCsvToTableProcessor)
				.writer(loadCsvToTableWriter)
				.build();
	}
	
	
}
