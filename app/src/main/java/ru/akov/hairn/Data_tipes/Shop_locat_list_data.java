package ru.akov.hairn.Data_tipes;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by Alexandr on 09.07.2016.
 */
@IgnoreExtraProperties
public class Shop_locat_list_data {


    public String latitude;
    public String longitude;


    public Shop_locat_list_data() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Shop_locat_list_data(String latitude, String longitude) {
      //  this.product = product;
        this.latitude = latitude;

        this.longitude = longitude;

    }
    public String getlatitude() {
        return latitude;
    }

    public String getlongitude() {
        return longitude;
    }





}