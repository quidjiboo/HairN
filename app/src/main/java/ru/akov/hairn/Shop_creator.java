package ru.akov.hairn;

import android.util.Log;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

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
                            mDatabase.child("shop").child("test_barber").child("workdays").child("20170116").push().setValue(mcm);
                            mcm = new Clock("8:30","free");
                            mDatabase.child("shop").child("test_barber").child("workdays").child("20170116").push().setValue(mcm);
                            mcm = new Clock("9:00","free");
                            mDatabase.child("shop").child("test_barber").child("workdays").child("20170116").push().setValue(mcm);
                            mcm = new Clock("9:30","free");
                            mDatabase.child("shop").child("test_barber").child("workdays").child("20170116").push().setValue(mcm);
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
}
