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

    private ArrayList<GPScoords> location_sort_arraylist;
    private SortedSet<GPScoords> location_sort_array_treest;
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

    synchronized void addlistner_location_sort_arraylist(final DatabaseReference mDatabase, final LatLng mylocation) {
        this.mDatabase_in_singl = mDatabase;
        mDatabase_in_singl.addValueEventListener(location_sort_arraylistner = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                get_sortet_hashset(mDatabase, mylocation);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    synchronized void remove_location_sort_arraylist() {
        mDatabase_in_singl.removeEventListener(location_sort_arraylistner);
    }

    synchronized void get_sortet_hashset(DatabaseReference mDatabase, final LatLng mylocation) {
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
        mDatabase.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {

                            Shop_in_locat_url_names_loc obj = childDataSnapshot.getValue(Shop_in_locat_url_names_loc.class);

                            LatLng mloc = new LatLng(obj.getlatitude(), obj.getlongitude());
                            double mdist = SphericalUtil.computeDistanceBetween(mloc, mylocation);
                            GPScoords obj_of_location_sort_array = new GPScoords(mdist, childDataSnapshot.getKey(), obj.getpicurl(), obj.getname(), obj.getlatitude(), obj.getlongitude());
                            location_sort_array_treest.add(obj_of_location_sort_array);

                        }
                        location_sort_arraylist = new ArrayList<GPScoords>(location_sort_array_treest);
                     /*   Iterator<GPScoords> iterator = location_sort_array.iterator();
                        while (iterator.hasNext()) {
                            GPScoords c = (GPScoords) iterator.next();
                            System.out.println("ОБЕКТЫ В ТРИИСЕТЕ =" + c.getmdist() + c.getname() + c.getkey());

                        }
                        System.out.println("КОЛВО ЗАПИСЕЙ " + location_sort_array.size());*/
                        myCallback.refresh();
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

    synchronized void addlistner_blocked_sort_arraylist(final DatabaseReference mDatabase, SortedSet<GPScoords> set) {


        this.mDatabase_in_blocked = mDatabase;
        mDatabase_in_singl.addChildEventListener(bloched_sort_arraylistner = new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {

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