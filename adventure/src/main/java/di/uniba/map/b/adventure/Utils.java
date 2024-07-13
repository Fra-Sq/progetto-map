/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package di.uniba.map.b.adventure;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Utility class providing static methods for common operations such as loading a list of strings from a file into a set and parsing strings into a list of tokens.
 */
public class Utils {

    /**
     * Loads each line from a specified file into a {@link Set} of strings. This method is useful for loading data where each line represents a unique entry, such as a list of stopwords.
     *
     * @param file The file to read from.
     * @return A {@link Set} containing all lines from the file, trimmed and converted to lowercase.
     * @throws IOException If an I/O error occurs reading from the file.
     */
    public static Set<String> loadFileListInSet(File file) throws IOException {
        Set<String> set = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while (reader.ready()) {
                set.add(reader.readLine().trim().toLowerCase());
            }
        }
        return set;
    }

    /**
     * Parses a given string into a list of tokens (words), excluding any tokens that are contained in a provided set of stopwords. This method is useful for text processing tasks such as tokenization.
     *
     * @param string The string to parse.
     * @param stopwords A {@link Set} of stopwords to exclude from the resulting list of tokens.
     * @return A {@link List} of tokens derived from the input string, excluding any stopwords.
     */
    public static List<String> parseString(String string, Set<String> stopwords) {
        List<String> tokens = new ArrayList<>();
        String[] split = string.toLowerCase().split("\\s+");
        for (String t : split) {
            if (!stopwords.contains(t)) {
                tokens.add(t);
            }
        }
        return tokens;
    }

}
