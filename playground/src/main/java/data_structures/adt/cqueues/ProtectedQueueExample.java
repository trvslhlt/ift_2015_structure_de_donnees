package data_structures.adt.cqueues;

import java.util.LinkedList;
import java.util.Queue;

public class ProtectedQueueExample {
	
	private static final Queue<Integer> queue = new LinkedList<Integer>();
	private static final Object lock = new Object();
	private static final int N_ELEMENTS = 1000;
	private static final int N_PRODUCERS = 2;
	private static final int N_CONSUMERS = 2;
	private static final int POISON_PILL = Integer.MIN_VALUE;
	

	static class Producer extends Thread {
		Producer(int id) {
			super(Integer.toString(id));
		}
		
		@Override
		public void run() {
			for (int i = 0; i < N_ELEMENTS; i++) {
				synchronized (lock) {
					queue.add(i);
					lock.notifyAll();
				}
			}
		}
	}
	
	static class Consumer extends Thread {
		private int consumed = 0;
		
		@Override
		public void run() {
			while(true) {
				int value;
				synchronized (lock) {
					if (queue.isEmpty()) {
						try {
							lock.wait();
						} catch (InterruptedException e) {
							Thread.currentThread().interrupt();
							return;
						}
					}
					value = queue.remove();
				}
				if (value == POISON_PILL) {
					return;
				}
				this.consumed++;
			}
		}
		
		public int getConsumed() {
			return this.consumed;
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		Producer[] producers = new Producer[N_PRODUCERS];
		Consumer[] consumers = new Consumer[N_CONSUMERS];
		
		for (int i = 0; i < N_PRODUCERS; i++) {
			Producer p = new Producer(i);
			p.start();
			producers[i] = p;
		}
		for (int i = 0; i < N_CONSUMERS; i++) {
			Consumer c = new Consumer();
			c.start();
			consumers[i] = c;
		}
		
		for (int i = 0; i < N_PRODUCERS; i++) {
			producers[i].join();
		}
		
		for (int i = 0; i < N_CONSUMERS; i++) {
			synchronized (lock) {
				queue.add(POISON_PILL);
				lock.notifyAll();
			}
		}
		
		for (int i = 0; i < N_CONSUMERS; i++) {
			consumers[i].join();
		}
		
		System.out.println("Final queue size = " + queue.size());
        System.out.println("Total consumed = " + (consumers[0].getConsumed() + consumers[1].getConsumed()));
        System.out.println("Total produced = " + (N_PRODUCERS * N_ELEMENTS));
	}
}
