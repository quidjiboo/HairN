package ru.akov.hairn.main_window;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DatabaseReference;

import ru.akov.hairn.My_app;
import ru.akov.hairn.R;

/**
 * Created by Alexandr on 12.06.2017.
 */

public class Fragment_Select_Currect_Services extends Fragment implements MyHolder_selected.ClickListener {

    // Store instance variables
    private myfirebaseRecyclAdapter_selected_mode mAdapter;
    private String servicetype;
    private String title;
    private int page;
    private onSomeEventListener1 someEventListener;

    @Override
    public void onItemClicked(int position) {
Log.d("ПОЗИЦИЯ", position +  " " );

            toggleSelection(position);


    }
    private void toggleSelection(int position) {
        mAdapter.toggleSelection(position);
        Log.d("КАКАЯ АКТИВНОСТЬ", getActivity().findViewById(R.id.fab1).isShown() +  "" );
// getActivity().findViewById(R.id.fab1).

        someEventListener.someEvent1("sdfsdf");
    }

    @Override
    public boolean onItemLongClicked(int position) {
        return false;
    }


    public interface onSomeEventListener1 {
        public void someEvent1(String fragmentnumber);
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            Activity activity;
            if (context instanceof Activity){
                activity=(Activity) context;
                someEventListener = (Fragment_Select_Currect_Services.onSomeEventListener1) context;

            }
        }
        catch (ClassCastException e)
        {
            throw new ClassCastException(context.toString()+" must implement onMainMenuListener");
        }
    }


    // newInstance constructor for creating fragment with arguments
    public static Fragment_Select_Currect_Services newInstance(int page, String title, String servicetype ) {

        Fragment_Select_Currect_Services fragmentFirst = new Fragment_Select_Currect_Services();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        args.putString("servicetype", servicetype);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt", 0);
        title = getArguments().getString("someTitle");
        servicetype =  getArguments().getString("servicetype");
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycler_view_2, container, false);
        My_app   app = ((My_app) getActivity().getApplicationContext());
        Log.d("My Ref","референс к базе подключаю"+servicetype);
        DatabaseReference m_ref_test = app.getmDatabase().child("services").child(servicetype);

        RecyclerView messages = (RecyclerView) view.findViewById(R.id.my_recycler_view_2);


        mAdapter = new myfirebaseRecyclAdapter_selected_mode(String.class,R.layout.item_tile_marked,MyHolder_selected.class,m_ref_test,getContext(),this);
        LinearLayoutManager    linearLayoutManager = new LinearLayoutManager(getActivity());
        messages.setAdapter(mAdapter);
        messages.setLayoutManager(linearLayoutManager);
        return view;

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        mAdapter.cleanup();
    }

}