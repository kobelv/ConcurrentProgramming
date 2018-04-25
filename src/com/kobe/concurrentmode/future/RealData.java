package com.kobe.concurrentmode.future;

public class RealData implements Data {

	private String data;
	public RealData(String input) {
		//make some processing either fetch data from db or elsewhere
		System.out.println("started to fetch data.");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("finished to fetch data.");
		
		data = "Data is ready, it's from RealData's data";
	}

	@Override
	public String getData() {
		return data;
	}

}
