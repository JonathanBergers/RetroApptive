package com.saxion.nl.retroapptive.controller;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentStatePagerAdapter;

import com.saxion.nl.retroapptive.activities.MainActivity;
import com.saxion.nl.retroapptive.model.Actie;
import com.saxion.nl.retroapptive.model.Notitie;
import com.saxion.nl.retroapptive.view.ActiesListViewFragment;
import com.saxion.nl.retroapptive.view.NotesListViewFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by falco on 28-5-15.
 */
public class CollectionPagerAdapter extends FragmentStatePagerAdapter {

    private String[] pageTitles = new String[] {"Notities", "Acties"};//"User stories", "Acties"};
    private MainActivity activity;
    private List<Notitie> notities = new ArrayList<>();
    private List<Actie> acties = new ArrayList<>();

    public CollectionPagerAdapter(FragmentManager fm, MainActivity activity) {
        super(fm);
        this.activity = activity;
    }

    public List<Notitie> getNotities() {
        return notities;
    }

    public List<Actie> getActies() { return acties; }

    public void notifyNoteChanges() {

    }

    @Override
    public Fragment getItem(int i) {
        switch(i){
            case 0:
                return NotesListViewFragment.newInstance(notities);
            case 1:
                return ActiesListViewFragment.newInstance(acties);
        }
        return null;
    }

    @Override
    public int getCount() {
        return pageTitles.length;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return pageTitles[position];
    }

}
