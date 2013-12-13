package edu.uci.arcastro;

import java.io.FileNotFoundException;

public class Global {
	
	//Initialize all data from files
	public static void Initialize(String[] args) throws FileNotFoundException
	{
		Patterns.LoadFiveSyllables("5 Syllables.txt");
		Patterns.LoadSevenSyllables("7 Syllables.txt");
		Patterns.LoadHaikuPatterns("patterns.txt");

		Dictionary.LoadSyllableDictionary("syllables.txt");
		Dictionary.LoadPOSDictionary("part-of-speech_UPDATED.txt");

        for (String s : args) {
            Parser.TrainWithCorpus(s);
        }

        Parser.TrainWithWAN("wan/wan-processed.txt");
	}

}
