package aoc.aoc2023;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day03Test {

    Day03 day03;
    String lines = """
            467..114..
            ...*......
            ..35..633.
            ......#...
            617*......
            .....+.58.
            ..592.....
            ......755.
            ...$.*....
            .664.598..
            """;

    @BeforeEach
    public void setUp() {

        day03 = new Day03();
    }
    
    @Test
    public void checkLeftSideIsDigit() {
        String line = "2*.";
        assertEquals("2", day03.getLeftNumber(line, 1, ""));
    }

    @Test
    public void checkLeftSideIsNumber() {
        String line = "333*.";
        assertEquals("333", day03.getLeftNumber(line, 3, ""));
    }

    @Test
    public void checkRightSideIsDigit() {
        String line = ".*3";
        assertEquals("3", day03.getRightNumber(line, 1, ""));
    }

    @Test
    public void checkRightSideIsNumber() {
        String line = ".*333.";
        assertEquals("333", day03.getRightNumber(line, 1, ""));
    }


}