package com.profile_maker.utils;

import java.io.IOException;
import java.util.Properties;

public class ConfigLoader {

	// Sample comments here
	public Properties getDbConfigs() throws IOException {
		Properties properties = new Properties();
		properties.load(this.getClass().getResourceAsStream("/config.properties"));
		return properties;
	}
	
	public Properties getQueries() throws IOException {
		Properties properties = new Properties();
		properties.load(this.getClass().getResourceAsStream("/query.properties"));
		return properties;
	}
}
