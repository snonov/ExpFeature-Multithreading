package com.sno.explore.multithreading.commontroubles;

public class ThreadInterferenceTrouble {

	
	private int c = 0;

	public void increment() {
		for (int i = 0; i < 10000; i++) {
			c++;
		}

	}

	public void decrement() {
		for (int i = 0; i < 5000; i++) {
			c--;
		}
	}

	public int value() {
		return c;
	}
}
