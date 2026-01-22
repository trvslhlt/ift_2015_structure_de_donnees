package ca.umontreal.IFT2015.maps;

import java.util.concurrent.TimeUnit;
import java.lang.Math;

public class CuckooApp {

    public static void main( String[] args ) {

	long startTime;
	int numberIn = 20000000;

	System.out.println( "CuckooHashMap" );
	Set<Integer> cuckoo = new CuckooHashSet<>();
	// measure insertion time
	startTime = System.nanoTime();
	for( int i = 0; i < numberIn; i++ )
	    cuckoo.add( i );
	System.out.println( ( System.nanoTime() - startTime ) / 1000000 + " milliseconds for insertions" );

	// measure search time
	startTime = System.nanoTime();
	// make a number of searches
	for( int i = 0; i < numberIn; i++ ) {
	    int r = (int)(Math.random() * numberIn );
	    cuckoo.contains( r );
	}
	System.out.println( ( System.nanoTime() - startTime ) / 1000000 + " milliseconds for searches" );

	// measure remove time
	startTime = System.nanoTime();
	// make a number of removes
	for( int i = 0; i < numberIn; i++ ) {
	    int r = (int)(Math.random() * numberIn );
	    cuckoo.remove( r );
	}
	System.out.println( ( System.nanoTime() - startTime ) / 1000000 + " milliseconds for removes" );
    }
}
