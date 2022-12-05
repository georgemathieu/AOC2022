package aocDay5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Day5 {
	
	/**
	 * Moves a given number of items from one stack to another.
	 * @param stackFrom the stack the items are move from
	 * @param stackTo the stack the items are move to
	 * @param toMove the number of items to move
	 */
	static void move(Stack<Character> stackFrom, Stack<Character> stackTo, int toMove) {
		for (int i = 0; i < toMove; i++) {
			stackTo.push(stackFrom.pop());
		}
	}
	

	public static void main(String[] args) throws IOException {
		Path inputPath = Paths.get("src/aocDay5/input_day5.txt");
        String input = Files.readString(inputPath);
        
        List<Stack<Character>> stacks = new ArrayList<>();
        Stack<Character> s0 = new Stack<>();
        s0.addAll(List.of('B', 'Q', 'C'));
        stacks.add(s0);
        
        Stack<Character> s1 = new Stack<>();
        s1.addAll(List.of('R', 'Q', 'W', 'Z'));
        stacks.add(s1);
        
        Stack<Character> s2 = new Stack<>();
        s2.addAll(List.of('B', 'M', 'R', 'L', 'V'));
        stacks.add(s2);
        
        Stack<Character> s3 = new Stack<>();
        s3.addAll(List.of('C', 'Z', 'H', 'V', 'T', 'W'));
        stacks.add(s3);
        
        Stack<Character> s4 = new Stack<>();
        s4.addAll(List.of('D', 'Z', 'H', 'B', 'N', 'V', 'G'));
        stacks.add(s4);
        
        Stack<Character> s5 = new Stack<>();
        s5.addAll(List.of('H', 'N', 'P', 'C', 'J', 'F', 'V', 'Q'));
        stacks.add(s5);
        
        Stack<Character> s6 = new Stack<>();
        s6.addAll(List.of('D', 'G', 'T', 'R', 'W', 'Z', 'S'));
        stacks.add(s6);
        
        Stack<Character> s7 = new Stack<>();
        s7.addAll(List.of('C', 'G', 'M', 'N', 'B', 'W', 'Z', 'P'));
        stacks.add(s7);
        
        Stack<Character> s8 = new Stack<>();
        s8.addAll(List.of('N', 'J', 'B', 'M', 'W', 'Q', 'F', 'P'));
        stacks.add(s8);
        
        String[] steps = Arrays.asList(input.split("\r\n")).stream()
        		.filter(s -> s.startsWith("move"))
        		.toArray(String[]::new);
        
        for (String step : steps) {
        	String[] stepParsed = step.split(" "); // ex : move 3 from 6 to 2
        	int nbItems = Integer.parseInt(stepParsed[1]);
        	Stack<Character> stackFrom = stacks.get(Integer.parseInt(stepParsed[3]) - 1);
        	Stack<Character> stackTo = stacks.get(Integer.parseInt(stepParsed[5]) - 1);
        	move(stackFrom, stackTo, nbItems);
		}
        
        for (Stack<Character> stack : stacks) {
			System.out.print(stack.peek());
		}
        
	}
}
