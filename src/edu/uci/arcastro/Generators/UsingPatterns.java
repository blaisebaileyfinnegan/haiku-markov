package edu.uci.arcastro.Generators;

import java.util.ArrayList;
import java.util.EnumSet;

import edu.uci.arcastro.Dictionary;
import edu.uci.arcastro.POS;
import edu.uci.arcastro.Patterns;
import edu.uci.arcastro.Query;
import edu.uci.arcastro.SentencePattern;
import edu.uci.arcastro.Word;

public class UsingPatterns implements HaikuGenerator {

	@Override
	public String Generate(Word[] seeds) 
	{
		int[] FirstLineSyllablePattern = Query.ChooseRandom(Patterns.fiveSyllables);
		int[] SecondLineSyllablePattern = Query.ChooseRandom(Patterns.sevenSyllables);
		int[] ThirdLineSyllablePattern = Query.ChooseRandom(Patterns.fiveSyllables);
		
		SentencePattern p = Query.ChooseRandom(Patterns.grammarPatterns);
		ArrayList<EnumSet<POS>> FirstLinePOSPattern = p.firstLine;
		ArrayList<EnumSet<POS>> SecondLinePOSPattern = p.secondLine;
		ArrayList<EnumSet<POS>> ThirdLinePOSPattern = p.thirdLine;
		
		StringBuilder Haiku = new StringBuilder();
		Haiku.append(GenerateLine(FirstLineSyllablePattern, FirstLinePOSPattern));
		Haiku.append('\n');
		Haiku.append(GenerateLine(SecondLineSyllablePattern, SecondLinePOSPattern));
		Haiku.append('\n');
		Haiku.append(GenerateLine(ThirdLineSyllablePattern, ThirdLinePOSPattern));
		Haiku.append('\n');
		
		return Haiku.toString();
	}
	
	public String GenerateLine(int[] SyllablePattern, ArrayList<EnumSet<POS>> POSPattern)
	{
		StringBuilder line = new StringBuilder();
		for(int i = 0; i < SyllablePattern.length; i++)
		{
			int Syllables = SyllablePattern[i];
			EnumSet<POS> POS = POSPattern.get(i);
			
			if(i > 0) line.append(' ');
			line.append(GenerateWord(Syllables, POS));
		}
		return line.toString();
	}
	
	public String GenerateWord(int Syllables, EnumSet<POS> POS)
	{
		ArrayList<Word> candidates = new ArrayList<Word>();
		for(Word w : Dictionary.SyllableDictionary.get(Syllables))
			if(w.PartsOfSpeech.containsAll(POS))
				candidates.add(w);
		return Query.ChooseRandom(candidates).spelling;
	}
}