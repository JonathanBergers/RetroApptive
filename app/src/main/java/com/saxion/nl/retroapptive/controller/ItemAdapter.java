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
public  class ItemAdapter<T> extends ArrayAdapter<T>{



    protected TextView title;
    protected TextView summary;
    protected TextView sprintNumber;

    private TextView category;

    //private ....View sprint
    // private ...View points / priority
    // private ..View Attachment ?






    public ItemAdapter(Context context, int resource, List<T> objects) {
        super(context, resource, objects);


    }


    @Override
     public View getView(int position, View convertView, ViewGroup parent) {



        if(convertView == null){

            LayoutInflater inflater = (LayoutInflater) super.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflateView(inflater, convertView, parent);
        }





        //DATA IS THE SAME FOR EVERY ITEM ??
        title = (TextView) convertView.findViewById(R.id.textViewNoteTitle);
        summary = (TextView) convertView.findViewById(R.id.textViewNotesummary);
        //sprintNumber = (TextView) convertView.findViewById(R.id.textViewSprintNumber);

        addViews(convertView, position);



        setData(convertView, position);


        return convertView;
    }



    public  View inflateView(LayoutInflater inflater, View convertView, ViewGroup parent){

        return inflater.inflate(R.layout.fragment_list_item, parent, false);
    }


    public   void setData(View convertedView, int position){






    }


    public   void addViews(View convertedView, int position){


    }


    public View getNoteView(){


        //TODO Hier zet je de data in de view
return  null;

    }


    public View getUserStoryView(){
//TODO Hier zet je de data in de view
        return  null;
    }


    public View getReactionView(){
//TODO Hier zet je de data in de view
        return  null;

    }


}
