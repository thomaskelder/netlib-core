package org.networklibrary.core.parsing;

import java.util.Collection;

public interface Parser<T> {

	boolean hasHeader();
	void parseHeader(String header);
	Collection<T> parse(String line);

}
