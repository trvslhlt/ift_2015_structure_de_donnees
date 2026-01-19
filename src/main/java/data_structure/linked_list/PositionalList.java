package data_structure.linked_list;

import java.lang.IllegalArgumentException;
import java.lang.Iterable;
import java.util.Iterator;

public interface PositionalList<E> extends Iterable<E> {
	int size();
	boolean isEmpty();
	Position<E> first();
	Position<E> last();
	Position<E> before(Position<E> p) throws IllegalArgumentException;
	Position<E> after(Position<E> p) throws IllegalArgumentException;
	Position<E> addFirst(Position<E> p);
	void moveFirst(Position<E> p) throws IllegalArgumentException;
	void moveLast(Position<E> p) throws IllegalArgumentException;
	Position<E> addLast(E e);
	Position<E> addBefore(Position<E> p, E e) throws IllegalArgumentException;
	Position<E> addAfter(Position<E> p, E e) throws IllegalArgumentException;
	E set(Position<E> p, E e) throws IllegalArgumentException;
	E remove(Position<E> p) throws IllegalArgumentException;
	Position<E> moveBefore(Position<E> p, Position<E> toMove) throws IllegalArgumentException;
	void sort();
	Iterator<E> iterator();
	Iterable<Position<E>> positions();
}
