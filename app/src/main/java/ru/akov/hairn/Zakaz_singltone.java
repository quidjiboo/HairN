package ru.akov.hairn;

import android.app.Activity;
import android.util.Log;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by User on 17.03.2017.
 */

public class Zakaz_singltone {
    private String mail="";
    private String name="";
    private String phone="";
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
}