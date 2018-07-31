package com.sno.explore.multithreading.commontroubles;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class DeadLockTroubleTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(DeadLockTroubleTest.class);
	
	//To maximize chance to get deadlock increment
	private static final int NB_COUPLE_THREAD = 1;
	
	@Test
	void testDeadLockTrouble() {

		LOGGER.info("testDeadLockTrouble");
		
		//To check global action of threads
		AtomicInteger countTaskDone = new AtomicInteger();
		
		DeadLockTrouble program = new DeadLockTrouble();
		//To run thread working part on lock more simultaneous
		CountDownLatch countDownLatch = new CountDownLatch(NB_COUPLE_THREAD * 2);
		//To let Test main thread wait for threads action (but will never occurs if deadlock occurs before) 
		CountDownLatch countDownLatchTest = new CountDownLatch(NB_COUPLE_THREAD * 2);
		
        for (int i = 0; i < NB_COUPLE_THREAD; i++) {
        	
            new Thread(new Runnable() {
                public void run() {
                	countDownLatch.countDown();
                	try {
						countDownLatch.await();
					} catch (InterruptedException e) {
						LOGGER.error("InterruptedException ", e);
					}
                	LOGGER.info("firstTask will increment");
                	program.firstTask();
                	countTaskDone.incrementAndGet();
                	LOGGER.info("countTaskDone firstTask value [{}]", countTaskDone.get());
                	LOGGER.info("firstTask increment done");
                	countDownLatchTest.countDown();
                }
            }).start();
            
            new Thread(new Runnable() {
                public void run() {
                	countDownLatch.countDown();
                	try {
						countDownLatch.await();
					} catch (InterruptedException e) {
						LOGGER.error("InterruptedException ", e);
					}
                	LOGGER.info("secondTask will increment");
                	program.secondTask();
                	countTaskDone.incrementAndGet();
                	LOGGER.info("countTaskDone secondTask value [{}]", countTaskDone.get());
                	LOGGER.info("secondTask increment done");
                	countDownLatchTest.countDown();
                }
            }).start();
        }
        
        //not ending, check deadlock with JConsole or visualVM
        LOGGER.info("Wait for countDownLatchTest");
        try {
			countDownLatchTest.await();
		} catch (InterruptedException e) {
			LOGGER.error("InterruptedException ", e);
		}
        assertEquals(NB_COUPLE_THREAD * 2, countTaskDone.get());
	}

}
