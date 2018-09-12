package Threads;

import org.junit.Test;

public class Thread_1a implements Runnable {

	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println(Thread.currentThread().getName());
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		Thread t = new Thread(new Thread_1a());
		t.start();
	}
	@Test
	public void testSomething( ) {
		System.out.println("Test started");
	}
	
}
