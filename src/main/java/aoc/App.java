package aoc;

import aoc.aoc2023.Day05;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class App {

    private static final Map<String, Day> DAYS2023;

    static {
        DAYS2023 = new HashMap<>();
//        DAYS2023.put("01", new Day01());
//        DAYS2023.put("02", new Day02());
//        DAYS2023.put("03", new Day03());
//        DAYS2023.put("04", new Day04());
        DAYS2023.put("05", new Day05());
    }

    public static void main(String[] args) {
        System.out.println("AOC23");
        for (String day : DAYS2023.keySet()) {
            List<String> lines;
            String pathString = "src/main/resources/aoc/aoc2023/Day" + day + "Input.txt";
            Path path = Paths.get(pathString);
            try {
                Stream<String> stream = Files.lines(path);
                lines = stream.toList();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("AOC.Day " + day);
            System.out.print("Part 1: ");
            System.out.println(DAYS2023.get(day).part1(lines));
            System.out.print("Part 2:");
            System.out.println(DAYS2023.get(day).part2(lines));

        }
    }
}
