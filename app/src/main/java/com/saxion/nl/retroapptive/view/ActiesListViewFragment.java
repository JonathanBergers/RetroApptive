package com.saxion.nl.retroapptive.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.saxion.nl.retroapptive.R;
import com.saxion.nl.retroapptive.activities.ItemDetailActivity;
import com.saxion.nl.retroapptive.controller.ActionAdapter;
import com.saxion.nl.retroapptive.model.Actie;

import java.util.List;

/**
 * Created by falco on 15-6-15.
 */
public class ActiesListViewFragment extends ListViewFragment {

    public static ActiesListViewFragment instance = null;

    private ActionAdapter actionAdapter;
    private List<Actie> acties;

    public static ActiesListViewFragment newInstance(final List<Actie> acties) {
        final ActiesListViewFragment actiesListViewFragment = new ActiesListViewFragment();
        actiesListViewFragment.setArguments(new Bundle());
        actiesListViewFragment.acties = acties;
        return actiesListViewFragment;

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        actionAdapter = new ActionAdapter(getActivity(), R.layout.fragment_list_item_action, acties);
        instance = this;



    }
    public ActionAdapter getActionAdapter(){ return actionAdapter; }

    public List<Actie> getActies(){ return acties; }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        listView.setAdapter(actionAdapter);
    }
}
