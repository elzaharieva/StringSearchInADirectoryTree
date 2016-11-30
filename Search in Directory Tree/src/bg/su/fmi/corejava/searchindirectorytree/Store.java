package bg.su.fmi.corejava.searchindirectorytree;

import java.util.concurrent.ConcurrentLinkedQueue;

public class Store extends ConcurrentLinkedQueue<Product> {

	private static final long serialVersionUID = 1L;
	private final Integer CAPACITY = 1000;

	public boolean isFull() {
		return this.size() >= CAPACITY;
	}

}
