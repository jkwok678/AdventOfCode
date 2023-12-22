package aoc.aoc2023;

import aoc.Day;

import java.util.List;

public class Day01 implements Day {

    static String[] words = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
    static String[] replaceWords = {"o1e", "t2o", "t3e", "f4r", "f5e", "s6x", "s7n", "e8t", "n9e"};


    @Override
    public String part1(List<String> input) {
        return String.valueOf(input.stream().mapToInt(Day01::getNumberFromString1).sum());
    }

    @Override
    public String part2(List<String> input) {
        return String.valueOf(input.stream().mapToInt(Day01::getNumberFromString2).sum());
    }


    public static int getNumberFromString1(String line) {
        String numbers = line.replaceAll("[^0-9.]", "");
        int first = Integer.parseInt(String.valueOf(numbers.charAt(0)));
        int last = Integer.parseInt(String.valueOf(numbers.charAt(numbers.length() - 1)));
        return first * 10 + last;
    }

    public static int getNumberFromString2(String line) {
        for (int i = 0; i < words.length; i++) {
            line = line.replace(words[i], replaceWords[i]);
        }

        return getNumberFromString1(line);
    }
}
