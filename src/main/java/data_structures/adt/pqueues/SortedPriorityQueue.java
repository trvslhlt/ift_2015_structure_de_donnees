package data_structures.adt.pqueues;

import java.util.Comparator;

import ca.umontreal.IFT2015.adt.list.LinkedPositionalList;
import ca.umontreal.IFT2015.adt.list.Position;
import ca.umontreal.IFT2015.adt.list.PositionalList;

public class SortedPriorityQueue<K, V> extends AbstractPriorityQueue<K, V> {
	
	PositionalList<Entry<K, V>> list = new LinkedPositionalList<>();
	
	public SortedPriorityQueue() {
		super();
	}
	
	public SortedPriorityQueue(Comparator<K> comp) {
		super(comp);
	}

	@Override
	public int size() {
		return this.list.size();
	}

	@Override
	public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
		Entry<K, V> entry = new PQEntry<>(key, value);
		Position<Entry<K, V>> walk = this.list.last();
		
		// Why do we walk backwards through the list?
		while (walk != null && this.compare(entry, walk.getElement()) < 0) {
			walk = this.list.before(walk);
		}
		if (walk == null) {
			this.list.addFirst(entry);
		} else {
			this.list.addAfter(walk, entry);
		}
		return entry;
	}

	@Override
	public Entry<K, V> min() {
		if (this.list.isEmpty()) {
			return null;
		}
		return this.list.first().getElement();
	}

	@Override
	public Entry<K, V> removeMin() {
		if (this.list.isEmpty()) {
			return null;
		}
		return this.list.remove(this.list.first());
	}
	
	@Override
	public String toString() {
		return this.list.toString();
	}

}
