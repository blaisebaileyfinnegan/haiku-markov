package edu.uci.arcastro.Predicates;

import edu.uci.arcastro.English.POS;
import edu.uci.arcastro.English.Word;

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
