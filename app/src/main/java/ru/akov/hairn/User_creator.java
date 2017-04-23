package ru.akov.hairn;

import android.util.Log;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;


/**
 * Created by User on 13.01.2017.
 */

public   class User_creator {


    public  static void add_user(final DatabaseReference mDatabase, final FirebaseUser user ){
        //дефолтовыймагазин
        final String TAG = "ДЕФОЛТНЫЙ БАРБЕР ШОП";
        final String userId = user.getUid();
        mDatabase.child("users").child(user.getUid()).addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        if(!dataSnapshot.exists()) {

                        /*    Shops msg = new Shops("noname", "notipe", "https://firebasestorage.googleapis.com/v0/b/test-base-soc-net.appspot.com/o/defaultshop.png?alt=media&token=92cc5bdb-bb0d-4a03-a292-da6ef5eb622d",userId);
                            //    String key=mDatabase.child("shops").push().getKey();
*/

                        //    mDatabase.child("users").child(user.getUid()).setValue("true");
                            String name = user.getDisplayName();

                            
                            String DeviceToken =  FirebaseInstanceId.getInstance().getToken();
                            //    String key=mDatabase.child("shops").push().getKey();


                            mDatabase.child("users").child(user.getUid()).setValue(name);

                            mDatabase.child("users").child(user.getUid()).child("tokens").push().setValue(DeviceToken);
                            Log.v("AKOV", "ADD USER ");

                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w(TAG, "getUser:onCancelled", databaseError.toException());
                    }
                });
    }


}
