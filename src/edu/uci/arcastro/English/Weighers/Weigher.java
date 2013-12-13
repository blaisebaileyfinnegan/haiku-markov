package edu.uci.arcastro.English.Weighers;

/**
 * Created by Alan Castro on 12/12/13.
 */

public interface Weigher<T> {
    public double getWeight(T item);
}
