package comprehensive;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Main class to generate random phrases from a grammar file.
 * 
 * @author Max Barker & Josi Gac
 * @version 12/2/25
 */
public class RandomPhraseGenerator {

	/**
	 * Reads the grammar and prints random phrases.
	 * 
	 * @param args args[0] = grammar file path and args[1] = number of phrases.
	 */
	public static void main(String[] args) {
		HashMap<String, ArrayList<String>> grammarMap = GrammarReader.buildGrammarMap(args[0]);
		for (int i = 0; i < Integer.valueOf(args[1]); i++) {
			System.out.println(GrammarReader.createNewPhrase(grammarMap.get("<start>").get(0), 
					grammarMap));
		}
	}
}
