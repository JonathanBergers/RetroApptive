package com.saxion.nl.retroapptive.controller;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.saxion.nl.retroapptive.R;
import com.saxion.nl.retroapptive.model.Model;
import com.saxion.nl.retroapptive.model.Notitie;

import java.util.List;

/**
 * Created by jonathan on 28-5-15.
 */
public class NoteAdapter extends ItemAdapter<Notitie> {

    private List<Notitie> notities;
    private TextView category;

    public NoteAdapter(Context context, int resource, List<Notitie> objects) {
        super(context, resource, objects);
        this.notities = objects;
    }

    @Override
    public void setData(View convertedView, int position) {
        title.setText(notities.get(position).getDescription());
        summary.setText(notities.get(position).getSummary());
        category.setText(notities.get(position).getSubcategory());
    }

    @Override
    public void addViews(View convertedView, int position) {

        category = (TextView) convertedView.findViewById(R.id.textViewItemCategory);
    }


}
