package co.uk.wob.configuration.client;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class WireMockConfiguration {
	
	@Autowired
	private WireMockServer wireMockServer;
	
	@Bean(initMethod = "start", destroyMethod = "stop")
	public WireMockServer mockEndpointService() {
		return new WireMockServer(9561);
	}

}
