package ru.akov.hairn.main_window;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.florent37.singledateandtimepicker.SingleDateAndTimePicker;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.roomorama.caldroid.CaldroidFragment;

import java.util.Calendar;
import java.util.Date;

import ru.akov.hairn.R;

/**
 * Created by Alexandr on 12.06.2017.
 */

public class DatePickerFragment extends Fragment {
    private CaldroidFragment caldroidFragment;
    // Store instance variables
    private String title;
    private int page;
    private MaterialCalendarView widget;
   // private Toolbar tv;
    private onSomeEventListenerDatePickerFragment someEventListener;
    public interface onSomeEventListenerDatePickerFragment {
        public void someEvent2(String fragmentnumber);
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            Activity activity;
            if (context instanceof Activity){
                activity=(Activity) context;
                someEventListener = (DatePickerFragment.onSomeEventListenerDatePickerFragment) context;
            }
        }
        catch (ClassCastException e)
        {
            throw new ClassCastException(context.toString()+" must implement onMainMenuListener");
        }
    }
    // newInstance constructor for creating fragment with arguments
    public static DatePickerFragment newInstance(int page, String title) {
        DatePickerFragment fragmentFirst = new DatePickerFragment();
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



        View view = inflater.inflate(R.layout.time_picker_fragment, container, false);
        SingleDateAndTimePicker dgfds = (SingleDateAndTimePicker)view.findViewById(R.id.fdgdfg) ;
        dgfds.setStepMinutes(30);
        dgfds.setIsAmPm(false);
        dgfds.setMustBeOnFuture(true);
        dgfds.setTextColor(Color.WHITE);
        dgfds.setSelectedTextColor(Color.WHITE);
        dgfds.setSelectorColor(Color.WHITE);

        dgfds.setListener(new SingleDateAndTimePicker.Listener() {
            @Override
            public void onDateChanged(String displayed, Date date) {
                Date cal = (Date) Calendar.getInstance().getTime();

                if (date.after(cal)){
                    Log.d("Время выбрано", displayed);
                Log.d("Время выбрано", date.toString());}
            }
        });

        String[] myDataset = getDataSet();
        RecyclerView mRecyclerView = (RecyclerView) view.findViewById(R.id.testrectimepicker);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        // создаем адаптер
        RecyclerAdapter   mAdapter = new RecyclerAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);
        return view;

    }
    private String[] getDataSet() {

        String[] mDataSet = new String[100];
        for (int i = 0; i < 100; i++) {
            mDataSet[i] = "item" + i;
        }
        return mDataSet;
    }
    public  String getTitle(){

        return "dfdsfdsfdgfdghgh";
    }
}