package data_structure.linked_list;

import java.util.Comparator;

public class DefaultComparator<E extends Comparable<? super E>> implements Comparator<E> {
    
	@Override
    public int compare(E a, E b) throws ClassCastException {
    	return a.compareTo(b);
    }
}