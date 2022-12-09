package aocDay9;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;

public class Day9 {

    static Consumer<Coordinates> fetchDirectionFunction(char direction) {
        switch(direction) {
            case 'U':
                return coord -> coord.y += 1;

            case 'D':
                return coord -> coord.y -= 1;

            case 'R':
                return coord -> coord.x += 1;

            case 'L':
                return coord -> coord.x -= 1;

            default:
                return null;
        }

    }

    static Coordinates computeAdjustedTailPosition(char direction) {
        switch(direction) {
            case 'U':
                return new Coordinates(headPosition.x, headPosition.y - 1);

            case 'D':
                return new Coordinates(headPosition.x, headPosition.y + 1);

            case 'R':
                return new Coordinates(headPosition.x - 1, headPosition.y);

            case 'L':
                return new Coordinates(headPosition.x + 1, headPosition.y);

            default:
                return null;
        }
    }

    static void adjustTailPosition(char direction) {
        // Check if points are already adjacent, if so do nothing
        if (Math.abs(headPosition.x - tailPosition.x) <= 1 && Math.abs(headPosition.y - tailPosition.y) <= 1) {
            return;
        } else {
            tailPosition = computeAdjustedTailPosition(direction);
        }
    }

    static void interpreteMove(char direction, int distance) {
        Consumer<Coordinates> applyMove = fetchDirectionFunction(direction);
        for (int i = 0; i < distance; i++) {
            applyMove.accept(headPosition);
            adjustTailPosition(direction);
            tailPositionHistory.add(tailPosition);
        }
    }

    private static class Coordinates {
        int x;
        int y;

        public Coordinates(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Coordinates that = (Coordinates) o;
            return x == that.x && y == that.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    private static Coordinates headPosition = new Coordinates(0, 0);
    private static Coordinates tailPosition = new Coordinates(0, 0);
    private static Set<Coordinates> tailPositionHistory = new HashSet<>();

    public static void main(String[] args) throws IOException {
        Path inputPath = Paths.get("src/aocDay9/input_day9.txt");
        String input = Files.readString(inputPath);

        tailPositionHistory.add(tailPosition);

        String[] commands = input.split("\r\n");
        for (String command : commands) {
            String[] commandDetailled = command.split(" ");
            interpreteMove(commandDetailled[0].charAt(0), Integer.parseInt(commandDetailled[1]));
        }
        System.out.println("Number of tail positions : " + tailPositionHistory.size());
    }
}
