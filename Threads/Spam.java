package Threads;

import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

public class Spam extends Thread {
	// массив временных интервалов
	Date[] timeArray;
	// массив сообщений
	String[] messageArray;

	public Spam(Date[] tArray, String[] mArray) {
		timeArray = tArray;
		messageArray = mArray;
	}

	public void run() {
		int i = 0;
		for (Date d : timeArray) {

			try {
				sleep(d.getTime());

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			for (; i < messageArray.length; i++) {
				System.out.println(messageArray[i]);
				++i;
				break;
			}
		}
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
		String m4 = "First message";
		String m5 = "Second message";
		String m6 = "Third message";
		String[] arrayStrings = { m1, m2, m3, m4, m5, m6 };

		Spam sp = new Spam(arrayDates, arrayStrings);
		sp.start();
		char ch;
		int code;
		try {
			while (-1 != (code = System.in.read())) {
				ch = (char) code;

				if ('\n' == ch) {
					System.exit(0);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
