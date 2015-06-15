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
import com.saxion.nl.retroapptive.controller.UserStoryAdapter;
import com.saxion.nl.retroapptive.model.Model;
import com.saxion.nl.retroapptive.model.Notitie;
import com.saxion.nl.retroapptive.model.Sprint;

import java.util.List;

/**
 * @Author Thomas
 */
public final class NotesListViewFragment extends ListViewFragment {

    public static NotesListViewFragment instance = null;

    private NoteAdapter noteAdapter;
    private List<Notitie> notes;

    public static NotesListViewFragment newInstance(final List<Notitie> notes) {
        final NotesListViewFragment notesListViewFragment = new NotesListViewFragment();
        notesListViewFragment.setArguments(new Bundle());
        notesListViewFragment.notes = notes;
        Log.d("NOTELISTVIEWFR" , "NEWINSTANCE");
        return notesListViewFragment;
    }

    public NoteAdapter getNoteAdapter() {
        return noteAdapter;
    }

    public List<Notitie> getNotes() {
        return notes;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        noteAdapter = new NoteAdapter(getActivity(), R.layout.fragment_list_item_note, notes);
        instance = this;


        Log.d("NOTELISTVIEWFR" , "CREATED");
    }




    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent detailsIntent = new Intent(getActivity(), DetailActivity.class);
                detailsIntent.putExtra("position", i);
                detailsIntent.putExtra("list", DetailActivity.NOTES_LIST);
                getActivity().startActivityForResult(detailsIntent, 102);
            }
        });
        listView.setAdapter(noteAdapter);
    }


}
