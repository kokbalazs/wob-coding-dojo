package co.uk.wob.processor;

import co.uk.wob.model.Person;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class LoadCsvToTableProcessor implements ItemProcessor<Person, Person> {
	
	@Override
	public Person process(Person person) throws Exception {
		return null;
	}
	
}
