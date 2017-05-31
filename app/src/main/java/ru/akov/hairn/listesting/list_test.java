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
import com.google.firebase.database.DatabaseReference;
import com.google.maps.android.SphericalUtil;

import java.util.ArrayList;

import ru.akov.hairn.MainActivity;
import ru.akov.hairn.My_app;
import ru.akov.hairn.R;
import ru.akov.hairn.listesting.DATA.GPScoords_price;

public class list_test extends AppCompatActivity implements MyCallbacl_refresherlist {
    //временные переменные должны приходить при открытии списка
    //  String namebarbershop_key ="-KlJ2HhZMdxUQCa2HF2Z";
    String location = "Novovoronezh";
    String services_names = "services_names";
    String service = "Female haircut";
    String date = "20170602";
    String time = "08_00";
    LatLng mymloc = new LatLng(31.7853339, -112.4026973);

    //private LatLng mymloc;
    private ListView mlistView;

    private MyArrayAdapter_price adapter_price;
    private My_app app;

    private ArrayList<GPScoords_price> arra_for_listvieew_price;


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


        mlistView = (ListView) findViewById(R.id.mlist);
        arra_for_listvieew_price = new ArrayList<>();
        adapter_price = new MyArrayAdapter_price(this, R.layout.test_card, arra_for_listvieew_price);
        mlistView.setAdapter(adapter_price);
        Button mbutton = (Button) findViewById(R.id.button17);
        mbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });


        DatabaseReference m_service_ref = app.getmDatabase().child(services_names).child(service).child(location);
        DatabaseReference m_block_date_time = app.getmDatabase().child(date).child(time).child(service).child(location);

        Single_tone_array_creator.getInstance().addlistner_location_sort_arraylist(m_service_ref, mymloc, m_block_date_time);
        Single_tone_array_creator.getInstance().registerCallBack(this);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Single_tone_array_creator.getInstance().remove_location_sort_arraylist();
        Intent intent = new Intent(list_test.this, MainActivity.class);

        startActivity(intent);

        this.finish();
    }


    @Override
    synchronized public void addtolist(GPScoords_price obj) {

        LatLng mloc = new LatLng(obj.getlatitude(), obj.getlongitude());
        double mdist = SphericalUtil.computeDistanceBetween(mloc, mymloc);
        obj.setmdist(mdist);
        adapter_price.add(obj);
        adapter_price.notifyDataSetChanged();
    /*    if(adapter_price.getCount()>0) {
            for (int i = 0; i < adapter_price.getCount(); i++) {
                GPScoords_price orig = adapter_price.getItem(i);
                if (!orig.getkey().contains(obj.getkey())) {
                    Log.d("dsfsdfs", "YEY YEY YEY E " + obj.getkey());

                }
            }
       }*/

    }

    @Override
    synchronized public void removefromlist(String obj) {
        GPScoords_price orig = null;
        for (int i = 0; i < adapter_price.getCount(); i++) {
            orig = adapter_price.getItem(i);
            if (orig.getkey().contains(obj)) {
                adapter_price.remove(orig);
                adapter_price.notifyDataSetChanged();
            }
        }

    }


}
