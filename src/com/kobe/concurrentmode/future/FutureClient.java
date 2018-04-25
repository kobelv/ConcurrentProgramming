package com.kobe.concurrentmode.future;

public class FutureClient {
	FutureData futureData = new FutureData();

	public Data request() {
		new Thread(new Runnable(){
			@Override
			public void run() {
				RealData realData = new RealData("input");
				futureData.setRealData(realData);
			}
			
		}).start();
		return futureData;
	}

}
