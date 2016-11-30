package bg.su.fmi.corejava.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import bg.su.fmi.corejava.searchindirectorytree.Main;

public class MainTest {

	@Test
	public void textFilesCrowlerTest() {
		Main.getAllTextFiles("D:/fld");
		assertEquals(208, Main.getTextFiles().size());
	}

	@Test
	public void validPathTest() {
		assertTrue(Main.isValid("D:/fld"));
	}

	@Test
	public void notValidPathTest() {
		assertFalse(Main.isValid("D:/shgal"));
	}

	@Test
	public void producersTest() {
		Main.addProducers();
		assertEquals((int) Main.getProducersCount(), Main.getProducers().size());
	}

	@Test
	public void consumersTest() {
		Main.addConsumers();
		assertEquals((int) Main.getConsumersCount(), Main.getConsumers().size());
	}
}
