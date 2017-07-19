package ru.akov.hairn.main_window;

import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by User on 11.07.2017.
 */

public class Sing_tone_choosings {



    //должны выставляться автоматически
    LatLng mymloc   = new LatLng(31.7853339, -112.4026973);
    String location = "Novovoronezh";



    String shopid =  "";


    String services_names = "services_names";
    String locations_names_names = "locations_names";



    String types_of_shops = "";



    ArrayList<String> services = null;

    String date = "20170602";



    String time = "08_00";
    private static Sing_tone_choosings instance;
    private Sing_tone_choosings() {
        services = new ArrayList<>();
        Date date = (Date) Calendar.getInstance().getTime();
        final String month = (String) android.text.format.DateFormat.format("MM", date); //06
        final String year = (String) android.text.format.DateFormat.format("yyyy", date); //2013
        final String day = (String) android.text.format.DateFormat.format("dd", date);
        final String hour = (String) android.text.format.DateFormat.format("kk", date);

        int result = Integer.parseInt(hour) + 1;
        final String zapis = year + month + day;

        setDate(zapis);
        setTime("24" + "_" + 00);

    }




    public static synchronized Sing_tone_choosings getInstance() {
        if (instance == null) {
            instance = new Sing_tone_choosings();   /// спорное решение !!!
        }
        return instance;
    }

    synchronized public void add_types_of_shops(String type) {
        switch(type) {
            case "barbershops" :

                types_of_shops = type;
                break; // optional

            case "car_workshops" :

                types_of_shops = type;
                // Statements
                break; // optional

            // You can have any number of case statements.
            default : // Optional
                // Statements
        }
    }

    synchronized   public String getTypes_of_shops() {
        return types_of_shops;
    }
    synchronized   public void setServices(ArrayList<String> inarray) {
        Log.d("ВЫБРАННЫЕ ЭЛЕМЕНТЫ!Ё!!",   inarray.toString());
        services.clear();
        services.addAll(inarray);
        Log.d("ВЫБРАННЫЕ ЭЛЕМЕНТЫ!Ё!!",   services.toString());
    }
    public ArrayList<String> getServices() {
        return services;
    }
    public String getLocation() {
        return location;
    }
    public LatLng getMymloc() {
        return mymloc;
    }
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
    public String getShopid() {
        return shopid;
    }

    public void setShopid(String shopid) {
        this.shopid = shopid;
    }
}
