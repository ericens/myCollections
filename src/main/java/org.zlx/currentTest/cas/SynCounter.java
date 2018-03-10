package org.zlx.currentTest.cas;

public class SynCounter implements Counter {
	private volatile long counter = 0;


	public synchronized void increment() {
		counter++;
	}

	public long getCounter() {
		return counter;
	}



}
