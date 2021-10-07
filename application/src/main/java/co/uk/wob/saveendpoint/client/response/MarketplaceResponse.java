package co.uk.wob.saveendpoint.client.response;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MarketplaceResponse {
	
	private Long id;
	@JsonAlias("marketplace_name")
	private String marketplaceName;
	
}
