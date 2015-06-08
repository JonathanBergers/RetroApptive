package com.saxion.nl.retroapptive;

import android.app.ActionBar;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.widget.DrawerLayout;
import android.view.View;

import com.melnykov.fab.FloatingActionButton;
import com.saxion.nl.retroapptive.activities.ObjectActivity;
import com.saxion.nl.retroapptive.controller.CollectionPagerAdapter;
import com.saxion.nl.retroapptive.controller.sprintselector.SprintSelectorFragment;
import com.saxion.nl.retroapptive.model.Item;
import com.saxion.nl.retroapptive.model.Model;
import com.saxion.nl.retroapptive.model.Notitie;
import com.saxion.nl.retroapptive.model.Project;
import com.saxion.nl.retroapptive.model.Sprint;
import com.saxion.nl.retroapptive.view.NotesListViewFragment;

import java.util.List;

public class MainActivity extends BaseActivity{

    private static final String HOST = "http://topicus.apps.gedge.nl/todoapp/";

    public static MainActivity instance = null;

    public static Sprint currentSprint = null;

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private SprintSelectorFragment mSprintSelectorFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    private boolean userStoriesSynced = false;
    private boolean actionsSynced = false;

    // When requested, this adapter returns a DemoObjectFragment,
    // representing an object in the collection.
    CollectionPagerAdapter mCollectionPagerAdapter;
    ViewPager mViewPager;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;
        getLayoutInflater().inflate(R.layout.activity_main, drawer);

        final ActionBar actionBar = getActionBar();

        // Specify that tabs should be displayed in the action bar.
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Create a tab listener that is called when the user changes tabs.
        ActionBar.TabListener tabListener = new ActionBar.TabListener() {
            @Override
            public void onTabSelected(ActionBar.Tab tab, android.app.FragmentTransaction fragmentTransaction) {

            }

            @Override
            public void onTabUnselected(ActionBar.Tab tab, android.app.FragmentTransaction fragmentTransaction) {
                // hide the given tab
            }

            @Override
            public void onTabReselected(ActionBar.Tab tab, android.app.FragmentTransaction fragmentTransaction) {
                // probably ignore this event
            }

        };

        for (int i = 0; i < 3; i++) {
            actionBar.addTab(
                    actionBar.newTab()
                            .setText("Tab " + (i + 1))
                            .setTabListener(tabListener));
        }

        fragmentManager = getFragmentManager();

        mCollectionPagerAdapter =
                new CollectionPagerAdapter(getFragmentManager(), this);

        mViewPager = (ViewPager) findViewById(R.id.pager);

        mViewPager.setAdapter(mCollectionPagerAdapter);

        //link.setHref("http://145.76.115.243:8080/restful/objects/TODO/1");
        //GetItemsTask getNoteTask = new GetItemsTask(ActionResult.class);

        //getNoteTask.execute(link);

        //mCollectionPagerAdapter.notifyDataSetChanged();

        //setting de plusbutton
        FloatingActionButton plusButton = (FloatingActionButton) findViewById(R.id.fab);
        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ObjectActivity.class);
                i.putExtra("item", (mViewPager.getCurrentItem()));
                Log.d("Item", "" + mViewPager.getCurrentItem());
                startActivityForResult(i, 100);
            }
        });

    }

    //TODO OPHALEN VAN USER STORIES bij oncreate.

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK) {
            mCollectionPagerAdapter.notifyDataSetChanged();

        }

    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void getNotes() {

    }

}
