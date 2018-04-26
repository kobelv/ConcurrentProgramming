package com.kobe.concurrentmode.producerconsumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class ProducerConsumerTest {

	public static void main(String[] args) throws InterruptedException {
	
	BlockingQueue<Message> queue = new LinkedBlockingQueue<>();	
	ExecutorService es = Executors.newCachedThreadPool();
	Producer p1 = new Producer(queue);
	Producer p2 = new Producer(queue);
	Producer p3 = new Producer(queue);
	es.submit(p1);
	es.submit(p2);
	es.submit(p3);
	
	es.submit(new Consumer(queue));
	es.submit(new Consumer(queue));
	es.submit(new Consumer(queue));
	
	Thread.sleep(5000);
	
	p1.stop();
	p2.stop();
	p3.stop();
	es.shutdown();
	}

}
