package ru.akov.hairn;

import android.support.v4.app.FragmentManager;
import android.util.Log;

import com.google.android.gms.common.GoogleApiAvailability;
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

import ru.akov.hairn.Data_tipes.Clock;

/**
 * Created by User on 16.01.2017.
 */
public class Day_Reader {
    MyCallback myCallback;
    public void registerCallBack(MyCallback callback) {
        this.myCallback = callback;
    }

    public void List_ofdays(final DatabaseReference mDatabase, final FirebaseUser user) {
        mDatabase.child("shop").child("test_barber").child("workdays").addValueEventListener(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {


                        ArrayList<Date> notbuzy_dayz = new ArrayList();
                        ArrayList<Date> buzy_dayz = new ArrayList();

                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                            Boolean freeday=false;
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

                           //тут часы выделяем
                            for (DataSnapshot postpostSnapshot: postSnapshot.getChildren()) {

                                Clock clocks = new Clock();
                                clocks =   postpostSnapshot.getValue(Clock.class);
                                if (clocks.getavaluble().contains("free"))

                                    freeday=true;
                                Log.v("AKOV", "Часы в дне" + postpostSnapshot.getValue().toString());

                            }


                            if(postSnapshot.getChildrenCount()==0)  buzy_dayz.add(date);
                            if(!freeday)  buzy_dayz.add(date);
                            else
                            notbuzy_dayz.add(date);
                            Log.v("AKOV", "добавлена дата" + date.toString());
                            // TODO: ТУТ СОЗДАВАТЬ КАЛЕНДАРИК ЧЕРЕЗ колбэк.. и по всем элементам массива создавать СЛУШАТЕЛИ . МЕНЕДЖЕР СЛУШАТЕЛЕЙ СДЕЛАТЬ.. в бужщем


                        }
                        // обновление календаричка при изменении таблицы с числами и данными в ней
                        myCallback.izmenit_calendar(notbuzy_dayz,buzy_dayz);


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
