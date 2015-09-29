package org.networklibrary.core.types;

public class Index {

	protected String label;
	protected String property;
	public Index(String label, String property) {
		super();
		this.label = label;
		this.property = property;
	}
	
	public String getLabel() {
		return label;
	}
	public String getProperty() {
		return property;
	}
	
	
}
