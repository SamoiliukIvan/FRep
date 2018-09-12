package Threads;


import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.Test;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Writer_and_Readers {
	// количество повторов записи/считывания
	private static int numberOfRepetitions = 3;
	// количество реализованных повторов
	private static int iteration; // 0
	// количество читателей
	private static int numberOfReaders = 3;
	// буфер для символов
	private static StringBuffer stringBuffer = new StringBuffer();
	// размер для буфера символов
	private static int sizeOfStringBuffer = 16;
	// время сна потоков
	private static int sleepTime = 15;
	// флаг для выхода из цикла
	private static boolean exitFromCycle; // false
	// объект блокировки потока
	private static ReentrantLock reentrantLock = new ReentrantLock();
	// количество потоков читателей, которые прочли буфер
	private static int numberOfReadBufferReaders = 3;
	// объект для получения случайных символов
	private static Random random = new Random();

	private static class Writer extends Thread {
		@Override
		public void run() {
			// пока флаг exitFromCycle не переключился с false на true
			while (!exitFromCycle) {
				try {
					// и количество читателей, которые не прочли из буфера не стало равно 3
					while (numberOfReadBufferReaders != numberOfReaders) {
						// усыпляем поток на случайное количество микросекунд
						TimeUnit.MICROSECONDS.sleep(sleepTime);
					}
					// и блокируем поток
					reentrantLock.lock();
					// пишем в буфер
					write();
				} catch (InterruptedException e) {
					e.printStackTrace();
					// в любом случае
				} finally {
					// количество читателей сбрасываем до нуля
					numberOfReadBufferReaders = 0;
					// и разблокируем поток
					reentrantLock.unlock();
					// пока количество итераций не стало равно 3
					while (++iteration == numberOfRepetitions) {
						// и если количество еще не прочитавших читателей не стало равно 3
						if (numberOfReadBufferReaders != numberOfReaders) {
							try {
								// засыпаем на случайное количество микросекунд
								TimeUnit.MICROSECONDS.sleep(sleepTime);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						// и переключаем флаг в true
						exitFromCycle = true;
					}
				}
			}
		}
	}

	private static class Reader extends Thread {

		@Override
		public void run() {
			// пока флаг exitFromCycle не переключится с false на true
			while (!exitFromCycle) {
				// и если количество не прочитавших из буфера читателей равно нулю
				if (numberOfReadBufferReaders == 0) {
					try {
						// то блокируем поток
						reentrantLock.lock();
						// и читаем из буфера
						read(getName());
					} finally {
						// в конце увеличиваем число прочитавших
						++numberOfReadBufferReaders;
						// и снимаем блокировку с потока
						reentrantLock.unlock();
					}
				}
				try {
					// если количество прочитавших не равно нулю, то усыпляем поток
					TimeUnit.MICROSECONDS.sleep(sleepTime);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private static void read(String nameOfThread) {
		// выводим на консоль имя потока, который сейчас пытается читать из буфера
		System.out.printf("%s: ", nameOfThread);
		// пока количество прочтенных символов меньше длины буфера
		try {
			for (int j = 0; j < sizeOfStringBuffer; j++) {
				// усыпляем поток
				Thread.sleep(sleepTime);
				// и выводим на консоль прочтенный символ
				System.out.print(stringBuffer.charAt(j));
			}
			// переходим на новую строку
			System.out.println();
			// и усыпляем поток
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static void write() throws InterruptedException {
		// очищаем буфер
		stringBuffer.setLength(0);

		System.out.print("Writer: ");
		// пока количество символов меньше 16
		for (int i = 0; i < sizeOfStringBuffer; i++) {
			// каждый раз усыпляем поток
			Thread.sleep(sleepTime);
			// и получаем случайный символ из английского алфавита
			char c = (char) (random.nextInt(26) + 'a');
			// печатаем каждый символ в консоль
			System.out.print(c);
			// и добавляем в буфер
			stringBuffer.append(c);
		}
		// переходим на новую строку
		System.out.println();
		// и усыпляем поток
		Thread.sleep(sleepTime);
	}

	public static void main(String[] args) throws InterruptedException {

		LocalTime start = LocalTime.now();

		// создаем писателя
		Writer writer = new Writer();

		// создаем список потоков читателей
		ArrayList<Thread> readers = new ArrayList<>();
		for (int i = 0; i < numberOfReaders; i++) {
			readers.add(new Reader());
		}

		// усыпляем поток
		Thread.sleep(sleepTime);

		// и запускаем метод run для каждого потока читателей
		for (Thread reader : readers) {
			reader.start();
		}

		// усыпляем поток
		Thread.sleep(sleepTime);

		// и запускаем поток писателя
		writer.start();

		// ждем окончания работы потока писателя
		writer.join();

		// ждем окончания работы всех потоков читателей
		for (Thread reader : readers) {
			reader.join();
		}

		// определяем затраченное на выполнение программы время
		System.out.println();
		LocalTime end = LocalTime.now();
		System.out.println("Время работы программы - " + (end.getSecond() - start.getSecond()) + " секунды");
	}
}



