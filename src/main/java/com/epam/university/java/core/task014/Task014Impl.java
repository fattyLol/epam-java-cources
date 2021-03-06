package com.epam.university.java.core.task014;

import java.util.Collection;
import java.util.ArrayList;

public class Task014Impl implements Task014 {
    @Override
    public Collection<VampireNumber> getVampireNumbers() {

        Collection<VampireNumber> list = new ArrayList<>();
        ArrayList<Integer> added = new ArrayList<>();
        int min = 10;
        int max = 100;

        int first = min;
        int second;
        int multiplication;

        while (first < max) {
            second = min;
            while (second < max) {
                multiplication = first * second;
                String str = String.valueOf(multiplication);
                String sFirst = String.valueOf(first);
                String sSecond = String.valueOf(second);
                if (str.length() > 3) {
                    if (isMultiplication(str, sFirst, sSecond) && !added.contains(multiplication)) {
                        list.add(new VampireNumberImpl(multiplication, first, second));
                        added.add(multiplication);
                    }
                }
                second++;
            }
            first++;
        }

        return list;
    }

    private boolean isMultiplication(String sMul, String num1, String num2) {

        char[] chars1 = num1.toCharArray();
        char[] chars2 = num2.toCharArray();
        char[] common = sMul.toCharArray();

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                if (common[j] == chars1[i]) {
                    common[j] = ' ';
                    break;
                }
            }
        }

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                if (common[j] == chars2[i]) {
                    common[j] = ' ';
                    break;
                }
            }
        }
        return (new String(common).trim().length()) == 0;
    }
}
