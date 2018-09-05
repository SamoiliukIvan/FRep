package Threads;
//========================= Класс с мейном ===========================
public class ThreadApp {

	public static void main(String[] args) {
		// создаем объект общего ресурса
		CommonResource commonResource = new CommonResource();
		// создаем цикл для создания потоков,
		// которые обращаются к общему ресурсу
		for (int i = 1; i < 100; i++) {
			Thread t = new Thread(new CountThread(commonResource));
			t.start();
		}
	}
}

//======================== Общий ресурс ===============================
class CommonResource {
	int x = 1;
	int y = 1;
}

//=====================================================================
class CountThread implements Runnable {
	// создаем ссылку на общий ресурс
	CommonResource res;

	// в конструкторе передаем ссылке объект общего ресурса
	public CountThread(CommonResource res) {
		this.res = res;
	}

	public void run() {
		if(res.x > res.y) System.out.println(res.x + " больше " + res.y);
		else if(res.x < res.y) System.out.println(res.x + " меньше " + res.y);
		else System.out.println(res.x + " равно " + res.y);
		// увеличиваем переменную общего ресурса X и спим 10 мс
		res.x++;
		// в цикле увеличиваем переменную общего ресурса и спим 100 мс
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
		}
		// увеличиваем переменную общего ресурса Y
		res.y++;
	}
}
