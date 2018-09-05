package Threads;

import java.time.LocalDateTime;
import java.util.Random;

public class Thread_Array2 {
	public static void main(String[] args) {
		
	}
}

class SoloThread implements Runnable {
	
	ArrayStore aStore;
	public SoloThread(ArrayStore aStore) {
		this.aStore = aStore;
	}
		
	public void run() {
		int max = aStore.array[0][0];
		aStore.initializeArray();

		for (int i = 0; i < aStore.array[0].length; i++) {

			for (int j = 0; j < aStore.array.length; j++) {

				if (max < aStore.array[j][i]) {
					max = aStore.array[j][i];
				}

			}
		}
	}
}

	class ArrayStore {
		int[][] array = new int[4][100];
		void initializeArray() {
		Random random = new Random();
		// массив
		for (int i = 0; i < 4; ++i)
			for (int j = 0; j < 100; ++j)
				array[i][j] = random.nextInt(100);
		}
}

