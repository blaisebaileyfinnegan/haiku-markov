package edu.uci.arcastro.Generators;

import edu.uci.arcastro.*;

import java.util.List;

public class NaiveGenerator extends HaikuGenerator {

	@Override
	public String Generate(List<Seed> seeds)
	{
		int[] FirstLineSyllablePattern = Query.ChooseRandom(Patterns.fiveSyllables);
		int[] SecondLineSyllablePattern = Query.ChooseRandom(Patterns.sevenSyllables);
		int[] ThirdLineSyllablePattern = Query.ChooseRandom(Patterns.fiveSyllables);
		
		StringBuilder Haiku = new StringBuilder();
		Haiku.append(GenerateLine(FirstLineSyllablePattern));
		Haiku.append('\n');
		Haiku.append(GenerateLine(SecondLineSyllablePattern));
		Haiku.append('\n');
		Haiku.append(GenerateLine(ThirdLineSyllablePattern));
		Haiku.append('\n');
		
		return Haiku.toString();
	}

	public String GenerateLine(int[] SyllablePattern)
	{
		StringBuilder line = new StringBuilder();
		for(int i = 0; i < SyllablePattern.length; i++)
		{
			int Syllables = SyllablePattern[i];
			
			if(i > 0) line.append(' ');
			line.append(GenerateWord(Syllables));
		}
		return line.toString();
	}
	
	public String GenerateWord(int Syllables)
	{
		return Query.ChooseRandom(Dictionary.SyllableDictionary.get(Syllables)).spelling;
	}

}
