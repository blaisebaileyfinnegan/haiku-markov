package edu.uci.arcastro;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class Dictionary 
{	
	public static HashMap<String, Word> Words = 
			new HashMap<String, Word>();
	public static HashMap<Integer, ArrayList<Word>> SyllableDictionary = 
			new HashMap<Integer, ArrayList<Word>>();
	public static HashMap<POS, ArrayList<Word>> PartOfSpeechDictionary = 
			new HashMap<POS, ArrayList<Word>>();
	public static HashMap<Integer, ArrayList<Word>> SentimentDictionary = 
			new HashMap<Integer, ArrayList<Word>>();
	
	private static Word getWord(String spelling)
	{
		if(Words.containsKey(spelling)) return Words.get(spelling);
		Word word = new Word(spelling);
		Words.put(spelling, word);
		return word;
	}
	
	public static void LoadSyllableDictionary(String fileLocation) throws FileNotFoundException
	{
		Scanner s = new Scanner(new File(fileLocation));
		while(s.hasNextLine())
		{
			String line = s.nextLine();
			String[] parts = line.split(" ");
			String word_spelling = parts[0];
			int syllables = Integer.parseInt(parts[1]);
			
			Word word = getWord(word_spelling);
			word.syllables = syllables;
			if(!SyllableDictionary.containsKey(syllables))
				SyllableDictionary.put(syllables, new ArrayList<Word>());
			SyllableDictionary.get(syllables).add(word);
		}
		s.close();
	}
	
	//THE POS DICTIONARY IS USED AS THE "SOURCE" OF ALL WORDS.
	public static void LoadPOSDictionary(String fileLocation) throws FileNotFoundException
	{
		Scanner s = new Scanner(new File(fileLocation));
		while(s.hasNextLine())
		{
			String line = s.nextLine();
			String[] parts = line.split("\t");
			String word_spelling = parts[0].toUpperCase();
			char[] POS_text = parts[1].toCharArray();
			
			if(!Words.containsKey(word_spelling))continue;
			Word word = Words.get(word_spelling);
			for(char c : POS_text)
			{
				POS pos = POS.Unknown;
				if(c == 'N') pos = POS.Noun;
				if(c == 'P') pos = POS.Plural;
				if(c == 'h') pos = POS.Noun_Phrase;
				if(c == 'V') pos = POS.Verb_Usu_Participle;
				if(c == 't') pos = POS.Verb_Transitive;
				if(c == 'i') pos = POS.Verb_Intransitive;
				if(c == 'A') pos = POS.Adjective;
				if(c == 'v') pos = POS.Adverb;
				if(c == 'C') pos = POS.Conjunction;
				if(c == 'P') pos = POS.Preposition;
				if(c == '!') pos = POS.Interjection;
				if(c == 'r') pos = POS.Pronoun;
				if(c == 'D') pos = POS.Definite_Article;
				if(c == 'I') pos = POS.Indefinite_Article;
				if(c == 'o') pos = POS.Nominative;
				if(pos != POS.Unknown)
				{
					word.PartsOfSpeech.remove(POS.Unknown);
					word.PartsOfSpeech.add(pos);
					if(!PartOfSpeechDictionary.containsKey(pos))
						PartOfSpeechDictionary.put(pos, new ArrayList<Word>());
					PartOfSpeechDictionary.get(pos).add(word);
				}
			}
		}
		s.close();
	}

	public static void LoadSentimentDictionary(String fileLocation) throws FileNotFoundException
	{
		Scanner s = new Scanner(new File(fileLocation));
		while(s.hasNextLine())
		{
			String line = s.nextLine();
			String[] parts = line.split(" ");
			String word_spelling = parts[0];
			int syllables = Integer.parseInt(parts[1]);
			
			if(Words.containsKey(word_spelling))
			{
				Word w = Words.get(word_spelling);
				if(!SentimentDictionary.containsKey(syllables))
					SentimentDictionary.put(syllables, new ArrayList<Word>());
				SentimentDictionary.get(syllables).add(w);
			}
		}
		s.close();
	}
}
