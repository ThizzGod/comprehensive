package comprehensive;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Tester class for the Random Phrase Generator.
 * 
 * @author Max Barker & Josi Gac
 * @version 12/2/25
 */
public class RandomPhraseTester {

    public static void main(String[] args) {
        String[] grammarFiles = {"super_simple.g", "poetic_sentence.g", "abc_spaces.g"};

        int numPhrases = 5;  

        for (String grammarFile : grammarFiles) {
            System.out.println("Testing grammar file: " + grammarFile);

            String startPhrase = GrammarReader.getPhrase(grammarFile);
            System.out.println(startPhrase + "\n");

            HashMap<String, ArrayList<String>> grammarMap = GrammarReader.buildGrammarMap(grammarFile);
            for (String key : grammarMap.keySet()) {
                System.out.println(key + " - " + grammarMap.get(key));
            }
            System.out.println();

            System.out.println("Randomly generated phrases:");
            for (int i = 0; i < numPhrases; i++) {
                String phrase = GrammarReader.createNewPhrase(startPhrase, grammarMap);
                System.out.println((i + 1) + ": " + phrase);
            }
        }
    }
}
