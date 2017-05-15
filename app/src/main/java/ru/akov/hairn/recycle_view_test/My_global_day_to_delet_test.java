package ru.akov.hairn.recycle_view_test;

import android.util.Log;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import ru.akov.hairn.recycle_view_test.MyCallbacl_listner_global_baddy;

/**
 * Created by User on 15.05.2017.
 */

public class My_global_day_to_delet_test {
    MyCallbacl_listner_global_baddy myCallback;

    public void registerCallBack(MyCallbacl_listner_global_baddy callback) {
        this.myCallback = callback;
    }

    public void List_ofdays(final DatabaseReference mDatabase) {
        mDatabase.child("test_days_to_delete").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.d("Добавилась запись", dataSnapshot.getKey());
                myCallback.dayisaddedinbadlist(dataSnapshot.getKey());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Log.d("Удалилась запись", dataSnapshot.getKey());
                myCallback.dayisdeletedfrombadlist(dataSnapshot.getKey());
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    static public void getdata(final DatabaseReference mDatabase) {
        mDatabase.child("test_days_to_delete").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
