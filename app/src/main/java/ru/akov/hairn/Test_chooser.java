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

import com.firebase.ui.database.FirebaseIndexListAdapter;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DataSnapshot;
import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

import ru.akov.hairn.Data_tipes.Clock;

public class Test_chooser extends AppCompatActivity   implements MyCallback {
    Calendar  cal;
    private Day_Reader product_touch_listenr;
    private My_app app;
    TextView dateDisplay;
    CaldroidFragment caldroidFragment;
    private  Day_Reader day_reader;
    private  Calendarik_data_pick calendarik_data_picker;
    private ListView messagesView;
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
        cal = Calendar.getInstance();
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

        calendarik_data_picker  = new Calendarik_data_pick();
        calendarik_data_picker.registerCallBack(this);
        caldroidFragment.setCaldroidListener(calendarik_data_picker.createlistner());


        messagesView  = (ListView) findViewById(R.id.clocks);


    }

    @Override
    public void izmenit_calendar(ArrayList<Date> dates,ArrayList<Date> buzydates) {
        caldroidFragment.clearDisableDates();
       caldroidFragment.getBackgroundForDateTimeMap().clear();
        ColorDrawable green = new ColorDrawable(Color.GREEN);
        caldroidFragment.setDisableDates(buzydates);
        for (Date date : dates) {
            caldroidFragment.setBackgroundDrawableForDate(green,date);

        }

        caldroidFragment.refreshView();

    }

    @Override
    public void vibral_datu(String date) {
       /* Date date1 = null;
        String stringDateFormat = "yyyyMMdd";
        SimpleDateFormat format = new SimpleDateFormat(stringDateFormat, Locale.US);
        try {
            date1 = format.parse(date);

            System.out.println(date.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ColorDrawable green = new ColorDrawable(Color.BLUE);
        caldroidFragment.setBackgroundDrawableForDate(green,date1);*/

        FirebaseListAdapter mAdapter;


        Snackbar.make(getCurrentFocus(),"Выбрал дату" + date , Snackbar.LENGTH_LONG).show();

        mAdapter = new FirebaseListAdapter<Clock>(this, Clock.class, android.R.layout.simple_list_item_1, app.getmDatabase().getRef().child("shop").child("test_barber").child("workdays").child(date)) {
            @Override
            protected void populateView(View view, Clock clocks, int position) {
                if(!clocks.getavaluble().contains("free"))
                    ((TextView) view.findViewById(android.R.id.text1)).setText("ЗАНЯТО");
                else {
                    ((TextView) view.findViewById(android.R.id.text1)).setText(clocks.getclock());
                }

            }
        };

        messagesView.setAdapter(mAdapter);
 //       caldroidFragment.refreshView();
    }
}



