package com.kobe.concurrentmode.masterworker;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Master {
	//a queue to hold tasks
	private ConcurrentLinkedQueue<Task> taskQueue = new ConcurrentLinkedQueue<>();
	
	//master needs to know all its workers in terms of process, status
	private ConcurrentHashMap<String, Thread> workersMap = new ConcurrentHashMap<>();
	
	//a map to hold final result
	private ConcurrentHashMap<String, BigDecimal> resultMap = new ConcurrentHashMap<>();
	
	public Master(Worker worker, int threadCount) {
		worker.setTaskQueue(this.taskQueue);
		worker.setResultMap(this.resultMap);
		for(int i = 0; i < threadCount; i++){
			workersMap.put("worker"+i, new Thread(worker));
		}
	}

	public void submit(Task task) {
		this.taskQueue.add(task);
	}

	public void execute() {
		for(Map.Entry<String, Thread> e : workersMap.entrySet()){
			e.getValue().start();
		}
	}

	public boolean isCompleted() {
		for(Map.Entry<String, Thread> e : workersMap.entrySet()){
			if(e.getValue().getState() != Thread.State.TERMINATED){
				return false;
			}
		}
		return true;
	}

	public BigDecimal getResult() {
		BigDecimal total = BigDecimal.ZERO;
		for(Map.Entry<String, BigDecimal> e : resultMap.entrySet()){
			total = total.add(e.getValue());
		}
		return total;
	}

}
