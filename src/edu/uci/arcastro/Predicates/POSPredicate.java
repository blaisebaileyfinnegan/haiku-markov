package edu.uci.arcastro.Predicates;

import edu.uci.arcastro.POS;
import edu.uci.arcastro.Predicate;
import edu.uci.arcastro.Word;

import java.util.Collections;
import java.util.EnumSet;

/**
 * Created by Alan Castro on 12/12/13.
 */
public class POSPredicate implements Predicate {
    private final EnumSet<POS> partsOfSpeech;

    public POSPredicate(EnumSet<POS> partsOfSpeech) {
        this.partsOfSpeech = partsOfSpeech;
    }

    public boolean isFulfilledBy(Word w) {
        return w.PartsOfSpeech.containsAll(this.partsOfSpeech);
    }
}
