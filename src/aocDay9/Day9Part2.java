package aocDay9;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Consumer;

public class Day9Part2 {

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

    static void adjustTailPosition(char direction, int index) {
        // Check if points are already adjacent, if so do nothing
        Coordinates previousNode = ropePositions.get(index - 1);
        Coordinates currentNode = ropePositions.get(index);
        if (Math.abs(previousNode.x - currentNode.x) <= 1 && Math.abs(previousNode.y - currentNode.y) <= 1) {
            return;
        } else {
            int diffX = Math.max(-1, Math.min(previousNode.x - currentNode.x, 1));
            int diffY = Math.max(-1, Math.min(previousNode.y - currentNode.y, 1));
            ropePositions.set(index, new Coordinates(currentNode.x + diffX, currentNode.y + diffY));
        }
    }

    static void interpreteMove(char direction, int distance) {
        Consumer<Coordinates> applyMove = fetchDirectionFunction(direction);
        for (int i = 0; i < distance; i++) {
            applyMove.accept(ropePositions.get(0));
            for (int j = 1; j < ropePositions.size(); j++) {
                adjustTailPosition(direction, j);
            }
            tailPositionHistory.add(ropePositions.get(9));
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

    private static List<Coordinates> ropePositions = new ArrayList<>();
    private static Set<Coordinates> tailPositionHistory = new HashSet<>();

    public static void main(String[] args) throws IOException {
        Path inputPath = Paths.get("src/aocDay9/input_day9.txt");
        String input = Files.readString(inputPath);

        for(int i = 0; i < 10; i++) {
            ropePositions.add(new Coordinates(0, 0));
        }

        tailPositionHistory.add(ropePositions.get(9));

        String[] commands = input.split("\r\n");
        for (String command : commands) {
            String[] commandDetailled = command.split(" ");
            interpreteMove(commandDetailled[0].charAt(0), Integer.parseInt(commandDetailled[1]));
        }
        System.out.println("Number of tail positions : " + tailPositionHistory.size());
    }
}
