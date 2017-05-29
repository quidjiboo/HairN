package ru.akov.hairn.listesting;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.maps.android.SphericalUtil;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

import ru.akov.hairn.Data_tipes.Shop_in_locat_url_names_loc;
import ru.akov.hairn.My_app;
import ru.akov.hairn.listesting.DATA.GPScoords;
import ru.akov.hairn.listesting.DATA.GPScoords_price;
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

    private SortedSet<GPScoords_price> location_sort_array_treest;

    private DatabaseReference mDatabase_in_singl;
    private DatabaseReference mDatabase_in_blocked;
    private My_app app;
    private MyCallbacl_refresherlist myCallback;
    private static Spisok_singl instance;
    private LatLng mylocation;

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

    synchronized void addlistner_location_sort_arraylist(final DatabaseReference mDatabase, final LatLng mylocation, final DatabaseReference isklmDatabase) {
        this.mylocation = mylocation;
        this.mDatabase_in_singl = mDatabase;
        this.mDatabase_in_blocked = isklmDatabase;

        final Comparator mcomparator = new Comparator<GPScoords>() {
            public int compare(GPScoords o1, GPScoords o2) {
                double mdist1 = o1.getmdist();
                double mdist2 = o2.getmdist();
                String key1 = o1.getname();
                String key2 = o2.getname();
                if ((mdist1 > mdist2)) {
                    return 1;
                } else if (mdist1 < mdist2) {
                    return -1;
                } else if (key1.length() > key2.length()) {
                    return 1;
                } else if (key2.length() > key1.length()) {
                    return -1;
                } else {
                    return o1.toString().compareTo(o2.toString());
                }
            }
        };
        location_sort_array_treest = new TreeSet<>(mcomparator);

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
        location_sort_arraylist_COPY = new ArrayList<Shop_info_data>();
        location_sort_arraylist_of_keys = new ArrayList<String>();
        mDatabase.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        System.out.println("Скачал");
                        for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {

                            Shop_in_locat_url_names_loc obj = childDataSnapshot.getValue(Shop_in_locat_url_names_loc.class);

                            location_sort_arraylist_of_keys.add(childDataSnapshot.getKey());

                            Shop_info_data obj_of_location_sort_array = new Shop_info_data(childDataSnapshot.getKey(), obj.getpicurl(), obj.getname(), obj.getlatitude(), obj.getlongitude());
                            location_sort_arraylist.add(obj_of_location_sort_array);
                            location_sort_arraylist_COPY.add(obj_of_location_sort_array);

                            add(obj_of_location_sort_array);

                       //     System.out.println("ТУТ СКАЧАЛ И СДЕЛАЛ ОБЕКТ" + obj.getname());

                        }


                        if (bloched_sort_arraylistner != null)
                            mDatabase_in_blocked.removeEventListener(bloched_sort_arraylistner);
                        //   myCallback.refresh();


                        add_rem_hashset(mDatabase_in_blocked);

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
                        GPScoords_price obj_of_location_sort_array = new GPScoords_price(mdist, obj.getkey(), obj.geturi(), obj.getname(), obj.getlatitude(), obj.getlongitude(),test);
                        location_sort_array_treest.add(obj_of_location_sort_array);

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                }

        );


    }

    synchronized void remove(Shop_info_data obj) {
        System.out.println("Убрал обект из трисеет" + obj.getname());
        Iterator<GPScoords_price> iterator = location_sort_array_treest.iterator();
        while (iterator.hasNext()) {

            GPScoords_price c = (GPScoords_price) iterator.next();
            if (c.getkey().contains(obj.getkey()))
                iterator.remove();


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
                                if (c.getkey().contains(dataSnapshot.getKey())) {
                                    remove(c);
                                    iterator.remove();
                                }

                            }


                            //       myCallback.refresh();
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
                                if (c.getkey().contains(dataSnapshot.getKey())) {
                                    location_sort_arraylist.add(c);

                                    add(c);
                                }

                            }


                            //        myCallback.refresh();


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