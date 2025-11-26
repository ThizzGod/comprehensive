package comprehensive;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class GrammarReader {
	public GrammarReader() {

	}
	
	public static String getPhrase(String filePath) {
		BufferedReader buff;
		StringBuilder builder = new StringBuilder();
		try {
			buff = new BufferedReader(new FileReader(new File(filePath)));
			while (!buff.readLine().equals("{")) {
				;
			}
			while (!buff.readLine().equals("}")) {
				builder.append(buff.readLine());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return builder.toString();
	}
	
	public static HashMap<String, ArrayList<String>> buildGrammarMap(String filePath) {
		BufferedReader buff;
		HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
		try {
			buff = new BufferedReader(new FileReader(new File(filePath)));
			while (!buff.readLine().equals("}")) {
				;
			}
			while (buff.readLine() != null) {
				while (!buff.readLine().equals("{")) {
					;
				}
				String key = buff.readLine();
				map.put(key, new ArrayList<String>());
				String line = buff.readLine();
				while (!line.equals("}")) {
					map.get(key).add(line);
					line = buff.readLine();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	return map;	
	}
	
	public static String createNewPhrase(String phrase, HashMap<String, ArrayList<String>> grammarMap) {
		Random rng = new Random();
		StringBuilder phraseBuilder = new StringBuilder();
		for (int j = 0; j < phrase.length(); j++) {
			if (phrase.charAt(j) != '<') {
				phraseBuilder.append(phrase.charAt(j));
			} else {
				StringBuilder keyBuilder = new StringBuilder();
				while (phrase.charAt(j) != '>') {
					keyBuilder.append(phrase.charAt(j));
					j++;
				}
				keyBuilder.append('>');
				ArrayList<String> wordList = grammarMap.get(keyBuilder.toString());
				String word = wordList.get(rng.nextInt(wordList.size()));
				String newPhrase = createNewPhrase(word, grammarMap);
				phraseBuilder.append(newPhrase);
			}
		}
		return phraseBuilder.toString();
	}
	
}
