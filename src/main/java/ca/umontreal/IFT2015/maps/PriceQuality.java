package ca.umontreal.IFT2015.maps;

import java.util.Comparator;
//import java.util.Map;
//import java.util.concurrent.ConcurrentSkipListMap;
//import java.util.Map.Entry;

/**
* QualityPrice is a class to maintain a maxima set
*   using a SortedMap
* 
* Based on Goodrich, Tamassia, Goldwasser
*
* @author      Francois Major
* @version     1.0
* @since       1.0
*/

public class PriceQuality {
    SortedMap<Integer,Integer> map = new SortedTableMap<>();
    //SortedMap<Integer,Integer> map = new SkipListMap( Integer.MIN_VALUE, Integer.MAX_VALUE );
    //ConcurrentSkipListMap<Integer,Integer> map = new ConcurrentSkipListMap<>();
    //SortedMap<Integer,Integer> map = new TreeMap<>();

    // construct an initially empty database
    public PriceQuality() {}

    // return the (price,quality) entry with largest quality not exceeding price,
    //   or null if no entry exist with price or less
    public Entry<Integer,Integer> best( int price ) { return this.map.floorEntry( price ); }

    // return number of entries in the map
    public int size() { return this.map.size(); }

    // ass a new entry with given price and quality
    public void add( int p, int q ) {
	Entry<Integer,Integer> other = this.map.floorEntry( p ); // other at least as cheap
	if( other != null && other.getValue() >= q ) // if its quality is as good
	    return; // (p,q) is dominated, so ignore it
	this.map.put( p, q ); // otherwise, add (p,q) to database
	// and remove the entries that are dominated by the new one
	other = this.map.higherEntry( p ); // other is more expensive than p
	while( other != null && other.getValue() <= q ) { // if not better quality
	    this.map.remove( other.getKey() ); // remove the other entry
	    other = this.map.higherEntry( p );
	}
    }
    public String toString() { return this.map.toString(); }
}
