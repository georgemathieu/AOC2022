package aocDay8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Day8Part2 {

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

    private static class Tree {
        Coordinates coord;
        int height;

        int canSeeAbove;
        int canSeeBelow;
        int canSeeLeft;
        int canSeeRight;

        public Tree(Coordinates coord, int height) {
            this.coord = coord;
            this.height = height;
        }

        public int scenicScore() {
            return canSeeAbove * canSeeBelow * canSeeLeft * canSeeRight;
        }
    }

    private static class TreeUtils {
        static boolean sameColumn(Tree t1, Tree t2) {
            return t1.coord.x == t2.coord.x;
        }

        static boolean sameRow(Tree t1, Tree t2) {
            return t1.coord.y == t2.coord.y;
        }
    }

    static Map<Coordinates, Tree> trees = new HashMap();

    public static void main(String[] args) throws IOException {
        Path inputPath = Paths.get("src/aocDay8/input_day8.txt");
        String input = Files.readString(inputPath);

        int row = 0;
        int column = 0;
        int maxRow = 0;
        int maxColumn = 0;
        String[] treeInput = input.split("\r\n");
        for (String tree : treeInput) {
            for (char i : tree.toCharArray()) {
                int height = Character.getNumericValue(i);
                Coordinates c = new Coordinates(column, row);
                trees.put(c, new Tree(c, height));
                maxColumn = column;
                column++;

            }
            column = 0;
            maxRow =  row;
            row++;
        }

        for (Tree tree : trees.values()) {
            // Above
            for(int y = tree.coord.y - 1; y >= 0; y--) {
                Tree t = trees.get(new Coordinates(tree.coord.x, y));
                if (t.height < tree.height) {
                    tree.canSeeAbove++;
                } else {
                    tree.canSeeAbove++;
                    break;
                }
            }

            // Below
            for(int y = tree.coord.y + 1; y <= maxRow; y++) {
                Tree t = trees.get(new Coordinates(tree.coord.x, y));
                if (t.height < tree.height) {
                    tree.canSeeBelow++;
                } else {
                    tree.canSeeBelow++;
                    break;
                }
            }

            // Left
            for(int x = tree.coord.x - 1; x >= 0; x--) {
                Tree t = trees.get(new Coordinates(x, tree.coord.y));
                if (t.height < tree.height) {
                    tree.canSeeLeft++;
                } else {
                    tree.canSeeLeft++;
                    break;
                }
            }

            // Right
            for(int x = tree.coord.x + 1; x <= maxColumn; x++) {
                Tree t = trees.get(new Coordinates(x, tree.coord.y));
                if (t.height < tree.height) {
                    tree.canSeeRight++;
                } else {
                    tree.canSeeRight++;
                    break;
                }
            }
        }

        System.out.println(trees.values().stream().mapToInt(Tree::scenicScore).max().getAsInt());
    }

}
