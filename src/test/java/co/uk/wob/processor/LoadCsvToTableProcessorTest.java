package co.uk.wob.processor;

import co.uk.wob.model.Person;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoadCsvToTableProcessorTest {
	
	@Test
	public void testProcessor() {
		Person person = new Person();
		person.setFirstName("tset_");
		
		LoadCsvToTableProcessor processor = new LoadCsvToTableProcessor();
		Person newPerson = processor.process(person);
		
		assert newPerson != null;
		assertEquals("tset__test", newPerson.getFirstName());
	}

}