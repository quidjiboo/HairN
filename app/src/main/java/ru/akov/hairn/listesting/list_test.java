package ru.akov.hairn.listesting;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

import ru.akov.hairn.MainActivity;
import ru.akov.hairn.My_app;
import ru.akov.hairn.R;
import ru.akov.hairn.recycle_view_test.GPScoords;

public class list_test extends AppCompatActivity implements MyCallbacl_refresherlist {
    private LatLng mymloc;
    private ListView mlistView;
    private MyArrayAdapter adapter;
    private My_app app;
    private ArrayList<GPScoords> arra_for_listvieew;
    private  String key = "000";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app = ((My_app) getApplicationContext());
        setContentView(R.layout.activity_list_test);
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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mymloc = new LatLng(31.7853339, -112.4026973);

        Spisok_array_hashmap_singl.getInstance().addlistner_location_sort_arraylist(app.getmDatabase().child("locations_names").child("Novovoronezh").child("barbershops_names"), mymloc,app.getmDatabase().child("test_rem_add"));
        mlistView = (ListView) findViewById(R.id.mlist);

        arra_for_listvieew = Spisok_array_hashmap_singl.getInstance().getlist();
        adapter = new MyArrayAdapter(this, R.layout.test_card, arra_for_listvieew);
        mlistView.setAdapter(adapter);


        Button mbutton = (Button) findViewById(R.id.button17);
        mbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   }
        });

        Spisok_array_hashmap_singl.getInstance().registerCallBack(this);



    }

    @Override
    public void onBackPressed() {
        // super.onBackPressed();
        Spisok_array_hashmap_singl.getInstance().remove_location_sort_arraylist();
        Intent intent = new Intent(list_test.this, MainActivity.class);

        startActivity(intent);

        this.finish();
    }

    @Override
    public void refresh() {
        arra_for_listvieew.clear();
        arra_for_listvieew.addAll(Spisok_array_hashmap_singl.getInstance().getlist());
        adapter.notifyDataSetChanged();
    }

    @Override
   synchronized public void adddata(GPScoords obj) {

    }



}
