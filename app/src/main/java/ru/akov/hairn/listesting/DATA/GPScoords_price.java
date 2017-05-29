package ru.akov.hairn.listesting.DATA;

/**
 * Created by User on 16.05.2017.
 */

public class GPScoords_price {
    private Double mdist;
    private String key;
    private String uri = "empty";
    private String name = "empty";
    private double latitude;
    private double longitude;
    private double price = 0.0;
    public GPScoords_price(){
        super();
    }
    public GPScoords_price(double mdist, String key, String uri, String name, double latitude, double longitude,double price) {
        this.mdist = mdist;
        this.key = key;
        this.uri = uri;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.price = price;
    }



    public double getmdist() {
        return mdist;
    }

    public String getkey() {
        return key;
    }

    public String getname() {
        return name;
    }
    public String geturi() {
        return uri;
    }

    public void setname(String name) {
        this.name = name;
    }

    public void seturi(String uri) {
        this.uri = uri;
    }
    public void setprice(Double price) {
        this.price = price;
    }
}
