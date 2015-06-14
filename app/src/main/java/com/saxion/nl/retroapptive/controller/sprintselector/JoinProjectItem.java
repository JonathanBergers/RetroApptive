package com.saxion.nl.retroapptive.controller.sprintselector;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.saxion.nl.retroapptive.R;
import com.saxion.nl.retroapptive.model.Project;

/**
 * Created by Thomas on 3-6-2015.
 */
public class JoinProjectItem implements Item {

    public static final int VIEW_TYPE = 3;

    @Override
    public int getViewType() {
        return VIEW_TYPE;
    }
}
