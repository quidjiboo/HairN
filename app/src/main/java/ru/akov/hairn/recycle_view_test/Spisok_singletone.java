package ru.akov.hairn.recycle_view_test;

import android.support.v4.util.ArrayMap;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

import ru.akov.hairn.recycle_view_test.Rec_data_tipes.Rec_Shop_data;

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

    public void add_data(final ArrayList<GPScoords> mset, DatabaseReference mDatabase) {
        final HashMap<String, GPScoords> mMap = new HashMap<String, GPScoords>();
        for (GPScoords product : mset) {
            mMap.put(product.getkey(), product);
        }
        mDatabase.child("locations_names").child("Novovoronezh").child("barbershops_names").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {

                    if (mMap.containsKey(childDataSnapshot.getKey())) {
                        Rec_Shop_data post = childDataSnapshot.getValue(Rec_Shop_data.class);
                        mMap.get(childDataSnapshot.getKey()).setname(post.getname());
                        mMap.get(childDataSnapshot.getKey()).seturi(post.getpicurl());
                        Log.v(TAG, "Совпадение в двух базах можно добавлять данные" + childDataSnapshot.getKey());
                    }

                }
                for (GPScoords product : mset) {
                    Log.v(TAG, "НОВЫЕ ПОЛЯ В ХЭММАПЕ СПИСКА" + product.getmdist() + product.getname());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }


    synchronized public ArrayMap<String, LatLng> msort(ArrayMap<String, LatLng> massiv) {


        return massiv;
    }


}