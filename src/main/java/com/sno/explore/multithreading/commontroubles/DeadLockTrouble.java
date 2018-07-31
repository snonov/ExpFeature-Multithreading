package com.sno.explore.multithreading.commontroubles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * Deadlock : when two more threads are blocked because of waiting for each other resources acquired forever
 *
 */
public class DeadLockTrouble {

	private static final Logger LOGGER = LoggerFactory.getLogger(DeadLockTrouble.class);
	
    private final Object lock1 = new Object();
    private final Object lock2 = new Object();
    
    //use those two lock on second task to run with out deadlock
//    private final Object lock3 = new Object();
//    private final Object lock4 = new Object();
 
    public void firstTask() {
    	LOGGER.info("firstTask getting lock1");
        synchronized (lock1) {
        	LOGGER.info("firstTask getting lock2");
            synchronized (lock2) {
            	LOGGER.info("Done firstTask");
            }
        }
    }
 
    public void secondTask() {
    	LOGGER.info("secondTask getting lock2");
        synchronized (lock2) {
        	LOGGER.info("secondTask getting lock1");
            synchronized (lock1) {
            	LOGGER.info("Done secondTask");
            }
        }
    }

}
