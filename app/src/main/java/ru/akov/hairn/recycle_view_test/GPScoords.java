package ru.akov.hairn.recycle_view_test;

/**
 * Created by User on 16.05.2017.
 */

public class GPScoords {
    private Double mdist;
        private String key;
    private String uid="null";
    private String name="null";
     GPScoords(String key,Double mdist)
     {
this.key=key;
        this.mdist=mdist;
    }
    public Double getmdist(){
        return mdist;
    }
    public String getkey(){
        return key;
    }
    public String getname(){
        return name;
    }

    public void setname(String name){
        this.name = name;
    }
    public void setuid(String uid){
        this.uid = uid;
    }
}
