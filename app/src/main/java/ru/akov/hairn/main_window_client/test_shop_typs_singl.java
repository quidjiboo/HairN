package ru.akov.hairn.main_window_client;

import android.util.Log;

import com.google.firebase.database.DatabaseReference;

import ru.akov.hairn.Data_tipes.Zakaz_shop_second_fragment;
import ru.akov.hairn.My_app;
import ru.akov.hairn.R;
import ru.akov.hairn.main_window_shop.MyHolder_zakazi;
import ru.akov.hairn.main_window_shop.myfirebaseRecyclAdapter_zakazi_shop;

/**
 * Created by User on 20.07.2017.
 */

public class test_shop_typs_singl {
    //TODO
    //ЗАдавать при инициализации первого фрагмента , щас оставил подефолту
    private  String current_tipe_of_shop="barbershops";



    private  String current_shop_id="";
    private myfirebaseRecyclAdapter_zakazi_shop mAdapter_my_shops_zakazi;
    private myfirebaseRecyclAdapter mAdapter_service_type;
    private myfirebaseRecyclAdapter mAdapter_my_shops;
    private DatabaseReference my_shops_ref;
    private DatabaseReference shop_typs_ref;
    private DatabaseReference zakazi_my_shop;
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

    public myfirebaseRecyclAdapter getmAdapter_service_type() {
        if(mAdapter_service_type ==null)
            mAdapter_service_type = new myfirebaseRecyclAdapter(String.class, R.layout.item_tile,MyHolder.class, shop_typs_ref,app.getContext());
        return mAdapter_service_type;
    }
    public myfirebaseRecyclAdapter getmAdapter_myshops_list() {
        if(mAdapter_my_shops ==null)
            mAdapter_my_shops = new myfirebaseRecyclAdapter(String.class, R.layout.item_tile,MyHolder.class, my_shops_ref,app.getContext());
        return mAdapter_my_shops;
    }
    public myfirebaseRecyclAdapter_zakazi_shop getmAdapter_inmyshops_list_zakazi() {
        if(mAdapter_my_shops_zakazi ==null)
            mAdapter_my_shops_zakazi = new myfirebaseRecyclAdapter_zakazi_shop(Zakaz_shop_second_fragment.class, R.layout.item_tile,MyHolder_zakazi.class, zakazi_my_shop);
        return mAdapter_my_shops_zakazi;
    }
    public void cleanadapter(){
        Log.d("Фрагмент", "отсоединил слушателя адпетр");
        mAdapter_service_type.cleanup();
    }

    test_shop_typs_singl(My_app appl){
          app = appl;
        zakazi_my_shop=app.getmDatabase().child("barbershops").child("-KolDBkidNrRySkXn5Oh").child("zakazi");
     //   zakazi_my_shop=app.getmDatabase().child(current_tipe_of_shop).child(current_shop_id).child("zakazi");
        my_shops_ref=app.getmDatabase().child("users").child(app.getauth().getCurrentUser().getUid()).child("myshops");
        shop_typs_ref = app.getmDatabase().child("shops_types");
        mAdapter_service_type = new myfirebaseRecyclAdapter(String.class, R.layout.item_tile,MyHolder.class, shop_typs_ref,app.getContext());
    }

    //TODO
    //блин вот это не забыть релаизовать!!!
    public void set_zakazi_my_shop (String typeofshop, String key) {
        typeofshop = "barbershops";
        key = "-KolDBkidNrRySkXn5Oh";
        zakazi_my_shop = app.getmDatabase().child(typeofshop).child(key).child("zakazi");

    }
    public void setCurrent_shop_id(String current_shop_id) {
        this.current_shop_id = current_shop_id;
    }
}
