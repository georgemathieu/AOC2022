package aocDay3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Day3Part2 {

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

    public static int findCommonChar(String s1, String s2, String s3) {
        return s1.chars()
                .filter(c -> s2.indexOf(c) != -1 && s3.indexOf(c) != -1)
                .findFirst().orElse(0);
    }

    public static void main(String[] args) throws IOException {
        Path inputPath = Paths.get("src/aoc_day3/input_day3.txt");
        String input = Files.readString(inputPath);

        String[] rucksacks = input.split("\r\n");
        long total = 0;
        for (int i = 0; i < rucksacks.length; i += 3) {
            total += toPriority((char) findCommonChar(rucksacks[i], rucksacks[i + 1], rucksacks[i + 2]));
        }
        System.out.println(total);
    }
}
