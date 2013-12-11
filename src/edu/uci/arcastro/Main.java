package edu.uci.arcastro;

import static com.wagnerandade.coollection.Coollection.from;
import static com.wagnerandade.coollection.Coollection.greaterThan;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		System.out.print("Reading dictionaries from file...");
		
		Dictionary.LoadSyllableDictionary("syllables.txt");
		Dictionary.LoadPOSDictionary("part-of-speech_UPDATED.txt");
		Parser.TrainWithCorpus("big.txt");
		Parser.TrainWithWAN("wan/wan-processed.txt");
		
		System.out.println("Done");
		System.out.println("Enter a syllable number: ");
		//Given a number
		//Find a random, frequent word of that many syllables
		//And print the word, along with its POS and associated words.
		Random r = new Random();
		Scanner s = new Scanner(System.in);
		while(s.hasNextLine())
		{
			int count = Integer.parseInt(s.nextLine());
			if(!Dictionary.SyllableDictionary.containsKey(count))
			{
				System.out.println("No words of that many syllables were found.");
				continue;
			}
			
			List<Word> words = from(Dictionary.SyllableDictionary.get(count)).where("frequency", greaterThan(1)).all();
			if(words.size() == 0)
			{
				System.out.println("No frequent (>1) words of that many syllables were found.");
				continue;
			}
			
			Word word = words.get(r.nextInt(words.size()));
			System.out.format("WORD: %s\nParts of Speech: ", word.spelling);
			for(POS p : word.PartsOfSpeech)
				System.out.print(p.toString() + ", ");
			System.out.println();

			System.out.print("Sentence Associated Words: ");
			Set<Entry<Word, Integer>> associated = word.Associated.entrySet();
			for(Entry<Word, Integer> w : associated)
				System.out.print(w.getKey().spelling + " ");
			System.out.println("\n");

			if (word.hasWanResponses()) {
				System.out.println("WAN response words:");
				Set<Entry<Word, Integer>> responses = word.WordToWanFrequency.entrySet();
				for (Entry<Word, Integer> entry : responses) {
					System.out.print("(" + entry.getKey().spelling + ", " + entry.getValue());
				}
			} else {
				System.out.println("No WAN targets for this word.");
			}

			System.out.println("\n");
		}
		s.close();
	}

}
