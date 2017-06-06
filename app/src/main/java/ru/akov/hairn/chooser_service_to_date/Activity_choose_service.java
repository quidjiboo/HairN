package ru.akov.hairn.chooser_service_to_date;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

import ru.akov.hairn.My_app;
import ru.akov.hairn.R;

/**
 * Created by User on 01.06.2017.
 */

public class Activity_choose_service extends AppCompatActivity implements TileContentFragment.onSomeEventListener {
    private  TabLayout tabs;
    private ViewPager viewPager;
    private  MyAdapter adapter;
    final static String TAG_1 = "SHOPS_TYPS";
    private My_app app;
    private DatabaseReference m_ref_test;
    private TileContentFragment testfrag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app = ((My_app) getApplicationContext());

        m_ref_test = app.getmDatabase().child("shops_types");


        setContentView(R.layout.choose_type_of_shops);
        // Adding Toolbar to Main screen
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Setting ViewPager for each Tabs
         viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        // Set Tabs inside Toolbar
        tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        Single_simple.getInstance().addlistner(m_ref_test);


    }

    // Add Fragments to Tabs
    private void setupViewPager(ViewPager viewPager) {
        adapter = new MyAdapter(getSupportFragmentManager());
        adapter.addFragment( new ListContentFragment(), "List");
        adapter.addFragment(testfrag = new TileContentFragment(), "Типа услуг");

        viewPager.setAdapter(adapter);
    }
    private void setupViewPager1(ViewPager viewPager, String obj) {
        adapter = new MyAdapter(getSupportFragmentManager());

        adapter.addFragment(testfrag = new TileContentFragment(), "Услуга" + obj);

        viewPager.setAdapter(adapter);
    }


    @Override
    public void someEvent(String s) {
        DatabaseReference  m_ref_test = app.getmDatabase().child("services").child(s);

        setupViewPager1(viewPager, s);
        tabs.setupWithViewPager(viewPager);
        Single_simple.getInstance().addlistner(m_ref_test);
       // adapter.getfrag("List")
        System.out.println(s);
    }

    static class MyAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public MyAdapter(FragmentManager manager) {
            super(manager);
        }

        public Fragment getfrag(String position) {
            return mFragmentList.get(mFragmentTitleList.indexOf(position));
        }
        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
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

}
