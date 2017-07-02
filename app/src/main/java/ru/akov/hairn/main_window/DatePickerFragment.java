package ru.akov.hairn.main_window;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.roomorama.caldroid.CaldroidFragment;

import java.util.Calendar;

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
        View view = inflater.inflate(R.layout.data_pick_fragment, container, false);
        widget = (MaterialCalendarView)view.findViewById(R.id.calendarView);
        widget.setTileSize(LinearLayout.LayoutParams.MATCH_PARENT);
        widget.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                someEventListener.someEvent2("3");
            }
        });
        return view;

    }
    public  String getTitle(){

        return "dfdsfdsfdgfdghgh";
    }
}