package co.uk.wob.loadcsvtotable.writer;

import co.uk.wob.model.Person;
import co.uk.wob.repository.LoadCsvRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LoadCsvToTableWriter implements ItemWriter<Person> {
	
	private final LoadCsvRepository repository;
	
	@Override
	public void write(List<? extends Person> personList) {
		personList.forEach(repository::storePersonToDatabase);
	}
}
