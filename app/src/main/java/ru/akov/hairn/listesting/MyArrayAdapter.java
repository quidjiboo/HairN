package ru.akov.hairn.listesting;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import ru.akov.hairn.R;
import ru.akov.hairn.recycle_view_test.GPScoords;

/**
 * Created by User on 19.05.2017.
 */

public class MyArrayAdapter extends ArrayAdapter<GPScoords> {

    Context context;
    int layoutResourceId;
    ArrayList<GPScoords> data = null;

    public MyArrayAdapter(Context context, int layoutResourceId, ArrayList<GPScoords> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        WeatherHolder holder = null;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new WeatherHolder();
            holder.name = (TextView)row.findViewById(R.id.txtTitle1);
            holder.dist = (TextView)row.findViewById(R.id.txtTitle2);
         //   holder.image = (ImageView)row.findViewById(R.id.imgIcon123);
            row.setTag(holder);
        }
        else
        {
            holder = (WeatherHolder)row.getTag();
        }



        GPScoords weather = data.get(position);
        double newDouble = new BigDecimal(weather.getmdist()).setScale(3, RoundingMode.UP).doubleValue();
        holder.dist.setText(Double.toString(newDouble));
        holder.name.setText(weather.getname());

       /* Glide.with(context)
                .load(weather.geturi())

                .centerCrop()
                .override(200, 200)


                .into(holder.image);*/
        return row;
    }

    static class WeatherHolder
    {
        TextView name;
        TextView dist;
      //  ImageView image;
    }
}

