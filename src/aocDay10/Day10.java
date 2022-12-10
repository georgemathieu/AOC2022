package aocDay10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Day10 {

    /**
     * The state of the register for each cycle.
     */
    private static List<Integer> registerStateHistory = new ArrayList<>();

    private static int registerState = 1;

    private static void interpreteInstruction(String instruction) {
        if (instruction.startsWith("noop")) {
            registerStateHistory.add(registerState);
        } else if (instruction.startsWith("addx")) {
            int increment = Integer.parseInt(instruction.split(" ")[1]);
            registerStateHistory.add(registerState);
            registerState += increment;
            registerStateHistory.add(registerState);
        } else {

        }
    }

    public static void main(String[] args) throws IOException {
        Path inputPath = Paths.get("src/aocDay10/input_day10.txt");
        String input = Files.readString(inputPath);

        // Register initial state
        registerStateHistory.add(registerState);

        String[] instructions = input.split("\r\n");
        for (String intruction : instructions) {
            interpreteInstruction(intruction);
        }

        // Part 1
        /* int signalStrengthSum = 0;
        for (int i = 20; i < registerStateHistory.size() && i <= 220; i += 40) {
            System.out.println("During cycle : " + i + ", register has the value : " + registerStateHistory.get(i));
            signalStrengthSum += i * registerStateHistory.get(i - 1);
        }
        System.out.println("Total sum of the signal strengths is : " + signalStrengthSum); */

        // Part 2
        for (int i = 0; i < registerStateHistory.size() && i <= 239; i++) {
            int spriteMiddlePosition = registerStateHistory.get(i);
            int crtPosition = i % 40;
            if (crtPosition >= spriteMiddlePosition - 1 && crtPosition <= spriteMiddlePosition + 1) {
                System.out.print('#');
            } else {
                System.out.print('.');
            }
            if (i % 40 == 39) {
                System.out.println();
            }
        }

    }


}
