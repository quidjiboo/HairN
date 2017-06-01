package ru.akov.hairn.Data_tipes;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by Alexandr on 09.07.2016.
 */
@IgnoreExtraProperties
public class Shop_data {

    public String admin;
    public String name;
    public String tipe_of_shop;
    public String photourl;
    public String location;
    public double latitude;
    public double longitude;
    public String phonenumber;
    public String dataurl;
    public String stars;
    public Shop_data() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Shop_data(String name, String tipe_of_shop, String photourl, String admin, String location, double latitude, double longitude,String dataurl,String stars,String phonenumber) {
      //  this.product = product;
        this.name = name;
        this.tipe_of_shop = tipe_of_shop;
        this.photourl = photourl;
        this.admin = admin;
        this.location = location;
        this.latitude = latitude;
        this.longitude = longitude;
        this.dataurl = dataurl;
        this.stars = stars;
        this.phonenumber = phonenumber;
    }
    public String getname() {
        return name;
    }
    public String gettipe_of_shop() {
        return tipe_of_shop;
    }
    public String getphotourl() {
        return photourl;
    }
    public String getadmin() {
        return admin;
    }
    public String getlocation() {
        return location;
    }
    public double getlatitude() {
        return latitude;
    }
    public double getlongitude() {
        return longitude;
    }
    public String getdataurl() {
        return dataurl;
    }
    public String getstars() {
        return stars;
    }
    public String getphonenumber() {
        return phonenumber;
    }


}