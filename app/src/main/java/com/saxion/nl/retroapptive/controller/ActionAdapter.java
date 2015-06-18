package com.saxion.nl.retroapptive.controller;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.saxion.nl.retroapptive.R;
import com.saxion.nl.retroapptive.model.Actie;

import java.util.List;

/**
 * Created by jonathan on 28-5-15.
 */
public class ActionAdapter extends ItemAdapter<Actie> {
    private List<Actie> acties;
    public ActionAdapter(Context context, int resource, List<Actie> objects) {
        super(context, resource, objects);
        this.acties = objects;
    }





    @Override
    public void setData(View convertedView, int position) {

        title.setText(acties.get(position).getDescription());
        summary.setText(acties.get(position).getSummary());
    }

    @Override
    public void addViews(View convertedView, int position) {
        summary = (TextView) convertedView.findViewById(R.id.textViewListItemSummary);

    }

    @Override
    public View inflateView(View view, ViewGroup parent) {
        view = inflater.inflate(R.layout.fragment_list_item_action, parent, false);
        return  view;
    }
}
