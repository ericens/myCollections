package org.zlx.currentTest.cas;

import java.util.concurrent.atomic.AtomicLong;

public class AtomicCounter implements Counter {
	private AtomicLong counter = new AtomicLong(0);

	public void increment() {
		counter.incrementAndGet();
	}

	public long getCounter() {

		return counter.get();
	}



}
