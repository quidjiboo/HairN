package ru.akov.hairn;

import java.util.ArrayList;
import java.util.Date;


/**
 * Created by User on 21.04.2016.
 */
public interface MyCallback {

       void izmenit_calendar(ArrayList<Date> date,ArrayList<Date> buzydate);
       void vibral_datu(String date);
    /*   void show_clocks(ArrayList date);*/
}