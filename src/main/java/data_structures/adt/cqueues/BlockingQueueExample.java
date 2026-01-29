package data_structures.adt.cqueues;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class BlockingQueueExample {
	
	private static final int N_MESSAGES = 1000;
	private static final BlockingQueue<Integer> queue = new LinkedBlockingQueue<>();
	private static final int POISON_PILL = Integer.MIN_VALUE;
	
	protected static class Producer extends Thread {
		Producer(int id) {
			super(Integer.toString(id));
		}
		
		@Override
		public void run() {
			try {
				for (int i = 0; i < N_MESSAGES; i++) {
					queue.put(i);	
				}
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
	}
	
	protected static class Consumer extends Thread {
		private int consumed = 0;
		
		@Override
		public void run() {
			int value;
			try {
				while (true) {	
					value = queue.take();
					if (value == POISON_PILL) {
						return;
					}
					this.consumed++;
				}
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
		
		public int getConsumed() {
			return this.consumed;
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		Producer p1 = new Producer(1);
		Producer p2 = new Producer(2);
		Consumer c1 = new Consumer();
		Consumer c2 = new Consumer();
		
		p1.start();
		p2.start();
		c1.start();
		c2.start();
		
		p1.join();
		p2.join();
		
		try {
			// one for each consumer
			queue.put(POISON_PILL);
			queue.put(POISON_PILL);
		} catch (InterruptedException e) {
			System.out.println("interrrupted while adding POISON_PILL");
		}
		
		c1.join();
		c2.join();
		
		System.out.println("Final queue size = " + queue.size());
        System.out.println("Total consumed = " + (c1.getConsumed() + c2.getConsumed()));
        System.out.println("Total produced = " + (2 * N_MESSAGES));
	}

}
