package comprehensive;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class RandomPhraseGenerator {

	public static void main(String[] args) {
		HashMap<String, ArrayList<String>> grammarMap = GrammarReader.buildGrammarMap(args[0]);
		for (int i = 0; i < Integer.valueOf(args[1]); i++) {
			System.out.println(GrammarReader.createNewPhrase(grammarMap.get("<start>").get(0), 
					grammarMap));
		}
	}
}
