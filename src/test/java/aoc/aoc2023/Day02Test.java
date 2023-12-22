package aoc.aoc2023;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day02Test {
    Day02 day02;

    @BeforeEach
    void setUp() {
        day02 = new Day02();
    }

    @Test
    public void getIfPossibleTest1() {
        String test = "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green";


        assertEquals(1, Day02.getIfPossible(test));
    }

    @Test
    public void getPowerTest1() {
        String test = "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green";
        assertEquals(48, Day02.getPower(test));

    }
}
