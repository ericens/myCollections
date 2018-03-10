package org.zlx.currentTest.cas;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ClientTest {


	public static void main(String args[]) throws Exception {

		System.out.println("CASCountertest--------------..............." );
		Counter c = new AtomicCounter();
		test(c);



		System.out.println("SynCountertest--------------..............." );
		c = new SynCounter();
		test(c);

	}

	public static void test(Counter c) throws Exception{
		int NUM_OF_THREADS = 100;
		int NUM_OF_INCREMENTS = 100000;

		ExecutorService service = Executors.newFixedThreadPool(NUM_OF_THREADS);

		long before = System.currentTimeMillis();

		for (int i = 0; i < NUM_OF_THREADS; i++) {
			service.submit(new CounterClient(c, NUM_OF_INCREMENTS));
		}
		service.shutdown();
		service.awaitTermination(1, TimeUnit.MINUTES);
		long after = System.currentTimeMillis();
		System.out.println("Counter result: " + c.getCounter());
		System.out.println("Time passed in ms:" + (after - before));
	}
}
