package ru.akov.hairn.recycle_view_test;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by User on 16.05.2017.
 */

public class GPScoords {
    private Double mdist;
    private LatLng mcoordinates;
    private String key;
    private String uid;
    GPScoords(String key,Double mdist){
this.key=key;
        this.mdist=mdist;
    }
    public Double getmdist(){
        return mdist;
    }
}
