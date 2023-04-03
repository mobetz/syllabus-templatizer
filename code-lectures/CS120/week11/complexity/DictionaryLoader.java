



import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


public class DictionaryLoader {
	
	private String filepath;

	public DictionaryLoader(String filepath) {
		this.filepath = filepath;
	}





    public List<String> load_dictionary() throws IOException {

    	List<String> all_words = new ArrayList<String>();

        Scanner word_reader = new Scanner(new File(this.filepath));


        while ( word_reader.hasNextLine() ) {
        	String next_word = word_reader.nextLine();
            next_word = next_word.toLowerCase();
            all_words.add(next_word);
        }

        return all_words;

    }


}