package ru.akov.hairn;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

/**
 * Created by User on 30.01.2017.
 */

public class List_time_pick {
    MyCallback myCallback;
    public void registerCallBack(MyCallback callback) {
        this.myCallback = callback;
    }
    public AdapterView.OnItemClickListener createlistner(){
        AdapterView.OnItemClickListener listener = new  AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

               myCallback.vibral_vremia(((TextView) view).getText().toString());
                Log.v("AKOV", " Время " + ((TextView) view).getText().toString());

            }

                  };


        return listener;
    }


}
