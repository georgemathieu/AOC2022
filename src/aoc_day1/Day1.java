package aoc_day1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.NavigableSet;
import java.util.TreeSet;
import java.util.function.Function;

public class Day1 {

    public static void main(String[] args) throws IOException {
        Path inputPath = Paths.get("src/aoc_day1/input_day1.txt");
        String input = Files.readString(inputPath);
        // System.out.println(input);

        /*
         * Base - Fin top calories
         */
        /* String[] elves = input.split("\r\n\r\n");
        long max = 0;
        for (String elf : elves) {
            String[] parsedCalories = elf.split("\r\n");
            long calories = Arrays.asList(parsedCalories).stream().mapToLong(s -> Long.parseLong(s)).sum();
            if (calories > max) {
                max = calories;
            }
        }
        System.out.println(max);*/

        /*
         * Variant - Find top 3 calories
         */
        NavigableSet<Long> sortedCalories = new TreeSet<>();
        String[] elves = input.split("\r\n\r\n");
        for (String elf : elves) {
            String[] parsedCalories = elf.split("\r\n");
            sortedCalories.add(Arrays.asList(parsedCalories).stream().mapToLong(s -> Long.parseLong(s)).sum());
        }
        System.out.println(sortedCalories.descendingSet().stream()
                .mapToLong(l -> l)
                .limit(3)
                .sum());
    }
}
