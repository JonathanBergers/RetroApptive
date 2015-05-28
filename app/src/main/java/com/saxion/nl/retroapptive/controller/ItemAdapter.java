package com.saxion.nl.retroapptive.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.saxion.nl.retroapptive.R;
import com.saxion.nl.retroapptive.model.Item;
import com.saxion.nl.retroapptive.model.Model;
import com.saxion.nl.retroapptive.model.Notitie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by falco on 28-5-15.
 */
public class ItemAdapter<T> extends ArrayAdapter<T>{

    private TextView title;
    private TextView summary;
    private TextView category;





    public ItemAdapter(Context context, int resource, List<T> objects) {
        super(context, resource, objects);


    }


    @Override
     public View getView(int position, View convertView, ViewGroup parent) {



        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) super.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.fragment_list_item, parent, false);
        }

        title = (TextView) convertView.findViewById(R.id.textViewNoteTitle);
        summary = (TextView) convertView.findViewById(R.id.textViewNotesummary);
        category = (TextView) convertView.findViewById(R.id.textViewNoteCategory);

        title.setText(Model.getInstance().notes.get(position).getDescription());
        summary.setText(Model.getInstance().notes.get(position).getSummary());
        category.setText(Model.getInstance().notes.get(position).getCategory());


        return convertView;
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
