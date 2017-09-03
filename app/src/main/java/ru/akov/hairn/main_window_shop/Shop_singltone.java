package ru.akov.hairn.main_window_shop;

import android.util.Log;

/**
 * Created by User on 17.03.2017.
 */

public class Shop_singltone {
    private String curid_shop="";

    private static Shop_singltone instance;

    private Shop_singltone() {

    }

    public static synchronized Shop_singltone getInstance() {
        if (instance == null) {
            instance = new Shop_singltone();   /// спорное решение !!!
        }
        return instance;
    }

    synchronized  public   void add_data(String curid_shop) {
        this.curid_shop=curid_shop;

    }

    public   String getcurid_shop() {
        Log.v("AKOV",curid_shop + " ghgfhgf");
return  curid_shop;
    }
    public   void Show_data() {
        Log.v("AKOV",curid_shop + " ghgfhgf");

    }

}