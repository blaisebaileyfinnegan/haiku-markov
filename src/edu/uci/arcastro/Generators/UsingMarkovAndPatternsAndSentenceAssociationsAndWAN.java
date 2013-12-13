package edu.uci.arcastro.Generators;

import edu.uci.arcastro.*;
import edu.uci.arcastro.Exceptions.ImpossibleException;
import edu.uci.arcastro.Predicates.AnyPOSPredicate;
import edu.uci.arcastro.Predicates.ConstrainedAssociations;
import edu.uci.arcastro.Predicates.POSPredicate;
import edu.uci.arcastro.Predicates.SyllablePredicate;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

public class UsingMarkovAndPatternsAndSentenceAssociationsAndWAN extends HaikuGenerator {

    @Override
    public String Generate(List<Seed> seeds) {
        for (int i = 0; i < 100; i++) {
            try {
                SentencePattern p = Query.ChooseRandom(Patterns.grammarPatterns);
                ArrayList<EnumSet<POS>> FirstLinePOSPattern = p.firstLine;
                ArrayList<EnumSet<POS>> SecondLinePOSPattern = p.secondLine;
                ArrayList<EnumSet<POS>> ThirdLinePOSPattern = p.thirdLine;

                int[] FirstLineSyllablePattern = ChooseSyllablePattern(Patterns.fiveSyllables, FirstLinePOSPattern.size());
                int[] SecondLineSyllablePattern = ChooseSyllablePattern(Patterns.sevenSyllables, SecondLinePOSPattern.size());
                int[] ThirdLineSyllablePattern = ChooseSyllablePattern(Patterns.fiveSyllables, ThirdLinePOSPattern.size());

                List<List<Blank>> lines = new ArrayList<List<Blank>>();
                lines.add(this.zipPatterns(FirstLinePOSPattern, FirstLineSyllablePattern));
                lines.add(this.zipPatterns(SecondLinePOSPattern, SecondLineSyllablePattern));
                lines.add(this.zipPatterns(ThirdLinePOSPattern, ThirdLineSyllablePattern));

                return this.generateHaiku(lines, seeds).toString();
            } catch (ImpossibleException e) {}
        }

        return "Could not generate Haiku after 100 tries. Giving up.";
    }

    private Haiku generateHaiku(List<List<Blank>> lines, List<Seed> seeds) throws ImpossibleException {
        Haiku haiku = new Haiku();

        for (List<Blank> line : lines) {
            haiku.lines.add(this.GenerateLine(line, seeds));
        }

        return haiku;
    }

    private List<Word> GenerateLine(List<Blank> blanks, List<Seed> seeds) throws ImpossibleException {
        List<Word> line = new ArrayList<Word>();
        Seed markovSeed = null;
        for (Blank b : blanks) {
            Word w = GenerateWord(b, seeds, markovSeed);
            line.add(w);

            markovSeed = new Seed(w, 5.0);
            markovSeed.word.CollocatedBefore.clear();
            markovSeed.word.Associated.clear();
            markovSeed.word.WordToWanFrequency.clear();
        }

        return line;
    }

    private Word GenerateWord(Blank b, List<Seed> seeds, Seed markovSeed) throws ImpossibleException {
        ArrayList<Predicate> predicates = new ArrayList<Predicate>();
        predicates.add(new POSPredicate(b.pos));
        predicates.add(new SyllablePredicate(b.syllableCount));

        ConstrainedAssociations c = new ConstrainedAssociations(seeds.get(0).word, predicates);
        for (int i = 1; i < seeds.size(); i++) {
            // Union with other seed associations if they exist
            c = c.union(new ConstrainedAssociations(seeds.get(i).word, predicates), seeds.get(i).weight);
        }

        if (markovSeed != null) {
            c = c.union(new ConstrainedAssociations(markovSeed.word, predicates), markovSeed.weight);
        }

        Set<Map.Entry<Word, Double>> probabilityTable = c.toProbabilityTable(0.1, 0.1, 0.4, 0.4).entrySet();

        return Query.ChooseWeightedRandom(probabilityTable);
    }

}

