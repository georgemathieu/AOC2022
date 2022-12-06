package aocDay6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

public class Day6 {
	
	static int findFirstNonRepeatedCharacter(String input) {
		Set<Character> chars = new HashSet<>();
		for (int i = 13; i < input.length(); i++) {
			 chars.clear();
			for (int j = i; j > i - 14; j-- ) {
				chars.add(input.charAt(j));
			}
			
			if (chars.size() == 14) {
				return i + 1;
			}
		}
		return 0;
	}

	public static void main(String[] args) throws IOException {
		Path inputPath = Paths.get("src/aocDay6/input_day6.txt");
        String input = Files.readString(inputPath);
        
        System.out.println(findFirstNonRepeatedCharacter(input));
	}
}
