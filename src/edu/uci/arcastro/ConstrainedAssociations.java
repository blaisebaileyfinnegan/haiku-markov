package edu.uci.arcastro;

import java.util.*;
import java.util.Map.Entry;

public class ConstrainedAssociations {
    public final HashMap<Word, Integer> CollocatedBefore;
    public final HashMap<Word, Integer> CollocatedAfter;
    public final HashMap<Word, Integer> Associated;

    public int totalWanResponseCount;
    public final HashMap<Word, Integer> WordToWanFrequency;

    public ConstrainedAssociations(Word word, List<Predicate> predicates) {
        this.CollocatedBefore = this.filter(word.CollocatedBefore(), predicates);
        this.CollocatedAfter = this.filter(word.CollocatedAfter(), predicates);
        this.Associated = this.filter(word.Associated(), predicates);

        this.WordToWanFrequency = this.filter(word.WordToWanFrequency(), predicates);
        this.totalWanResponseCount = word.totalWanResponseCount;
    }

    private HashMap<Word, Integer> filter(HashMap<Word, Integer> collection,
                                          List<Predicate> predicates) {
        HashMap<Word, Integer> newCollection = new HashMap<Word, Integer>();

        Set<Entry<Word, Integer>> entries = collection.entrySet();
        for (Entry<Word, Integer> e : entries) {
            Word key = e.getKey();
            if (this.forall(predicates, key)) {
                newCollection.put(key, e.getValue());
            }
        }

        return newCollection;
    }

    private boolean forall(List<Predicate> predicates, Word w) {
        for (Predicate p : predicates) {
            if (!p.fulfills(w)) {
                return false;
            }
        }

        return true;
    }


}
