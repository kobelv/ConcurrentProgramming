package com.kobe.concurrentmode.producerconsumer;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {
	private BlockingQueue<Message> queue;

	public Consumer(BlockingQueue<Message> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		while(true){
			try {
				Message m1 = this.queue.take();
				System.out.println(Thread.currentThread().getName() + "--" + m1.getName()+m1.getId());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
