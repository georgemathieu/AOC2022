package aocDay7;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Day7 {
	
	static void interpreteCommand(String command) {
		
	}
	
	public static void main(String[] args) throws IOException {
		Path inputPath = Paths.get("src/aocDay7/input_day7.txt");
        String input = Files.readString(inputPath);
        
        String[] commands = input.split("\r\n");
        for (String command : commands) {
			
		}
	}

}
