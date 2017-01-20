package ru.akov.hairn;

import android.support.design.widget.Snackbar;
import android.view.View;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidListener;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by User on 20.01.2017.
 */

public class Calendarik_data_pick {
    MyCallback myCallback;
    public void registerCallBack(MyCallback callback) {
        this.myCallback = callback;
    }
    public CaldroidListener createlistner(){
        CaldroidListener listener = new CaldroidListener() {
            @Override
            public void onSelectDate(Date date, View view) {

                if (date.before(Calendar.getInstance().getTime())){

                    Snackbar.make(view,"Выбеи другую дату", Snackbar.LENGTH_LONG).show();
                }
                else {

                    final  String month = (String) android.text.format.DateFormat.format("MM", date); //06
                    final String year = (String) android.text.format.DateFormat.format("yyyy", date); //2013
                    final   String day = (String) android.text.format.DateFormat.format("dd", date);
                    final String zapis = year + month + day;
                    myCallback.vibral_datu(zapis);
                }
            }
        };


        return listener;
    }



}
