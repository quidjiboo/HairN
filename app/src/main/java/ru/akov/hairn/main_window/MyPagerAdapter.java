package ru.akov.hairn.main_window;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import ru.akov.hairn.My_app;

/**
 * Created by Alexandr on 12.06.2017.
 */

public  class MyPagerAdapter extends SmartFragmentStatePagerAdapter {
    private My_app app;

    private static int NUM_ITEMS = 3;

    public MyPagerAdapter(FragmentManager fragmentManager,My_app app) {

        super(fragmentManager);
       this.app=app;
    }

    // Returns total number of pages
    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    // Returns the fragment to display for that page
    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0: // Fragment # 0 - This will show FirstFragment
            {
                if(app.getFragmentname().contains("0")){
                     return  FirstFragment.newInstance(0, "First # 1");}
                if(app.getFragmentname().contains("1")){
                    return  SecondFragment.newInstance(0, "Page # 2");}
            }

            case 1: // Fragment # 0 - This will show FirstFragment different title
                return SecondFragment.newInstance(1, "Page # 2");
            case 2: // Fragment # 1 - This will show SecondFragment
                return ThirdFragment.newInstance(2, "Page # 3");
            default:
                return null;
        }
    }

    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {


        return "Page " + position ;
    }
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

}
