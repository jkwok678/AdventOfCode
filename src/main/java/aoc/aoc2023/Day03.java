package aoc.aoc2023;

import aoc.Day;

import java.util.List;

public class Day03 implements Day {


    @Override
    public String part1(List<String> input) {
        int lineLength = input.get(0).length();
        int total = getPartNumbers(input, lineLength);
        return String.valueOf(total);
    }

    @Override
    public String part2(List<String> input) {
        int lineLength = input.get(0).length();
        int total = getGearRatio(input, lineLength);

        return String.valueOf(total);
    }

    private int getPartNumbers(List<String> lines, int lineLength) {
        int total = 0;
        for (int row = 0; row < lines.size(); row++) {
            String line = lines.get(row);
            int rowTotal = 0;
            for (int column = 0; column < line.length(); column++) {
                if (Character.isDigit(line.charAt(column))) {
                    StringBuilder num = new StringBuilder();
                    int i;
                    for (i = column; i < lineLength; i++) {
                        num.append(line.charAt(i));
                        if ((i + 1 < lineLength) && !Character.isDigit(line.charAt(i + 1))) {
                            break;
                        }
                    }
                    String wholeNumber = num.toString();
                    if (checkHasSymbolsAround(lines, row, column, i)) {
                        rowTotal += Integer.parseInt(wholeNumber);
                    }
                    column = i + 1;
                }
            }
            total += rowTotal;
        }
        return total;
    }

    private boolean checkHasSymbolsAround(List<String> lines, int lineNumber, int x1, int x2) {
        boolean hasSymbol = false;
        if (lineNumber == 0) {
            String line1 = lines.get(0);
            String line2 = lines.get(1);
            hasSymbol = checkSidesForSymbols(x1, x2, line1);
            if (!hasSymbol) {
                hasSymbol = checkLineForSymbols(x1, x2, line2);
            }
            return hasSymbol;
        } else if (lineNumber == lines.size() - 1) {
            String line1 = lines.get(0);
            String line2 = lines.get(lines.size() - 2);
            hasSymbol = checkSidesForSymbols(x1, x2, line1);
            if (!hasSymbol) {
                hasSymbol = checkLineForSymbols(x1, x2, line2);
            }
            return hasSymbol;
        } else {
            String line1 = lines.get(lineNumber);
            String line2 = lines.get(lineNumber - 1);
            String line3 = lines.get(lineNumber + 1);
            hasSymbol = checkSidesForSymbols(x1, x2, line1);
            if (!hasSymbol) {
                hasSymbol = checkLineForSymbols(x1, x2, line2);
            }
            if (!hasSymbol) {
                hasSymbol = checkLineForSymbols(x1, x2, line3);
            }
            return hasSymbol;
        }
    }

    private boolean checkSidesForSymbols(int x1, int x2, String line) {
        boolean hasSymbol = false;
        if (x1 > 0) {
            hasSymbol = isSymbol(line.charAt(x1 - 1));
        }
        if (x2 < line.length() - 2 && !hasSymbol) {
            hasSymbol = isSymbol(line.charAt(x2 + 1));
        }
        return hasSymbol;
    }

    private boolean checkLineForSymbols(int x1, int x2, String line) {
        if (x1 > 0) {
            x1 = x1 - 1;
        }
        if (x2 < line.length() - 2) {
            x2 = x2 + 2;
        }
        return line.substring(x1, x2).chars().mapToObj(c -> (char) c).anyMatch(Day03::isSymbol);
    }

    private static boolean isSymbol(char c) {
        return !Character.isDigit(c) && c != '.';
    }

    public int getGearRatio(List<String> lines, int lineLength) {
        int total = 0;
        for (int row = 0; row < lines.size(); row++) {
            String line = lines.get(row);
            for (int column = 0; column < line.length(); column++) {

                if (isStar(line.charAt(column))) {
                    int gearRatio = countNumbersAround(lines, row, column);
                    total += gearRatio;
                    column = column + 1;
                }
            }

        }
        return total;

    }

    public int countNumbersAround(List<String> lines, int row, int column) {
        int gearRatio = 1;
        int count = 0;
        int aboveRow = row - 1;
        int belowRow = row + 1;
        if (aboveRow == -1) {
            if (Character.isDigit(lines.get(row + 1).charAt(column))) {
                count++;
                gearRatio *= Integer.parseInt(getMiddleNumber(lines.get(row + 1), column, lines.get(row + 1).charAt(column) + ""));
            } else {
                if (Character.isDigit(lines.get(row + 1).charAt(column - 1))) {
                    count++;
                    gearRatio *= Integer.parseInt(getLeftNumber(lines.get(row + 1), column - 1, lines.get(row + 1).charAt(column - 1) + ""));
                }
                if (Character.isDigit(lines.get(row + 1).charAt(column + 1))) {
                    count++;
                    gearRatio *= Integer.parseInt(getRightNumber(lines.get(row + 1), column + 1, lines.get(row + 1).charAt(column + 1) + ""));
                }
            }
        } else if (belowRow == lines.size()) {
            if (Character.isDigit(lines.get(row - 1).charAt(column))) {
                count++;
                gearRatio *= Integer.parseInt(getMiddleNumber(lines.get(row - 1), column, lines.get(row - 1).charAt(column) + ""));
            } else {
                if (Character.isDigit(lines.get(row - 1).charAt(column - 1))) {
                    count++;
                    gearRatio *= Integer.parseInt(getLeftNumber(lines.get(row - 1), column - 1, lines.get(row - 1).charAt(column - 1) + ""));
                }
                if (Character.isDigit(lines.get(row - 1).charAt(column + 1))) {
                    count++;
                    gearRatio *= Integer.parseInt(getRightNumber(lines.get(row - 1), column + 1, lines.get(row - 1).charAt(column + 1) + ""));
                }
            }
        } else {
            if (Character.isDigit(lines.get(row - 1).charAt(column))) {
                count++;
                gearRatio *= Integer.parseInt(getMiddleNumber(lines.get(row - 1), column, lines.get(row - 1).charAt(column) + ""));
            } else {
                if (Character.isDigit(lines.get(row - 1).charAt(column - 1))) {
                    count++;
                    gearRatio *= Integer.parseInt(getLeftNumber(lines.get(row - 1), column - 1, lines.get(row - 1).charAt(column - 1) + ""));
                }
                if (Character.isDigit(lines.get(row - 1).charAt(column + 1))) {
                    count++;
                    gearRatio *= Integer.parseInt(getRightNumber(lines.get(row - 1), column + 1, lines.get(row - 1).charAt(column + 1) + ""));
                }
            }
            if (Character.isDigit(lines.get(row + 1).charAt(column))) {
                count++;
                gearRatio *= Integer.parseInt(getMiddleNumber(lines.get(row + 1), column, lines.get(row + 1).charAt(column) + ""));
            } else {
                if (Character.isDigit(lines.get(row + 1).charAt(column - 1))) {
                    count++;
                    gearRatio *= Integer.parseInt(getLeftNumber(lines.get(row + 1), column - 1, lines.get(row + 1).charAt(column - 1) + ""));
                }
                if (Character.isDigit(lines.get(row + 1).charAt(column + 1))) {
                    count++;
                    gearRatio *= Integer.parseInt(getRightNumber(lines.get(row + 1), column + 1, lines.get(row + 1).charAt(column + 1) + ""));
                }
            }
        }

        if (Character.isDigit(lines.get(row).charAt(column - 1))) {
            count++;
            gearRatio *= Integer.parseInt(getLeftNumber(lines.get(row), column - 1, lines.get(row).charAt(column - 1) + ""));
        }
        if (Character.isDigit(lines.get(row).charAt(column + 1))) {
            count++;
            gearRatio *= Integer.parseInt(getRightNumber(lines.get(row), column + 1, lines.get(row).charAt(column + 1) + ""));
        }

        if (count != 2) {
            return 0;
        }
        return gearRatio;
    }

    public String getMiddleNumber(String line, int column, String number) {
        if (!Character.isDigit(line.charAt(column - 1)) && !Character.isDigit(line.charAt(column + 1))) {
            return number;
        }
        if (Character.isDigit(line.charAt(column - 1)) && Character.isDigit(line.charAt(column + 1))) {

            return getLeftNumber(line, column - 1, line.charAt(column - 1) + "") + number +
                    getRightNumber(line, column + 1, line.charAt(column + 1) + "");
        }
        if (Character.isDigit(line.charAt(column - 1))) {
            return getLeftNumber(line, column - 1, line.charAt(column - 1) + number);
        }
        if (Character.isDigit(line.charAt(column + 1))) {
            return getRightNumber(line, column + 1, number + line.charAt(column + 1));
        }
        return number;
    }


    public String getLeftNumber(String line, int column, String number) {
        if (column == 0 || !Character.isDigit(line.charAt(column - 1))) {
            return number;
        }
        return getLeftNumber(line, column - 1, line.charAt(column - 1) + number);
    }


    public String getRightNumber(String line, int column, String number) {
        if (column == line.length() - 1 || !Character.isDigit(line.charAt(column + 1))) {
            return number;
        }

        return getRightNumber(line, column + 1, number + line.charAt(column + 1));

    }

    private boolean isStar(char c) {
        return c == '*';
    }


}
