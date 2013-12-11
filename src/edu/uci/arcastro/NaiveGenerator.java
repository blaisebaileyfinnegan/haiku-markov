package edu.uci.arcastro;

import java.util.Random;

public class NaiveGenerator implements HaikuGenerator {

	@Override
	public String Generate(Word[] seeds) 
	{
		Random r = new Random();
		SentencePattern pattern = Query.ChooseRandom(Patterns.grammarPatterns);
		int[] FirstLineSyllablePattern = Query.ChooseRandom(Query.FiveSyllablePatternWithNWords(pattern.firstLine.size()));
		StringBuilder FirstLine = new StringBuilder();
		for(int i = 0; i < FirstLineSyllablePattern.length; i++)
		{
			FirstLine.append(Query.ChooseRandom(Dictionary.SyllableDictionary.get(FirstLineSyllablePattern[i])).spelling);
			FirstLine.append(' ');
		}
		return FirstLine.toString();
	}

}
