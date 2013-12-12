package edu.uci.arcastro;

import java.io.FileNotFoundException;
import java.util.Scanner;

import edu.uci.arcastro.Generators.HaikuGenerator;
import edu.uci.arcastro.Generators.UsingPatterns;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		System.out.print("Reading dictionaries from file...");
		Global.Initialize();
		System.out.println("Done");
		
		HaikuGenerator g = new UsingPatterns();
		Scanner s = new Scanner(System.in);
		while(s.hasNextLine())
		{
			s.nextLine();
			System.out.println(g.Generate(null));
		}
		s.close();
	}

}
