package com.epam.university.java.core.task067;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Task067Impl implements Task067 {

    Map<Point, Integer> board = new HashMap<>();

    @Override
    public int knightMovements(int x1, int y1, int x2, int y2) {

        final Point start = new Point(x1, y1);
        final Point end = new Point(x2, y2);

        for (int x = 1; x < 9; x++) {
            for (int y = 1; y < 9; y++) {
                board.put(new Point(x, y), null);
            }
        }
        board.put(start, 0);
        fillTheBoard();
        while (board.get(end) == null) {
            fillTheBoard();
        }
        return board.get(end);
    }

    int currentStep = 0;

    private void fillTheBoard() {
        for (Point point : board.keySet()) {
            if (board.get(point) != null
                    && board.get(point) == currentStep) {
                final List<Point> points = point.findPossiblePoints();

                for (Point point1 : points) {
                    if (board.get(point1) == null
                            || board.get(point1) > currentStep) {
                        board.put(point1, currentStep + 1);
                    }
                }
            }
        }
        currentStep++;
    }
}