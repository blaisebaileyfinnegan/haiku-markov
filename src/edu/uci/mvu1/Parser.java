package edu.uci.mvu1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    public static String loadFromFile(String path) {
        StringBuilder buffer = new StringBuilder();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(path));
            String currentLine = reader.readLine();
            while (currentLine != null) {
                buffer.append(currentLine + "\n");
                currentLine = reader.readLine();
            }

            return buffer.toString();
        } catch(Exception e) {
            return null;
        }
    }

    public static List<String> parseCorpus(String corpus) {
        Matcher m = Pattern.compile("\\w+").matcher(corpus);
        ArrayList<String> matches = new ArrayList<String>();
        while(m.find()) {
            matches.add(m.group().toLowerCase());
        }

        return matches;
    }

    public static HashMap<String, Integer> parseSyllables(String body) {
        String[] lines = body.split("\n");
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        for (String line : lines) {
            String[] columns = line.split(" ");
            map.put(columns[0].toLowerCase(), Integer.parseInt(columns[1]));
        }

        return map;
    }

}
