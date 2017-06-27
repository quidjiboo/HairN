package ru.akov.hairn.main_window;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.database.DatabaseReference;

import ru.akov.hairn.MainActivity;
import ru.akov.hairn.My_app;
import ru.akov.hairn.R;

/**
 * Created by User on 01.06.2017.
 */

public class Activity_main_choosing_tabs extends AppCompatActivity
        implements FirstFragment_Select_Service_Type.onSomeEventListener , Fragment_Select_Currect_Services.onSomeEventListener1{
    final static String TAG = "MAIN_ACTIVITY";
    private TabLayout tabs;
    private ViewPager viewPager;
    private MyPagerAdapter adapter;
    private My_app app;
    private DatabaseReference m_ref_test;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app = ((My_app) getApplicationContext());

        m_ref_test = app.getmDatabase().child("shops_types");

        setContentView(R.layout.activity_chooser_main);
        // Adding Toolbar to Main screen
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Setting ViewPager for each Tabs
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        // Set Tabs inside Toolbar
        tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            // This method will be invoked when a new page becomes selected.
            @Override
            public void onPageSelected(int position) {
           /*     Toast.makeText(Activity_main_choosing_tabs.this,
                        "Selected page position: " + position, Toast.LENGTH_SHORT).show();*/
                //А вот тут надо подумать!! при поворроте не может отработать!!!
                //         Log.d(TAG,"ПЕРЕЛИСТЫВАЮ!! "  + adapter.getRegisteredFragment(viewPager.getCurrentItem()).getArguments()) ;
            }

            // This method will be invoked when the current page is scrolled
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // Code goes here
            }
            // Called when the scroll state changes:
            // SCROLL_STATE_IDLE, SCROLL_STATE_DRAGGING, SCROLL_STATE_SETTLING
            @Override
            public void onPageScrollStateChanged(int state) {
                // Code goes here
            }
        });

    }
    @Override
    public void onBackPressed() {

        if(app.getFragmentname().contains("1")){
            someEvent("0");
        }
        else {
            super.onBackPressed();
        Intent intent = new Intent(Activity_main_choosing_tabs.this, MainActivity.class);
        startActivity(intent);
        this.finish();}

    }

    // Add Fragments to Tabs
    private void setupViewPager(ViewPager viewPager) {
        adapter = new MyPagerAdapter(getSupportFragmentManager(), app);
        viewPager.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void someEvent(String fragmentnumber) {
String fn= fragmentnumber;
        app.setFragmentname(fn);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void someEvent1(String fragmentnumber) {

    }
}
