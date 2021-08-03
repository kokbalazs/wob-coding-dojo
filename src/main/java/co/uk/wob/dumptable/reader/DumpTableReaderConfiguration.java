package co.uk.wob.dumptable.reader;

import co.uk.wob.model.Person;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.builder.JdbcPagingItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DumpTableReaderConfiguration {
	
	public DataSource dataSource;
	@Value("${source-table}")
	private String sourceTable;
	
	@Bean
	public ItemReader<Person> dumpTableItemReader() {
		return new JdbcPagingItemReaderBuilder<Person>()
				.name("dumpTableItemReader")
				.dataSource(dataSource)
				.selectClause("first_name, last_name, age ")
				.fromClause(sourceTable)
				.fetchSize(100)
				.pageSize(100)
				.build();
	}
	
}
