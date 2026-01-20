package data_structure.linked_list;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedPositionalList<E extends Comparable<? super E>> implements PositionalList<E> {

	protected class Node implements Position<E> {
		private E element;
		private Node prev;
		private Node next;
		private Object container; // the containing list

		public Node(E element, Node prev, Node next) {
			this.element = element;
			this.prev = prev;
			this.next = next;
			this.container = LinkedPositionalList.this;
		}

		@Override
		public E getElement() throws IllegalStateException {
			if (this.next == null) {
				throw new IllegalStateException("Position is no longer valid");
			}
			return this.element;
		}

		public Node getPrev() {
			return this.prev;
		}

		public Node getNext() {
			return this.next;
		}

		public Object getContainer() {
			return this.container;
		}

		public void setElement(E e) {
			this.element = e;
		}

		public void setPrev(Node prev) {
			this.prev = prev;
		}

		public void setNext(Node next) {
			this.next = next;
		}

		@Override
		public String toString() {
			return "Position(element = " + this.element + " )";
		}
	}

	protected class PositionIterable implements Iterable<Position<E>> {
		@Override
		public Iterator<Position<E>> iterator() {
			return new PositionIterator();
		}
	}

	protected class PositionIterator implements Iterator<Position<E>> {
		private Node current = getHead();
		private Node last = null;

		@Override
		public boolean hasNext() {
			return this.current != null && this.current != trailer;
		}

		@Override
		public Position<E> next() throws NoSuchElementException {
			if (this.current == null) {
				throw new NoSuchElementException("No more positions");
			}
			this.last = this.current;
			Position<E> p = position(this.current);
			if (this.current == trailer.getPrev()) {
				this.current = null;
			} else {
				this.current = this.current.getNext();
			}
			return p;
		}

		@Override
		public void remove() throws NoSuchElementException {
			if (this.last == null) {
				throw new NoSuchElementException("No position to remove");
			}
			LinkedPositionalList.this.remove(position(this.last));
			this.last = null;
		}

	}

	protected class ElementIterator implements Iterator<E> {
		Iterator<Position<E>> positionIt = new PositionIterator();

		@Override
		public boolean hasNext() {
			return this.positionIt.hasNext();
		}

		@Override
		public E next() throws NoSuchElementException {
			return this.positionIt.next().getElement();
		}

		@Override
		public void remove() throws NoSuchElementException {
			this.positionIt.remove();
		}
	}

	private int size = 0;
	private Node header;
	private Node trailer;

	public LinkedPositionalList() {
		this.header = new Node(null, null, null);
		this.trailer = new Node(null, this.header, null);
		this.header.setNext(this.trailer);
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public boolean isEmpty() {
		return this.size == 0;
	}

	@Override
	public Position<E> first() {
		return this.position(this.header.getNext());
	}

	@Override
	public Position<E> last() {
		return this.position(this.trailer.getPrev());
	}

	@Override
	public Position<E> before(Position<E> p) throws IllegalArgumentException {
		Node node = this.validate(p);
		return this.position(node.getPrev());
	}

	@Override
	public Position<E> after(Position<E> p) throws IllegalArgumentException {
		Node node = this.validate(p);
		return this.position(node.getNext());
	}

	@Override
	public Position<E> addFirst(E e) {
		return this.addBetween(e, this.header, this.header.getNext());
	}

	@Override
	public void moveFirst(Position<E> p) throws IllegalArgumentException {
		Node moving = this.validate(p);

		// If already first, do nothing
		if (moving == header.getNext()) {
			return;
		}

		// Remove from current position
		moving.getPrev().setNext(moving.getNext());
		moving.getNext().setPrev(moving.getPrev());

		// Insert after header (i.e., at the beginning)
		moving.setPrev(header);
		moving.setNext(header.getNext());

		header.getNext().setPrev(moving);
		header.setNext(moving);
	}

	@Override
	public void moveLast(Position<E> p) throws IllegalArgumentException {
		Node moving = this.validate(p);

		// If already last, do nothing
		if (moving == trailer.getPrev()) {
			return;
		}

		// Remove from current position
		moving.getPrev().setNext(moving.getNext());
		moving.getNext().setPrev(moving.getPrev());

		// Insert before trailer (i.e., at the end)
		moving.setPrev(trailer.getPrev());
		moving.setNext(trailer);

		trailer.getPrev().setNext(moving);
		trailer.setPrev(moving);
	}

	@Override
	public Position<E> addLast(E e) {
		return this.addBetween(e, this.trailer.getPrev(), this.trailer);
	}

	@Override
	public Position<E> addBefore(Position<E> p, E e) throws IllegalArgumentException {
		Node node = this.validate(p);
		return this.addBetween(e, node.getPrev(), node);
	}

	@Override
	public Position<E> addAfter(Position<E> p, E e) throws IllegalArgumentException {
		Node node = this.validate(p);
		return this.addBetween(e, node, node.getNext());
	}

	@Override
	public E set(Position<E> p, E e) throws IllegalArgumentException {
		Node node = this.validate(p);
		E curr = node.getElement();
		node.setElement(e);
		return curr;
	}

	@Override
	public E remove(Position<E> p) throws IllegalArgumentException {
		Node node = this.validate(p);

		node.getPrev().setNext(node.getNext());
		node.getNext().setPrev(node.getPrev());

		E curr = node.getElement();

		node.setPrev(null);
		node.setNext(null);

		node.setElement(null);

		this.size--;

		return curr;
	}

	@Override
	public Position<E> moveBefore(Position<E> p, Position<E> toMove) throws IllegalArgumentException {
		Node target = this.validate(p);
		Node moving = this.validate(toMove);

		moving.getPrev().setNext(moving.getNext());
		moving.getNext().setPrev(moving.getPrev());

		moving.setPrev(target.getPrev());
		moving.setNext(target);

		target.getPrev().setNext(moving);
		target.setPrev(moving);

		return moving;
	}

	@Override
	public void sort() {
		// insertion sort
		Position<E> marker = this.first();
		while (marker != this.last()) {
			Position<E> pivot = this.after(marker);
			E value = pivot.getElement();
			if (value.compareTo(marker.getElement()) > 0) {
				marker = pivot;
			} else {
				Position<E> walker = marker;
				while (walker != this.first() && value.compareTo(this.before(walker).getElement()) < 0) {
					walker = this.before(walker);
				}
				this.moveBefore(walker, pivot);
			}
		}
	}

	@Override
	public Iterator<E> iterator() {
		return new ElementIterator();
	}

	@Override
	public Iterable<Position<E>> positions() {
		return new PositionIterable();
	}

	@Override
	public String toString() {
		if (this.isEmpty())
			return "[]";
		StringBuilder sb = new StringBuilder("[");
		Node current = this.getHead();
		while (current != this.getTail()) {
			sb.append(current.getElement()).append(",");
			current = current.getNext();
		}
		sb.append(current.getElement()).append("]");
		return sb.toString();
	}

	private Position<E> position(Node node) {
		if (node == this.header || node == this.trailer) {
			return null;
		}
		return node;
	}

	private Position<E> addBetween(E e, Node prev, Node succ) {
		Node node = new Node(e, prev, succ);
		prev.setNext(node);
		succ.setPrev(node);
		this.size++;
		return node;
	}

	private Node getHead() {
		return this.header.getNext();
	}

	private Node getTail() {
		return this.trailer.getPrev();
	}

	private Node validate(Position<E> p) throws IllegalArgumentException {
		if (!(p instanceof Node)) {
			throw new IllegalArgumentException("Invalid position");
		}
		Node node = (Node) p;
		if (node.getContainer() != this) {
			throw new IllegalArgumentException("Invalid container");
		}
		if (node.getNext() == null) {
			throw new IllegalArgumentException("position has been removed");
		}
		return node;
	}

}
