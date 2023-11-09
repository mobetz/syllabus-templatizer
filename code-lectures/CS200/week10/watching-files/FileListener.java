
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.io.*;
import java.nio.*;
import java.nio.file.*;

public class FileListener implements Runnable {

	
	private WatchService watcher;
	private File watched_file;


	private long last_position;
	private RandomAccessFile open_file;


	public FileListener(File watched_file) {
		this.watched_file = watched_file;
	}

	public void run() {
		System.out.println("Starting the file watcher:");

		try {
			this.open_file = new RandomAccessFile(this.watched_file, "rw");
			this.last_position = this.open_file.getFilePointer();

			this.watcher = FileSystems.getDefault().newWatchService();
			Path folder = this.watched_file.toPath().toAbsolutePath().getParent();

			folder.register(this.watcher, StandardWatchEventKinds.ENTRY_MODIFY);
			boolean watching_file = true;
			while (watching_file) {
				WatchKey key = this.watcher.take();
				List<WatchEvent<?>> things_that_happened = key.pollEvents();
				for (WatchEvent<?> event : things_that_happened) {
					if (event.kind().equals(StandardWatchEventKinds.ENTRY_MODIFY)) {
						Path modified_file = (Path) event.context();
						if (modified_file.equals(this.watched_file.toPath())) {
							/*
							List<String> lines = Files.readAllLines(this.watched_file.toPath());
							System.out.println(lines);
							 */

							long whole_file = this.open_file.length();
							long file_remaining = whole_file - this.last_position;
							if ( file_remaining > 0 ) {

								byte[] rest_of_file = new byte[(int) file_remaining];
								this.open_file.read(rest_of_file);
								String new_part_of_file = new String(rest_of_file, StandardCharsets.UTF_8);
								System.out.print(new_part_of_file);

								this.last_position = this.open_file.getFilePointer();
							}
						}
					}


				}

				key.reset(); //<- when we're done processing each thing that happened, we have to "reset" the watcher so it can listen again
			}

		} catch (Exception e) {
			System.err.println("Failed to create file watcher.");
			e.printStackTrace();
		}


	}


}