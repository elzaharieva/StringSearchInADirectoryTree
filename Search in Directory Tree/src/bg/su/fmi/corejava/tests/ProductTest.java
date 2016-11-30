package bg.su.fmi.corejava.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import bg.su.fmi.corejava.searchindirectorytree.Product;

public class ProductTest {
	Product product = new Product();

	@Test
	public void testToString() {
		assertEquals("FileName: '', Line Number: '-1', Line: ''", product.toString());
	}

}
