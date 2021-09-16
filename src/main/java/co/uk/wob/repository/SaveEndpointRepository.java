package co.uk.wob.repository;

import co.uk.wob.saveendpoint.client.response.ListingStatusResponse;
import co.uk.wob.saveendpoint.client.response.MarketplaceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SaveEndpointRepository {
	
	private final NamedParameterJdbcTemplate targetNamedParameterJdbcTemplate;
	
	private String getStoreListingStatusToDatabaseQuery() {
		return "INSERT INTO listing_status(id, status_name) VALUES (:id, :statusName) ON CONFLICT (id) DO UPDATE set status_name=:statusName";
	}
	
	private String getStoreMarketplaceToDatabaseQuery() {
		return "INSERT INTO marketplace(id, marketplace_name) VALUES (:id, :marketplaceName) ON CONFLICT (id) DO UPDATE set marketplace_name=:marketplaceName";
	}
	
	public void storeListingStatusToDatabase(ListingStatusResponse listingStatusResponse) {
		targetNamedParameterJdbcTemplate.update(
				getStoreListingStatusToDatabaseQuery(),
				new BeanPropertySqlParameterSource(listingStatusResponse));
	}
	
	public void storeMarketplaceToDatabase(MarketplaceResponse marketplaceResponse) {
		targetNamedParameterJdbcTemplate.update(
				getStoreMarketplaceToDatabaseQuery(),
				new BeanPropertySqlParameterSource(marketplaceResponse));
	}
}
