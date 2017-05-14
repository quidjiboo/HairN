package ru.akov.hairn.Data_tipes;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by Alexandr on 09.07.2016.
 */
@IgnoreExtraProperties
public class Shop_in_locat_url_names {


    public String name;
    public String picurl;


    public Shop_in_locat_url_names() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Shop_in_locat_url_names(String name, String picurl) {
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