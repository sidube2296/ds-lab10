package edu.uwm.cs351;

import java.util.Map;
import java.util.Map.Entry;

public class WordCounter {
	private final Map<String,Integer> map = new SortedArrayMap<String,Integer>((s1, s2)-> s1.compareTo(s2));
	
	/**
	 * Add each word in the sentence to the current word frequency count
	 * Use get to see if the word is already in the map, 
	 * if so, use put to update its count;
	 * if not, use put to add a new entry.
	 * 
	 * @param sentence to add
	 */
	public void addWords(String sentence) {
		String[] words = sentence.split(" ");
		for (String word : words) {
			// TODO add each word and its count to the map--do NOT use loops or change the above lines
			
		}
	}
	
	/**
	 * Returns the frequency count for a given word
	 */
	public int getCount(String word) {
		// TODO 
		// Watch out for null returns
		// (think about which map methods return null and why)
		return 0;
	}
	
	/**
	 * Returns a string representation of the contents of the word frequency count
	 * in the following format
	 * 
	 * word : frequency
	 * 
	 * Each word should be on its own line.
	 * 
	 * This should take linear time. Don't call a map method on each word.
	 */
	public String getContents() {
		String result = "";
		// TODO
		
		return result;
	}
	
	/**
	 * Clears the current word frequency count
	 */
	public void clear() {
		map.clear();
	}
	
	/**
	 * Returns the number of distinct words counted
	 */
	public int numberDistinctWords() {
		return map.size();
	}
}
