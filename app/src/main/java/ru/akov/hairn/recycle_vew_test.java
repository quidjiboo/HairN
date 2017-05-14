package ru.akov.hairn;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.firebase.geofire.GeoFire;
import com.firebase.geofire.GeoLocation;
import com.firebase.geofire.GeoQuery;
import com.firebase.geofire.GeoQueryEventListener;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;

public class Recycle_vew_test extends AppCompatActivity {

    private My_app app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final ArrayList<String> catnames = new ArrayList<String>();
        super.onCreate(savedInstanceState);
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


        app = ((My_app) getApplicationContext());
        GeoFire geoFire = new GeoFire(app.getmDatabase().child("locations").child("Novovoronezh").child("barbershops_locations"));
        geoFire.setLocation("-Kk6KCRefV3EGZ8Vn222", new GeoLocation(37.7853839, -112.4056973));
        geoFire.setLocation("-Kk6KCRefV3EGZ8Vn333", new GeoLocation(33.7853839, -112.4056973));
        geoFire.setLocation("-Kk6KCRefV3EGZ8Vn444", new GeoLocation(31.7853839, -112.4056973));
        GeoQuery geoQuery = geoFire.queryAtLocation(new GeoLocation(31.7853339, -112.4026973), 20);
        geoQuery.addGeoQueryEventListener(new GeoQueryEventListener() {
            @Override
            public void onKeyEntered(String key, GeoLocation location) {
                System.out.println(String.format("Key %s entered the search area at [%f,%f]", key, location.latitude, location.longitude));
               // catnames.add(key);
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
            }

            @Override
            public void onGeoQueryError(DatabaseError error) {
                System.err.println("There was an error with this query: " + error);
            }
        });

    }

}
