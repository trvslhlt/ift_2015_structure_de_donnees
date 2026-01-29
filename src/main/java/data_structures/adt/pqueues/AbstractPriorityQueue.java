package data_structures.adt.pqueues;

import java.util.Comparator;

public abstract class AbstractPriorityQueue<K, V> implements PriorityQueue<K,V> {
	
	protected static class PQEntry<K, V> implements Entry<K, V> {
		
		private K key;
		private V value;

		PQEntry(K key, V value) {
			this.key = key;
			this.value = value;
		}
		
		@Override
		public K getKey() {
			return this.key;
		}

		@Override
		public V getValue() {
			return this.value;
		}
		
		protected void setKey(K key) {
			this.key = key;
		}
		
		protected void setValue(V value) {
			this.value = value;
		}
		
		@Override
		public String toString() {
			return "(" + this.getKey() + "," + this.getValue() + ")";
		}
		
	}
	
	private Comparator<K> comp;
	
	AbstractPriorityQueue(Comparator<K> comp) {
		this.comp = comp;
	}
	
	public AbstractPriorityQueue() {
		this.comp = new DefaultComparator<K>();
	}
	
	protected int compare(Entry<K, V> a, Entry<K, V> b) {
		return this.comp.compare(a.getKey(), b.getKey());
	}
	
	// Why does this function need a return value?
	protected boolean isValidKey(K key) throws IllegalArgumentException {
		try {
			return this.comp.compare(key, key) == 0;
		} catch (ClassCastException e) {
			throw new IllegalArgumentException("invalid key");
		}
	}

	@Override
	public boolean isEmpty() {
		return this.size() == 0;
	}

}
