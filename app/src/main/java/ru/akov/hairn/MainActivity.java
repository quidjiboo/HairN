package ru.akov.hairn;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.GoogleApiAvailability;
import com.google.firebase.iid.FirebaseInstanceId;

import ru.akov.hairn.listesting.list_test;
import ru.akov.hairn.main_window.Activity_main_choosing_tabs;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    private My_app app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        app = ((My_app) getApplicationContext());

        Button logTokenButton = (Button) findViewById(R.id.logTokenButton);
        logTokenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get token
                String token = FirebaseInstanceId.getInstance().getToken();

                // Log and toast
                String msg = getString(R.string.msg_token_fmt, token);
                Log.d(TAG, msg);
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void login_action(View view) {

        Status_auth_changes_singltonne.getInstance().login_action(app.getauth(),this);
    }
    public void new_test_workdays(View view) {
        Shop_creator creator = new Shop_creator();
        creator.add_test_workdays(app.getmDatabase(),app.getauth().getCurrentUser());

    }

    public void new_shop(View view) {
Shop_creator creator = new Shop_creator();
        creator.add_default_barbeshop(app.getmDatabase(),app.getauth().getCurrentUser());
    }
    public void login_out(View view) {

        Status_auth_changes_singltonne.getInstance().logout_action(this,view);
    }

    public void test_choose_day(View view) {

        Intent intent = new Intent(MainActivity.this,Date_chooser_main.class);

        startActivity(intent);

        this.finish();
    }


    public void add_list_view_test(View view) {

        Intent intent = new Intent(MainActivity.this,list_test.class);

        startActivity(intent);

        this.finish();
    }
    public void newLive(View view) {
      //  Intent intent = new Intent(MainActivity.this,     Activity_choose_service.class);

       Intent intent = new Intent(MainActivity.this,Activity_main_choosing_tabs.class);

        startActivity(intent);

        this.finish();
    }

    public void  curent_user_action(View view) {
        if (app.getauth().getCurrentUser() != null) {
            Log.v(TAG, "getDisplayName = " +  app.getauth().getCurrentUser().getDisplayName());
            Log.v(TAG, "getEmail = " +  app.getauth().getCurrentUser().getEmail());
            Log.v(TAG, "getPhotoUrl = " +  app.getauth().getCurrentUser().getPhotoUrl());
            //    auth = FirebaseAuth.getInstance();
            Snackbar.make(view,app.getauth().getCurrentUser().toString(), Snackbar.LENGTH_LONG).show();
        } else {
            Snackbar.make(view,"Вы не залогинены", Snackbar.LENGTH_LONG).show();
            // not signed in
        }
    }
    public void  all_days(View view) {
        Shop_creator creator = new Shop_creator();
        creator.get_days_ofmouth1();
    }
    public void  creat_user(View view) {
        User_creator creator = new User_creator();
        creator.add_user(app.getmDatabase(),app.getauth().getCurrentUser());
    }

    public void  add_defaults(View view) {
        Shop_creator creator = new Shop_creator();
        creator.add_defaults(app.getmDatabase());
    }
    public void  add_Nady_barbeshop_text_first(View view) {
        Shop_creator creator = new Shop_creator();
        creator.add_Nady_barbeshop_text_first(app.getmDatabase(),app.getauth().getCurrentUser());
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v("AKOV", "Версия гугля сервиса полностьюЖ!!!" + GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(app));
      int resultCode=GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(app);
        if(resultCode!=0)
        GoogleApiAvailability.getInstance().getErrorDialog(this, resultCode, 1).show();

    }
}
