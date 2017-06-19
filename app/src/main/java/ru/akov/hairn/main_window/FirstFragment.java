package ru.akov.hairn.main_window;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DatabaseReference;

import ru.akov.hairn.My_app;
import ru.akov.hairn.R;

/**
 * Created by Alexandr on 12.06.2017.
 */

public class FirstFragment extends Fragment {

    // Store instance variables
    private String title;
    private int page;
    private onSomeEventListener someEventListener;


    public interface onSomeEventListener {
        public void someEvent();
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            Activity activity;
            if (context instanceof Activity){
                activity=(Activity) context;
                someEventListener = (FirstFragment.onSomeEventListener) context;
            }
        }
        catch (ClassCastException e)
        {
            throw new ClassCastException(context.toString()+" must implement onMainMenuListener");
        }
    }


    // newInstance constructor for creating fragment with arguments
    public static FirstFragment newInstance(int page, String title ) {

        FirstFragment fragmentFirst = new FirstFragment();
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
        My_app   app = ((My_app) getActivity().getApplicationContext());

        DatabaseReference m_ref_test = app.getmDatabase().child("shops_types");
       /* m_ref_test.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("dsfsd",dataSnapshot.toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/
        RecyclerView messages = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        myfirebaseRecyclAdapter mAdapter = new myfirebaseRecyclAdapter(String.class,R.layout.item_tile,MyHolder.class,m_ref_test,getContext());
        LinearLayoutManager    linearLayoutManager = new LinearLayoutManager(getActivity());
        messages.setLayoutManager(linearLayoutManager);
        messages.setAdapter(mAdapter);

      /*  Button  newbut = (Button)  view.findViewById(R.id.tosecond);
        newbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                someEventListener.someEvent();
             *//*   FragmentTransaction trans = getFragmentManager()
                        .beginTransaction();
                trans.replace(R.id.root_frame, new ListContentFragment());


                trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                trans.addToBackStack(null);

                trans.commit();*//*
            }
        });
        TextView tvLabel = (TextView) view.findViewById(R.id.mytext1);
        tvLabel.setText(page + " -- " + title);*/
        return view;

    }
}