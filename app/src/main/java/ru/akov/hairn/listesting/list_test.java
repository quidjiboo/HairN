package ru.akov.hairn.listesting;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import ru.akov.hairn.MainActivity;
import ru.akov.hairn.My_app;
import ru.akov.hairn.R;

public class list_test extends AppCompatActivity {
    private My_app app;
    mFirebaseArray marra;
    String key="000";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app = ((My_app) getApplicationContext());
        setContentView(R.layout.activity_list_test);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Button mbutton = (Button) findViewById(R.id.button17);
        Button addbutton = (Button) findViewById(R.id.button333);
        Button addbutton9 = (Button) findViewById(R.id.button1999);
        ListView mlistView = (ListView) findViewById(R.id.mlist);
        final ArrayList<String> catnames = new ArrayList<String>();

        final ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, catnames);
        // Привяжем массив через адаптер к ListView
        mlistView.setAdapter(adapter);

        mbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                catnames.add("lalal");
                if(catnames.size()>=14 && catnames.get(3)!=null){
                    catnames.set(3,"dsfdsfsdf");
                }
                adapter.notifyDataSetChanged();
                System.out.println(marra.getCount());
            }
        });
        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(key.contains("000")){
                    key=app.getmDatabase().child("test").push().getKey();
               app.getmDatabase().child("test").child(key).setValue(true);}
                app.getmDatabase().child("test").push().setValue(true);
            }
        });
        addbutton9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                app.getmDatabase().child("test2").child(key).setValue(true);
            }
        });

        marra  = new mFirebaseArray(app.getmDatabase().child("test"),app.getmDatabase().child("test2"));
        System.out.println(marra.getCount());
    }
    @Override
    public void onBackPressed() {
        // super.onBackPressed();

        Intent intent = new Intent(list_test.this, MainActivity.class);

        startActivity(intent);

        this.finish();
    }
}
