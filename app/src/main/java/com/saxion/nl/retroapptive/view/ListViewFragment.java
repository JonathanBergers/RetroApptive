package com.saxion.nl.retroapptive.view;

import android.app.Fragment;
import android.app.ListFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.saxion.nl.retroapptive.R;
import com.saxion.nl.retroapptive.controller.ItemAdapter;
import com.saxion.nl.retroapptive.model.Item;
import com.saxion.nl.retroapptive.model.Model;
import com.saxion.nl.retroapptive.model.Notitie;

import java.util.ArrayList;


public class ListViewFragment extends Fragment {




    ListView list;







    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Inflate the layout
        View root = inflater.inflate(R.layout.fragment_list, container, false);

        //Inflate the listView
        list = (ListView) root.findViewById(R.id.listViewItems);


        Log.d("LISTVIEWFRAGMENT", "INFLATED");









        return root;
    }


    public ListView getListView(){
        return list;
    }

}
