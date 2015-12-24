package org.networklibrary.core.parsing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.networklibrary.core.config.Dictionary;

public abstract class FileBasedParser<T> implements Parser<T> {

	protected BufferedReader reader = null;
	protected Dictionary dictionary = null;
	
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
	
	@Override
	public void setDictionary(Dictionary dictionary){
		this.dictionary = dictionary;
	}
	
	protected Dictionary getDictionary(){
		return dictionary;
	}
	
	/**
	 * Returns the substitute for value or value if nothing is found.
	 * @param value
	 * @return the value in the dictionary OR value if nothing is found in the dictionary
	 */
	protected String checkDictionary(String value){
		return getDictionary().hasSubstitute(value) ? getDictionary().getSubstitute(value) : value;
	}

}
