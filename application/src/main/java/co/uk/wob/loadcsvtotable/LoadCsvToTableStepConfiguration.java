package co.uk.wob.loadcsvtotable;

import co.uk.wob.loadcsvtotable.reader.LoadCsvToTableReaderConfiguration;
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
	private final LoadCsvToTableReaderConfiguration readerConfiguration;
	
	@Bean
	public Step loadCsvToTableStep() {
		return stepBuilderFactory
				.get("loadCsvToTableStep")
				.<Person, Person>chunk(100)
				.faultTolerant()
				.skipLimit(3)
				.skip(Exception.class)
				.reader(loadCsvToTableReader)
				.processor(loadCsvToTableProcessor)
				.writer(loadCsvToTableWriter)
				.listener(readerConfiguration)
				.build();
	}
	
	
}
