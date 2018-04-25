package com.kobe.concurrentmode.masterworker;

import java.math.BigDecimal;
import java.util.Random;

public class MasterWorkerTest {

	public static void main(String[] args) {
		//1000 workers will be triggered
		Master m = new Master(new Worker(), 1000);
		Random r = new Random();
		for(int i=0; i<200; i++){
			//submit 200 tasks
			m.submit(new Task(i, "Task"+i, new BigDecimal(r.nextDouble())));
		}
		m.execute();
		
		long startTime = System.currentTimeMillis();
		BigDecimal totalScore;
		while(true){
			if(m.isCompleted()){
				totalScore = m.getResult();
				break;
			}
		}
		long endTime = System.currentTimeMillis();
		System.out.println(String.format("Total score is calculated: %s, cost time: %s.",
				totalScore, endTime-startTime));
	}

}
