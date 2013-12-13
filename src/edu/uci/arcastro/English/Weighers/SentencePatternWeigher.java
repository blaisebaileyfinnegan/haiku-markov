package edu.uci.arcastro.English.Weighers;

import edu.uci.arcastro.English.SentencePattern;

/**
 * Created by Alan Castro on 12/12/13.
 */
public class SentencePatternWeigher implements Weigher<SentencePattern>
{
    @Override
    public double getWeight(SentencePattern item)
    {
        return item.avg_applicability();
    }
}
