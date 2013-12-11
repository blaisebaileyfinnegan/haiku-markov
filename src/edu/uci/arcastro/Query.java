package edu.uci.arcastro;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//Helper class
public class Query 
{
	private static Random r = new Random();
	public static ArrayList<int[]> FiveSyllablePatternWithNWords(int N)
	{
		ArrayList<int[]> ToReturn = new ArrayList<int[]>();
		for(int[] pattern : Patterns.fiveSyllables)
			if(pattern.length == N) ToReturn.add(pattern);
		return ToReturn;
	}

	public static ArrayList<int[]> SevenSyllablePatternWithNWords(int N)
	{
		ArrayList<int[]> ToReturn = new ArrayList<int[]>();
		for(int[] pattern : Patterns.sevenSyllables)
			if(pattern.length == N) ToReturn.add(pattern);
		return ToReturn;
	}
	
	public static <T> T ChooseRandom(List<T> Items)
	{
		return Items.get(r.nextInt(Items.size()));
	}
	
}
