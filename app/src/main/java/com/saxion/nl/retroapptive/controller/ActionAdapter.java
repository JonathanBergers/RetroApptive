package com.saxion.nl.retroapptive.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.saxion.nl.retroapptive.R;
import com.saxion.nl.retroapptive.model.Model;
import com.saxion.nl.retroapptive.model.Action;

import java.util.List;

/**
 * Created by jonathan on 28-5-15.
 */
public class ActionAdapter extends ItemAdapter<Action> {
    public ActionAdapter(Context context, int resource, List<Action> obejects) {
        super(context, resource, obejects);
    }





    @Override
    public void setData(View convertedView, int position) {

        title.setText(Model.getInstance().getAction(position).getDescription());
        summary.setText(Model.getInstance().getAction(position).getSummary());

    }

    @Override
    public void addViews(View convertedView, int position) {


    }
}
