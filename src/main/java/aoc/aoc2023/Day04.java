package aoc.aoc2023;

import aoc.Day;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day04 implements Day {
    @Override
    public String part1(List<String> input) {
        int total = 0;
        for (String line : input) {
            List<Integer> winningNumbers = getWinningNumbers(line);
            List<Integer> numbers = getNumbers(line);
            int matches = findMatches(winningNumbers, numbers);
            total += findPower(matches);
        }
        return String.valueOf(total);
    }

    @Override
    public String part2(List<String> cardsList) {
        int cards = 0;
        Map<Integer, Integer> cardCopyMap = new HashMap<>();
        for (int i = 1; i < cardsList.size() + 1; i++) {
            cardCopyMap.put(i, 1);
        }
        for (int cardNumber = 0; cardNumber < cardsList.size(); cardNumber++) {
            List<Integer> winningNumbers = getWinningNumbers(cardsList.get(cardNumber));
            List<Integer> numbers = getNumbers(cardsList.get(cardNumber));
            int matches = findMatches(winningNumbers, numbers);
            int currentNumberOfCards = cardCopyMap.get(cardNumber + 1);
            int nextCard = cardNumber + 2;
            int lastCard = cardNumber + matches + 2;
            if (matches != 0) {
                for (int i = nextCard; i < lastCard; i++) {
                    cardCopyMap.put(i, (cardCopyMap.get(i) + currentNumberOfCards));
                }
            }
        }

        return String.valueOf(cardCopyMap.values().stream().mapToInt(Integer::valueOf).sum());
    }


    public List<Integer> getWinningNumbers(String card) {
        int winningsIndex = card.indexOf(":");
        int numbersIndex = card.indexOf("|");
        String winningsString = card.substring(winningsIndex + 2, numbersIndex - 1);
        return numbersToList(winningsString);
    }

    public List<Integer> getNumbers(String card) {
        int numbersIndex = card.indexOf("|");
        String numbersString = card.substring(numbersIndex + 1);
        return numbersToList(numbersString);
    }

    private static List<Integer> numbersToList(String numbersString) {
        List<Integer> numbersList = new ArrayList<>();
        StringBuilder number = new StringBuilder();
        for (char c : numbersString.toCharArray()) {
            if (Character.isDigit(c)) {
                number.append(c);

            } else {
                if (!number.isEmpty()) {
                    numbersList.add(Integer.valueOf(number.toString()));
                    number.delete(0, number.length());
                }
            }
        }
        numbersList.add(Integer.valueOf(number.toString()));
        return numbersList;
    }

    public int findMatches(List<Integer> list1, List<Integer> list2) {
        list2.retainAll(list1);
        return list2.size();
    }

    public int findPower(int number) {
        if (number == 0) {
            return 0;
        }
        int power = 1;
        for (int i = 1; i < number; i++) {
            power *= 2;
        }
        return power;
    }

}
