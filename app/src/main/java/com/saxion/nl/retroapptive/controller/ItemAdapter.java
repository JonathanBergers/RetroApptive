package com.saxion.nl.retroapptive.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.saxion.nl.retroapptive.R;

import java.util.List;

/**
 * Created by falco on 28-5-15.
 */
public class ItemAdapter<T> extends ArrayAdapter<T> {


    protected TextView title;
    protected TextView summary;
    protected TextView sprintNumber;

    private TextView category;

    protected LayoutInflater inflater;

    public int currentPosition = 0;

    public ItemAdapter(Context context, int resource, List<T> objects) {
        super(context, resource, objects);
    }

    //private ....View sprint
    // private ...View points / priority
    // private ..View Attachment ?


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        if (convertView == null) {

            inflater = (LayoutInflater) super.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);


            convertView = inflateView(convertView, parent);
            title = (TextView) convertView.findViewById(R.id.textViewListItemTitle);



        }


        addViews(convertView, position);


        setData(convertView, position);


        return convertView;
    }


    public void setData(View convertedView, int position) {

//OVERIDE


    }


    public void addViews(View convertedView, int position) {
//OVERIDE

    }

    public View inflateView(View view, ViewGroup parent){
        //OVERRIDE
        return null;
    }


}
