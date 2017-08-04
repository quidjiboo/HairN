package ru.akov.hairn.main_window_client.data_pick_fragment;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pascalwelsch.arrayadapter.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import ru.akov.hairn.R;
import ru.akov.hairn.listesting.DATA.GPScoords_price;
import ru.akov.hairn.main_window_client.Sing_tone_choosings;

/**
 * Created by User on 06.07.2017.
 */

public class DataArrayAdapter extends ArrayAdapter<GPScoords_price, DataArrayAdapter.ViewHolder> {
    private SparseBooleanArray selectedItems;
    private ViewHolder.ClickListener clickListener;
    public DataArrayAdapter(ViewHolder.ClickListener clickListener) {
        super();
        selectedItems = new SparseBooleanArray();
        this.clickListener = clickListener;
    }
    public boolean isSelected(int position) {
        return getSelectedItems().contains(position);
    }

    /**
     * Toggle the selection status of the item at a given position
     * @param position Position of the item to toggle the selection status for
     */
    public void toggleSelection(int position) {
        clearSelection();
       if (selectedItems.get(position, false)) {
            selectedItems.delete(position);
        } else {
            selectedItems.put(position, true);
        }

        notifyItemChanged(position);
        Sing_tone_choosings.getInstance().setShopid(this.getItem(position).getkey());
        Sing_tone_choosings.getInstance().setShopname(this.getItem(position).getname());
    }

    /**
     * Clear the selection status for all items
     */
    public void clearSelection() {
        List<Integer> selection = getSelectedItems();
        selectedItems.clear();
        for (Integer i : selection) {
            notifyItemChanged(i);
        }
    }

    /**
     * Count the selected items
     * @return Selected items count
     */
    public int getSelectedItemCount() {
        return selectedItems.size();
    }

    /**
     * Indicates the list of selected items
     * @return List of selected items ids
     */
    public List<Integer> getSelectedItems() {
        List<Integer> items = new ArrayList<>(selectedItems.size());
        for (int i = 0; i < selectedItems.size(); ++i) {
            items.add(selectedItems.keyAt(i));
        }
        return items;
    }


public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,
        View.OnLongClickListener{
    private ClickListener listener;
    View selectedOverlay;
    private final TextView titleView;
    private final TextView price;

    public ViewHolder(View itemView, ClickListener listener) {
        super(itemView);
        itemView.setClickable(true);
        selectedOverlay = itemView.findViewById(R.id.selected_overlay1);
        titleView = (TextView) itemView.findViewById(R.id.txtTitle2);
        price = (TextView) itemView.findViewById(R.id.txtTitle1);
        this.listener = listener;
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }



    @Override
    public void onClick(View v) {
        if (listener != null) {
            listener.onItemClicked(getPosition());
        }
    }

    @Override
    public boolean onLongClick(View v) {
        if (listener != null) {
            return listener.onItemLongClicked(getPosition());
        }

        return false;
    }

    public interface ClickListener {
        public void onItemClicked(int position);
        public boolean onItemLongClicked(int position);
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
        String total2 = String.valueOf(item.getprice());
        holder.price.setText(total2);
        holder.selectedOverlay.setVisibility(isSelected(position) ? View.VISIBLE : View.INVISIBLE);
        Log.d("ОЛОЛОЛО", "ололо выао лывоа дывлопалдвор лдпора " + isSelected(position));
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.test_card, parent, false);
        return new ViewHolder(view, clickListener);
    }
}