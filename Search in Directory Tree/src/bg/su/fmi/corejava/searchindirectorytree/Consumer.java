package bg.su.fmi.corejava.searchindirectorytree;

public class Consumer implements Runnable {
	private Store store;
	private String stringToFind;

	public Consumer(String stringToFind, Store store) {
		this.stringToFind = stringToFind;
		this.store = store;
	}

	@Override
	public void run() {
		while (true) {
			synchronized (store) {
				while (!store.isEmpty()) {
					Product product = store.poll();
					if (product.getLine().contains(stringToFind)) {
						product.setFound(true);
						System.out.println(product);
					}
					store.notifyAll();
				}

				try {
					store.wait();
				} catch (InterruptedException e) {
					break;
				}
			}
		}
	}
}
