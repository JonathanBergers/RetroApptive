package com.saxion.nl.retroapptive.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.saxion.nl.retroapptive.R;
import com.saxion.nl.retroapptive.model.Model;
import com.saxion.nl.retroapptive.model.UserStory;

import java.util.List;

/**
 * Created by jonathan on 28-5-15.
 */
public class UserStoryAdapter extends ItemAdapter<UserStory> {

    private CheckBox isBurned;



    public UserStoryAdapter(Context context, int resource, List<UserStory> obejects) {

        super(context, resource, obejects);
    }




    @Override
    public void setData(View convertedView, int position) {

        title.setText(Model.getInstance().getUserStory(position).getDescription());
        summary.setText(Model.getInstance().getUserStory(position).getSummary());
        isBurned.setChecked(Model.getInstance().getUserStory(position).isBurned());

    }

    @Override
    public void addViews(View convertedView, int position) {



        isBurned = (CheckBox) convertedView.findViewById(R.id.checkListUserStoryBurned);
        isBurned.setFocusable(false);


    }
}
