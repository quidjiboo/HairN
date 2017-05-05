package ru.akov.hairn;

import android.util.Log;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import ru.akov.hairn.Data_tipes.Zakaz;

/**
 * Created by User on 17.03.2017.
 */

public class Zakaz_sender {
 static    public  void sen_zakaz(final DatabaseReference mDatabase, final FirebaseUser user ){

       final Zakaz aaa = new Zakaz("wd0BrpZcbThQ4nJCbAka5pqufV13", "shop","mail_test", "TestName", "123213123","освадебная причёска, брови", "20170521", "8:00","need" );
        //дефолтовыймагазин
        final String TAG = "Отправка заказа";

     mDatabase.child(user.getUid()).child("myzakazy").addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                   //   if(!dataSnapshot.exists()) {

                        /*    Shops msg = new Shops("noname", "notipe", "https://firebasestorage.googleapis.com/v0/b/test-base-soc-net.appspot.com/o/defaultshop.png?alt=media&token=92cc5bdb-bb0d-4a03-a292-da6ef5eb622d",userId);
                            //    String key=mDatabase.child("shops").push().getKey();
*/

                            String key  = mDatabase.child(user.getUid()).child("myzakazy").push().getKey();
/// СНАЧАЛА У КЛИЕНТА ДЕЛАЕМ ЗАПРОС смотри рулезы
                            mDatabase.child("users").child(user.getUid()).child("myzakazy").child(key).setValue(aaa);

                            mDatabase.child("shop").child("zakazi").child(key).setValue(aaa);




                            Log.v("AKOV", "ADD USER ");

                    //    }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w(TAG, "getUser:onCancelled", databaseError.toException());
                    }
                });
    }
}
