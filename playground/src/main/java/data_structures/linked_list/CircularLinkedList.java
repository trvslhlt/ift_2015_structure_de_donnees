package data_structures.linked_list;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;

public class CircularLinkedList<E> implements List<E> {
	
	protected class Node {
		private E element;
		private Node next;
		
		public Node(E element, Node next) {
			this.element = element;
			this.next = next;
		}
		
		public E getElement() { return this.element; }
		public Node getNext() { return this.next; }
		public void setElement(E element) { this.element = element; }
		public void setNext(Node next) { this.next = next; }
	}
	
	protected class CLLIterator implements Iterator<E> {

		Node currNode;
		
		public CLLIterator() {
			this.currNode = (tail != null) ? tail.getNext() : null;
		}
		
		@Override
		public boolean hasNext() {
			return this.currNode != null;
		}

		@Override
		public E next() {
			Node prevNode = this.currNode;
			this.currNode = (this.currNode != tail) ? this.currNode.getNext() : null;
			return prevNode.getElement();
		}
		
	}
	
	private Node tail;
	private int size;
	
	public CircularLinkedList() {
		this.tail = null;
		this.size = 0;
	}

	@Override
	public boolean add(E element) {
		Node node = new Node(element, null);
		if (this.isEmpty()) {
			this.tail = node;
		}
		node.next = this.tail.getNext();
		this.tail.setNext(node);
		this.size++;
		return true;
	}

	@Override
	public void add(int idx, E element) {
		if (this.isEmpty()) {
			this.add(element);
			return;
		}
		Node prevNode = this.tail;
		Node currNode = this.tail.getNext();
		int currIdx = 0;
		
		idx = this.getCleanIdx(idx);
		
		while (currIdx < idx) {
			prevNode = currNode;
			currNode = currNode.getNext();
			currIdx++;
		}
		Node node = new Node(element, currNode);
		prevNode.setNext(node);
		this.size++;
	}

	@Override
	public boolean addAll(Collection<? extends E> elements) {
		for (E e : elements) {
			this.add(e);
		}
		return true;
	}

	@Override
	public boolean addAll(int idx, Collection<? extends E> elements) {
		for (E e : elements) {
			this.add(idx, e);
		}
		return true;
	}

	@Override
	public void clear() {
		this.tail = null;
	}

	@Override
	public boolean contains(Object obj) {
		return this.indexOf(obj) != -1;
	}

	@Override
	public boolean containsAll(Collection<?> objs) {
		for (Object obj : objs) {
			if (!this.contains(obj)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public E get(int idx) {
		if (this.isEmpty()) {
			return null;
		}
		idx = this.getCleanIdx(idx);
		int currIdx = 0;
		Node currNode = this.tail.getNext();
		while (currIdx < idx) {
			currNode = currNode.getNext();
			currIdx++;
		}
		return currNode.getElement();
	}

	@Override
	public int indexOf(Object obj) {
		if (this.isEmpty()) {
			return -1;
		}
		
		Node currNode = this.tail.getNext();
		int currIdx = 0;
		do {
			if (Objects.equals(obj, currNode.getElement())) {
				return currIdx;
			}
			currNode = currNode.getNext();
			currIdx++;
		} while (currNode != this.tail);
		return -1;
	}

	@Override
	public boolean isEmpty() {
		return this.tail == null;
	}

	@Override
	public Iterator<E> iterator() {
		return new CLLIterator();
	}

	@Override
	public int lastIndexOf(Object obj) {
		Iterator<E> it = this.iterator();
		int lastIdx = -1;
		int currIdx = 0;
		while (it.hasNext()) {
			if (Objects.equals(obj, it.next())) {
				lastIdx = currIdx;
			}
			currIdx++;
		}
		return 0;
	}

	@Override
	public ListIterator<E> listIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListIterator<E> listIterator(int idx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(Object obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public E remove(int idx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean removeAll(Collection<?> objs) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> objs) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public E set(int idx, E element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<E> subList(int fromIdx, int toIdx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T[] toArray(T[] arr) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private int getCleanIdx(int idx) {
		// adjust `idx` so that:
		// 1. it is positive
		// 2. it is no greater than the size
		return Math.floorMod(idx, this.size);
	}
}
