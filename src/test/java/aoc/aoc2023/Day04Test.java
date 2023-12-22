package aoc.aoc2023;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

public class Day04Test {

    Day04 day04;
    List<String> lines;

    @BeforeEach
    public void setUp() {
        lines = new ArrayList<>();
        lines.add("Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53");
        lines.add("Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19");
        lines.add("Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1");
        lines.add("Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83");
        lines.add("Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36");
        lines.add("Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11");
        day04 = new Day04();
    }


    @Test
    public void readWinningNumbers() {
        String line = "Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53";
        assertIterableEquals(List.of(41, 48, 83, 86, 17), day04.getWinningNumbers(line));
    }

    @Test
    public void readWinningNumbers2() {
        String line = "Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1";
        assertIterableEquals(List.of(1, 21, 53, 59, 44), day04.getWinningNumbers(line));
    }


    @Test
    public void readNumbers() {
        String line = "Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53";
        assertIterableEquals(List.of(83, 86, 6, 31, 17, 9, 48, 53), day04.getNumbers(line));
    }

    @Test
    void checkMatches() {
        String line = "Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53";
        List<Integer> winningNumbers = day04.getWinningNumbers(line);
        List<Integer> numbers = day04.getNumbers(line);
        assertEquals(4, day04.findMatches(winningNumbers, numbers));

    }

    @Test
    void checkPower() {
        assertEquals(8, day04.findPower(4));
    }

    @Test
    void testPart1() {
        assertEquals(13, Integer.parseInt(day04.part1(lines)));
    }

    @Test
    void testPart2() {
        assertEquals(30, Integer.parseInt(day04.part2(lines)));
    }

}
