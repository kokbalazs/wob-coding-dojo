package co.uk.wob.repository;

import lombok.extern.slf4j.Slf4j;
import org.postgresql.copy.CopyManager;
import org.springframework.stereotype.Repository;

import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;

@Repository
@Slf4j
public class RemoteDumpRepository {
	
	private final CopyManager copyManager;
	
	public RemoteDumpRepository(CopyManager copyManager) {
		this.copyManager = copyManager;
	}
	
	public void loadDataToDatabase(String csvPath) throws SQLException, IOException {
		long copyCount = copyManager.copyIn("COPY person_target (last_name, first_name, age) FROM STDIN", new FileReader(csvPath));
		log.info("Copied {} items to database.", copyCount);
	}
	
}
