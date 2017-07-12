package ru.akov.hairn.listesting.DATA;

/**
 * Created by User on 16.05.2017.
 */

public class GPScoords_price {
    private Double mdist;
    private String key;
    private String uri = "empty";
    private String name = "empty";
    private Double latitude;
    private Double longitude;
    private String price = "empty";
    public GPScoords_price(){
        super();
    }
    public GPScoords_price(double mdist, String key, String uri, String name, double latitude, double longitude,String price) {
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
    public double getlatitude() {
        return latitude;
    }
    public double getlongitude() {
        return longitude;
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
    public String getprice() {
        return price;
    }

    public void setname(String name) {
        this.name = name;
    }
    public void setmdist(Double mdist) {
        this.mdist = mdist;
    }
    public void seturi(String uri) {
        this.uri = uri;
    }
    public void setprice(String price) {
        this.price = price;
    }
}
