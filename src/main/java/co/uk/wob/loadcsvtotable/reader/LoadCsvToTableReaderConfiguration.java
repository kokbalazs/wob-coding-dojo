package co.uk.wob.loadcsvtotable.reader;

import co.uk.wob.model.Person;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

@Configuration
public class LoadCsvToTableReaderConfiguration {
	
	public String csvPath;
	
	@Bean
	@StepScope
	public FlatFileItemReader<Person> loadCsvToTableReader() {
		return new FlatFileItemReaderBuilder<Person>()
				.name("loadCsvToTableReader")
				.resource(new FileSystemResource(csvPath))
				.linesToSkip(1)
				.delimited()
				.delimiter(DelimitedLineTokenizer.DELIMITER_TAB)
				.names(getNames())
				.targetType(Person.class)
				.build();
	}
	
	private String[] getNames() {
		return new String[]{"last_name", "first_name", "age"};
	}
	
	@BeforeStep
	public void beforeStep(StepExecution stepExecution) {
		csvPath = stepExecution
				.getJobExecution()
				.getExecutionContext()
				.getString("csvPath");
	}
	
}
