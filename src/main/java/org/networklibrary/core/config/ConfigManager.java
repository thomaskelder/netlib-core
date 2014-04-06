package org.networklibrary.core.config;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class ConfigManager {
	
	public final static String DEFAULT_CONFIG = "netlib.conf";

	private Configuration config = null;
	
	public ConfigManager(String config){
		load(config);
	}
	
	public ConfigManager(){
		load(DEFAULT_CONFIG);
	}
	
	private void load(String configLoc) {
		try {
			config = new PropertiesConfiguration(configLoc);
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
		
	}
	
	public String getErrorLogLocation(){
		return config.getString("error_log", "netlib.errors");
	}
	
	public boolean createUnknownNodes(){
		return config.getBoolean("create_unknown",false);
	}
	
}
