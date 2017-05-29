package ru.akov.hairn.listesting.DATA;

/**
 * Created by User on 16.05.2017.
 */

public class Shop_info_data {
    private String key;
    private String uri = "empty";
    private String name = "empty";
    private double latitude;
    private double longitude;
    public Shop_info_data(){
        super();
    }
    public Shop_info_data( String key, String uri, String name, double latitude, double longitude) {

        this.key = key;
        this.uri = uri;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Shop_info_data(Double mdist, String key) {

        this.key = key;
        this.uri = uri;

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
    public double getlatitude() {
        return latitude;
    }
    public void setname(String name) {
        this.name = name;
    }

    public void seturi(String uri) {
        this.uri = uri;
    }
}
