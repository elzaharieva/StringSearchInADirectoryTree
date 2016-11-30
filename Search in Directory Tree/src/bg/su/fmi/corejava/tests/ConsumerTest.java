package bg.su.fmi.corejava.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import bg.su.fmi.corejava.searchindirectorytree.Consumer;
import bg.su.fmi.corejava.searchindirectorytree.Product;
import bg.su.fmi.corejava.searchindirectorytree.Store;

public class ConsumerTest {
	private Store store = new Store();
	private Product product = new Product();
	private Thread consumer = new Thread(new Consumer("my", store));

	@Test
	public void foundStringInProductTest() throws InterruptedException {
		product.setLine("myLine");
		store.add(product);
		consumer.start();
		Thread.sleep(200);
		assertTrue(product.isFound());
	}

	@Test
	public void notFoundStringInProductTest() throws InterruptedException {
		product.setLine("First line");
		store.add(product);
		consumer.start();
		assertFalse(product.isFound());
	}

	@Test
	public void waitingWhileStoreIsEmptyTest() throws InterruptedException {
		consumer.start();
		Thread.sleep(200);
		assertEquals(Thread.State.WAITING, consumer.getState());
	}

	@Test
	public void interrupredThreadException() throws InterruptedException {
		consumer.start();
		consumer.interrupt();
		Thread.sleep(200);
		assertEquals(Thread.State.TERMINATED, consumer.getState());
	}

}
