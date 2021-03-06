package com.saxion.nl.retroapptive.activities;

import android.app.ActionBar;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.saxion.nl.retroapptive.R;
import com.saxion.nl.retroapptive.controller.sprintselector.ProjectItem;
import com.saxion.nl.retroapptive.controller.sprintselector.SprintItem;
import com.saxion.nl.retroapptive.controller.sprintselector.SprintSelectorFragment;
import com.saxion.nl.retroapptive.model.Actie;
import com.saxion.nl.retroapptive.model.Item;
import com.saxion.nl.retroapptive.model.Model;
import com.saxion.nl.retroapptive.model.Notitie;
import com.saxion.nl.retroapptive.model.Project;
import com.saxion.nl.retroapptive.model.Sprint;
import com.saxion.nl.retroapptive.view.ActiesListViewFragment;
import com.saxion.nl.retroapptive.view.NotesListViewFragment;

import java.io.IOException;
import java.util.List;

import scanner.ZBarConstants;
import scanner.ZBarScannerActivity;

/**
 * Created by Jelle on 8-6-2015.
 * Deze activity zorgt dat de navigation drawer op elke
 */

public class BaseActivity extends FragmentActivity implements SprintSelectorFragment.NavigationDrawerCallbacks {

    private SprintSelectorFragment mNavigationDrawerFragment;
    protected FrameLayout drawer;

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
                    final List<Project> projects = Model.getInstance().getProjects();

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

        final List<Notitie> oldNotes = NotesListViewFragment.instance.getNotes();
        final List<Actie> oldActions = ActiesListViewFragment.instance.getActies();
        //final List<Profiel> oldLeden = LedenListViewFragment.instance.getLeden();

        Log.d("BASEACTIVITTY", "OLDNOTES SIZE: " + oldNotes.size());

        oldNotes.clear();
        oldActions.clear();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    final List<Item> items = Model.getInstance().getItems(sprint);
                    for (Item item : items) {
                        if (item instanceof Notitie) {
                            oldNotes.add((Notitie) item);
                            Log.d("BASEACTIVITTY", item.getDescription() + ": ADDED");
                        } else if(item instanceof Actie){
                            oldActions.add((Actie) item);
                            Log.d("BASEACTIVITTY", item.getDescription() + ": ADDED");
                        } else {
                            Log.d("BASEACTIVITTY", item.getDescription() + ": NOT ADDED");
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        NotesListViewFragment.instance.getNoteAdapter().notifyDataSetChanged();
                        ActiesListViewFragment.instance.getActionAdapter().notifyDataSetChanged();
                        //MainActivity.instance.getmCollectionPagerAdapter().notifyNoteChanges();
                    }
                });
            }
        }).start();
    }

    /**
     * v1 format:
     * v1:type:projectIdentifier
     * type = isis only now
     * projectIdentifier = identifier for the data gatherer to use to load the project
     *
     * For isis:
     * Project link: http://topicus.apps.gedge.nl/simpleapp/restful/objects/domainapp.dom.data.project.Project/L_1
     * would give the following projectIdentifier:
     * objects/domainapp.dom.data.project.Project/L_1
     *
     * We do not include the first part of the url, so if the server changes address the QR code will still work.
     */
    private void joinProject(final String qrCode) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (qrCode.startsWith("v1:")) {
                    final String[] args = qrCode.split(":");
                    try {
                        final String type = args[1];
                        final String projectIdentifier = args[2];
                        Model.getInstance().joinProject(projectIdentifier);
                    } catch (Exception e) {
                        final String msg;
                        if (e instanceof  IOException) { //not a seperate catch clause to avoid boilerplate code
                            msg = "Error connecting to server";
                        } else {
                            msg = "Could not join project (malformed QR code?)";
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(BaseActivity.this, msg, Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
                loadProjects();
            }
        }).start();
    }

    private static final int ZBAR_SCANNER_REQUEST = 0;
    private static final int ZBAR_QR_SCANNER_REQUEST = 1;

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        if (position == 0) {//join project
            if (isCameraAvailable()) {
                //BELNGRIJK
                Intent intent = new Intent(this, ZBarScannerActivity.class);
                intent.putExtra(ZBarConstants.SCAN_MODES, new int[]{64});
                startActivityForResult(intent, ZBAR_QR_SCANNER_REQUEST);
                //TOT HIER
            } else {
                Toast.makeText(this, "Rear Facing Camera Unavailable", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        final Sprint sprint = mNavigationDrawerFragment.getSelectedSprint();
        MainActivity.currentSprint = sprint;
        System.out.println("SELECTED SPRINT: " + sprint.getProject().getName() + ":" + sprint.getSprintID());
        loadNotes(sprint);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case ZBAR_SCANNER_REQUEST:
            case ZBAR_QR_SCANNER_REQUEST:
                if (resultCode == RESULT_OK) {
                    final String qrCode = data.getStringExtra(ZBarConstants.SCAN_RESULT);
                    if (qrCode.startsWith("joinProject:")) {
                        joinProject(qrCode.substring(qrCode.indexOf(":") + 1));
                    }
                } else if (resultCode == RESULT_CANCELED && data != null) {
                    String error = data.getStringExtra(ZBarConstants.ERROR_INFO);
                    if (!TextUtils.isEmpty(error)) {
                        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
    }

    public boolean isCameraAvailable() {
        PackageManager pm = getPackageManager();
        return pm.hasSystemFeature(PackageManager.FEATURE_CAMERA);
    }

    protected boolean isDrawerOpen() {
        return mNavigationDrawerFragment.isDrawerOpen();
    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(getTitle());
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        if(item.getItemId()== R.id.action_logout){
            this.finish();
        } else if(item.getItemId() == R.id.action_settings){
            startActivity(new Intent(this, ProfileActivity.class));
        }
        return super.onMenuItemSelected(featureId, item);
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
