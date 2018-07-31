package com.sno.explore.multithreading.commontroubles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * Livelock : when two threads are busy responding to actions of each other.
 * Threads are not blocked when livelock occurs but it runs in an infinite loop
 *
 */
public class LiveLockTrouble {

	private static final Logger LOGGER = LoggerFactory.getLogger(LiveLockTrouble.class);

	private LiveLockActorA actorA = new LiveLockActorA();
	private LiveLockActorB actorB = new LiveLockActorB();

	public void runActors() {

		LOGGER.info("runActors");

		Thread t1 = new Thread(new Runnable() {
			public void run() {
				actorA.doActorAAction(actorB);
			}
		});
		t1.start();

		Thread t2 = new Thread(new Runnable() {
			public void run() {
				actorB.doActorBAction(actorA);
			}
		});
		t2.start();
	}

	public boolean isConditionOk() {

		return actorA.isActionADone() && actorB.isActionBDone();
	}

}
