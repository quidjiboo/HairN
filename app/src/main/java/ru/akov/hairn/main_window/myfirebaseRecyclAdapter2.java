package ru.akov.hairn.main_window;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.Query;

import ru.akov.hairn.Data_tipes.Typs_of_shop;
import ru.akov.hairn.R;

/**
 * Created by User on 05.06.2017.
 */

public class myfirebaseRecyclAdapter2 extends FirebaseRecyclerAdapter {
    /**
     * @param modelClass      Firebase will marshall the data at a location into an instance of a class that you provide
     * @param modelLayout     This is the layout used to represent a single item in the list. You will be responsible for populating an
     *                        instance of the corresponding view with the data from an instance of modelClass.
     * @param viewHolderClass The class that hold references to all sub-views in an instance modelLayout.
     * @param ref             The Firebase location to watch for data changes. Can also be a slice of a location, using some
     *                        combination of {@code limit()}, {@code startAt()}, and {@code endAt()}.
     */
    public myfirebaseRecyclAdapter2(Class<Typs_of_shop> modelClass, int modelLayout, Class<mViewHolder> viewHolderClass, Query ref) {
        super(modelClass, modelLayout, viewHolderClass, ref);
    }




 

    @Override
    protected void populateViewHolder(RecyclerView.ViewHolder viewHolder, Object model, int position) {


    }


    public static class mViewHolder extends RecyclerView.ViewHolder  {

        public TextView mTextView;

        public mViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.item_tile, parent, false));

            mTextView = (TextView) itemView.findViewById(R.id.tile_text);
        }
        public void setName(String name) {
            mTextView.setText(name);
        }

        public void setMessage(String message) {
            mTextView.setText(message);
        }
    }


}