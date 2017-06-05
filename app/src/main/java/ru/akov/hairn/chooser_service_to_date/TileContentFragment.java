/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ru.akov.hairn.chooser_service_to_date;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import ru.akov.hairn.R;

/**
 * Provides UI for the view with Tiles.
 */
public class TileContentFragment extends Fragment implements Callback_for_Fragments {
private  myRecyclAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      ArrayList<String> myDataset = getDataSet();

        RecyclerView recyclerView = (RecyclerView) inflater.inflate(
                R.layout.recycler_view, container, false);


        adapter   = new myRecyclAdapter();



        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        // Set padding for Tiles
        int tilePadding = getResources().getDimensionPixelSize(R.dimen.tile_padding);
        recyclerView.setPadding(tilePadding, tilePadding, tilePadding, tilePadding);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        Single_simple.getInstance().registerCallBack(this);


        return recyclerView;
    }

    public  ArrayList<String> getDataSet() {
        ArrayList<String> myDataset = new ArrayList<>();
        String[] mDataSet = new String[30];
        for (int i = 0; i < 30; i++) {
            mDataSet[i] = "itdddem" + i;
            myDataset.add(mDataSet[i]);
        }
        return myDataset;
    }


    @Override
    public void addtolist(String obj) {
        adapter.add(obj);

    }

    @Override
    public void removefromlist(String obj) {

    }
}

