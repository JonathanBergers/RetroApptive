package com.saxion.nl.retroapptive.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.saxion.nl.retroapptive.R;
import com.saxion.nl.retroapptive.view.ListViewFragment;


import java.util.List;

/**
 * Created by falco on 28-5-15.
 */
public  class ItemAdapter<T> extends ArrayAdapter<T>{



    protected TextView title;
    protected TextView summary;
    protected TextView sprintNumber;

    private TextView category;

    public int currentPosition = 0;

    public ItemAdapter(Context context, int resource, List<T> objects) {
        super(context, resource, objects);
    }

    //private ....View sprint
    // private ...View points / priority
    // private ..View Attachment ?









    @Override
     public View getView(int position, View convertView, ViewGroup parent) {



        if(convertView == null){

            LayoutInflater inflater = (LayoutInflater) super.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflateView(inflater, convertView, parent);
        }





        if(currentPosition == 0){
            title = (TextView) convertView.findViewById(R.id.textViewListNoteTitle);
            summary = (TextView) convertView.findViewById(R.id.textViewListNotesummary);
        } else {
            title = (TextView) convertView.findViewById(R.id.textViewListUserStoryTitle);
            summary = (TextView) convertView.findViewById(R.id.textViewListUserStorySummary);
        }





        addViews(convertView, position);



        setData(convertView, position);


        return convertView;
    }



    public  View inflateView(LayoutInflater inflater, View convertView, ViewGroup parent){


            return inflater.inflate(R.layout.fragment_list_item_note, parent, false);



    }


    public   void setData(View convertedView, int position){

//OVERIDE




    }


    public   void addViews(View convertedView, int position){
//OVERIDE

    }



}
