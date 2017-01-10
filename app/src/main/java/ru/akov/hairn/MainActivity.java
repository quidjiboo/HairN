package ru.akov.hairn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
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
    public void login_out(View view) {

        Status_auth_changes_singltonne.getInstance().logout_action(this,view);
    }
}
