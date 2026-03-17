package ca.umontreal.IFT2015.adt.cqueues;

import java.util.LinkedList;
import java.util.Queue;

public class ProtectedQueueExample {

    // Shared queue protected by a single monitor
    private static final Queue<Integer> queue = new LinkedList<>();
    private static final Object lock = new Object();

    static class Producer extends Thread {

        private final int id;

        Producer( int id ) { this.id = id; }

        @Override
        public void run() {
            for( int i = 0; i < 1000; i++ ) {
                synchronized( lock ) {
                    queue.add( i );
                }
            }
        }
    }

    static class Consumer extends Thread {

        @Override
        public void run() {
            for( int i = 0; i < 1000; i++ ) {
                Integer value = null;

                // Make the check-and-remove atomic
                synchronized( lock ) {
                    if( !queue.isEmpty() ) {
                        value = queue.remove();
                    }
                }

                if( value != null ) {
                    // do something with value
                }
            }
        }
    }

    public static void main( String[] args ) throws InterruptedException {

        Thread p1 = new Producer( 1 );
        Thread p2 = new Producer( 2 );
        Thread c1 = new Consumer();
        Thread c2 = new Consumer();

        p1.start();
        p2.start();
        c1.start();
        c2.start();

        p1.join();
        p2.join();
        c1.join();
        c2.join();

        System.out.println( "Final queue size = " + queue.size() );
    }
}
