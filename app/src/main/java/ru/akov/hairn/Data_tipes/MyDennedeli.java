package ru.akov.hairn.Data_tipes;

/**
 * Created by User on 23.01.2017.
 */

public class MyDennedeli {
    private String day;
    private int dayinweek;

    public MyDennedeli(String day, int dayinweek) {
        this.day = day;
        this.dayinweek = dayinweek;
    }

    public String getDay() {
        return day;
    }

    public int getDayinweek() {
        return dayinweek;
    }
}
