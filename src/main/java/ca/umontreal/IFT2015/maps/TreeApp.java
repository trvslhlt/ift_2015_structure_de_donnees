package ca.umontreal.IFT2015.maps;

import java.util.concurrent.TimeUnit;

import ca.umontreal.IFT2015.adt.list.Position;

public class TreeApp {

    public static void main( String[] args ) {

	//TreeMap<Integer,Integer> tm = new TreeMap<>();
	TreeMap<Integer,Integer> avlTree = new AVLTreeMap<>();
	// System.out.println( tm.size() );
	// System.out.println( tm );
	// tm.put( 6, 6 );
	// System.out.println( tm.size() );
	// System.out.println( tm );
	// tm.put( 2, 2 );
	// System.out.println( tm.size() );
	// System.out.println( tm );
	// tm.put( 1, 1 );
	// System.out.println( tm );
	// tm.put( 4, 4 );
	// System.out.println( tm );
	// tm.put( 9, 9 );
	// System.out.println( tm );
	// tm.put( 8, 8 );
	// System.out.println( tm );
	// System.out.println( "first entry: " + tm.firstEntry() );
	// for( Entry<Integer,Integer> entry : tm.subMap( 1, 10 ) )
	//     System.out.println( entry );

	avlTree.put( 44, 44 );
	avlTree.put( 17, 17 );
	avlTree.put( 78, 78 );
	avlTree.put( 32, 32 );
	avlTree.put( 50, 50 );
	avlTree.put( 88, 88 );
	avlTree.put( 48, 48 );
	avlTree.put( 62, 62 );
	System.out.println( avlTree );
	avlTree.put( 54, 54 );
	System.out.println( avlTree );
	avlTree.remove( 32 );
	System.out.println( avlTree );

	System.out.println( "slide#8" );
	Map<Integer,Integer> tMap = new TreeMap<>();
	tMap.put( 1, 1 );
	tMap.put( 3, 3 );
	tMap.put( 2, 2 );
	tMap.put( 8, 8 );
	tMap.put( 9, 9 );
	tMap.put( 7, 7 );
	tMap.put( 5, 5 );
	tMap.put( 6, 6 );
	System.out.println( tMap );
	tMap.remove( 3 );
	System.out.println( tMap );
    }
}
