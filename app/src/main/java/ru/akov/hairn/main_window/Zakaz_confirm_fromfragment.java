package ru.akov.hairn.main_window;

import android.util.Log;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import ru.akov.hairn.Data_tipes.Zakaz_fragment;

/**
 * Created by User on 17.03.2017.
 */

public class Zakaz_confirm_fromfragment {

    static    public  void confirm_zakaz_fragment(String typeofshop,String key,final DatabaseReference mDatabase, final FirebaseUser user,Zakaz_fragment aaa  ){
key = "-KqWww31Ig-L0-NRm48v";
        typeofshop = "barbershops";



        mDatabase.child(typeofshop).child(aaa.getmagazinid()).child("zakazi").child(key).child("status").setValue("ok");
        mDatabase.child("users").child(aaa.clienid).child("myzakazy").child(key).child("status").setValue("ok");






                        Log.v("AKOV", "ADD USER ");

                        //    }

                    }



}
