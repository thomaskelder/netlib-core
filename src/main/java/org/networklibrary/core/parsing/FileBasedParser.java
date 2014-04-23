package org.networklibrary.core.parsing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public abstract class FileBasedParser<T> implements Parser<T> {

	protected BufferedReader reader = null;
	
	protected abstract boolean hasHeader();
	protected abstract void parseHeader(String header);
	
	protected String readLine() throws ParsingErrorException {
		try {
			return reader.readLine();
		} catch (IOException e) {
			throw new ParsingErrorException("failure to read new line",e);
		}
	}
	
	@Override
	public boolean ready() throws ParsingErrorException {
		try {
			return reader.ready();
		} catch (IOException e) {
			throw new ParsingErrorException("failure to check readyness",e);
		}
	}

	@Override
	public void setDataSource(String location) throws ParsingErrorException {
		try {
			reader = new BufferedReader(new FileReader(location));
			if(hasHeader())
				parseHeader(reader.readLine());
		} catch (IOException e) {
			throw new ParsingErrorException("failure to prepare datasource for reading",e);
		}
		
	}

}
