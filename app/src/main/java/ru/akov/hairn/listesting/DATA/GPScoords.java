package ru.akov.hairn.listesting.DATA;

/**
 * Created by User on 16.05.2017.
 */

public class GPScoords {
    private Double mdist;
    private String key;
    private String uri = "empty";
    private String name = "empty";
    private double latitude;
    private double longitude;
    public GPScoords(){
        super();
    }
    public GPScoords(double mdist, String key, String uri, String name, double latitude, double longitude) {
        this.mdist = mdist;
        this.key = key;
        this.uri = uri;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public GPScoords(Double mdist, String key) {
        this.mdist = mdist;
        this.key = key;
        this.uri = uri;

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
}
