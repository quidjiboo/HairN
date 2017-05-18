package ru.akov.hairn.listesting;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 18.05.2017.
 */
class mFirebaseArray implements ChildEventListener, ValueEventListener {
    private Query mQuery;
    private Query m1Query;
    private List<DataSnapshot> mSnapshots = new ArrayList<>();

    public mFirebaseArray(Query ref,Query ref2) {
        mQuery = ref;
        m1Query = ref2;
        mQuery.addChildEventListener(this);
        mQuery.addValueEventListener(this);

        m1Query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildKey) {
              int index = getIndexForKey(dataSnapshot.getKey());
                mSnapshots.remove(index);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {


            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void cleanup() {
        mQuery.removeEventListener((ValueEventListener) this);
        mQuery.removeEventListener((ChildEventListener) this);
    }

    public int getCount() {
        return mSnapshots.size();
    }

    public DataSnapshot getItem(int index) {
        return mSnapshots.get(index);
    }

    private int getIndexForKey(String key) {
        int index = 0;
        for (DataSnapshot snapshot : mSnapshots) {
            if (snapshot.getKey().equals(key)) {
                return index;
            } else {
                index++;
            }
        }
        throw new IllegalArgumentException("Key not found");
    }

    @Override
    public void onChildAdded(DataSnapshot snapshot, String previousChildKey) {
System.out.println(previousChildKey);
        int index = 0;
        if (previousChildKey != null) {
            index = getIndexForKey(previousChildKey) + 1;
        }

        mSnapshots.add(index, snapshot);

    }

    @Override
    public void onChildChanged(DataSnapshot snapshot, String previousChildKey) {
        int index = getIndexForKey(snapshot.getKey());
        mSnapshots.set(index, snapshot);

    }

    @Override
    public void onChildRemoved(DataSnapshot snapshot) {
        int index = getIndexForKey(snapshot.getKey());
        mSnapshots.remove(index);

    }

    @Override
    public void onChildMoved(DataSnapshot snapshot, String previousChildKey) {
        int oldIndex = getIndexForKey(snapshot.getKey());
        mSnapshots.remove(oldIndex);
        int newIndex = previousChildKey == null ? 0 : (getIndexForKey(previousChildKey) + 1);
        mSnapshots.add(newIndex, snapshot);

    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {

    }

    @Override
    public void onCancelled(DatabaseError error) {

    }




}
