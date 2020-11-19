package com.epam.university.java.core.task056;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Task056Impl implements Task056 {

    private List<PillBox> pillBoxes;

    @Override
    public Collection<Integer> necessaryMedication(String prescriptionFile) {

        if (prescriptionFile == null) {
            throw new IllegalArgumentException();
        }
        List<String> prescriptions;
        try {
            File file = new File(prescriptionFile);
            Path path = Paths.get(file.toURI());
            prescriptions = Files.readAllLines(path);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        pillBoxes = new ArrayList<>();
        int numberOfBox = 0;
        for (String prescription : prescriptions) {
            String[] lines = prescription.split(" ");
            int amountOfPills = Integer.parseInt(lines[0]);
            int fromDay = Integer.parseInt(lines[1].split("-")[0]);
            pillBoxes.add(new PillBox(numberOfBox, amountOfPills, fromDay));
            numberOfBox++;
        }

        Collections.sort(pillBoxes);


        int amountOfWays = (int) Math.pow(2, pillBoxes.size());

        List<PillBox> bestChoice = new ArrayList<>();
        List<PillBox> tmpSet;
        for (int i = 0; i < amountOfWays; i++) {
            tmpSet = new ArrayList<>();
            StringBuilder binaryString = new StringBuilder(Integer.toBinaryString(i));
            while (binaryString.length() < pillBoxes.size()) {
                binaryString.insert(0, "0");
            }

            for (int j = 0; j < binaryString.length(); j++) {
                if (binaryString.charAt(j) == '1') {
                    PillBox pillBox = pillBoxes.get(j);
                    tmpSet.add(pillBox);
                }
            }

            if (setIsValid(tmpSet)) {
                int pillsFromBest = 0;
                for (PillBox pillBox : bestChoice) {
                    pillsFromBest += pillBox.getAmountOfPills();
                }

                int pillsFromTmp = 0;
                for (PillBox pillBox : tmpSet) {
                    pillsFromTmp += pillBox.getAmountOfPills();
                }
                if (pillsFromTmp > pillsFromBest) {
                    bestChoice = tmpSet;
                }
            }
        }

        List<Integer> numbersOfPacks = new ArrayList<>();

        for (PillBox pillBox : bestChoice) {
            numbersOfPacks.add(pillBox.getNumber());
        }
        Collections.sort(numbersOfPacks);
        return numbersOfPacks;
    }

    @Override
    public Collection<String> intervalBetweenMedication(Collection<Integer> necessaryMedication) {
        if (necessaryMedication == null) {
            throw new IllegalArgumentException();
        }
        List<PillBox> bestChoice = new ArrayList<>();

        for (Integer integer : necessaryMedication) {
            for (PillBox pillBox : pillBoxes) {
                if (pillBox.getNumber() == integer) {
                    bestChoice.add(pillBox);
                }
            }
        }
        Collections.sort(bestChoice);

        List<String> alcoTrips = new ArrayList<>();
        for (int i = 1; i < bestChoice.size(); i++) {
            PillBox prev = bestChoice.get(i - 1);
            PillBox current = bestChoice.get(i);

            int firstDayOfAlcohol = prev.getDayTill() + 1;
            int lastDayOfAlcohol = current.getDayFrom() - 1;

            if (lastDayOfAlcohol >= firstDayOfAlcohol) {
                String periodOfAlcohol = firstDayOfAlcohol + "-" + lastDayOfAlcohol;
                alcoTrips.add(periodOfAlcohol);
            }
        }


        return alcoTrips;
    }

    private boolean setIsValid(List<PillBox> tmpSet) {
        int lastDayOfPrev = 0;
        if (tmpSet.size() < 2) {
            return true;
        }
        for (PillBox pillBox : tmpSet) {
            int newFrom = pillBox.getDayFrom();
            if (newFrom <= lastDayOfPrev) {
                return false;
            }
            lastDayOfPrev = pillBox.getDayTill();
        }
        return true;
    }
}
