package com.saxion.nl.retroapptive.controller;

import android.content.Context;
import android.view.View;

import com.saxion.nl.retroapptive.model.Actie;

import java.util.List;

/**
 * Created by jonathan on 28-5-15.
 */
public class ActionAdapter extends ItemAdapter<Actie> {
    public ActionAdapter(Context context, int resource, List<Actie> obejects) {
        super(context, resource, obejects);
    }





    @Override
    public void setData(View convertedView, int position) {

        //title.setText(Model.getInstance().getAction(position).getDescription());
        //summary.setText(Model.getInstance().getAction(position).getSummary());

    }

    @Override
    public void addViews(View convertedView, int position) {


    }
}
