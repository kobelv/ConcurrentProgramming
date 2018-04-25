package com.kobe.concurrentmode.future;

public class FutureData implements Data{
	private RealData realData;

	private boolean isReady = false;
	
	@Override
	public synchronized String getData() {
		if(!isReady){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return realData.getData();
	}

	public synchronized void setRealData(RealData realData) {
		if(isReady){
			return;
		}
		this.realData = realData;
		isReady = true;
		notify();
	}

}
