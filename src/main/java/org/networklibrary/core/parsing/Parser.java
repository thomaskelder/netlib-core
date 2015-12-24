package org.networklibrary.core.parsing;

import java.util.Collection;
import java.util.List;

import org.networklibrary.core.config.Dictionary;

public interface Parser<T> {
	
	void setDataSource(String location) throws ParsingErrorException;
	void setDictionary(Dictionary dictionary);
	
	boolean ready() throws ParsingErrorException;
	Collection<T> parse() throws ParsingErrorException;

	boolean hasExtraParameters();
	void takeExtraParameters(List<String> extras);
	
}
