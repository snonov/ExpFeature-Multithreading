package com.sno.explore.multithreading.commontroubles;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class LiveLockTroubleTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(LiveLockTroubleTest.class);
	
	@Test
	void testRunActors() {
		LOGGER.info("testRunActors");
		
		LiveLockTrouble running = new LiveLockTrouble();
		running.runActors();
		
		while (!running.isConditionOk()) {
			
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }	
		}
		//if we go here, this is isConditionOk true
		assertTrue(true);
	}

}
