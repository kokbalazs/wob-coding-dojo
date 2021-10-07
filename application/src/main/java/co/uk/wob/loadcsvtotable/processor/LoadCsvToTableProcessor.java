package co.uk.wob.loadcsvtotable.processor;

import co.uk.wob.model.Person;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class LoadCsvToTableProcessor implements ItemProcessor<Person, Person> {
	
	private static final String TEST = "_test";
	
	@Override
	public Person process(Person person) {
		person.setFirstName(person.getFirstName().concat(TEST));
		return person;
	}
	
}
