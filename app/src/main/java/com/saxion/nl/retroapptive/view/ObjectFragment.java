package com.saxion.nl.retroapptive.view;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.saxion.nl.retroapptive.R;

/**
 * Created by falco on 28-5-15.
 */
public final class ObjectFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // The last two arguments ensure LayoutParams are inflated
        // properly.
        View rootView = inflater.inflate(
                R.layout.fragment_list, container, false);
        Bundle args = getArguments();

        return rootView;
    }
}
