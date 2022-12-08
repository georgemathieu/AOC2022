package aocDay8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

public class Day8 {

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

        boolean isVisible;

        public Tree(Coordinates coord, int height) {
            this.coord = coord;
            this.height = height;
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

    private static List<Tree> trees = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        Path inputPath = Paths.get("src/aocDay8/input_day8.txt");
        String input = Files.readString(inputPath);

        int row = 0;
        int column = 0;
        int maxRow = 0;
        int maxColumn = 0;
        String[] treeInput = input.split("\r\n");
        for (String tree : treeInput) {
            for (char c : tree.toCharArray()) {
                int height = Character.getNumericValue(c);
                trees.add(new Tree(new Coordinates(column, row), height));
                maxColumn = column;
                column++;

            }
            column = 0;
            maxRow =  row;
            row++;
        }

        for (Tree tree : trees) {
            if (tree.coord.x == 0 || tree.coord.x == maxColumn
                    || tree.coord.y == 0 || tree.coord.y == maxRow) {
                tree.isVisible = true;
                continue;
            }

            // Is visible from above
            Optional<Tree> isVisibleFromAbove = trees.stream().filter(t -> {
                return TreeUtils.sameColumn(tree, t) && tree.coord.y > t.coord.y && tree.height <= t.height;
            }).findFirst();
            if (isVisibleFromAbove.isEmpty()) {
                tree.isVisible = true;
            }

            // Is visible from below
            Optional<Tree> isVisibleFromBelow = trees.stream().filter(t -> {
                return TreeUtils.sameColumn(tree, t) && tree.coord.y < t.coord.y && tree.height <= t.height;
            }).findFirst();
            if (isVisibleFromBelow.isEmpty()) {
                tree.isVisible = true;
            }

            // Is visible from the left
            Optional<Tree> isVisibleFromLeft = trees.stream().filter(t -> {
                return TreeUtils.sameRow(tree, t) && tree.coord.x > t.coord.x && tree.height <= t.height;
            }).findFirst();
            if (isVisibleFromLeft.isEmpty()) {
                tree.isVisible = true;
            }

            // Is visible from the right
            Optional<Tree> isVisibleFromRight = trees.stream().filter(t -> {
                return TreeUtils.sameRow(tree, t) && tree.coord.x < t.coord.x && tree.height <= t.height;
            }).findFirst();
            if (isVisibleFromRight.isEmpty()) {
                tree.isVisible = true;
            }
        }

        System.out.println(trees.stream().filter(t -> t.isVisible).count());
    }

}
