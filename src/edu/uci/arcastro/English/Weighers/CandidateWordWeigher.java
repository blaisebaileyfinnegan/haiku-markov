package edu.uci.arcastro.English.Weighers;

import com.sun.corba.se.spi.activation._InitialNameServiceImplBase;
import edu.uci.arcastro.Dictionary;
import edu.uci.arcastro.English.CandidateWord;
import edu.uci.arcastro.English.Word;
import edu.uci.arcastro.Patterns;

/**
 * Created by Alan Castro on 12/12/13.
 */
public class CandidateWordWeigher implements Weigher<CandidateWord> {

    @Override
    public double getWeight(CandidateWord item) {
        if(item.word.syllables != item.syllables) return 0;
        if(!item.word.PartsOfSpeech.containsAll(item.POS)) return 0;


        double weight = item.word.frequency;
        if(item.prevWord == null)
        {
            //We are judging the first word in the sentence
            Integer startingFrequency = Patterns.startingWords.get(item.word);
            if(startingFrequency != null) weight += 100 * startingFrequency;
        }
        else
        {
            Integer afterFrequency =  item.prevWord.CollocatedAfter.get(item.word);
            if(afterFrequency != null) weight += 10 * afterFrequency;

            Integer prevAssociationFrequency = item.prevWord.Associated.get(item.word);
            if(prevAssociationFrequency != null) weight += 2 * prevAssociationFrequency;

            Integer prevWANAssociationFrequency = item.prevWord.WordToWanFrequency.get(item.word);
            if(prevWANAssociationFrequency != null) weight += 4 * prevWANAssociationFrequency;
        }

        if(item.seeds != null)
        {
            for(Word seed : item.seeds)
            {
                Integer seedAssociationFrequency = seed.Associated.get(item.word);
                if(seedAssociationFrequency != null) weight += 10 * seedAssociationFrequency;

                Integer seedWANAssociationFrequency = seed.WordToWanFrequency.get(item.word);
                if(seedWANAssociationFrequency != null) weight += 400 * seedWANAssociationFrequency;

                if(seed == item.word)
                    weight += 1000 * seed.frequency;

                if(seed.sentiment * item.word.sentiment > 0 ) weight += 5;
            }
        }
        return weight;
    }
}
