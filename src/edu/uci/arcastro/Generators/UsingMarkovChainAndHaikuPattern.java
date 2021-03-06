package edu.uci.arcastro.Generators;

import edu.uci.arcastro.*;
import edu.uci.arcastro.English.HaikuPattern;
import edu.uci.arcastro.English.POS;
import edu.uci.arcastro.English.Word;
import org.apache.commons.lang3.StringUtils;

import edu.uci.arcastro.Exceptions.ImpossibleException;
import edu.uci.arcastro.Predicates.*;

import java.util.*;

/**
 * Created by Alan Castro on 12/12/13.
 */
public class UsingMarkovChainAndHaikuPattern extends HaikuGenerator {

    @Override
    public String Generate(List<Seed> seeds) {
        for(int i = 0; i < 100; i++)
        {
            try
            {
                HaikuPattern p = Query.ChooseRandom(Patterns.grammarPatterns);
                ArrayList<EnumSet<POS>> FirstLinePOSPattern = p.firstLine;
                ArrayList<EnumSet<POS>> SecondLinePOSPattern = p.secondLine;
                ArrayList<EnumSet<POS>> ThirdLinePOSPattern = p.thirdLine;

                int[] FirstLineSyllablePattern = ChooseSyllablePattern(Patterns.fiveSyllables, FirstLinePOSPattern.size());
                int[] SecondLineSyllablePattern = ChooseSyllablePattern(Patterns.sevenSyllables, SecondLinePOSPattern.size());
                int[] ThirdLineSyllablePattern = ChooseSyllablePattern(Patterns.fiveSyllables, ThirdLinePOSPattern.size());

                StringBuilder Haiku = new StringBuilder();
                List<Word> line = GenerateLine(FirstLineSyllablePattern, FirstLinePOSPattern, null);
                Word prevWord = line.get(line.size() - 1);
                Haiku.append(StringUtils.join(line, " "));
                Haiku.append('\n');

                line = GenerateLine(SecondLineSyllablePattern, SecondLinePOSPattern, prevWord);
                prevWord = line.get(line.size() - 1);
                Haiku.append(StringUtils.join(line, " "));
                Haiku.append('\n');

                line = GenerateLine(ThirdLineSyllablePattern, ThirdLinePOSPattern, prevWord);
                Haiku.append(StringUtils.join(line, " "));
                Haiku.append('\n');
                return Haiku.toString();
            }
            catch (ImpossibleException e){
                System.out.println(e.getMessage());
            }
        }
        return "Could not generate Haiku after 100 tries. Giving up.";
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
     * @throws ImpossibleException
     */
    private Word GenerateWord(int Syllables, EnumSet<POS> POS, Word PreviousWord) throws ImpossibleException {
        if (PreviousWord == null) {
            return UsingPatterns.GenerateWord(Syllables, POS);
        } else {
            ArrayList<Predicate> predicates = new ArrayList<Predicate>();
            predicates.add(new AnyPOSPredicate(POS));
            predicates.add(new SyllablePredicate(Syllables));

            ConstrainedAssociations c = new ConstrainedAssociations(PreviousWord, predicates);
            Set<Map.Entry<Word, Double>> choices = c.CollocatedAfter.entrySet();

            return Query.ChooseWeightedRandom(choices);
        }
    }

}
