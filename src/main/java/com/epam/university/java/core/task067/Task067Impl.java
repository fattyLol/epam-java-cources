package com.epam.university.java.core.task067;

import java.util.List;

public class Task067Impl implements Task067 {
    @Override
    public int knightMovements(int x1, int y1, int x2, int y2) {

        final Point start = new Point(x1, y1);
        final Point end = new Point(x2, y2);

        int way = findWay(start, end, 0);

        return way;
    }

    public int findWay(Point from, Point to, int stepsBefore) {
        stepsBefore++;
        final List<Point> possiblePoints = from.findPossiblePoints();
        for (Point possiblePoint : possiblePoints) {
            if (!possiblePoint.equals(to)) {
                if (stepsBefore > 7) {
                    return 9;
                }
                int newWay = findWay(possiblePoint, to, stepsBefore);
                if (newWay < 9){
                    stepsBefore = newWay;
                }
            } else {
                return stepsBefore;
            }
        }
        return stepsBefore;

    }


}
