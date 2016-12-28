package org.springframework.boot.starter.cachetoredis.autoconfigure;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.cache")
public class RedisConfigProperties {
	private List<String> cacheNames = new ArrayList<String>();

	public List<String> getCacheNames() {
		return cacheNames;
	}

	public void setCacheNames(List<String> cacheNames) {
		this.cacheNames = cacheNames;
	}
	
}
