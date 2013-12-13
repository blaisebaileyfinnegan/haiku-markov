package edu.uci.arcastro.Predicates;

import edu.uci.arcastro.English.Word;

public interface Predicate {
    boolean isFulfilledBy(Word w);
}