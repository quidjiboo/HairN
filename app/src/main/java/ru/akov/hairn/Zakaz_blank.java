package ru.akov.hairn;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.MainThread;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.lang.ref.WeakReference;


public class Zakaz_blank extends AppCompatActivity {

    private My_app app;
    private FirebaseAuth auth;
    private  FirebaseAuth.AuthStateListener mAuthListener;

    EditText mphone;
    EditText mmail;
    EditText mname;

    TextView mUserEmail;
    TextView mUserDisplayName;
    TextView mPhoneNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zakaz_blank);
        mphone = (EditText)findViewById(R.id.mphone_num);
        mname = (EditText)findViewById(R.id.Name_my);
        mmail = (EditText)findViewById(R.id.Mail_my);

        mphone.setInputType(InputType.TYPE_CLASS_PHONE);
        mphone.setText("");
        mphone.append("+7");


        mphone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String formatted;
                String regex1 = "(\\+\\d)(\\d{3})";
                String regex2 = "(.+ )(\\d{3})$";
                String regex3 = "(.+\\-)(\\d{2})$";

                // буду реализвывать ввод телефона в формате +х (ххх) ххх-хх-хх
                if (s.toString().matches(regex1)) {
                    formatted = String.valueOf(s).replaceFirst(regex1, "$1 ($2) ");
                    mphone.setText(formatted);
                    mphone.setSelection(formatted.length());
                } else if (s.toString().matches(regex2)) {
                    formatted = String.valueOf(s).replaceFirst(regex2, "$1$2-");
                    mphone.setText(formatted);
                    mphone.setSelection(formatted.length());
                } else if (s.toString().matches(regex3) && s.length() < 18) {
                    formatted = String.valueOf(s).replaceFirst(regex3, "$1$2-");
                    mphone.setText(formatted);
                    mphone.setSelection(formatted.length());
                }
            }
        });


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

            mmail.setText(
                   TextUtils.isEmpty(user.getEmail()) ? "No email" : user.getEmail());
            mname.setText(
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
