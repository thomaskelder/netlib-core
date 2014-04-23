package org.networklibrary.core.parsing;

import java.util.Collection;
import java.util.List;

public interface Parser<T> {

//	boolean hasHeader();
//	void parseHeader(String header);
	
	void setDataSource(String location) throws ParsingErrorException;
	
	boolean ready() throws ParsingErrorException;
	Collection<T> parse() throws ParsingErrorException;

	boolean hasExtraParameters();
	void takeExtraParameters(List<String> extras);
	
}
