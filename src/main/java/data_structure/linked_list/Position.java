package data_structure.linked_list;

import java.lang.IllegalStateException;

public interface Position<E> {
	E getElement() throws IllegalStateException;
}
