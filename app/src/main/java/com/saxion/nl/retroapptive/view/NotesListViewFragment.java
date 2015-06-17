package com.saxion.nl.retroapptive.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.saxion.nl.retroapptive.R;
import com.saxion.nl.retroapptive.activities.ItemDetailActivity;
import com.saxion.nl.retroapptive.controller.NoteAdapter;
import com.saxion.nl.retroapptive.model.ItemType;
import com.saxion.nl.retroapptive.model.Notitie;

import java.util.List;

/**
 * @Author Thomas
 */
public final class NotesListViewFragment extends ListViewFragment {

    public static NotesListViewFragment instance = null;

    private NoteAdapter noteAdapter;
    private static List<Notitie> notes;

    public static NotesListViewFragment newInstance(final List<Notitie> notes) {
        final NotesListViewFragment notesListViewFragment = new NotesListViewFragment();
        notesListViewFragment.setArguments(new Bundle());
        NotesListViewFragment.notes = notes;
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

        listView.setAdapter(noteAdapter);
    }


}
