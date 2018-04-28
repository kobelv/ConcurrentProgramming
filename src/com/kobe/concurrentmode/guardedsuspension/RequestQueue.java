package com.kobe.concurrentmode.guardedsuspension;

import java.util.LinkedList;

public class RequestQueue {
	private LinkedList<Request> queue = new LinkedList<>();

	public synchronized Request getRequest() {
		while(queue.isEmpty()){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return queue.removeFirst();
	}

	public synchronized void putRequest(Request request) {
		queue.add(request);
		notifyAll();
	}
	
	
}
