package edu.uci.arcastro;

import java.io.FileNotFoundException;

public class Global {
	
	public static void Initialize() throws FileNotFoundException
	{
		Patterns.LoadFiveSyllables("5 Syllables.txt");
		Patterns.LoadSevenSyllables("7 Syllables.txt");
		Patterns.LoadPatterns("patterns.txt");

		Dictionary.LoadSyllableDictionary("syllables.txt");
		Dictionary.LoadPOSDictionary("part-of-speech_UPDATED.txt");
		Parser.TrainWithCorpus("big.txt");
	}

}
