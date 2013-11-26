package edu.uci.mvu1;

import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Expected <corpus file> <syllable counts file> arguments!");
            System.exit(1);
        }

        String corpus = Parser.loadFromFile(args[0]);
        List<String> wordsInCorpus = Parser.parseCorpus(corpus);

        String syllableCounts = Parser.loadFromFile(args[1]);
        HashMap<String, Integer> syllableMap = Parser.parseSyllables(syllableCounts);

        Markov chain = new Markov(wordsInCorpus, syllableMap, 7);

        for (int i = 0; i < 10; i++) {
            System.out.println(chain.generateHaiku());
        }
    }
}
