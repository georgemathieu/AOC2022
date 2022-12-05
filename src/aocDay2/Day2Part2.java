package aocDay2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Day2Part2 {

    private enum Coup {
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
                    return Coup.ROCK;
                case  'B':
                    return Coup.PAPER;
                case 'C':
                    return Coup.SCISSORS;
                default:
                    return null;
            }

        }


    }



    private enum Result {
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

        public static Result toEnum(char c) {
            switch (c) {
                case 'X':
                    return Result.LOSS;
                case 'Y':
                    return Result.DRAW;
                case 'Z':
                    return Result.WIN;
                default:
                    return null;
            }
        }
    }

    public static int evaluateResult(char c1, char c2) {
        Coup coup1 = Coup.toEnum(c1);
        Result result = Result.toEnum(c2);

        if (result.equals(Result.DRAW)) {
            return Result.DRAW.getScore() + coup1.getScore();

        } else if (result.equals(Result.LOSS)) {
            int lossScore = Result.LOSS.getScore();
            if (coup1.equals(Coup.ROCK)) {
                return lossScore + Coup.SCISSORS.getScore();
            } else if (coup1.equals(Coup.PAPER)) {
                return lossScore + Coup.ROCK.getScore();
            } else { // SCISSORS
                return lossScore + Coup.PAPER.getScore();
            }

        } else { // WIN
            int wScore = Result.WIN.getScore();
            if (coup1.equals(Coup.ROCK)) {
                return wScore + Coup.PAPER.getScore();
            } else if (coup1.equals(Coup.PAPER)) {
                return wScore + Coup.SCISSORS.getScore();
            } else { // SCISSORS
                return wScore + Coup.ROCK.getScore();
            }
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
