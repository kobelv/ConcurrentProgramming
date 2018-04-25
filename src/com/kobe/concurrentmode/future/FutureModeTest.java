package com.kobe.concurrentmode.future;

public class FutureModeTest {

	public static void main(String[] args) throws InterruptedException {
		FutureClient fc = new FutureClient();
		Data data = fc.request();
		
		System.out.println("doing other things in main");

		Thread.sleep(8000);
		System.out.println("finished doing other things in main");

		String result = data.getData();
		System.out.println(result);

	}

}
