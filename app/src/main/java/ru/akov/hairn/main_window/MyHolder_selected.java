package ru.akov.hairn.main_window;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import ru.akov.hairn.R;

/**
 * Created by Alexandr on 15.06.2017.
 */

public class MyHolder_selected extends RecyclerView.ViewHolder implements View.OnClickListener,
        View.OnLongClickListener{
    private static final String TAG = MyHolder_selected.class.getSimpleName();
    public TextView recipeName;
    View selectedOverlay;
    private ClickListener listener;


    public MyHolder_selected(View itemView, ClickListener listener) {
        super(itemView);
        recipeName = (TextView)itemView.findViewById(R.id.tile_text_marked);
        selectedOverlay = itemView.findViewById(R.id.selected_overlay);
      //  itemView.setClickable(true);

        this.listener = listener;

        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (listener != null) {
            listener.onItemClicked(getAdapterPosition());
        }

    }

    @Override
    public boolean onLongClick(View v) {
        if (listener != null) {
            return listener.onItemLongClicked(getAdapterPosition());
        }

        return false;
    }
    public interface ClickListener {
        public void onItemClicked(int position);
        public boolean onItemLongClicked(int position);
    }
}
