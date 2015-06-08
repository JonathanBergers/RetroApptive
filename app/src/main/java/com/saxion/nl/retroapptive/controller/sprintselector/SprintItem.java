package com.saxion.nl.retroapptive.controller.sprintselector;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.saxion.nl.retroapptive.R;
import com.saxion.nl.retroapptive.model.Project;
import com.saxion.nl.retroapptive.model.Sprint;

/**
 * Created by Thomas on 3-6-2015.
 */
public class SprintItem implements Item {

    public static final int VIEW_TYPE = 2;
    private final Sprint sprint;

    public SprintItem(final Sprint sprint){
        this.sprint = sprint;
    }

    @Override
    public int getViewType() {
        return VIEW_TYPE;
    }

    public Sprint getSprint() {
        return sprint;
    }
}
