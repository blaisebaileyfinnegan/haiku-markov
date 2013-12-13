package edu.uci.arcastro.Generators;

import edu.uci.arcastro.*;
import edu.uci.arcastro.Dictionary;
import edu.uci.arcastro.English.HaikuPattern;
import edu.uci.arcastro.English.POS;
import edu.uci.arcastro.English.SentencePattern;
import edu.uci.arcastro.English.Word;
import edu.uci.arcastro.Exceptions.ImpossibleException;
import edu.uci.arcastro.Predicates.AnyPOSPredicate;
import edu.uci.arcastro.Predicates.ConstrainedAssociations;
import edu.uci.arcastro.Predicates.Predicate;
import edu.uci.arcastro.Predicates.SyllablePredicate;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * Created by Alan Castro on 12/12/13.
 */
public class UsingMarkovChainAndSentencePattern implements HaikuGenerator {

    @Override
    public String Generate(Word[] seeds) {
        ArrayList<SentencePattern> candidates = new ArrayList<SentencePattern>();
        for(SentencePattern sp : Dictionary.SentencePatternDictionary.get(4))
            if(sp.getFrequency() > 1)
                candidates.add(sp);
        return StringUtils.join(candidates, "\n");
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

    private List<Word> GenerateLine(int[] SyllablePattern, ArrayList<EnumSet<POS>> POSPattern, Word prevWord) throws ImpossibleException {
        List<Word> line = new ArrayList<Word>();
        Word word = prevWord;
        for(int i = 0; i < SyllablePattern.length; i++)
        {
            int Syllables = SyllablePattern[i];
            EnumSet<POS> POS = POSPattern.get(i);

            word = GenerateWord(Syllables, POS, word);
            line.add(word);
        }

        return line;
    }

    /**
     *
     * @param Syllables
     * @param POS
     * @param PreviousWord: may be null, in which case pick a random word which fulfills the POS and syllable count
     * @return
     * @throws edu.uci.arcastro.Exceptions.ImpossibleException
     */
    private Word GenerateWord(int Syllables, EnumSet<POS> POS, Word PreviousWord) throws ImpossibleException {
        if (PreviousWord == null) {
            return UsingPatterns.GenerateWord(Syllables, POS);
        } else {
            ArrayList<Predicate> predicates = new ArrayList<Predicate>();
            predicates.add(new AnyPOSPredicate(POS));
            predicates.add(new SyllablePredicate(Syllables));

            ConstrainedAssociations c = new ConstrainedAssociations(PreviousWord, predicates);
            Set<Map.Entry<Word, Integer>> choices = c.CollocatedAfter.entrySet();

            return Query.ChooseWeightedRandom(choices);
        }
    }

}
