package edu.uci.arcastro.Generators;

import edu.uci.arcastro.Dictionary;
import edu.uci.arcastro.English.*;
import edu.uci.arcastro.English.Weighers.CandidateWordWeigher;
import edu.uci.arcastro.English.Weighers.SentencePatternWeigher;
import edu.uci.arcastro.Exceptions.ImpossibleException;
import edu.uci.arcastro.Patterns;
import edu.uci.arcastro.Query;
import edu.uci.arcastro.Seed;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

/**
 * Created by Alan Castro on 12/12/13.
 */
public class UsingProbabilityDistributionAndFrequentSentecePatterns {

    public String Generate(List<Word> seeds) {
        for(int i = 0; i < 100; i++)
        {
            try
            {
                // [1, 4]
                int[] FirstLineSyllablePattern = Query.ChooseRandom(Patterns.fiveSyllables);
                int[] SecondLineSyllablePattern = Query.ChooseRandom(Patterns.sevenSyllables);
                int[] ThirdLineSyllablePattern = Query.ChooseRandom(Patterns.fiveSyllables);

                SentencePattern FirstSentencePattern = ChooseSentencePattern(FirstLineSyllablePattern.length);
                SentencePattern SecondSentencePattern = ChooseSentencePattern(SecondLineSyllablePattern.length);
                SentencePattern ThirdSentencePattern = ChooseSentencePattern(ThirdLineSyllablePattern.length);

                List<Word> FirstSentence = GenerateSentence(FirstLineSyllablePattern, FirstSentencePattern.partsOfSpeech, seeds);
                List<Word> SecondSentence = GenerateSentence(SecondLineSyllablePattern, SecondSentencePattern.partsOfSpeech, seeds);
                List<Word> ThirdSentence = GenerateSentence(ThirdLineSyllablePattern, ThirdSentencePattern.partsOfSpeech, seeds);

                StringBuilder Haiku = new StringBuilder();
                Haiku.append(StringUtils.join(FirstSentence, " "));
                Haiku.append('\n');
                Haiku.append(StringUtils.join(SecondSentence, " "));
                Haiku.append('\n');
                Haiku.append(StringUtils.join(ThirdSentence, " "));
                Haiku.append('\n');
                return Haiku.toString();
            }
            catch (ImpossibleException e){}
        }
        return "Could not generate Haiku after 100 tries. Giving up.";
    }

    private SentencePattern ChooseSentencePattern(int WordCount) throws ImpossibleException
    {
        SentencePatternWeigher weigher = new SentencePatternWeigher();
        ArrayList<SentencePattern> candidate = new ArrayList<SentencePattern>();
        for(SentencePattern s : Dictionary.SentencePatternDictionary.get(WordCount))
        {
            if(s.getFrequency() > 1)
                candidate.add(s);
        }
        if(candidate.size() == 0)
            throw new ImpossibleException(String.format(
                "Could not find a frequent (>1) sentence pattern with %d words", WordCount));
        return Query.ChooseRandom(candidate);
    }

    private List<Word> GenerateSentence(int[] SyllablePattern, ArrayList<EnumSet<POS>> POSPattern, List<Word> seeds) throws ImpossibleException {
        ArrayList<Word> sentence = new ArrayList<Word>();
        Word word = null;
        for(int i = 0; i < SyllablePattern.length; i++)
        {
            int Syllables = SyllablePattern[i];
            EnumSet<POS> POS = POSPattern.get(i);

            word = GenerateWord(Syllables, POS, word, seeds);
            sentence.add(word);
        }
        return sentence;
    }

    private static CandidateWordWeigher wordWeigher = new CandidateWordWeigher();
    public static Word GenerateWord(int Syllables, EnumSet<POS> POS, Word prevWord, List<Word> seeds) throws ImpossibleException
    {
        ArrayList<CandidateWord> candidates = new ArrayList<CandidateWord>();

        for(Word w : Dictionary.SyllableDictionary.get(Syllables))
            if(w.PartsOfSpeech.equals(POS))
                candidates.add(new CandidateWord(w, Syllables, POS, prevWord, seeds));

        if(candidates.size() == 0)
            throw new ImpossibleException(
                    String.format("Could not find any word with %d syllables and the following parts of speech: %s", Syllables, POS.toString()));

        return Query.ChooseWeightedRandom(candidates, wordWeigher).word;
    }
}
