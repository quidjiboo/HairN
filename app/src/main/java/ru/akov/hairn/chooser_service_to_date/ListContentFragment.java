package ru.akov.hairn.chooser_service_to_date;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.akov.hairn.R;

/**
 * Created by User on 01.06.2017.
 */

public class ListContentFragment  extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.item_list, null);
    }
}

