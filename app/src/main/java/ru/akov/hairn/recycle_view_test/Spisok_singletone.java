package ru.akov.hairn.recycle_view_test;

import android.support.v4.util.ArrayMap;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.SphericalUtil;

/**
 * Created by User on 17.03.2017.
 */

public class Spisok_singletone {
    private static final String TAG = "СПИСОК СИНГЛТОН";
  //  private ArrayMap<String, LatLng> marray_keys = new ArrayMap<>();
  //  private SortedSet<GPScoords> countrySet;

    private static Spisok_singletone instance;

    private Spisok_singletone() {

    }

    public static synchronized Spisok_singletone getInstance() {
        if (instance == null) {
            instance = new Spisok_singletone();   /// спорное решение !!!
        }
        return instance;
    }

    synchronized  public   void add_data(ArrayMap<String, LatLng> massiv, LatLng pos) {

  /*      Comparator test = new Comparator<GPScoords>() {
            public int compare(GPScoords o1, GPScoords o2) {
                float price1 = o1.getdist();
                float price2 = o2.getdist();
                if (price1 > price2) {
                    return 1;
                } else if (price1 < price2) {
                    return -1;
                } else {
                    return 0;
                }
            }};
        countrySet = new TreeSet<>(test);*/

        for (int i = 0; i < massiv.size(); i++) {
            String key = massiv.keyAt(i);
            Log.i(TAG, key);
            LatLng value = massiv.valueAt(i);
            Log.i(TAG, "КООРДИНАТЫ=" + value.toString());
            Log.i(TAG, "РАСТОЯНИЕ МЕЖДУ МНОИ И ТОЧКААМИ=" + SphericalUtil.computeDistanceBetween(pos,value));

        //   countrySet.add(SphericalUtil.computeDistanceBetween(pos,value));

        }

     //   Log.i(TAG, "Получивщийся трисет=" + countrySet);
 //       this.marray_keys=marray_keys;

    }



    synchronized  public  ArrayMap<String, LatLng> msort( ArrayMap<String, LatLng> massiv) {


        return massiv;
    }


}