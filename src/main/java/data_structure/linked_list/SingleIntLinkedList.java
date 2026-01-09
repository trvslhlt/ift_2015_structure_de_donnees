package data_structure.linked_list;

public class SingleIntLinkedList {
	
	static private class Node {
		private int value;
		private Node next;
		
		public Node(int value, Node next) {
			this.setValue(value);
			this.setNext(next);
		}

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}

		public Node getNext() {
			return next;
		}

		public void setNext(Node next) {
			this.next = next;
		}
	}
	
	private Node head;
	
	public int get(int index) {
		int i = 0;
		Node node = this.head;
		while (i < index) {
			node = node.getNext();
			i++;
		}
		return node.getValue();
	}
	
	public int getHead() {
		int value = 0;
		if (this.head != null) {
			value = this.head.getValue();
		}
		return value;
	}
	
	public void insert(int value, int index) {
		if (index == 0) {
			this.insertHead(value);
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
	}
	
	public void insertHead(int value) {
		Node node = new Node(value, null);
		if (this.head != null) {
			node.setNext(this.head);
		}
		this.head = node;
	}
	
	public void remove(int index) {
		if (index == 0) {
			this.removeHead();
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
	}
	
	public void removeHead() {
		if (this.head != null) {
			Node n = this.head;
			this.head = this.head.getNext();
			n.setNext(null);
		}
	}
	
	@Override
    public String toString() {
		String s = "[";
		Node n = this.head;
		if (n != null) {
			s += String.valueOf(n.getValue());
			n = n.getNext();
		}
		while (n != null) {
			s += ", " + String.valueOf(n.getValue());
			n = n.getNext();
		}
		s += "]";
		return s;
    }

}
