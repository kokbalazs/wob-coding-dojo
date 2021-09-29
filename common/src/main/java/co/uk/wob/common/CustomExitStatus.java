package co.uk.wob.common;

import org.springframework.batch.core.ExitStatus;

public class CustomExitStatus extends ExitStatus {
	
	public static final ExitStatus FTP_ALLOWED = new ExitStatus("FTP_ALLOWED");
	
	public CustomExitStatus(String exitCode) {
		super(exitCode);
	}
	
	public CustomExitStatus(String exitCode, String exitDescription) {
		super(exitCode, exitDescription);
	}
}
