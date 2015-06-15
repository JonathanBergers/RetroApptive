package com.saxion.nl.retroapptive.view;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.saxion.nl.retroapptive.R;
import com.saxion.nl.retroapptive.activities.DetailActivity;
import com.saxion.nl.retroapptive.controller.NoteAdapter;
import com.saxion.nl.retroapptive.controller.ActionAdapter;
import com.saxion.nl.retroapptive.controller.UserStoryAdapter;
import com.saxion.nl.retroapptive.model.Model;

/**
 * Created by falco on 28-5-15.
 */
public class ListViewFragment extends Fragment {

    protected ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // The last two arguments ensure LayoutParams are inflated
        // properly.
        View rootView = inflater.inflate(
                R.layout.list, container, false);
        listView = (ListView) rootView.findViewById(R.id.listView);
        return rootView;
    }


}
