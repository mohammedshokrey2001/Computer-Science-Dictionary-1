package com.example.csdict.UserFragments.ui.main;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.csdict.DataModels.DataModelUser;
import com.example.csdict.R;
import com.example.csdict.UserFragments.ui.Fragment1;
import com.example.csdict.UserFragments.ui.Fragment2;
import com.example.csdict.UserFragments.ui.Fragment3;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {


    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2,R.string.tab_text_3};
    private final Context mContext;
    private final Bundle bundle;


    public SectionsPagerAdapter(Context context, FragmentManager fm, Bundle bundle) {
        super(fm);
        mContext = context;
        this.bundle = bundle;
    }




    @Override
    public Fragment getItem(int position) {
      Fragment fragment = null;

      DataModelUser modelUser ;

      switch (position){
          case 0 :
          {
              fragment = new Fragment1();
              fragment.setArguments(bundle);
              break;
          }
          case 1 :{
              fragment = new Fragment2();
              fragment.setArguments(bundle);
              break;
          }

          case 2 :{
              fragment = new Fragment3();
              fragment.setArguments(bundle);

              break;
          }

      }

      return  fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 3;
    }
}