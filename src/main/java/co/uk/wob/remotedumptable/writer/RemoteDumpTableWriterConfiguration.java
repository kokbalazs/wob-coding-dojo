package co.uk.wob.remotedumptable.writer;

import co.uk.wob.model.Person;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import java.time.LocalDateTime;

@Configuration
public class RemoteDumpTableWriterConfiguration {
	
	@Value("${file-save-location}")
	private String fileLocation;
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
		return fileLocation + "Persons_" + LocalDateTime.now().toString().replace(":", "_") + ".csv";
	}
	
	private String[] getNames() {
		return new String[]{"lastName", "firstName", "age"};
	}
	
	
	@AfterStep
	public void afterStep(StepExecution stepExecution) {
		stepExecution
				.getJobExecution()
				.getExecutionContext()
				.put("csvPath", csvPath);
	}
	
}
