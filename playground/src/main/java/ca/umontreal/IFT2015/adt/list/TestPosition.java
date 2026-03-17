package ca.umontreal.IFT2015.adt.list;

/**
* TestPosition is a class to exemplify the concept of Position
* 
* @author      Francois Major
* @version     1.0
* @since       1.0 (2024.09.24)
*/

public class TestPosition {
    public static void main( String[] args ) {
	
	// Using PositionalList
	PositionalList<String> maListePositionnelle = new LinkedPositionalList<>();
	maListePositionnelle.addLast( "premier" );
	maListePositionnelle.addLast( "deuxieme" );
	maListePositionnelle.addLast( "troisieme" );
	maListePositionnelle.addLast( "quatrieme" );
	maListePositionnelle.addLast( "cinquieme" );
	Position positionPaule = maListePositionnelle.addLast( "Paule" ); // save position on the fly
	maListePositionnelle.addLast( "septieme" );
	maListePositionnelle.addLast( "huitieme" );
	maListePositionnelle.addLast( "neuvieme" );
	System.out.println( "original: " + maListePositionnelle );

	// instructions' order is not important
	// Add Paul after Paule
	maListePositionnelle.addAfter( positionPaule, "Paul" );      // O(1)
	System.out.println( "addAfter( positionPaule, Paul ): " + maListePositionnelle );
	// Add Paulette before Paule
	maListePositionnelle.addBefore( positionPaule, "Paulette" ); // O(1), Paule's position never changes
	System.out.println( "addBefore( positionPaule, Paulette ): " + maListePositionnelle );
    }
}
