package edu.uci.arcastro;

import java.io.FileNotFoundException;
import java.util.Scanner;

import edu.uci.arcastro.English.Word;
import edu.uci.arcastro.Generators.HaikuGenerator;
import edu.uci.arcastro.Generators.UsingSeededMarkovChainAndPattern;
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
            String spelling = s.nextLine();
            if(!Dictionary.Words.containsKey(spelling))
            {
                System.out.println("Seed word not found in dictionary");
                continue;
            }
            Word seed = Dictionary.Words.get(spelling);

            HaikuGenerator generator = new UsingSeededMarkovChainAndPattern();
            String haiku = generator.Generate(new Word[]{seed});
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
