package ru.akov.hairn;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.MainThread;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class Zakaz_blank extends AppCompatActivity {

    private My_app app;
    private FirebaseAuth auth;
    private  FirebaseAuth.AuthStateListener mAuthListener;


    TextView mUserEmail;

    TextView mUserDisplayName;
    TextView mPhoneNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zakaz_blank);

        mUserEmail = (TextView)findViewById(R.id.user_email);
        mUserDisplayName = (TextView)findViewById(R.id.user_display_name);
        mPhoneNumber = (TextView)findViewById(R.id.phonenumber);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        populateProfile();
    }
    @MainThread
    private void populateProfile() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null) {
            Log.v("AKOV", "MAIL=" + user.getEmail());
            Log.v("AKOV", "NAME=" + user.getDisplayName());

          mUserEmail.setText(
                   TextUtils.isEmpty(user.getEmail()) ? "No email" : user.getEmail());
            mUserDisplayName.setText(
                   TextUtils.isEmpty(user.getDisplayName()) ? "No display name" : user.getDisplayName());


        }}
    @Override
    public void onBackPressed() {
        // super.onBackPressed();
        Intent intent = new Intent(Zakaz_blank.this,Test_chooser.class);

        startActivity(intent);

        this.finish();
    }

}
