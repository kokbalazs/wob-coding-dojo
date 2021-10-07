package co.uk.wob.ftp;

import co.uk.wob.configuration.client.FtpClientProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;

@Component
@Slf4j
@RequiredArgsConstructor
public class FtpUploaderTasklet implements Tasklet {
	
	private final FTPClient ftpClient;
	private final FtpClientProperties ftpClientProperties;
	private String fileName;
	
	@Override
	public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
		try {
			log.info("Connecting to ftp server ...");
			ftpClient.connect(ftpClientProperties.getHostName(), ftpClientProperties.getPort());
			boolean connected = ftpClient.login(ftpClientProperties.getUsername(), ftpClientProperties.getPassword());
			log.info("Is connected {}", connected);
			ftpClient.enterLocalPassiveMode();
			log.info("Entered local passive mode.");
			FTPFile[] files = ftpClient.listFiles();
			log.debug("Found {} files.", files.length);
			boolean stored = ftpClient.storeFile(ftpClientProperties.getFilePath(), new FileInputStream(fileName));
			log.info("Did store file {}", stored);
			boolean loggedOut = ftpClient.logout();
			log.info("Is logged out {}", loggedOut);
		} finally {
			ftpClient.disconnect();
		}
		
		return RepeatStatus.FINISHED;
	}
	
	@BeforeStep
	public void beforeStep(StepExecution stepExecution) {
		fileName = stepExecution
				.getJobExecution()
				.getExecutionContext()
				.getString("csvPath");
	}
}