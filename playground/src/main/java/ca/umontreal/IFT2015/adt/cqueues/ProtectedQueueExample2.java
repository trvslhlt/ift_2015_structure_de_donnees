package ca.umontreal.IFT2015.adt.cqueues;

import java.util.LinkedList;
import java.util.Queue;

public class ProtectedQueueExample2 {

    private static final Queue<Integer> queue = new LinkedList<>();
    private static final Object lock = new Object();

    private static final int ITEMS_PER_PRODUCER = 1000;
    private static final int PRODUCERS = 2;
    private static final int CONSUMERS = 2;

    // Special value meaning "stop"
    private static final int POISON_PILL = Integer.MIN_VALUE;

    static class Producer extends Thread {

        private final int id;

        Producer( int id ) { this.id = id; }

        @Override
        public void run() {
            for( int i = 0; i < ITEMS_PER_PRODUCER; i++ ) {
                synchronized( lock ) {
                    queue.add( i );
                    lock.notifyAll(); // wake up consumers waiting for data
                }
            }
        }
    }

    static class Consumer extends Thread {

        private int consumed = 0;

        @Override
        public void run() {
            while( true ) {
                int value;
		
                synchronized( lock ) {
                    while( queue.isEmpty() ) {
                        try {
                            lock.wait(); // wait for data
                        } catch( InterruptedException e ) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                    value = queue.remove();
                }

                if( value == POISON_PILL ) {
                    return; // stop consuming
                }

                consumed++;
                // do something with value
            }
        }

        public int getConsumed() {
            return consumed;
        }
    }

    public static void main(String[] args) throws InterruptedException {

        Thread p1 = new Producer( 1 );
        Thread p2 = new Producer( 2 );

        Consumer c1 = new Consumer();
        Consumer c2 = new Consumer();

        p1.start();
        p2.start();
        c1.start();
        c2.start();

        p1.join();
        p2.join();

        // Send one poison pill per consumer
        synchronized( lock ) {
            for( int i = 0; i < CONSUMERS; i++ ) {
                queue.add( POISON_PILL );
            }
            lock.notifyAll();
        }

        c1.join();
        c2.join();

        System.out.println( "Final queue size = " + queue.size() );
        System.out.println( "Total consumed = " + ( c1.getConsumed() + c2.getConsumed() ) );
        System.out.println( "Total produced = " + ( PRODUCERS * ITEMS_PER_PRODUCER ) );
    }
}
