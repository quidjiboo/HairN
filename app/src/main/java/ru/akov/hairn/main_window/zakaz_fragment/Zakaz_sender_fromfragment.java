package ru.akov.hairn.main_window.zakaz_fragment;

import android.util.Log;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import ru.akov.hairn.Data_tipes.Zakaz_fragment;

/**
 * Created by User on 17.03.2017.
 */

public class Zakaz_sender_fromfragment {

    static    public  void sen_zakaz_fragment(String typeofshop,final DatabaseReference mDatabase, final FirebaseUser user,Zakaz_fragment aaa  ){


        //дефолтовыймагазин
        final String TAG = "Отправка заказа";



                        String key  = mDatabase.child(user.getUid()).child("myzakazy").push().getKey();
/// СНАЧАЛА У КЛИЕНТА ДЕЛАЕМ ЗАПРОС смотри рулезы
                        mDatabase.child("users").child(user.getUid()).child("myzakazy").child(key).setValue(aaa);

                        mDatabase.child(typeofshop).child(aaa.getmagazinid()).child("zakazi").child(key).setValue(aaa);




                        Log.v("AKOV", "ADD USER ");

                        //    }

                    }



}
