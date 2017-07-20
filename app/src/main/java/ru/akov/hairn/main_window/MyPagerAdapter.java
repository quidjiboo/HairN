package ru.akov.hairn.main_window;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import ru.akov.hairn.My_app;
import ru.akov.hairn.helpers.SmartFragmentStatePagerAdapter;
import ru.akov.hairn.main_window.data_pick_fragment.DatePickerFragment;
import ru.akov.hairn.main_window.zakaz_fragment.ZakazFragment;

/**
 * Created by Alexandr on 12.06.2017.
 */

public  class
MyPagerAdapter extends SmartFragmentStatePagerAdapter {
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
            case 0: // Fragment # 0 - This will show FirstFragment_Select_Service_Type
            {
                if(app.getFragmentname().contains("0")){
                     return  FirstFragment_Select_Service_Type.newInstance(0, "Page # 1");}
                if(app.getFragmentname().contains("1")){
                return  Fragment_Select_Currect_Services.newInstance(0, "Page # 2",Sing_tone_choosings.getInstance().getTypes_of_shops());}
                if(app.getFragmentname().contains("2")){
                    return  DatePickerFragment.newInstance(0, "Page # 3");}
                if(app.getFragmentname().contains("3")){
                    return  ZakazFragment.newInstance(0, "Page # 4");}
            }

            case 1: // Fragment # 0 - This will show FirstFragment_Select_Service_Type different title
                return SecondFragment.newInstance(1, "Page # 1");
            case 2: // Fragment # 1 - This will show SecondFragment
                return ThirdFragment.newInstance(2, "Page # 1");
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
