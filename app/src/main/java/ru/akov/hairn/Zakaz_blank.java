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
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Date;

import ru.akov.hairn.Data_tipes.Clock;
import ru.akov.hairn.Data_tipes.Usluga;


public class Zakaz_blank extends AppCompatActivity implements MyCallback_textwatcher {
    private MtextWatcher textwatcher;
    private My_app app;
    private FirebaseAuth auth;
    private  FirebaseAuth.AuthStateListener mAuthListener;
    private ListView messagesView;
    private EditText mphone;
    private  EditText mmail;
    private EditText mname;

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

        app = ((My_app) getApplicationContext());

        textwatcher = new MtextWatcher();
        textwatcher.registerCallBack(this);
        mphone.addTextChangedListener(textwatcher);


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

        messagesView  = (ListView) findViewById(R.id.list_uslugi);

        FirebaseListAdapter mAdapter = new FirebaseListAdapter<Usluga>(this, Usluga.class, R.layout.list_item, app.getmDatabase().getRef().child("shop").child("test_barber").child("uslugi")) {
            @Override
            protected void populateView(View view, Usluga usluga, int position) {


                    ((TextView) view.findViewById(android.R.id.text1)).setText(usluga.getvalue());


            }

        };


        messagesView.setAdapter(mAdapter);

        messagesView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
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



    @Override
    public void izmenit_text(String date, int number) {
        mphone.setText(date);
        mphone.setSelection(number);
    }
}
