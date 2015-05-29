package com.saxion.nl.retroapptive.view;

import android.app.Fragment;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.saxion.nl.retroapptive.R;
import com.saxion.nl.retroapptive.controller.NoteAdapter;
import com.saxion.nl.retroapptive.controller.ReactionAdapter;
import com.saxion.nl.retroapptive.controller.UserStoryAdapter;
import com.saxion.nl.retroapptive.model.Model;
import com.saxion.nl.retroapptive.model.Notitie;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by falco on 28-5-15.
 */
public final class ListViewFragment extends Fragment {
    public static final String ARG_OBJECT = "object";
    public static Bundle args;
    public ListView listView;
    public static NoteAdapter noteAdapter;
    public  ReactionAdapter reactionAdapter;

    int currentPosition;


    public static ListViewFragment init(int value) {
        ListViewFragment fragment = new ListViewFragment();
        // Supply val input as an argument.
        Bundle args = new Bundle();
        args.putInt(ListViewFragment.ARG_OBJECT, value);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        currentPosition= getArguments() != null ? getArguments().getInt(ListViewFragment.ARG_OBJECT) : 0;

        noteAdapter = new NoteAdapter(getActivity(), R.layout.fragment_list_item, Model.getInstance().notes);
        reactionAdapter = new ReactionAdapter(getActivity(), R.layout.fragment_list_item, Model.getInstance().reactions);




    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // The last two arguments ensure LayoutParams are inflated
        // properly.


        View rootView = inflater.inflate(
                R.layout.list, container, false);
        args = getArguments();

        listView = (ListView) rootView.findViewById(R.id.listView);

        noteAdapter.notifyDataSetChanged();


        return rootView;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);




        currentPosition = args.getInt(ListViewFragment.ARG_OBJECT);
        Log.d("Position", ("" + currentPosition));
















        switch (currentPosition){


            case 0 : {
                Log.d("Position", "JAAAAAAAAAAAAAA");
                listView.setAdapter(noteAdapter);



            }


            break;
            case 1 : {

                listView.setAdapter(reactionAdapter);
            }

            case 2 : {
                UserStoryAdapter adapter = new UserStoryAdapter(getActivity(), R.layout.fragment_list_item, null);
            }

            break;



        }









    }



    public void updateListView() {
        if(noteAdapter!=null){
            noteAdapter.notifyDataSetChanged();
        }


    }
}
