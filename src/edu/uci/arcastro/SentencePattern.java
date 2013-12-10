package edu.uci.arcastro;
import java.util.*;

public class SentencePattern {
	public ArrayList<EnumSet<POS>> firstLine;
	public ArrayList<EnumSet<POS>> secondLine;
	public ArrayList<EnumSet<POS>> thirdLine;

	public SentencePattern(){
		firstLine = new ArrayList<EnumSet<POS>>();
		secondLine = new ArrayList<EnumSet<POS>>();
		thirdLine = new ArrayList<EnumSet<POS>>();
	}
	
}
