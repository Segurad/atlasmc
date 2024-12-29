package de.atlasmc.util.dataset;

import java.util.Collection;
import java.util.List;

import de.atlasmc.NamespacedKey.Namespaced;

class EmptyDataSet<T extends Namespaced> implements DataSet<T> {

	public static final EmptyDataSet<?> INSTANCE = new EmptyDataSet<>();
	
	private EmptyDataSet() {}
	
	@Override
	public Collection<T> values() {
		return List.of();
	}

	@Override
	public boolean contains(T value) {
		return false;
	}
	
	@Override
	public int size() {
		return 0;
	}

}
