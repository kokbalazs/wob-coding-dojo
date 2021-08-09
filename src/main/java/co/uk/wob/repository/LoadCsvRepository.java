package co.uk.wob.repository;

import co.uk.wob.model.Person;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LoadCsvRepository {
	
	private final NamedParameterJdbcTemplate targetNamedParameterJdbcTemplate;
	
	public LoadCsvRepository(@Qualifier("targetNamedParameterJdbcTemplate") NamedParameterJdbcTemplate targetNamedParameterJdbcTemplate) {
		this.targetNamedParameterJdbcTemplate = targetNamedParameterJdbcTemplate;
	}
	
	private String getStorePersonToDatabaseQuery() {
		return "INSERT INTO person_target (first_name, last_name, age) values (:firstName, :lastName, :age)";
	}
	
	public void storePersonToDatabase(Person p) {
		targetNamedParameterJdbcTemplate.update(
				getStorePersonToDatabaseQuery(),
				new BeanPropertySqlParameterSource(p));
	}
	
}
