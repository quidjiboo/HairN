package ru.akov.hairn.Data_tipes;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by Alexandr on 09.07.2016.
 */
@IgnoreExtraProperties
public class Shop_in_locat_url_names_loc {


    public String name;
    public String picurl;
    public Double latitude;
    public Double longitude;

    public Shop_in_locat_url_names_loc() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Shop_in_locat_url_names_loc(String name, String picurl, Double latitude, Double longitude) {
        //  this.product = product;
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
        this.picurl = picurl;

    }
    public Double getlatitude() {
        return latitude;
    }

    public Double getlongitude() {
        return longitude;
    }
    public String getname() {
        return name;
    }

    public String getpicurl() {
        return picurl;
    }


}