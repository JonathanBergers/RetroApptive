package com.saxion.nl.retroapptive.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.saxion.nl.retroapptive.R;
import com.saxion.nl.retroapptive.activities.MainActivity;
import com.saxion.nl.retroapptive.model.Model;
import com.saxion.nl.retroapptive.model.Profiel;

import java.util.List;

/**
 * Created by falco on 17-6-15.
 */
public class LedenAdapter extends ArrayAdapter<Profiel> {

    private TextView naam;
    private TextView type;

    public LedenAdapter(Context context, int resource, List<Profiel> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {

            LayoutInflater inflater = (LayoutInflater) super.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //convertView = inflater.inflate(R.layout.fragment_list_item_lid, parent);


        }

        //naam = (TextView) convertView.findViewById(R.id.textViewListItemLidNaam);
        //type = (TextView) convertView.findViewById(R.id.textViewRol);

        try {
            Profiel profiel = Model.getInstance().getMembers(MainActivity.currentSprint.getProject()).get(position);
            naam.setText(profiel.getName()+ " "+profiel.getSurname());
            type.setText(profiel.getProfileType().name());

        } catch (Exception e) {
            e.printStackTrace();
        }




        return convertView;
    }
}
