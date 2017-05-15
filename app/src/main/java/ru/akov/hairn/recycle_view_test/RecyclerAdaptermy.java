package ru.akov.hairn.recycle_view_test;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.net.URI;
import java.util.ArrayList;
import java.util.UUID;

import ru.akov.hairn.My_app;
import ru.akov.hairn.R;


public class RecyclerAdaptermy extends RecyclerView.Adapter<RecyclerAdaptermy.ViewHolder> {

    private Mecycle_vew_test context;
    private ArrayList<String> mDataset;

    // класс view holder-а с помощью которого мы получаем ссылку на каждый элемент
    // отдельного пункта списка
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // наш пункт состоит только из одного TextView
        public TextView mTextView;
        public ImageView mimageView;
        public ViewHolder(View v) {
            super(v);
            mTextView = (TextView) v.findViewById(R.id.tv_recycler_item);
            mimageView  = (ImageView) v.findViewById(R.id.imageView2);
        }
    }

    // Конструктор
    public RecyclerAdaptermy(Context context,ArrayList<String> dataset) {

        this.context = (Mecycle_vew_test) context;
        mDataset  = new ArrayList<String>();
        mDataset=dataset;
    }

    // Создает новые views (вызывается layout manager-ом)
    @Override
    public RecyclerAdaptermy.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                           int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item, parent, false);

        // тут можно программно менять атрибуты лэйаута (size, margins, paddings и др.)

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Заменяет контент отдельного view (вызывается layout manager-ом)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.mTextView.setText(mDataset.get(position));

        Glide.with(context)
                .load("https://firebasestorage.googleapis.com/v0/b/test-base-soc-net.appspot.com/o/defaultshop.png?alt=media&token=92cc5bdb-bb0d-4a03-a292-da6ef5eb622d")
                .into(holder.mimageView);


    }

    // Возвращает размер данных (вызывается layout manager-ом)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}