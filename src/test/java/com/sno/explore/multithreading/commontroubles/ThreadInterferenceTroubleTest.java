package com.sno.explore.multithreading.commontroubles;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class ThreadInterferenceTroubleTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(ThreadInterferenceTroubleTest.class);
	
	@Test
	void testInterference() {

		LOGGER.info("testInterference");
		
		ThreadInterferenceTrouble threadInterf = new ThreadInterferenceTrouble();
		
	    Runnable r1 = new Runnable() {
	        @Override
	        public void run() {
	            threadInterf.increment();
	        }
	    };

	    Runnable r2 = new Runnable() {
	        @Override
	        public void run() {
	            threadInterf.decrement();
	        }
	    };

	    Thread t1 = new Thread(r1);
	    Thread t2 = new Thread(r2);     
	    t1.start();
	    t2.start();

	    try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			LOGGER.error("InterruptedException " ,e);
		}
	    
	    LOGGER.info("Result of thread work (increment and decrement ones) [{}]", threadInterf.value());	    
	    
	    //10 000 increment and 5 000 decrement if main thread end once two other end
		assertTrue(threadInterf.value()== 5000, "Expected result would be 5000 but value is " + threadInterf.value());
		
	}

}
