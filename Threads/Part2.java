package Threads;

import java.util.Date;

public class Part2 {

	public Part2() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		Date t1 = new Date(2000);
		Date t2 = new Date(800);
		Date t3 = new Date(7000);
		Date t4 = new Date(2000);
		Date t5 = new Date(800);
		Date t6 = new Date(7000);
		Date[] arrayDates = { t1, t2, t3, t4, t5, t6 };

		String m1 = "First message";
		String m2 = "Second message";
		String m3 = "Third message";
		String m4 = "Forth message";
		String m5 = "Fifth message";
		String m6 = "Sixth message";
		String[] arrayStrings = { m1, m2, m3, m4, m5, m6 };
		
		Spam spam = new Spam(arrayDates, arrayStrings);
		spam.start();
		try {
			spam.join(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.exit(0);
	}
}
