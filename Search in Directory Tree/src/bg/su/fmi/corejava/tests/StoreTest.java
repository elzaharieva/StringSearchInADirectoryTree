package bg.su.fmi.corejava.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import bg.su.fmi.corejava.searchindirectorytree.Product;
import bg.su.fmi.corejava.searchindirectorytree.Store;

public class StoreTest {
	Product product = new Product();

	@Test
	public void testStoreIsFull() {
		Store store = new Store();
		for (int i = 0; i <= 1000; i++) {
			store.add(product);
		}
		assertTrue(store.isFull());
	}

	@Test
	public void testStoreIsNotFull() {
		Store store = new Store();
		store.add(product);
		assertFalse(store.isFull());
	}

}
