package ca.umontreal.IFT2015.maps;

import java.lang.Iterable;
import java.util.ArrayList;
import java.util.Random;

/**
* CuckooHash is an implementation of the ADT Set
*   using cuckoo strategies
* 
* @author      Francois Major
* @version     1.0
* @since       1.0 (2024.11.12)
*/

public class CuckooHashSet<E> implements Set<E> {

    private static final int DEFAULT_CAPACITY = 11;
    private static final int MAX_REHASHES = 16; // maximum number of rehasing before resizing
    private final int numHashFunctions = 2; // number of hash functions
    private int count = 0;
    private Random random = new Random();
    private E[] table; // using a fixed array of entries

    public CuckooHashSet() {
	this.table = (E[]) new Object[DEFAULT_CAPACITY];
	this.random = new Random();
    }

    // hash functions
    private int hash1( E e ) {
        return Math.abs( e.hashCode() % this.table.length );
    }

    private int hash2( E e ) {
        return Math.abs( ( e.hashCode() / this.table.length ) % this.table.length );
    }

    public int size() { return this.count; };
    public boolean isEmpty() { return this.count == 0; };
    public boolean contains( E e ) {
    	int pos1 = hash1( e );
	int pos2 = hash2( e );

	if( this.table[pos1] != null && this.table[pos1].equals( e ) ) {
	    return true;
	}
	if( this.table[pos2] != null && this.table[pos2].equals( e ) ) {
	    return true;
	}
	return false;
    }

    // utilities
    private void rehash() {
	E[] oldTable = table;
	table = (E[]) new Object[oldTable.length * 2]; // new table with double space
	// copy the old table in new
	for( E e : oldTable ) {
	    if( e != null ) {
		this.add( e );  // reput each key
	    }
	}
    }

    public boolean add( E e ) {
	if( contains( e ) ) return false;  // Already in table

	E toKickOut = e;
	int attempts = 0;

	while( attempts < MAX_REHASHES ) {
	    int pos1 = hash1( toKickOut );
	    int pos2 = hash2( toKickOut );

	    if( this.table[pos1] == null ) {
		table[pos1] = toKickOut;
		return true;
	    } else if( this.table[pos2] == null ) {
		table[pos2] = toKickOut;
		return true;
	    }

	    // kick out and place in one of the hashed places
	    if( random.nextBoolean() ) {
		E temp = this.table[pos1];
		this.table[pos1] = toKickOut;
		toKickOut = temp;
	    } else {
		E temp = this.table[pos2];
		this.table[pos2] = toKickOut;
		toKickOut = temp;
	    }
	    attempts++;
	}

	// if number of rehashes reached, rehash and reput
	rehash();
	return this.add( toKickOut );
    }

    public boolean remove( E e ) {
	int pos1 = hash1( e );
	int pos2 = hash2( e );

	if( this.table[pos1] != null && this.table[pos1].equals( e ) ) {
	    this.table[pos1] = null;
	    return true;
	}
	if( this.table[pos2] != null && this.table[pos2].equals( e ) ) {
	    this.table[pos2] = null;
	    return true;
	}
	return false;
    }
}
