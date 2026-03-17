package ca.umontreal.IFT2015.maps;

import java.util.concurrent.TimeUnit;

public class Trips {

    public static void main( String[] args ) {

	PriceQuality vacationPackages = new PriceQuality();
	System.out.println( vacationPackages );
	vacationPackages.add( 2500,  90 ); // a
	System.out.println( vacationPackages );
	vacationPackages.add( 3000, 100 ); // b
	System.out.println( vacationPackages );
	vacationPackages.add( 3500, 120 ); // c
	System.out.println( vacationPackages );
	vacationPackages.add( 3550, 130 ); // d
	System.out.println( vacationPackages );
	vacationPackages.add( 3560, 140 ); // e
	System.out.println( vacationPackages );
	vacationPackages.add( 3600, 150 ); // f
	System.out.println( vacationPackages );
	vacationPackages.add( 3700, 155 ); // g
	System.out.println( vacationPackages );
	vacationPackages.add( 3900, 160 ); // h
	System.out.println( vacationPackages );
	vacationPackages.add( 3200, 145 ); // p
	System.out.println( vacationPackages );

	vacationPackages = new PriceQuality();
	
	long maxTrips = 500000000; // consider up to 500M trips
	int minPrice = 2000;
	int maxPrice = 250000;
	int maxSensation = 300;

	long start = System.currentTimeMillis();
	for( long length = 0; length <= maxTrips; length++ )
	    vacationPackages.add( (int)(Math.random() * maxPrice + minPrice  + 1 ),
			(int)(Math.random() * maxSensation + 1 ) );
	System.out.println( "Proposed trip: " + vacationPackages.best( 5000 ) );
	System.out.println( "Time to filter " + vacationPackages.size() + " dominant trips over " + maxTrips + " is " + (System.currentTimeMillis() - start) + " milliseconds." );
	System.out.println( vacationPackages );
    }
}
