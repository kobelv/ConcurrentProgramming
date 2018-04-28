package com.kobe.disruptor.helloworld;

import com.lmax.disruptor.RingBuffer;

public class MyEventProducer {

	private RingBuffer<MyEvent> ringBuffer;

	public MyEventProducer(RingBuffer<MyEvent> ringBuffer) {
		this.ringBuffer = ringBuffer;
	}

	public void onData(int i) {
		long sequence = ringBuffer.next();
		try{
			MyEvent event = ringBuffer.get(sequence);
			event.setValue(i);
		} finally{
			ringBuffer.publish(sequence);
		}
	}

}
