package ru.akov.hairn.listesting;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.maps.android.SphericalUtil;

import java.util.ArrayList;

import ru.akov.hairn.Data_tipes.Shop_in_locat_url_names_loc;
import ru.akov.hairn.My_app;
import ru.akov.hairn.listesting.DATA.GPScoords_price;
import ru.akov.hairn.listesting.DATA.Shop_info_data;

/**
 * Created by User on 17.03.2017.
 */

public class Spisok_singl_refactor_second {
    private static final String TAG = "Массив";

    private ChildEventListener location_sort_arraylistner_Child;
    private ChildEventListener bloched_sort_arraylistner;


    private ArrayList<String> block_location_sort_arraylist_of_keys;
 //   private ArrayList<String> location_sort_arraylist_of_keys;
 //   private ArrayList<Shop_info_data> location_sort_arraylist;
 //   private ArrayList<Shop_info_data> location_sort_arraylist_COPY;



    private DatabaseReference mDatabase_in_singl;
    private DatabaseReference mDatabase_in_blocked;
    private My_app app;
    private MyCallbacl_refresherlist myCallback;
    private static Spisok_singl_refactor_second instance;
    private LatLng mylocation;

    private Spisok_singl_refactor_second() {

    }


    public void registerCallBack(MyCallbacl_refresherlist callback) {
        this.myCallback = callback;
    }

    public static synchronized Spisok_singl_refactor_second getInstance() {
        if (instance == null) {
            instance = new Spisok_singl_refactor_second();   /// спорное решение !!!
        }
        return instance;
    }

    synchronized void addlistner_location_sort_arraylist(final DatabaseReference mDatabase, final LatLng mylocation, final DatabaseReference isklmDatabase) {
        this.mylocation = mylocation;
        this.mDatabase_in_singl = mDatabase;
        this.mDatabase_in_blocked = isklmDatabase;



        block_location_sort_arraylist_of_keys = new ArrayList<>();


                if (bloched_sort_arraylistner != null)
                    mDatabase_in_blocked.removeEventListener(bloched_sort_arraylistner);
                add_rem_hashset(mDatabase_in_blocked);

                childevent(mDatabase_in_singl, mDatabase_in_blocked);






    }

    synchronized void remove_location_sort_arraylist() {
        mDatabase_in_singl.removeEventListener(location_sort_arraylistner_Child);
        mDatabase_in_blocked.removeEventListener(bloched_sort_arraylistner);
    }


    void childevent(final DatabaseReference mDatabase, final DatabaseReference isklmDatabase) {

        mDatabase.addChildEventListener(location_sort_arraylistner_Child = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Shop_in_locat_url_names_loc obj = dataSnapshot.getValue(Shop_in_locat_url_names_loc.class);


                Shop_info_data obj_of_location_sort_array = new Shop_info_data(dataSnapshot.getKey(), obj.getpicurl(), obj.getname(), obj.getlatitude(), obj.getlongitude());


                add(obj_of_location_sort_array);


            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                Shop_in_locat_url_names_loc obj = dataSnapshot.getValue(Shop_in_locat_url_names_loc.class);
                Shop_info_data obj_of_location_sort_array = new Shop_info_data(dataSnapshot.getKey(), obj.getpicurl(), obj.getname(), obj.getlatitude(), obj.getlongitude());

                refresh(obj_of_location_sort_array);


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

    synchronized void refresh(final Shop_info_data obj) {
        mDatabase_in_singl.getRoot().child("services_names").child("Female haircut").child(obj.getkey()).addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Double test = dataSnapshot.getValue(Double.class);
                        System.out.println("Добавил обект в трисеет" + obj.getname());
                        LatLng mloc = new LatLng(obj.getlatitude(), obj.getlongitude());
                        double mdist = SphericalUtil.computeDistanceBetween(mloc, mylocation);
                        GPScoords_price obj_of_location_sort_array = new GPScoords_price(mdist, obj.getkey(), obj.geturi(), obj.getname(), obj.getlatitude(), obj.getlongitude(), test);

                        myCallback.refresh(obj_of_location_sort_array);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                }

        );


    }

    synchronized void add(final Shop_info_data obj) {
        mDatabase_in_singl.getRoot().child("services_names").child("Female haircut").child(obj.getkey()).addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {


                        Double test = dataSnapshot.getValue(Double.class);
                        System.out.println("Добавил обект в трисеет" + obj.getname());
                        LatLng mloc = new LatLng(obj.getlatitude(), obj.getlongitude());
                        double mdist = SphericalUtil.computeDistanceBetween(mloc, mylocation);
                        GPScoords_price obj_of_location_sort_array = new GPScoords_price(mdist, obj.getkey(), obj.geturi(), obj.getname(), obj.getlatitude(), obj.getlongitude(), test);


if(!block_location_sort_arraylist_of_keys.contains(obj.getkey()))
                        myCallback.addtolist(obj_of_location_sort_array);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                }

        );


    }

    synchronized void remove_by_key(String obj) {


         //   myCallback.removefromlist(c);



    }


    void add_rem_hashset(DatabaseReference mDatabase) {


        mDatabase.addChildEventListener(bloched_sort_arraylistner = new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        block_location_sort_arraylist_of_keys.add(dataSnapshot.getKey());


                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {
                        block_location_sort_arraylist_of_keys.remove(dataSnapshot.getKey());


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