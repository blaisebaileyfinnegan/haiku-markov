package edu.uci.arcastro;

import java.io.FileNotFoundException;
import java.util.*;
import java.util.Scanner;

import edu.uci.arcastro.Generators.HaikuGenerator;
import edu.uci.arcastro.Generators.NaiveGenerator;
import edu.uci.arcastro.Generators.UsingMarkovChainAndPattern;
import edu.uci.arcastro.Generators.UsingPatterns;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		System.out.print("Reading dictionaries from file...");
		Global.Initialize(args);
		System.out.println("Done");
		
		HaikuGenerator g = new UsingPatterns();
		Scanner s = new Scanner(System.in);

		while(s.hasNextLine())
		{
            s.nextLine();

            UsingMarkovChainAndPattern generator = new UsingMarkovChainAndPattern();
            String haiku = generator.Generate(null);
            System.out.println(haiku);
            /**
			String word_spelling = s.nextLine().toUpperCase();
			if(!Dictionary.Words.containsKey(word_spelling))
			{
				System.out.println("No word with that exact spelling was found.");
				continue;
			}

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
