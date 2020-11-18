package com.epam.university.java.core.task067;

import java.util.ArrayList;
import java.util.List;

public class Task067Impl implements Task067 {

    private int steps;

    @Override
    public int knightMovements(int x1, int y1, int x2, int y2) {
        steps = 100;
        final Point start = new Point(x1, y1);
        final Point end = new Point(x2, y2);

        findWay(start, end, 1);

        return steps;
    }

    private List<Point> checkedPoints = new ArrayList<>();

    public void findWay(Point from, Point to, int stepsBefore) {

        checkedPoints.add(from);
        final List<Point> possiblePoints = from.findPossiblePoints();

        for (Point possiblePoint : possiblePoints) {
            if (possiblePoint.equals(to)) {
                if (stepsBefore < steps) {
                    steps = stepsBefore;
                }
                return;
            }
        }
        for (Point possiblePoint : possiblePoints) {
            if (!checkedPoints.contains(possiblePoint)) {
                findWay(possiblePoint, to, ++stepsBefore);
            }
        }
    }
}