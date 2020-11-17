package com.epam.university.java.core.task053;

import com.epam.university.java.core.task008.Task008Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Task053Impl implements Task053 {



    private static final Map<Character, Integer> characters = Map.of(
            '(', 0,
            ')', 0,
            '+', 1,
            '-', 1,
            '*', 2,
            '/', 2,
            '^', 3

    );

    @Override
    public double calculate(String input) {
        if (!validate(input)) {
            throw new IllegalArgumentException();
        }
        List<Character> digits = new ArrayList<>();
        List<Character> operators = new ArrayList<>();


        for (Character c : input.toCharArray()) {
            if (Character.isDigit(c)) {
                digits.add(c);
            } else {
                digits.add(' ');
                operators.add(c);
            }
        }
        digits.add(' ');

        String rpnString = fromInfiniteToRpn(input);

        return 0;
    }


    private String fromInfiniteToRpn(String infiniteStr) {
        StringBuilder rpnString = new StringBuilder();
        List<Character> stack = new ArrayList<>();

        boolean numberIsOver;
        for (Character c : infiniteStr.toCharArray()) {
            if (Character.isDigit(c)) {
                rpnString.append(c);
                numberIsOver = false;
            } else {
                numberIsOver = true;
                if (characters.containsKey(c)
                        && orderIsOkay(stack)) {
                    stack.add(c);
                } else if (characters.containsKey(c)) {
                    for (Character character : stack) {
                        if (character != '('
                                && character != ')') {
                            rpnString.append(character);
                        }
                    }
                    stack = new ArrayList<>();
                }
            }
            if (numberIsOver) {
                rpnString.append(' ');
            }
        }
        rpnString.append(' ');
        for (Character character : stack) {
            if (character != '('
                    && character != ')') {
                rpnString.append(character);
            }
        }


        return rpnString.toString();
    }

    private boolean orderIsOkay(List<Character> stack) {

        int prev = 0;
        for (Character character : stack) {
            int num = characters.get(character);
            if (num <= prev) {
                return false;
            }
            prev = num;
        }

        return true;
    }

    private boolean validate(String input) {
        if (input == null || input.isBlank() || input.isEmpty()) {
            return false;
        }
        Task008Impl bracketsChecker = new Task008Impl();
        if (!bracketsChecker.isValid(input)) {
            return false;
        }
        int amountOfDigits = 0;
        int amountOfOperators = 0;
        for (Character c : input.toCharArray()) {
            if (!Character.isDigit(c) && !characters.containsKey(c)) {
                return false;
            } else if (Character.isDigit(c)) {
                amountOfDigits++;
            } else if (c != '(' && c != ')') {
                amountOfOperators++;
            }
        }
        if (amountOfDigits <= amountOfOperators) {
            return false;
        }
        return true;
    }
}
