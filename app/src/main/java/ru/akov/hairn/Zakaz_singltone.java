package ru.akov.hairn;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by User on 17.03.2017.
 */

public class Zakaz_singltone {
    private String mail="";
    private String name="";
    private String phone="";
    private String date="";
    private String clock="";
    private ArrayList<String> uslugi = new ArrayList();
    private static Zakaz_singltone instance;

    private Zakaz_singltone() {

    }

    public static synchronized Zakaz_singltone getInstance() {
        if (instance == null) {
            instance = new Zakaz_singltone();   /// спорное решение !!!
        }
        return instance;
    }

    synchronized  public   void add_data(String mail, String name, String phone, ArrayList<String> uslugi) {
        this.mail=mail;
        this.name=name;
        this.phone=phone;
        this.uslugi=uslugi;
    }
    synchronized  public   void add_data_of_date(String date) {
        this.date="";
        this.date=date;

    }
    synchronized  public   void add_clock(String clock) {
        Log.v("AKOV", " Время  в синглтоне" + clock);
        this.clock=clock;

    }
    public   void Show_data() {
        Log.v("AKOV",mail + " " + name + " " + phone + " " + uslugi + " " +  date + " " + clock);

    }

}