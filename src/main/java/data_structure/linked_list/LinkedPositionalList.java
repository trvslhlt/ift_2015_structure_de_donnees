package data_structure.linked_list;

import java.util.Iterator;

public class LinkedPositionalList<E> implements PositionalList<E> {
	
	protected class Node<E> implements Position<E> {
		private E element;
		private Node<E> prev;
		private Node<E> next;
		private Object container; // the containing list
		public Node(E element, Node<E> prev, Node<E> next) {
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
		public Node<E> getPrev() { return this.prev; }
		public Node<E> getNext() { return this.next; }
		public Object getContainer() { return this.container; }
		public void setElement(E e) { this.element = e; }
		public void setPrev(Node<E> prev) { this.prev = prev; }
		public void setNext(Node<E> next) { this.next= next; }
		@Override
		public String toString() {
			return "Position(element = " + this.element + " )";
		}
	}
	
	private int size = 0;
	private Node<E> header;
	private Node<E> trailer;

	public LinkedPositionalList() {
		this.header = new Node<>(null, null, null);
		this.trailer = new Node<>(null, this.header, null);
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
		Node<E> node = this.validate(p);
		return this.position(node.getPrev());
	}

	@Override
	public Position<E> after(Position<E> p) throws IllegalArgumentException {
		Node<E> node = this.validate(p);
		return this.position(node.getNext());
	}

	@Override
	public Position<E> addFirst(E e) {
		return this.addBetween(e, this.header, this.header.getNext());
	}

	@Override
	public void moveFirst(Position<E> p) throws IllegalArgumentException {
		Node<E> node = this.validate(p);
		this.moveBefore(this.first(), p);
	}

	@Override
	public void moveLast(Position<E> p) throws IllegalArgumentException {
		Node<E> node = this.validate(p);
		this.moveBefore((Position<E>)this.trailer, p); // Why not this.last()?
	}

	@Override
	public Position<E> addLast(E e) {
		return this.addBetween(e, this.trailer.getPrev(), this.trailer);
	}

	@Override
	public Position<E> addBefore(Position<E> p, E e) throws IllegalArgumentException {
		Node<E> node = this.validate(p);
		return this.addBetween(e, node.getPrev(), node);
	}

	@Override
	public Position<E> addAfter(Position<E> p, E e) throws IllegalArgumentException {
		Node <E> node = this.validate(p);
		return this.addBetween(e, node, node.getNext());
	}

	@Override
	public E set(Position<E> p, E e) throws IllegalArgumentException {
		Node<E> node = this.validate(p);
		E curr = node.getElement();
		node.setElement(e);
		return curr;
	}

	@Override
	public E remove(Position<E> p) throws IllegalArgumentException {
		Node<E> node = this.validate(p);
		
		node.getPrev().setNext(node.getNext());
		node.getNext().setPrev(node.getPrev());
		
		node.setPrev(null);
		node.setNext(null);
		
		E curr = node.getElement();
		node.setElement(null);
		
		this.size--;
		
		return curr;
	}

	@Override
	public Position<E> moveBefore(Position<E> p, Position<E> toMove) throws IllegalArgumentException {
		Node<E> target = this.validate(p);
		Node<E> moving = this.validate(toMove);
		
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Position<E>> positions() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private Position<E>position(Node<E> node) {
		if (node == this.header || node == this.trailer) {
			return null;
		}
		return node;
	}
	
	private Position<E> addBetween(E e, Node<E> prev, Node<E> succ) {
		Node<E> node = new Node<>(e, prev, succ);
		prev.setNext(node);
		succ.setPrev(node);
		this.size++;
		return node;
	}
	
	private Node<E> validate(Position<E> p) throws IllegalArgumentException {
		if (!(p instanceof Node)) {
			throw new IllegalArgumentException("Invalid position");
		}
		Node<E> node = (Node<E>) p;
		if (node.getContainer() != this) {
			throw new IllegalArgumentException("Invalid container");
		}
		if (node.getNext() == null) {
			throw new IllegalArgumentException("position has been removed");
		}
		return node;
	}

}
