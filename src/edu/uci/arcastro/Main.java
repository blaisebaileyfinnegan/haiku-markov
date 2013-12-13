package edu.uci.arcastro;

import java.io.FileNotFoundException;
import java.util.Scanner;

import edu.uci.arcastro.English.Word;
import edu.uci.arcastro.Generators.HaikuGenerator;
import edu.uci.arcastro.Generators.UsingMarkovChainAndHaikuPattern;
import edu.uci.arcastro.Generators.UsingMarkovChainAndSentencePattern;
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
            /*if(!Dictionary.Words.containsKey(spelling))
            {
                System.out.println("Seed word not found in dictionary");
                continue;
            }
            Word seed = Dictionary.Words.get(spelling);*/

            HaikuGenerator generator = new UsingMarkovChainAndSentencePattern();
            String haiku = generator.Generate(null);
            System.out.println(haiku);
		}
		s.close();
	}

}
