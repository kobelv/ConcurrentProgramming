package com.kobe.disruptor.helloworld;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.lmax.disruptor.dsl.Disruptor;

public class DisruptorTest {

	public static void main(String[] args) throws InterruptedException {
		/*
		 * preparation to new Disruptor
		 * 
		 * 
		 * */
		int bufferSize = 1024;
		Executor executor = Executors.newCachedThreadPool();
		Disruptor<MyEvent> disruptor = new Disruptor<MyEvent>(new MyEventFactory(), 
				bufferSize, executor);
		disruptor.handleEventsWith(new MyEventHandler());	
		disruptor.start();
		
		//param disruptor.getRingBuffer() is for producer to store event
		MyEventProducer producer = new MyEventProducer(disruptor.getRingBuffer());
		
		for(int i=0; i<100; i++){
			producer.onData(i);
			Thread.sleep(50);
		}
		
		disruptor.shutdown();

	}

}
