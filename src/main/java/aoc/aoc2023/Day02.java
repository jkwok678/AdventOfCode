package aoc.aoc2023;

import aoc.Day;

import java.util.List;

public class Day02 implements Day {

    @Override
    public String part1(List<String> input) {
        return String.valueOf(input.stream().mapToInt(Day02::getIfPossible).sum());
    }

    @Override
    public String part2(List<String> input) {
        return String.valueOf(input.stream().mapToInt(Day02::getPower).sum());
    }

    public static int getIfPossible(String line) {
        String newLine = line.replace("Game ", "");
        int game = Integer.parseInt(newLine.split(":")[0]);
        String gameString = game + ":";
        newLine = newLine.replace(gameString, "");
        int maxRed = 12, maxGreen = 13, maxBlue = 14;
        for (String round : newLine.split(";")) {
            for (String picks : round.split(",")) {
                String[] balls = picks.split(" ");
                if (balls[2].equals("red")) {
                    if (Integer.parseInt(balls[1]) > maxRed) {
                        return 0;
                    }
                } else if (balls[2].equals("green")) {
                    if (Integer.parseInt(balls[1]) > maxGreen) {
                        return 0;
                    }
                } else if (balls[2].equals("blue")) {
                    if (Integer.parseInt(balls[1]) > maxBlue) {
                        return 0;
                    }
                }
            }
        }
        return game;
    }

    public static int getPower(String line) {
        String newLine = line.replace("Game ", "");
        int game = Integer.parseInt(newLine.split(":")[0]);
        String gameString = game + ":";
        newLine = newLine.replace(gameString, "");
        int red = 0, green = 0, blue = 0;
        for (String round : newLine.split(";")) {
            for (String picks : round.split(",")) {
                String[] balls = picks.split(" ");
                int numberOfBalls = Integer.parseInt(balls[1]);

                if (balls[2].equals("red")) {
                    if (Integer.parseInt(balls[1]) > red) {
                        red = numberOfBalls;
                    }
                } else if (balls[2].equals("green")) {
                    if (Integer.parseInt(balls[1]) > green) {
                        green = numberOfBalls;
                    }
                } else if (balls[2].equals("blue")) {
                    if (Integer.parseInt(balls[1]) > blue) {
                        blue = numberOfBalls;
                    }
                }
            }
        }
        return red * blue * green;
    }


}
