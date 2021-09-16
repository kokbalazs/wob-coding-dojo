package co.uk.wob.remoteload;

import co.uk.wob.common.CustomExitStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.Whitebox;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;

class RemoteLoadTaskletTest {
	
	private RemoteLoadTasklet tasklet;
	private RandomNumberGenerator randomNumberGenerator;
	
	@BeforeEach
	public void init() {
		tasklet = Mockito.mock(RemoteLoadTasklet.class, Mockito.CALLS_REAL_METHODS);
		randomNumberGenerator = Mockito.mock(RandomNumberGenerator.class);
		Whitebox.setInternalState(tasklet, "randomNumberGenerator", randomNumberGenerator);
	}
	
	@Test
	public void testRemoteLoadAfterStepFtp() {
		Mockito.when(randomNumberGenerator.generateRandomNumber()).thenReturn(10);
		Assertions.assertEquals(CustomExitStatus.FTP_ALLOWED, tasklet.afterStep(Mockito.mock(StepExecution.class)));
	}
	
	@Test
	public void testRemoteLoadAfterStepNotFtp() {
		Mockito.when(randomNumberGenerator.generateRandomNumber()).thenReturn(10);
		Assertions.assertNotEquals(ExitStatus.COMPLETED, tasklet.afterStep(Mockito.mock(StepExecution.class)));
	}
	
	@Test
	public void testRemoteLoadAfterStepCompleted() {
		Mockito.when(randomNumberGenerator.generateRandomNumber()).thenReturn(1);
		Assertions.assertEquals(ExitStatus.COMPLETED, tasklet.afterStep(Mockito.mock(StepExecution.class)));
	}
	
	@Test
	public void testRemoteLoadAfterStepNotCompleted() {
		Mockito.when(randomNumberGenerator.generateRandomNumber()).thenReturn(1);
		Assertions.assertNotEquals(CustomExitStatus.FTP_ALLOWED, tasklet.afterStep(Mockito.mock(StepExecution.class)));
	}
	
}