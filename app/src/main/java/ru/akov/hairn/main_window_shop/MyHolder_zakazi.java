package ru.akov.hairn.main_window_shop;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import ru.akov.hairn.R;

/**
 * Created by Alexandr on 15.06.2017.
 */

public class MyHolder_zakazi extends RecyclerView.ViewHolder{
    private static final String TAG = MyHolder_zakazi.class.getSimpleName();
    public TextView recipeName;

    public MyHolder_zakazi(View itemView) {
        super(itemView);
        recipeName = (TextView)itemView.findViewById(R.id.tile_text);
        itemView.setClickable(true);
    }
}
