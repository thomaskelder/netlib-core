package org.networklibrary.core.types;

public class IdData {

	private String matchID;
	private String propertyName;
	private String value;
	
	public IdData(String matchID, String propertyName, String value) {
		super();
		this.matchID = matchID;
		this.propertyName = propertyName;
		this.value = value;
	}
	public String getMatchID() {
		return matchID;
	}
	public void setMatchID(String matchID) {
		this.matchID = matchID;
	}
	public String getPropertyName() {
		return propertyName;
	}
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	
	
}
