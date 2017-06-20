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
import ru.akov.hairn.helpers.ItemClickSupport;

/**
 * Created by Alexandr on 12.06.2017.
 */

public class Fragment_Select_Currect_Services extends Fragment {

    // Store instance variables
    private String servicetype;
    private String title;
    private int page;
    private onSomeEventListener1 someEventListener;


    public interface onSomeEventListener1 {
        public void someEvent(String fragmentnumber);
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
        View view = inflater.inflate(R.layout.recycler_view, container, false);
        My_app   app = ((My_app) getActivity().getApplicationContext());
        Log.d("My Ref","референс к базе подключаю"+servicetype);
        DatabaseReference m_ref_test = app.getmDatabase().child("services").child(servicetype);

        RecyclerView messages = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        myfirebaseRecyclAdapter mAdapter = new myfirebaseRecyclAdapter(String.class,R.layout.item_tile,MyHolder.class,m_ref_test,getContext());
        LinearLayoutManager    linearLayoutManager = new LinearLayoutManager(getActivity());
        messages.setLayoutManager(linearLayoutManager);
        messages.setAdapter(mAdapter);

        ItemClickSupport.addTo(messages).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Log.d("dsfsd","sdfsdfsdhgfhgfhgf");
                Log.d("dsfdsf", ((myfirebaseRecyclAdapter) recyclerView.getAdapter()).getItem(position));
                Log.d("d1112344f",   ((myfirebaseRecyclAdapter) recyclerView.getAdapter()).getRef(position).getKey().toString());

                // do it
            }
        });
        return view;

    }
}