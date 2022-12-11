package aocDay11;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntConsumer;
import java.util.regex.Pattern;

public class Day11 {

    private static class Monkey {
        List<Long> itemWorry = new ArrayList<>();
        Function<Long, Long> operation;
        Consumer<Long> test;
        List<Long> itemsToRemove = new ArrayList<>();
        int inspectCount = 0;

        public int getInspectCount() {
            return inspectCount;
        }
    }

    private static List<Monkey> monkeys = new ArrayList<>();

    private static Function<Long, Long> interpreteOperation(String operationInput) {
        String operationContent = operationInput.split("new = old ")[1];
        char operator = operationContent.charAt(0);

        Pattern pattern = Pattern.compile(".*[0-9].*");
        String inputValue = operationContent.split(" ")[1];
        Long value;
        if (pattern.matcher(inputValue).find()) {
            value = Long.parseLong(inputValue);
        } else {
            value = -1l;
        }

        Function<Long, Long> function = null;
        switch (operator) {
            case '+':
                function = (old) -> old + (value != -1 ? value : old);
                break;
            case '-':
                function = (old) -> old - (value != -1 ? value : old);
                break;

            case '*':
                function = (old) -> old * (value != -1 ? value : old);
                break;

            default:
                break;
        }
        return function;
    }

    private static Consumer<Long> interpreteTest(String diviseInput, String passeInputTrue, String passeInputFalse,
                                                    int initialMonkey) {
        Integer diviseValue = Integer.parseInt(diviseInput.split("Test: divisible by ")[1]);
        Integer passeTrue = Integer.parseInt(passeInputTrue.split("If true: throw to monkey ")[1]);
        Integer passeFalse = Integer.parseInt(passeInputFalse.split("If false: throw to monkey ")[1]);

        return (value) -> {
            if (value % diviseValue == 0) {
                monkeys.get(passeTrue).itemWorry.add(value);
                monkeys.get(initialMonkey).itemsToRemove.add(value);
            } else {
                monkeys.get(passeFalse).itemWorry.add(value);
                monkeys.get(initialMonkey).itemsToRemove.add(value);
            }
        };
    }

    public static void main(String[] args) throws IOException {
        Path inputPath = Paths.get("src/aocDay11/input_day11.txt");
        String input = Files.readString(inputPath);

        String[] lines = input.split("\r\n");
        // Build monkeys
        for(int i = 0; i < lines.length; i += 7) {
            Monkey m = new Monkey();
            monkeys.add(m);

            // Item list
            String[] itemsInput = lines[i + 1].split(": ")[1].split(", ");
            for (String item: itemsInput) {
                m.itemWorry.add(Long.parseLong(item));
            }

            // Operation
            m.operation = interpreteOperation(lines[i + 2]);

            // Test - divisible
            m.test = interpreteTest(lines[i + 3], lines[i + 4], lines[i + 5], monkeys.size() - 1);
        }

        int nbRounds = 10_000;
        for(int round = 0; round < nbRounds; round++) {
            for (Monkey monkey : monkeys) {
                for (int i = 0; i < monkey.itemWorry.size(); i++) {
                    Long item = monkey.itemWorry.get(i);
                    monkey.inspectCount++;
                    item = monkey.operation.apply(item);

                    // Part 1 only
                    // item /= 3;

                    monkey.itemWorry.set(i, item);
                    monkey.test.accept(item);
                }
                monkey.itemWorry.removeAll(monkey.itemsToRemove);
                monkey.itemsToRemove.clear();
            }
        }

        System.out.println("Monkey business : " + monkeys.stream()
                .map(m -> Integer.valueOf(m.getInspectCount()))
                .sorted(Collections.reverseOrder())
                .limit(2)
                .mapToInt(i -> i)
                .reduce((i1, i2) -> i1 * i2)
                .getAsInt()
        );
    }
}
