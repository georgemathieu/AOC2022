package aoc_day2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Day2 {

    enum Coup {
        ROCK(1),
        PAPER(2),
        SCISSORS(3);

        private int score;

        Coup(int score) {
            this.score = score;
        }

        public int getScore() {
            return this.score;
        }

        public static Coup toEnum(char c) {
            switch (c) {
                case 'A':
                case 'X':
                    return Coup.ROCK;
                case  'B':
                case  'Y':
                    return Coup.PAPER;
                case 'C':
                case 'Z':
                    return Coup.SCISSORS;
                default:
                    return null;
            }

        }


    }



    enum Result {
        LOSS(0),
        DRAW(3),
        WIN(6);

        private int score;

        Result(int score) {
            this.score = score;
        }

        public int getScore() {
            return score;
        }
    }

    public static int evaluateResult(char c1, char c2) {
        Coup coup1 = Coup.toEnum(c1);
        Coup coup2 = Coup.toEnum(c2);

        if (coup1.equals(Coup.ROCK) && coup2.equals(Coup.SCISSORS)) {
            return Result.LOSS.getScore() + coup2.getScore();
        } else if (coup1.equals(Coup.SCISSORS) && coup2.equals(Coup.ROCK)) {
            return Result.WIN.getScore() + coup2.getScore();
        } else if (coup1.getScore() > coup2.getScore()) {
            return Result.LOSS.getScore() + coup2.getScore();
        } else if (coup1.getScore() < coup2.getScore()) {
            return Result.WIN.getScore() + coup2.getScore();
        } else { // Draw
            return Result.DRAW.getScore() + coup2.getScore();
        }
    }

    public static void main(String[] args) throws IOException {
        Path inputPath = Paths.get("src/aoc_day2/input_day2.txt");
        String input = Files.readString(inputPath);

        long totalScore = 0;
        String[] matchs = input.split("\r\n");
        for (String match : matchs) {
            String[] moves = match.split(" ");
            totalScore += evaluateResult(moves[0].charAt(0), moves[1].charAt(0));
        }

        System.out.println(totalScore);


    }
}
