package edu.uci.arcastro.Predicates;

import edu.uci.arcastro.Utility;

import edu.uci.arcastro.Utility;
import edu.uci.arcastro.English.Word;

import javax.rmi.CORBA.Util;
import java.util.*;
import java.util.Map.Entry;

public class ConstrainedAssociations {
    public final HashMap<Word, Double> CollocatedBefore;
    public final HashMap<Word, Double> CollocatedAfter;
    public final HashMap<Word, Double> Associated;

    public int totalWanResponseCount;
    public final HashMap<Word, Double> WordToWanFrequency;

    public ConstrainedAssociations() {
        this.CollocatedBefore = new HashMap<Word, Double>();
        this.CollocatedAfter = new HashMap<Word, Double>();
        this.Associated = new HashMap<Word, Double>();
        this.WordToWanFrequency = new HashMap<Word, Double>();

        this.totalWanResponseCount = 0;
    }

    public ConstrainedAssociations(Word word, List<Predicate> predicates) {
        this.CollocatedBefore = this.filter(word.CollocatedBefore(), predicates);
        this.CollocatedAfter = this.filter(word.CollocatedAfter(), predicates);
        this.Associated = this.filter(word.Associated(), predicates);

        this.WordToWanFrequency = this.filter(word.WordToWanFrequency(), predicates);
        this.totalWanResponseCount = word.totalWanResponseCount;
    }

    public HashMap<Word, Double> toProbabilityTable(double collocatedBeforeWeight,
                                                    double collocatedAfterWeight,
                                                    double associatedWeight,
                                                    double wanResponseWeight) {
        HashMap<Word, Double> map = new HashMap<Word, Double>();

        Utility.merge(map, this.CollocatedBefore, collocatedBeforeWeight);
        Utility.merge(map, this.CollocatedAfter, collocatedAfterWeight);
        Utility.merge(map, this.Associated, associatedWeight);
        Utility.merge(map, this.WordToWanFrequency, wanResponseWeight);

        return map;
    }

    public ConstrainedAssociations union(ConstrainedAssociations other, double weight) {
        ConstrainedAssociations associations = new ConstrainedAssociations();

        associations.CollocatedBefore.putAll(this.CollocatedBefore);
        associations.CollocatedAfter.putAll(this.CollocatedAfter);
        associations.Associated.putAll(this.Associated);
        associations.WordToWanFrequency.putAll(this.WordToWanFrequency);

        Utility.merge(associations.CollocatedBefore, other.CollocatedBefore, weight);
        Utility.merge(associations.CollocatedAfter, other.CollocatedAfter, weight);
        Utility.merge(associations.Associated, other.Associated, weight);
        Utility.merge(associations.WordToWanFrequency, other.WordToWanFrequency, weight);

        associations.totalWanResponseCount = this.totalWanResponseCount + other.totalWanResponseCount;

        return associations;
    }

    private HashMap<Word, Double> filter(HashMap<Word, Integer> collection,
                                          List<Predicate> predicates) {
        HashMap<Word, Double> newCollection = new HashMap<Word, Double>();

        Set<Entry<Word, Integer>> entries = collection.entrySet();
        for (Entry<Word, Integer> e : entries) {
            Word key = e.getKey();
            if (this.forall(predicates, key)) {
                newCollection.put(key, e.getValue().doubleValue());
            }
        }

        return newCollection;
    }

    private boolean forall(List<Predicate> predicates, Word w) {
        for (Predicate p : predicates) {
            if (!p.isFulfilledBy(w)) {
                return false;
            }
        }

        return true;
    }


}
