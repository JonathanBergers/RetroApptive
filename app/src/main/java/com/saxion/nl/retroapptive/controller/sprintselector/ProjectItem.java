package com.saxion.nl.retroapptive.controller.sprintselector;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.saxion.nl.retroapptive.R;
import com.saxion.nl.retroapptive.model.Project;

/**
 * Created by Thomas on 3-6-2015.
 */
public class ProjectItem implements Item {

    public static final int VIEW_TYPE = 1;
    private final Project project;

    public ProjectItem(final Project project){
        this.project = project;
    }

    @Override
    public int getViewType() {
        return VIEW_TYPE;
    }

    public Project getProject() {
        return project;
    }
}
