package edu.uci.arcastro;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import edu.uci.arcastro.Generators.*;
import edu.uci.arcastro.English.Word;
import org.apache.commons.lang3.StringUtils;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		System.out.print("Reading dictionaries from file...");
		Global.Initialize(args);
		System.out.println("Done");

        UsingProbabilityDistributionAndFrequentSentecePatterns g = new UsingProbabilityDistributionAndFrequentSentecePatterns();
		Scanner s = new Scanner(System.in);

        System.out.println("Please enter a word:");
		while(s.hasNextLine())
		{
            String[] word_spellings = StringUtils.split(s.nextLine().toUpperCase(), ' ');
            ArrayList<Word> seeds = new ArrayList<Word>();
			for(String spelling : word_spellings)
            {
                if(Dictionary.Words.containsKey(spelling))
                    seeds.add(Dictionary.Words.get(spelling));
            }
            String haiku = g.Generate(seeds);
            System.out.println(haiku);

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
