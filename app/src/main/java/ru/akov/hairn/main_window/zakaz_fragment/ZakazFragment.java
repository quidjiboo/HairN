package ru.akov.hairn.main_window.zakaz_fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.model.LatLng;

import ru.akov.hairn.My_app;
import ru.akov.hairn.R;

/**
 * Created by User on 20.07.2017.
 */

public class ZakazFragment extends Fragment{

    // Store instance variables
    private String title;
    private int page;



    private My_app app;

    private LatLng mymloc;
    // private Toolbar tv;




    public interface onSomeEventListenerDatePickerFragment {
        public void someEvent3(String fragmentnumber);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            Activity activity;
            if (context instanceof Activity) {
                activity = (Activity) context;

            }
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement onMainMenuListener");
        }
    }

    // newInstance constructor for creating fragment with arguments
    public static ZakazFragment newInstance(int page, String title) {
        ZakazFragment fragmentFirst = new ZakazFragment();
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


        View view = inflater.inflate(R.layout.zakaz_blank_fragment, container, false);
              return view;

    }




    public String getTitle() {

        return "DataPickerFragment Фрагмент выбора даты";
    }
}