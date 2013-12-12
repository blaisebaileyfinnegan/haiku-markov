package edu.uci.arcastro;

public class SyllablePredicate implements Predicate {
    private final int syllableCount;

    public SyllablePredicate(int syllableCount) {
        this.syllableCount = syllableCount;
    }

    public boolean fulfills(Word w) {
        return w.syllables() == this.syllableCount;
    }
}