package co.uk.wob.remoteload;

import co.uk.wob.repository.RemoteDumpRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Random;

import static co.uk.wob.common.CustomExitStatus.FTP_ALLOWED;

@Component
@RequiredArgsConstructor
@Slf4j
public class RemoteLoadTasklet implements Tasklet {
	
	private String csvPath;
	private final RemoteDumpRepository remoteDumpRepository;
	
	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws SQLException, IOException {
		remoteDumpRepository.loadDataToDatabase(csvPath);
		return RepeatStatus.FINISHED;
	}
	
	@BeforeStep
	public void beforeStep(StepExecution stepExecution) {
		csvPath = stepExecution
				.getJobExecution()
				.getExecutionContext()
				.getString("csvPath");
	}
	
	@AfterStep
	public ExitStatus afterStep(StepExecution stepExecution) {
		int number = generateRandomNumber();
		log.info("Random number was {}.", number);
		if (number >= 5) {
			return FTP_ALLOWED;
		}
		
		return ExitStatus.COMPLETED;
	}
	
	private int generateRandomNumber() {
		return new Random().nextInt(10) + 1;
	}
	
}
