package co.uk.wob.configuration.database;

import com.zaxxer.hikari.HikariDataSource;
import org.postgresql.PGConnection;
import org.postgresql.copy.CopyManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Configuration
public class TargetDataSourceConfiguration {
	
	@Bean
	@ConfigurationProperties("target.datasource")
	public DataSourceProperties targetProperties() {
		return new DataSourceProperties();
	}
	
	@Bean
	@ConditionalOnBean(name = "targetProperties")
	public HikariDataSource targetDataSource(@Qualifier("targetProperties") DataSourceProperties targetProperties) {
		return targetProperties
				.initializeDataSourceBuilder()
				.type(HikariDataSource.class)
				.build();
	}
	
	@Bean
	@ConditionalOnBean(name = "targetDataSource")
	public NamedParameterJdbcTemplate targetNamedParameterJdbcTemplate(@Qualifier("targetDataSource") DataSource targetDataSource) {
		return new NamedParameterJdbcTemplate(targetDataSource);
	}
	
	@Bean
	@ConditionalOnBean(name = "targetDataSource")
	public PlatformTransactionManager targetPlatformTransactionManager(@Qualifier("targetDataSource") DataSource targetDataSource) {
		return new DataSourceTransactionManager(targetDataSource);
	}
	
	@Bean
	@ConditionalOnBean(name = "targetPlatformTransactionManager")
	public TransactionTemplate targetTransactionTemplate(@Qualifier("targetPlatformTransactionManager") PlatformTransactionManager targetPlatformTransactionManager) {
		return new TransactionTemplate(targetPlatformTransactionManager);
	}
	
	@Bean
	@ConditionalOnBean(name = "targetDataSource")
	public CopyManager copyManager(@Qualifier("targetDataSource") HikariDataSource dataSource) throws SQLException {
		Connection conn = dataSource.getConnection();
		PGConnection pgConnection = conn.unwrap(PGConnection.class);
		return pgConnection.getCopyAPI();
	}
	
}
