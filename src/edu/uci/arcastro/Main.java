package edu.uci.arcastro;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.uci.arcastro.Generators.*;
import edu.uci.arcastro.English.Word;
import edu.uci.arcastro.Generators.HaikuGenerator;
import edu.uci.arcastro.Generators.UsingPatterns;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		System.out.print("Reading dictionaries from file...");
		Global.Initialize(args);
		System.out.println("Done");
		
		HaikuGenerator g = new UsingPatterns();
		Scanner s = new Scanner(System.in);

        System.out.println("Please enter a word:");
		while(s.hasNextLine())
		{
            String word_spelling = s.nextLine().toUpperCase();
			if(!Dictionary.Words.containsKey(word_spelling))
			{
				System.out.println("No word with that exact spelling was found.");
				continue;
			}

            Word w = Dictionary.Words.get(word_spelling);
            List<Seed> seeds = new ArrayList<Seed>();
            seeds.add(new Seed(w, 1.0));

            HaikuGenerator generator = new UsingMarkovAndPatternsAndSentenceAssociationsAndWAN();
            String haiku = generator.Generate(seeds);
            System.out.println(haiku);

            System.out.println("Please enter a word:");
            /**

			Word inputWord = Dictionary.Words.get(word_spelling);

			System.out.println("Enter a syllable count:");
			int syllableCount = Integer.parseInt(s.nextLine());
			System.out.println("Enter a part of speech (Noun, Verb, Adjective, etc.):");
			String pos = s.nextLine().trim();

			ArrayList<Predicate> predicates = new ArrayList<Predicate>();
			predicates.add(new SyllablePredicate(syllableCount));
			predicates.add(new AnyPOSPredicate(EnumSet.of(POS.valueOf(pos))));

			ConstrainedAssociations c = new ConstrainedAssociations(inputWord, predicates);
			Set<Word> words = c.WordToWanFrequency.keySet();
			System.out.println("WAN words:");
			for (Word w : words) {
				System.out.println(w.spelling());
			}
             **/
		}
		s.close();
	}

}
