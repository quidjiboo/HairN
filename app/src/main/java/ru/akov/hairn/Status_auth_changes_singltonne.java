package ru.akov.hairn;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;


/**
 * Created by Alexandr on 09.07.2016.
 */
 public class Status_auth_changes_singltonne {
    private static Status_auth_changes_singltonne instance;

    private Status_auth_changes_singltonne(){

    }
    public static synchronized Status_auth_changes_singltonne getInstance() {
        if(instance==null){
            instance = new Status_auth_changes_singltonne();   /// спорное решение !!!
        }
        return instance;
    }







    public   void login_action(FirebaseAuth auth, Activity activity) {

        if(auth.getCurrentUser() == null){

            activity.startActivityForResult(
                    AuthUI.getInstance().createSignInIntentBuilder()
                            .setTheme(AuthUI.getDefaultTheme())
                            .setProviders(AuthUI.GOOGLE_PROVIDER)
                            .setTosUrl("https://www.google.com/policies/terms/")
                            .build(),
                    100);}
        else {   Log.v("AKOV", "ЗАЛОГИНИН ПОЛЬЗОВАТЕЛЬ " + auth.getCurrentUser()) ;}


    }

    public    void logout_action(Activity activity, final View view) {


        AuthUI.getInstance()
                .signOut(activity)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.v("AKOV", "РАЗЛОГИНИЛСЯ!!! ");
                            Snackbar.make(view,"разлогинились", Snackbar.LENGTH_LONG).show();

                        } else {
                            Log.v("AKOV", "ЧТО ТО НЕ РАЗЛОГИНИВАЕТСЯ ");

                        }
                    }
                });
    }


}
