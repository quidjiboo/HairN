package ru.akov.hairn.main_window;

import android.content.Context;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 05.06.2017.
 */

public class myfirebaseRecyclAdapter_selected_mode extends FirebaseRecyclerAdapter <String, MyHolder_selected>{

    private static final String TAG = myfirebaseRecyclAdapter_selected_mode.class.getSimpleName();
    private Context context;
    private  ArrayList<String> services = null;
    private SparseBooleanArray selectedItems;
    private MyHolder_selected.ClickListener clickListener;
    private ChildEventListener mylist;
    private  DatabaseReference ref;
    public myfirebaseRecyclAdapter_selected_mode(Class<String> modelClass, int modelLayout, Class<MyHolder_selected> viewHolderClass, DatabaseReference ref, Context context,MyHolder_selected.ClickListener clickListener) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        this.ref=ref;
        this.clickListener = clickListener;
        this.context = context;
        Log.d(TAG,"Сделал адаптер");
        services = new ArrayList<>();
        selectedItems = new SparseBooleanArray();
        ref.addChildEventListener(mylist = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                clearSelection();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                clearSelection();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                clearSelection();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                clearSelection();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                clearSelection();
            }
        });
    }

    protected void populateViewHolder(MyHolder_selected viewHolder, String model, int position) {

        Log.d(TAG,"НУ ЧТО БЫ ВИДНО БЛЫО " + getRef(position).getKey().toString());
        viewHolder.recipeName.setText(getRef(position).getKey().toString());


    }

    public MyHolder_selected onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);

        return new MyHolder_selected(view, clickListener);
    }

    public void onBindViewHolder(MyHolder_selected holder, int position) {

        String model = getItem(position);
        populateViewHolder(holder, model, position);
       Log.d("dsfsdf","Ололололо");
        holder.selectedOverlay.setVisibility(isSelected(position) ? View.VISIBLE : View.INVISIBLE);
    }
    public void toggleSelection(int position) {
        if (selectedItems.get(position, false)) {
            selectedItems.delete(position);
        } else {
            selectedItems.put(position, true);
        }
        notifyItemChanged(position);
        services.clear();
        for (int i = 0; i < selectedItems.size(); ++i) {
            services.add(getRef(i).getKey().toString());

        }
Sing_tone_choosings.getInstance().setServices(services);
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
    @Override
    public  void  cleanup(){
       super.cleanup();
        if(mylist!=null)
        ref.removeEventListener(mylist);
    }

}