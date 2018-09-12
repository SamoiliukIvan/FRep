package Threads;

import java.time.LocalDateTime;
import java.util.Random;

public class MaxFromArray_1 {
	public static void main(String[] args) {
		int[][] array = new int[4][100];
		Random random = new Random();
		// массив
		for (int i = 0; i < 4; ++i)
			for (int j = 0; j < 100; ++j)
				array[i][j] = random.nextInt(100);
		// переменная для хранения максимального значения
		int max = 0;
		// начальное время
		LocalDateTime startTime = LocalDateTime.now();
		long sTime = startTime.getNano();
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				if (max < array[i][j]) {
					max = array[i][j];
				}
			}
		}
		// конечное время
		LocalDateTime finishTime = LocalDateTime.now();
		long fTime = finishTime.getNano();
		System.out.println("Максимальный элемент двумерного массива " + max + ". Затраченное время: " + (fTime - sTime)
				+ " наносекунд");
	}
}

