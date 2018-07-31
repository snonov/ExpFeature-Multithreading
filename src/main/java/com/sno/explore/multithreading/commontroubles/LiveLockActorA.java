package com.sno.explore.multithreading.commontroubles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LiveLockActorA {

	private static final Logger LOGGER = LoggerFactory.getLogger(LiveLockActorA.class);
	
	private boolean actionADone = false;
	 
    public void doActorAAction(LiveLockActorB actorB) {
        
    	while (!actorB.isActionBDone()) {
 
        	LOGGER.info("ActorA: waiting ActorB to do its action");
 
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
 
        LOGGER.info("ActorA : do its action");
 
        this.actionADone = true;
    }
 
    public boolean isActionADone() {
        return this.actionADone;
    }
    
}
