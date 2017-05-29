package ru.akov.hairn.listesting;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;

import ru.akov.hairn.Data_tipes.Shop_in_locat_url_names_loc;
import ru.akov.hairn.My_app;
import ru.akov.hairn.listesting.DATA.Shop_info_data;

/**
 * Created by User on 17.03.2017.
 */

public class Spisok_singl {
    private static final String TAG = "Массив";
    private ValueEventListener location_sort_arraylistner;
    private ChildEventListener bloched_sort_arraylistner;

    private ArrayList<String> location_sort_arraylist_of_keys;


    private ArrayList<Shop_info_data> location_sort_arraylist;
    private ArrayList<Shop_info_data> location_sort_arraylist_COPY;


    private DatabaseReference mDatabase_in_singl;
    private DatabaseReference mDatabase_in_blocked;
    private My_app app;
    private MyCallbacl_refresherlist myCallback;
    private static Spisok_singl instance;

    private Spisok_singl() {

    }

    public void registerCallBack(MyCallbacl_refresherlist callback) {
        this.myCallback = callback;
    }

    public static synchronized Spisok_singl getInstance() {
        if (instance == null) {
            instance = new Spisok_singl();   /// спорное решение !!!
        }
        return instance;
    }

    synchronized void addlistner_location_sort_arraylist(final DatabaseReference mDatabase, final DatabaseReference isklmDatabase) {
        this.mDatabase_in_singl = mDatabase;
        this.mDatabase_in_blocked = isklmDatabase;
        mDatabase_in_singl.addValueEventListener(location_sort_arraylistner = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                get_sortet_hashset(mDatabase, isklmDatabase);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    synchronized void remove_location_sort_arraylist() {
        mDatabase_in_singl.removeEventListener(location_sort_arraylistner);
        mDatabase_in_blocked.removeEventListener(bloched_sort_arraylistner);
    }

    void get_sortet_hashset(final DatabaseReference mDatabase, final DatabaseReference isklmDatabase) {



        location_sort_arraylist = new ArrayList<Shop_info_data>();
        location_sort_arraylist_of_keys = new ArrayList<String>();
        mDatabase.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        System.out.println("Скачал");
                        for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {

                            Shop_in_locat_url_names_loc obj = childDataSnapshot.getValue(Shop_in_locat_url_names_loc.class);

                            location_sort_arraylist_of_keys.add(childDataSnapshot.getKey());

                            Shop_info_data obj_of_location_sort_array = new Shop_info_data( childDataSnapshot.getKey(), obj.getpicurl(), obj.getname(), obj.getlatitude(), obj.getlongitude());
                            location_sort_arraylist.add(obj_of_location_sort_array);
                            location_sort_arraylist_COPY.add(obj_of_location_sort_array);


                            System.out.println("ТУТ СКАЧАЛ И СДЕЛАЛ ОБЕКТ" + obj.getname());

                        }




                        myCallback.refresh();
                        add_rem_hashset(mDatabase_in_blocked);

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                }

        );


    }

    synchronized public ArrayList<Shop_info_data> getlist() {
        ArrayList nullarray = new ArrayList<Shop_info_data>();
        if (location_sort_arraylist != null) {
            return location_sort_arraylist;
        } else {

            return nullarray;
        }

    }

    void add_rem_hashset(DatabaseReference mDatabase) {


        mDatabase.addChildEventListener(bloched_sort_arraylistner = new ChildEventListener() {
                                            @Override
                                            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                                                if (location_sort_arraylist_of_keys.contains(dataSnapshot.getKey())) {


                                                    Iterator<Shop_info_data> iterator = location_sort_arraylist.iterator();
                                                    while (iterator.hasNext()) {

                                                        Shop_info_data c = (Shop_info_data) iterator.next();
                                                        if (c.getkey().contains(dataSnapshot.getKey()))
                                                            iterator.remove();


                                                    }



                                                    myCallback.refresh();
                                                }

                                            }

                                            @Override
                                            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                                            }

                                            @Override
                                            public void onChildRemoved(DataSnapshot dataSnapshot) {
                                                if (location_sort_arraylist_of_keys.contains(dataSnapshot.getKey())) {
                                                    Iterator<Shop_info_data> iterator = location_sort_arraylist_COPY.iterator();
                                                    while (iterator.hasNext()) {

                                                        Shop_info_data c = (Shop_info_data) iterator.next();
                                                        if (c.getkey().contains(dataSnapshot.getKey()))
                                                            location_sort_arraylist.add(c);


                                                    }


                                                    myCallback.refresh();


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