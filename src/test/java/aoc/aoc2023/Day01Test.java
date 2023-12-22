package aoc.aoc2023;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day01Test {

    Day01 day01;

    @BeforeEach
    void setUp() {
        day01 = new Day01();
    }

    @Test
    public void getNumberFromString1Test1() {
        String test = "1abc2";
        assertEquals(12, Day01.getNumberFromString1(test));
    }

    @Test
    public void getNumberFromString1Test2() {
        String test = "pqr3stu8vwx";
        assertEquals(38, Day01.getNumberFromString1(test));

    }

    @Test
    public void getNumberFromString1Test3() {
        String test = "a1b2c3d4e5f";
        assertEquals(15, Day01.getNumberFromString1(test));
    }

    @Test
    public void getNumberFromString1Test4() {
        String test = "treb7uchet";
        assertEquals(77, Day01.getNumberFromString1(test));
    }

    @Test
    public void getNumberFromString2Test1() {
        String test = "oneight";
        assertEquals(18, Day01.getNumberFromString2(test));
    }

}