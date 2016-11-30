package bg.su.fmi.corejava.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.junit.Before;
import org.junit.Test;

import bg.su.fmi.corejava.searchindirectorytree.Producer;
import bg.su.fmi.corejava.searchindirectorytree.Product;
import bg.su.fmi.corejava.searchindirectorytree.Store;

public class ProducerTest {
	private Product product = new Product();
	private Store store = new Store();
	private Queue<File> textFiles = new ConcurrentLinkedQueue<File>();

	@Before
	public void createFile() throws FileNotFoundException, UnsupportedEncodingException {
		store.clear();
		File file = new File("filename.txt");
		PrintWriter writer = new PrintWriter(file, "UTF-8");
		writer.println("The first line");
		writer.println("The second line");
		writer.close();
		textFiles.add(file);
		textFiles.add(file);
		textFiles.add(file);
	}

	@Test
	public void testAddingToStore() throws InterruptedException {
		Thread producer = new Thread(new Producer(store, textFiles));
		producer.start();
		producer.join();
		assertEquals(false, store.isEmpty());

	}

	@Test
	public void testProductsAddedToStore() throws InterruptedException {
		Thread producer = new Thread(new Producer(store, textFiles));
		producer.start();
		producer.join();
		Product product = new Product("filename.txt", 1, "The first line");
		assertTrue(product.toString().equals(store.poll().toString()));
	}

	@Test
	public void testAddingToFullStore() throws InterruptedException {
		for (int i = 0; i < 1000; i++) {
			store.add(product);
		}
		Thread producer = new Thread(new Producer(store, textFiles));
		producer.start();
		Thread.sleep(500);
		assertEquals(Thread.State.WAITING, producer.getState());
	}

	@Test
	public void testEmptyTextFilesQueue() throws InterruptedException {
		Queue<File> noFiles = new ConcurrentLinkedQueue<File>();
		for (int i = 0; i < 1000; i++) {
			store.add(product);
		}
		Thread producer = new Thread(new Producer(store, noFiles));
		producer.start();
		producer.join();
		assertEquals(Thread.State.TERMINATED, producer.getState());
	}
}
