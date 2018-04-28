package com.kobe.concurrentmode.guardedsuspension;

public class GuardedSuspensionTest {

	public static void main(String[] args) throws InterruptedException {
		RequestQueue queue = new RequestQueue();
		for(int i=0; i<10; i++){
			new ServerThread(queue, "ServerThread"+i).start();
		}

		for(int j=0; j<10; j++){
			new ClientThread(queue, "ClientThread"+j).start();
		}
		
		Thread.sleep(2000);
		System.exit(0);
	}

}
