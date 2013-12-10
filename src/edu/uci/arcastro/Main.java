package edu.uci.arcastro;

import static com.wagnerandade.coollection.Coollection.from;
import static com.wagnerandade.coollection.Coollection.greaterThan;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map.Entry;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		System.out.print("Reading dictionaries from file...");
		Global.Initialize();
		System.out.println("Done");
		
		System.out.print("Enter a word: ");
		//Given a number
		//Find a random, frequent word of that many syllables
		//And print the word, along with its POS and associated words.
		Random r = new Random();
		Scanner s = new Scanner(System.in);
		while(s.hasNextLine())
		{
			String word_spelling = s.nextLine().toUpperCase();
			if(!Dictionary.Words.containsKey(word_spelling))
			{
				System.out.println("No word with that exact spelling was found.");
				continue;
			}
			Word word = Dictionary.Words.get(word_spelling);
			System.out.format("WORD: %s\nParts of Speech: ", word.spelling);
			for(POS p : word.PartsOfSpeech)
				System.out.print(p.toString() + ", ");
			System.out.println();

			System.out.print("Associated Words: ");
			Set<Entry<Word, Integer>> associated = word.Associated.entrySet();
			for(Entry<Word, Integer> w : associated)
				System.out.print(w.getKey().spelling + " ");
			System.out.println("\n");
			
			System.out.print("Collocated Before: ");
			Set<Entry<Word, Integer>> before = word.CollocatedBefore.entrySet();
			for(Entry<Word, Integer> w : before)
				System.out.print(w.getKey().spelling + " ");
			System.out.println("\n");

			System.out.print("Collocated After: ");
			Set<Entry<Word, Integer>> after = word.CollocatedAfter.entrySet();
			for(Entry<Word, Integer> w : after)
				System.out.print(w.getKey().spelling + " ");
			System.out.println("\n");
			
		}
		s.close();
	}

}
