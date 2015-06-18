package com.saxion.nl.retroapptive.view;

import android.os.Bundle;
import android.util.Log;

import com.saxion.nl.retroapptive.R;
import com.saxion.nl.retroapptive.controller.LedenAdapter;
import com.saxion.nl.retroapptive.model.Notitie;
import com.saxion.nl.retroapptive.model.Profiel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by falco on 17-6-15.
 */
public final class LedenListViewFragment extends ListViewFragment {

    public static LedenListViewFragment instance = null;

    private LedenAdapter ledenAdapter;
    private List<Profiel> leden;


    public static LedenListViewFragment newInstance(final List<Profiel> leden) {
        final LedenListViewFragment ledenListViewFragment = new LedenListViewFragment();
        ledenListViewFragment.setArguments(new Bundle());
        ledenListViewFragment.leden = leden;
        return ledenListViewFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ledenAdapter = new LedenAdapter(getActivity(), R.layout.fragment_list_item_team, leden );
        instance = this;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listView.setAdapter(ledenAdapter);

    }
}
