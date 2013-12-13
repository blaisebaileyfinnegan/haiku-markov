package edu.uci.arcastro;

import edu.uci.arcastro.English.Weighers.Weigher;
import edu.uci.arcastro.Exceptions.ImpossibleException;

import java.util.*;

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

    public static <T> T ChooseWeightedRandom(Set<Map.Entry<T, Double>> Items) throws ImpossibleException
    {
        if (Items.isEmpty()) {
            throw new ImpossibleException("Can't choose an entry from an empty set");
        }

        double totalFrequency = 0;
        for (Map.Entry<T, Double> e : Items) {
            totalFrequency += e.getValue();
        }

        double random = Math.random() * totalFrequency;
        for (Map.Entry<T, Double> e : Items) {
            random -= e.getValue();
            if (random < 0.0d) {
                return e.getKey();
            }
        }

        throw new ImpossibleException("Weighted random picker didn't work");
    }

    public static <T> T ChooseWeightedRandom(Set<T> Items, Weigher<T> weigher) throws ImpossibleException
    {
        if (Items.isEmpty()) {
            throw new ImpossibleException("Can't choose an entry from an empty set");
        }

        double totalWeight = 0;
        for (T item : Items) {
            totalWeight += weigher.getWeight(item);
        }

        double random = Math.random() * totalWeight;
        for (T item : Items) {
            random -= weigher.getWeight(item);
            if (random < 0.0d) {
                return item;
            }
        }

        throw new ImpossibleException("Generic Weighted random picker didn't work");
    }
}
