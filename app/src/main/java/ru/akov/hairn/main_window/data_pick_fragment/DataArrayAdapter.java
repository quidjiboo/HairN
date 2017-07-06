package ru.akov.hairn.main_window.data_pick_fragment;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pascalwelsch.arrayadapter.ArrayAdapter;

import ru.akov.hairn.R;
import ru.akov.hairn.listesting.DATA.GPScoords_price;

/**
 * Created by User on 06.07.2017.
 */

public class DataArrayAdapter extends ArrayAdapter<GPScoords_price, DataArrayAdapter.ViewHolder> {

public static class ViewHolder extends RecyclerView.ViewHolder {

    private final TextView titleView;

    public ViewHolder(final View itemView) {
        super(itemView);
        titleView = (TextView) itemView.findViewById(R.id.txtTitle2);
    }
}

    @Nullable
    @Override
    public Object getItemId(@NonNull final GPScoords_price object) {
        return object.getkey();
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final GPScoords_price item = getItem(position);
        holder.titleView.setText(item.getname());
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.test_card, parent, false);
        return new ViewHolder(view);
    }
}