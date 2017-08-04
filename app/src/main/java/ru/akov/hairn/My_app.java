package ru.akov.hairn;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import ru.akov.hairn.main_window_client.test_shop_typs_singl;


/**
 * Created by User on 21.03.2016.
 */
public class My_app extends Application {
    //ВЫНЕСТИ В СИНГЛ ТОН В АКТИВИТИ!!! ВМЕСТО С ССЫЛКАМИ НА БАЗУ!
    private String fragmentname  = "0";




    private  Context mContext; // забыл зачем нужно, пусть будет

    private boolean flag = true;
    private DatabaseReference mDatabase;
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener mAuthListener;





    @Override
    public void onCreate() {
       super.onCreate();
//        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        // иницализиру сингтоны
        Zakaz_singltone.getInstance();
        Status_auth_changes_singltonne.getInstance();



        mContext = this;

        auth   = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        init();
    }
    private void init(){
        test_shop_typs_singl.initInstance(this);
    }
public FirebaseAuth getauth(){
    return  auth;
}
    public DatabaseReference getmDatabase(){
        return  mDatabase;
    }
    public FirebaseAuth.AuthStateListener  getmAuthListener(){
        return  mAuthListener;
    }

    // вынести в отдельный класс
public void createmAuthListener () {
    if (mAuthListener == null) {
        Log.v("AKOV", "Повесил листер!!!!!");
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null){
                Log.v("AKOV", "!!!!!!!Подключены!!!!!!!!!!" + user.getUid());

                if (user != null    && flag)

                    {
                    //     next_scr(getCurrentFocus());
                        //TODO синг тон надо добавить нвоый
                  //  Status_auth_changes_singltonne.getInstance().Chek_status_online_user_siglevalue_listner(mDatabase, auth.getCurrentUser());

                    flag = false;


                }
                  //  Log.v("AKOV", "!!!!!КОЛБЭК НА ПРОСЛУШКУ БАЗЫ");
                    //TODO подключен
                 //  myCallback.callBackReturn();
                } else {

               //     myCallback.callBackReturnofff();
                    // User is signed out
                    Log.v("AKOV", "НЕ ПОДКЛЮЧЕНЫ");
                }
                // ...
            }
        };
        auth.addAuthStateListener(mAuthListener);
    };


}
    public  Context getContext(){
        return mContext;
    }

    public String getFragmentname() {
        return fragmentname;
    }

    public void setFragmentname(String fragmentname) {
        this.fragmentname = fragmentname;
    }

}
