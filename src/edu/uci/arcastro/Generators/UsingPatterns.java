package edu.uci.arcastro.Generators;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import edu.uci.arcastro.*;
import edu.uci.arcastro.Exceptions.ImpossibleException;

public class UsingPatterns extends HaikuGenerator {

	@Override
	public String Generate(List<Seed> seeds) {
        for(int i = 0; i < 10; i++)
        {
            try
            {
                SentencePattern p = Query.ChooseRandom(Patterns.grammarPatterns);
                // [Noun, Verb]
                ArrayList<EnumSet<POS>> FirstLinePOSPattern = p.firstLine;
                ArrayList<EnumSet<POS>> SecondLinePOSPattern = p.secondLine;
                ArrayList<EnumSet<POS>> ThirdLinePOSPattern = p.thirdLine;

                // [1, 4]
                int[] FirstLineSyllablePattern = ChooseSyllablePattern(Patterns.fiveSyllables, FirstLinePOSPattern.size());
                int[] SecondLineSyllablePattern = ChooseSyllablePattern(Patterns.sevenSyllables, SecondLinePOSPattern.size());
                int[] ThirdLineSyllablePattern = ChooseSyllablePattern(Patterns.fiveSyllables, ThirdLinePOSPattern.size());

                StringBuilder Haiku = new StringBuilder();
                Haiku.append(GenerateLine(FirstLineSyllablePattern, FirstLinePOSPattern));
                Haiku.append('\n');
                Haiku.append(GenerateLine(SecondLineSyllablePattern, SecondLinePOSPattern));
                Haiku.append('\n');
                Haiku.append(GenerateLine(ThirdLineSyllablePattern, ThirdLinePOSPattern));
                Haiku.append('\n');
                return Haiku.toString();
            }
            catch (ImpossibleException e){}
        }
        return "Could not generate Haiku after 10 tries. Giving up.";
	}

    // [1, 4]
    // [Noun, Verb]
	private String GenerateLine(int[] SyllablePattern, ArrayList<EnumSet<POS>> POSPattern) throws ImpossibleException {
		StringBuilder line = new StringBuilder();
		for(int i = 0; i < SyllablePattern.length; i++)
		{
			int Syllables = SyllablePattern[i];
			EnumSet<POS> POS = POSPattern.get(i);
			
			if(i > 0) line.append(' ');
			line.append(GenerateWord(Syllables, POS).spelling);
		}
		return line.toString();
	}

	public static Word GenerateWord(int Syllables, EnumSet<POS> POS) throws ImpossibleException {
		ArrayList<Word> candidates = new ArrayList<Word>();
        ArrayList<Word> SCandidates = Dictionary.SyllableDictionary.get(Syllables);
		for(Word w : SCandidates)
			if(w.PartsOfSpeech.containsAll(POS))
				candidates.add(w);
        if(candidates.size() == 0)
            throw new ImpossibleException(
                    String.format("Could not find any word with %d syllables and the following parts of speech: %s", Syllables, POS.toString()));
		return Query.ChooseRandom(candidates);
	}
}