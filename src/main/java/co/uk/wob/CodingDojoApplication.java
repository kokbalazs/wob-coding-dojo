package co.uk.wob;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class CodingDojoApplication {
	
	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(CodingDojoApplication.class, args);
		int exitCode = SpringApplication.exit(ctx);
		System.exit(exitCode);
	}
	
}
