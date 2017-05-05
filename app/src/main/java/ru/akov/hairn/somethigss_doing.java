package ru.akov.hairn;

import android.util.Log;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import ru.akov.hairn.Data_tipes.Confirm;
import ru.akov.hairn.Data_tipes.Shops;


/**
 * Created by User on 13.01.2017.
 */

public   class somethigss_doing {


    public  static void confirm_order(final DatabaseReference mDatabase, final FirebaseUser user ){

        final String testdate = "20170504";
        final String testdate2 = "20170505";
        //дефолтовыймагазин
        final String TAG = "Поддверждение заказа";
        final String userId = user.getUid();
     //   String key = mDatabase.child("Confirmedorders").push().getKey();
      //  Confirm msg = new Confirm("wd0BrpZcbThQ4nJCbAka5pqufV13");
        mDatabase.child("Confirmedorders").child("wd0BrpZcbThQ4nJCbAka5pqufV13").setValue("false");

        String stringDateFormat = "yyyyMMdd";
        SimpleDateFormat format = new SimpleDateFormat(stringDateFormat, Locale.US);
        Date date = null;
        try {
            date = format.parse(testdate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar mycal = Calendar.getInstance();
        mycal.setTime(date);
        mDatabase.child("Confirmedorders_pull").child(String.valueOf(mycal.get(Calendar.YEAR)))
                .child(String.valueOf(mycal.get(Calendar.MONTH)+1))
                .child(String.valueOf(mycal.get(Calendar.DAY_OF_MONTH)))
                .child("wd0BrpZcbThQ4nJCbAka5pqufV13").setValue("true");
//со второй датой для теста !!
        date = null;
        try {
            date = format.parse(testdate2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        mycal = Calendar.getInstance();
        mycal.setTime(date);
        mDatabase.child("Confirmedorders_pull").child(String.valueOf(mycal.get(Calendar.YEAR)))
                .child(String.valueOf(mycal.get(Calendar.MONTH)+1))
                .child(String.valueOf(mycal.get(Calendar.DAY_OF_MONTH)))
                .child("wd0BrpZcbThQ4nJCbAka5pqufV13").setValue("true");
    }


}
