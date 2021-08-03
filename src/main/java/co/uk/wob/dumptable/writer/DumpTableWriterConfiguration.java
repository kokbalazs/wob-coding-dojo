package co.uk.wob.dumptable.writer;

import co.uk.wob.model.Person;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import java.time.LocalDateTime;

@Configuration
@StepScope
public class DumpTableWriterConfiguration {
	
	private String csvPath;
	
	@Bean
	public ItemWriter<Person> dumpTableItemWriter() {
		csvPath = generateCsvPath();
		return new FlatFileItemWriterBuilder<Person>()
				.name("dumpTableItemWriter")
				.resource(new FileSystemResource(csvPath))
				.delimited()
				.delimiter(DelimitedLineTokenizer.DELIMITER_TAB)
				.names(getNames())
				.build();
	}
	
	private String generateCsvPath() {
		return "Persons_" + LocalDateTime.now() + ".csv";
	}
	
	private String[] getNames() {
		return new String[]{"last_name", "first_name", "age"};
	}
	
	
	@AfterStep
	public void afterStep(StepExecution stepExecution) {
		stepExecution
				.getJobExecution()
				.getExecutionContext()
				.put("csvPath", csvPath);
	}
	
}
