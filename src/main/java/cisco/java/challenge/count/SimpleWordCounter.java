package cisco.java.challenge.count;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

import com.google.common.base.Strings;

/**
 * This class counts word occurrences in a given text file.
 * 
 * Assumptions
 * 
 * 1. input file is a text file 
 * 2. "Word" is considered any sequence of alpha characters. 
 * 		The following regex defines delimiters: [^a-zA-Z]+ 
 * 		One can change the definition of a word by updating the regex. 
 * 3. Words are assumed to be from English vocabulary, which are around 1 million 
 * 4. Two words are considered the same if they are equal in lower case. In other words, all
 * 		words are converted to lower case. For example: "Java" is treated the same as
 * 		"java" 
 * 5. Output is sorted DESC by the number of occurrences
 * 
 * Main steps:
 * 	1. Split the input into "words"
 * 	2. Count word occurrences
 * 		- implementation uses memory (hashmap) to count word occurrences
 * 		- for extra large inputs with large number of distinct words,
 * 			 one can modify implementation accordingly:
 * 			- define and split input into a collection of "words"
 * 			- externally sort "words"
 * 			- traverse sorted words and count/store their occurrences
 * 
 * @author stefan
 *
 */
public class SimpleWordCounter {

	private static final String DELIMITER_REGEX = "[^a-zA-Z]+";
	private final HashMap<String, Integer> words;
	private Scanner scanner;

	public SimpleWordCounter(String fileUrl) {
		if (Strings.isNullOrEmpty(fileUrl)) {
			throw new IllegalArgumentException("File url cannot be null or empty");
		}
		this.words = new HashMap<String, Integer>();
		countWords(fileUrl);
	}

	private void countWords(String fileUrl) {
		try {
			this.scanner = new Scanner(new File(fileUrl)).useDelimiter(DELIMITER_REGEX);
			while (scanner.hasNext()) {
				String word = scanner.next().toLowerCase();
				if (words.containsKey(word)) {
					words.put(word, words.get(word) + 1);
				} else {
					words.put(word, 1);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			scanner.close();
		}
	}

	private ArrayList<Map.Entry<String, Integer>> sortDescending(
			Set<Map.Entry<String, Integer>> entrySet) {
		ArrayList<Map.Entry<String, Integer>> entries = new ArrayList<>(entrySet);
		Comparator<Entry<String, Integer>> compatator = new Comparator<Map.Entry<String, Integer>>() {
			@Override
			public int compare(Map.Entry<String, Integer> a, Map.Entry<String, Integer> b) {
				return -1 * a.getValue().compareTo(b.getValue());
			}
		};
		Collections.sort(entries, compatator);
		return entries;
	}

	public void printWordsByFrequency() {
		List<Map.Entry<String, Integer>> entrySet = sortDescending(this.words.entrySet());
		for (Map.Entry<String, Integer> entry : entrySet) {
			System.out.println(entry.getValue() + " " + entry.getKey());
		}
	}

}
