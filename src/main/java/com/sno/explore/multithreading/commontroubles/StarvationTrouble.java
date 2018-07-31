package com.sno.explore.multithreading.commontroubles;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * Starvation : when a greedy thread holds a resource for a long time so other threads are blocked forever
 * <ul>
 * 		<li> Threads are waiting on a resource forever (synchronized code, I/O, ...)</i>
 * 		<li> Thread doesn’t get CPU’s time for execution coz low priority compared to other threads which have higher priority</i>
 * </ul>
 */
public class StarvationTrouble {

	private static final Logger LOGGER = LoggerFactory.getLogger(StarvationTrouble.class);
	
	public synchronized void doMyJob() {
        String name = Thread.currentThread().getName();
        
        File filePathExec = new File(".");
        String fileName = filePathExec.getAbsolutePath() + "//target//" + name + ".txt";
 
        try (
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        ) {
            writer.write("Thread " + name + " wrote this mesasge");
        } catch (IOException ex) {
        	LOGGER.error("IOException ", ex);
        }
 
        while (true) {
        	LOGGER.info(name + " is working write [{}] done but still working", fileName);
        }
    }
	
}
