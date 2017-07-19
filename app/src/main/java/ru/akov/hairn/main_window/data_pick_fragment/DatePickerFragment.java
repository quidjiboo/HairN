package ru.akov.hairn.main_window.data_pick_fragment;

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
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.DatabaseReference;
import com.google.maps.android.SphericalUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import ru.akov.hairn.My_app;
import ru.akov.hairn.R;
import ru.akov.hairn.listesting.DATA.GPScoords_price;
import ru.akov.hairn.main_window.Sing_tone_choosings;

/**
 * Created by Alexandr on 12.06.2017.
 */

public class DatePickerFragment extends Fragment implements MyCallbacl_refresherlist_for_fragment, DataArrayAdapter.ViewHolder.ClickListener {

    // Store instance variables
    private String title;
    private int page;

    private SingleDateAndTimePicker mydateAndTimePicker;
    private ArrayList<GPScoords_price> arra_for_listvieew_price;
    private My_app app;
    private DataArrayAdapter dataarrayadapter;
    private LatLng mymloc;
    // private Toolbar tv;
    private onSomeEventListenerDatePickerFragment someEventListener;

    @Override
    public void onItemClicked(int position) {
        toggleSelection(position);
    }

    @Override
    public boolean onItemLongClicked(int position) {
        return false;
    }
    private void toggleSelection(int position) {
        dataarrayadapter.toggleSelection(position);
        someEventListener.someEvent2(Sing_tone_choosings.getInstance().getShopid());

    }
    public interface onSomeEventListenerDatePickerFragment {
        public void someEvent2(String fragmentnumber);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            Activity activity;
            if (context instanceof Activity) {
                activity = (Activity) context;
                someEventListener = (DatePickerFragment.onSomeEventListenerDatePickerFragment) context;
            }
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement onMainMenuListener");
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
    private void settimeinsingl() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//Временные переменные , будут переданы при создании фрагмента
        mymloc = new LatLng(31.7853339, -112.4026973);

        String date = "20170602";
        String time = "08_00";

        View view = inflater.inflate(R.layout.time_picker_fragment, container, false);
        mydateAndTimePicker = (SingleDateAndTimePicker) view.findViewById(R.id.fdgdfg);

        mydateAndTimePicker.setStepMinutes(30);

        mydateAndTimePicker.setIsAmPm(false);
        mydateAndTimePicker.setMustBeOnFuture(true);
        mydateAndTimePicker.setTextColor(Color.WHITE);
        mydateAndTimePicker.setSelectedTextColor(Color.WHITE);
        mydateAndTimePicker.setSelectorColor(Color.WHITE);




        mydateAndTimePicker.setListener(new SingleDateAndTimePicker.Listener() {
            @Override
            public void onDateChanged(String displayed, Date date) {
                Date cal = (Date) Calendar.getInstance().getTime();

                if (date.after(cal)) {

                    Log.d("Время выбрано", displayed);
                    Log.d("Время выбрано", date.toString());

                    final String month = (String) android.text.format.DateFormat.format("MM", date); //06
                    final String year = (String) android.text.format.DateFormat.format("yyyy", date); //2013
                    final String day = (String) android.text.format.DateFormat.format("dd", date);

                    final String hour = (String) android.text.format.DateFormat.format("kk", date);
                    final String minet = (String) android.text.format.DateFormat.format("mm", date);
                    final String zapis = year + month + day;
                    Log.d("Время выбрано", zapis + hour + "_" + minet);
                    Sing_tone_choosings.getInstance().setDate(zapis);
                    Sing_tone_choosings.getInstance().setTime(hour + "_" + minet);

                    dataarrayadapter.clear();
                    DatabaseReference m_service_ref = app.getmDatabase().child("locations_names").child(Sing_tone_choosings.getInstance().getLocation()).child(Sing_tone_choosings.getInstance().getTypes_of_shops());
                    DatabaseReference m_block_date_time = app.getmDatabase().
                            child(Sing_tone_choosings.getInstance().getDate()).
                            child(Sing_tone_choosings.getInstance().getTime()).
                            child(Sing_tone_choosings.getInstance().getTypes_of_shops()).
                            child(Sing_tone_choosings.getInstance().getLocation());

                    Single_tone_array_creator_fragment.getInstance().addlistner_location_sort_arraylist(m_service_ref, mymloc, m_block_date_time);

                }
            }
        });


       RecyclerView mRecyclerView = (RecyclerView) view.findViewById(R.id.testrectimepicker);


        // создаем адаптер
        //   simpleRecyclerAdapter mAdapter = new simpleRecyclerAdapter(myDataset);
        //    mRecyclerView.setAdapter(mAdapter);

        app = ((My_app) getActivity().getApplicationContext());
        arra_for_listvieew_price = new ArrayList<>();
        dataarrayadapter = new DataArrayAdapter(this);
        mRecyclerView.setAdapter(dataarrayadapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(linearLayoutManager);



        DatabaseReference m_service_ref = app.getmDatabase().child("locations_names").child(Sing_tone_choosings.getInstance().getLocation()).child(Sing_tone_choosings.getInstance().getTypes_of_shops());
        DatabaseReference m_block_date_time = app.getmDatabase().
                child(Sing_tone_choosings.getInstance().getDate()).
                child(Sing_tone_choosings.getInstance().getTime()).
                child(Sing_tone_choosings.getInstance().getTypes_of_shops()).
                child(Sing_tone_choosings.getInstance().getLocation());

        Single_tone_array_creator_fragment.getInstance().addlistner_location_sort_arraylist(m_service_ref, mymloc, m_block_date_time);
        Single_tone_array_creator_fragment.getInstance().registerCallBack(this);




        return view;

    }


    @Override
    synchronized public void addtolist(GPScoords_price obj) {

        LatLng mloc = new LatLng(obj.getlatitude(), obj.getlongitude());
        double mdist = SphericalUtil.computeDistanceBetween(mloc, mymloc);
        obj.setmdist(mdist);
        dataarrayadapter.add(obj);
        dataarrayadapter.notifyDataSetChanged();

    }

    @Override
    synchronized public void removefromlist(String obj) {
        GPScoords_price orig = null;
        for (int i = 0; i < dataarrayadapter.getItemCount(); i++) {
            orig = dataarrayadapter.getItem(i);
            if (orig.getkey().contains(obj)) {
                dataarrayadapter.remove(orig);
                dataarrayadapter.notifyDataSetChanged();
            }
        }

    }

    public String getTitle() {

        return "DataPickerFragment Фрагмент выбора даты";
    }
}