package edu.uci.arcastro.Generators;

import edu.uci.arcastro.*;
import edu.uci.arcastro.Exceptions.ImpossibleException;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.HashMap;

/**
 * Created by Alan Castro on 12/12/13.
 */
public class UsingMarkovChainAndPattern implements HaikuGenerator {

    @Override
    public String Generate(Word[] seeds) {
        for(int i = 0; i < 10; i++)
        {
            try
            {
                SentencePattern p = Query.ChooseRandom(Patterns.grammarPatterns);
                ArrayList<EnumSet<POS>> FirstLinePOSPattern = p.firstLine;
                ArrayList<EnumSet<POS>> SecondLinePOSPattern = p.secondLine;
                ArrayList<EnumSet<POS>> ThirdLinePOSPattern = p.thirdLine;

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

    private int[] ChooseSyllablePattern(List<int[]> SyllablePatterns, int WordCount) throws ImpossibleException {
        ArrayList<int[]> candidate = new ArrayList<int[]>();
        for(int[] SyllablePattern : SyllablePatterns)
            if(SyllablePattern.length == WordCount)
                candidate.add(SyllablePattern);
        if(candidate.size() == 0)
            throw new ImpossibleException(String.format(
                    "Could not find any syllable patterns with %d words.", WordCount));
        return Query.ChooseRandom(candidate);
    }

    private String GenerateLine(int[] SyllablePattern, ArrayList<EnumSet<POS>> POSPattern) throws ImpossibleException {
        StringBuilder line = new StringBuilder();
        Word word = null;
        for(int i = 0; i < SyllablePattern.length; i++)
        {
            int Syllables = SyllablePattern[i];
            EnumSet<POS> POS = POSPattern.get(i);

            if(i > 0) line.append(' ');
            word = GenerateWord(Syllables, POS, word);
            line.append(word.spelling);
        }
        return line.toString();
    }

    private Word GenerateWord(int Syllables, EnumSet<POS> POS, Word PreviousWord) throws ImpossibleException {

        // Can you do this michael?
    }
}
