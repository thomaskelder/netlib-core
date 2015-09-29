package org.networklibrary.core.types;

public class NodeDelete {

	protected long id;
	protected boolean withEdges;
	
	public NodeDelete(long id, boolean withEdges) {
		super();
		this.id = id;
		this.withEdges = withEdges;
	}

	public long getId() {
		return id;
	}

	public boolean withEdges() {
		return withEdges;
	}
	
	
}
