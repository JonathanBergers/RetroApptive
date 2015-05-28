package com.saxion.nl.retroapptive.view;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.saxion.nl.retroapptive.R;
import com.saxion.nl.retroapptive.controller.ItemAdapter;
import com.saxion.nl.retroapptive.model.Model;

import java.util.ArrayList;


public final class ListViewFragment extends Fragment {

    ListView list;
    public void addData(ArrayList<String> data){

        ItemAdapter notesAdapter = new ItemAdapter(getActivity(), R.layout.fragment_list_item, Model.getInstance().notes);

        list.setAdapter(notesAdapter);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_listview, container, false);

        list = (ListView) root.findViewById(R.id.listViewItems);


        addData(Model.getInstance().notesTestStrings);




        return root;
    }
    public ListView getListView(){
        return list;
    }

}
