package com.kobe.disruptor.helloworld;

import com.lmax.disruptor.EventHandler;

public class MyEventHandler implements EventHandler<MyEvent> {

	@Override
	public void onEvent(MyEvent arg0, long arg1, boolean arg2) throws Exception {
		System.out.println(arg0.getValue() + " is consumed.");
	}

}
