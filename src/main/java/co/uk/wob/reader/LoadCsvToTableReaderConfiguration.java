package co.uk.wob.reader;

import co.uk.wob.model.Person;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

@Configuration
public class LoadCsvToTableReaderConfiguration {
	
	public static final String CSV_PATH = "csv/feladat_1.csv";
	
	@Bean
	public FlatFileItemReader<Person> loadCsvToTableReader() {
		FlatFileItemReader<Person> reader = new FlatFileItemReader<>();
		reader.setResource(new FileSystemResource(CSV_PATH));
		reader.setLinesToSkip(1);
		reader.setLineMapper(new DefaultLineMapper<>() {
			{
				setLineTokenizer(new DelimitedLineTokenizer() {
					{
						setNames("last_name", "first_name", "age");
					}
				});
				setFieldSetMapper(new BeanWrapperFieldSetMapper<>() {
					{
						setTargetType(Person.class);
					}
				});
			}
		});
		return reader;
	}
	
}
