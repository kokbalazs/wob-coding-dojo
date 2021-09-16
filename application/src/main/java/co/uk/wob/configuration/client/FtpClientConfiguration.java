package co.uk.wob.configuration.client;

import org.apache.commons.net.ftp.FTPClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FtpClientConfiguration {
	
	@Bean
	public FTPClient ftpClient() {
		return new FTPClient();
	}
	
}