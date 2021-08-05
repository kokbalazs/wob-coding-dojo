package co.uk.wob.dumptable.reader;

import co.uk.wob.model.Person;
import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.builder.JdbcPagingItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class DumpTableReaderConfiguration {
	
	public final HikariDataSource dataSource;
	@Value("${source-table}")
	private String sourceTable;
	
	@Bean
	public ItemReader<Person> dumpTableItemReader() {
		return new JdbcPagingItemReaderBuilder<Person>()
				.name("dumpTableItemReader")
				.dataSource(dataSource)
				.selectClause("first_name, last_name, age")
				.fromClause(sourceTable)
				.fetchSize(100)
				.pageSize(100)
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
