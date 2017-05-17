package ru.akov.hairn.recycle_view_test.Rec_data_tipes;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by Alexandr on 09.07.2016.
 */
@IgnoreExtraProperties
public class Rec_Shop_data {


    public String name;

    public String picurl;


    public Rec_Shop_data() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Rec_Shop_data(String name,String picurl) {
      //  this.product = product;
        this.name = name;

        this.picurl = picurl;

    }
    public String getname() {
        return name;
    }

    public String getpicurl() {
        return picurl;
    }




}