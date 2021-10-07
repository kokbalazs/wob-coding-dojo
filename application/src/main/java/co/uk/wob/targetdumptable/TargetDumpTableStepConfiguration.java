package co.uk.wob.targetdumptable;

import co.uk.wob.model.Person;
import co.uk.wob.targetdumptable.writer.TargetDumpTableWriterConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;

@Configuration
@RequiredArgsConstructor
public class TargetDumpTableStepConfiguration {
	
	private final StepBuilderFactory stepBuilderFactory;
	private final ItemReader<Person> targetDumpTableItemReader;
	private final ItemWriter<Person> targetDumpTableItemWriter;
	private final TargetDumpTableWriterConfiguration writerConfiguration;
	private final TaskExecutor multiThreadTaskExecutor;
	
	@Bean
	public Step targetDumpTableStep() {
		return stepBuilderFactory
				.get("targetDumpTableStep")
				.<Person, Person>chunk(1)
				.reader(targetDumpTableItemReader)
				.writer(targetDumpTableItemWriter)
				.listener(writerConfiguration)
				.faultTolerant()
				.skipLimit(3)
				.skip(Exception.class)
				.taskExecutor(multiThreadTaskExecutor)
				.build();
	}
	
}
