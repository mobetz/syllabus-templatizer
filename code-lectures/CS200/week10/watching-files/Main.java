

/*
Objectives for Today

By the end of today, you will:
   * Understand how watchers allow us to detect when files change.
   * Write code that responds to file modification events.
   * Get more practice with file access objects.
*/

import java.io.*;

public class Main {

	public static void main(String[] args) {

		FileListener l = new FileListener(new File("watched_file.txt"));

		l.run();

	}
}