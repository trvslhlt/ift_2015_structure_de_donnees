package ca.umontreal.IFT2015.adt.pqueues;

import java.util.Comparator;
import java.lang.IllegalArgumentException;
import ca.umontreal.IFT2015.adt.list.PositionalList;
import ca.umontreal.IFT2015.adt.list.Position;
import ca.umontreal.IFT2015.adt.list.LinkedPositionalList;

/**
* UnsortedPriorityQueue is an implementation of the ADT Priority Queue
*    using a LinkedPositionalList that we keep unsorted
* 
* Based on Goodrich, Tamassia, Goldwasser
*
* @author      Francois Major
* @version     1.0
* @since       1.0
*/

public class UnsortedPriorityQueue<K,V> extends AbstractPriorityQueue<K,V> {
    
    // attribute, physical data structure
    private PositionalList<Entry<K,V>> list = new LinkedPositionalList<>();
    
    // construct an empty PQ based on natural ordering
    public UnsortedPriorityQueue() { super(); }
    // construct an empty PQ based on a given comparator to order keys
    public UnsortedPriorityQueue( Comparator<K> comp ) { super( comp ); }

    // utility, find the smallest key in O(n)
    private Position<Entry<K,V>> findMin() { // only called when not empty
	Position<Entry<K,V>> small = this.list.first();
	for( Position<Entry<K,V>> walk : this.list.positions() )
	    if( this.compare( walk.getElement(), small.getElement() ) < 0 )
		small = walk; // found a smaller key
	return small;
    }
    
    // insert a (key, value) entry and return the entry created in O(1)
    @Override
    public Entry<K,V> insert( K k, V v ) throws IllegalArgumentException {
	checkKey( k ); // key checking method (could throw exception)
	Entry<K,V> newest = new PQEntry<>( k, v );
	this.list.addLast( newest );
	return newest;
    }

    // return (but do not remove) an entry of minimal key in O(n)
    @Override
    public Entry<K,V> min() {
	if( this.list.isEmpty() ) return null;
	return this.findMin().getElement();
    }

    // remove and return an entry of minimal key in O(n)
    @Override
    public Entry<K,V> removeMin() {
	if( this.list.isEmpty() ) return null;
	return this.list.remove( this.findMin() );
    }

    // return the number of entries in the PQ
    @Override
    public int size() { return this.list.size(); }

    // convert PQ to String
    @Override
    public String toString() { return this.list.toString(); }
}
