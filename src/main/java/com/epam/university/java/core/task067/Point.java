package com.epam.university.java.core.task067;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public List<Point> findPossiblePoints() {
        List<Point> points = new ArrayList<>();

        int baseX = this.getX();
        int baseY = this.getY();

        int newX = baseX + 1;
        int newY = baseY + 2;
        Point newPoint = new Point(newX, newY);
        if (isOnTheField(newPoint)) {
            points.add(newPoint);
        }

        newX = baseX - 1;
        newY = baseY + 2;
        newPoint = new Point(newX, newY);
        if (isOnTheField(newPoint)) {
            points.add(newPoint);
        }

        newX = baseX + 2;
        newY = baseY - 1;
        newPoint = new Point(newX, newY);
        if (isOnTheField(newPoint)) {
            points.add(newPoint);
        }

        newX = baseX + 2;
        newY = baseY + 1;
        newPoint = new Point(newX, newY);
        if (isOnTheField(newPoint)) {
            points.add(newPoint);
        }

        newX = baseX + 1;
        newY = baseY - 2;
        newPoint = new Point(newX, newY);
        if (isOnTheField(newPoint)) {
            points.add(newPoint);
        }


        newX = baseX - 1;
        newY = baseY - 2;
        newPoint = new Point(newX, newY);
        if (isOnTheField(newPoint)) {
            points.add(newPoint);
        }


        newX = baseX - 2;
        newY = baseY - 1;
        newPoint = new Point(newX, newY);
        if (isOnTheField(newPoint)) {
            points.add(newPoint);
        }

        newX = baseX - 2;
        newY = baseY + 1;
        newPoint = new Point(newX, newY);
        if (isOnTheField(newPoint)) {
            points.add(newPoint);
        }

        return points;
    }

    private boolean isOnTheField(Point point) {
        if (point.getX() > 8 || point.getX() < 1) {
            return false;
        }
        if (point.getY() > 8 || point.getY() < 1) {
            return false;
        }
        return true;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Point point = (Point) o;
        return getX() == point.getX()
                && getY() == point.getY();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY());
    }

}
