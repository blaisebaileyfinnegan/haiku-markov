package edu.uci.arcastro.English;

import edu.uci.arcastro.Dictionary;
import edu.uci.arcastro.Patterns;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Alan Castro on 12/12/13.
 */
public class SentencePattern
{
    public ArrayList<EnumSet<POS>> partsOfSpeech;
    private int frequency;
    private static HashMap<SentencePattern, SentencePattern> dic = new HashMap<SentencePattern, SentencePattern>();

    public SentencePattern(ArrayList<EnumSet<POS>> partsOfSpeech)
    {
        this.partsOfSpeech = partsOfSpeech;
        frequency = 1;
    }

    public int getFrequency()
    {
        return frequency;
    }

    /**
     * @return the number of words in this sentence pattern.
     */
    public int size()
    {
        return partsOfSpeech.size();
    }

    /**
     * @return the average number of parts of speech that
     * fit into each word in this sentence pattern.
     */
    public int avg_applicability()
    {
        double total = 0;
        for(EnumSet<POS> partOfSpeech : partsOfSpeech)total+= partOfSpeech.size();
        return (int)Math.ceil(total / size());
    }

    @Override
    public boolean equals(Object obj)
    {
        if(obj.getClass() != this.getClass()) return false;
        SentencePattern o = (SentencePattern)obj;
        if(this.partsOfSpeech.size() != o.partsOfSpeech.size()) return false;
        for(int i = 0; i < this.partsOfSpeech.size(); i++)
            if(!this.partsOfSpeech.get(i).equals(o.partsOfSpeech.get(i)))
                return false;
        return true;
    }

    @Override
    public int hashCode()
    {
        int avg = 0;
        for(int i = 0; i < partsOfSpeech.size(); i++)
        {
            avg += partsOfSpeech.get(i).hashCode() / partsOfSpeech.size();
        }
        return avg;
    }

    public static void AddOrIncrementFrequency(List<Word> sentence)
    {
        SentencePattern sentencePattern = new SentencePattern(new ArrayList<EnumSet<POS>>());
        for(Word w : sentence)
        {
            sentencePattern.partsOfSpeech.add(w.PartsOfSpeech);
        }

        SentencePattern real = dic.get(sentencePattern);
        if(real == null)
        {
            real = sentencePattern;
            dic.put(real, real);

            ArrayList<SentencePattern> SentencePatterns = Dictionary.SentencePatternDictionary.get(real.size());
            if(SentencePatterns == null)
            {
                SentencePatterns = new ArrayList<SentencePattern>();
                SentencePatterns.add(real);
                Dictionary.SentencePatternDictionary.put(real.size(), SentencePatterns);
            }
            else
            {
                SentencePatterns.add(real);
            }
        }
        else
        {
            real.frequency++;
        }
    }

    @Override
    public String toString() {
        return StringUtils.join(partsOfSpeech, " -- ");
    }
}
