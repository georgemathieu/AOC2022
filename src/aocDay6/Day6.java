package aocDay6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

public class Day6 {
	
	// Part 1, last 4 chars
	/*static int findFirstNonRepeatedCharacter(String input) {
		for (int i = 3; i < input.length(); i++) {
			char ref = input.charAt(i);
			char ref1 = input.charAt(i - 1);
			char ref2 = input.charAt(i - 2);
			char ref3 = input.charAt(i - 3);
			if (ref != ref1 && ref != ref2 && ref != ref3
					&& ref1 != ref2 && ref1 != ref3 && ref2 != ref3) {
				return i + 1;
			}
		}
		return 0;
	}*/
	
	/**
	 * Part 2, last 14 chars
	 */
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
