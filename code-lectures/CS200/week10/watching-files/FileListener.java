
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.io.*;
import java.nio.*;
import java.nio.file.*;

public class FileListener implements Runnable {

	/*
	To create my file watcher, I need to at least two pieces of state:
	   * What file is being watched
	   * An object that will do the actual watching for me and provide me with updates
	 */
	private File watched_file;
	private WatchService watcher; //<- WatchService is an object with many methods related to file updates


	/*
	If I were feeling extra motivated, I could even keep track of how much of the file I've read:
	 */
	private long last_position;
	private RandomAccessFile open_file;


	public FileListener(File watched_file) {
		this.watched_file = watched_file;
	}

	public void run() {
		/*
		I'm going to put all the file watching code in a method called run(). It turns out there's even an interface
		for objects with a method like this called Runnable. I'm going to choose to implement Runnable on  my class
		because it's going to help me a bit later!
		 */
		System.out.println("Starting the file watcher:");

		try {
			/*
			If I'm doing the 'fancy' updates-only version of this code, I can create my RandomAccessFile here.
			A RandomAccessFile is a lower level version of a file, that allows me to read just a small part of a file.
			This is helpful if I fear my file might get extremely long, and I need to take only a small part of it.
			 */
			this.open_file = new RandomAccessFile(this.watched_file, "rw");
			this.last_position = this.open_file.getFilePointer(); //<- doing this, I will also track "where I last was" in the file, starting at 0

			/*
			In order to watch files on my computer, I need my file system to give me a "watcher". Fortunately,
			Java has a factory method to give me an object with these capabilities:
			 */
			this.watcher = FileSystems.getDefault().newWatchService();
			/*
			In Java, Watchers can only watch folders, so I must obtain the path that my watched file lives in:
			 */
			Path folder = this.watched_file.toPath().toAbsolutePath().getParent();

			/*
			Once I've got that path, I can tell the Path to register my watcher, and then I'm all set! The watcher
			will let me know any time anything in that folder changes (note: this means we could even watch more than
			one file!)
			 */
			folder.register(this.watcher, StandardWatchEventKinds.ENTRY_MODIFY); //<- the second arg here describes what kinds of
			folder                                                              // changes I'm interested in


			/*
			Actually extracting the updates from the Watcher is slightly tricky. In order to do this, I have to do three
			steps:
			    - tell the watcher I want to "take" a list of updates, giving me a WatchKey.
			    - Loop through each event in the key and process it.
			    - Release the key back to the watcher with the "reset()" method, so that it is ready to watch again.
			 */
			boolean watching_file = true;
			while (watching_file) {
				WatchKey key = this.watcher.take(); //<- much like Scanner.nextLine(), WatchService.take() will "block" and wait
				                                   // for changes here.
				/*
				Once we get back a list of events in our Key, we can extract it with the pollEvents() function
				 */
				List<WatchEvent<?>> things_that_happened = key.pollEvents();

				/*
				Then, it's just a matter of looping through and responding to each:
				 */
				for (WatchEvent<?> event : things_that_happened) {
					// We care about an event if it is a file change....
					if (event.kind().equals(StandardWatchEventKinds.ENTRY_MODIFY)) {
						Path modified_file = (Path) event.context();
						// ...and if the file it changed is the one we're watching!
						if (modified_file.equals(this.watched_file.toPath())) {
							/*
							// In the simplest example, we could even just print out the new version of the file:

							List<String> lines = Files.readAllLines(this.watched_file.toPath());
							System.out.println(lines);
							 */

							/*
							If we want to get fancier, we can try reading just the part of the file that changed.
							To do that, we need to use a special File object called a RandomAccessFile:
							 */
							long whole_file = this.open_file.length();
							long file_remaining = whole_file - this.last_position;
							if ( file_remaining > 0 ) {

								byte[] rest_of_file = new byte[(int) file_remaining];
								/*
								A RandomAccessFile reads a file as a bunch of bytes, which are just numbers between
								0 and 255. We then have to construct a String out of those bytes, telling Java which
								decoding rules to use:
								 */
								this.open_file.read(rest_of_file);
								String new_part_of_file = new String(rest_of_file, StandardCharsets.UTF_8);

								System.out.print(new_part_of_file);

								/*
								Then we can update our 'cursor' to point to the new end of the file:
								 */
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