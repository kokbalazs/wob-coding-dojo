package co.uk.wob.configuration;

import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;

import javax.sql.DataSource;

public class CustomBatchConfigurer extends DefaultBatchConfigurer {
	
	@Override
	public void setDataSource(DataSource dataSource) {
		//Disabling Job Metadata saving during integration tests because it's not necessary. (It caused deadlocks because of gradle runs tests in parallel.)
	}
	
}
