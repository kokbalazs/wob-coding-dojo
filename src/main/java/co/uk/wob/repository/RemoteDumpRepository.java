package co.uk.wob.repository;

import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.postgresql.PGConnection;
import org.postgresql.copy.CopyManager;
import org.postgresql.core.BaseConnection;
import org.springframework.stereotype.Repository;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@Repository
@RequiredArgsConstructor
@Slf4j
public class RemoteDumpRepository {
	
	private final HikariDataSource dataSource;
	
	public void loadDataToDatabase(String csvPath) throws SQLException, IOException {
		Connection conn = dataSource.getConnection();
		PGConnection pgConnection = conn.unwrap(PGConnection.class);
		CopyManager copyManager = pgConnection.getCopyAPI();
		long copyCount = copyManager.copyIn("COPY person_source FROM STDIN", new FileReader(csvPath));
		log.info("Copied {} items to database.", copyCount);
	}
	
}
