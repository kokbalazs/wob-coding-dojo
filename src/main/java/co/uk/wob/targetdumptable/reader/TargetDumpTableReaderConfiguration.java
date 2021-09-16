package co.uk.wob.targetdumptable.reader;

import co.uk.wob.model.Person;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.builder.JdbcPagingItemReaderBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class TargetDumpTableReaderConfiguration {
	
	private static final int DEFAULT_FETCH_SIZE = 100;
	@Value("${target-table}")
	private String targetTable;
	
	@Bean
	public ItemReader<Person> targetDumpTableItemReader(@Qualifier("targetDataSource") HikariDataSource dataSource) {
		return new JdbcPagingItemReaderBuilder<Person>()
				.name("targetDumpTableItemReader")
				.dataSource(dataSource)
				.selectClause("first_name, last_name, age")
				.fromClause(targetTable)
				.fetchSize(DEFAULT_FETCH_SIZE)
				.pageSize(DEFAULT_FETCH_SIZE)
				.sortKeys(getSortKeys())
				.rowMapper(BeanPropertyRowMapper.newInstance(Person.class))
				.build();
	}
	
	private Map<String, Order> getSortKeys() {
		Map<String, Order> sortMap = new HashMap<>();
		sortMap.put("first_name", Order.ASCENDING);
		return sortMap;
	}
	
}
