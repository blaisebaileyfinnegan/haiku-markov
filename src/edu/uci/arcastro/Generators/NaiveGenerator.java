package edu.uci.arcastro.Generators;

import java.util.ArrayList;
import java.util.EnumSet;

import edu.uci.arcastro.Dictionary;
import edu.uci.arcastro.POS;
import edu.uci.arcastro.Patterns;
import edu.uci.arcastro.Query;
import edu.uci.arcastro.Word;

public class NaiveGenerator implements HaikuGenerator {

	@Override
	public String Generate(Word[] seeds) 
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
