package bg.su.fmi.corejava.searchindirectorytree;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Queue;

public class Producer implements Runnable {
	private Queue<File> textFiles;
	private Store store;

	public Producer(Store store, Queue<File> textFiles) {
		this.store = store;
		this.textFiles = textFiles;
	}

	@Override
	public void run() {

		while (!textFiles.isEmpty()) {
			File textFile = textFiles.poll();
			if (textFile != null) {
				try (BufferedReader br = new BufferedReader(new FileReader(textFile))) {
					String line = null;
					Integer lineCounter = 0;
					while ((line = br.readLine()) != null) {

						synchronized (store) {
							if (store.isFull()) {
								store.wait();
							}

							lineCounter++;
							store.add(new Product(textFile.getName(), lineCounter, line));
							store.notifyAll();
						}
					}
				} catch (IOException | InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
