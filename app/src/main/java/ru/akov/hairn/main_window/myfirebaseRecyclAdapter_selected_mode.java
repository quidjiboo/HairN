package ru.akov.hairn.main_window;

import android.content.Context;
import android.util.Log;
import android.util.SparseBooleanArray;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 05.06.2017.
 */

public class myfirebaseRecyclAdapter_selected_mode extends FirebaseRecyclerAdapter <String, MyHolder_selected>{

    private static final String TAG = myfirebaseRecyclAdapter_selected_mode.class.getSimpleName();
    private Context context;
    private SparseBooleanArray selectedItems;



    public myfirebaseRecyclAdapter_selected_mode(Class<String> modelClass, int modelLayout, Class<MyHolder_selected> viewHolderClass, DatabaseReference ref, Context context) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        this.context = context;
        Log.d(TAG,"Сделал адаптер");
        selectedItems = new SparseBooleanArray();
    }
    @Override
    protected void populateViewHolder(MyHolder_selected viewHolder, String model, int position) {

        Log.d(TAG,"НУ ЧТО БЫ ВИДНО БЛЫО " + getRef(position).getKey().toString());
        viewHolder.recipeName.setText(getRef(position).getKey().toString());


    }

    public void toggleSelection(int position) {
        if (selectedItems.get(position, false)) {
            selectedItems.delete(position);
        } else {
            selectedItems.put(position, true);
        }
        notifyItemChanged(position);
    }
    public void clearSelection() {
        List<Integer> selection = getSelectedItems();
        selectedItems.clear();
        for (Integer i : selection) {
            notifyItemChanged(i);
        }
    }
    public int getSelectedItemCount() {
        return selectedItems.size();
    }
    public List<Integer> getSelectedItems() {
        List<Integer> items = new ArrayList<>(selectedItems.size());
        for (int i = 0; i < selectedItems.size(); ++i) {
            items.add(selectedItems.keyAt(i));
        }
        return items;
    }
    public boolean isSelected(int position) {
        return getSelectedItems().contains(position);
    }
}