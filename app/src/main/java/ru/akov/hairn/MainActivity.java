package ru.akov.hairn;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.common.GoogleApiAvailability;

public class MainActivity extends AppCompatActivity {
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    private My_app app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        app = ((My_app) getApplicationContext());

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

        Intent intent = new Intent(MainActivity.this,Test_chooser.class);

        startActivity(intent);

        this.finish();
    }

    public void  curent_user_action(View view) {
        if (app.getauth().getCurrentUser() != null) {

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

    @Override
    protected void onResume() {
        super.onResume();
        Log.v("AKOV", "Версия гугля сервиса полностьюЖ!!!" + GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(app));
      int resultCode=GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(app);
        if(resultCode!=0)
        GoogleApiAvailability.getInstance().getErrorDialog(this, resultCode, 1).show();

    }
}
