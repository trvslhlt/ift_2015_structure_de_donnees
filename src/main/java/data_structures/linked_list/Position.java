package data_structures.linked_list;

import java.lang.IllegalStateException;

public interface Position<E> {
	E getElement() throws IllegalStateException;
}
