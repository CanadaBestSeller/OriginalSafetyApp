package com.example.safetyalert;

public class ThreadSleepRunnable implements Runnable {

	private int duration;
	
	public ThreadSleepRunnable(int duration) {
		this.duration = duration;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
} 