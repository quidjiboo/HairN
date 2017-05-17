package ru.akov.hairn.recycle_view_test;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.util.ArrayMap;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.firebase.geofire.GeoFire;
import com.firebase.geofire.GeoLocation;
import com.firebase.geofire.GeoQuery;
import com.firebase.geofire.GeoQueryEventListener;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.DatabaseError;
import com.google.maps.android.SphericalUtil;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

import ru.akov.hairn.MainActivity;
import ru.akov.hairn.My_app;
import ru.akov.hairn.R;

public class Mecycle_vew_test extends AppCompatActivity implements MyCallbacl_listner_global_baddy {

    private ArrayMap<String, LatLng> mkeys = new ArrayMap<>();
    private SortedSet<GPScoords> countrySet;

    private Mecycle_vew_test context;
    private static final String TAG = "MainActivity";
    private My_global_day_to_delet_test badday_reader;
    private My_app app;
    private GeoQuery geoQuery;
    final ArrayList<String> store_keys = new ArrayList<String>();

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        final Comparator testcomap = new Comparator<GPScoords>() {
            public int compare(GPScoords o1, GPScoords o2) {
                Double mdist1 = o1.getmdist();
                Double mdist2 = o2.getmdist();
                String key1 = o1.getkey();
                String key2 = o2.getkey();
                if ((mdist1 > mdist2)) {
                    return 1;
                } else if (mdist1 < mdist2) {
                    return -1;
                } else {

                    return o1.toString().compareTo(o2.toString());
                }
               }
        };
        countrySet = new TreeSet<>(testcomap);

        setContentView(R.layout.activity_recycle_vew_test);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        mRecyclerView = (RecyclerView) findViewById(R.id.RecView_my);
        // если мы уверены, что изменения в контенте не изменят размер layout-а RecyclerView
        // передаем параметр true - это увеличивает производительность
        mRecyclerView.setHasFixedSize(true);
        // используем linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        app = ((My_app) getApplicationContext());
        GeoFire geoFire = new GeoFire(app.getmDatabase().child("locations").child("Novovoronezh").child("barbershops_locations"));
        geoFire.setLocation("-Kk6KCRefV3EGZ8Vn222", new GeoLocation(37.7853839, -112.4056973));
        geoFire.setLocation("-Kk6KCRefV3EGZ8Vn333", new GeoLocation(31.7853839, -112.4356973));
        geoFire.setLocation("-Kk6KCRefV3EGZ8Vn444", new GeoLocation(31.7853839, -112.4356973));
        //Cоздал первоначальный список - пока не динамический , потом можно будет подкрутить
        geoQuery = geoFire.queryAtLocation(new GeoLocation(31.7853339, -112.4026973), 20);
        geoQuery.addGeoQueryEventListener(new GeoQueryEventListener() {

            LatLng mymloc = new LatLng(31.7853339, -112.4026973);

            @Override
            public void onKeyEntered(String key, GeoLocation location) {
                System.out.println(String.format("Key %s entered the search area at [%f,%f]", key, location.latitude, location.longitude));
                store_keys.add(key);

                LatLng mloc = new LatLng(location.latitude, location.longitude);


                mkeys.put(key.toString(), mloc);


                GPScoords objmy = new GPScoords(key.toString(), SphericalUtil.computeDistanceBetween(mloc, mymloc));
                countrySet.add(objmy);

            }

            @Override
            public void onKeyExited(String key) {
                System.out.println(String.format("Key %s is no longer in the search area", key));
            }

            @Override
            public void onKeyMoved(String key, GeoLocation location) {
                System.out.println(String.format("Key %s moved within the search area to [%f,%f]", key, location.latitude, location.longitude));
            }

            @Override
            public void onGeoQueryReady() {
                System.out.println("All initial data has been loaded and events have been fired!");
                //когда все прочитано удаляем листнер и начинаем работать с листом
                geoQuery.removeAllListeners();

                for (String cat : store_keys) {
                //    System.out.println("СПИСОК ТЕХ КТО В ЗОНЕ ДОСЯГАЕМОСТИ" + c at);

                }

                spisok_creator(store_keys);
                System.out.println("СПИСОК stn" + countrySet.size());

                ArrayList<GPScoords> countrylist = new ArrayList<GPScoords>(countrySet);
                Spisok_singletone.getInstance().add_data(countrylist,app.getmDatabase());
            }

            @Override
            public void onGeoQueryError(DatabaseError error) {
                System.err.println("There was an error with this query: " + error);
            }
        });


        ImageView mimageView = (ImageView) findViewById(R.id.imageView3);

        Glide.with(this)
                .load("https://firebasestorage.googleapis.com/v0/b/test-base-soc-net.appspot.com/o/defaultshop.png?alt=media&token=92cc5bdb-bb0d-4a03-a292-da6ef5eb622d")
                .into(mimageView);
    }


    public void spisok_creator(ArrayList<String> array_of_keys) {


        // добавлять листнер на удаление ненужно только после того как сделан общий список магазинов
        badday_reader = new My_global_day_to_delet_test();
        badday_reader.registerCallBack(this);
        badday_reader.List_ofdays(app.getmDatabase());
        //цеплять предварительно обработанный список
        mAdapter = new RecyclerAdaptermy(this, array_of_keys);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onBackPressed() {
        // super.onBackPressed();
        geoQuery.removeAllListeners();
        Intent intent = new Intent(Mecycle_vew_test.this, MainActivity.class);

        startActivity(intent);

        this.finish();
    }

    public void addbadday(View view) {
        app.getmDatabase().child("test_days_to_delete").child("-Kk6KCRefV3EGZ8Vn333").setValue(Boolean.TRUE);

    }

    public void deletbadday(View view) {
        app.getmDatabase().child("test_days_to_delete").child("-Kk6KCRefV3EGZ8Vn333").removeValue();
    }

    @Override
    synchronized public void dayisdeletedfrombadlist(String test) {

        if (!store_keys.contains(test)) {
            store_keys.add(test);
       //     Log.d(TAG, "Прибавил день в список" + test);
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    synchronized public void dayisaddedinbadlist(String test) {


        if (store_keys.contains(test)) {
            store_keys.remove(test);
       //     Log.d(TAG, "Удалил  день из списка" + test);
        }
        mAdapter.notifyDataSetChanged();
    }
}
