package ru.akov.hairn.chooser_service_to_date;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import ru.akov.hairn.My_app;

/**
 * Created by User on 17.03.2017.
 */

public class Single_simple {
    private static final String TAG = "Массив";

    private ArrayList<String> strings;
    private ValueEventListener singlevaluegetter;
    private My_app app;
    private Callback_for_Fragments myCallback;
    private Callback_for_Fragments myCallback1;
    private static Single_simple instance;


    private Single_simple() {

    }
    public void registerCallBack1(Callback_for_Fragments callback) {
        this.myCallback1 = callback;
    }
    public void unregisterCallBack1() {
        this.myCallback1 = null;
    }
    public void registerCallBack(Callback_for_Fragments callback) {
        this.myCallback = callback;
    }
    public void unregisterCallBack() {
        this.myCallback = null;
    }
    public static synchronized Single_simple getInstance() {
        if (instance == null) {
            instance = new Single_simple();   /// спорное решение !!!
        }
        return instance;
    }

    public void addlistner(DatabaseReference m_ref_test) {

            System.out.println("ПОВЕСИЛ ЛИСТЕ СЧИТАЛ МАГАЗИНЫ");
        m_ref_test.addListenerForSingleValueEvent(singlevaluegetter = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()){

                String obj = childDataSnapshot.getKey();
                myCallback.addtolist(obj);
                    myCallback1.addtolist(obj);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}