package com.saxion.nl.retroapptive.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

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


    public View inflateView(LayoutInflater inflater, View convertView, ViewGroup parent) {
        return inflater.inflate(R.layout.fragment_list_item_user_story, parent, false);
    }

    @Override
    public void setData(View convertedView, int position) {




        title.setText(Model.getInstance().userStories.get(position).getDescription());
        summary.setText(Model.getInstance().userStories.get(position).getSummary());
        isBurned.setChecked(Model.getInstance().userStories.get(position).isBurned());

    }

    @Override
    public void addViews(View convertedView, int position) {

        isBurned = (CheckBox) convertedView.findViewById(R.id.checkListUserStoryBurned);
        isBurned.setFocusable(false);


    }
}
