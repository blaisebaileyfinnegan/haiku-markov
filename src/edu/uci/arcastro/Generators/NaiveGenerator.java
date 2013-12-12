package edu.uci.arcastro.Generators;

import edu.uci.arcastro.Dictionary;
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
		
		//Generate first line
		for(int i = 0; i < FirstLineSyllablePattern.length; i++)
		{
			Haiku.append(Query.ChooseRandom(Dictionary.SyllableDictionary.get(FirstLineSyllablePattern[i])).spelling);
			Haiku.append(' ');
		}
		Haiku.append('\n');
		
		//Generate second line
		for(int i = 0; i < SecondLineSyllablePattern.length; i++)
		{
			Haiku.append(Query.ChooseRandom(Dictionary.SyllableDictionary.get(SecondLineSyllablePattern[i])).spelling);
			Haiku.append(' ');
		}
		Haiku.append('\n');
		
		//generate third line
		for(int i = 0; i < ThirdLineSyllablePattern.length; i++)
		{
			Haiku.append(Query.ChooseRandom(Dictionary.SyllableDictionary.get(ThirdLineSyllablePattern[i])).spelling);
			Haiku.append(' ');
		}
		
		return Haiku.toString();
	}

}
