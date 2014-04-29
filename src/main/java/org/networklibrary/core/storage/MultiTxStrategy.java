package org.networklibrary.core.storage;

import java.util.Collection;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Transaction;
import org.networklibrary.core.config.ConfigManager;

public abstract class MultiTxStrategy<T> implements StorageEngine<T> {

	private GraphDatabaseService graph = null;

	private Transaction currTx = null;
	private long currOp = 0;

	private long maxOps = 0;

	public MultiTxStrategy(GraphDatabaseService graph, ConfigManager confMgr) {
		this.graph = graph;

		maxOps = 1000;
	}

	@Override
	public void store(T curr) {
		checkTx();
		{
		doStore(curr);
		}
	}

	protected abstract void doStore(T curr);

	@Override
	public void finishUp() {

		if(currTx != null){
			currTx.success();
			currTx.close();
		}

	}

	@Override
	public void storeAll(Collection<T> bundles) {
		for(T b : bundles){
			store(b);
		}
	}

	protected void checkTx() {
		if(currOp >= maxOps){
			currTx.success();
			currTx.close();
			currOp = 0;
			currTx = null;
		}
		
		if(currTx == null){
			currTx = graph.beginTx();
			currOp = 0;
		}
		currOp = currOp + 1;
	}

	protected GraphDatabaseService getGraph(){
		return graph;
	}
}
