package ca.umontreal.IFT2015.adt.cqueues;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class BlockingQueueExample {

    private static final int ITEMS_PER_PRODUCER = 1000;
    private static final int PRODUCERS = 2;
    private static final int CONSUMERS = 2;

    // Special value meaning "stop"
    private static final int POISON_PILL = Integer.MIN_VALUE;

    private static final BlockingQueue<Integer> queue = new LinkedBlockingQueue<>();

    static class Producer extends Thread {

        private final int id;

        Producer( int id ) { this.id = id; }

        @Override
        public void run() {
            try {
                for( int i = 0; i < ITEMS_PER_PRODUCER; i++ ) {
                    queue.put( i );
                }
            } catch( InterruptedException e ) {
                Thread.currentThread().interrupt();
            }
        }
    }

    static class Consumer extends Thread {

        private int consumed = 0;

        @Override
        public void run() {
            try {
                while( true ) {
                    int value = queue.take(); // blocks if empty

                    if( value == POISON_PILL ) {
                        return; // stop consuming
                    }

                    consumed++;
                    // do something with value
                }
            } catch( InterruptedException e ) {
                Thread.currentThread().interrupt();
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
        for( int i = 0; i < CONSUMERS; i++ ) {
            queue.put( POISON_PILL );
        }

        c1.join();
        c2.join();

        System.out.println( "Final queue size = " + queue.size() );
        System.out.println( "Total consumed = " + ( c1.getConsumed() + c2.getConsumed() ) );
        System.out.println( "Total produced = " + ( PRODUCERS * ITEMS_PER_PRODUCER ) );
    }
}
