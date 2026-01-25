package ca.umontreal.IFT2015.adt.cqueues;

import java.util.LinkedList;
import java.util.Queue;

public class UnprotectedQueueExample {

    // Shared non-thread-safe queue
    private static final Queue<Integer> queue = new LinkedList<>();

    // add elements to the queue
    static class Producer extends Thread {

	// attributes
        private final int id;

	// constructor
        Producer( int id ) { this.id = id; }

        @Override
        public void run() {
            for( int i = 0; i < 1000; i++ ) { // add 1000 elements
                queue.add( i );
            }
        }
    }

    // take elements from the queue
    static class Consumer extends Thread {

        @Override
        public void run() {
            for( int i = 0; i < 1000; i++ ) { // remove/poll 1000 elements
                if( !queue.isEmpty() ) {
                    //Integer value = queue.remove();
		    Integer value = queue.poll(); // null if empty
                    // do something with value
                }
            }
        }
    }

    public static void main( String[] args ) throws InterruptedException {

	// create 2 producers and 2 consumers
        Thread p1 = new Producer( 1 );
        Thread p2 = new Producer( 2 );
        Thread c1 = new Consumer();
        Thread c2 = new Consumer();

	// start the producers and consumers
        p1.start();
        p2.start();
        c1.start();
        c2.start();

	// make sure we wait until all threads are finished
        p1.join();
        p2.join();
        c1.join();
        c2.join();

	// print the number of elements in the queue (should be 0 if all goes well)
        System.out.println( "Final queue size = " + queue.size() );
    }
}
