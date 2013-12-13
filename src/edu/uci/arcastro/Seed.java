package edu.uci.arcastro;

public class Seed {
    public Word word;
    public double weight;

    public Seed(Word word, double weight) {
        this.word = word;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return this.word.toString();
    }
}
