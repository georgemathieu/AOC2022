package aocDay3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Day3 {

    public static int toPriority(char c) {
        int charValue = (int) c;
        if (!Character.isAlphabetic(charValue)) {
            return 0;
        }
        if (Character.isUpperCase(c)) {
            return charValue - 38;
        } else { // Lower case
            return charValue - 96;
        }
    }

    public static int findCommonChar(String s1, String s2) {
        return s1.chars()
                .filter(c -> s2.indexOf(c) != -1)
                .findFirst().orElse(0);
    }

    public static void main(String[] args) throws IOException {
        Path inputPath = Paths.get("src/aocDay3/input_day3.txt");
        String input = Files.readString(inputPath);

        String[] rucksacks = input.split("\r\n");
        long total = 0;
        for (String sack : rucksacks) {
            int middle = sack.length() / 2;
            String firstHalf = sack.substring(0, middle);
            String secondHalf = sack.substring(middle);
            total += toPriority((char) findCommonChar(firstHalf, secondHalf));
        }
        System.out.println(total);
    }
}
