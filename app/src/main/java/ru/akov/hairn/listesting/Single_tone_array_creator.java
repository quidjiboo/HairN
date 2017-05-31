package ru.akov.hairn.listesting;

import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import ru.akov.hairn.Data_tipes.Shop_in_locat_url_names_loc;
import ru.akov.hairn.My_app;
import ru.akov.hairn.listesting.DATA.GPScoords_price;

/**
 * Created by User on 17.03.2017.
 */

public class Single_tone_array_creator {
    private static final String TAG = "Массив";

    private ChildEventListener location_sort_arraylistner_Child;
    private ChildEventListener bloched_sort_arraylistner;


    private ArrayList<String> blocked_tim_arraylist_of_keys;


    private DatabaseReference mDatabase_in_singl;
    private DatabaseReference mDatabase_in_blocked;
    private My_app app;
    private MyCallbacl_refresherlist myCallback;
    private static Single_tone_array_creator instance;
    private LatLng mylocation;

    private Single_tone_array_creator() {

    }


    public void registerCallBack(MyCallbacl_refresherlist callback) {
        this.myCallback = callback;
    }

    public static synchronized Single_tone_array_creator getInstance() {
        if (instance == null) {
            instance = new Single_tone_array_creator();   /// спорное решение !!!
        }
        return instance;
    }

    synchronized void addlistner_location_sort_arraylist(final DatabaseReference mDatabase, final LatLng mylocation, final DatabaseReference isklmDatabase) {
        this.mylocation = mylocation;
        this.mDatabase_in_singl = mDatabase;
        this.mDatabase_in_blocked = isklmDatabase;


        blocked_tim_arraylist_of_keys = new ArrayList<>();
        mDatabase_in_blocked.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {

                    blocked_tim_arraylist_of_keys.add(dataSnapshot.getKey());


                }
                if (bloched_sort_arraylistner != null)
                    mDatabase_in_blocked.removeEventListener(bloched_sort_arraylistner);
                add_rem_hashset(mDatabase_in_blocked);
                childevent(mDatabase_in_singl);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }

    synchronized void remove_location_sort_arraylist() {
       mDatabase_in_singl.removeEventListener(location_sort_arraylistner_Child);
        mDatabase_in_blocked.removeEventListener(bloched_sort_arraylistner);
    }


    void childevent(final DatabaseReference mDatabase) {

        mDatabase.addChildEventListener(location_sort_arraylistner_Child = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                Double price = dataSnapshot.getValue(Double.class);
                Log.d(TAG, "DEDEDED= " + dataSnapshot.getKey());
                add(dataSnapshot.getKey(), price);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            /*    Double obj = dataSnapshot.getValue(Double.class);
                add(dataSnapshot.getKey(),obj);*/
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                remove_by_key(dataSnapshot.getKey());
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    synchronized void add(final String key, final Double price) {

        mDatabase_in_singl.getRoot().child("locations_names").child("Novovoronezh").child("barbershops_names").child(key).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Shop_in_locat_url_names_loc obj = dataSnapshot.getValue(Shop_in_locat_url_names_loc.class);
                GPScoords_price obj_of_location_sort_array = new GPScoords_price(0.0, dataSnapshot.getKey(), obj.getpicurl(), obj.getname(), obj.getlatitude(), obj.getlongitude(), price);

                if (!blocked_tim_arraylist_of_keys.contains(key))
                    myCallback.addtolist(obj_of_location_sort_array);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
    synchronized void add_withoutprice(final String key) {

        mDatabase_in_singl.child(key).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    final Double price = dataSnapshot.getValue(Double.class);
                    mDatabase_in_singl.getRoot().child("locations_names").child("Novovoronezh").child("barbershops_names").child(key).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            Shop_in_locat_url_names_loc obj = dataSnapshot.getValue(Shop_in_locat_url_names_loc.class);
                            GPScoords_price obj_of_location_sort_array = new GPScoords_price(0.0, dataSnapshot.getKey(), obj.getpicurl(), obj.getname(), obj.getlatitude(), obj.getlongitude(), price);

                            if (!blocked_tim_arraylist_of_keys.contains(key))
                                myCallback.addtolist(obj_of_location_sort_array);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    synchronized void remove_by_key(String obj) {

           myCallback.removefromlist(obj);
    }


    void add_rem_hashset(DatabaseReference mDatabase) {


        mDatabase.addChildEventListener(bloched_sort_arraylistner = new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
if(!blocked_tim_arraylist_of_keys.contains(dataSnapshot.getKey())){
                        blocked_tim_arraylist_of_keys.add(dataSnapshot.getKey());
                        remove_by_key(dataSnapshot.getKey());}
                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {
                        if(blocked_tim_arraylist_of_keys.contains(dataSnapshot.getKey())) {
                            blocked_tim_arraylist_of_keys.remove(dataSnapshot.getKey());
                            add_withoutprice(dataSnapshot.getKey());
                        }
                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                }
        );


    }


}