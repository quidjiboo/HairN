package ru.akov.hairn.main_window.zakaz_fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import ru.akov.hairn.MtextWatcher;
import ru.akov.hairn.MyCallback_textwatcher;
import ru.akov.hairn.My_app;
import ru.akov.hairn.R;

/**
 * Created by User on 20.07.2017.
 */

public class ZakazFragment extends Fragment  implements MyCallback_textwatcher {
    private MtextWatcher textwatcher;

    private FirebaseAuth auth;
    private  FirebaseAuth.AuthStateListener mAuthListener;
    private ListView messagesView;
    private EditText mphone;
    private  EditText mmail;
    private EditText mname;

    // Store instance variables
    private String title;
    private int page;



    private My_app app;

    private LatLng mymloc;

    @Override
    public void izmenit_text(String date, int number) {
        mphone.setText(date);
        mphone.setSelection(number);
    }
    // private Toolbar tv;






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



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//Временные переменные , будут переданы при создании фрагмента
        mymloc = new LatLng(31.7853339, -112.4026973);





        View view = inflater.inflate(R.layout.zakaz_blank_fragment, container, false);


        mphone = (EditText)view.findViewById(R.id.mphone_num1);
        mname = (EditText)view.findViewById(R.id.zzName_my);
        mmail = (EditText)view.findViewById(R.id.zzMail_my);
        messagesView  = (ListView)view.findViewById(R.id.zzlist_uslugi);

        mphone.setInputType(InputType.TYPE_CLASS_PHONE);
        mphone.setText("");
        mphone.append("+7");



        textwatcher = new MtextWatcher();
        textwatcher.registerCallBack(this);
        mphone.addTextChangedListener(textwatcher);


        populateProfile();
              return view;

    }
    private void populateProfile() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null) {
            Log.v("AKOV", "MAIL=" + user.getEmail());
            Log.v("AKOV", "NAME=" + user.getDisplayName());

            mmail.setText(
                    TextUtils.isEmpty(user.getEmail()) ? "No email" : user.getEmail());
            mname.setText(
                    TextUtils.isEmpty(user.getDisplayName()) ? "No display name" : user.getDisplayName());


        }}



    public String getTitle() {

        return "DataPickerFragment Фрагмент выбора даты";
    }
}