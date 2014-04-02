package org.networklibrary.core.types;

import java.util.Map;

public class EdgeData {

	private String fromID;
	private String toID;
	
	private String type;
	
	private Map<String,Object> properties;

	public String getFromID() {
		return fromID;
	}

	public void setFromID(String fromID) {
		this.fromID = fromID;
	}

	public String getToID() {
		return toID;
	}

	public void setToID(String toID) {
		this.toID = toID;
	}

	public Map<String, Object> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, Object> properties) {
		this.properties = properties;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public EdgeData(String fromID, String toID, String type,
			Map<String, Object> properties) {
		super();
		this.fromID = fromID;
		this.toID = toID;
		this.type = type;
		this.properties = properties;
	}	
}
