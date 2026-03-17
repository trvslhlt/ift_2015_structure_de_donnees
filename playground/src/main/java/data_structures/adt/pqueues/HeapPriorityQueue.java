package data_structures.adt.pqueues;

import java.util.Comparator;

import ca.umontreal.IFT2015.adt.list.ArrayList;
import ca.umontreal.IFT2015.adt.list.List;

public class HeapPriorityQueue<K, V> extends AbstractPriorityQueue<K, V> {

	List<Entry<K, V>> heap = new ArrayList<>();

	public HeapPriorityQueue() {
		super();
	}

	public HeapPriorityQueue(Comparator<K> comp) {
		super(comp);
	}

	public HeapPriorityQueue(K[] keys, V[] values) {
		super();
		for (int i = 0; i < keys.length && i < values.length; i++) {
			this.heap.add(new PQEntry<>(keys[i], values[i]));
		}
		this.heapify();
	}

	protected int parent(int idx) {
		return (idx - 1) / 2;
	}
	
	protected int left(int idx) {
		return idx * 2 + 1;
	}
	
	protected int right(int idx) {
		return idx * 2 + 2;
	}
	
	protected boolean hasLeft(int idx) {
		return this.left(idx) < this.size();
	}
	
	protected boolean hasRight(int idx) {
		return this.right(idx) < this.size();
	}
	
	protected void heapify() {
		int startIdx = this.parent(this.size() - 1);
		for (int i = startIdx; i >= 0; i--) {
			this.sink(i);
		}
	}
	
	protected void sink(int idx) {
		while (this.hasLeft(idx)) {
			int leftIdx = this.left(idx);
			int smallestChildIdx = this.left(idx);
			if (this.hasRight(idx)) {
				int rightIdx = this.right(idx);
				if (this.compare(this.heap.get(leftIdx), this.heap.get(idx)) > 0) {
					smallestChildIdx = rightIdx;
				}
			}
			if (this.compare(this.heap.get(smallestChildIdx), this.heap.get(idx)) >= 0) {
				break;
			}
			this.swap(idx, smallestChildIdx);
			idx = smallestChildIdx;
		}
	}
	
	protected void swap(int a, int b) {
		Entry<K, V> temp = this.heap.get(a);
		this.heap.set(a, this.heap.get(b));
		this.heap.set(b, temp);
	}
	
	protected void swim(int idx) {
		while (idx > 0) {
			int parentIdx = this.parent(idx);
			if (this.compare(this.heap.get(idx), this.heap.get(parentIdx)) >= 0) {
				break;
			}
			this.swap(idx, parentIdx);
			idx = parentIdx;
		}
	}
	
	@Override
	public int size() {
		return this.heap.size();
	}

	@Override
	public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
		Entry<K, V> entry = new PQEntry<>(key, value);
		this.heap.add(entry);
		int entryIdx = this.size() - 1;
		this.swim(entryIdx);
		return entry;
	}

	@Override
	public Entry<K, V> min() {
		return this.heap.first();
	}

	@Override
	public Entry<K, V> removeMin() {
		if (this.isEmpty()) {
			return null;
		}
		Entry<K, V> min = this.heap.get(0);
		int lastIdx = this.size() - 1;
		this.swap(0, lastIdx);
		this.heap.remove(lastIdx);
		this.sink(0);
		return min;
	}
	
	@Override
    public String toString() {
		return this.heap.toString();
	}
}
