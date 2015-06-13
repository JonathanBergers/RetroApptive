package com.saxion.nl.retroapptive;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.widget.FrameLayout;

import com.saxion.nl.retroapptive.controller.sprintselector.SprintSelectorFragment;
import com.saxion.nl.retroapptive.model.Item;
import com.saxion.nl.retroapptive.model.Model;
import com.saxion.nl.retroapptive.model.Notitie;
import com.saxion.nl.retroapptive.model.Sprint;
import com.saxion.nl.retroapptive.view.NotesListViewFragment;

import java.util.List;

/**
 * Created by Jelle on 8-6-2015.
 * Deze activity zorgt dat de navigation drawer op elke
 */

public class BaseActivity extends FragmentActivity implements SprintSelectorFragment.NavigationDrawerCallbacks {

    private SprintSelectorFragment mNavigationDrawerFragment;
    protected FrameLayout drawer;
    private Sprint currentSprint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer);

        drawer = (FrameLayout) findViewById(R.id.drawer_frame);

        mNavigationDrawerFragment = (SprintSelectorFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mNavigationDrawerFragment.setUp(
				R.id.navigation_drawer,
				(DrawerLayout) findViewById(R.id.drawer_layout));

    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        final Sprint sprint = mNavigationDrawerFragment.getSelectedSprint();
        currentSprint = sprint;
        System.out.println("SELECTED SPRINT: " + sprint.getProject().getName() + ":" + sprint.getSprintID());
        loadNotes(sprint);
    }

    public void loadNotes(final Sprint sprint) {
        final List<Notitie> oldNotes = NotesListViewFragment.instance.getNotes();
        oldNotes.clear();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    final List<Item> items = Model.getInstance().getItems(sprint);
                    for (Item item : items) {
                        if (item instanceof  Notitie) {
                            oldNotes.add((Notitie)item);
                            Log.d("BASEACTIVITTY", item.getDescription() + ": ADDED" );
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        NotesListViewFragment.instance.getNoteAdapter().notifyDataSetChanged();
                    }
                });
            }
        }).start();
    }

    protected boolean isDrawerOpen(){
        return mNavigationDrawerFragment.isDrawerOpen();
    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(getTitle());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }
}