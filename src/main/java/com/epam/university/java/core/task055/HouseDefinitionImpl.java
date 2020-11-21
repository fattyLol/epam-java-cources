package com.epam.university.java.core.task055;

public class HouseDefinitionImpl implements HouseDefinition {

    private String address;
    private int year;
    private double area;
    private boolean communal;


    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public int getYear() {
        return year;
    }

    @Override
    public double getArea() {
        return area;
    }

    @Override
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public void setArea(double area) {
        this.area = area;
    }

    public boolean isCommunal() {
        return communal;
    }

    public void setCommunal(boolean communal) {
        this.communal = communal;
    }
}
