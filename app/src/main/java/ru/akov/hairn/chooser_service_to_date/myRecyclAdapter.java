package ru.akov.hairn.chooser_service_to_date;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pascalwelsch.arrayadapter.ArrayAdapter;

import java.util.ArrayList;

import ru.akov.hairn.R;

/**
 * Created by User on 05.06.2017.
 */

public class myRecyclAdapter extends ArrayAdapter<String, myRecyclAdapter.ViewHolder> {



    @Nullable
    @Override
    public Object getItemId(@NonNull String item) {
        return null;
    }

        public static class ViewHolder extends RecyclerView.ViewHolder  {

            public TextView mTextView;

            public ViewHolder(LayoutInflater inflater, ViewGroup parent) {
                super(inflater.inflate(R.layout.item_tile, parent, false));

                mTextView = (TextView) itemView.findViewById(R.id.tile_text);
            }

        }

    public myRecyclAdapter(ArrayList<String> dataset) {
        super(dataset);
    }

    public myRecyclAdapter() {
        super();
    }

    @Override
    public myRecyclAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new myRecyclAdapter.ViewHolder(LayoutInflater.from(parent.getContext()), parent);
    }

    @Override
    public void onBindViewHolder(myRecyclAdapter.ViewHolder holder, int position) {
        final String item = getItem(position);
        holder.mTextView.setText(item);

    }
}