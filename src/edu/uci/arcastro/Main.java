package edu.uci.arcastro;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import edu.uci.arcastro.English.Word;
import edu.uci.arcastro.Generators.*;
import org.apache.commons.lang3.StringUtils;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		System.out.print("Reading dictionaries from file...");
		Global.Initialize(args);
		System.out.println("Done");

        UsingProbabilityDistributionAndFrequentSentencePatterns g = new UsingProbabilityDistributionAndFrequentSentencePatterns();
		Scanner s = new Scanner(System.in);
        System.out.print("Enter seed word/phrase: ");
		while(s.hasNextLine())
		{
            String[] seed_spellings = StringUtils.split(s.nextLine().toUpperCase(), ' ');
            ArrayList<Word> seeds = new ArrayList<Word>();
            for(String spelling : seed_spellings)
            {
                if(Dictionary.Words.containsKey(spelling))
                    seeds.add(Dictionary.Words.get(spelling));
            }

            System.out.println(g.Generate(seeds));
            System.out.print("Enter seed word/phrase: ");
		}
        s.close();
	}

}
