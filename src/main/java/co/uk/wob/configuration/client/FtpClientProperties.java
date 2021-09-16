package co.uk.wob.configuration.client;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@ConfigurationProperties("ftp")
@Validated
@Data
public class FtpClientProperties {
	@NotEmpty(message = "The FTP hostName key can't be empty.")
	private String hostName;
	private Integer port;
	@NotEmpty(message = "The FTP username key can't be empty.")
	private String username;
	@NotEmpty(message = "The FTP password key can't be empty.")
	private String password;
	@NotEmpty(message = "The FTP filePath key can't be empty.")
	private String filePath;
}
