package com.kobe.concurrentmode.guardedsuspension;

public class Request {
	private String name;

	public Request(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
