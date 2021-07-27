package co.uk.wob.repository;

import co.uk.wob.model.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LoadCsvRepository {
	
	private final NamedParameterJdbcTemplate jdbcTemplate;
	
	private String getStorePersonToDatabaseQuery() {
		return "INSERT INTO person (first_name, last_name, age) values (:firstName, :lastName, :age)";
	}
	
	public void storePersonToDatabase(Person p) {
		jdbcTemplate.update(
				getStorePersonToDatabaseQuery(),
				new BeanPropertySqlParameterSource(p));
	}
	
}
