package org.networklibrary.core.config;

import java.util.logging.Logger;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class ConfigManager implements General, Dictionary, Indexing {
	protected static final Logger log = Logger.getLogger(ConfigManager.class.getName());

	public final static String DEFAULT_CONFIG_LOCATION = "netlib_default.conf";
	public final static String USER_CONFIG_LOCATION = ".netlib.conf";
	
	protected final static String DICTIONARY_KEY = "dictionary";

	protected Configuration config = null;
	protected Configuration dictionary = null;

	public ConfigManager(String runConfig){
		load(runConfig);
	}

	public ConfigManager(){
		load(null);
	}

	private void load(String runConfigFile) {

		config = new CompositeConfiguration();

		Configuration runConfig = null;
		if(runConfigFile != null && !runConfigFile.isEmpty()){
			try {
				runConfig = new PropertiesConfiguration(runConfigFile);
				((CompositeConfiguration)config).addConfiguration(runConfig);
				log.info("loaded " + runConfigFile);
			} catch (ConfigurationException e) {
				log.warning("invalid run config supplied " + runConfigFile + ". defaulting.");
			}
		}
		
		Configuration defaultConf;
		try {
			defaultConf = new PropertiesConfiguration(DEFAULT_CONFIG_LOCATION);
			((CompositeConfiguration)config).addConfiguration(defaultConf);
			log.info("loaded " + DEFAULT_CONFIG_LOCATION);
		} catch (ConfigurationException e) {
			log.severe("could not load default config " + DEFAULT_CONFIG_LOCATION + ". Stopping");
		}

		if(((CompositeConfiguration)config).getNumberOfConfigurations() == 0){
			config = null;
		}
		
		// prepare the dictionary if one is provided.
		if(config != null && config.containsKey(DICTIONARY_KEY)){
			try {
				dictionary = new PropertiesConfiguration(config.getString(DICTIONARY_KEY));
			} catch (ConfigurationException e) {
				log.warning("failed to load the dictionary at " + config.getString(DICTIONARY_KEY));
				dictionary = null;
			}
		}
	}
	
	/*
	 * Indexing implementation
	 */
	@Override
	public String getPrimaryKey() {
		return getConfig().getString("primary_key");
	}
	
	@Override
	public String getPrimaryIndex(){
		return getConfig().getString("primary_index");
	}
	
	/*
	 * General implementation
	 */
	public boolean isReady(){
		return getConfig() != null && !getConfig().isEmpty();
	}
	public String getErrorLogLocation(){
		return getConfig().getString("error_log");
	}

	/*
	 * Dictionary implementation
	 */
	@Override
	public String getSubstitute(String orig) {
		return getDictionary() != null ? getDictionary().getString(orig) : null;
	}
	
	@Override
	public boolean 	hasSubstitute(String orig){
		return getDictionary() != null && getDictionary().containsKey(orig);
	}

	protected Configuration getConfig() {
		return config;
	}

	protected void setConfig(Configuration config) {
		this.config = config;
	}

	protected Configuration getDictionary() {
		return dictionary;
	}

	protected void setDictionary(Configuration dictionary) {
		this.dictionary = dictionary;
	}
	
	
}
