package com.kobe.ThreadPoolExecutor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class TimingThreadPool extends ThreadPoolExecutor{
	private final AtomicLong numTasks = new AtomicLong();
	private final ThreadLocal<Long> startTime = new ThreadLocal<>();
	private final AtomicLong totalTime = new AtomicLong();
	
	@Override
	protected void beforeExecute(Thread t, Runnable r) {
		super.beforeExecute(t, r);
		//System.out.println(String.format("Thread %s: start %s", t, r));
		startTime.set(System.nanoTime());
	}

	@Override
	protected void afterExecute(Runnable r, Throwable t) {
		long endTime = System.nanoTime();
		totalTime.addAndGet(endTime - startTime.get());
		numTasks.incrementAndGet();
		
		//System.out.println(String.format("Thread %s: end %s, time=%dns", t, r, totalTime.get()));
		
		super.afterExecute(r, t);
	}

	@Override
	protected void terminated() {
		System.out.println(String.format("Terminated: avg time=%dns", totalTime.get()/numTasks.get()));
		
		super.terminated();
	}

	public TimingThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
			BlockingQueue<Runnable> workQueue, RejectedExecutionHandler h) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, h);
	}

	public static void main(String[] args) throws InterruptedException {
		TimingThreadPool ttp = new 	TimingThreadPool(2, 2, 3, TimeUnit.SECONDS, 
				new SynchronousQueue<Runnable>(),new AbortPolicy());
		
		
		for(int i=0; i<10;i++){
			ttp.execute(new Runnable(){
				@Override
				public void run() {
					int total=0;
					for(int j=0;j<100000;j++){
						total+=j;
					}
				}		
			});
			System.out.println(String.format("thread pool's size=%d, queue's size=%d,"
					+ "active count=%d, completed task count=%d",ttp.getPoolSize(), 
					ttp.getQueue().size(), ttp.getActiveCount(), ttp.getCompletedTaskCount()));
			
		}
		
	Thread.sleep(3000);
	System.out.println("=======");
	System.out.println(String.format("thread pool's size=%d, queue's size=%d,"
			+ "active count=%d, completed task count=%d",ttp.getPoolSize(), 
			ttp.getQueue().size(), ttp.getActiveCount(), ttp.getCompletedTaskCount()));
	ttp.shutdown();
	}
	
}
