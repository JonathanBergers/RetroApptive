package com.saxion.nl.retroapptive;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.widget.FrameLayout;

import com.saxion.nl.retroapptive.controller.sprintselector.ProjectItem;
import com.saxion.nl.retroapptive.controller.sprintselector.SprintItem;
import com.saxion.nl.retroapptive.controller.sprintselector.SprintSelectorFragment;
import com.saxion.nl.retroapptive.model.Item;
import com.saxion.nl.retroapptive.model.Model;
import com.saxion.nl.retroapptive.model.Notitie;
import com.saxion.nl.retroapptive.model.Project;
import com.saxion.nl.retroapptive.model.Sprint;
import com.saxion.nl.retroapptive.view.NotesListViewFragment;

import java.util.ArrayList;
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
        loadProjects();
    }

    public void loadProjects() {
        final List<com.saxion.nl.retroapptive.controller.sprintselector.Item> items = mNavigationDrawerFragment.getItems();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Log.d("LOADING", "LOADING");
                    final List<Project> projects = Model.getInstance().getProjects();

                    Log.d("LOADING2", "LOADING2");
                    items.clear();

                    mNavigationDrawerFragment.setSelectedSprint(null);

                    for (Project project : projects) {
                        items.add(new ProjectItem(project));
                        final List<Sprint> sprints = Model.getInstance().getSprints(project);
                        for (Sprint sprint : sprints) {
                            items.add(new SprintItem(sprint));
                            mNavigationDrawerFragment.setSelectedSprint(sprint);
                            mNavigationDrawerFragment.setCurrentSelectedPosition(items.size() - 1);
                        }
                    }

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mNavigationDrawerFragment.getProjectsArrayAdapter().notifyDataSetChanged();
                            if (mNavigationDrawerFragment.getSelectedSprint() != null) {
                                mNavigationDrawerFragment.selectItem(mNavigationDrawerFragment.getCurrentSelectedPosition());
                            }
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void loadNotes(final Sprint sprint) {
        NotesListViewFragment.newInstance(new ArrayList<Notitie>());
        final List<Notitie> oldNotes = NotesListViewFragment.instance.getNotes();
        Log.d("BASEACTIVITTY", "OLDNOTES SIZE: " + oldNotes.size());

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

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        final Sprint sprint = mNavigationDrawerFragment.getSelectedSprint();
        currentSprint = sprint;
        System.out.println("SELECTED SPRINT: " + sprint.getProject().getName() + ":" + sprint.getSprintID());
        loadNotes(sprint);
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
