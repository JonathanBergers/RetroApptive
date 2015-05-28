package com.saxion.nl.retroapptive.view;

import android.app.Fragment;
import android.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.saxion.nl.retroapptive.R;
import com.saxion.nl.retroapptive.controller.ItemAdapter;
import com.saxion.nl.retroapptive.controller.NoteAdapter;
import com.saxion.nl.retroapptive.model.Model;
import com.saxion.nl.retroapptive.model.Notitie;

import java.util.List;

/**
 * Created by falco on 28-5-15.
 */
public final class ObjectFragment extends Fragment {
    public static final String ARG_OBJECT = "object";
    public static Bundle args;


    int currentPosition;


    public static ObjectFragment init(int value) {
        ObjectFragment fragment = new ObjectFragment();
        // Supply val input as an argument.
        Bundle args = new Bundle();
        args.putInt(ObjectFragment.ARG_OBJECT, value);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        currentPosition= getArguments() != null ? getArguments().getInt(ObjectFragment.ARG_OBJECT) : 1;




    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // The last two arguments ensure LayoutParams are inflated
        // properly.
        View rootView = inflater.inflate(
                R.layout.list, container, false);
        args = getArguments();


        Log.d("INFLATE", "ONCREATEVIEW");

        currentPosition = args.getInt(ObjectFragment.ARG_OBJECT);
        Log.d("Position", ("" + currentPosition));

        ListView listView = (ListView) rootView.findViewById(R.id.listView);
        listView.setSelection(currentPosition);


        switch (currentPosition){


            case 0 : {   NoteAdapter noteAdapter = new NoteAdapter(container.getContext(), R.layout.fragment_list_item, Model.getInstance().notes);
                listView.setAdapter(noteAdapter);}
            break;
            case 1 : {  // TODO Item adapter voor acties
             }

            case 2 : {  // TODO item adapter voor user stories
             }

            break;



        }








        return rootView;
    }



}
