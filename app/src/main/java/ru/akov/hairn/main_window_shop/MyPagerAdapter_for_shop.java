package ru.akov.hairn.main_window_shop;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import ru.akov.hairn.My_app;
import ru.akov.hairn.helpers.SmartFragmentStatePagerAdapter;
import ru.akov.hairn.main_window_client.SecondFragment;
import ru.akov.hairn.main_window_client.ThirdFragment;

/**
 * Created by Alexandr on 12.06.2017.
 */

public  class
MyPagerAdapter_for_shop extends SmartFragmentStatePagerAdapter {
    private My_app app;

    private static int NUM_ITEMS1 = 3;

    public MyPagerAdapter_for_shop(FragmentManager fragmentManager, My_app app) {

        super(fragmentManager);
       this.app=app;
    }

    // Returns total number of pages
    @Override
    public int getCount() {
        return NUM_ITEMS1;
    }

    // Returns the fragment to display for that page
    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0: // Fragment # 0 - This will show FirstFragment_Select_Service_Type
            { // return FirstFragment_myshopslist.newInstance(0, "Page # 1");
               if(app.getshopFragmentname().contains("0")){
                     return  FirstFragment_myshopslist.newInstance(0, "Page # 1");}
                if(app.getshopFragmentname().contains("1")){
                    Log.d("ХУЙНЯ",Shop_singltone.getInstance().getcurid_shop());
                return  SecondFragment_myshopzakazllist.newInstance(0, "Page # 2");}
               /*  if(app.getFragmentname().contains("2")){
                    return  DatePickerFragment.newInstance(0, "Page # 3");}
                if(app.getFragmentname().contains("3")){
                    return  ZakazFragment.newInstance(0, "Page # 4");}*/
            }

            case 1: // Fragment # 0 - This will show FirstFragment_Select_Service_Type different title
                return SecondFragment.newInstance(1, "Page # 34");
            case 2: // Fragment # 1 - This will show SecondFragment
                return ThirdFragment.newInstance(2, "Page #56561");
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
      /*  if (object instanceof ThirdFragment || object instanceof  SecondFragment) {
            return POSITION_UNCHANGED; // don't force a reload
        } else {
            // POSITION_NONE means something like: this fragment is no longer valid
            // triggering the ViewPager to re-build the instance of this fragment.
            return POSITION_NONE;
        }*/
        return POSITION_NONE;
    }

}
