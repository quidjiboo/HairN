package ru.akov.hairn.main_window_shop;

import android.content.Context;
import android.util.Log;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;

import ru.akov.hairn.main_window_client.MyHolder;

/**
 * Created by User on 05.06.2017.
 */

public class myfirebaseRecyclAdapter_zakazi_shop extends FirebaseRecyclerAdapter <String, MyHolder>{

    private static final String TAG = myfirebaseRecyclAdapter_zakazi_shop.class.getSimpleName();
    private Context context;
    public myfirebaseRecyclAdapter_zakazi_shop(Class<String> modelClass, int modelLayout, Class<MyHolder> viewHolderClass, DatabaseReference ref, Context context) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        this.context = context;
        Log.d(TAG,"Сделал адаптер");
    }
    @Override
    protected void populateViewHolder(MyHolder viewHolder, String model, int position) {

        Log.d(TAG,"НУ ЧТО БЫ ВИДНО БЛЫО " + getRef(position).getKey().toString());
        viewHolder.recipeName.setText(getRef(position).getKey().toString());


    }

}