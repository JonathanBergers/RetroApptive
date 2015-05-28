package com.saxion.nl.retroapptive.controller;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.util.Log;
import android.view.ViewGroup;

import com.saxion.nl.retroapptive.MainActivity;
import com.saxion.nl.retroapptive.R;
import com.saxion.nl.retroapptive.model.Model;
import com.saxion.nl.retroapptive.model.Notitie;
import com.saxion.nl.retroapptive.view.ListViewFragment;
import com.saxion.nl.retroapptive.view.ObjectFragment;

/**
 * Created by falco on 28-5-15.
 */
public class CollectionPagerAdapter extends FragmentStatePagerAdapter {


    MainActivity activity;

    public CollectionPagerAdapter(FragmentManager fm, MainActivity activity) {

        super(fm);
        this.activity = activity;
    }



    @Override
    public Fragment getItem(int i) {





        return ObjectFragment.init(i);

    }




    @Override
    public int getCount() {
        return 3;
    }




    @Override
    public CharSequence getPageTitle(int position) {
        return getTap(position);
    }

    private String getTap(int i) {
        if(i == 0){
            // eerste tap
           return "Notities";
        } else if(i==1){
            //tweede tap
            return "User Stories";
        } else {
            return "iets anders";
        }

    }
}
