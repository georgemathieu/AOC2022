package aocDay4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class Day4 {

    /**
     * Takes 2 strings following the format xx-xx (xx being numbers).
     * Determines if one of the xx-xx completely overlaps the other.
     * (ex : 2-8 overlaps 3-7)
     * @param s1 format xx-xx
     * @param s2 format xx-xx
     * @return true if an assignment overlaps the other
     */
    static boolean isFullOverlap(String s1, String s2) {
        Integer[] firstAssignment = parseAssignment(s1);
        Integer[] secondAssignment = parseAssignment(s2);

        final boolean firstOverlapsSecond = firstAssignment[0] <= secondAssignment[0]
                && firstAssignment[1] >= secondAssignment[1];
        final boolean secondOverlapsFirst = secondAssignment[0] <= firstAssignment[0]
                && secondAssignment[1] >= firstAssignment[1];

        return firstOverlapsSecond || secondOverlapsFirst;
    }

    /**
     * Parses a string with the xx-xx (xx is an integer) format to an array of Integers.
     * @param s the string with the xx-xx format
     * @return the corresponding array of integers
     */
    static Integer[] parseAssignment(String s) {
        return Arrays.asList(s.split("-")).stream()
                .map(Integer::parseInt)
                .toArray(Integer[]::new);
    }

    public static void main(String[] args) throws IOException {
        Path inputPath = Paths.get("src/aocDay4/input_day4.txt");
        String input = Files.readString(inputPath);

        String[] pairs = input.split("\r\n");
        int compteur = 0;
        for (String pair : pairs) {
            String[] assignments = pair.split(",");
            if (isFullOverlap(assignments[0], assignments[1])) {
                compteur++;
            }
        }
        System.out.println(compteur);
    }
}
