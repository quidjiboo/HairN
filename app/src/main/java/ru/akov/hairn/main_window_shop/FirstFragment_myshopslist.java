package ru.akov.hairn.main_window_shop;

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

import ru.akov.hairn.R;
import ru.akov.hairn.helpers.ItemClickSupport;
import ru.akov.hairn.main_window_client.myfirebaseRecyclAdapter;
import ru.akov.hairn.main_window_client.test_shop_typs_singl;

/**
 * Created by Alexandr on 12.06.2017.
 */

public class FirstFragment_myshopslist extends Fragment {

    // Store instance variables
    private String title;
    private int page;
    private onSomeEventListener someEventListener;


    public interface onSomeEventListener {
        public void someEvent(String fragmentnumber);
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            Activity activity;
            if (context instanceof Activity){
                activity=(Activity) context;
                someEventListener = (FirstFragment_myshopslist.onSomeEventListener) context;
            }
        }
        catch (ClassCastException e)
        {
            throw new ClassCastException(context.toString()+" must implement onMainMenuListener");
        }
    }


    // newInstance constructor for creating fragment with arguments
    public static FirstFragment_myshopslist newInstance(int page, String title ) {

        FirstFragment_myshopslist fragmentFirst = new FirstFragment_myshopslist();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt", 0);
        title = getArguments().getString("someTitle");
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycler_view, container, false);
       // final My_app   app = ((My_app) getActivity().getApplicationContext());

       // DatabaseReference m_ref_test = app.getmDatabase().child("shops_types");

        RecyclerView messages = (RecyclerView) view.findViewById(R.id.my_recycler_view);
      //  myfirebaseRecyclAdapter mAdapter = new myfirebaseRecyclAdapter(String.class,R.layout.item_tile,MyHolder.class,m_ref_test,getContext());
        LinearLayoutManager    linearLayoutManager = new LinearLayoutManager(getActivity());
        messages.setLayoutManager(linearLayoutManager);
        messages.setAdapter(test_shop_typs_singl.getInstance().getmAdapter_myshops_list());

        ItemClickSupport.addTo(messages).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {

                Shop_singltone.getInstance().add_data(((myfirebaseRecyclAdapter) recyclerView.getAdapter()).getRef(position).getKey());
                Log.d("d1112344f",   (((myfirebaseRecyclAdapter) recyclerView.getAdapter()).getItem(position)));
             //   Log.d("d1112344f",   ((TextView)v.findViewById(R.id.textViewvalue).);
                //TODO ТОТОТОТОТОТОТ
                someEventListener.someEvent("1");

            }
        });
        return view;

    }




}