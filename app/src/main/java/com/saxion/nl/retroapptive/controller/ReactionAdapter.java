package com.saxion.nl.retroapptive.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.saxion.nl.retroapptive.R;
import com.saxion.nl.retroapptive.model.Model;
import com.saxion.nl.retroapptive.model.Reaction;

import java.util.List;

/**
 * Created by jonathan on 28-5-15.
 */
public class ReactionAdapter extends ItemAdapter<Reaction> {
    public ReactionAdapter(Context context, int resource, List<Reaction> obejects) {
        super(context, resource, obejects);
    }


    @Override
    public View inflateView(LayoutInflater inflater, View convertView, ViewGroup parent) {
        return inflater.inflate(R.layout.fragment_list_item_note, parent, false);
    }

    @Override
    public void setData(View convertedView, int position) {




        title.setText(Model.getInstance().reactions.get(position).getDescription());
        summary.setText(Model.getInstance().reactions.get(position).getSummary());

    }

    @Override
    public void addViews(View convertedView, int position) {


    }
}
