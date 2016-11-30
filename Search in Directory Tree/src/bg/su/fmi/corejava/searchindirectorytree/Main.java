package bg.su.fmi.corejava.searchindirectorytree;

import java.io.File;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Main {

	private static final Integer PRODUCERS_COUNT = 6;
	private static final Integer CONSUMERS_COUNT = 4;
	public static String stringToFind;
	public static String directory;
	private static Store store = new Store();
	private static ArrayList<Thread> consumers = new ArrayList<Thread>();
	private static ArrayList<Thread> producers = new ArrayList<Thread>();
	private static Queue<File> textFiles = new ConcurrentLinkedQueue<File>();

	public static void main(String[] args) {

		input();

		long t0 = System.currentTimeMillis();

		addConsumers();
		addProducers();

		for (Thread c : consumers) {
			c.start();
			System.out.println("Consumer " + c.getName() + " started");
		}

		getAllTextFiles(directory);

		for (Thread p : producers) {
			p.start();
			System.out.println("Producer " + p.getName() + " started");
		}

		int i = 0;
		while (i < PRODUCERS_COUNT) {
			if (!producers.get(i).isAlive() && store.isEmpty()) {
				i++;
			}
		}
		for (Thread c : consumers)
			c.interrupt();

		long t1 = System.currentTimeMillis();
		System.out.println(t1 - t0);
	}

	private static void input() {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Enter the string you are searching for: ");
		stringToFind = scanner.next();

		System.out.println("Enter directory to search in: ");
		directory = scanner.next();

		while (!isValid(directory)) {
			System.out.println("This is not a valid directory. Enter a valid one: ");
			directory = scanner.next();
		}
		scanner.close();
	}

	public static boolean isValid(String dir) {
		File file = new File(dir);
		if (file.isDirectory())
			return true;
		return false;

	}

	public static void getAllTextFiles(String directoryRoot) {
		File root = new File(directoryRoot);
		if (root.listFiles() != null) {
			File[] allFiles = root.listFiles();
			for (File file : allFiles) {
				if (file.isFile() && file.getAbsolutePath().endsWith(".txt")) {
					textFiles.add(file);
				}
				if (file.isDirectory()) {
					getAllTextFiles(file.getAbsolutePath());
				}
			}
		}
	}

	public static void addProducers() {
		for (int i = 0; i < PRODUCERS_COUNT; i++) {
			Thread producer = new Thread(new Producer(store, textFiles));
			producers.add(producer);
		}
	}

	public static void addConsumers() {
		for (int i = 0; i < CONSUMERS_COUNT; i++) {
			Thread consumer = new Thread(new Consumer(stringToFind, store));
			consumers.add(consumer);
		}
	}

	public static Queue<File> getTextFiles() {
		return textFiles;
	}

	public static ArrayList<Thread> getConsumers() {
		return consumers;
	}

	public static ArrayList<Thread> getProducers() {
		return producers;
	}

	public static Integer getConsumersCount() {
		return CONSUMERS_COUNT;
	}

	public static Integer getProducersCount() {
		return PRODUCERS_COUNT;
	}
}