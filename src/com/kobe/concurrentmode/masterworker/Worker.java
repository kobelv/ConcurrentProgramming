package com.kobe.concurrentmode.masterworker;

import java.math.BigDecimal;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Worker implements Runnable {
	//worker needs to pick task per capacity 
	private ConcurrentLinkedQueue<Task> taskQueue;
	
	//worker needs to report its result
	private ConcurrentHashMap<String, BigDecimal> resultMap;

	public void setTaskQueue(ConcurrentLinkedQueue<Task> taskQueue) {
		this.taskQueue = taskQueue;
	}

	public void setResultMap(ConcurrentHashMap<String, BigDecimal> resultMap) {
		this.resultMap = resultMap;
	}

	@Override
	public void run() {
		while(true){
			Task task = taskQueue.poll();
			if(task == null){//no more task to handle
				break;
			}
			resultMap.put(task.getId()+task.getName(), handleTask(task));
		}
		
	}

	private BigDecimal handleTask(Task task) {
		try {
			//simulate task processing
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return task.getScore();
	}

}
