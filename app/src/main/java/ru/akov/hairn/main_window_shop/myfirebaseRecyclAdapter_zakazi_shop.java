package ru.akov.hairn.main_window_shop;

import android.content.Context;
import android.util.Log;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.Query;

import ru.akov.hairn.Data_tipes.Zakaz_shop_second_fragment;

/**
 * Created by User on 05.06.2017.
 */

public class myfirebaseRecyclAdapter_zakazi_shop extends FirebaseRecyclerAdapter <Zakaz_shop_second_fragment, MyHolder_zakazi>{

    private static final String TAG = myfirebaseRecyclAdapter_zakazi_shop.class.getSimpleName();
    private Context context;

    /**
     * @param modelClass      Firebase will marshall the data at a location into an instance of a class that you provide
     * @param modelLayout     This is the layout used to represent a single item in the list. You will be responsible for populating an
     *                        instance of the corresponding view with the data from an instance of modelClass.
     * @param viewHolderClass The class that hold references to all sub-views in an instance modelLayout.
     * @param ref             The Firebase location to watch for data changes. Can also be a slice of a location, using some
     *                        combination of {@code limit()}, {@code startAt()}, and {@code endAt()}.
     */
    public myfirebaseRecyclAdapter_zakazi_shop(Class<Zakaz_shop_second_fragment> modelClass, int modelLayout, Class<MyHolder_zakazi> viewHolderClass, Query ref) {
        super(modelClass, modelLayout, viewHolderClass, ref);
    }

    /*  public myfirebaseRecyclAdapter_zakazi_shop(Class<Zakaz_confirm_fromfragment> modelClass, int modelLayout, Class<MyHolder> viewHolderClass, DatabaseReference ref, Context context) {
          super(Class<T> modelClass, modelLayout, viewHolderClass, ref);
          this.context = context;
          Log.d(TAG,"Сделал адаптер");
      }*/
    @Override
    protected void populateViewHolder(MyHolder_zakazi viewHolder, Zakaz_shop_second_fragment model, int position) {

        Log.d(TAG,"НУ ЧТО БЫ ВИДНО БЛЫО " + getRef(position).getKey().toString());
       // viewHolder.recipeName.setText(getRef(position).getKey().toString());
        viewHolder.recipeName.setText(model.getuslugi());

    }

}