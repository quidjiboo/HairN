package ru.akov.hairn;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DataSnapshot;
import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import ru.akov.hairn.Data_tipes.Clock;

public class Test_chooser extends AppCompatActivity   implements MyCallback {
    private Day_Reader product_touch_listenr;
    private My_app app;
    TextView dateDisplay;
    CaldroidFragment caldroidFragment;
    Day_Reader day_reader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app = ((My_app) getApplicationContext());
        setContentView(R.layout.activity_test_choser);
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

        caldroidFragment = new CaldroidFragment();
        Bundle args = new Bundle();
        Calendar  cal = Calendar.getInstance();
        args.putInt(CaldroidFragment.MONTH, cal.get(Calendar.MONTH) + 1);
        args.putInt(CaldroidFragment.YEAR, cal.get(Calendar.YEAR));
        args.putInt(CaldroidFragment.START_DAY_OF_WEEK, CaldroidFragment.MONDAY);
        args.putBoolean(CaldroidFragment.SQUARE_TEXT_VIEW_CELL, false);

        caldroidFragment.setArguments(args);

        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        t.replace(R.id.calendar1, caldroidFragment);
        t.commit();


        day_reader  = new Day_Reader();
        day_reader.registerCallBack(this);
        day_reader.List_ofdays(app.getmDatabase(),app.getauth().getCurrentUser());

  /*      final CaldroidListener listener = new CaldroidListener() {

            @Override
            public void onSelectDate(Date date, View view) {

           }
        };

        caldroidFragment.setCaldroidListener(listener);*/
        ListView messagesView = (ListView) findViewById(R.id.clocks);

        FirebaseListAdapter mAdapter = new FirebaseListAdapter<Clock>(this, Clock.class, android.R.layout.simple_list_item_1, app.getmDatabase().getRef().child("shop").child("test_barber").child("workdays").child("20170116")) {
            @Override
            protected void populateView(View view, Clock clocks, int position) {

                ((TextView)view.findViewById(android.R.id.text1)).setText(clocks.getclock());


            }
        };
        messagesView.setAdapter(mAdapter);
    }

    @Override
    public void izmenit_calendar(ArrayList<Date> dates) {
       caldroidFragment.getBackgroundForDateTimeMap().clear();
        ColorDrawable green = new ColorDrawable(Color.GREEN);
        for (Date date : dates) {
            caldroidFragment.setBackgroundDrawableForDate(green,date);
        }

        caldroidFragment.refreshView();
    }
}



