package edu.uci.arcastro.English;
import edu.uci.arcastro.English.POS;

import java.util.*;

public class HaikuPattern {
	public ArrayList<EnumSet<POS>> firstLine;
	public ArrayList<EnumSet<POS>> secondLine;
	public ArrayList<EnumSet<POS>> thirdLine;

	public HaikuPattern(){
		firstLine = new ArrayList<EnumSet<POS>>();
		secondLine = new ArrayList<EnumSet<POS>>();
		thirdLine = new ArrayList<EnumSet<POS>>();
	}
	
}
