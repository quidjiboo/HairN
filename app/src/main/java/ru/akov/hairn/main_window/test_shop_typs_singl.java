package ru.akov.hairn.main_window;

import android.util.Log;

import com.google.firebase.database.DatabaseReference;

import ru.akov.hairn.My_app;
import ru.akov.hairn.R;

/**
 * Created by User on 20.07.2017.
 */

public class test_shop_typs_singl {
    private myfirebaseRecyclAdapter mAdapter;
    private DatabaseReference m_ref_test;
    private My_app app;
    private static test_shop_typs_singl instance;


    public static void initInstance(My_app appl) {

        if (instance == null) {
            instance = new test_shop_typs_singl(appl);
        }
    }

    public static test_shop_typs_singl getInstance() {

        return instance;
    }

    public myfirebaseRecyclAdapter getmAdapter() {
        if(mAdapter==null)
            mAdapter = new myfirebaseRecyclAdapter(String.class, R.layout.item_tile,MyHolder.class,m_ref_test,app.getContext());
        return mAdapter;
    }
    public void cleanadapter(){
        Log.d("Фрагмент", "отсоединил слушателя адпетр");
        mAdapter.cleanup();
    }

    test_shop_typs_singl(My_app appl){
          app = appl;
        m_ref_test = app.getmDatabase().child("shops_types");
        mAdapter = new myfirebaseRecyclAdapter(String.class, R.layout.item_tile,MyHolder.class,m_ref_test,app.getContext());
    }

}
