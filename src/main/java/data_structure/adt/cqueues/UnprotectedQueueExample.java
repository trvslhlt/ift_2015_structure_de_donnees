package data_structure.adt.cqueues;

import java.util.LinkedList;
import java.util.Queue;

public class UnprotectedQueueExample {
	private static final Queue<Integer> queue = new LinkedList<>();
	private static int elementCount = 1000;
	
	static class Producer extends Thread {
		
		Producer(int id) { 
			super("Producer " + Integer.toString(id));
		}
		
		@Override
		public void run() {
			for (int i = 0; i < elementCount; i++) {
				queue.add(i);
			}
		}
	}
	
	static class Consumer extends Thread {

		Consumer(int id) { 
			super("Consumer " + Integer.toString(id));
		}
		
		@Override
		public void run() {
			for (int i = 0; i < elementCount; i++) {
				if (!queue.isEmpty()) {
					// this typically exits with an exception because `queue.remove()` 
//					 gets called in this thread after it is called in another thread
//					queue.remove();
					queue.poll();
				}
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		Producer p1 = new Producer(1);
		Producer p2 = new Producer(2);
		Consumer c1 = new Consumer(1);
		Consumer c2 = new Consumer(2);
		
		p1.start();
		p2.start();
		c1.start();
		c2.start();
		
		p1.join();
		p2.join();
		c1.join();
		c2.join();
		
		System.out.println("Exiting with items in the queue: " + queue.size());
	}
}
