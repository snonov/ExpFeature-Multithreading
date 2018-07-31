package com.sno.explore.multithreading.commontroubles;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * Date not thread safe and you can get inconsistent result
 * 
 * Log sample
 * 2018-07-31 15:37:07.093 [main] INFO  c.s.e.m.c.RaceConditionTrouble - 20180731 15:07:07 093
 * 2018-07-31 15:37:07.093 [Thread-0] INFO  c.s.e.m.c.RaceConditionTrouble - 20190131 15:01:07 093
 * 2018-07-31 15:37:07.093 [main] INFO  c.s.e.m.c.RaceConditionTrouble - 20191201 15:12:07 093
 * 2018-07-31 15:37:07.093 [Thread-1] INFO  c.s.e.m.c.RaceConditionTrouble - 20191201 15:12:07 093
 *
 */
public class RaceConditionTrouble {

	private static final Logger LOGGER = LoggerFactory.getLogger(RaceConditionTrouble.class);
	
	public static void main (String[] args) {
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:MM:ss SSS");
		
		String formatDate = sdf.format(date);
		LOGGER.info("Original " + formatDate);
		
		CountDownLatch countDownLatch = new CountDownLatch(2);
		
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				countDownLatch.countDown();
				date.setMonth(12);
				String formatDate = sdf.format(date);
				LOGGER.info("With set month december " + formatDate);
			}
		});
		t1.start();

		Thread t2 = new Thread(new Runnable() {
			public void run() {
				countDownLatch.countDown();
				date.setMonth(10);
				String formatDate = sdf.format(date);
				LOGGER.info("With set month october " + formatDate);
			}
		});
		t2.start();
		
		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		LOGGER.info(sdf.format(date));
	}
	
}
