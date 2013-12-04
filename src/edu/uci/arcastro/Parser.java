package edu.uci.arcastro;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Parser 
{
	public static void TrainWithCorpus(String fileLocation) throws FileNotFoundException
	{
		Scanner s = new Scanner(new File(fileLocation));
		ArrayList<String> sentence = new ArrayList<String>();
		while(s.hasNext())
		{
			String spelling = s.next().toUpperCase().replace("[\",\\,]", "");
			boolean endOfSentence = false;
			while(spelling.endsWith(".") || spelling.endsWith("?") || spelling.endsWith("!") || spelling.endsWith(";"))
			{
				spelling = spelling.substring(0, spelling.length() - 1);
				endOfSentence = true;
			}
			sentence.add(spelling);
			if(endOfSentence)
			{
				TrainWithSentence(sentence);
				sentence.clear();
			}
		}
		if(sentence.size() > 0)
		{
			TrainWithSentence(sentence);
			sentence.clear();
		}
		s.close();
	}
	
	public static void TrainWithSentence(ArrayList<String> sentence)
	{
		ArrayList<Word> words = new ArrayList<Word>();
		for(String s : sentence)
		{
			Word w = Dictionary.Words.get(s);
			words.add(w);
		}
		
		for(int i = 0; i < words.size() - 1; i++)
		{
			Word w1 = words.get(i);
			if(w1 == null) continue;
			
			w1.frequency++;
			for(int j = i + 1; j < words.size(); j++)
			{
				Word assoc = words.get(j);
				if(assoc == null) continue;
				
				Integer count = w1.Associated.get(assoc);
				if(count ==null) w1.Associated.put(assoc, (count = new Integer(1)));
				else count++;
				
				count = assoc.Associated.get(w1);
				if(count ==null) assoc.Associated.put(w1, (count = new Integer(1)));
				else count++;
			}

			Word w2 = words.get(i+1);
			if (w2 == null) continue;
			
			Integer count = w1.CollocatedAfter.get(w1);
			if(count ==null) w1.CollocatedAfter.put(w2, (count = new Integer(1)));
			else count++;
			
			count = w2.CollocatedBefore.get(w1);
			if(count ==null) w2.CollocatedBefore.put(w1, (count = new Integer(1)));
			else count++;
		}
		
		if(words.size() > 0 && words.get(words.size() - 1) != null) words.get(words.size() - 1).frequency++;
	}
	
}
