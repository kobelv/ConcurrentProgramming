package com.kobe.concurrentmode.guardedsuspension;

import java.util.concurrent.atomic.AtomicInteger;

public class ClientThread extends Thread{
	private RequestQueue queue;
	private String name;
	private static AtomicInteger counter = new AtomicInteger(1);
	public ClientThread(RequestQueue queue, String name) {
		this.queue = queue;
		this.name = name;
	}

	@Override
	public void run() {
		while(true){
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Request request = new Request(String.valueOf(counter.incrementAndGet()));
			queue.putRequest(request);
			System.out.println(this.name + " put Request " + request.getName() + " into queue.");
		}
	}

}
