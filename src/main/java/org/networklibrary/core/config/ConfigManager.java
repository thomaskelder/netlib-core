package org.networklibrary.core.config;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public abstract class ConfigManager implements General, Dictionary, Indexing {
	protected static final Logger log = Logger.getLogger(ConfigManager.class.getName());

	public final static String DEFAULT_CONFIG_LOCATION = "/netlib_default.config";
	
	protected final static String DICTIONARY_KEY = "dictionary";

	protected Configuration config = new CompositeConfiguration();
	protected Configuration dictionary = null;


	public ConfigManager(){
		((CompositeConfiguration)config).addConfiguration(new PropertiesConfiguration(), true);
	}

	protected void load(String runConfigFile) {

		if(runConfigFile != null && !runConfigFile.isEmpty()){
			try {
				Configuration runConfig = new PropertiesConfiguration(runConfigFile);
				((CompositeConfiguration)config).addConfiguration(runConfig);
				log.info("loaded " + runConfigFile);
			} catch (ConfigurationException e) {
				log.warning("invalid run config supplied " + runConfigFile + ". defaulting.");
			}
		}
		
		try {
			
			URL defaultFile = getClass().getResource(DEFAULT_CONFIG_LOCATION);
			Configuration defaultConf = new PropertiesConfiguration(defaultFile);
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
				URL resource = getClass().getResource(config.getString(DICTIONARY_KEY));
				if(resource == null){
					dictionary = new PropertiesConfiguration(config.getString(DICTIONARY_KEY));
				} else {
					dictionary = new PropertiesConfiguration(resource);
				}
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
		return getConfig().getString("primary_index_key");
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
	
	@Override
	public String getDefaultName(){
		return getConfig().getString("default_name");
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
