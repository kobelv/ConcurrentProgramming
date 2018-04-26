package com.kobe.concurrentmode.producerconsumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Producer implements Runnable {

	private BlockingQueue<Message> queue;
	boolean isRunning = true;
	AtomicInteger counter = new AtomicInteger(0);
	public Producer(BlockingQueue<Message> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		while(isRunning){
			Message m1 = new Message(counter.incrementAndGet(), "producer");
			try {
				if(!queue.offer(m1, 2, TimeUnit.SECONDS)){
					//means failed to add into queue, try again
					queue.offer(m1, 2, TimeUnit.SECONDS);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void stop(){
		isRunning = false;
	}

}
