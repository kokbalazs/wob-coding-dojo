package co.uk.wob.configuration;

import org.springframework.test.context.ActiveProfilesResolver;

import java.util.Map;

public class IntegrationTestActiveProfilesResolver implements ActiveProfilesResolver {
	
	@Override
	public String[] resolve(Class<?> testClass) {
		Map<String, String> env = System.getenv();
		return new String[]{env.getOrDefault("SPRING_PROFILES_ACTIVE", "")};
	}
	
}
