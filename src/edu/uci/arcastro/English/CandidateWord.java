package edu.uci.arcastro.English;

import java.util.EnumSet;
import java.util.List;

/**
 * Created by Alan Castro on 12/12/13.
 */
public class CandidateWord
{
    public Word word;
    public Word prevWord;
    public List<Word> seeds;
    public int syllables;
    public EnumSet<POS> POS;

    public CandidateWord(Word word, int syllables, EnumSet<POS> POS, Word prevWord, List<Word> seeds)
    {
        this.word = word;
        this.syllables = syllables;
        this.POS = POS;
        this.prevWord = prevWord;
        this.seeds = seeds;
    }
}
