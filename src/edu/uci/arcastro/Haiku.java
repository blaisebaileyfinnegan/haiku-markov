package edu.uci.arcastro;

import edu.uci.arcastro.English.Word;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class Haiku {
    public List<List<Word>> lines = new ArrayList<List<Word>>();

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();

        for (List<Word> line : lines) {
            s.append(StringUtils.join(line, " "));
            s.append("\n");
        }

        return s.toString();
    }
}
