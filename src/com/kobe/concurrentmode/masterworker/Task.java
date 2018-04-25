package com.kobe.concurrentmode.masterworker;

import java.math.BigDecimal;

public class Task {
	private int id;
	private String name;
	private BigDecimal score;
	public Task(int id, String name, BigDecimal score) {
		this.id = id;
		this.name = name;
		this.score = score;
	}
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public BigDecimal getScore() {
		return score;
	}
	
	
	
}
