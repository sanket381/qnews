package com.example.qnews;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class categoryAdapter extends FragmentPagerAdapter {

    private Context mcontext;

    public categoryAdapter(Context context, FragmentManager fm) {
        super(fm);
        mcontext=context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        if (position==0){
            return new Top_heqadings();
        }
        if(position==1) {
            return new Technology();
        }
        if(position==2) {
            return new Health();
        }
        if (position==3) {
            return new sports();
        }
        if (position==4) {
            return new Entertainment();
        }
        else
            return new Business();
    }

    @Override
    public int getCount() {
        return 6;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (position==0){
            return "TopHeadings";
        }
        if(position==1) {
            return "Technology";
        }
        if(position==2) {
            return "Health";
        }
        if (position==3) {
            return "Sports";
        }
        if (position==4) {
            return "Entertainment";
        }
        else
            return "Business";
    }
}
