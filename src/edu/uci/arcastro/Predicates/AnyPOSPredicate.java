package edu.uci.arcastro.Predicates;

import edu.uci.arcastro.POS;
import edu.uci.arcastro.Predicate;
import edu.uci.arcastro.Word;

import java.util.Collections;
import java.util.EnumSet;

public class AnyPOSPredicate implements Predicate {
    private final EnumSet<POS> partsOfSpeech;

    public AnyPOSPredicate(EnumSet<POS> partsOfSpeech) {
        this.partsOfSpeech = partsOfSpeech;
    }

    public boolean isFulfilledBy(Word w) {
        return !Collections.disjoint(this.partsOfSpeech, w.PartsOfSpeech);
    }
}
