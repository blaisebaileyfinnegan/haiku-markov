package edu.uci.arcastro.Generators;

import edu.uci.arcastro.Exceptions.ImpossibleException;
import edu.uci.arcastro.Word;

public interface HaikuGenerator 
{
	String Generate(Word[] seeds);
}
