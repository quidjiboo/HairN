package ru.akov.hairn.main_window;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import ru.akov.hairn.R;

/**
 * Created by Alexandr on 15.06.2017.
 */

public class MyHolder_selected extends RecyclerView.ViewHolder{
    private static final String TAG = MyHolder_selected.class.getSimpleName();
    public TextView recipeName;

    public MyHolder_selected(View itemView) {
        super(itemView);
        recipeName = (TextView)itemView.findViewById(R.id.tile_text);
        itemView.setClickable(true);
    }
}
