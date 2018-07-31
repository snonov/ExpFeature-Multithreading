package com.sno.explore.multithreading.commontroubles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LiveLockActorB {

	private static final Logger LOGGER = LoggerFactory.getLogger(LiveLockActorB.class);
	
    private boolean actionBDone = false;
    
    public void doActorBAction(LiveLockActorA actorB) {
 
        while (!actorB.isActionADone()) {
 
        	LOGGER.info("ActorB: waiting ActorA to do its action");
 
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
 
        LOGGER.info("ActorB: do its action");
 
        this.actionBDone = true;
    }
 
    public boolean isActionBDone() {
        return this.actionBDone;
    }
    
}
