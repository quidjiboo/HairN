package ru.akov.hairn;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseIndexListAdapter;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
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

import static ru.akov.hairn.R.style.AppTheme;

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
        args.putInt(CaldroidFragment.THEME_RESOURCE,R.style.MyTheme);
       // com.caldroid.R.style.CaldroidMy
        cal = Calendar.getInstance();
        args.putInt(CaldroidFragment.MONTH, cal.get(Calendar.MONTH) + 1);
        args.putInt(CaldroidFragment.YEAR, cal.get(Calendar.YEAR));
        args.putInt(CaldroidFragment.START_DAY_OF_WEEK, CaldroidFragment.MONDAY);
       // args.putBoolean(CaldroidFragment.SQUARE_TEXT_VIEW_CELL, false);

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
     //   caldroidFragment.clearDisableDates();
       caldroidFragment.getBackgroundForDateTimeMap().clear();
        //(R.color.my)
    //    ColorDrawable white = new ColorDrawable(getResources().getColor(R.color.my));
        ColorDrawable white = new ColorDrawable(Color.WHITE);
        ColorDrawable gray = new ColorDrawable(Color.GRAY);
      //  caldroidFragment.setDisableDates(buzydates);
        for (Date date1 : buzydates) {

         //   if(date1.after(Calendar.getInstance().getTime()) )
            caldroidFragment.setBackgroundDrawableForDate(gray,date1);

        }

        for (Date date1 : dates) {
       //     if(date1.after(Calendar.getInstance().getTime()) )
          caldroidFragment.setBackgroundDrawableForDate(white,date1);

        }

        caldroidFragment.refreshView();

    }

    @Override
    public void vibral_datu(String date) {

        test_data(date);
     FirebaseListAdapter  mAdapter = new FirebaseListAdapter<Clock>(this, Clock.class, android.R.layout.simple_list_item_1, app.getmDatabase().getRef().child("shop").child("test_barber").child("workdays").child(date)) {
            @Override
            protected void populateView(View view, Clock clocks, int position) {

                if(clocks.getavaluble().contains("VOHODNOI"))
                    ((TextView) view.findViewById(android.R.id.text1)).setText("ВЫХОДНОЙ");
                else if (clocks.getavaluble().contains("free")){
                    ((TextView) view.findViewById(android.R.id.text1)).setText(clocks.getclock());
                }
                else{  ((TextView) view.findViewById(android.R.id.text1)).setText("ЗАНЯТО");}

            }

        };

        messagesView.setAdapter(mAdapter);

    }

    public void test_data(String date) {
        app.getmDatabase().child("shop").child("test_barber").child("workdays").child(date).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (!dataSnapshot.exists()){
                    Log.w("уккщк", "НЕТ ДАННЫХ!");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });}

}



