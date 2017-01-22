package ru.akov.hairn;

import android.util.Log;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;

import ru.akov.hairn.Data_tipes.Clock;
import ru.akov.hairn.Data_tipes.Shops;

/**
 * Created by User on 13.01.2017.
 */

public   class Shop_creator {


    public  static void add_default_barbeshop(final DatabaseReference mDatabase, final FirebaseUser user ){
        //дефолтовыймагазин
        final String TAG = "ДЕФОЛТНЫЙ БАРБЕР ШОП";
        final String userId = user.getUid();
        mDatabase.child("shop").addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        if(!dataSnapshot.exists()) {

                            Shops msg = new Shops("noname", "notipe", "https://firebasestorage.googleapis.com/v0/b/test-base-soc-net.appspot.com/o/defaultshop.png?alt=media&token=92cc5bdb-bb0d-4a03-a292-da6ef5eb622d",userId);
                            //    String key=mDatabase.child("shops").push().getKey();


                            mDatabase.child("shop").setValue(msg);

                         /*   Product product = new Product("default","0.0","https://firebasestorage.googleapis.com/v0/b/test-base-soc-net.appspot.com/o/shopping-paper-bag-outline_318-39786.png?alt=media&token=93a2373e-1336-4fbe-9268-924db09e4fb9");
                            String key = mDatabase.child("shops").child(key1).child("products").push().getKey();
                            mDatabase.child("shops").child(key1).child("products").child(key).setValue(product);

                            Product_varibles_default variebl = new Product_varibles_default("default");
                            mDatabase.child("shops").child(key1).child("products").child(key).child("variebles").setValue(variebl);*/

                            Log.v("AKOV", "NO SHOPS");
                        }
                        // ...

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w(TAG, "getUser:onCancelled", databaseError.toException());
                    }
                });
    }
    public  static void add_test_workdays(final DatabaseReference mDatabase, final FirebaseUser user ){
        //дефолтовыймагазин
        final String TAG = "ДЕФОЛТНЫЙ БАРБЕР ШОП";
        final String userId = user.getUid();
        mDatabase.child("shop").child("test_barber").child("workdays").addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        if(!dataSnapshot.exists()) {


                            Clock mcm = new Clock("8:00","free");
                            mDatabase.child("shop").child("test_barber").child("workdays").child("20170121").push().setValue(mcm);
                            mcm = new Clock("8:30","free");
                            mDatabase.child("shop").child("test_barber").child("workdays").child("20170121").push().setValue(mcm);
                            mcm = new Clock("9:00","free");
                            mDatabase.child("shop").child("test_barber").child("workdays").child("20170122").push().setValue(mcm);
                            mcm = new Clock("9:30","free");
                            mDatabase.child("shop").child("test_barber").child("workdays").child("20170122").push().setValue(mcm);
                           /* mDatabase.child("shop").child("test_barber").child("workdays").child("20170116").child("8:00").setValue("free");
                            mDatabase.child("shop").child("test_barber").child("workdays").child("20170116").child("8:30").setValue("free");
                            mDatabase.child("shop").child("test_barber").child("workdays").child("20170116").child("9:00").setValue("free");
                            mDatabase.child("shop").child("test_barber").child("workdays").child("20170117").child("9:00").setValue("free");
                            mDatabase.child("shop").child("test_barber").child("workdays").child("20170117").child("9:30").setValue("free");
                            mDatabase.child("shop").child("test_barber").child("workdays").child("20170117").child("10:00").setValue("free");*/

                                 }
                        // ...

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w(TAG, "getUser:onCancelled", databaseError.toException());
                    }
                });
    }
    public  static ArrayList<String> get_days_ofmouth(){
        ArrayList<String> days = new ArrayList();
        Calendar cal = Calendar.getInstance();
        int today_month;
        int today_year;
        int day_in_month;
        today_year = cal.get(Calendar.YEAR);
        today_month = cal.get(Calendar.MONTH);

        day_in_month=cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        for (int i = 0; i <= day_in_month; i++){
            days.add(Integer.toString(today_year)+Integer.toString(today_month)+Integer.toString(i));
            Log.w("AKOV",Integer.toString(today_year)+Integer.toString(today_month)+Integer.toString(i) );
        }
        return days;
    }
    public  static void get_days_ofmouth1(){
        ArrayList<String> days = new ArrayList();
        Calendar cal = Calendar.getInstance();
        int today_month;
        int today_year;
        int day_in_month;
        today_year = cal.get(Calendar.YEAR);
        today_month = cal.get(Calendar.MONTH);


        day_in_month=cal.getActualMaximum(Calendar.DAY_OF_MONTH);

        for (int i = 1; i <= day_in_month; i++){
            String month = String.format("%02d", today_month+1);
            String day = String.format("%02d",i);
            days.add(Integer.toString(today_year)+month+day);
            Log.w("AKOV",Integer.toString(today_year)+month+day );

        }
        cal.set(Calendar.MONTH, today_month+1);
        int next_month = cal.get(Calendar.MONTH);
        int day_in_next_month=cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        for (int i = 1; i <= day_in_next_month; i++){
            String next_month1 = String.format("%02d", next_month+1);
            String day = String.format("%02d",i);
             days.add(Integer.toString(today_year)+next_month1+day);
            Log.w("AKOV",Integer.toString(today_year)+next_month1+day );
        }
        cal.clear();
    }
}
