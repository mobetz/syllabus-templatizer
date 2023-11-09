
import java.util.*;
import java.io.*;
import java.nio.*;
import java.nio.file.*;

public class FileListener implements Runnable {

	
	private WatchService watcher;
	private File watched_file;
	private List<String> file_updates;


	private int last_position;
	private RandomAccessFile open_file;


	public FileListener(File watched_file) {
		this.watched_file = watched_file;
	}

	public void run() {
		System.out.println("Starting the file watcher:");

		try { 

		this.watcher = FileSystems.getDefault().newWatchService();
		this.watched_file.toPath().register(this.watcher, StandardWatchEventKinds.ENTRY_MODIFY);
		boolean watching_file = true;
		while (watching_file) {
			WatchKey key = this.watcher.take();
			List<WatchEvent<?>> things_that_happened = key.pollEvents();
			for ( WatchEvent<?> event : things_that_happened) {

				List<String> lines = Files.readAllLines(this.watched_file.toPath());
				System.out.println(lines);

			}

			key.reset(); //<- when we're done processing each thing that happened, we have to "reset" the watcher so it can listen again
		}

		} catch ( Exception e ) {
			System.err.println("Failed to create file watcher.");
			e.printStackTrace();
		}

		

	}

}