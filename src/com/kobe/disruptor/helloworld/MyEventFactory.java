package com.kobe.disruptor.helloworld;

import com.lmax.disruptor.EventFactory;

public class MyEventFactory implements EventFactory<MyEvent> {

	@Override
	public MyEvent newInstance() {
		System.out.println("MyEventFactory:newInstance() is called");
		return new MyEvent();
	}

}
