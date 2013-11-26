package edu.uci.mvu1;

import java.util.*;

public class Markov {
    private HashMap<Integer, HashSet<Word>> syllablesToCorpusWords =
            new HashMap<Integer, HashSet<Word>>();

    // Duplicating words allows us to mimic word frequencies using an easy to
    // implement uniform distribution
    private HashMap<String, List<List<Word>>> transitions =
        new HashMap<String, List<List<Word>>>();

    private Random random = new Random();

    public Markov(List<String> corpus, HashMap<String, Integer> syllableCounts, int maxSyllables) {
        String prev = null;
        for (String word : corpus) {
            boolean isKnownWord = syllableCounts.containsKey(word);
            if (isKnownWord) {
                int syllableCount = syllableCounts.get(word);
                int key = syllableCount - 1;

                Word w = new Word(word, syllableCount);

                if (!syllablesToCorpusWords.containsKey(key)) {
                    HashSet<Word> list = new HashSet<Word>();
                    list.add(w);
                    syllablesToCorpusWords.put(key, list);
                } else {
                    syllablesToCorpusWords.get(key).add(w);
                }

                if (prev != null) {
                    if (!this.transitions.containsKey(prev)) {
                        List<List<Word>> syllableMaps = new ArrayList<List<Word>>();
                        for (int i = 0; i < maxSyllables; i++) {
                            syllableMaps.add(new ArrayList<Word>());
                        }

                        this.transitions.put(prev, syllableMaps);
                    }

                    List<List<Word>> syllableMaps = this.transitions.get(prev);
                    List<Word> words = syllableMaps.get(syllableCount - 1);
                    words.add(w);
                }
            }

            prev = word;
        }
    }

    private Word pickRandom(Set<Word> set) {
        int whichWord = this.random.nextInt(set.size());
        int i = 0;
        for (Word word : set) {
            if (i++ == whichWord) {
                return word;
            }
        }

        throw new RuntimeException();
    }

    public Word getStartWord(int sentenceSize) {
        // Hack
        int syllables = this.random.nextInt(sentenceSize);
        HashSet<Word> possibleWords = this.syllablesToCorpusWords.get(syllables);

        return pickRandom(possibleWords);
    }

    public Word getNextWord(String word, int maxSize) {
        if (!this.transitions.containsKey(word)) {
            return null;
        }

        int syllables = this.random.nextInt(maxSize);
        List<List<Word>> syllableMap = this.transitions.get(word);
        List<Word> words = syllableMap.get(syllables);

        if (words.isEmpty()) {
            throw new RuntimeException();
        } else {
            return words.get(this.random.nextInt(words.size()));
        }
    }

    public String generateHaiku() {
        try {
            StringBuilder builder = new StringBuilder();

            builder.append(generateSentence(5)).append("\n");
            builder.append(generateSentence(7)).append("\n");
            builder.append(generateSentence(5)).append("\n");

            return builder.toString();
        } catch(RuntimeException e) {
            return generateHaiku();
        }
    }

    public String generateSentence(int syllables) {
        if (syllables < 1) {
            return null;
        }

        StringBuilder builder = new StringBuilder();

        Word word = this.getStartWord(syllables);
        while(syllables > 0) {
            builder.append(word.text + " ");
            syllables -= word.syllableCount;

            if (syllables == 0) break;

            word = this.getNextWord(word.text, syllables);
        }

        return builder.toString();
    }

    @Override
    public String toString() {
        return transitions.toString();
    }
}
