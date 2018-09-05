package Threads;

public class ThreadApp_Synch {
	public static void main(String[] args) {
		// создаем объект общего ресурса
		CommonResource2 commonResource2 = new CommonResource2();
		// создаем цикл для создания потоков,
		// которые обращаются к общему ресурсу
		for (int i = 1; i < 100; i++) {
			Thread t = new Thread(new CountThread2(commonResource2));
			t.start();
		}
	}
}

//======================== Общий ресурс ===============================
class CommonResource2 {
	int x = 1;
	int y = 1;
}

//=====================================================================
class CountThread2 implements Runnable {
	// создаем ссылку на общий ресурс
	CommonResource2 res;

	// в конструкторе передаем ссылке объект общего ресурса
	public CountThread2(CommonResource2 res) {
		this.res = res;
	}

	public void run() {
		// синхронизируем общий ресурс
		synchronized (res) {
			if(res.x > res.y) System.out.println(res.x + " больше " + res.y);
			else if(res.x < res.y) System.out.println(res.x + " меньше " + res.y);
			else System.out.println(res.x + " равно " + res.y);
			// увеличиваем переменную общего ресурса X и спим 10 мс
			res.x++;
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
			}
			// увеличиваем переменную общего ресурса Y
			res.y++;
		}
	}
}

