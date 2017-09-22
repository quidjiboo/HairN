package ru.akov.hairn.main_window_shop;

import android.util.Log;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

/**
 * Created by User on 17.03.2017.
 */

public class Zakaz_accepted_fromshopfragment {

    static    public  void _accept_zakaz(String typeofshop,final DatabaseReference mDatabase, final FirebaseUser user,String client_id,String Zakaz_key, String magazinid){


        //дефолтовыймагазин
        final String TAG = "Отправка заказа";



                     mDatabase.child("users").child(client_id).child("myzakazy").child(Zakaz_key).setValue("accepted");



                        mDatabase.child(typeofshop).child(magazinid).child("zakazi").child(Zakaz_key).setValue("accepted");




                        Log.v("AKOV", "ADD USER ");

                        //    }

                    }



}
