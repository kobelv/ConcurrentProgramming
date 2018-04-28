package com.kobe.concurrentmode.guardedsuspension;

public class ServerThread extends Thread{
	private RequestQueue queue;
	private String name;
	public ServerThread(RequestQueue queue, String name) {
		this.queue = queue;
		this.name = name;
	}

	@Override
	public void run() {
		while(true){
			Request request = queue.getRequest();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(this.name + " is processing " + request.getName());
		}
		
	}

}
