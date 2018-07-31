package com.sno.explore.multithreading.commontroubles;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.CountDownLatch;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class StarvationTroubleTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(StarvationTroubleTest.class);
	
	private static final int NB_WORKER = 10;
	
	@Test
	void testStarvation() {
		LOGGER.info("testStarvation");
		
		StarvationTrouble worker = new StarvationTrouble();
        CountDownLatch countDownLatch = new CountDownLatch(NB_WORKER);
		
        for (int i = 0; i < NB_WORKER; i++) {
            new Thread(new Runnable() {
                public void run() {
                    worker.doMyJob();
                    countDownLatch.countDown();
                }
            }).start();
        }
        
        try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			LOGGER.error("InterruptedException ", e);
		}
        assertTrue(true);
	}

}
