package ru.akov.hairn.main_window.data_pick_fragment;

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
import ru.akov.hairn.main_window.Sing_tone_choosings;

/**
 * Created by User on 17.03.2017.
 */

public class Single_tone_array_creator_fragment {
    private static final String TAG = "Массив";
    private Double summa = 0.0;
    private ChildEventListener location_sort_arraylistner_Child;
    private ChildEventListener bloched_sort_arraylistner;


    private ArrayList<String> blocked_tim_arraylist_of_keys;


    private DatabaseReference mDatabase_in_singl;
    private DatabaseReference mDatabase_in_blocked;
    private My_app app;
    private MyCallbacl_refresherlist_for_fragment myCallback;
    private static Single_tone_array_creator_fragment instance;
    private LatLng mylocation;

    private Single_tone_array_creator_fragment() {

    }


    public void registerCallBack(MyCallbacl_refresherlist_for_fragment callback) {
        this.myCallback = callback;
    }

    public static synchronized Single_tone_array_creator_fragment getInstance() {
        if (instance == null) {
            instance = new Single_tone_array_creator_fragment();   /// спорное решение !!!
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

                //    Shop_in_locat_url_names_loc shop = dataSnapshot.getValue(Shop_in_locat_url_names_loc.class);
                //    Log.d(TAG, "DEDEDED= " + dataSnapshot.getKey());
                add_withoutprice(dataSnapshot.getKey());
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


    synchronized void add_withoutprice(final String key) {

/*for (int i = 0; i < Sing_tone_choosings.getInstance().getServices().size(); i++){
    String obj = Sing_tone_choosings.getInstance().getServices().get(i);

        mDatabase_in_singl.getRoot().child("services_names").child(obj).child(Sing_tone_choosings.getInstance().getLocation()).child(key).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Double oj = dataSnapshot.getValue(Double.class);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

}*/
        mDatabase_in_singl.getRoot().child(Sing_tone_choosings.getInstance().getTypes_of_shops()).child(key).child("services_price").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Double summ = 0.0;
                Integer summ_ok = 0;
                for (DataSnapshot messageSnapshot : dataSnapshot.getChildren()) {
                    Double oj = messageSnapshot.getValue(Double.class);

                    if (Sing_tone_choosings.getInstance().getServices().contains(messageSnapshot.getKey().toString())) {

                        summ = summ + oj;
                        summ_ok = summ_ok + 1;
                    }

                }

                Log.d("Summa", "===" + summ);


                String strsumm = Double.toString(summ);
                if (summ_ok != Sing_tone_choosings.getInstance().getServices().size()) {
                    strsumm = "Нет всех услуг";
                }

                final String finalSumm = strsumm;

                mDatabase_in_singl.child(key).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        Shop_in_locat_url_names_loc obj = dataSnapshot.getValue(Shop_in_locat_url_names_loc.class);

                        GPScoords_price obj_of_location_sort_array = new GPScoords_price(0.0, dataSnapshot.getKey(), obj.getpicurl(), obj.getname(), obj.getlatitude(), obj.getlongitude(), finalSumm);

                        if (!blocked_tim_arraylist_of_keys.contains(key))
                            myCallback.addtolist(obj_of_location_sort_array);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
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
                        if (!blocked_tim_arraylist_of_keys.contains(dataSnapshot.getKey())) {
                            blocked_tim_arraylist_of_keys.add(dataSnapshot.getKey());
                            remove_by_key(dataSnapshot.getKey());
                        }
                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {
                        if (blocked_tim_arraylist_of_keys.contains(dataSnapshot.getKey())) {
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