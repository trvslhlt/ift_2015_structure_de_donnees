package ca.umontreal.IFT2015.adt.list;

import java.util.Iterator;

public class TestInvalidContainer {

    public static void main( String[] args ) throws Exception {
	// mixing two positional lists
	PositionalList<Integer> L1 = new LinkedPositionalList<>();
	PositionalList<Integer> L2 = new LinkedPositionalList<>();
	
	Position p = L1.addLast( 1 );
	Position q = L1.addLast( 2 );
	Position r = L2.addLast( 3 );
	Position s = L2.addLast( 4 );
	System.out.println( "L1: " + L1 );
	System.out.println( "L2: " + L2 );
	L1.remove( p );
	L2.remove( r );
	L1.remove( s ); // error
	System.out.println( "L1: " + L1 );
	System.out.println( "L2: " + L2 );
    }
}
