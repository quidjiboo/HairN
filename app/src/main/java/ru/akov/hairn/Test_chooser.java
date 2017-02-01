package ru.akov.hairn;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.ProgressBar;
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
    private ProgressDialog progressDialog = null;
    private  ArrayList<Date> mybuzydates=null;
    private ArrayList<Date> mydates=null;
    private Date datet =null;
    private String lastpickdate;
    private String lastpickdatecolor;
    Calendar  cal;
    private Day_Reader product_touch_listenr;
    private My_app app;
    TextView dateDisplay;
    CaldroidFragment caldroidFragment;
    private  Day_Reader day_reader;
    private  Day_Reader day_reader1;
    private  Calendarik_data_pick calendarik_data_picker;
    private ListView messagesView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
       //  wight_gray = ContextCompat.getDrawable(this, R.drawable.cell_bg_my_freeday);
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
        day_reader.List_ofdays(app.getmDatabase(),app.getauth().getCurrentUser(),"test_barber");

        day_reader1  = new Day_Reader();
        day_reader1.registerCallBack(this);
        day_reader1.List_ofdays(app.getmDatabase(),app.getauth().getCurrentUser(),"test_barber1");

        calendarik_data_picker  = new Calendarik_data_pick();
        calendarik_data_picker.registerCallBack(this);
        caldroidFragment.setCaldroidListener(calendarik_data_picker.createlistner());


        messagesView  = (ListView) findViewById(R.id.clocks);




        showProgress("загрузка");


    }

    @Override
    public synchronized void izmenit_calendar(ArrayList<Date> dates, ArrayList<Date> buzydates) {
       /* if(mydates!=null&&mybuzydates!=null){
        mybuzydates.clear();
        mydates.clear();}*/
        if(mydates==null){
            mydates=dates;
        }
        for (int i = 0; i < buzydates.size(); i++) {
if(!mydates.contains(dates.get(i))){
    mydates.add(dates.get(i));
}

        }
     //   mybuzydates=buzydates;

        mybuzydates=buzydates;
      caldroidFragment.getBackgroundForDateTimeMap().clear();

        ColorDrawable white = new ColorDrawable(Color.WHITE);

        ColorDrawable gray = new ColorDrawable(Color.GRAY);



        for (Date date1 : mybuzydates) {


            caldroidFragment.setBackgroundDrawableForDate(gray,date1);
            caldroidFragment.refreshView();
        }

        for (Date date1 : mydates) {
            Drawable gra1 =  ContextCompat.getDrawable(this,R.drawable.cell_bg_my_canuse);
        caldroidFragment.setBackgroundDrawableForDate(gra1,date1);
            caldroidFragment.refreshView();
        }

        caldroidFragment.refreshView();
   //     progressBar.setVisibility(ProgressBar.INVISIBLE);
        hideProgress();
    }


    public void vibral_datu_vivod(String date) {






     FirebaseListAdapter  mAdapter = new FirebaseListAdapter<Clock>(this, Clock.class, R.layout.list_item, app.getmDatabase().getRef().child("shop").child("test_barber").child("workdays").child(date)) {
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

        messagesView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);



    }

    public void vibral_datu(String date) {
        if(datet!=null)
        { caldroidFragment.clearBackgroundDrawableForDate(datet);
            caldroidFragment.refreshView();}

        if(mydates!=null&&mybuzydates!=null){
            izmenit_calendar(mydates,mybuzydates);}

        Drawable gra1 =  ContextCompat.getDrawable(this,R.drawable.test);
        String stringDateFormat = "yyyyMMdd";
        SimpleDateFormat format = new SimpleDateFormat(stringDateFormat, Locale.US);
        try {
            datet = format.parse(date);

        } catch (ParseException e) {
            e.printStackTrace();
        }
//убрал изменение кнопки выбраннного дня
      //  caldroidFragment.setBackgroundDrawableForDate(gra1,datet);
     //   caldroidFragment.refreshView();

        test_data(date);


    }

    @Override
    public void vibral_vremia(String date) {

    }

    public void net_dannih() {
        String[] values = new String[] { "НЕТ ДАННЫХ" };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, values);
        messagesView.setAdapter(adapter);
    }


    public void test_data(String date) {

        app.getmDatabase().child("shop").child("test_barber").child("workdays").child(date).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (!dataSnapshot.exists()){
                    net_dannih();
                    Log.w("уккщк", "НЕТ ДАННЫХ!");
                }
                else{
                    Log.w("уккщк", dataSnapshot.getKey().toString());
                 vibral_datu_vivod(dataSnapshot.getKey().toString());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });}






    private void showProgress(String text) {

        if (progressDialog == null) {
            try {
                progressDialog = ProgressDialog.show(this, "", text);
              //  progressDialog.setCancelable(false);
                progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {

                    }
                });
                progressDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
                    @Override
                    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                        if(keyCode == KeyEvent.KEYCODE_BACK)
                        {
                            back_to_main();
                            // ваш код
                        }
                        return true;

                    }
                });

            } catch (Exception e) {

            }

        }

    }
    public void hideProgress() {

        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }
    public void back_to_main() {
        progressDialog.dismiss();
        progressDialog = null;
        Intent intent = new Intent(Test_chooser.this,MainActivity.class);

        startActivity(intent);

        this.finish();
    }

}



