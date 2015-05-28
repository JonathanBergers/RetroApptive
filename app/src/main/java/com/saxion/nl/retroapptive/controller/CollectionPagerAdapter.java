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

        Fragment fragment = new ObjectFragment();
        Bundle args = new Bundle();

        Log.d("TAB", ("" + i));





//    ListViewFragment listViewFragment = new ListViewFragment();
//
//
//    listViewFragment.setListAdapter(new ItemAdapter<Notitie>(activity, R.layout.fragment_list_item, Model.getInstance().notes));
//
//    //fragment.getChildFragmentManager().beginTransaction().add(1, listViewFragment);
//
//    fragment = listViewFragment;




       args.putCharSequence(ObjectFragment.ARG_OBJECT, getTap(i));
//
//

       fragment.setArguments(args);


        return fragment;
    }



    @Override
    public int getCount() {
        return 100;
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
