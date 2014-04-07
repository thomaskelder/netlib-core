package org.networklibrary.core.storage;

import java.util.Collection;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Transaction;
import org.networklibrary.core.config.ConfigManager;

public abstract class SingleTxStrategy<T> implements StorageEngine<T> {
	private GraphDatabaseService graph = null;
	private ConfigManager confMgr;
	
	public SingleTxStrategy(GraphDatabaseService graph, ConfigManager confMgr) {
		this.graph = graph;
		this.confMgr = confMgr;
	}

	public void store(T curr) {		
		try ( Transaction tx = graph.beginTx() ){
			doStore(curr);

			tx.success();
		}
	}

	public void finishUp() {		
	}

	public void storeAll(Collection<T> data) {
		for(T ed : data){
			store(ed);
		}
	}
	
	protected ConfigManager getConfMgr() {
		return confMgr;
	}
	
	protected GraphDatabaseService getGraph(){
		return graph;
	}

	protected abstract void doStore(T curr);
}
