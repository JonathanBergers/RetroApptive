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

    private TextView category;

    public NoteAdapter(Context context, int resource, List<Notitie> objects) {
        super(context, resource, objects);

    }




    @Override
    public void setData(View convertedView, int position) {

        title.setText(Model.getInstance().getNote(position).getDescription());
        summary.setText(Model.getInstance().getNote(position).getSummary());
        category.setText(Model.getInstance().getNote(position).getCategory());
    }

    @Override
    public void addViews(View convertedView, int position) {

        category = (TextView) convertedView.findViewById(R.id.textViewListNoteCategory);
    }
}
