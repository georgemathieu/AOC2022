package aocDay12;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Day12 {

    public static void main(String[] args) throws IOException {
        Path inputPath = Paths.get("src/aocDay12/input_day12.txt");
        String input = Files.readString(inputPath);
    }
}
