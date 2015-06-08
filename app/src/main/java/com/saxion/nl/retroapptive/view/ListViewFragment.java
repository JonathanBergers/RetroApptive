package com.saxion.nl.retroapptive.view;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.saxion.nl.retroapptive.R;
import com.saxion.nl.retroapptive.activities.DetailActivity;
import com.saxion.nl.retroapptive.controller.NoteAdapter;
import com.saxion.nl.retroapptive.controller.ActionAdapter;
import com.saxion.nl.retroapptive.controller.UserStoryAdapter;
import com.saxion.nl.retroapptive.model.Model;

/**
 * Created by falco on 28-5-15.
 */
public final class ListViewFragment extends Fragment {

    public static final String ARG_OBJECT = "object";
    public static Bundle args;
    public ListView listView;
    public NoteAdapter noteAdapter;
    public UserStoryAdapter userStoryAdapter;
    public ActionAdapter actionAdapter;

    public int currentPosition;


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

        currentPosition = getArguments() != null ? getArguments().getInt(ListViewFragment.ARG_OBJECT) : 0;

       // noteAdapter = new NoteAdapter(getActivity(), R.layout.fragment_list_item_note, Model.getInstance().getNotes());
        //userStoryAdapter = new UserStoryAdapter(getActivity(), R.layout.fragment_list_item_user_story, Model.getInstance().getUserStories());
        //actionAdapter = new ActionAdapter(getActivity(), R.layout.fragment_list_item_note, Model.getInstance().getActions());


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


        return rootView;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        currentPosition = args.getInt(ListViewFragment.ARG_OBJECT);
        noteAdapter.currentPosition = currentPosition;
        userStoryAdapter.currentPosition = currentPosition;
        actionAdapter.currentPosition = currentPosition;
        Log.d("Position", ("" + currentPosition));


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent detailsIntent = new Intent(getActivity(), DetailActivity.class);
                detailsIntent.putExtra("position", i);
                if (listView.getAdapter().equals(noteAdapter)) {
                    detailsIntent.putExtra("list", DetailActivity.NOTES_LIST);
                } else if (listView.getAdapter().equals(userStoryAdapter)) {
                    detailsIntent.putExtra("list", DetailActivity.USERSTORIES_LIST);
                } else {
                    detailsIntent.putExtra("list", DetailActivity.ACTIONS_LIST);
                }

                getActivity().startActivityForResult(detailsIntent, 102);

            }
        });


        switch (currentPosition) {


            case 0: {

                listView.setAdapter(noteAdapter);


            }


            break;
            case 1: {

                listView.setAdapter(userStoryAdapter);
            }
            break;

            case 2: {
                listView.setAdapter(actionAdapter);
            }

            break;


        }


    }


}
