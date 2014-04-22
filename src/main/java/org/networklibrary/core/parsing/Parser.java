package org.networklibrary.core.parsing;

import java.util.Collection;
import java.util.List;

public interface Parser<T> {

	boolean hasHeader();
	void parseHeader(String header);
	Collection<T> parse(String line);

	boolean hasExtraParameters();
	void takeExtraParameters(List<String> extras);
	
}
