package edu.uci.arcastro.Generators;

import com.sun.javaws.exceptions.InvalidArgumentException;
import edu.uci.arcastro.*;
import edu.uci.arcastro.Exceptions.ImpossibleException;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public abstract class HaikuGenerator
{
	abstract public String Generate(List<Seed> seeds);

    protected int[] ChooseSyllablePattern(List<int[]> SyllablePatterns, int WordCount) throws ImpossibleException {
        ArrayList<int[]> candidate = new ArrayList<int[]>();

        for(int[] SyllablePattern : SyllablePatterns) {
            if(SyllablePattern.length == WordCount)
                candidate.add(SyllablePattern);
        }

        if(candidate.size() == 0) {
            throw new ImpossibleException(String.format(
                    "Could not find any syllable patterns with %d words.", WordCount));
        }

        return Query.ChooseRandom(candidate);
    }

    protected List<Blank> zipPatterns(List<EnumSet<POS>> posPattern, int[] syllablePattern) {
        if (posPattern.size() != syllablePattern.length) {
            throw new RuntimeException("Expected two patterns of equal size!");
        }

        List<Blank> tuples = new ArrayList<Blank>();
        for (int i = 0; i < syllablePattern.length; i++) {
            tuples.add(new Blank(posPattern.get(i), syllablePattern[i]));
        }

        return tuples;
    }
}
