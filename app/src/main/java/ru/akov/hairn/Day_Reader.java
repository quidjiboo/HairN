package ru.akov.hairn;

import android.support.v4.app.FragmentManager;
import android.util.Log;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Created by User on 16.01.2017.
 */
public class Day_Reader {


    public void List_ofdays(final DatabaseReference mDatabase, final FirebaseUser user) {
        mDatabase.child("shop").child("test_barber").child("workdays").addValueEventListener(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {


                        ArrayList buzy_dayz = new ArrayList();

                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {


                            Date date = null;
                            System.out.println(postSnapshot.getKey().toString());
                            String stringDateFormat = "yyyyMMdd";
                            SimpleDateFormat format = new SimpleDateFormat(stringDateFormat, Locale.US);

                            try {
                                date = format.parse(postSnapshot.getKey().toString());

                                System.out.println(date.toString());
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            //  Buzy_day X_day = new  Buzy_day(date, (int) postSnapshot.getChildrenCount());
                                    buzy_dayz.add(date);
                            // TODO: ТУТ СОЗДАВАТЬ КАЛЕНДАРИК ЧЕРЕЗ колбэк.. и по всем элементам массива создавать СЛУШАТЕЛИ . МЕНЕДЖЕР СЛУШАТЕЛЕЙ СДЕЛАТЬ..


                        }



                        /// сохдёте окно календарика!
                        //         myCallback.callBack_touchproduct_creat_calendarik(buzy_dayz,max_client,myposition);

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w("уккщк", "getUser:onCancelled", databaseError.toException());
                    }
                });
    }
}
