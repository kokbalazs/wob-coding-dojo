package co.uk.wob.saveendpoint.client;

import co.uk.wob.client.response.ListingStatusResponse;
import co.uk.wob.client.response.MarketplaceResponse;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "${endpoint.name}", url = "${endpoint.url}")
public interface EndpointClient {
	
	@GetMapping(value = "/listingStatus?key=63304c70")
	@Headers("Content-Type: application/json")
	List<ListingStatusResponse> listingStatus();
	
	@GetMapping(value = "/marketplace?key=63304c70")
	List<MarketplaceResponse> marketplace();
	
}
