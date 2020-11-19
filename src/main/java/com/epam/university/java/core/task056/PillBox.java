package com.epam.university.java.core.task056;


public class PillBox implements Comparable<PillBox> {
    private int number;
    private int amountOfPills;
    private int dayFrom;
    private int dayTill;

    public PillBox() {
    }

    public PillBox(int number, int amountOfPills, int dayFrom) {
        this.number = number;
        this.amountOfPills = amountOfPills;
        this.dayFrom = dayFrom;
        this.dayTill = dayFrom + amountOfPills - 1;
    }

    public int getDayFrom() {
        return dayFrom;
    }

    public void setDayFrom(int dayFrom) {
        this.dayFrom = dayFrom;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getAmountOfPills() {
        return amountOfPills;
    }

    public void setAmountOfPills(int amountOfPills) {
        this.amountOfPills = amountOfPills;
    }

    public int getDayTill() {
        return dayTill;
    }

    public void setDayTill(int dayTill) {
        this.dayTill = dayTill;
    }

    @Override
    public int compareTo(PillBox o) {
        return this.dayFrom - o.dayFrom;
    }
}