package co.uk.wob.saveendpoint.client.response;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ListingStatusResponse {
	
	private Long id;
	@JsonAlias("status_name")
	private String statusName;
	
}
