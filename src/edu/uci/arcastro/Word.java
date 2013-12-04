package edu.uci.arcastro;

import java.util.ArrayList;
import java.util.HashMap;


public class Word
{
	public final String spelling;
	public int syllables;
	public int frequency;
	public final HashMap<Word, Integer> CollocatedBefore;
	public final HashMap<Word, Integer> CollocatedAfter;
	public final HashMap<Word, Integer> Associated;
	public final ArrayList<Word> Rhyme;
	public final ArrayList<POS> PartsOfSpeech;
	public Word(String spelling)
	{
		this.syllables = 0;
		this.frequency = 0;
		this.spelling = spelling;
		this.CollocatedBefore = new HashMap<Word, Integer>();
		this.CollocatedAfter = new HashMap<Word, Integer>();
		this.Associated = new HashMap<Word, Integer>();
		this.Rhyme = new ArrayList<Word>();
		this.PartsOfSpeech = new ArrayList<POS>();
	}
	
	public String spelling() {
		return spelling;
	}
	public int syllables(){
		return syllables;
	}
	public int frequency(){
		return frequency;
	}	
	public HashMap<Word, Integer> CollocatedBefore(){
		return CollocatedBefore;
	}
	public HashMap<Word, Integer> CollocatedAfter(){
		return CollocatedAfter;
	}
	public HashMap<Word, Integer> Associated(){
		return Associated;
	}
	public ArrayList<Word> Rhyme(){
		return Rhyme;
	}
	public ArrayList<POS> PartsOfSpeech(){
		return PartsOfSpeech;
	}
}
