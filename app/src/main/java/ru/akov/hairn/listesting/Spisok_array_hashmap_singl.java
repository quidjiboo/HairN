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
import ru.akov.hairn.recycle_view_test.GPScoords;

/**
 * Created by User on 17.03.2017.
 */

public class Spisok_array_hashmap_singl {
    private static final String TAG = "Массив";
    private ValueEventListener location_sort_arraylistner;
    private ChildEventListener bloched_sort_arraylistner;

    private ArrayList<Shop_in_locat_url_names_loc> location_sort_arraylist_of_Shop_in_locat_url_names_loc;
    private ArrayList<Shop_in_locat_url_names_loc> location_sort_arraylist_of_Shop_in_locat_url_names_loc_COPY;
    private ArrayList<String> location_sort_arraylist_of_keys;


    private ArrayList<GPScoords> location_sort_arraylist;
    private SortedSet<GPScoords> location_sort_array_treest;
    private SortedSet<GPScoords> location_sort_array_treest_COPY;

    private DatabaseReference mDatabase_in_singl;
    private DatabaseReference mDatabase_in_blocked;
    private My_app app;
    private MyCallbacl_refresherlist myCallback;
    private static Spisok_array_hashmap_singl instance;

    private Spisok_array_hashmap_singl() {

    }

    public void registerCallBack(MyCallbacl_refresherlist callback) {
        this.myCallback = callback;
    }

    public static synchronized Spisok_array_hashmap_singl getInstance() {
        if (instance == null) {
            instance = new Spisok_array_hashmap_singl();   /// спорное решение !!!
        }
        return instance;
    }

    synchronized void addlistner_location_sort_arraylist(final DatabaseReference mDatabase, final LatLng mylocation, final DatabaseReference isklmDatabase) {
        this.mDatabase_in_singl = mDatabase;
        mDatabase_in_singl.addValueEventListener(location_sort_arraylistner = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                get_sortet_hashset(mDatabase, mylocation, isklmDatabase);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    synchronized void remove_location_sort_arraylist() {
        mDatabase_in_singl.removeEventListener(location_sort_arraylistner);
    }

    void get_sortet_hashset(final DatabaseReference mDatabase, final LatLng mylocation, final DatabaseReference isklmDatabase) {
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
        location_sort_array_treest_COPY = new TreeSet<>(mcomparator);
        location_sort_arraylist_of_Shop_in_locat_url_names_loc = new ArrayList<Shop_in_locat_url_names_loc>();

        location_sort_arraylist_of_keys = new ArrayList<String>();
        mDatabase.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        System.out.println("Скачал");
                        for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {

                            Shop_in_locat_url_names_loc obj = childDataSnapshot.getValue(Shop_in_locat_url_names_loc.class);
                            location_sort_arraylist_of_Shop_in_locat_url_names_loc.add(obj);
                            location_sort_arraylist_of_keys.add(childDataSnapshot.getKey());

                            LatLng mloc = new LatLng(obj.getlatitude(), obj.getlongitude());
                            double mdist = SphericalUtil.computeDistanceBetween(mloc, mylocation);
                            GPScoords obj_of_location_sort_array = new GPScoords(mdist, childDataSnapshot.getKey(), obj.getpicurl(), obj.getname(), obj.getlatitude(), obj.getlongitude());
                            location_sort_array_treest.add(obj_of_location_sort_array);
                            location_sort_array_treest_COPY.add(obj_of_location_sort_array);
                            System.out.println("ТУТ СКАЧАЛ И СДЕЛАЛ ОБЕКТ" + obj.getname());

                        }

                        location_sort_arraylist = new ArrayList<GPScoords>(location_sort_array_treest);
                        location_sort_arraylist_of_keys = new ArrayList<String>();

                        Iterator<GPScoords> iterator = location_sort_array_treest.iterator();
                        while (iterator.hasNext()) {
                            GPScoords c = (GPScoords) iterator.next();
                            location_sort_arraylist_of_keys.add(c.getkey());
                            System.out.println("ОБЕКТЫ В ТРИИСЕТЕ =" + c.getmdist() + c.getname() + c.getkey());

                        }
                     //   myCallback.refresh();
                        add_rem_hashset(isklmDatabase);

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                }

        );


    }

    synchronized public ArrayList<GPScoords> getlist() {
        ArrayList nullarray = new ArrayList<GPScoords>();
        if (location_sort_arraylist != null) {
            return location_sort_arraylist;
        } else {

            return nullarray;
        }

    }

    void add_rem_hashset(DatabaseReference mDatabase) {


        mDatabase.addChildEventListener(new ChildEventListener() {
                                            @Override
                                            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                                                if (location_sort_arraylist_of_keys.contains(dataSnapshot.getKey())) {

                                                    Iterator<GPScoords> iterator = location_sort_array_treest.iterator();
                                                    while (iterator.hasNext()) {

                                                        GPScoords c = (GPScoords) iterator.next();
                                                        if (c.getkey().contains(dataSnapshot.getKey()))
                                                            iterator.remove();


                                                    }
                                                    location_sort_arraylist = new ArrayList<GPScoords>(location_sort_array_treest);

                                                    myCallback.refresh();
                                                }

                                            }

                                            @Override
                                            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                                            }

                                            @Override
                                            public void onChildRemoved(DataSnapshot dataSnapshot) {
                                                if (location_sort_arraylist_of_keys.contains(dataSnapshot.getKey())) {
                                                    Iterator<GPScoords> iterator = location_sort_array_treest_COPY.iterator();
                                                    while (iterator.hasNext()) {

                                                        GPScoords c = (GPScoords) iterator.next();
                                                        if (c.getkey().contains(dataSnapshot.getKey()))
                                                            location_sort_array_treest.add(c);


                                                    }
                                                    location_sort_arraylist = new ArrayList<GPScoords>(location_sort_array_treest);

                                                    myCallback.refresh();

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