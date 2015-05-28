package com.saxion.nl.retroapptive.controller;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v13.app.FragmentStatePagerAdapter;

import com.saxion.nl.retroapptive.view.ObjectFragment;

/**
 * Created by falco on 28-5-15.
 */
public class CollectionPagerAdapter extends FragmentStatePagerAdapter {
    public CollectionPagerAdapter(FragmentManager fm) {
        super(fm);
    }



    @Override
    public Fragment getItem(int i) {
        Fragment fragment = new ObjectFragment();
        Bundle args = new Bundle();
        // Our object is just an integer :-P
        args.putInt(ObjectFragment.ARG_OBJECT, i + 1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getCount() {
        return 100;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "OBJECT " + (position + 1);
    }
}
