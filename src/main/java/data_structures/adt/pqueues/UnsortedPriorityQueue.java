package data_structures.adt.pqueues;

import java.util.Comparator;

import ca.umontreal.IFT2015.adt.list.LinkedPositionalList;
import ca.umontreal.IFT2015.adt.list.Position;
import ca.umontreal.IFT2015.adt.list.PositionalList;

public class UnsortedPriorityQueue<K,V> extends AbstractPriorityQueue<K, V> {

	private PositionalList<Entry<K, V>> list = new LinkedPositionalList<>();
	
	public UnsortedPriorityQueue() {
		super();
	}
	
	public UnsortedPriorityQueue(Comparator<K> comp) {
		super(comp);
	}
	
	private Position<Entry<K, V>> findMin() {
		Position<Entry<K, V>> smallest = this.list.first();
		for (Position<Entry<K, V>> walk : this.list.positions()) {
			if (this.compare(walk.getElement(), smallest.getElement()) < 0) {
				smallest = walk;
			}
		}
		return smallest;
	}
	
	@Override
	public int size() {
		return this.list.size();
	}

	@Override
	public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
		this.isValidKey(key);
		Entry<K, V> entry = new PQEntry<>(key, value);
		this.list.addLast(entry);
		return entry;
	}

	@Override
	public Entry<K, V> min() {
		if (this.list.isEmpty()) {
			return null;
		}
		return this.findMin().getElement();
	}

	@Override
	public Entry<K, V> removeMin() {
		if (this.list.isEmpty()) {
			return null;
		}
		return this.list.remove(this.findMin());
	}
	
	@Override
	public String toString() {
		return this.list.toString();
	}

}
