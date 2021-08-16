package co.uk.wob.remoteload;

import co.uk.wob.repository.RemoteDumpRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.SQLException;

@Component
@RequiredArgsConstructor
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
	
}
