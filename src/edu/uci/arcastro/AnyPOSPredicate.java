package edu.uci.arcastro;

import java.util.EnumSet;

public class AnyPOSPredicate implements Predicate {
    private final EnumSet<POS> partsOfSpeech;

    public AnyPOSPredicate(EnumSet<POS> partsOfSpeech) {
        this.partsOfSpeech = partsOfSpeech;
    }

    public boolean fulfills(Word w) {
        for (POS p : w.PartsOfSpeech) {
            if (this.partsOfSpeech.contains(p)) {
                return true;
            }
        }

        return false;
    }
}
