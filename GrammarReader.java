package comprehensive;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Reads a grammar file and helps create random phrases.
 * This handles non terminals like object and terminals like words.
 * 
 * @author Max Barker & Josi Gac
 * @version 12/2/25
 */
public class GrammarReader {
	
	/**
	 * Default constructor.
	 */
	public GrammarReader() {}
	
	
	/**
	 * Builds a map of all non terminals in the grammar.
	 * 
	 * @param filePath filePath path to the grammar file
	 * @return a map where the key is a non terminal and the value is its list of options
	 */
	public static HashMap<String, ArrayList<String>> buildGrammarMap(String filePath) {
		BufferedReader buff;
		HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
		try {
			buff = new BufferedReader(new FileReader(new File(filePath)));
			String line = buff.readLine();
			while (line != null) {
				while (line != null && !line.equals("{")) {
					line = buff.readLine();
				}
				if (line == null) {
					break;
				}
				String key = buff.readLine();
				line = buff.readLine();
				map.put(key, new ArrayList<String>());
				while (!line.equals("}")) {
					map.get(key).add(line);
					line = buff.readLine();
				}
				line = buff.readLine();
			}
			
			buff.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	return map;	
	}
	
	/**
	 * Recursively creates a random phrase by replacing non terminals.
	 * 
	 * @param phrase the current phrase
	 * @param grammarMap map of all non terminals and their options
	 * @return a fully written random phrase
	 */
	public static String createNewPhrase(String phrase, HashMap<String, ArrayList<String>> grammarMap) {
		Random rng = new Random();
		StringBuilder phraseBuilder = new StringBuilder();
		for (int j = 0; j < phrase.length(); j++) {
			//read and write characters until an open angle bracket is reached
			if (phrase.charAt(j) != '<') {
				phraseBuilder.append(phrase.charAt(j));
			} else {
				//read the nonterminal and write to key
				StringBuilder keyBuilder = new StringBuilder();
				while (phrase.charAt(j) != '>') {
					keyBuilder.append(phrase.charAt(j));
					j++;
				}
				keyBuilder.append('>');
				//use key to get list of child-words
				ArrayList<String> wordList = grammarMap.get(keyBuilder.toString());
				//select a random word from the list and recursively call this method
				String word = wordList.get(rng.nextInt(wordList.size()));
				//if the child word contains another nonterminal it will be terminated and so on
				String newPhrase = createNewPhrase(word, grammarMap);
				//once a phrase of all terminals is reached it will be returned and appended
				phraseBuilder.append(newPhrase);
			}
		}
		//basecase for the recursive method. When a string with no non-terminals is read this will run
		return phraseBuilder.toString();
	}
}
