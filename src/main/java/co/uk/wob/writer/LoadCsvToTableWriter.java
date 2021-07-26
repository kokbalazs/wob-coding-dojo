package co.uk.wob.writer;

import co.uk.wob.model.Person;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LoadCsvToTableWriter implements ItemWriter<Person> {
	
	@Override
	public void write(List<? extends Person> list) throws Exception {
	
	}
}
