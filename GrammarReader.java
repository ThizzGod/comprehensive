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
	
	private static final Random RNG = new Random();
	
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
     * Generates a random phrase by expanding all non terminals in the input phrase.
     * This version uses an iterative approach with a stack.
	 * 
	 * @param phrase the current phrase
	 * @param grammarMap map of all non terminals and their options
	 * @return a fully written random phrase
	 */
	public static String createNewPhrase(String phrase, HashMap<String, ArrayList<String>> grammarMap) {
		StringBuilder phraseBuilder = new StringBuilder();
	    ArrayList<String> stack = new ArrayList<>();
	    stack.add(phrase);

	    while (!stack.isEmpty()) {
	        String current = stack.remove(stack.size() - 1); 
	        int i = 0;
	        int phraseLength = current.length();
	        boolean replaced = false;
	        while (i < phraseLength) {
	            char character = current.charAt(i);
	            if (character != '<') {
	                phraseBuilder.append(character);
	                i++;
	                continue;
	            }
	            int end = current.indexOf('>', i);
	            String key = current.substring(i, end + 1);
	            ArrayList<String> options = grammarMap.get(key);
	            String replacement = options.get(RNG.nextInt(options.size()));

	            if (end + 1 < phraseLength) {
	                stack.add(current.substring(end + 1));
	            }
	            stack.add(replacement);
	            replaced = true;
	            break;
	        }
	        if (!replaced) {
	            continue;
	        }
	    }
	    return phraseBuilder.toString();
    }
}