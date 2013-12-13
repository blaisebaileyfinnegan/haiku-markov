package edu.uci.arcastro;

import java.util.EnumSet;

public class Blank {
    public final EnumSet<POS> pos;
    public final int syllableCount;

    public Blank(EnumSet<POS> pos, int syllableCount) {
        this.pos = pos;
        this.syllableCount = syllableCount;
    }
}
