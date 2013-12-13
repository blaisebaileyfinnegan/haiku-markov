package edu.uci.arcastro;

import edu.uci.arcastro.English.Word;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Utility {
    // Merges other into preserve, adding values if they exist in preserve
    public static void merge(HashMap<Word, Double> preserve, HashMap<Word, Double> other, double weight) {
        Set<Map.Entry<Word, Double>> entries = other.entrySet();
        for (Map.Entry<Word, Double> e : entries) {
            Word w = e.getKey();
            Double v = e.getValue() * weight;
            if (preserve.containsKey(w)) {
                preserve.put(w, preserve.get(w) + v);
            } else {
                preserve.put(w, v);
            }
        }
    }
}
