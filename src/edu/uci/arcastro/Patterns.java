package edu.uci.arcastro;

import edu.uci.arcastro.English.HaikuPattern;
import edu.uci.arcastro.English.POS;
import edu.uci.arcastro.English.SentencePattern;
import edu.uci.arcastro.English.Word;

import java.util.*;
import java.io.*;

public class Patterns {
	public static List<int[]> fiveSyllables = new ArrayList<int[]>();
	public static List<int[]> sevenSyllables = new ArrayList<int[]>();
	public static List<HaikuPattern> grammarPatterns = new ArrayList<HaikuPattern>();
    public static HashMap<SentencePattern, Integer> sentencePatterns = new HashMap<SentencePattern, Integer>();
    public static HashMap<Word, Integer> startingWords = new HashMap<Word, Integer>();
	
	public static void LoadFiveSyllables(String FileName) throws FileNotFoundException{
		Scanner reader = new Scanner(new File(FileName));
		
		while(reader.hasNextLine()){
			String lineOfText = reader.nextLine();
			String[] stArray = lineOfText.split(" ");
			int[] syllArray = new int[stArray.length];
			
			for(int i = 0; i<stArray.length; i++)
			{
				syllArray[i] = Integer.parseInt(stArray[i]);
			}
			
			fiveSyllables.add(syllArray);
		}
		
		reader.close();
	}
	
	public static void LoadSevenSyllables(String FileName) throws FileNotFoundException{
		Scanner reader = new Scanner(new File(FileName));
		
		while(reader.hasNextLine()){
			String lineOfText = reader.nextLine();
			String[] stArray = lineOfText.split(" ");
			int[] syllArray = new int[stArray.length];
			
			for(int i = 0; i<stArray.length; i++)
			{
				syllArray[i] = Integer.parseInt(stArray[i]);
			}
			
			sevenSyllables.add(syllArray);
		}
		
		reader.close();
	}
	
	public static void LoadHaikuPatterns(String FileName) throws FileNotFoundException{
		Scanner reader = new Scanner(new File(FileName));
		
		while(reader.hasNextLine())
		{
			EnumSet<POS> set = EnumSet.noneOf(POS.class);
			HaikuPattern newPttrn = new HaikuPattern();
			int numLine = 0;
			
			while(numLine < 3 && reader.hasNextLine())
			{
				String lineOfText = reader.nextLine();
				String[] stArray = lineOfText.split(" ");
				if(stArray[0].equals(""))
					break;
				
				for(int i = 0; i < stArray.length; i++)
				{
					POS pos = POS.Unknown;
					
					if(stArray[i].equals("Noun")) pos = POS.Noun;
					if(stArray[i].equals("Plural")) pos = POS.Plural;
					if(stArray[i].equals("Noun_Phrase")) pos = POS.Noun_Phrase;
					if(stArray[i].equals("Verb_Usu_Participle")) pos = POS.Verb_Usu_Participle;
					if(stArray[i].equals("Verb_Transitive")) pos = POS.Verb_Transitive;
					if(stArray[i].equals("Verb_Intransitive")) pos = POS.Verb_Intransitive;
					if(stArray[i].equals("Adjective")) pos = POS.Adjective;
					if(stArray[i].equals("Adverb")) pos = POS.Adverb;
					if(stArray[i].equals("Conjunction")) pos = POS.Conjunction;
					if(stArray[i].equals("Preposition")) pos = POS.Preposition;
					if(stArray[i].equals("Interjection")) pos = POS.Interjection;
					if(stArray[i].equals("Pronoun")) pos = POS.Pronoun;
					if(stArray[i].equals("Definite_Article")) pos = POS.Definite_Article;
					if(stArray[i].equals("Indefinite_Article")) pos = POS.Indefinite_Article;
					if(stArray[i].equals("Nominative")) pos = POS.Nominative;
					if(pos != POS.Unknown && !stArray[i].equals("|"))
						set.add(pos);
					
					if(stArray[i].equals("|") || i == stArray.length-1){
						if(numLine == 0) newPttrn.firstLine.add(set);
						if(numLine == 1) newPttrn.secondLine.add(set);
						if(numLine == 2) newPttrn.thirdLine.add(set);
						
						set = EnumSet.noneOf(POS.class); //create new set
					}	
				}
				numLine++;
			}
			
			if(numLine == 3) grammarPatterns.add(newPttrn);
		}
		reader.close();
		/*System.out.println(grammarPatterns.size());
		System.out.println(grammarPatterns.get(100).firstLine.get(0).toString());
		System.out.println(grammarPatterns.get(100).secondLine.get(0).toString());
		System.out.println(grammarPatterns.get(100).thirdLine.get(0).toString());*/
	}
}
