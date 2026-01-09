package data_structure.linked_list;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SingleIntegerLinkedList implements Iterable<Integer> {

	private Node head;
	private int size = 0;

	static private class Node {
		private Integer value;
		private Node next;

		public Node(Integer value, Node next) {
			this.setValue(value);
			this.setNext(next);
		}

		public Integer getValue() {
			return value;
		}

		public void setValue(Integer value) {
			this.value = value;
		}

		public Node getNext() {
			return next;
		}

		public void setNext(Node next) {
			this.next = next;
		}
	}

	private class SILLIterator implements Iterator<Integer> {

		private Node previous = null;
		private Node current = null;
		private Node next = head;
		private boolean canRemove = false;

		@Override
		public boolean hasNext() {
			return this.next != null;
		}

		@Override
		public Integer next() {
			if (!this.hasNext()) {
				throw new NoSuchElementException();
			}
			this.previous = this.current;
			this.current = this.next;
			this.next = this.next.getNext();
			this.canRemove = true;
			return this.current.getValue();
		}

		@Override
		public void remove() {
			if (!this.canRemove) {
				throw new IllegalStateException();
			}
			if (this.previous == null) {
				head = this.next;
			} else {
				this.previous.setNext(this.next);
			}
			this.current = this.previous;
			this.canRemove = false;

			SingleIntegerLinkedList.this.decrementSize();
		}

	}

	public Integer get(int index) {
		this.checkIndexValidity(index);

		int i = 0;
		Node node = this.head;
		while (i < index) {
			node = node.getNext();
			i++;
		}
		return node.getValue();
	}

	public Integer getFirst() {
		Integer value = null;
		if (this.head != null) {
			value = this.head.getValue();
		}
		return value;
	}

	public int size() {
		return this.size;
	}

	public void add(Integer value, int index) {
		this.checkIndexValidity(index);

		if (index == 0) {
			this.addFirst(value);
			return;
		}
		Node node = new Node(value, null);
		Node n = this.head;
		Node p = null;
		int i = 0;
		while (i < index) {
			p = n;
			n = n.getNext();
			i++;
		}
		p.setNext(node);
		node.setNext(n);

		this.incrementSize();
	}

	public void addFirst(Integer value) {
		Node node = new Node(value, null);
		if (this.head != null) {
			node.setNext(this.head);
		}
		this.head = node;

		this.incrementSize();
	}

	public void remove(int index) {
		this.checkIndexValidity(index);

		if (index == 0) {
			this.removeFirst();
			return;
		}
		Node p = null;
		Node c = this.head;
		Node n = null;

		int i = 0;
		while (i < index) {
			p = c;
			c = c.getNext();
			n = c.getNext();
			i++;
		}
		p.setNext(n);
		c.setNext(null);

		this.decrementSize();
	}

	public void removeFirst() {
		if (this.head != null) {
			Node n = this.head;
			this.head = this.head.getNext();
			n.setNext(null);
		}

		this.decrementSize();
	}

	@Override
	public String toString() {
		// TODO: add logic for large lists
		StringBuilder sb = new StringBuilder("[");

		Node n = this.head;
		if (n != null) {
			sb.append(n.getValue());
			n = n.getNext();
		}
		while (n != null) {
			sb.append(", ").append(n.getValue());
			n = n.getNext();
		}
		sb.append("]");
		return sb.toString();
	}

	@Override
	public Iterator<Integer> iterator() {
		return new SILLIterator();
	}

	private void checkIndexValidity(int index) {
		if (index < 0 || this.size <= index) {
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
		}
	}
	
	private void incrementSize() {
		this.size++;
	}

	private void decrementSize() {
		if (this.size > 0) {
			this.size--;
		}
	}
}
