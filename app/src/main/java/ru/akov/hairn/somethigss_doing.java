package ru.akov.hairn;

import android.util.Log;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;

import ru.akov.hairn.Data_tipes.Confirm;
import ru.akov.hairn.Data_tipes.Shops;


/**
 * Created by User on 13.01.2017.
 */

public   class somethigss_doing {


    public  static void confirm_order(final DatabaseReference mDatabase, final FirebaseUser user ){
        //дефолтовыймагазин
        final String TAG = "Поддверждение заказа";
        final String userId = user.getUid();
     //   String key = mDatabase.child("Confirmedorders").push().getKey();
      //  Confirm msg = new Confirm("wd0BrpZcbThQ4nJCbAka5pqufV13");
        mDatabase.child("Confirmedorders").child("wd0BrpZcbThQ4nJCbAka5pqufV13").setValue("false");
    }


}
