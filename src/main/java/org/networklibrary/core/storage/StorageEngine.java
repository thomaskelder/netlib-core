package org.networklibrary.core.storage;

import java.util.Collection;

public interface StorageEngine<T> {

	void store(T curr);

	void finishUp();

	void storeAll(Collection<T> parse);

}
