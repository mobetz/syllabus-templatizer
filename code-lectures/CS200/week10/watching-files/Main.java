

/*
Objectives for Today

By the end of today, you will:
   * Understand how watchers allow us to detect when files change.
   * Write code that responds to file modification events.
   * Get more practice with file access objects.
*/

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;
import java.util.concurrent.Executors;

public class Main {

	public static void main(String[] args) {
		File target_file = new File("watched_file.txt");

		FileListener l = new FileListener(target_file);


		Executors.newSingleThreadExecutor().submit(l);


		Scanner s = new Scanner(System.in);
		System.out.print("Enter a username: ");
		String username = s.nextLine();

			while ( true ) {
				try {
					String input = username + " >> " + s.nextLine() + "\n";
					Files.writeString(target_file.toPath(), input, StandardOpenOption.APPEND);
				} catch ( Exception e) {
					System.out.println("Failed to write to file:" + e.getMessage());
				}
			}

	}
}